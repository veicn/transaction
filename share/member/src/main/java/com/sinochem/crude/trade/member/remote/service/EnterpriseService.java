package com.sinochem.crude.trade.member.remote.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.sinochem.crude.trade.member.remote.vo.EnterpriseDetailVO;
import org.springframework.ui.ModelMap;


import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;

/**
 * @Description:
 * @Author : chenyz
 * @Date: 2017/11/22
 */
public interface EnterpriseService {

    /**
     * 获取企业名称
     * @param epMemberIds 企业ID
     * @return
     */
    Map<Long,String> getEnterpriseName(Long[] epMemberIds, Locale locale);

    /**
     * 查询企业信息
     * @param memberId
     * @return
     */
    EnterpriseVo getByMemberId(Long memberId);

    /**
     * 通过资质查询所有企业信息
     *
     * @param code 资质code
    0  --  系统 1  --  炼厂 2  --  贸易商 3  --  商检
    4  --  船东 5  --  船经纪 6  --  船代 7  --  转租船东
    8  --  银行 9  --  期货经纪商 10  --  原油供应商
    11  --  平台邀请观察员 99  --  企业资质 98  --  平台OM角色
     * @return
     */
    List<EnterpriseVo> selectByCredentials(String code);


    public  EnterpriseVo selectByNameLike(String name, ModelMap modelMap);

    public  EnterpriseVo selectByPrimaryKey(Long id, ModelMap modelMap);

    List<EnterpriseDetailVO> getAllEnterprises();
}
