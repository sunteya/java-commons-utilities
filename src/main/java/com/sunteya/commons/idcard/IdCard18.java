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
public interface IdCard18 extends IdentityCard {
	public static final int INFORMATION_CODE_LENGTH = 17;
	public static final int IDENTITY_CARD_LENGTH = 18;

	public abstract String getInformationCode();
	public abstract String getCheckCode();
}