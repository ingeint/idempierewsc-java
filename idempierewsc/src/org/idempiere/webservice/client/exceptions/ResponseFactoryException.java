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
 * Response Factory Exception
 */
public class ResponseFactoryException extends Exception {

	private static final long serialVersionUID = -2637340666807002546L;

	public ResponseFactoryException() {
	}

	public ResponseFactoryException(String message) {
		super(message);
	}

	public ResponseFactoryException(Throwable cause) {
		super(cause);
	}

	public ResponseFactoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
