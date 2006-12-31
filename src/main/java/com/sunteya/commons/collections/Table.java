/**
 * Created on 2006-5-26
 * Created by Sunteya
 */
package com.sunteya.commons.collections;

/**
 * 提供表格支持，表格从0开始
 *
 * @author 诸南敏
 * @email Sunteya@gmail.com
 * @param <V>
 */
public interface Table<V> {

	/**
	 * 得到总行数
	 *
	 * @return 得到总行数
	 */
	public abstract int getRowCount();

	/**
	 * 得到总列数
	 *
	 * @return 得到总列数
	 */
	public abstract int getColumnCount();

	/**
	 * 得到单元格值
	 * @param row
	 * @param column
	 * @return
	 */
	public abstract V getValue(int row, int column);

	/**
	 * 在尾部增加一行
	 */
	public abstract void addRowAtLast();

	/**
	 * 在尾部增加一列
	 */
	public abstract void addColumnAtLast();

	/**
	 * 设置某行的值
	 * @param row
	 * @param column
	 * @param cell
	 */
	public abstract void setValueAt(int row, int column, V cell);

	/**
	 * 在指定位置插入一行
	 * @param row
	 */
	public abstract void insertRowAt(int row);

	/**
	 * 在指定位置插入一列
	 * @param column
	 */
	public abstract void insertColumnAt(int column);

	/**
	 *
	 *
	 * @author 诸南敏
	 * @param i
	 */
	public abstract void deleteRowAt(int row);

	/**
	 *
	 * @param column
	 */
	public abstract void deleteColumnAt(int column);

}