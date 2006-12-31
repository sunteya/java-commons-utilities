/**
 * Created on 2006-5-26
 * Created by Sunteya
 */
package com.sunteya.commons.collections;

/**
*
* @author Sunteya
* @email Sunteya@gmail.com
*/
class Position {
	public int column;
	public int row;

	/**
	 * @param column
	 * @param row
	 */
	public Position(int row, int column) {
		super();
		this.column = column;
		this.row = row;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + this.column;
		result = PRIME * result + this.row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Position other = (Position) obj;
		if (this.column != other.column) {
			return false;
		}
		if (this.row != other.row) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "(row=" + this.row + ",column=" + this.column + ")";
	}

}
