/**
 * Created on 2006-6-30
 * Created by Sunteya
 */
package com.sunteya.commons.idcard;

/**
 *
 * @author 诸南敏
 * @email zhunm@dep5.com
 */
public class IdCard18Utils {
	private static final String[] CHECK_CODES = { "1", "0", "X", "9", "8", "7",
			"6", "5", "4", "3", "2" };

	public static String calculateCheckCode(String informationCode) {
		String infoCode = informationCode;
		long sum = 0;
		for (int i = 0; i < getInformationCodeLength(); i++) {
			long factor = getFactor(i);
			int num = Integer.parseInt(infoCode.substring(i, i + 1));
			sum += factor * num;
		}
		int offset = (int) sum % 11;
		return CHECK_CODES[offset];
	}

	/**
	 * 计算交验信息中需要的权值
	 */
	private static long getFactor(int index) {
		int exponent = getInformationCodeLength() - index;
		long factor = (long) Math.pow(2, exponent);
		return factor;
	}

	private static int getInformationCodeLength() {
		return IdCard18.INFORMATION_CODE_LENGTH;
	}
}
