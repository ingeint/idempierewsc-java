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

import java.util.ArrayList;
import java.util.List;

import org.idempiere.webservice.client.base.Enums.WebServiceRequestModel;

/**
 * CompositeRequest. Web Service Request
 */
public abstract class CompositeRequest extends WebServiceRequest {

	private List<Operation> operations;

	protected CompositeRequest() {
		operations = new ArrayList<Operation>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.idempiere.webservice.client.base.WebServiceRequest#getWebServiceRequestModel()
	 */
	@Override
	public WebServiceRequestModel getWebServiceRequestModel() {
		return WebServiceRequestModel.CompositeRequest;
	}

	/**
	 * Add a new Web Service
	 * 
	 * @param operation
	 *            Operation
	 */
	public void addOperation(Operation operation) {
		operations.add(operation);
	}

	/**
	 * Adds the operation
	 * 
	 * @param webService
	 *            Web service
	 */
	public void addOperation(WebServiceRequest webService) {
		addOperation(new Operation(webService));
	}

	/**
	 * Adds the operation
	 * 
	 * @param webService
	 *            Web service
	 * @param preCommit
	 *            If set to <c>true</c> pre commit
	 * @param postCommit
	 *            If set to <c>true</c> post commit
	 */
	public void addOperation(WebServiceRequest webService, boolean preCommit, boolean postCommit) {
		addOperation(new Operation(webService, preCommit, postCommit));
	}

	/**
	 * Remove a Web Service
	 * 
	 * @param operation
	 *            Operation
	 */
	public void removeOperation(Operation operation) {
		operations.remove(operation);
	}

	/**
	 * Removes the operation
	 * 
	 * @param pos
	 *            Position
	 * @return The operation
	 */
	public Operation removeOperation(int pos) {
		Operation operation = operations.get(pos);
		removeOperation(operation);
		return operation;
	}

	/**
	 * Get all field
	 * 
	 * @return List fields
	 */
	public List<Operation> getOperations() {
		List<Operation> temp = new ArrayList<Operation>();
		temp.addAll(operations);
		return temp;
	}

	/**
	 * Gets the operation
	 * 
	 * @param pos
	 *            Position
	 * @return The operation
	 */
	public Operation getOperation(int pos) {
		return operations.get(pos);
	}

	/**
	 * Get the count Operations
	 * 
	 * @return Count
	 */
	public int getOperationsCount() {
		return operations.size();
	}

	/**
	 * Clear this instance
	 */
	public void clear() {
		operations.clear();
	}

}
