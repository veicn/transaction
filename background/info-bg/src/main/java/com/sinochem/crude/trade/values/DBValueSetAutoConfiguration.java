package com.sinochem.crude.trade.values;

import com.sinochem.crude.trade.common.utils.ValueSetUtils;
import com.sinochem.crude.trade.common.values.ValueSetManager;
import com.sinochem.crude.trade.values.impl.DBValueSetManager;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by GHuang on 2016/11/25.
 * 从数据库中载入值集的方法
 */
@Configuration
public class DBValueSetAutoConfiguration {

    @Bean(initMethod = "load", destroyMethod = "clean")
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ValueSetManager valueSetManager(JdbcTemplate jdbcTemplate, DBValueSetProperties properties) {
        DBValueSetManager dbValueSetManager = new DBValueSetManager(jdbcTemplate, properties);
        ValueSetUtils.init(dbValueSetManager);
        return dbValueSetManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
        return template;
    }
}
