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

package org.idempiere.webservice.client.response;

import org.idempiere.webservice.client.base.Enums.WebServiceResponseModel;
import org.idempiere.webservice.client.base.WebServiceResponse;

/**
 * RunProcessResponse. Response from RunProcess Web Service
 */
public class RunProcessResponse extends WebServiceResponse {

	private String logInfo;
	private String summary;

	/**
	 * Gets the logInfo
	 * 
	 * @return The logInfo
	 */
	public String getLogInfo() {
		return logInfo;
	}

	/**
	 * Sets the logInfo
	 * 
	 * @param logInfo
	 *            The logInfo to set
	 */
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	/**
	 * Gets the summary
	 * 
	 * @return The summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Sets the summary
	 * 
	 * @param summary
	 *            The summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.idempiere.webservice.client.base.WebServiceResponse#getWebServiceResponseModel()
	 */
	@Override
	public WebServiceResponseModel getWebServiceResponseModel() {
		return WebServiceResponseModel.RunProcessResponse;
	}

}
