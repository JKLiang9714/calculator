package org.liangjiakun.cal;

import javax.swing.JFrame;

/**
 * 计算器入口类
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
		//传入参数null,即可让JFrame 位于屏幕中央
		//若传入一个Component,则JFrame位于该组件的中央
		f.setLocationRelativeTo(null);
	}
}
