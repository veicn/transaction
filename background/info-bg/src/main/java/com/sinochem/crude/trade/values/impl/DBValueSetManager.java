package com.sinochem.crude.trade.values.impl;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.HashBiMap;
import com.sinochem.crude.trade.common.exception.BizException;
import com.sinochem.crude.trade.common.utils.BeanConvertUtils;
import com.sinochem.crude.trade.common.utils.JedisUtils;
import com.sinochem.crude.trade.common.values.CodeValue;
import com.sinochem.crude.trade.common.values.FlushDateUtils;
import com.sinochem.crude.trade.common.values.impl.CommonValueSetManager;
import com.sinochem.crude.trade.values.DBValueSetProperties;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by GHuang on 2016/11/25.
 * Implements of valueSetManager using BD storage.
 */
public class DBValueSetManager extends CommonValueSetManager {

    private static final Log log = LogFactory.getLog(DBValueSetManager.class);

    private JdbcTemplate jdbcTemplate;

    private DBValueSetProperties properties;

    private String createDateSql;

    private String updateDateSql;

    private RAtomicLong lastDate = JedisUtils.getRedisson().getAtomicLong("_value_code_map_lastDate");

    private RLock lock = JedisUtils.getRedisson().getFairLock("_value_code_map_lock");

    public DBValueSetManager(JdbcTemplate jdbcTemplate, DBValueSetProperties properties) {
        this.jdbcTemplate = jdbcTemplate;
        this.properties = properties;
        this.createDateSql = "select max(`" + properties.getCreateDateField() + "`) from " +
                properties.getTable();
        this.updateDateSql = "select max(`" + properties.getUpdateDateField() + "`) from " +
                properties.getTable();
    }

    @Override
    public void load() {
        try {
            log.info("Loading value set form DB...");
            HashBiMap<String, String> resultMap = HashBiMap.create(properties.getResultMap());
            String sort = resultMap.get("sort");
            String sql = Joiner.on("`, `")
                    .withKeyValueSeparator("` as `")
                    .appendTo(new StringBuilder("select `"), resultMap.inverse())
                    .append("` from ")
                    .append(properties.getTable())
                    .append(" where `ALIVE_FLAG` = '1'")
                    .append(" order by `")
                    .append(Strings.isNullOrEmpty(sort) ? resultMap.get("id") : sort)
                    .append("`")
                    .toString();
            if (log.isDebugEnabled()) {
                log.debug("Load value set. SQL: " + sql);
            }
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

            lock.lock(30, TimeUnit.SECONDS);
            map.clear();
            for (Map<String, Object> result : list) {
                CodeValue value = BeanConvertUtils.mapToBean(result, CodeValue.class);
                map.fastPut(value.getGroup()+"." +value.getLangVer() + "." + value.getCode(), value);
            }
            lastDate.getAndSet(determineLastDate());
            log.info("Loaded " + map.size() + " values form DB, last timestamp: " + lastDate);
        } catch (DataAccessException e) {
            throw new BizException("Cannot load code-value set.", e);
        } finally {
            lock.unlock();
        }
    }

    public Collection<CodeValue> getAllList() {
        return map.values();
    }

    public String commandLoad() {
        map.clear();
        load();
        return "Loaded " + map.size() + "values to memory";
    }

    @Scheduled(fixedRate = 10000)
    public void flushValuesTask() {
        long nowDate = determineLastDate();
        if ( log.isDebugEnabled() ) {
            log.debug("Task is running: flush value set.");
        }
        if ( this.lastDate.get() < nowDate ) {
            log.info("Reload value set.");
            load();
        }
    }

    private long determineLastDate() {
        return FlushDateUtils.determineLastDate(jdbcTemplate, createDateSql, updateDateSql);
    }
}
