package com.zjl.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.zjl.dao.IBaseDAO;
import com.zjl.jdbc.JDBCHelper;
import com.zjl.util.MyUtils;

public class BaseDAOImpl implements IBaseDAO {

	public void insertBatch(String tableName, List<Map<String, Object>> records) {
		Map<String, Object> row = records.get(0);
		StringBuilder fields = new StringBuilder();
		StringBuilder questions = new StringBuilder();
		for(String field : row.keySet()) {
			fields.append(field).append(",");
			questions.append("?,");
		}
		String fieldsStr = fields.toString();
		fieldsStr = MyUtils.trimComma(fieldsStr , ",");
		String questionsStr = questions.toString();
		questionsStr = MyUtils.trimComma(questionsStr , ",");
		String sql = "INSERT INTO "+ tableName + "(" + fieldsStr +") VALUES(" + questionsStr + ")";
		List<Object[]> paramsList = new ArrayList<Object[]>();
		for(Map<String, Object> record : records) {
			List<Object> params = new ArrayList<Object>();
			for (Entry<String, Object> entry : record.entrySet()) {
				params.add(entry.getValue());
			}
			paramsList.add(params.toArray());
		}
		
		JDBCHelper jdbcHelper = JDBCHelper.getInstance();
		jdbcHelper.executeBatch(sql, paramsList);
	}

}
