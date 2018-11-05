package com.sinochem.crude.trade.order.model.result;

import com.sinochem.crude.trade.order.domain.Contract;

public class LongContractQueryResult extends Contract {
    /*油种*/
    private String name;
    /*采购统计*/
    private Long sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }


    //展示数据
    public Double getSumStr(){
        if(null!=sum){
            return Double.valueOf(this.getSum()) / 10000;
        }else{
            return 0d;
        }

    }

   /* public void setNumStr(Double numStr){
        this.setNum( Double.valueOf(numStr*10000).longValue());
    }*/
}
