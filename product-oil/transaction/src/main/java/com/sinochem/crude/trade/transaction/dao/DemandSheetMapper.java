package com.sinochem.crude.trade.transaction.dao;

import com.sinochem.crude.trade.transaction.domain.po.DemandSheet;
import com.sinochem.crude.trade.transaction.model.query.DemandSheetQuery;

import java.util.List;

public interface DemandSheetMapper {

    /**
     * 新增对象
     */
    int insert(DemandSheet demandSheet);

    /**
     * 根据主键物理删除, 慎用
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键修改对象
     */
    int updateByPrimaryKey(DemandSheet demandSheet);

    /**
     * 根据主键修改对象，更新有值的字段
     */
    int updateByPrimaryKeySelective(DemandSheet demandSheet);

    /**
     * 根据主键查询对象
     */
    DemandSheet selectByPrimaryKey(Long id);

    /**
     * 根据uuid查询对象
     */
    DemandSheet selectByUuid(String uuid);

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据条件查询销售需求单
     */
    List<DemandSheet> getDemandSheetList(DemandSheetQuery demandSheetQuery);

    /**
     * 根据条件查询销售需求单，并受权限控制（比如定向企业）
     */
    List<DemandSheet> getVisibleDemandSheetList(DemandSheetQuery demandSheetQuery);

    /**
     * 根据条件查询泉化能看到的销售需求单
     */
    List<DemandSheet> getQuanzhouVisibleDemandSheet(DemandSheetQuery demandSheetQuery);
}