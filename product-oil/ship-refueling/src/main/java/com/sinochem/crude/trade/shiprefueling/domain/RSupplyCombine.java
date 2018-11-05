package com.sinochem.crude.trade.shiprefueling.domain;

import com.sinochem.crude.trade.shiprefueling.domain.po.Gory;
import com.sinochem.crude.trade.shiprefueling.domain.po.RIgnition;
import com.sinochem.crude.trade.shiprefueling.domain.po.RSupply;

import java.util.List;

public class RSupplyCombine {

    private RSupply rSupply;

    private List<Gory> list;

    public RSupplyCombine() { }

    public RSupplyCombine(RSupply rSupply, List<Gory> list) {
        this.rSupply = rSupply;
        this.list = list;
    }

    public RSupply getrSupply() {
        return rSupply;
    }

    public void setrSupply(RSupply rSupply) {
        this.rSupply = rSupply;
    }

    public List<Gory> getList() {
        return list;
    }

    public void setList(List<Gory> list) {
        this.list = list;
    }
}
