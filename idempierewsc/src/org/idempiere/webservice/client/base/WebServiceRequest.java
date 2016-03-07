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

import org.idempiere.webservice.client.base.Enums.WebServiceDefinition;
import org.idempiere.webservice.client.base.Enums.WebServiceMethod;
import org.idempiere.webservice.client.base.Enums.WebServiceRequestModel;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseModel;

/**
 * WebServiceRequest
 */
public abstract class WebServiceRequest {

	private LoginRequest login;
	private String webServiceType;

	public WebServiceRequest() {
		this.login = new LoginRequest();
	}

	/**
	 * Gets the login
	 * 
	 * @return The login
	 */
	public LoginRequest getLogin() {
		return login;
	}

	/**
	 * Sets the login
	 * 
	 * @param login
	 *            The login to set
	 */
	public void setLogin(LoginRequest login) {
		this.login = login;
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

	/**
	 * Web Service Method
	 * 
	 * @returnWeb Service Method
	 */
	public abstract WebServiceMethod getWebServiceMethod();

	/**
	 * Web Service Definition
	 * 
	 * @return Web Service Definition
	 */
	public abstract WebServiceDefinition getWebServiceDefinition();

	/**
	 * Web Service Request Type
	 * 
	 * @return Web Service Request Type
	 */
	public abstract WebServiceRequestModel getWebServiceRequestModel();

	/**
	 * Web Service Response Type
	 * 
	 * @return Web Service Response Type
	 */
	public abstract WebServiceResponseModel getWebServiceResponseModel();

}
