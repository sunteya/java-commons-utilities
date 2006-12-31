/**
 * Created on 2006-5-18
 * Created by Sunteya
 */
package com.sunteya.commons.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author 诸南敏
 */
public class SwingUtils {

	/**
	 * 设置窗口到屏幕中央
	 * @param window 要设置位置的窗口
	 */
	public static void setToScreenCenter(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = window.getSize();
		if (windowSize.height > screenSize.height) {
			windowSize.height = screenSize.height;
		}
		if (windowSize.width > screenSize.width) {
			windowSize.width = screenSize.width;
		}
		window.setLocation((screenSize.width - windowSize.width) / 2,
				(screenSize.height - windowSize.height) / 2);
	}
}
