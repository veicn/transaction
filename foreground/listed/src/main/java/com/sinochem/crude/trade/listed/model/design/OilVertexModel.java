package com.sinochem.crude.trade.listed.model.design;

import com.sinochem.crude.trade.listed.model.design.base.OilModel;
import com.sinochem.crude.trade.listed.model.design.base.VertexModel;

/**
 * 使用油种作为图中的点
 * @author Yichen Zhao
 * date: 20180202
 */
public class OilVertexModel extends VertexModel {

    private OilModel oilModel;

    public OilVertexModel(OilModel oilModel) {
        this.oilModel = oilModel;
    }

    @Override
    public int hashCode() {
        return oilModel.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (!(other instanceof OilVertexModel)) {
            return false;
        }

        return oilModel.equals(((OilVertexModel) other).getOilModel());
    }

    /** getters and setters */
    public OilModel getOilModel() {
        return oilModel;
    }

    public void setOilModel(OilModel oilModel) {
        this.oilModel = oilModel;
    }
}
