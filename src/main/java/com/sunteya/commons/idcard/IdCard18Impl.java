/**
 * Created on 2006-6-29
 * Created by Sunteya
 */
package com.sunteya.commons.idcard;

import java.util.Date;

/**
 *
 * @author 诸南敏
 * @email Sunteya@gmail.com
 */
public class IdCard18Impl extends AbstractIdCard implements IdCard18 {

	public IdCard18Impl(String identityCode) {
		super(identityCode);

		String checkCode = identityCode.substring(17);
		if (!checkCode.equals(IdCard18Utils.calculateCheckCode(getInformationCode()))) {
			throw new IllegalArgumentException("证号交验错误");
		}
	}

	@Override
	protected String parseRegionCode(String identityCode) {
		String regionCode = identityCode.substring(0, 6);
		if (IdCardUtils.isNotNumber(regionCode)) {
			throw new IllegalArgumentException("地区码格式错误");
		}
		return regionCode;
	}

	@Override
	protected Date parseBirthday(String identityCode)
			throws IllegalArgumentException {
		String source = identityCode.substring(6, 14);
		Date birthday = null;
		birthday = IdCardUtils.parseDate(source);
		if (source.equals(IdCardUtils.formatDate(birthday))) {
			new IllegalArgumentException("日期格式错误");
		}
		return birthday;
	}

	@Override
	protected String parseSerialNumber(String identityCode) {
		String serialNumber = identityCode.substring(14, 17);
		if (IdCardUtils.isNotNumber(serialNumber)) {
			throw new IllegalArgumentException("顺序码格式错误");
		}
		return serialNumber;
	}

	public String getInformationCode() {
		return getIdentityCode().substring(0, getInformationCodeLength());
	}

	private int getInformationCodeLength() {
		return IdCard18.INFORMATION_CODE_LENGTH;
	}

	public int getIdentityCodeLength() {
		return IdCard18.IDENTITY_CARD_LENGTH;
	}

	public String getCheckCode() {
		return IdCard18Utils.calculateCheckCode(getInformationCode());
	}
}
