/**
 * Created on 2006-5-26
 * Created by Sunteya
 */
package com.sunteya.commons.collections;

import java.util.HashMap;
import java.util.Map;

/**
*
* @author Sunteya
* @email Sunteya@gmail.com
*/
public class SimpleTable<V> extends AbstractTable<V> {

	private static final long serialVersionUID = 5228575066403988903L;

	private int row;
	private int column;

	private Map<Position, V> cells;

	public SimpleTable() {
		this(0, 0);
	}

	/**
	 * @param row
	 * @param column
	 */
	public SimpleTable(int row, int column) {
		this.row = row < 0 ? 0 : row;
		this.column = column < 0 ? 0 : column;
		this.cells = new HashMap<Position, V>();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#getRowCount()
	 */
	public int getRowCount() {
		return this.row;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#getColumnCount()
	 */
	public int getColumnCount() {
		return this.column;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#getValue(int, int)
	 */
	public V getValue(int row, int column) {
		checkAndThrowBounds(row, column);

		V cell = this.cells.get(new Position(row, column));
		return cell;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#addRowAtLast()
	 */
	public void addRowAtLast() {
		this.row++;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#addColumnAtLast()
	 */
	public void addColumnAtLast() {
		this.column++;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#setValueAt(int, int, V)
	 */
	public void setValueAt(int row, int column, V cell) {
		checkAndThrowBounds(row, column);
		this.cells.put(new Position(row, column), cell);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#insertRowAt(int)
	 */
	public void insertRowAt(int row) {
		Map<Position, V> newPostions = new HashMap<Position, V>();
		for (int rowIndex = row; rowIndex < getRowCount(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
				V cell = this.cells.remove(new Position(rowIndex, columnIndex));
				if (cell != null) {
					newPostions.put(new Position(rowIndex + 1, columnIndex),
							cell);
				}
			}
		}
		this.row++;
		this.cells.putAll(newPostions);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#insertColumnAt(int)
	 */
	public void insertColumnAt(int column) {
		Map<Position, V> newPostions = new HashMap<Position, V>();
		for (int rowIndex = 0; rowIndex < getRowCount(); rowIndex++) {
			for (int columnIndex = column; columnIndex < getColumnCount(); columnIndex++) {
				V cell = this.cells.remove(new Position(rowIndex, columnIndex));
				if (cell != null) {
					newPostions.put(new Position(rowIndex, columnIndex + 1),
							cell);
				}
			}
		}
		this.column++;
		this.cells.putAll(newPostions);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#deleteRowAt(int)
	 */
	public void deleteRowAt(int row) {
		checkAndThrowBounds(row, 0);

		Map<Position, V> newPostions = new HashMap<Position, V>();
		for (int rowIndex = row; rowIndex < getRowCount(); rowIndex++) {
			for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
				V cell = this.cells.remove(new Position(rowIndex, columnIndex));
				if (rowIndex != row && cell != null) {
					newPostions.put(new Position(rowIndex - 1, columnIndex),
							cell);
				}
			}
		}
		this.row--;
		this.cells.putAll(newPostions);

	}

	private void checkAndThrowBounds(int row, int column) {
		if (isOutOfBounds(row, column)) {
			throw new IndexOutOfBoundsException("row=" + row + ", column="
					+ column);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.bankcomm.crms.report.reliable.Table#deleteColumnAt(int)
	 */
	public void deleteColumnAt(int column) {
		checkAndThrowBounds(0, column);

		Map<Position, V> newPostions = new HashMap<Position, V>();
		for (int rowIndex = 0; rowIndex < getRowCount(); rowIndex++) {
			for (int columnIndex = column; columnIndex < getColumnCount(); columnIndex++) {
				V cell = this.cells.remove(new Position(rowIndex, columnIndex));
				if (columnIndex != column && cell != null) {
					newPostions.put(new Position(rowIndex, columnIndex - 1),
							cell);
				}
			}
		}
		this.column--;
		this.cells.putAll(newPostions);
	}
}
