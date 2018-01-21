package org.liangjiakun.cal;

import java.math.BigDecimal;

/**
 * 四则运算类
 * 
 * @author Liang Jiakun
 * @version 1.0
 */
public class MyMath {
	// 保留小数点位数
	public static final int DEFAULT_SCALE = 20;
	
	/**
	 * 加法
	 * 
	 * @param num1
	 * @param num2
	 * @return 两者相加的结果
	 */
	public static double add(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.add(second).doubleValue();
	}
	
	/**
	 * 减法
	 * 
	 * @param num1
	 * @param num2
	 * @return 两者相减的结果
	 */
	public static double subtract(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.subtract(second).doubleValue();
	}
	
	/**
	 * 乘法
	 * 
	 * @param num1
	 * @param num2
	 * @return 两者相乘的结果
	 */
	public static double multiply(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.multiply(second).doubleValue();
	}
	
	/**
	 * 除法
	 * 
	 * @param num1
	 * @param num2
	 * @return 两者相除的结果
	 */
	public static double divide(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}
}
