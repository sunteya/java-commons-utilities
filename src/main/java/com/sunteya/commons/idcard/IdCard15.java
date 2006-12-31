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
public class IdCard15 extends AbstractIdCard {

	public IdCard15(String identityCode) {
		super(identityCode);
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
		String fixSource = "19" + identityCode.substring(6, 12);
		Date birthday = IdCardUtils.parseDate(fixSource);
		if (!fixSource.equals(IdCardUtils.formatDate(birthday))) {
			throw new IllegalArgumentException("日期格式错误");
		}
		return birthday;
	}

	@Override
	protected String parseSerialNumber(String identityCode) {
		String serialNumber = identityCode.substring(12);
		if (IdCardUtils.isNotNumber(serialNumber)) {
			throw new IllegalArgumentException("顺序码格式错误");
		}
		return serialNumber;
	}

	public int getIdentityCodeLength() {
		return 15;
	}
}
