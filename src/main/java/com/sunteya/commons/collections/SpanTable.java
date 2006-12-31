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
public class SpanTable<V> extends AbstractTable<V> {

	private static final long serialVersionUID = 6959314451287902522L;

	protected Table<SpanWrapper<V>> table;

	protected Map<Position, Position> anchorMap;

	public SpanTable() {
		this(0, 0);
	}

	public SpanTable(int row, int column) {
		this.table = new SimpleTable<SpanWrapper<V>>(row, column);
		this.anchorMap = new HashMap<Position, Position>();
	}

	public void addColumnAtLast() {
		this.table.addColumnAtLast();
	}

	public void addRowAtLast() {
		this.table.addRowAtLast();
	}

	public void deleteColumnAt(int column) {
		if (!isOutOfColumn(column)) {
			for (int rowIndex = 0; rowIndex < getRowCount(); rowIndex++) {

				if (isCombined(rowIndex, column)) {
					Position anchor = getAnchorPosition(rowIndex, column);
					int anchorRow = anchor.row;
					int anchorColumn = anchor.column;
					SpanWrapper<V> span = getSpanWrapper(anchorRow,
							anchorColumn);
					if (anchorRow != rowIndex || anchorColumn != column) {
						span.setColumnSpan(span.getColumnSpan() - 1);
					} else {
						if (span.getColumnSpan() != 1) {
							SpanWrapper<V> nextSpan = getSpanWrapper(anchorRow,
									anchorColumn + 1);
							nextSpan.setRowSpan(span.getRowSpan());
							nextSpan.setColumnSpan(span.getColumnSpan() - 1);
						}
					}
					rowIndex = anchorRow + span.getRowSpan() - 1;
				}
			}
		}

		this.table.deleteColumnAt(column);
		rebuildAnchors();
	}

	public void deleteRowAt(int row) {

		if (!isOutOfRow(row)) {
			for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {
				if (isCombined(row, columnIndex)) {
					Position anchor = getAnchorPosition(row, columnIndex);
					int anchorRow = anchor.row;
					int anchorColumn = anchor.column;
					SpanWrapper<V> span = getSpanWrapper(anchorRow,
							anchorColumn);
					if (anchorRow != row || anchorColumn != columnIndex) {
						span.setRowSpan(span.getRowSpan() - 1);
					} else {
						if (span.getRowSpan() != 1) {
							SpanWrapper<V> nextSpan = getSpanWrapper(
									anchorRow + 1, anchorColumn);
							nextSpan.setRowSpan(span.getRowSpan() - 1);
							nextSpan.setColumnSpan(span.getColumnSpan());
						}
					}
					columnIndex = anchorColumn + span.getColumnSpan() - 1;
				}
			}
		}

		this.table.deleteRowAt(row);
		rebuildAnchors();
	}

	public int getColumnCount() {
		return this.table.getColumnCount();
	}

	public int getRowCount() {
		return this.table.getRowCount();
	}

	public void insertColumnAt(int column) {
		if (!isOutOfColumn(column)) {
			for (int rowIndex = 0; rowIndex < getRowCount(); rowIndex++) {

				if (isCombined(rowIndex, column)) {
					Position anchor = getAnchorPosition(rowIndex, column);
					int anchorRow = anchor.row;
					int anchorColumn = anchor.column;
					SpanWrapper<V> span = getSpanWrapper(anchorRow,
							anchorColumn);
					if (anchorRow != rowIndex || anchorColumn != column) {
						span.setColumnSpan(span.getColumnSpan() + 1);
					}
					rowIndex = anchorRow + span.getRowSpan() - 1;
				}
			}
		}

		this.table.insertColumnAt(column);
		rebuildAnchors();
	}

	public void insertRowAt(int row) {
		if (!isOutOfRow(row)) {
			for (int columnIndex = 0; columnIndex < getColumnCount(); columnIndex++) {

				if (isCombined(row, columnIndex)) {
					Position anchor = getAnchorPosition(row, columnIndex);
					int anchorRow = anchor.row;
					int anchorColumn = anchor.column;
					SpanWrapper<V> span = getSpanWrapper(anchorRow,
							anchorColumn);
					if (anchorRow != row || anchorColumn != columnIndex) {
						span.setRowSpan(span.getRowSpan() + 1);
					}
					columnIndex = anchorColumn + span.getColumnSpan() - 1;
				}
			}
		}

		this.table.insertRowAt(row);
		rebuildAnchors();
	}

	public void combine(int startRow, int startColumn, int endRow, int endColumn) {
		int minRow = Math.min(startRow, endRow);
		int minColumn = Math.min(startColumn, endColumn);

		int maxRow = Math.max(startRow, endRow);
		int maxColumn = Math.max(startColumn, endColumn);

		for (int row = minRow; row <= maxRow; row++) {
			for (int column = minColumn; column <= maxColumn; column++) {
				Position parent = getAnchorPosition(row, column);
				if (minRow > parent.row || parent.row > maxRow
						|| minColumn > parent.column || parent.column > maxColumn) {
					return;
				}
			}
		}

		SpanWrapper<V> anchorSpan = getSpanWrapper(minRow, minColumn);
		anchorSpan.setColumnSpan(maxColumn - minColumn + 1);
		anchorSpan.setRowSpan(maxRow - minRow + 1);
		setSpanWrapper(minRow, minColumn, anchorSpan);

		buildAnchor(minRow, minColumn);
	}

	public boolean isCombined(int row, int column) {
		return getSpanWrapper(row, column).isCombined();
	}

	public int getRowSpan(int row, int column) {
		SpanWrapper<V> span = getSpanWrapper(row, column);
		return span.getRowSpan();
	}

	public int getColumnSpan(int row, int column) {
		SpanWrapper<V> span = getSpanWrapper(row, column);
		return span.getColumnSpan();
	}

	public void setValueAt(int row, int column, V cell) {
		SpanWrapper<V> span = getSpanWrapper(row, column);
		span.setSource(cell);
		setSpanWrapper(row, column, span);
	}

	private void setSpanWrapper(int row, int column, SpanWrapper<V> cell) {
		this.table.setValueAt(row, column, cell);
	}

	public V getValue(int row, int column) {
		return getSpanWrapper(row, column).getSource();
	}

	private SpanWrapper<V> getSpanWrapper(int row, int column) {
		SpanWrapper<V> span = this.table.getValue(row, column);
		if (span == null) {
			span = new SpanWrapper<V>(null, 1, 1);
		}
		return span;
	}

	public int getAnchorColumn(int row, int column) {
		return getAnchorPosition(row, column).column;
	}

	public int getAnchorRow(int row, int column) {
		return getAnchorPosition(row, column).row;
	}

	private Position getAnchorPosition(int row, int column) {
		Position anchor = this.anchorMap.get(new Position(row, column));
		if (anchor == null) {
			anchor = new Position(row, column);
		}
		return anchor;
	}

	private void rebuildAnchors() {
		this.anchorMap.clear();
		for (int row = 0; row < getRowCount(); row++) {
			for (int column = 0; column < getColumnCount(); column++) {
				SpanWrapper<V> span = getSpanWrapper(row, column);
				if (span.getColumnSpan() > 1 && span.getRowSpan() > 1) {
					buildAnchor(row, column);
				}
			}
		}
	}

	private void buildAnchor(int anchorRow, int anchorColumn) {
		SpanWrapper<V> anchorSpan = getSpanWrapper(anchorRow, anchorColumn);
		int columnSpan = anchorSpan.getColumnSpan();
		int rowSpan = anchorSpan.getRowSpan();
		for (int rowOffset = 0; rowOffset < rowSpan; rowOffset++) {
			for (int columnOffset = 0; columnOffset < columnSpan; columnOffset++) {
				int rowIndex = anchorRow + rowOffset;
				int columnIndex = anchorColumn + columnOffset;
				if (rowIndex == anchorRow && columnIndex == anchorColumn) {
					// ê�����
					continue;
				}

				SpanWrapper<V> child = getSpanWrapper(rowIndex, columnIndex);
				child.setColumnSpan(-1);
				child.setRowSpan(-1);

				setSpanWrapper(rowIndex, columnIndex, child);
				this.anchorMap.put(new Position(rowIndex, columnIndex),
						new Position(anchorRow, anchorColumn));
			}
		}
	}
}
