package com.sinochem.crude.trade.info.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

public class DBUtil {
	private String className = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://10.160.112.12:3306/zhcms?useUnicode=true&characterEncoding=UTF-8";
	private String user = "root";
	private String password = "Oil$msl1!";
	
	private static final Log log = LogFactory.getLog(DBUtil.class);
	
	public void setClassName(String className) {
		this.className = className;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public  Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		} catch (SQLException e) {
			log.error("SQLException", e);
		}
		return conn;
	}

	public  void realseConn(Connection conn, PreparedStatement pstmt,
			ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			log.error("", e);
		}

	}

	public List<Map<String, Object>> queryAll(String sql, Map<Integer, Object> conditionMap) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> resultList = new ArrayList<>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			if (conditionMap != null && conditionMap.size() != 0) {

				int paramNum = conditionMap.size();
				for (int i = 1; i <= paramNum; i++) {

					Object paramValue = conditionMap.get(i);

					String type = paramValue.getClass().getName();
					switch(type){
					case "java.lang.Integer":
						pstmt.setInt(i, Integer.parseInt(paramValue.toString()));
					case "java.lang.String":
						pstmt.setString(i, paramValue.toString());
					case "java.math.BigDecimal":
						pstmt.setBigDecimal(i, new BigDecimal(paramValue.toString()));
					}
				}
			}

			rs = pstmt.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnNum = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> dataMap = new HashMap<String, Object>(0);
				for (int i = 1; i <= columnNum; i++) {
					dataMap.put(rsmd.getColumnLabel(i), rs.getObject(i));
				}
				resultList.add(dataMap);
			}

		} catch (SQLException e) {
			log.error("", e);
		} finally {
			realseConn(conn, pstmt, rs);
		}
		if(null==resultList || resultList.isEmpty()){
			Map<String,Object> valueMap=new HashMap<>();
			resultList.add(valueMap);
		}
		return resultList;
	}


	

	/*public  void test1() {
		String sql = "select * from test where id=? and name=?";
		Map<Integer, Object> conditionMap = new HashMap<Integer, Object>();
		conditionMap.put(1, 5);
		conditionMap.put(2, "e");
		List<Map<String, Object>> resultList = DBUtil.queryAll(sql,
				conditionMap);
		System.out.println(resultList);
	}

	/*public  void test2() {
		String sql = "select * from user where id=? and username=?";
		Map<Integer, Object> conditionMap = new HashMap<Integer, Object>();
		conditionMap.put(1, 1);
		conditionMap.put(2, "admin");
		List<Map<String, Object>> resultList = DBUtil.queryAll(sql,
				conditionMap);
		System.out.println(resultList);
	}

	public  void test3() {
		String sql = "select * from user";
		List<Map<String, Object>> resultList = DBUtil.queryAll(sql, null);
		System.out.println(resultList);
	}*/
}
