package com.zjl.dao;

import java.util.List;
import java.util.Map;

/**
 * 数据库操作接口
 * @author zhoujialin
 * @created 2018年2月2日
 */
public interface IBaseDAO {

	/**
	 * 批量插入多行数据
	 * @param tableName 表名
	 * @param records 待插入的多行数据
	 */
	void insertBatch(String tableName, List<Map<String,Object>> records);
	
}
