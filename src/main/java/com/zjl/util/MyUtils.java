package com.zjl.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import com.zjl.exception.MyException;

/**
 * 工具类
 * @author Administrator
 *
 */
public class MyUtils {
	
	private MyUtils() {}
	/**
	 * 截断字符串两侧的分隔符
	 * @param str 字符串
	 * @return 字符串
	 */
	public static String trimComma(String str,String reg) {
		if(str.startsWith(reg)) {
			str = str.substring(1);
		}
		if(str.endsWith(reg)) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	/**
	 * 随机生成某个区间内的时间
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Date randomRangeDate(String startDate,String endDate) {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		if(!pattern.matcher(startDate).matches() || !pattern.matcher(endDate).matches()) {
			throw new MyException("时间格式必须为yyyy-MM-dd!");
		}
		if(startDate.compareTo(endDate) >= 0) {
			throw new MyException("开始时间必须小于结束时间!");
		}
		Calendar calendar = Calendar.getInstance();
		// 注意月份要减去1
		String[] startYMD = startDate.split("-");
		calendar.set(Integer.parseInt(startYMD[0]), Integer.parseInt(startYMD[1]) - 1, Integer.parseInt(startYMD[2]));
		calendar.getTime().getTime();
		// 根据需求，这里要将时分秒设置为0
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		long min = calendar.getTime().getTime();

		String[] endYMD = endDate.split("-");
		calendar.set(Integer.parseInt(endYMD[0]), Integer.parseInt(endYMD[1]) - 1, Integer.parseInt(endYMD[2]));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.getTime().getTime();
		long max = calendar.getTime().getTime();
		// 得到大于等于start小于end的double值
		double randomDate = Math.random() * (max - min) + min;
		// 将double值舍入为整数，转化成long类型
		calendar.setTimeInMillis(Math.round(randomDate));
		return calendar.getTime();
	}

}
