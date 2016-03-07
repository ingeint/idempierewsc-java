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

package org.idempiere.webservice.client.exceptions;

/**
 * WebService Timeout Exception
 */
public class WebServiceTimeoutException extends WebServiceException {

	private static final long serialVersionUID = -3429134033196006001L;

	public WebServiceTimeoutException() {
	}

	public WebServiceTimeoutException(String message) {
		super(message);
	}

	public WebServiceTimeoutException(Throwable cause) {
		super(cause);
	}

	public WebServiceTimeoutException(String message, Throwable cause) {
		super(message, cause);
	}

}
