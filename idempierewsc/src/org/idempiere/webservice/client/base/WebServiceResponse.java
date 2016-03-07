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

import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseModel;

/**
 * Class to abstract the iDempiere response
 */
public abstract class WebServiceResponse {

	private WebServiceResponseStatus status;
	private String errorMessage;
	private String webServiceType;

	/**
	 * Gets the web service response type
	 * 
	 * @return WebServiceResponseModel
	 */
	public abstract WebServiceResponseModel getWebServiceResponseModel();

	/**
	 * Gets the status
	 * 
	 * @return The status
	 */
	public WebServiceResponseStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status
	 * 
	 * @param status
	 *            The status to set
	 */
	public void setStatus(WebServiceResponseStatus status) {
		this.status = status;
	}

	/**
	 * Gets the errorMessage
	 * 
	 * @return The errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the errorMessage
	 * 
	 * @param errorMessage
	 *            The errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets the Web Service Type Name. Table: WS_WebServiceType
	 * 
	 * @return The Web Service Type Name
	 */
	public String getWebServiceType() {
		return webServiceType;
	}

	/**
	 * Sets the Web Service Type Name. Table: WS_WebServiceType
	 * 
	 * @param webServiceType
	 *            The Web Service Type Name to set
	 */
	public void setWebServiceType(String webServiceType) {
		this.webServiceType = webServiceType;
	}

}
