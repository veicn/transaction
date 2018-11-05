package com.sinochem.crude.trade.listed.model.form;

import com.sinochem.crude.trade.listed.domain.DemandShipBerth;

import java.util.List;

/**
 * Created by sijliu on 28/11/2017.
 */
public class DemandShipBerthListForm {

    List<DemandShipBerth> shipBerths;

    public List<DemandShipBerth> getShipBerths() {
        return shipBerths;
    }

    public void setShipBerths(List<DemandShipBerth> shipBerths) {
        this.shipBerths = shipBerths;
    }
}
