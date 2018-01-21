package org.liangjiakun.cal;

/**
 * ����ҵ����
 * 
 * @author Liang Jiakun
 * @version 1.0
 */
public class CalService {
	// ���ڱ�����ʱ��Ҫ����ļ�����
	private double store = 0;
	// ��һ��������
	private String firstNum = null;
	// �ϴβ���
	private String lastOp = null;
	// �ڶ���������
	private String secondNum = null;
	// �Ƿ��ǵڶ���������������ǣ��������ּ�ʱ�����ı�������������
	private boolean isSecondNum = false;
	
	// ����
	private String numString = "0123456789.";
	// ��������
	private String opString = "+-*/";
	
	/**
	 * Ĭ�Ϲ�����
	 */
	public CalService() {
		super();
	}
	
	/**
	 * ���ش洢���еĽ��
	 * 
	 * @return double
	 */
	public double getStore() {
		return this.store;
	}
	
	/**
	 * ���÷��������ؼ�����
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
	 * ʵ�ִ洢��������
	 * 
	 * @param cmd ��������
	 * @param text �����ı����еĽ��
	 * @return
	 */
	public String mCmd(String cmd, String text) {
		if (cmd.equals("M+")) {
			this.store = MyMath.add(this.store, Double.valueOf(text));
		} else if (cmd.equals("MC")) {
			// �����"MC"�����������store
			this.store = 0;
		} else if (cmd.equals("MR")) {
			// �����"MR"���������store��ֵ������
			isSecondNum = true;
			return String.valueOf(this.store);
		} else if (cmd.equals("MS")) {
			// �����"MS"��������Ѽ��������浽store
			this.store = Double.valueOf(text).doubleValue();
		}
		return null;
	}

	/**
	 * ���㿪��
	 * 
	 * @param text ������е�ֵ
	 * @return
	 */
	public String sqrt(String text) {
		// ��isSecondNum��־Ϊtrue
		this.isSecondNum = true;
		// ������������
		return String.valueOf(Math.sqrt(Double.valueOf(text)));
	}

	/**
	 * ���㵹��
	 * 
	 * @param text ������е�ֵ
	 * @return
	 */
	public String setReciprocal(String text) {
		// ���textΪ0��������
		if (text.equals("0")) {
			return text;
		} else {
			// ��isSecondNum��־Ϊtrue
			this.isSecondNum = true;
			// ������������
			return String.valueOf(MyMath.divide(1, Double.valueOf(text)));
		}
	}

	/**
	 * ����������
	 * 
	 * @param text ������е�ֵ
	 * @return
	 */
	public String setNegative(String text) {
		// ���text�Ǹ������ͽ�����Ϊ����
		if (text.indexOf("-") == 0) {
			return text.substring(1, text.length());
		}
		// ���򣬽�������ɸ���
		return text.equals("0") ? text : "-" + text;
	}

	/**
	 * ��������������
	 * @param text
	 * @param isPercent
	 * @return
	 * @throws Exception
	 */
	public String cal(String text, boolean isPercent) throws Exception {
		// ��ʼ���ڶ���������
		double secondResult = secondNum == null ? Double.valueOf(text)
				.doubleValue() : Double.valueOf(secondNum).doubleValue();
		// �������Ϊ0���򲻴���
		if (this.lastOp.equals("/") && secondResult == 0) {
			return "0";
		}
		// �����"%"��������ڶ���������������������ٳ���100
		if (isPercent) {
			secondResult = MyMath.multiply(Double.valueOf(firstNum), 
					MyMath.divide(Double.valueOf(secondResult), 100));
		}
		// ��������
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
		// ���ڶ������������¸�ֵ
		secondNum = secondNum == null ? text : secondNum;
		// ��isSecondNum��־Ϊtrue
		this.isSecondNum = true;
		return firstNum;
	}

	/**
	 * ���ò�������
	 * 
	 * @param cmd ��������
	 * @param text ������е�ֵ
	 * @return
	 */
	public String setOp(String cmd, String text) {
		// ���˲�����������Ϊ�ϴεĲ���
		this.lastOp = cmd;
		// ���õ�һ����������ֵ
		this.firstNum = text;
		// ���õڶ���������Ϊ��
		this.secondNum = null;
		// ��isSecondNum��־Ϊtrue
		this.isSecondNum = true;
		return null;
	}

	/**
	 * ������������֣�ÿ�ε�����֣���׷�ӵ�����
	 * 
	 * @param cmd ��������
	 * @param text ������ֵ
	 * @return
	 */
	public String catNum(String cmd, String text) {
		String result = cmd;
		// �����ǰ��text������0
		if (!text.equals("0")) {
			if (isSecondNum) {
				// ��isSecondNum��־Ϊfalse
				isSecondNum = false;
			} else {
				// �շ��ؽ��ΪĿǰ��text�����µ��������
				result = text + cmd;
			}
		}
		// �����.��ͷ������ǰ���0
		if (text.indexOf(".") == 0) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * ʵ��backspace����
	 * 
	 * @param text �����ı����еĽ��
	 * @return
	 */
	public String backSpace(String text) {
		return text.equals("0") || text.equals("") ? "0" : text.substring(0,
				text.length() - 1);
	}

	/**
	 * ������м�����
	 * 
	 * @param text �����ı����еĽ��
	 * @return
	 */
	public String clear(String text) {
		return "0";
	}

	/**
	 * ������м�����
	 * 
	 * @return String
	 */
	public String clearAll() {
		// ����һ�ڶ��������ָ�ΪĬ��ֵ
		this.firstNum = "0";
		this.secondNum = null;
		return this.firstNum;
	}
}
