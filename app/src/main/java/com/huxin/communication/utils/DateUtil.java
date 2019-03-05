package com.huxin.communication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 * @author Jason
 *
 */
public class DateUtil {

	static String format = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 获取当前时间
	 * 制式yyyy-MM-dd HH:mm:ss
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String dateNow(){
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}
	public static String dateStr(int date){
		String str=null;
		try {
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
			Long time=new Long(date);
			str = format.format(time*1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
	public static String dateStr(String date){
		String str=null;
		try {
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
			Long time=new Long(Integer.parseInt(date));
			str = format.format(time*1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return str;
	}
	/***
	 * 时间对比
	 * @param
	 * @param
	 * @return 间隔秒
	 */
	public static Long dateDiff(String startTime, String endTime) {
		String str = "s";
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;
		// 一天的毫秒数
		long nh = 1000 * 60 * 60;
		// 一小时的毫秒数
		long nm = 1000 * 60;
		// 一分钟的毫秒数
		long ns = 1000;
		// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			// day = diff / nd;// 计算差多少天
			// hour = diff % nd / nh + day * 24;// 计算差多少小时
			// min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			//sec = diff % nd % nh % nm / ns;// 计算差多少秒
			sec = diff/ns;// 计算多少秒
			// 输出结果
			// System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
			// + (min - day * 24 * 60) + "分钟" + sec + "秒。");
			// System.out.println("hour=" + hour + ",min=" + min);
			if (str.equalsIgnoreCase("h")) {
				return hour;
			} else if (str.equalsIgnoreCase("m")) {
				return min;
			} else {
				return sec;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (str.equalsIgnoreCase("h")) {
			return hour;
		} else if (str.equalsIgnoreCase("m")) {
			return min;
		} else {
			return sec;
		}
	}
	/*public static String dateDiff(String startTime, String endTime) {
		String str = "s";
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;
		// 一天的毫秒数
		long nh = 1000 * 60 * 60;
		// 一小时的毫秒数
		long nm = 1000 * 60;
		// 一分钟的毫秒数
		long ns = 1000;
		// 一秒钟的毫秒数
		long diff;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		// 获得两个时间的毫秒时间差异
		try {
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			// day = diff / nd;// 计算差多少天
			// hour = diff % nd / nh + day * 24;// 计算差多少小时
			//min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
			min = diff / nm ;// 计算差多少分钟
			sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			// System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
			// + (min - day * 24 * 60) + "分钟" + sec + "秒。");
			// System.out.println("hour=" + hour + ",min=" + min);
			if (str.equalsIgnoreCase("h")) {
				return hour+"";
			} else if (str.equalsIgnoreCase("m")) {
				return min+"";
			} else {
				if (min!=0) {
					String re=min+"分"+sec+"秒";
					return re;
				}else {
					return sec+"秒";
				}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (str.equalsIgnoreCase("h")) {
			return hour;
		} else if (str.equalsIgnoreCase("m")) {
			return min;
		} else {
			return sec;
		}
		return "";
	}*/

}

