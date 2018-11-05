package com.sinochem.crude.trade.listed.service;

import com.sinochem.crude.trade.listed.domain.DemandImages;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
public interface DemandImageService {

    /**
     * 保存更新
     * @param record
     * @return
     */
    public Long SaveOrUpdateDemandImage(DemandImages record);

    /**
     * 按需求id查询
     * @param demandId
     * @return
     */
    public List<DemandImages> getImagesByDemandId(Long demandId);

    /**
     * 按主键查询
     * @param id
     * @return
     */
    public DemandImages getImageByKey(Long id);
}
