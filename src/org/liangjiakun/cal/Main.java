package org.liangjiakun.cal;

import javax.swing.JFrame;

/**
 * �����������
 * 
 * @author Liang Jiakun
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		CalFrame f = new CalFrame();
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�������null,������JFrame λ����Ļ����
		//������һ��Component,��JFrameλ�ڸ����������
		f.setLocationRelativeTo(null);
	}
}
