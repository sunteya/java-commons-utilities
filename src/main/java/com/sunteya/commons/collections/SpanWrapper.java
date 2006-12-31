/**
 * Created on 2006-5-26
 * Created by Sunteya
 */
package com.sunteya.commons.collections;

import java.io.Serializable;

/**
 *
 * @author Sunteya
 * @email Sunteya@gmail.com
 */
public class SpanWrapper<V> implements Serializable {

	private static final long serialVersionUID = 2646760148799514107L;
	private V source;
	private int rowSpan;
	private int columnSpan;

	/**
	 * @param source
	 */
	public SpanWrapper(V source) {
		this(source, 1, 1);
	}

	/**
	 * @param source
	 * @param row
	 * @param column
	 */
	public SpanWrapper(V source, int row, int column) {
		super();
		this.source = source;
		this.rowSpan = row;
		this.columnSpan = column;
	}

	/**
	 *
	 * @return
	 */
	public V getSource() {
		return this.source;
	}

	/**
	 *
	 * @return
	 */
	public int getColumnSpan() {
		return this.columnSpan;
	}

	/**
	 *
	 * @return
	 */
	public int getRowSpan() {
		return this.rowSpan;
	}

	/**
	 *
	 * @param other
	 */
	public void setSource(V other) {
		this.source = other;
	}

	/**
	 *
	 * @param column
	 */
	public void setColumnSpan(int column) {
		this.columnSpan = column;
	}

	/**
	 *
	 * @param row
	 */
	public void setRowSpan(int row) {
		this.rowSpan = row;
	}

	/**
	 *
	 * @return
	 */
	public boolean isCombined() {
		if (this.rowSpan == 1 && this.columnSpan == 1) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "rowSpan=" + this.rowSpan + ",columnSpan=" + this.columnSpan;// +
																	// "source="
		// + source == null ? "null" : source.toString();
	}
}
