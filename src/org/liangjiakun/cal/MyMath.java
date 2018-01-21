package org.liangjiakun.cal;

import java.math.BigDecimal;

/**
 * ����������
 * 
 * @author Liang Jiakun
 * @version 1.0
 */
public class MyMath {
	// ����С����λ��
	public static final int DEFAULT_SCALE = 20;
	
	/**
	 * �ӷ�
	 * 
	 * @param num1
	 * @param num2
	 * @return ������ӵĽ��
	 */
	public static double add(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.add(second).doubleValue();
	}
	
	/**
	 * ����
	 * 
	 * @param num1
	 * @param num2
	 * @return ��������Ľ��
	 */
	public static double subtract(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.subtract(second).doubleValue();
	}
	
	/**
	 * �˷�
	 * 
	 * @param num1
	 * @param num2
	 * @return ������˵Ľ��
	 */
	public static double multiply(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.multiply(second).doubleValue();
	}
	
	/**
	 * ����
	 * 
	 * @param num1
	 * @param num2
	 * @return ��������Ľ��
	 */
	public static double divide(double num1, double num2) {
		BigDecimal first = new BigDecimal(num1);
		BigDecimal second = new BigDecimal(num2);
		return first.divide(second, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP)
				.doubleValue();
	}
}
