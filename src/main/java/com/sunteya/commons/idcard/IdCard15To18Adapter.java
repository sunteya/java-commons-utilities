/**
 * Created on 2006-6-30
 * Created by Sunteya
 */
package com.sunteya.commons.idcard;

import java.util.Date;

/**
 *
 * @author 诸南敏
 * @email zhunm@dep5.com
 */
public class IdCard15To18Adapter implements IdCard18 {
	private IdCard15 fifteenIdentityCard;

	public IdCard15To18Adapter(IdCard15 fifteenIdentityCard) {
		this.fifteenIdentityCard = fifteenIdentityCard;
	}

	public String getIdentityCode() {
		return getInformationCode() + getCheckCode();
	}

	public String getInformationCode() {
		return getRegionCode() + IdCardUtils.formatDate(getBirthday())
				+ getSerialNumber();
	}

	public String getCheckCode() {
		return IdCard18Utils.calculateCheckCode(getInformationCode());
	}

	public Date getBirthday() {
		return this.fifteenIdentityCard.getBirthday();
	}

	public int getIdentityCodeLength() {
		return this.fifteenIdentityCard.getIdentityCodeLength();
	}

	public String getRegionCode() {
		return this.fifteenIdentityCard.getRegionCode();
	}

	public String getSerialNumber() {
		return this.fifteenIdentityCard.getSerialNumber();
	}

	public boolean isFemale() {
		return this.fifteenIdentityCard.isFemale();
	}

	public boolean isMale() {
		return this.fifteenIdentityCard.isMale();
	}
}
