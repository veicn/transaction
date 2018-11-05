package com.sinochem.crude.trade.listed.service.impl;

import com.sinochem.crude.trade.listed.dao.DemandImagesMapper;
import com.sinochem.crude.trade.listed.domain.DemandImages;
import com.sinochem.crude.trade.listed.service.DemandImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sijliu on 15/11/2017.
 */
@Service
public class DemandImageServiceImpl implements DemandImageService {

    @Autowired
    DemandImagesMapper demandImagesMapper;

    @Override
    public Long SaveOrUpdateDemandImage(DemandImages record) {
        if (record.getId() == null) {
            demandImagesMapper.insert(record);
        } else {
            demandImagesMapper.updateByPrimaryKeySelective(record);
        }
        return record.getId();
    }

    @Override
    public List<DemandImages> getImagesByDemandId(Long demandId) {
        return demandImagesMapper.selectByDemandId(demandId);
    }

    @Override
    public DemandImages getImageByKey(Long id) {
        return demandImagesMapper.selectByPrimaryKey(id);
    }
}
