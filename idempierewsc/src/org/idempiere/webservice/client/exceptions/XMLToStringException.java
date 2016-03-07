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
 * XML ToString Exception, when convert XML to string
 */
public class XMLToStringException extends Exception {

	private static final long serialVersionUID = -2045661026734977206L;

	public XMLToStringException() {
	}

	public XMLToStringException(String message) {
		super(message);
	}

	public XMLToStringException(Throwable cause) {
		super(cause);
	}

	public XMLToStringException(String message, Throwable cause) {
		super(message, cause);
	}

}
