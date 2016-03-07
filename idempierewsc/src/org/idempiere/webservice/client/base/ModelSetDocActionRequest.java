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

import org.idempiere.webservice.client.base.Enums.DocAction;
import org.idempiere.webservice.client.base.Enums.WebServiceRequestModel;

/**
 * ModelSetDocActionRequest. Web Service Request
 */
public abstract class ModelSetDocActionRequest extends WebServiceRequest {

	private String tableName;
	private Integer recordID;
	private String recordIDVariable;
	private DocAction docAction;

	/**
	 * Gets the table name
	 * 
	 * @return The table name
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * Sets the table name
	 * 
	 * @param tableName
	 *            The table name to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * Gets the record ID
	 * 
	 * @return The record ID
	 */
	public Integer getRecordID() {
		return recordID;
	}

	/**
	 * Sets the record ID
	 * 
	 * @param recordID
	 *            The record ID to set
	 */
	public void setRecordID(Integer recordID) {
		this.recordID = recordID;
	}

	/**
	 * Gets the record ID Variable. For composite operation
	 * 
	 * @return The record ID Variable
	 */
	public String getRecordIDVariable() {
		return recordIDVariable;
	}

	/**
	 * Sets the record ID Variable. For composite operation
	 * 
	 * @param recordIDVariable
	 *            The record ID Variable to set
	 */
	public void setRecordIDVariable(String recordIDVariable) {
		this.recordIDVariable = recordIDVariable;
	}

	/**
	 * Gets the docAction
	 * 
	 * @return The docAction
	 */
	public DocAction getDocAction() {
		return docAction;
	}

	/**
	 * Sets the docAction
	 * 
	 * @param docAction
	 *            The docAction to set
	 */
	public void setDocAction(DocAction docAction) {
		this.docAction = docAction;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.idempiere.webservice.client.base.WebServiceRequest#getWebServiceRequestModel()
	 */
	@Override
	public WebServiceRequestModel getWebServiceRequestModel() {
		return WebServiceRequestModel.ModelSetDocActionRequest;
	}
}
