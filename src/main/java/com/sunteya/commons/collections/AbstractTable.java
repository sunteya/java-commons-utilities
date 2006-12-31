package com.sunteya.commons.collections;

import java.io.Serializable;

public abstract class AbstractTable<V> implements Table<V>, Serializable {

	protected boolean isOutOfBounds(int row, int column) {
		if (isOutOfColumn(column)) {
			return true;
		}
		if (isOutOfRow(row)) {
			return true;
		}

		return false;
	}

	protected boolean isOutOfRow(int row) {
		if (row < 0 || row >= getRowCount()) {
			return true;
		}
		return false;
	}

	protected boolean isOutOfColumn(int column) {
		if (column < 0 || column >= getColumnCount()) {
			return true;
		}
		return false;
	}
}
