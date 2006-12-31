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
public interface IdentityCard {

	public abstract String getIdentityCode();

	public abstract int getIdentityCodeLength();

	public abstract String getRegionCode();

	public abstract Date getBirthday();

	public abstract String getSerialNumber();

	public abstract boolean isMale();

	public abstract boolean isFemale();

}