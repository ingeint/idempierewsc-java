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

import org.idempiere.webservice.client.base.Enums.WebServiceMethod;

/**
 * Operation For composite operation
 */
public class Operation {

	private WebServiceRequest webService;
	private boolean preCommit;
	private boolean postCommit;

	/**
	 * Default constructor
	 */
	public Operation() {
		this(false, false);
	}

	/**
	 * Web service operation
	 * 
	 * @param webService
	 *            Inner web service
	 */
	public Operation(WebServiceRequest webService) {
		this(webService, false, false);
	}

	/**
	 * Web service operation
	 * 
	 * @param preCommit
	 *            Pre Commit Option
	 * @param postCommit
	 *            Post Commit Option
	 */
	public Operation(boolean preCommit, boolean postCommit) {
		this(null, preCommit, postCommit);
	}

	/**
	 * Web service operation
	 * 
	 * @param preCommit
	 *            Pre Commit Option
	 * @param postCommit
	 *            Post Commit Option
	 * @param webService
	 *            Inner web service
	 */
	public Operation(WebServiceRequest webService, boolean preCommit, boolean postCommit) {
		this.preCommit = preCommit;
		this.postCommit = postCommit;
		setWebService(webService);
	}

	/**
	 * Gets the webService
	 * 
	 * @return The webService
	 */
	public WebServiceRequest getWebService() {
		return webService;
	}

	/**
	 * Sets the webService
	 * 
	 * @param webService
	 *            The webService to set
	 */
	public void setWebService(WebServiceRequest webService) {
		if (webService != null)
			if (webService.getWebServiceMethod() == WebServiceMethod.getList || webService.getWebServiceMethod() == WebServiceMethod.queryData || webService.getWebServiceMethod() == WebServiceMethod.compositeOperation)
				throw new IllegalArgumentException(String.format("WebService %s not allowed for Composite Operation", webService.getWebServiceMethod()));

		this.webService = webService;
	}

	/**
	 * Gets the preCommit
	 * 
	 * @return The preCommit
	 */
	public boolean isPreCommit() {
		return preCommit;
	}

	/**
	 * Sets the preCommit. If preCommit is true, whatever done before current operation will be committed to the database
	 * 
	 * @param preCommit
	 *            The preCommit to set
	 */
	public void setPreCommit(boolean preCommit) {
		this.preCommit = preCommit;
	}

	/**
	 * Gets the postCommit
	 * 
	 * @return The postCommit
	 */
	public boolean isPostCommit() {
		return postCommit;
	}

	/**
	 * Sets the postCommit. When postCommit is true, commit is performed after current operation is executed successfully
	 * 
	 * @param postCommit
	 *            The postCommit to set
	 */
	public void setPostCommit(boolean postCommit) {
		this.postCommit = postCommit;
	}

}
