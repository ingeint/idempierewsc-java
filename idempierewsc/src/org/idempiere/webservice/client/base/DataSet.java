/**
 * Copyright (c) 2016 Saúl Piña <sauljabin@gmail.com>.
 * 
 * This file is part of idempierewsc.
 * 
 * idempierewsc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * idempierewsc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with idempierewsc.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.idempiere.webservice.client.base;

import java.util.ArrayList;
import java.util.List;

/**
 * DataSet, DataRow Container
 */
public class DataSet {

	private List<DataRow> rows;

	/**
	 * Default constructor
	 */
	public DataSet() {
		rows = new ArrayList<DataRow>();
	}

	/**
	 * Get all rows
	 * 
	 * @returns List rows
	 */
	public List<DataRow> getRows() {
		List<DataRow> temp = new ArrayList<DataRow>();
		temp.addAll(rows);
		return temp;
	}

	/**
	 * Add a new row
	 * 
	 * @param row
	 *            Row
	 */
	public void addRow(DataRow row) {
		rows.add(row);
	}

	/**
	 * Remove a row
	 * 
	 * @param row
	 *            Row
	 */
	public void removeRow(DataRow row) {
		rows.remove(row);
	}

	/**
	 * Removes the row
	 * 
	 * @param pos
	 *            Position
	 * @return The row
	 */
	public DataRow removeRow(int pos) {
		DataRow row = rows.get(pos);
		removeRow(row);
		return row;
	}

	/**
	 * Gets the row
	 * 
	 * @param pos
	 *            Position
	 * @return The row
	 */
	public DataRow getRow(int pos) {
		return rows.get(pos);
	}

	/**
	 * Get the count Rows
	 * 
	 * @return Count
	 */
	public int getRowsCount() {
		return rows.size();
	}

	/**
	 * Clear this instance
	 */
	public void clear() {
		rows.clear();
	}
}
