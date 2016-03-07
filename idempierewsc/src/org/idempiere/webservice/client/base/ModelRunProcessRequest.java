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
 * ModelRunProcessRequest. Web Service Request
 */
public abstract class ModelRunProcessRequest extends WebServiceRequest {

	private ParamValues paramValues;
	private DocAction docAction;
	private Integer AD_Record_ID;
	private Integer AD_Menu_ID;
	private Integer AD_Process_ID;

	/**
	 * Default constructor
	 */
	protected ModelRunProcessRequest() {
		paramValues = new ParamValues();
	}

	/**
	 * Gets the paramValues
	 * 
	 * @return The paramValues
	 */
	public ParamValues getParamValues() {
		return paramValues;
	}

	/**
	 * Sets the paramValues
	 * 
	 * @param paramValues
	 *            The paramValues to set
	 */
	public void setParamValues(ParamValues paramValues) {
		this.paramValues = paramValues;
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

	/**
	 * Gets the AD_Record_ID
	 * 
	 * @return The AD_Record_ID
	 */
	public Integer getAD_Record_ID() {
		return AD_Record_ID;
	}

	/**
	 * Sets the AD_Record_ID
	 * 
	 * @param AD_Record_ID
	 *            The AD_Record_ID to set
	 */
	public void setAD_Record_ID(Integer AD_Record_ID) {
		this.AD_Record_ID = AD_Record_ID;
	}

	/**
	 * Gets the AD_Menu_ID
	 * 
	 * @return The AD_Menu_ID
	 */
	public Integer getAD_Menu_ID() {
		return AD_Menu_ID;
	}

	/**
	 * Sets the AD_Menu_ID
	 * 
	 * @param AD_Menu_ID
	 *            The AD_Menu_ID to set
	 */
	public void setAD_Menu_ID(Integer AD_Menu_ID) {
		this.AD_Menu_ID = AD_Menu_ID;
	}

	/**
	 * Gets the AD_Process_ID
	 * 
	 * @return The AD_Process_ID
	 */
	public Integer getAD_Process_ID() {
		return AD_Process_ID;
	}

	/**
	 * Sets the AD_Process_ID
	 * 
	 * @param AD_Process_ID
	 *            The AD_Process_ID to set
	 */
	public void setAD_Process_ID(Integer AD_Process_ID) {
		this.AD_Process_ID = AD_Process_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.idempiere.webservice.client.base.WebServiceRequest#getWebServiceRequestModel()
	 */
	@Override
	public WebServiceRequestModel getWebServiceRequestModel() {
		return WebServiceRequestModel.ModelRunProcessRequest;
	}

}
