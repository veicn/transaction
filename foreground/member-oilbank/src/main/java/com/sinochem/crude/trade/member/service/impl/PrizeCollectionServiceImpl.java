package com.sinochem.crude.trade.member.service.impl;

import com.sinochem.crude.trade.member.dao.PrizeCollectionMapper;
import com.sinochem.crude.trade.member.domain.PrizeCollection;
import com.sinochem.crude.trade.member.model.PrizeCollectionVO;
import com.sinochem.crude.trade.member.service.PrizeCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrizeCollectionServiceImpl implements PrizeCollectionService {

    @Autowired
    private PrizeCollectionMapper prizeCollectionMapper;

    @Override
    public void add(PrizeCollection prizeCollection) {

        prizeCollectionMapper.insertSelective(prizeCollection);

    }

    @Override
    public List<PrizeCollectionVO> selectAll() {
        List<PrizeCollection> list = prizeCollectionMapper.selectAll();
        List<PrizeCollectionVO> list2 = new ArrayList<PrizeCollectionVO>();
        if(!CollectionUtils.isEmpty(list)){
            for (PrizeCollection PrizeCollection : list ) {
                PrizeCollectionVO PrizeCollectionVO = new PrizeCollectionVO(PrizeCollection);
                list2.add(PrizeCollectionVO);
            }
        }
        return list2;
    }

    @Override
    public List<PrizeCollectionVO> selectByContactUser(String contactUser) {
        List<PrizeCollection> list = prizeCollectionMapper.selectByContactUser(contactUser);
        List<PrizeCollectionVO> list2 = new ArrayList<PrizeCollectionVO>();
        if(!CollectionUtils.isEmpty(list)){
            for (PrizeCollection PrizeCollection : list ) {
                PrizeCollectionVO PrizeCollectionVO = new PrizeCollectionVO(PrizeCollection);
                list2.add(PrizeCollectionVO);
            }
        }
        return list2;
    }

    @Override
    public void deleteByPrimaryKey(Long id){
        prizeCollectionMapper.deleteByPrimaryKey(id);
    }

}
