package com.sinochem.crude.trade.transaction.service.impl.extend;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Sinochem;
import com.sinochem.crude.trade.transaction.dao.SaleSheetMapper;
import com.sinochem.crude.trade.transaction.domain.SaleSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.enums.ExceptionEnum;
import com.sinochem.crude.trade.transaction.model.query.SaleSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.SaleSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.SaleSheetService;
import com.sinochem.crude.trade.transaction.service.impl.SaleSheetServiceImpl;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 实现中化新和泉化的权限需求
 * @author Yichen Zhao
 * date: 20180318
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SaleSheetServiceiImplExtend extends SaleSheetServiceImpl implements SaleSheetService {

    @Value("${id.singapore}")
    private Long idSingapore;

    @Value("${id.quanzhou}")
    private Long idQuanzhou;

    @Autowired
    private SaleSheetMapper saleSheetMapper;

    @Override
    public PageInfoResult getSaleSheetList(CurrentUser currentUser, SaleSheetQueryVO saleSheetQueryVO, PageInfo pageInfo) throws BizException {
        BizException bizException = new BizException();

        if (currentUser == null || saleSheetQueryVO == null ) {
            bizException.setCode(ExceptionEnum.NULL_PARAMETER.getCode());
            throw bizException;
        }

        SaleSheetQuery saleSheetQuery = new SaleSheetQuery();
        if(saleSheetQueryVO != null){
            saleSheetQuery = saleSheetQueryVO.getSaleSheetQuery();
        }

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }

        List<SaleSheet> saleSheetList;

        /*String serialNumber = saleSheetQuery.getSerialNumber();
        if(serialNumber != null && !StringUtil.isEmpty(serialNumber)){
            saleSheetQuery.setSerialNumber("%"+serialNumber+"%");
        }*/

        if (idQuanzhou.equals(currentUser.getEpMemberId())) {
            saleSheetQuery.setQueryEnterpriseId(idSingapore);
            saleSheetList = saleSheetMapper.getQuanzhouVisibleSaleSheet(saleSheetQuery);
        } else {
            saleSheetList = saleSheetMapper.getSaleSheetList(saleSheetQuery);
        }

        return new PageInfoResult<SaleSheet>(saleSheetList);
    }
}
