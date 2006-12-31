/**
 * Created on 2006-5-25
 * Created by Sunteya
 */
package com.sunteya.commons.util;

import java.util.*;

/**
 * 按字母和中文拼音（理论上是UNICODE按照拼音编码）比较
 *
 * @author 诸南敏
 */
public class StringComparator implements Comparator<String> {

	public int compare(String strA, String strB) {
		byte[] bytesA = strA.getBytes();
		byte[] bytesB = strB.getBytes();

		int len = Math.min(bytesA.length, bytesB.length);
		for (int i = 0; i < len; i++) {
			int def = bytesA[i] - bytesB[i];
			if (def == 0) {
				continue;
			}
			return def;
		}

		if (bytesA.length > bytesB.length) {
			return 1;
		} else if (bytesA.length == bytesB.length) {
			return 0;
		} else {
			return -1;
		}
	}
}
