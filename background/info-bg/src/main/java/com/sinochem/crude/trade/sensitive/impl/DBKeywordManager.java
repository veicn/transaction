package com.sinochem.crude.trade.sensitive.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.sensitive.AbstractKeywordManager;
import com.sinochem.crude.trade.common.values.FlushDateUtils;
import com.sinochem.crude.trade.sensitive.DBKeywordProperties;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by GHuang on 2016/12/19.
 * Sensitive word set loader.
 */
public class DBKeywordManager extends AbstractKeywordManager {

    private static final Log log = LogFactory.getLog(DBKeywordManager.class);

    private JdbcTemplate jdbcTemplate;

    private DBKeywordProperties properties;

    private String createDateSql;

    private String updateDateSql;

    private long lastDate = 0;

    public DBKeywordManager(JdbcTemplate jdbcTemplate, DBKeywordProperties properties) {
        this.jdbcTemplate = jdbcTemplate;
        this.properties = properties;
        this.createDateSql = "select max(`" + properties.getCreateDateField() + "`) from " +
                properties.getTable();
        this.updateDateSql = "select max(`" + properties.getUpdateDateField() + "`) from " +
                properties.getTable();
    }

    @Override
    public Collection<String> getKeywords() {
        log.info("Loading sensitive words form DB...");
        StringBuilder sql = new StringBuilder("select `");
        if ( !Strings.isNullOrEmpty(properties.getLevelColumn()) ) {
            sql.append(properties.getLevelColumn()).append("` as `level`,`");
        }
        sql.append(properties.getColumn())
                .append("` as `keyword`")
                .append(" from ")
                .append(properties.getTable())
                .append(" where `ALIVE_FLAG` = '1'");
        if ( log.isDebugEnabled() ) {
            log.debug("Load sensitive words. SQL: " + sql);
        }
        List<String> words = Lists.newArrayList();
        try {
            List<Map<String, String>> maps = jdbcTemplate.query(sql.toString(), new RowMapper<Map<String, String>>() {
                @Override
                public Map<String, String> mapRow(ResultSet resultSet, int i) throws SQLException {
                    Map<String, String> map = Maps.newHashMap();
                    map.put("keyword", resultSet.getString("keyword"));
                    String level = resultSet.getString("level");
                    if ( !Strings.isNullOrEmpty(level) ) {
                        map.put("level", level);
                    }
                    return map;
                }
            });
            for (Map<String, String> map : maps) {
                if (map.containsKey("level")) {
                    words.add("#" + map.get("level") + "#" + map.get("keyword"));
                } else {
                    words.add(map.get("keyword"));
                }
            }
        } catch (DataAccessException e) {
            throw new BizException("Cannot load sensitive words.", e);
        }
        lastDate = determineLastDate();
        return words;
    }

    public String reload() {
        return load();
    }

    @Scheduled(fixedRate = 30000)
    public void flushKeywordsTask() {
        long nowDate = determineLastDate();
        if ( log.isDebugEnabled() ) {
            log.debug("Task is running: flush value set.");
        }
        if ( this.lastDate < nowDate ) {
            log.info("Reload value set.");
            String reloaded = reload();
            log.info(reloaded);
        }
    }

    private long determineLastDate() {
        return FlushDateUtils.determineLastDate(jdbcTemplate, createDateSql, updateDateSql);
    }
}
