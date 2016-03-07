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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.idempiere.webservice.client.base.Enums.DocAction;
import org.idempiere.webservice.client.base.Enums.DocStatus;
import org.idempiere.webservice.client.util.Base64Util;

/**
 * Filed for ModelCRUDRequest
 */
public class Field {

	private Object value;
	private String column;
	private String type;
	private String lval;
	private Boolean disp;
	private Boolean edit;
	private Boolean error;
	private String errorVal;

	/**
	 * Constructor column
	 * 
	 * @param column
	 */
	public Field(String column) {
		this.column = column;
	}

	/**
	 * Constructor column and value
	 * 
	 * @param value
	 * @param column
	 */
	public Field(String column, Object value) {
		this(column);
		this.value = value;
	}

	/**
	 * Default
	 */
	public Field() {
	}

	/**
	 * Gets the value
	 * 
	 * @return The value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value
	 * 
	 * @param value
	 *            The value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets the column
	 * 
	 * @return The column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * Sets the column
	 * 
	 * @param column
	 *            The column to set
	 */
	public void setColumn(String column) {
		this.column = column;
	}

	/**
	 * Gets the type
	 * 
	 * @return The type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type
	 * 
	 * @param type
	 *            The type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the lval
	 * 
	 * @return The lval
	 */
	public String getLval() {
		return lval;
	}

	/**
	 * Sets the lval
	 * 
	 * @param lval
	 *            The lval to set
	 */
	public void setLval(String lval) {
		this.lval = lval;
	}

	/**
	 * Gets the disp
	 * 
	 * @return The disp
	 */
	public Boolean getDisp() {
		return disp;
	}

	/**
	 * Sets the disp
	 * 
	 * @param disp
	 *            The disp to set
	 */
	public void setDisp(Boolean disp) {
		this.disp = disp;
	}

	/**
	 * Gets the edit
	 * 
	 * @return The edit
	 */
	public Boolean getEdit() {
		return edit;
	}

	/**
	 * Sets the edit
	 * 
	 * @param edit
	 *            The edit to set
	 */
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	/**
	 * Gets the error
	 * 
	 * @return The error
	 */
	public Boolean getError() {
		return error;
	}

	/**
	 * Sets the error
	 * 
	 * @param error
	 *            The error to set
	 */
	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * Gets the errorVal
	 * 
	 * @return The errorVal
	 */
	public String getErrorVal() {
		return errorVal;
	}

	/**
	 * Sets the errorVal
	 * 
	 * @param errorVal
	 *            The errorVal to set
	 */
	public void setErrorVal(String errorVal) {
		this.errorVal = errorVal;
	}

	/**
	 * Cast value to String
	 * 
	 * @return String value
	 */
	public String getStringValue() {
		return getValue() == null ? null : getValue().toString();
	}

	/**
	 * Cast value to int
	 * 
	 * @return Int value
	 */
	public Integer getIntValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String)
			return Integer.parseInt(getValue().toString());

		return (Integer) getValue();
	}

	/**
	 * Cast value to byte[]
	 * 
	 * @return Byte array value
	 */
	public byte[] getByteValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String)
			return Base64Util.decode(getValue().toString());

		return (byte[]) getValue();
	}

	/**
	 * Cast value to double
	 * 
	 * @return Double value
	 */
	public Double getDoubleValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String)
			return Double.parseDouble(getValue().toString());

		return (Double) getValue();
	}

	/**
	 * Cast value to float
	 * 
	 * @return Float value
	 */
	public Float getFloatValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String)
			return Float.parseFloat(getValue().toString());

		return (Float) getValue();
	}

	/**
	 * Cast value to Boolean
	 * 
	 * @return Boolean Value
	 */
	public Boolean getBooleanValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String) {
			String value = getValue().toString().toUpperCase();
			
			if(value.equals("Y") || value.equals("YES"))
				value = Boolean.TRUE.toString();
			
			if(value.equals("N") || value.equals("NO"))
				value = Boolean.FALSE.toString();
			
			return Boolean.parseBoolean(value);
		}

		return (Boolean) getValue();
	}

	/**
	 * Cast value to Date
	 * 
	 * @return Date value
	 */
	public Date getDateValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(getValue().toString());
			} catch (ParseException e) {
				throw new ClassCastException("No Valid String: " + getValue().toString());
			}
		}
		return (Date) getValue();
	}

	/**
	 * Cast value to DocStats
	 * 
	 * @return DocStatus value
	 */
	public DocStatus getDocStatusValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String) {
			DocStatus[] values = DocStatus.values();

			for (DocStatus docStatus : values) {
				if (getValue().toString().equals(docStatus.getValue()))
					return docStatus;
			}

			return DocStatus.valueOf(getValue().toString());
		}

		return (DocStatus) getValue();
	}

	/**
	 * Cast value to DocAction
	 * 
	 * @return DocAction value
	 */
	public DocAction getDocActionValue() {
		if (getValue() == null)
			return null;

		if (getValue() instanceof String) {
			DocAction[] values = DocAction.values();

			for (DocAction docAction : values) {
				if (getValue().toString().equals(docAction.getValue()))
					return docAction;
			}

			return DocAction.valueOf(getValue().toString());
		}

		return (DocAction) getValue();
	}

}
