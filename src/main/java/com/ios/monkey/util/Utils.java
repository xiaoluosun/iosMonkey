package com.ios.monkey.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生成唯一序列号
 * @author sun
 *
 */
public class Utils {
	
	/**
     * 用当前日期生成唯一序列号
     * 格式：年+月+日+小时+分钟      20150421113300
	 * @return 
     */ 
	public static String currentTime(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		Calendar cal = Calendar.getInstance();
		String times = format.format(cal.getTime()).substring(0,19);
		Pattern pattern = Pattern.compile("[^0-9]");  
		Matcher matcher = pattern.matcher(times); 
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sbr,"");
		}
		matcher.appendTail(sbr);
//		System.out.println(sbr.toString());
		return sbr.toString();	
	}
	
	public static void sleep(double d) {
		try {
			d *= 1000;
			Thread.sleep((int)d);
		} catch (Exception e){}
	}
	
}
