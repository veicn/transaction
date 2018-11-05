package com.sinochem.crude.trade.transaction.dao;

import com.github.pagehelper.Page;
import com.sinochem.crude.trade.transaction.domain.po.ContractSheet;
import com.sinochem.crude.trade.transaction.model.query.ContractSheetQuery;

import java.util.List;

public interface ContractSheetMapper {

    /**
     * 新增对象
     */
    int insert(ContractSheet contractSheet);

    /**
     * 根据主键物理删除, 慎用
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键修改对象
     */
    int updateByPrimaryKey(ContractSheet contractSheet);

    /**
     * 根据主键修改对象，更新有值的字段
     */
    int updateByPrimaryKeySelective(ContractSheet contractSheet);

    /**
     * 根据主键查询对象
     */
    ContractSheet selectByPrimaryKey(Long id);

    /**
     * 根据uuid查询对象
     */
    ContractSheet selectByUuid(String uuid);

    //**************************以下方法由开发者补充*********************************/

    /**
     * 根据条件查询合约单（订单）
     */
    List<ContractSheet> getContractSheetList(ContractSheetQuery contractSheetQuery);

    Page<ContractSheet> getContractSheetPage(ContractSheetQuery contractSheetQuery);

    /**
     * 根据条件查询合约单（订单）(微信)
     */
    List<ContractSheet> getContractSheetListwx(ContractSheetQuery contractSheetQuery);


    /**
     * 查询泉化可见的合约单（订单）
     */
    List<ContractSheet> getQuanzhouContractSheetList(ContractSheetQuery contractSheetQuery);

    /**
     * 查询泉化可见的合约单（订单） 微信
     */
    List<ContractSheet> getQuanzhouContractSheetListwx(ContractSheetQuery contractSheetQuery);

    /**
     * 根据销售单id查询合约单
     */

    ContractSheet getContractBySaleSheetId(Long saleSheetId);


    /**
     * 根据销售单serial_number查询合约单
     */

    ContractSheet selectBySerialNumber(String serialNumber);
}