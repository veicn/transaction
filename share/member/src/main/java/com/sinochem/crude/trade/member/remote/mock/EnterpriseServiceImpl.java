//package com.sinochem.crude.trade.member.remote.mock;
//
//import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
//import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.ModelMap;
//
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
///**
// * @Description:
// * @Author : chenyz
// * @Date: 2017/11/22
// */
//@Service("mockEnterpriseServiceImpl")
//public class EnterpriseServiceImpl implements EnterpriseService {
//
//
//    @Override
//    public Map<Long, String> getEnterpriseName(Long[] epMemberIds, Locale locale) {
//        return null;
//    }
//
//    public EnterpriseVo getByMemberId(Long memberId) {
//        EnterpriseVo enterpriseVo = new EnterpriseVo();
//        enterpriseVo.setId(10000L);
//        enterpriseVo.setName("天津友发石油集团销售有限公司");
//        return enterpriseVo;
//    }
//
//    @Override
//    public List<EnterpriseVo> selectByCredentials(String code) {
//        return null;
//    }
//
//
//    @Override
//    public EnterpriseVo selectByNameLike(String name, ModelMap modelMap) {
//        return null;
//    }
//
//    @Override
//    public EnterpriseVo selectByPrimaryKey(Long id, ModelMap modelMap) {
//        return null;
//    }
//
//
//}
