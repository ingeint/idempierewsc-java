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

import org.idempiere.webservice.client.base.Enums.FieldsContainerType;

/**
 * WebServiceFieldsContainer For field collections
 */
public abstract class FieldsContainer {

	private List<Field> fields;

	/**
	 * Default constructor
	 */
	public FieldsContainer() {
		fields = new ArrayList<Field>();
	}

	/**
	 * Get all field
	 * 
	 * @returns List fields
	 */
	public List<Field> getFields() {
		List<Field> temp = new ArrayList<Field>();
		temp.addAll(fields);
		return temp;
	}

	/**
	 * Removes the field
	 * 
	 * @param field
	 */
	public void removeField(Field field) {
		fields.remove(field);
	}

	/**
	 * Removes the field
	 * 
	 * @param pos
	 *            Position
	 * @return The field
	 */
	public Field removeField(int pos) {
		return fields.remove(pos);
	}

	/**
	 * Removes the field
	 * 
	 * @param columnName
	 *            Column name
	 * @return The field
	 */
	public Field removeField(String columnName) {
		Field returnField = getField(columnName);
		fields.remove(returnField);
		return returnField;
	}

	/**
	 * Adds the field
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Value
	 */
	public void addField(String columnName, Object value) {
		addField(new Field(columnName, value));
	}

	/**
	 * Add a new field
	 * 
	 * @param field
	 *            New Field
	 */
	public void addField(Field field) {
		Field findField = getField(field.getColumn());
		if (findField != null)
			fields.remove(findField);
		fields.add(field);
	}

	/**
	 * Get the count fields
	 * 
	 * @return Count
	 */
	public int getFieldsCount() {
		return fields.size();
	}

	/**
	 * Find a field from colum name value
	 * 
	 * @param columnName
	 *            Key for column name
	 * @return Field
	 */
	public Field getField(String columnName) {
		for (int i = 0; i < fields.size(); i++) {
			if (fields.get(i).getColumn().equals(columnName)) {
				return fields.get(i);
			}
		}
		return null;
	}

	/**
	 * Gets the field
	 * 
	 * @param pos
	 *            Position
	 * @return The field
	 */
	public Field getField(int pos) {
		return fields.get(pos);
	}

	/**
	 * Clear this instance
	 */
	public void clear() {
		fields.clear();
	}

	/**
	 * Get the node root name
	 * 
	 * @return Fields Container Type
	 */
	public abstract FieldsContainerType getWebServiceFieldsContainerType();

}
