package com.sinochem.crude.trade.member.remote.impl;


import com.eyeieye.melody.util.ArrayUtil;
import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.dao.EnterpriseContactMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseLogMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseMapper;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.domain.MemberCredentials;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.enums.EnumEpType;
import com.sinochem.crude.trade.member.model.EnterpriseDetail;
import com.sinochem.crude.trade.member.remote.service.EnterpriseService;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseDetailVO;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;
import com.sinochem.crude.trade.member.service.SualificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


import java.util.*;

@Service("remoteEnterpriseProductServiceImpl")
public class RemoteEnterpriseServiceImpl implements EnterpriseService {
	
	@Autowired
    private EnterpriseMapper enterpriseMapper;

	@Autowired
    private EnterpriseContactMapper enterpriseContactMapper;

	@Autowired
    private SualificationService sualificationService;

    @Override
    public Map<Long, String> getEnterpriseName(Long[] epMemberIds, Locale locale) {
        Map<Long, String> enterpriseNameMap = new HashMap<>();
        if(epMemberIds != null && epMemberIds.length > 0){
            for(Long epMemberId : epMemberIds){
                if(!enterpriseNameMap.containsKey(epMemberId)){
                    EnterpriseQuery enterprise = enterpriseMapper.selectWithInfoByMemberId( epMemberId);
                    if(enterprise != null){
                        //类型,0=境内,1=境外
                        if(enterprise.getEpType() == EnumEpType.EP_TYPE_1.getCode()){
                            enterpriseNameMap.put(epMemberId, enterprise.getEnglishName());
                        }else{
                            if(locale!= null && StringUtil.isNotEmpty(locale.getLanguage())&&locale.getLanguage().equals("en")){
                                String name = enterprise.getEnglishName();
                                if(StringUtil.isEmpty(name)){
                                    name = enterprise.getFullName();
                                }
                                enterpriseNameMap.put(epMemberId, name);
                            }else{
                                String name = enterprise.getFullName();
                                if(StringUtil.isEmpty(name)){
                                    name = enterprise.getEnglishName();
                                }
                                enterpriseNameMap.put(epMemberId, name);
                            }
                        }
                    }
                }
            }
        }
        return enterpriseNameMap;
    }

    @Override
    public EnterpriseVo getByMemberId(Long memberId) {
        EnterpriseVo rtnVo = new EnterpriseVo();
        EnterpriseQuery enterprise = enterpriseMapper.selectWithInfoByMemberId( memberId);
        if(enterprise != null){
            if(enterprise.getEpType() != null && enterprise.getEpType() == 1){//类型,0=境内,1=境外
                enterprise.setFullName(enterprise.getEnglishName());
            }else{
                if(StringUtil.isEmpty(enterprise.getFullName())){
                    enterprise.setFullName(enterprise.getEnglishName());
                }
                if(StringUtil.isEmpty(enterprise.getEnglishName())){
                    enterprise.setEnglishName(enterprise.getFullName());
                }
            }
            EnterpriseContact contact = new EnterpriseContact();
            contact.setEnterpriseId(enterprise.getId());
            contact.setMemberId(enterprise.getMemberId());
            EnterpriseContact enterpriseContact = enterpriseContactMapper.findContactWithMbIdAndEpId(contact);
            BeanUtils.copyProperties(enterprise,rtnVo);
            if(enterpriseContact != null){
                rtnVo.setContactName(enterpriseContact.getName());
                rtnVo.setContactMobile(enterpriseContact.getMobile());
                rtnVo.setContactAddress(enterpriseContact.getAddress());
                rtnVo.setContactMail(enterpriseContact.getMail());
                rtnVo.setContactPhone(enterpriseContact.getPhone());
                //后期维护member 追加
                rtnVo.setScope(enterprise.getScope());
                rtnVo.setEptype(enterprise.getEpType());
            }
            //获取企业资质
            List<String> credentialsCode = new ArrayList<String>();
            List<MemberCredentials> credentials = sualificationService.getRedentials(enterprise.getMemberId());
            if(credentials != null && credentials.size() > 0){
                for(MemberCredentials creden : credentials){
                    if(creden.getAudit()==1){
                        credentialsCode.add(creden.getCredentialsCode());
                    }
                }
            }
            if(credentialsCode != null){
                String[] credentialsCodes = new String[credentials.size()];
                credentialsCode.toArray(credentialsCodes);
                rtnVo.setCredentialsCode(credentialsCodes);
            }
        }
        return rtnVo;
    }

    @Override
    public List<EnterpriseVo> selectByCredentials(String code) {
        List<EnterpriseVo> enterpriseVoList  = new ArrayList<>();
        List<EnterpriseDetail> detailList =  enterpriseMapper.selectByCredentials(code);
        for(EnterpriseDetail detail : detailList){
            EnterpriseVo enterpriseVo = new EnterpriseVo();
            enterpriseVo.setId(detail.getId());
            enterpriseVo.setName(detail.getName());
            enterpriseVo.setCode(detail.getCode());
            enterpriseVo.setDescription(detail.getDescription());
            enterpriseVo.setFullName(detail.getFullName());
            enterpriseVo.setLicenseNo(detail.getLicenseNo());
            enterpriseVo.setOrganizationCode(detail.getOrganizationCode());
            enterpriseVo.setRegisterTime(detail.getRegisterTime());
            enterpriseVo.setMemberId(detail.getMemberId());
            enterpriseVo.setTaxNo(detail.getTaxNo());
            enterpriseVo.setTaxCode(detail.getTaxCode());
            enterpriseVo.setEnglishName(detail.getEnglishName());
            enterpriseVo.setAbbEnglishName(detail.getAbbEnglishName());
            enterpriseVo.setCreateTime(detail.getCreateTime());
            enterpriseVo.setCreateUser(detail.getCreateUser());
            enterpriseVo.setUpdateTime(detail.getUpdateTime());
            enterpriseVo.setUpdateUser(detail.getUpdateUser());
            enterpriseVoList.add(enterpriseVo);
        }
        return enterpriseVoList;
    }



    //前台企业名称模糊查询
    
    public  EnterpriseVo selectByNameLike(String name, ModelMap modelMap){
    	 Enterprise enterprise = new Enterprise();
         List<Enterprise> enterpriseList  = enterpriseMapper.selectByNameLike(name);
         EnterpriseVo rtnVo = new EnterpriseVo();
        if(!ArrayUtil.isEmpty(enterpriseList)){
            enterprise = enterpriseList.get(0);
        }else{
            return null;
        }
         BeanUtils.copyProperties(enterprise,rtnVo);
        return rtnVo;
        
    }
    
    //前台通过id回显企业信息
    
    public  EnterpriseVo selectByPrimaryKey(Long id, ModelMap modelMap){
    	System.out.println(id);
    	Enterprise enterprise  =  enterpriseMapper.selectByPrimaryKey(id);
        EnterpriseVo rtnVo = new EnterpriseVo();
    	if(enterprise != null){
            BeanUtils.copyProperties(enterprise,rtnVo);
        }
    	return rtnVo;
    }

    @Override
    public List<EnterpriseDetailVO> getAllEnterprises() {

        List<EnterpriseDetail> enterpriseDetailList = enterpriseMapper.selectAllDetail();

        List<EnterpriseDetailVO> enterpriseDetailVOList = new ArrayList<>();
        for (EnterpriseDetail enterpriseDetail : enterpriseDetailList) {
            EnterpriseDetailVO enterpriseDetailVO = new EnterpriseDetailVO();

            enterpriseDetailVO.setId(enterpriseDetail.getId());
            enterpriseDetailVO.setName(enterpriseDetail.getFullName());
            enterpriseDetailVO.setCres(enterpriseDetail.getCredentialsStr());
            enterpriseDetailVO.setMemberId(enterpriseDetail.getMemberId());

            enterpriseDetailVOList.add(enterpriseDetailVO);
        }

        return enterpriseDetailVOList;
    }

    /**
     * 根据公司名字集合查询公司列表
     * @param list
     * @return
     */
    @Override
    public List<EnterpriseVo> queryEnterpriseVoList(List<String> list) {
         List<Enterprise> enList =  enterpriseMapper.queryEnterpriseVoList(list);
         List<EnterpriseVo> voList = null;
         if(enList != null && !enList.isEmpty()){
             voList = new ArrayList<>(enList.size());
             for (Enterprise domain:enList) {
                 EnterpriseVo vo = new EnterpriseVo();
                 vo.setId(domain.getId());
                 vo.setMemberId(domain.getMemberId());
                 vo.setName(domain.getName());
                 vo.setAbbEnglishName(domain.getAbbEnglishName());
                 vo.setCreateUser(domain.getCreateUser());
                 voList.add(vo);
             }
         }
         return voList;
    }

}
