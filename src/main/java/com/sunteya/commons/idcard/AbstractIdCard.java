/**
 * Created on 2006-6-29
 * Created by Sunteya
 */
package com.sunteya.commons.idcard;

import java.util.Date;

/**
 *
 * @author 诸南敏
 * @email zhunm@dep5.com
 */
public abstract class AbstractIdCard implements IdentityCard {

	private String identityCode;
	private Date birthday;
	private String serialNumber;
	private String regionCode;

	protected AbstractIdCard(String identityCode) {
		if (identityCode.length() != getIdentityCodeLength()) {
			throw new IllegalArgumentException("证件号长度错误");
		}
		this.identityCode = identityCode;
		this.regionCode = parseRegionCode(identityCode);
		this.birthday = parseBirthday(identityCode);
		this.serialNumber = parseSerialNumber(identityCode);
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public String getIdentityCode() {
		return this.identityCode;
	}

	public boolean isMale() {
		String endStr = getSerialNumber().substring(2);
		int endNum = Integer.parseInt(endStr);
		return endNum % 2 == 1 ? true : false;
	}

	public boolean isFemale() {
		return !isMale();
	}

	protected abstract String parseSerialNumber(String identityCode);

	protected abstract Date parseBirthday(String identityCode);

	protected abstract String parseRegionCode(String identityCode);
}