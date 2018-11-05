package com.sinochem.crude.trade.transaction.service.impl.extend;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.crude.trade.transaction.constant.Sinochem;
import com.sinochem.crude.trade.transaction.dao.ContractSheetMapper;
import com.sinochem.crude.trade.transaction.domain.ContractSheetCombine;
import com.sinochem.crude.trade.transaction.domain.po.BiddingSheet;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.domain.po.SaleSheet;
import com.sinochem.crude.trade.transaction.model.query.ContractSheetQuery;
import com.sinochem.crude.trade.transaction.model.vo.ContractSheetQueryVO;
import com.sinochem.crude.trade.transaction.service.ContractSheetService;
import com.sinochem.crude.trade.transaction.service.impl.ContractSheetServiceImpl;
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
public class ContractSheetServiceImplExtend extends ContractSheetServiceImpl implements ContractSheetService {

    @Value("${id.singapore}")
    private Long idSingapore;

    @Value("${id.quanzhou}")
    private Long idQuanzhou;

    @Autowired
    private ContractSheetMapper contractSheetMapper;

    @Override
    public PageInfoResult getContractSheetList(CurrentUser currentUser, ContractSheetQueryVO contractSheetQueryVO, PageInfo pageInfo) throws BizException {

        ContractSheetQuery contractSheetQuery = new ContractSheetQuery();

        if (contractSheetQueryVO != null) {
            contractSheetQuery = contractSheetQueryVO.getContractSheetQuery();
        }

        if (pageInfo != null) {
            PageUtils.page(pageInfo);
        }

        if(currentUser.getEpMemberId() == null){
            return null;
        }
        System.out.print("#################################1currentUser.getEpMemberId():"+currentUser.getEpMemberId());
        if(currentUser.getEpMemberId()==100008254){
            System.out.print("#################################2currentUser.getEpMemberId():"+currentUser.getEpMemberId());
            contractSheetQuery.setSalerId(100008253L);
        }else{
            System.out.print("#################################3currentUser.getEpMemberId():"+currentUser.getEpMemberId());
            contractSheetQuery.setSalerId(currentUser.getEpMemberId());
        }
        System.out.print("#################################4currentUser.getEpMemberId():"+currentUser.getEpMemberId());


        List<ContractSheet> contractSheetList;
        if (idQuanzhou.equals(currentUser.getEpMemberId())) {
            contractSheetQuery.setEnterpriseIdSingapore(idSingapore);
            contractSheetList = contractSheetMapper.getQuanzhouContractSheetList(contractSheetQuery);
        } else {
            contractSheetList = contractSheetMapper.getContractSheetList(contractSheetQuery);
        }

        return new PageInfoResult(contractSheetList);
    }
}
