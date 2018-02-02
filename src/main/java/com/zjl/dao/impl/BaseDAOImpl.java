package com.zjl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.zjl.dao.IBaseDAO;
import com.zjl.jdbc.JDBCHelper;
import com.zjl.util.MyUtils;

public class BaseDAOImpl implements IBaseDAO {

	public void insertBatch(String tableName, List<Map<String, Object>> records) {
		Map<String, Object> row = records.get(0);
		String fields = "";
		String questions = "";
		for(String field : row.keySet()) {
			fields += field + ",";
			questions += "?,";
		}
		fields = MyUtils.trimComma(fields , ",");
		questions = MyUtils.trimComma(questions , ",");
		String sql = "INSERT INTO "+ tableName + "(" + fields +") VALUES(" + questions + ")";
		List<Object[]> paramsList = new ArrayList<Object[]>();
		for(Map<String, Object> record : records) {
			List<Object> params = new ArrayList<Object>();
			for(Object key : record.keySet()) {
				params.add(record.get(key));
			}
			paramsList.add(params.toArray());
		}
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeBatch(sql, paramsList);
	}

}
