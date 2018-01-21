package org.liangjiakun.cal;

/**
 * 计算业务类
 * 
 * @author Liang Jiakun
 * @version 1.0
 */
public class CalService {
	// 用于保存暂时需要保存的计算结果
	private double store = 0;
	// 第一个操作数
	private String firstNum = null;
	// 上次操作
	private String lastOp = null;
	// 第二个操作数
	private String secondNum = null;
	// 是否是第二个操作数，如果是，单击数字键时则在文本框中重新输入
	private boolean isSecondNum = false;
	
	// 数字
	private String numString = "0123456789.";
	// 四则运算
	private String opString = "+-*/";
	
	/**
	 * 默认构造器
	 */
	public CalService() {
		super();
	}
	
	/**
	 * 返回存储器中的结果
	 * 
	 * @return double
	 */
	public double getStore() {
		return this.store;
	}
	
	/**
	 * 调用方法并返回计算结果
	 * 
	 * @return String	
	 */
	public String callMethod(String cmd, String text) throws Exception {
		if (cmd.equals("C")) {
			return clearAll();
		} else if (cmd.equals("CE")) {
			return clear(text);
		} else if (cmd.equals("Back")) {
			return backSpace(text);
		} else if (numString.indexOf(cmd) != -1) {
			return catNum(cmd, text);
		} else if (opString.indexOf(cmd) != -1) {
			return setOp(cmd, text);
		} else if (cmd.equals("=")) {
			return cal(text, false);
		} else if (cmd.equals("+/-")) {
			return setNegative(text);
		} else if (cmd.equals("1/x")) {
			return setReciprocal(text);
		} else if (cmd.equals("sqrt")) {
			return sqrt(text);
		} else if (cmd.equals("%")) {
			return cal(text, true);
		} else {
			return mCmd(cmd, text);
		}
	}
	
	/**
	 * 实现存储操作命令
	 * 
	 * @param cmd 操作符号
	 * @param text 现在文本框中的结果
	 * @return
	 */
	public String mCmd(String cmd, String text) {
		if (cmd.equals("M+")) {
			this.store = MyMath.add(this.store, Double.valueOf(text));
		} else if (cmd.equals("MC")) {
			// 如果是"MC"操作，则清除store
			this.store = 0;
		} else if (cmd.equals("MR")) {
			// 如果是"MR"操作，则把store的值读出来
			isSecondNum = true;
			return String.valueOf(this.store);
		} else if (cmd.equals("MS")) {
			// 如果是"MS"操作，则把计算结果保存到store
			this.store = Double.valueOf(text).doubleValue();
		}
		return null;
	}

	/**
	 * 计算开方
	 * 
	 * @param text 输入框中的值
	 * @return
	 */
	public String sqrt(String text) {
		// 将isSecondNum标志为true
		this.isSecondNum = true;
		// 计算结果并返回
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}

	/**
	 * 计算倒数
	 * 
	 * @param text 输入框中的值
	 * @return
	 */
	public String setReciprocal(String text) {
		// 如果text为0，则不求倒数
		if (text.equals("0")) {
			return text;
		} else {
			// 将isSecondNum标志为true
			this.isSecondNum = true;
			// 计算结果并返回
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}

	/**
	 * 设置正负数
	 * 
	 * @param text 输入框中的值
	 * @return
	 */
	public String setNegative(String text) {
		// 如果text是负数，就将它变为正数
		if (text.indexOf("-") == 0) {
			return text.substring(1, text.length());
		}
		// 否则，将正数变成负数
		return text.equals("0") ? text : "-" + text;
	}

	/**
	 * 计算四则运算结果
	 * @param text
	 * @param isPercent
	 * @return
	 * @throws Exception
	 */
	public String cal(String text, boolean isPercent) throws Exception {
		// 初始化第二个操作数
		double secondResult = secondNum == null ? Double.valueOf(text)
				.doubleValue() : Double.valueOf(secondNum).doubleValue();
		// 如果除数为0，则不处理
		if (this.lastOp.equals("/") && secondResult == 0) {
			return "0";
		}
		// 如果有"%"操作，则第二个操作数等于两数相乘再除以100
		if (isPercent) {
			secondResult = MyMath.multiply(Double.valueOf(firstNum), 
					MyMath.divide(Double.valueOf(secondResult), 100));
		}
		// 四则运算
		if (this.lastOp.equals("+")) {
			firstNum = String.valueOf(MyMath.add(Double.valueOf(firstNum), 
					secondResult));
		} else if (this.lastOp.equals("-")) {
			firstNum = String.valueOf(MyMath.subtract(Double.valueOf(firstNum), 
					secondResult));
		} else if (this.lastOp.equals("*")) {
			firstNum = String.valueOf(MyMath.multiply(Double.valueOf(firstNum), 
					secondResult));
		} else if (this.lastOp.equals("/")) {
			firstNum = String.valueOf(MyMath.divide(Double.valueOf(firstNum), 
					secondResult));
		}
		// 给第二个操作数重新赋值
		secondNum = secondNum == null ? text : secondNum;
		// 把isSecondNum标志为true
		this.isSecondNum = true;
		return firstNum;
	}

	/**
	 * 设置操作符号
	 * 
	 * @param cmd 操作符号
	 * @param text 输入框中的值
	 * @return
	 */
	public String setOp(String cmd, String text) {
		// 将此操作符号设置为上次的操作
		this.lastOp = cmd;
		// 设置第一个操作数的值
		this.firstNum = text;
		// 设置第二个操作数为空
		this.secondNum = null;
		// 将isSecondNum标志为true
		this.isSecondNum = true;
		return null;
	}

	/**
	 * 连续输入的数字，每次点击数字，就追加到后面
	 * 
	 * @param cmd 操作符号
	 * @param text 输入框的值
	 * @return
	 */
	public String catNum(String cmd, String text) {
		String result = cmd;
		// 如果当前的text不等于0
		if (!text.equals("0")) {
			if (isSecondNum) {
				// 将isSecondNum标志为false
				isSecondNum = false;
			} else {
				// 刚返回结果为目前的text加上新点击的数字
				result = text + cmd;
			}
		}
		// 如果有.开头，就在前面加0
		if (text.indexOf(".") == 0) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * 实现backspace功能
	 * 
	 * @param text 现在文本框中的结果
	 * @return
	 */
	public String backSpace(String text) {
		return text.equals("0") || text.equals("") ? "0" : text.substring(0,
				text.length() - 1);
	}

	/**
	 * 清除所有计算结果
	 * 
	 * @param text 现在文本框中的结果
	 * @return
	 */
	public String clear(String text) {
		return "0";
	}

	/**
	 * 清除所有计算结果
	 * 
	 * @return String
	 */
	public String clearAll() {
		// 将第一第二操作数恢复为默认值
		this.firstNum = "0";
		this.secondNum = null;
		return this.firstNum;
	}
}
