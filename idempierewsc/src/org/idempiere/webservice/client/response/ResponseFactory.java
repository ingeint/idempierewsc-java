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

import org.idempiere.webservice.client.base.DataRow;
import org.idempiere.webservice.client.base.DataSet;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseModel;
import org.idempiere.webservice.client.base.Field;
import org.idempiere.webservice.client.base.WebServiceResponse;
import org.idempiere.webservice.client.exceptions.ResponseFactoryException;
import org.idempiere.webservice.client.util.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * ResponseFactory. Class for build reponses
 */
public class ResponseFactory {

	/**
	 * Build a response model from xml response
	 * 
	 * @param responseModel
	 *            Object type
	 * @param responseXml
	 *            Xml response
	 * @return Response model
	 * @throws ResponseFactoryException
	 */
	public static WebServiceResponse createResponse(WebServiceResponseModel responseModel, Document responseXml) throws ResponseFactoryException {
		if (responseModel == WebServiceResponseModel.CompositeResponse)
			return createCompositeResponse(responseXml);
		else if (responseModel == WebServiceResponseModel.RunProcessResponse)
			return createRunProcessResponse(responseXml);
		else if (responseModel == WebServiceResponseModel.StandardResponse)
			return createStandardResponse(responseXml);
		else if (responseModel == WebServiceResponseModel.WindowTabDataResponse)
			return createWindowTabDataResponse(responseXml);
		return null;
	}

	/**
	 * Processes the error
	 * @param responseModel
	 *            Response
	 * @param response
	 *            Response document
	 * 
	 * @return true if error
	 */
	private static boolean hasFaultError(WebServiceResponse responseModel, Document response) {
		NodeList xmlFault = response.getElementsByTagName("faultstring");
		if (xmlFault.getLength() > 0) {
			responseModel.setStatus(WebServiceResponseStatus.Error);
			responseModel.setErrorMessage(xmlFault.item(0).getTextContent());
			return true;
		}
		return false;
	}

	/**
	 * Create a composite response model
	 * 
	 * @param response
	 *            Xml response
	 * @return Response model
	 * @throws ResponseFactoryException
	 */
	public static CompositeResponse createCompositeResponse(Document response) throws ResponseFactoryException {
		try {
			CompositeResponse responseModel = new CompositeResponse();

			if (hasFaultError(responseModel, response)) {
				return responseModel;
			}

			responseModel.setStatus(WebServiceResponseStatus.Successful);

			NodeList xmlResponses = response.getElementsByTagName("StandardResponse");

			for (int i = 0; i < xmlResponses.getLength(); i++) {
				Element xmlTemp = (Element) xmlResponses.item(i);
				WebServiceResponse partialResponse = null;

				if (xmlTemp.getElementsByTagName("WindowTabData").getLength() > 0) {
					Document xmlDocTemp = XMLUtil.newDocument();
					xmlDocTemp.appendChild(xmlDocTemp.importNode(xmlTemp.getElementsByTagName("WindowTabData").item(0), true));
					partialResponse = createWindowTabDataResponse(xmlDocTemp);
					responseModel.addResponse(partialResponse);
				} else if (xmlTemp.getElementsByTagName("RunProcessResponse").getLength() > 0) {
					Document xmlDocTemp = XMLUtil.newDocument();
					xmlDocTemp.appendChild(xmlDocTemp.importNode(xmlTemp.getElementsByTagName("RunProcessResponse").item(0), true));
					partialResponse = createRunProcessResponse(xmlDocTemp);
					responseModel.addResponse(partialResponse);
				} else {
					Document xmlDocTemp = XMLUtil.newDocument();
					xmlDocTemp.appendChild(xmlDocTemp.importNode(xmlTemp, true));
					partialResponse = createStandardResponse(xmlDocTemp);
					responseModel.addResponse(partialResponse);
				}

				if (partialResponse != null && partialResponse.getStatus().equals(WebServiceResponseStatus.Error)) {
					responseModel.setStatus(WebServiceResponseStatus.Error);
					responseModel.setErrorMessage(partialResponse.getErrorMessage());
				}
			}

			return responseModel;
		} catch (ResponseFactoryException e) {
			throw e;
		} catch (Exception e) {
			throw new ResponseFactoryException("Error building CompositeResponse", e);
		}
	}

	/**
	 * Create a run process response model
	 * 
	 * @param response
	 *            Xml response
	 * @return Response model
	 * @throws ResponseFactoryException
	 */
	public static RunProcessResponse createRunProcessResponse(Document response) throws ResponseFactoryException {
		try {
			RunProcessResponse responseModel = new RunProcessResponse();

			if (hasFaultError(responseModel, response)) {
				return responseModel;
			}

			NodeList xmlProcess = response.getElementsByTagName("RunProcessResponse");
			boolean isReport = false;
			
			
			if (xmlProcess.getLength() > 0) {
				if (xmlProcess.item(0) != null && 
						xmlProcess.item(0).getAttributes() != null &&
						xmlProcess.item(0).getAttributes().getNamedItem("IsReport") != null &&
						Boolean.parseBoolean(xmlProcess.item(0).getAttributes().getNamedItem("IsReport").getNodeValue())) {
					isReport = true;
				} else if (Boolean.parseBoolean(xmlProcess.item(0).getAttributes().getNamedItem("IsError").getNodeValue())) {
					responseModel.setStatus(WebServiceResponseStatus.Error);
					NodeList xmlError = response.getElementsByTagName("Error");
					responseModel.setErrorMessage(xmlError.item(0).getTextContent());
					return responseModel;
				}
			}

			responseModel.setStatus(WebServiceResponseStatus.Successful);

			if (isReport) {
				NodeList xmlData = response.getElementsByTagName("Data");
				responseModel.setSummary(xmlData.item(0).getTextContent());
				responseModel.setReportFormat(xmlProcess.item(0).getAttributes().getNamedItem("ReportFormat").getNodeValue());
				
			} else {
				NodeList xmlSummary = response.getElementsByTagName("Summary");
				responseModel.setSummary(xmlSummary.item(0).getTextContent());

				NodeList xmlLogInfo = response.getElementsByTagName("LogInfo");
				responseModel.setLogInfo(xmlLogInfo.item(0).getTextContent());				
			}

			return responseModel;
		} catch (Exception e) {
			throw new ResponseFactoryException("Error building RunProcessResponse", e);
		}
	}

	/**
	 * Create a standard response model
	 * 
	 * @param response
	 *            Xml response
	 * @return Response model
	 * @throws ResponseFactoryException
	 */
	public static StandardResponse createStandardResponse(Document response) throws ResponseFactoryException {
		try {
			StandardResponse responseModel = new StandardResponse();

			if (hasFaultError(responseModel, response)) {
				return responseModel;
			}

			NodeList xmlError = response.getElementsByTagName("Error");
			if (xmlError.getLength() > 0) {
				responseModel.setStatus(WebServiceResponseStatus.Error);
				responseModel.setErrorMessage(xmlError.item(0).getTextContent());
				return responseModel;
			}

			responseModel.setStatus(WebServiceResponseStatus.Successful);

			NodeList xmlStandard = response.getElementsByTagName("StandardResponse");
			if (xmlStandard.getLength() > 0) {

				Node nodeRecordID = xmlStandard.item(0).getAttributes().getNamedItem("RecordID");
				if (nodeRecordID != null) {
					String recordIDString = nodeRecordID.getNodeValue().trim();

					if (!recordIDString.isEmpty())
						responseModel.setRecordID(Integer.parseInt(recordIDString));
				}

			}

			DataRow dataRow = new DataRow();
			responseModel.setOutputFields(dataRow);

			NodeList xmlDataFields = response.getElementsByTagName("outputField");

			for (int j = 0; j < xmlDataFields.getLength(); j++) {
				Field field = new Field();
				dataRow.addField(field);

				Element xmlDataField = (Element) xmlDataFields.item(j);
				if (xmlDataField.getAttributes().getNamedItem("column") != null)
					field.setColumn(xmlDataField.getAttributes().getNamedItem("column").getNodeValue());

				if (xmlDataField.getAttributes().getNamedItem("value") != null)
					field.setValue(xmlDataField.getAttributes().getNamedItem("value").getNodeValue());
			}

			return responseModel;
		} catch (Exception e) {
			throw new ResponseFactoryException("Error building StandardResponse", e);
		}
	}

	/**
	 * Create a tab data response model
	 * 
	 * @param response
	 *            Xml response
	 * @return Response model
	 * @throws ResponseFactoryException
	 */
	public static WindowTabDataResponse createWindowTabDataResponse(Document response) throws ResponseFactoryException {
		try {
			WindowTabDataResponse responseModel = new WindowTabDataResponse();

			if (hasFaultError(responseModel, response)) {
				return responseModel;
			}

			NodeList xmlError = response.getElementsByTagName("Error");
			if (xmlError.getLength() > 0) {
				responseModel.setStatus(WebServiceResponseStatus.Error);
				responseModel.setErrorMessage(xmlError.item(0).getTextContent());
				return responseModel;
			}

			NodeList xmlSuccess = response.getElementsByTagName("Success");
			if (xmlSuccess.getLength() > 0) {
				if (!Boolean.parseBoolean(xmlSuccess.item(0).getTextContent())) {
					responseModel.setStatus(WebServiceResponseStatus.Unsuccessful);
					return responseModel;
				}
			}

			responseModel.setStatus(WebServiceResponseStatus.Successful);

			NodeList xmlWindowTabData = response.getElementsByTagName("WindowTabData");

			if (xmlWindowTabData.getLength() > 0) {

				Node nodeNumRows = xmlWindowTabData.item(0).getAttributes().getNamedItem("NumRows");
				if (nodeNumRows != null) {
					String numRowsString = nodeNumRows.getNodeValue().trim();

					if (!numRowsString.isEmpty())
						responseModel.setNumRows(Integer.parseInt(numRowsString));
				}

				Node nodeTotalRows = xmlWindowTabData.item(0).getAttributes().getNamedItem("TotalRows");
				if (nodeTotalRows != null) {
					String totalRowsString = nodeTotalRows.getNodeValue().trim();

					if (!totalRowsString.isEmpty())
						responseModel.setTotalRows(Integer.parseInt(totalRowsString));
				}

				Node nodeStartRow = xmlWindowTabData.item(0).getAttributes().getNamedItem("StartRow");
				if (nodeStartRow != null) {
					String startRowsString = nodeStartRow.getNodeValue().trim();

					if (!startRowsString.isEmpty())
						responseModel.setStartRow(Integer.parseInt(startRowsString));
				}

			}

			DataSet dataSet = new DataSet();

			responseModel.setDataSet(dataSet);

			NodeList xmlDataSet = response.getElementsByTagName("DataRow");

			for (int i = 0; i < xmlDataSet.getLength(); i++) {

				DataRow dataRow = new DataRow();
				dataSet.addRow(dataRow);

				Element xmlDataRow = (Element) xmlDataSet.item(i);

				NodeList xmlDataFields = xmlDataRow.getElementsByTagName("field");

				for (int j = 0; j < xmlDataFields.getLength(); j++) {
					Field field = new Field();
					dataRow.addField(field);

					Element xmlDataField = (Element) xmlDataFields.item(j);
					field.setColumn(xmlDataField.getAttributes().getNamedItem("column").getNodeValue());

					if (xmlDataField.getElementsByTagName("val").item(0).getTextContent() == null)
						field.setValue("");
					else
						field.setValue(xmlDataField.getElementsByTagName("val").item(0).getTextContent());
				}
			}

			return responseModel;
		} catch (Exception e) {
			throw new ResponseFactoryException("Error building WindowTabData", e);
		}
	}

}
