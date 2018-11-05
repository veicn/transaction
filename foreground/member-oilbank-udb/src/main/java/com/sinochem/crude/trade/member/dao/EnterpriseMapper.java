package com.sinochem.crude.trade.member.dao;

import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.model.EnterpriseDetail;
import com.sinochem.crude.trade.member.remote.vo.EnterpriseVo;

import java.util.List;

public interface EnterpriseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKeyWithBLOBs(Enterprise record);

    int updateByPrimaryKey(Enterprise record);

	List<Enterprise> selectEnter(String name);


	//企业信息详情查询
	Enterprise enterpersiceDetail(Long memberId);

	List<Enterprise> selectByNameLike(String name);

	//根据前台id回显所需数据
	Enterprise enterpriseById(Long id);

	Enterprise getByMemberId(Long id);

    EnterpriseQuery selectWithInfoByMemberId(Long memberId);

    List<EnterpriseDetail> selectAllDetail();

    List<EnterpriseDetail> selectByCredentials(String code);

    /**
     * 查询企业列表
     * <p>
     *     包含企业补充信息
     * </p>
     * @param query
     * @return
     */
    List<Enterprise> selectWithCrude(EnterpriseQuery query);

    /**
     * 根据条件统计条数
     * @param query
     * @return
     */
    int count(EnterpriseQuery query);



    /**
     * 按名称模糊查询企业
     * @param epname
     * @return
     */
    List<EnterpriseDetail> selectAllDetailByName(String epname);

    /**
     * 根据公司名称集合查询公司信息列表 去重
     * @param list
     */
    List<Enterprise> queryEnterpriseVoList(List<String> list);
}