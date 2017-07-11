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

package org.idempiere.webservice.logic;

import org.idempiere.webservice.client.base.Enums.ErrorType;

/**
 * @author antunesleo
 * 
 * This class implements logic on the WebServiceRespons class
 *
 */

public class WebServiceResponseLogic {
	/**
	 * This method classify the error message
	 * @param errorMessage
	 * @return ErrorType
	 */
	public static ErrorType geErrorType(String errorMessage) {
		if (errorMessage == null || errorMessage.isEmpty()) {
			return ErrorType.EMPTY_ERROR;
		}
		if (errorMessage.substring(0, 9).equals("No Record") && errorMessage.contains("in")) {
			return ErrorType.RECORD_NOT_EXISTS;
		}
		if (errorMessage.equals("Service type") && errorMessage.contains("not configured")) {
			return ErrorType.SERVICE_TYPE_NOT_EXISTS;
		}
		return ErrorType.UNKNOW_ERROR;
	}
}