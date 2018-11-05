package com.sinochem.crude.trade.shiprefueling.domain;

import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;

import java.util.List;

public class RIgnitionCombine {

    private RIgnition rIgnition;

    private List<Gory> list;

    public RIgnitionCombine() {
    }

    public RIgnitionCombine(RIgnition rIgnition, List<Gory> list) {
        this.rIgnition = rIgnition;
        this.list = list;
    }

    public RIgnition getrIgnition() {
        return rIgnition;
    }

    public void setrIgnition(RIgnition rIgnition) {
        this.rIgnition = rIgnition;
    }

    public List<Gory> getList() {
        return list;
    }

    public void setList(List<Gory> list) {
        this.list = list;
    }
}
