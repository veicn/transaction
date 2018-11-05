package com.sinochem.crude.trade.common.values;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by GHuang on 2016/12/27.
 * Utils to handle flush date.
 */
public class FlushDateUtils {

    private static final Log log = LogFactory.getLog(FlushDateUtils.class);

    public static long determineLastDate(JdbcTemplate jdbcTemplate, String createDateSql, String updateDateSql) {
        Date maxCreateDate;
        Date maxUpdateDate;
        try {
            maxCreateDate = jdbcTemplate.queryForObject(createDateSql, Date.class);
            maxUpdateDate = jdbcTemplate.queryForObject(updateDateSql, Date.class);
        } catch (DataAccessException e) {
            if ( log.isErrorEnabled() ) {
                log.error("Load last date error, returned 0.");
            }
            return 0;
        }
        if ( null != maxCreateDate && null != maxUpdateDate ) {
            long maxCreateDateLong = maxCreateDate.getTime();
            long maxUpdateDateLong = maxUpdateDate.getTime();
            return maxCreateDateLong < maxUpdateDateLong ? maxUpdateDateLong : maxCreateDateLong;
        } else if ( null != maxCreateDate ) {
            return maxCreateDate.getTime();
        }
        return 0;
    }
}
