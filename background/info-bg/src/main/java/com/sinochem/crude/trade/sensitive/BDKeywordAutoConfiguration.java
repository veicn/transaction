package com.sinochem.crude.trade.sensitive;

import com.sinochem.crude.trade.common.sensitive.AbstractKeywordManager;
import com.sinochem.crude.trade.sensitive.impl.DBKeywordManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by GHuang on 2016/12/19.
 */
@Configuration
public class BDKeywordAutoConfiguration {

    @Bean
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public AbstractKeywordManager keywordManager(JdbcTemplate jdbcTemplate, DBKeywordProperties properties) {
        return new DBKeywordManager(jdbcTemplate, properties);
    }
}
