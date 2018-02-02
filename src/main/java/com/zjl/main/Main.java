package com.zjl.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import com.zjl.dao.IBaseDAO;
import com.zjl.dao.impl.BaseDAOImpl;
import com.zjl.util.MyUtils;

/**
 * 模拟数据程序
 * 
 * @author Administrator
 * 
 */
public class Main {
	private static final Random random = new Random();
	private static final List<Map<String,Object>> records = new ArrayList<Map<String,Object>>();
	
	/**
	 * 在mysql数据库的order_record表中批量生成一万条随机记录
	 */
	public static void mock() {
		for (int i = 0; i < 10000; i++) {
			//record表示一行数据，其中的key对应数据库表中的字段名，value对应字段的值
			Map<String,Object> record = new TreeMap<String,Object>();
			record.put("order_id", i+1);
			record.put("order_date", MyUtils.randomRangeDate("1987-11-31", "2017-11-31"));
			record.put("user_id", random.nextInt(1000000));
			record.put("goods_id", random.nextInt(1000000));
			record.put("amount", random.nextInt(1000000));
			record.put("order_status", random.nextInt(2));
			record.put("remark", "remark" + i);
			//records表示多行数据的集合
			records.add(record);
		}
		IBaseDAO baseDAOImpl = new BaseDAOImpl();
		baseDAOImpl.insertBatch("order_record", records);
	}

	public static void main(String[] args) {
		mock();
	}
}
