package com.sinochem.crude.trade.shiprefueling.model.query;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.shiprefueling.model.vo.HaseInfoVO;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author WGP
 * @description
 * @date 2018/6/6
 **/
public class HaseInfoQuery extends HaseInfoVO {



    private String releaseBeginDate;
    private String releaseEndDate;

    public String getReleaseEndDate() {
        return releaseEndDate;
    }

    public void setReleaseEndDate(String releaseEndDate) {
        this.releaseEndDate = releaseEndDate;
    }

    public String getReleaseBeginDate() {
        return releaseBeginDate;
    }

    public void setReleaseBeginDate(String getReleaseBeginDate) {
        this.releaseBeginDate = getReleaseBeginDate;
    }



}
