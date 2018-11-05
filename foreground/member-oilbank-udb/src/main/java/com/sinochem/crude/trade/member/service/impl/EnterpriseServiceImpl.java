package com.sinochem.crude.trade.member.service.impl;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.util.uuid.UUIDGenerator;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UdbCodeConstant;
import com.sinochem.crude.trade.member.dao.EnterpriseContactMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseLogMapper;
import com.sinochem.crude.trade.member.dao.EnterpriseMapper;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.domain.EnterpriseLog;
import com.sinochem.crude.trade.member.domain.MemberCredentials;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.helper.HttpClientHelper;
import com.sinochem.crude.trade.member.model.EnterpriseCredentialsEnum;
import com.sinochem.crude.trade.member.model.EnterpriseDetail;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseContactVo;
import com.sinochem.crude.trade.member.model.udbvo.UdbEnterpriseVO;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.udbservice.EnterpriseContactUdbService;
import com.sinochem.crude.trade.member.service.udbservice.EnterpriseUdbServiceImpl;
import com.sinochem.crude.trade.member.service.udbservice.MemberCredentialsUdbService;
import com.sinochem.crude.trade.member.util.UdbResult;
import com.sinochem.it.b2b.common.exception.BizException;

import com.sinochem.it.b2b.common.page.PageUtils;
import com.sun.tools.javac.comp.Enter;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;


@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	private final Integer UNAUDITED = 0;
	private final Integer AUDITED = 1;


	@Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    EnterpriseLogMapper enterpriseLogMapper;
	@Autowired
	private UUIDGenerator uuidGenerator;
	@Autowired
	MemberCredentialsService memberCredentialsService;
	@Autowired
	EnterpriseContactMapper enterpriseContactMapper;

	@Autowired
	EnterpriseUdbServiceImpl enterpriseUdbService;



	@Autowired
	EnterpriseContactUdbService enterpriseContactUdbService;

    @Override
    public void add(Enterprise enterprise) throws BizException {
//		UdbResult<UdbEnterpriseVO> udbResult =  enterpriseUdbService.udbInsert(enterprise);
//		if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
//			enterprise.setUdbUuid(udbResult.getResponse().getEnterpriseUid());
//		}else{
//			throw new BizException(udbResult.getMessage());
//		}
    	enterpriseMapper.insert(enterprise);
    	EnterpriseLog enterpriseLog = new EnterpriseLog();
        BeanUtils.copyProperties(enterprise,enterpriseLog);
        enterpriseLog.setId(null);
        enterpriseLog.setEnterpriseId(enterpriseLog.getId());
        enterpriseLogMapper.insert(enterpriseLog);
    }

    @Override
    public void update(Enterprise enterprise) throws BizException {
		UdbResult udbResult =  enterpriseUdbService.udbUpdate(enterprise);
		if(!UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
			throw new BizException(udbResult.getMessage());
		}
    	enterpriseMapper.updateByPrimaryKey(enterprise);

    	EnterpriseLog enterpriseLog = new EnterpriseLog();
        BeanUtils.copyProperties(enterprise,enterpriseLog);
        enterpriseLog.setId(null);
        enterpriseLog.setEnterpriseId(enterpriseLog.getId());
        enterpriseLogMapper.insert(enterpriseLog);
    }
    
    @Override
    public void updateByPrimaryKeySelective(Enterprise enterprise,BindingResult bindingResult) throws BizException {
		validEnterprise(enterprise, bindingResult);
		if(bindingResult.hasErrors()){
			return;
		}
		UdbResult<UdbEnterpriseVO> udbResult =  enterpriseUdbService.udbUpdate(enterprise);
		if(!UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
			throw new BizException(udbResult.getMessage());
		}
    	enterpriseMapper.updateByPrimaryKeySelective(enterprise);

    	EnterpriseLog enterpriseLog = new EnterpriseLog();
        BeanUtils.copyProperties(enterprise,enterpriseLog);
        enterpriseLog.setId(null);
        enterpriseLog.setEnterpriseId(enterpriseLog.getId());
        enterpriseLogMapper.insert(enterpriseLog);
    }

//    @Override
//    public List<Enterprise> selectByPrimary(Enterprise enterprise) {
//    	//todo
////        return this.enterpriseMapper.selectByPrimary(enterprise);
//        return null;
//    }

	//TODO:
	@Override
	public List<Enterprise> selectEnter(String name) {
		List<Enterprise> list = enterpriseMapper.selectEnter(name);
		return list;
	}

	@Override
	public String insertEnterprise(Enterprise enterprise)throws BizException {

//		UdbResult<UdbEnterpriseVO> udbResult =  enterpriseUdbService.udbInsert(enterprise);
//		if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
//			enterprise.setUdbUuid(udbResult.getResponse().getEnterpriseUid());
//		}else{
//			throw new BizException(udbResult.getMessage());
//		}
		enterpriseMapper.insert(enterprise);
		return null;
	}


	//TODO:UDB没有企业的没有删除接口
	@Override
	public void deleteEnterprise(Long id) {
		enterpriseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Enterprise queryById(Long id) {
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(id);
		return enterprise;
	}

	@Override
	public List<Enterprise> selectByNameLike(@Param("name") String name) {
		List<Enterprise> list = enterpriseMapper.selectByNameLike(name+"%");
		return list;
	}
	//根据前台id回显所需数据
	@Override
	public Enterprise enterpriseById( Long id) {
		Enterprise enterprise = enterpriseMapper.enterpriseById(id);
		return enterprise;
	}

	@Override
	public Enterprise enterpriseByMemberId(Long memberId) {
    	
    	Enterprise enterprise = enterpriseMapper.enterpersiceDetail(memberId);
		return enterprise;
	}

	//企业详细信息查询
	@Override
	public Enterprise enterpersiceDetail(Long memberId) {
		Enterprise enterprise = enterpriseMapper.enterpersiceDetail(memberId);
		return enterprise;
	}

	@Override
	public List<EnterpriseDetail> selectAll() {
		return enterpriseMapper.selectAllDetail();
	}

	@Override
	public Enterprise selectByPrimaryKey(Long epMemberId) {
		return enterpriseMapper.selectByPrimaryKey(epMemberId);
	}

	@Override
	public List<Enterprise> selectWithCrude(EnterpriseQuery query) {
		return enterpriseMapper.selectWithCrude(query);
	}

	@Override
	public List<Enterprise> selectWithCrude(EnterpriseQuery query, PageInfo pageInfo) {
		PageUtils.page(pageInfo);
		return enterpriseMapper.selectWithCrude(query);
	}

	@Override
	public List<EnterpriseDetail> selectAllByName(String name) {
		return enterpriseMapper.selectAllDetailByName(name);
	}

	@Override
	public Enterprise getByMemberId(Long epMemberId) {
		return enterpriseMapper.getByMemberId(epMemberId);
	}

	@Override
	public String fill(Enterprise enterprise, Long memberId, BindingResult bindingResult) throws BizException{
		if(enterprise.getId() == null) {
			enterprise.setMemberId(memberId);
		}
		//验证信息
		validEnterprise(enterprise, bindingResult);
		if(bindingResult.hasErrors()) {
			return null;
		}
		//企业名称判断 这里要求保证境外注册fullName不能为空
		Integer epType = Integer.valueOf(1);
		if(enterprise.getEpType()== epType){//境外
			if(StringUtil.isEmpty(enterprise.getName())){
				enterprise.setName(enterprise.getAbbEnglishName());
			}else if(StringUtil.isEmpty(enterprise.getFullName())){
				enterprise.setFullName(enterprise.getEnglishName());
			}
		}
		//需要判断当前会员是否已有企业信息
		Enterprise ep = enterpriseMapper.getByMemberId(memberId);
		if(null == ep) {
			enterprise.setCode(uuidGenerator.gain());
//			需要审核所以不能插入企业信息
//			UdbResult<UdbEnterpriseVO> udbResult =  enterpriseUdbService.udbInsert(enterprise);
//			if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
//				enterprise.setUdbUuid(udbResult.getResponse().getEnterpriseUid());
//			}else{
//				throw new BizException(udbResult.getMessage());
//			}
			enterpriseMapper.insert(enterprise);

			MemberCredentials memberCredentials = new MemberCredentials();
			memberCredentials.setAudit(UNAUDITED);
			memberCredentials.setMemberId(memberId);
			memberCredentials.setCredentialsCode(String.valueOf(EnterpriseCredentialsEnum.ENTERPRISES.getCode()));
			memberCredentialsService.save(memberCredentials);

		}else{//已有企业，修改现有企业信息
			//修改企业信息，需要在udb里更新
			enterprise.setId(ep.getId());
			enterprise.setUdbUuid(ep.getUdbUuid());
			enterprise.setMemberId(memberId);
			UdbResult<UdbEnterpriseVO> result = enterpriseUdbService.udbUpdate(enterprise);
			if(!UdbCodeConstant.SUCCESS.equals(result.getCode())) {
				throw new BizException(result.getMessage());
			}
			enterpriseMapper.updateByPrimaryKey(enterprise);

			reApplyCredentials(memberId); //重新申请资质
		}
		EnterpriseLog enterpriseLog = new EnterpriseLog();
		enterpriseLog.setId(null);
		enterpriseLog.setEnterpriseId(enterprise.getId());
		BeanUtils.copyProperties(enterprise, enterpriseLog);
		enterpriseLogMapper.insert(enterpriseLog);
		return enterprise.getUdbUuid();
		/*Member member =  memberMapper.selectByPrimaryKey(memberId);
		member.setType("1");
		memberMapper.updateByPrimaryKey(member);*/
	}


	/**
	 * 后台om添加企业补充信息
	 * @param enterprise
	 * @param memberId
	 * @param bindingResult
	 * @return
	 * @throws BizException
	 */
	@Override
	public String omFill(Enterprise enterprise, Long memberId, BindingResult bindingResult) throws BizException{
		if(enterprise.getId() == null) {
			enterprise.setMemberId(memberId);
		}
		//验证信息
		validEnterprise(enterprise, bindingResult);
		if(bindingResult.hasErrors()) {
			return null;
		}
		//企业名称判断 这里要求保证境外注册fullName不能为空
		Integer epType = Integer.valueOf(1);
		if(enterprise.getEpType()== epType){//境外
			if(StringUtil.isEmpty(enterprise.getName())){
				enterprise.setName(enterprise.getAbbEnglishName());
			}else if(StringUtil.isEmpty(enterprise.getFullName())){
				enterprise.setFullName(enterprise.getEnglishName());
			}
		}
		//需要判断当前会员是否已有企业信息
		Enterprise ep = enterpriseMapper.getByMemberId(memberId);
		if(null == ep) {
			enterprise.setCode(uuidGenerator.gain());
//			需要审核所以不能插入企业信息
			UdbResult<UdbEnterpriseVO> udbResult =  enterpriseUdbService.udbInsert(enterprise);
			if(UdbCodeConstant.SUCCESS.equals(udbResult.getCode())){
				enterprise.setUdbUuid(udbResult.getResponse().getEnterpriseUid());
			}else{
				throw new BizException(udbResult.getMessage());
			}
			enterpriseMapper.insert(enterprise);

			MemberCredentials memberCredentials = new MemberCredentials();
			memberCredentials.setAudit(AUDITED);
			memberCredentials.setMemberId(memberId);
			memberCredentials.setCredentialsCode(String.valueOf(EnterpriseCredentialsEnum.ENTERPRISES.getCode()));
			memberCredentialsService.save(memberCredentials);

		}else{//已有企业，修改现有企业信息
			//修改企业信息，需要在udb里更新
			enterprise.setId(ep.getId());
			enterprise.setUdbUuid(ep.getUdbUuid());
			enterprise.setMemberId(memberId);
			UdbResult<UdbEnterpriseVO> result = enterpriseUdbService.udbUpdate(enterprise);
			if(!UdbCodeConstant.SUCCESS.equals(result.getCode())) {
				throw new BizException(result.getMessage());
			}
			enterpriseMapper.updateByPrimaryKey(enterprise);

			reApplyCredentials(memberId); //重新申请资质
		}
		EnterpriseLog enterpriseLog = new EnterpriseLog();
		enterpriseLog.setId(null);
		enterpriseLog.setEnterpriseId(enterprise.getId());
		BeanUtils.copyProperties(enterprise, enterpriseLog);
		enterpriseLogMapper.insert(enterpriseLog);
		return enterprise.getUdbUuid();
		/*Member member =  memberMapper.selectByPrimaryKey(memberId);
		member.setType("1");
		memberMapper.updateByPrimaryKey(member);*/
	}
	@Override
	public void register(Enterprise enterprise, EnterpriseContact contact, Long memberId, String[] types, BindingResult bindingResult) throws BizException{
		 String uuid = fill(enterprise,memberId,bindingResult);
		if (bindingResult.hasErrors()) {
			return;
		}
		if(types != null && types.length > 0){
			for(String type :types){
				insertCredentials(memberId, type);
			}
		}
		contact.setMemberId(memberId);
		contact.setEnterpriseId(enterprise.getId());

		EnterpriseContact enterpriseContact = enterpriseContactMapper.findContactWithMbIdAndEpId(contact);

		if(enterpriseContact == null || enterpriseContact.getId() == null) {
			Enterprise en = enterpriseMapper.selectByPrimaryKey(contact.getEnterpriseId());
//			UdbResult<UdbEnterpriseContactVo> result = enterpriseContactUdbService.udbInsert(contact);
//			if(UdbCodeConstant.SUCCESS.equals(result.getCode())){
//				contact.setUdbUuid(result.getResponse().getEntpContsUid());
				enterpriseContactMapper.insert(contact);
//			}else{
//				throw new BizException(result.getMessage());
//			}
		}else {
			UdbResult result = enterpriseContactUdbService.udbUpdate(contact);
			if(!UdbCodeConstant.SUCCESS.equals(result.getCode())){
				throw new BizException(result.getMessage());
			}
			enterpriseContactMapper.updateByPrimaryKey(contact);
		}
	}


	/**
	 * 后台om添加企业，无需审核，所以得走udb接口
	 * @param enterprise
	 * @param contact
	 * @param memberId
	 * @param types
	 * @param bindingResult
	 * @throws BizException
	 */
	@Override
	public void omRegister(Enterprise enterprise, EnterpriseContact contact, Long memberId, String[] types, BindingResult bindingResult) throws BizException{
		String uuid = omFill(enterprise,memberId,bindingResult);
		if (bindingResult.hasErrors()) {
			return;
		}
		if(types != null && types.length > 0){
			for(String type :types){
				insertCredentials(memberId, type);
			}
		}
		contact.setMemberId(memberId);
		contact.setEnterpriseId(enterprise.getId());

		EnterpriseContact enterpriseContact = enterpriseContactMapper.findContactWithMbIdAndEpId(contact);

		if(enterpriseContact == null || enterpriseContact.getId() == null) {
			Enterprise en = enterpriseMapper.selectByPrimaryKey(contact.getEnterpriseId());
			UdbResult<UdbEnterpriseContactVo> result = enterpriseContactUdbService.udbInsert(contact);
			if(UdbCodeConstant.SUCCESS.equals(result.getCode())){
				contact.setUdbUuid(result.getResponse().getEntpContsUid());
			enterpriseContactMapper.insert(contact);
			}else{
				throw new BizException(result.getMessage());
			}
		}else {
			UdbResult result = enterpriseContactUdbService.udbUpdate(contact);
			if(!UdbCodeConstant.SUCCESS.equals(result.getCode())){
				throw new BizException(result.getMessage());
			}
			enterpriseContactMapper.updateByPrimaryKey(contact);
		}
	}
	//申请企业资质
	private void insertCredentials(Long memberId, String type) {
		MemberCredentials memberCredentia = memberCredentialsService.getCredentials(memberId, Long.valueOf(type));
		if(memberCredentia == null || memberCredentia.getMemberId() == null){
			MemberCredentials memberCredentials = new MemberCredentials();
			memberCredentials.setAudit(0);
			memberCredentials.setMemberId(memberId);
			memberCredentials.setCredentialsCode(type);
			memberCredentialsService.save(memberCredentials);
		}
	}

    /**
     * 重新申请资质
     * @param memberId
     * @throws BizException
     */
	private void reApplyCredentials(Long memberId) throws BizException{
	    List<MemberCredentials> rejectList = this.memberCredentialsService.getRejects(memberId);
	    //重新申请资质
	    for(MemberCredentials memberCredentials : rejectList){
            this.memberCredentialsService.setApply(memberCredentials.getId());
        }
    }

	/**
	 * 企业信息验证
	 * @param enterprise
	 */
	public void validEnterprise(Enterprise enterprise, BindingResult bindingResult) {
		EnterpriseQuery query = new EnterpriseQuery();
		if (null != enterprise.getId()) {
			query.setIdNot(enterprise.getId());
		}
		if(enterprise.getEpType()!=null&&enterprise.getEpType()==1){
			//境外
			enterprise.setOrganizationCode(null);
			enterprise.setTaxCode(null);
			enterprise.setTaxNo(null);
			enterprise.setLicenseNo(null);
			enterprise.setSocialCreditCode(null);
		}else {
			//境内
		}

		//社会信用统一代码不为空，并且为true
		if(enterprise.getUseSocialCreditCert()!=null&&enterprise.getUseSocialCreditCert()){
			//三证合一
			enterprise.setOrganizationCode(null);
			enterprise.setTaxCode(null);
			enterprise.setTaxNo(null);
			enterprise.setLicenseNo(null);
		}else {
			enterprise.setSocialCreditCode(null);
		}
		//如果组织机构代码不为空
		if (StringUtils.isNotEmpty(enterprise.getOrganizationCode())) {
			query.setOrganizationCode(enterprise.getOrganizationCode());
			int count = enterpriseMapper.count(query);
			if(count > 0){
				bindingResult.rejectValue("organizationCode",null, VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO32));
			}
			query.setOrganizationCode(null);
		}
		//如果营业执照号不为空
		if (StringUtils.isNotEmpty(enterprise.getLicenseNo())) {
			query.setLicenseNo(enterprise.getLicenseNo());
			int count = enterpriseMapper.count(query);
			if(count > 0){
				bindingResult.rejectValue("licenseNo",null,VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO33));
			}
			query.setLicenseNo(null);
		}
		if (StringUtils.isNotEmpty(enterprise.getTaxCode())) {
			query.setTaxCode(enterprise.getTaxCode());
			int count = enterpriseMapper.count(query);
			if(count > 0){
				bindingResult.rejectValue("taxCode",null,VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO34));
			}
			query.setTaxCode(null);
		}
		if (StringUtils.isNotEmpty(enterprise.getTaxNo())) {
			query.setTaxNo(enterprise.getTaxNo());
			int count = enterpriseMapper.count(query);
			if(count > 0){
				bindingResult.rejectValue("taxNo",null,VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO35));
			}
			query.setTaxNo(null);
		}
		if (StringUtils.isNotEmpty(enterprise.getSocialCreditCode())) {
			query.setSocialCreditCode(enterprise.getSocialCreditCode());
			int count = enterpriseMapper.count(query);
			if(count > 0){
				bindingResult.rejectValue("socialCreditCode",null,VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO36));
			}
			query.setSocialCreditCode(null);
		}
	}
}
