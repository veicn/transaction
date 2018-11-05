package com.sinochem.crude.trade.transaction.service.impl.extend;

import com.eyeieye.melody.util.StringUtil;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.utils.DateUtil;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Sinochem;
import com.sinochem.crude.trade.transaction.dao.BiddingSheetMapper;
import com.sinochem.crude.trade.transaction.domain.BiddingSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.model.query.BiddingSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.BiddingSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.BiddingSheetService;
import com.sinochem.crude.trade.transaction.service.impl.BiddingSheetServiceImpl;
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
public class BiddingSheetServiceImplExtend extends BiddingSheetServiceImpl implements BiddingSheetService {

    @Value("${id.singapore}")
    private Long idSingapore;

    @Value("${id.quanzhou}")
    private Long idQuanzhou;

    @Autowired
    private BiddingSheetMapper biddingSheetMapper;

    @Override
    public PageInfoResult getBiddingSheetList(CurrentUser currentUser, BiddingSheetQueryVO biddingSheetQueryVO, PageInfo pageInfo) throws BizException {

        PageUtils.page(pageInfo);
        BiddingSheetQuery biddingSheetQuery = new BiddingSheetQuery();

        //saleSheetQueryVO  转 saleSheetQuery 方法转换
        if(biddingSheetQueryVO != null){
            biddingSheetQuery = biddingSheetQueryVO.getBiddingSheetQuery();
        }

        //查询时，按照所属企业进行查询，即假如一个公司有两个业务员都发了投标单，这两个业务员是能看到互相发的单的
        biddingSheetQuery.setEnterpriseId(currentUser.getEpMemberId());

        List<BiddingSheet> biddingSheetList = biddingSheetMapper.getBiddingSheetList(biddingSheetQuery);;
       /* if (idQuanzhou.equals(currentUser.getEpMemberId())) {
            biddingSheetQuery.setEnterpriseIdSingapore(idSingapore);
            biddingSheetList = biddingSheetMapper.getQuanzhouBiddingList(biddingSheetQuery);
        } else {
            biddingSheetList = biddingSheetMapper.getBiddingSheetList(biddingSheetQuery);
        }*/

        return new PageInfoResult(biddingSheetList);
    }
}
