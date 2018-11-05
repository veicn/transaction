package com.sinochem.crude.trade.info.dao;

import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sinochem.crude.trade.info.domain.Fabulous;
//import org.apache.ibatis.annotations.Mapper;
import com.sinochem.crude.trade.info.model.FabulousVO;
import com.sinochem.crude.trade.member.user.CurrentUser;

//@Mapper
public interface FabulousMapper {

	public int insertRecord(Fabulous entity);
	
	public int deleteById( @Param("id") Long id);
	
	public int deleteRecords(Fabulous entity);
	
	public int updateRecordById(Fabulous entity);
	
	public int updateRecordByUuid(Fabulous entity);
	
	public int updateRecords(Map<String,Object> map);
	
	public Fabulous findByPrimaryKey( Long id);	
	
	public Fabulous findByUuid(String uuid);	
	
	//返回对象的List
	public List<Fabulous> queryByEntitys(Fabulous entity);
	
	//返回Map的List
	public List<Map<String,Object>> queryRecords(Map<String,Object> map);
	
	public int countRecords(Map<String,Object> map);


	//**************************以下方法为开发者补充*********************************/
	public Fabulous queryIfTread(FabulousVO vo);

	public List<Fabulous> findFabulousByInfoId(FabulousVO fabulousVO);
}
