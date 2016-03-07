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

import org.idempiere.webservice.client.base.DataSet;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseModel;
import org.idempiere.webservice.client.base.WebServiceResponse;

/**
 * WindowTabDataResponse. Response from QueryData, GetList, ReadData Web Services
 */
public class WindowTabDataResponse extends WebServiceResponse {

	private Integer numRows;
	private Integer totalRows;
	private Integer startRow;
	private DataSet dataSet;

	/**
	 * Response from QueryData, GetList, ReadData Web Services
	 */
	public WindowTabDataResponse() {
		dataSet = new DataSet();
	}

	/**
	 * Gets the numRows
	 * 
	 * @return The numRows
	 */
	public Integer getNumRows() {
		return numRows;
	}

	/**
	 * Sets the numRows
	 * 
	 * @param numRows
	 *            The numRows to set
	 */
	public void setNumRows(Integer numRows) {
		this.numRows = numRows;
	}

	/**
	 * Gets the totalRows
	 * 
	 * @return The totalRows
	 */
	public Integer getTotalRows() {
		return totalRows;
	}

	/**
	 * Sets the totalRows
	 * 
	 * @param totalRows
	 *            The totalRows to set
	 */
	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	/**
	 * Gets the startRow
	 * 
	 * @return The startRow
	 */
	public Integer getStartRow() {
		return startRow;
	}

	/**
	 * Sets the startRow
	 * 
	 * @param startRow
	 *            The startRow to set
	 */
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	/**
	 * Gets the dataSet
	 * 
	 * @return The dataSet
	 */
	public DataSet getDataSet() {
		return dataSet;
	}

	/**
	 * Sets the dataSet
	 * 
	 * @param dataSet
	 *            The dataSet to set
	 */
	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.idempiere.webservice.client.base.WebServiceResponse#getWebServiceResponseModel()
	 */
	@Override
	public WebServiceResponseModel getWebServiceResponseModel() {
		return WebServiceResponseModel.WindowTabDataResponse;
	}

}
