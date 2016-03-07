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

import org.idempiere.webservice.client.base.Enums.ModelCRUDAction;
import org.idempiere.webservice.client.base.Enums.WebServiceRequestModel;


/**
 * ModelCRUDRequest. Web Service Request
 */
public abstract class ModelCRUDRequest extends WebServiceRequest {

	private DataRow dataRow;
	private Integer offset;
	private Integer limit;
	private String filter;
	private ModelCRUDAction action;
	private Integer recordID;
	private String recordIDVariable;
	private String tableName;

	/**
	 * Default constructor
	 */
	protected ModelCRUDRequest() {
		dataRow = new DataRow();
	}

	/**
	 * Gets the dataRow
	 * 
	 * @return The dataRow
	 */
	public DataRow getDataRow() {
		return dataRow;
	}

	/**
	 * Sets the dataRow
	 * 
	 * @param dataRow
	 *            The dataRow to set
	 */
	public void setDataRow(DataRow dataRow) {
		this.dataRow = dataRow;
	}

	/**
	 * Gets the offset
	 * 
	 * @return The offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * Sets the offset
	 * 
	 * @param offset
	 *            The offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * Gets the limit
	 * 
	 * @return The limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * Sets the limit
	 * 
	 * @param limit
	 *            The limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * Gets the filter
	 * 
	 * @return The filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * Sets the filter
	 * 
	 * @param filter
	 *            The filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}

	/**
	 * Gets the action
	 * 
	 * @return The action
	 */
	public ModelCRUDAction getAction() {
		return action;
	}

	/**
	 * Sets the action
	 * 
	 * @param action
	 *            The action to set
	 */
	public void setAction(ModelCRUDAction action) {
		this.action = action;
	}

	/**
	 * Gets the recordID
	 * 
	 * @return The recordID
	 */
	public Integer getRecordID() {
		return recordID;
	}

	/**
	 * Sets the recordID
	 * 
	 * @param recordID
	 *            The recordID to set
	 */
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}

	/**
	 * Gets the recordIDVariable
	 * 
	 * @return The recordIDVariable
	 */
	public String getRecordIDVariable() {
		return recordIDVariable;
	}

	/**
	 * Sets the recordIDVariable
	 * 
	 * @param recordIDVariable
	 *            The recordIDVariable to set
	 */
	public void setRecordIDVariable(String recordIDVariable) {
		this.recordIDVariable = recordIDVariable;
	}

	/**
	 * Gets the tableName
	 * 
	 * @return The tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the tableName
	 * 
	 * @param tableName
	 *            The tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.idempiere.webservice.client.base.WebServiceRequest#getWebServiceRequestModel()
	 */
	@Override
	public WebServiceRequestModel getWebServiceRequestModel() {
		return WebServiceRequestModel.ModelCRUDRequest;
	}

}
