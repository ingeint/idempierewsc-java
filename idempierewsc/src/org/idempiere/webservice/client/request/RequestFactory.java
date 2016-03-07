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

package org.idempiere.webservice.client.request;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.idempiere.webservice.client.base.Enums.DocAction;
import org.idempiere.webservice.client.base.Enums.DocStatus;
import org.idempiere.webservice.client.base.Enums.WebServiceRequestModel;
import org.idempiere.webservice.client.base.CompositeRequest;
import org.idempiere.webservice.client.base.Field;
import org.idempiere.webservice.client.base.FieldsContainer;
import org.idempiere.webservice.client.base.ModelCRUDRequest;
import org.idempiere.webservice.client.base.ModelGetListRequest;
import org.idempiere.webservice.client.base.ModelRunProcessRequest;
import org.idempiere.webservice.client.base.ModelSetDocActionRequest;
import org.idempiere.webservice.client.base.LoginRequest;
import org.idempiere.webservice.client.base.Operation;
import org.idempiere.webservice.client.base.WebServiceRequest;
import org.idempiere.webservice.client.exceptions.RequestFactoryException;
import org.idempiere.webservice.client.util.Base64Util;
import org.idempiere.webservice.client.util.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * RequestFactory. Class for build de Web Service Xml Document
 */
public class RequestFactory {

	public static final String prefix_0 = "_0";
	public static final String namespace_0 = "http://idempiere.org/ADInterface/1_0";
	public static final String prefixSoapenv = "soapenv";
	public static final String namespaceSoapenv = "http://schemas.xmlsoap.org/soap/envelope/";
	public static final String attributeXmlns = "xmlns";
	public static final String namespaceXmlns = "http://www.w3.org/2000/xmlns/";

	/**
	 * Creates element for _0 attribute
	 * 
	 * @param xmlDocument
	 *            Xml Document base
	 * @param name
	 *            Element Name
	 * @return Element _0
	 */
	public static Element createXmlElement_0(Document xmlDocument, String name) {
		Element element = xmlDocument.createElementNS(namespace_0, name);
		element.setPrefix(prefix_0);
		return element;
	}

	/**
	 * Creates element for _0 attribute
	 * 
	 * @param xmlDocument
	 *            Xml Document base
	 * @param name
	 *            Element Name
	 * @param text
	 *            Inner text
	 * @return Element _0
	 */
	public static Element createXmlElement_0(Document xmlDocument, String name, String text) {
		Element element = xmlDocument.createElementNS(namespace_0, name);
		element.setPrefix(prefix_0);
		element.setTextContent(text);
		return element;
	}

	/**
	 * Creates element for soapenv attribute
	 * 
	 * @param xmlDocument
	 *            Xml Document base
	 * @param name
	 *            Element Name
	 * @return Element soapenv
	 */
	public static Element createXmlElementSoapenv(Document xmlDocument, String name) {
		Element element = xmlDocument.createElementNS(namespaceSoapenv, name);
		element.setPrefix(prefixSoapenv);
		return element;
	}

	/**
	 * Generates full request xml document
	 * 
	 * @param webService
	 *            Web service model
	 * @return Full document for request
	 * @throws RequestFactoryException
	 */
	public static Document createRequest(WebServiceRequest webService) throws RequestFactoryException {
		return buildXmlDocument(webService);
	}

	private static Document buildXmlDocument(WebServiceRequest webService) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();
			doc.setXmlStandalone(true);
			doc.appendChild(doc.importNode(buildXmlEnvelope(webService), true));
			return doc;
		} catch (RequestFactoryException fe) {
			throw fe;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML request", e);
		}
	}

	/**
	 * Generates full request xml document
	 * 
	 * @param webService
	 *            Web service model
	 * @return Full document for request
	 * @throws RequestFactoryException
	 */
	private static Element buildXmlEnvelope(WebServiceRequest webService) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();

			Element envelope = doc.createElementNS(namespaceSoapenv, "Envelope");
			envelope.setPrefix(prefixSoapenv);
			envelope.setAttributeNS(namespaceXmlns, attributeXmlns + ":" + prefix_0, namespace_0);

			Element nodeHeader = createXmlElementSoapenv(doc, "Header");
			Element nodeBody = createXmlElementSoapenv(doc, "Body");
			Element nodeRequest = createXmlElement_0(doc, webService.getWebServiceMethod().toString());

			envelope.appendChild(nodeHeader);
			envelope.appendChild(nodeBody);

			nodeBody.appendChild(nodeRequest);

			nodeRequest.appendChild(doc.importNode(buildXmlRequest(webService), true));

			return envelope;
		} catch (RequestFactoryException fe) {
			throw fe;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML Envelope request", e);
		}
	}

	/**
	 * Generates the xml body for request
	 * 
	 * @param webServiceWeb
	 *            service model
	 * @return Xml for body
	 * @throws RequestFactoryException
	 */
	private static Element buildXmlRequest(WebServiceRequest webService) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();

			Element xmlRequest = createXmlElement_0(doc, webService.getWebServiceRequestModel().toString());

			if (webService.getWebServiceRequestModel() == WebServiceRequestModel.CompositeRequest) {
				Element xmlServiceType = createXmlElement_0(doc, "serviceType", webService.getWebServiceType());
				xmlRequest.appendChild(xmlServiceType);
			}

			xmlRequest.appendChild(doc.importNode(buildXmlModel(webService), true));

			if (webService.getLogin() != null)
				xmlRequest.appendChild(doc.importNode(buildXmlLogin(webService.getLogin()), true));

			return xmlRequest;
		} catch (RequestFactoryException fe) {
			throw fe;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML body request", e);
		}
	}

	/**
	 * Generates xml login
	 * 
	 * @param login
	 *            Login data model
	 * @return Xml for Login section
	 * @throws RequestFactoryException
	 */
	private static Element buildXmlLogin(LoginRequest login) throws RequestFactoryException {
		try {
			Document docLogin = XMLUtil.newDocument();
			Element xmlLogin = createXmlElement_0(docLogin, "ADLoginRequest");
			docLogin.appendChild(xmlLogin);

			if (login.getUser() != null) {
				Element xmlUser = createXmlElement_0(docLogin, "user", login.getUser());
				xmlLogin.appendChild(xmlUser);
			}

			if (login.getPass() != null) {
				Element xmlPass = createXmlElement_0(docLogin, "pass", login.getPass());
				xmlLogin.appendChild(xmlPass);
			}

			if (login.getLang() != null) {
				Element xmlLang = createXmlElement_0(docLogin, "lang", login.getLang().toString());
				xmlLogin.appendChild(xmlLang);
			}

			if (login.getClientID() != null) {
				Element xmlClient = createXmlElement_0(docLogin, "ClientID", login.getClientID().toString());
				xmlLogin.appendChild(xmlClient);
			}

			if (login.getRoleID() != null) {
				Element xmlRole = createXmlElement_0(docLogin, "RoleID", login.getRoleID().toString());
				xmlLogin.appendChild(xmlRole);
			}

			if (login.getOrgID() != null) {
				Element xmlOrg = createXmlElement_0(docLogin, "OrgID", login.getOrgID().toString());
				xmlLogin.appendChild(xmlOrg);
			}

			if (login.getWarehouseID() != null) {
				Element xmlWarehouse = createXmlElement_0(docLogin, "WarehouseID", login.getWarehouseID().toString());
				xmlLogin.appendChild(xmlWarehouse);
			}

			if (login.getStage() != null) {
				Element xmlStage = createXmlElement_0(docLogin, "stage", login.getStage().toString());
				xmlLogin.appendChild(xmlStage);
			}

			return xmlLogin;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML body request", e);
		}
	}

	/**
	 * Generates the xml operation for request
	 * 
	 * @param webService
	 *            Web service model
	 * @return Xml for operation
	 */
	private static Element buildXmlModel(WebServiceRequest webService) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();

			if (webService.getWebServiceRequestModel() == WebServiceRequestModel.CompositeRequest) {
				CompositeRequest request = (CompositeRequest) webService;

				Element xmlModel = createXmlElement_0(doc, "operations");

				for (int i = 0; i < request.getOperations().size(); i++) {
					xmlModel.appendChild(doc.importNode(buildXmlOperation(request.getOperations().get(i)), true));
				}

				return xmlModel;
			} else if (webService.getWebServiceRequestModel() == WebServiceRequestModel.ModelCRUDRequest) {
				ModelCRUDRequest request = (ModelCRUDRequest) webService;

				Element xmlModel = createXmlElement_0(doc, "ModelCRUD");

				Element xmlServiceType = createXmlElement_0(doc, "serviceType", webService.getWebServiceType());
				xmlModel.appendChild(xmlServiceType);

				if (request.getTableName() != null) {
					Element xmlTableName = createXmlElement_0(doc, "TableName", request.getTableName());
					xmlModel.appendChild(xmlTableName);
				}

				if (request.getRecordID() != null) {
					Element xmlRecordID = createXmlElement_0(doc, "RecordID", request.getRecordID().toString());
					xmlModel.appendChild(xmlRecordID);
				}

				if (request.getRecordIDVariable() != null) {
					Element xmlRecordIDVariable = createXmlElement_0(doc, "recordIDVariable", request.getRecordIDVariable());
					xmlModel.appendChild(xmlRecordIDVariable);
				}

				if (request.getAction() != null) {
					Element xmlAction = createXmlElement_0(doc, "Action", request.getAction().toString());
					xmlModel.appendChild(xmlAction);
				}

				if (request.getFilter() != null) {
					Element xmlFilter = createXmlElement_0(doc, "Filter", request.getFilter());
					xmlModel.appendChild(xmlFilter);
				}

				if (request.getLimit() != null) {
					Element xmlLimit = createXmlElement_0(doc, "Limit", request.getLimit().toString());
					xmlModel.appendChild(xmlLimit);
				}

				if (request.getOffset() != null) {
					Element xmlOffset = createXmlElement_0(doc, "Offset", request.getOffset().toString());
					xmlModel.appendChild(xmlOffset);
				}

				if (request.getDataRow() != null && request.getDataRow().getFieldsCount() > 0) {
					xmlModel.appendChild(doc.importNode(buildXmlFieldsContainer(request.getDataRow()), true));
				}

				return xmlModel;
			} else if (webService.getWebServiceRequestModel() == WebServiceRequestModel.ModelGetListRequest) {
				ModelGetListRequest request = (ModelGetListRequest) webService;

				Element xmlModel = createXmlElement_0(doc, "ModelGetList");

				Element xmlServiceType = createXmlElement_0(doc, "serviceType", request.getWebServiceType());
				xmlModel.appendChild(xmlServiceType);

				if (request.getAD_Reference_ID() != null) {
					Element xmlReferenceID = createXmlElement_0(doc, "AD_Reference_ID", request.getAD_Reference_ID().toString());
					xmlModel.appendChild(xmlReferenceID);
				}

				if (request.getFilter() != null) {
					Element xmlFilter = createXmlElement_0(doc, "Filter", request.getFilter());
					xmlModel.appendChild(xmlFilter);
				}

				return xmlModel;
			} else if (webService.getWebServiceRequestModel() == WebServiceRequestModel.ModelRunProcessRequest) {
				ModelRunProcessRequest request = (ModelRunProcessRequest) webService;

				Element xmlModel = createXmlElement_0(doc, "ModelRunProcess");

				Element xmlServiceType = createXmlElement_0(doc, "serviceType", request.getWebServiceType());
				xmlModel.appendChild(xmlServiceType);

				if (request.getAD_Process_ID() != null) {
					xmlModel.setAttribute("AD_Process_ID", request.getAD_Process_ID().toString());
				}

				if (request.getAD_Menu_ID() != null) {
					xmlModel.setAttribute("AD_Menu_ID", request.getAD_Menu_ID().toString());
				}

				if (request.getAD_Record_ID() != null) {
					xmlModel.setAttribute("AD_Record_ID", request.getAD_Record_ID().toString());
				}

				if (request.getDocAction() != null) {
					xmlModel.setAttribute("DocAction", request.getDocAction().getValue());
				}

				if (request.getParamValues() != null && request.getParamValues().getFieldsCount() > 0) {
					xmlModel.appendChild(doc.importNode(buildXmlFieldsContainer(request.getParamValues()), true));
				}

				return xmlModel;
			} else if (webService.getWebServiceRequestModel() == WebServiceRequestModel.ModelSetDocActionRequest) {
				ModelSetDocActionRequest request = (ModelSetDocActionRequest) webService;

				Element xmlModel = createXmlElement_0(doc, "ModelSetDocAction");

				Element xmlServiceType = createXmlElement_0(doc, "serviceType", request.getWebServiceType());
				xmlModel.appendChild(xmlServiceType);

				if (request.getTableName() != null) {
					Element xmlTableName = createXmlElement_0(doc, "tableName", request.getTableName());
					xmlModel.appendChild(xmlTableName);
				}

				if (request.getRecordID() != null) {
					Element xmlRecordID = createXmlElement_0(doc, "recordID", request.getRecordID().toString());
					xmlModel.appendChild(xmlRecordID);
				}

				if (request.getRecordIDVariable() != null) {
					Element xmlRecordIDVariable = createXmlElement_0(doc, "recordIDVariable", request.getRecordIDVariable());
					xmlModel.appendChild(xmlRecordIDVariable);
				}

				if (request.getDocAction() != null) {
					Element xmlDocAction = createXmlElement_0(doc, "docAction", request.getDocAction().getValue());
					xmlModel.appendChild(xmlDocAction);
				}

				return xmlModel;
			}

			return doc.createElement("NoModel");
		} catch (RequestFactoryException fe) {
			throw fe;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML Fields container", e);
		}
	}

	/**
	 * Generates xml for fields
	 * 
	 * @param container
	 *            Fields container
	 * @return ml for fields section
	 * @throws RequestFactoryException
	 */
	private static Element buildXmlFieldsContainer(FieldsContainer container) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();
			Element xmlFields = createXmlElement_0(doc, container.getWebServiceFieldsContainerType().toString());

			for (int i = 0; i < container.getFieldsCount(); i++) {
				xmlFields.appendChild(doc.importNode(buildXmlField(container.getField(i)), true));
			}

			return xmlFields;
		} catch (RequestFactoryException fe) {
			throw fe;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML Fields container", e);
		}
	}

	/**
	 * Generates xml Field
	 * 
	 * @param field
	 *            Field
	 * @return Xml for Field section
	 */
	private static Element buildXmlField(Field field) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();
			Element xmlField = createXmlElement_0(doc, "field");

			if (field.getColumn() != null) {
				xmlField.setAttribute("column", field.getColumn());
			}

			if (field.getType() != null) {
				xmlField.setAttribute("type", field.getType());
			}

			if (field.getLval() != null) {
				xmlField.setAttribute("lval", field.getLval());
			}

			if (field.getDisp() != null) {
				xmlField.setAttribute("disp", field.getDisp().toString().toLowerCase());
			}

			if (field.getEdit() != null) {
				xmlField.setAttribute("edit", field.getEdit().toString().toLowerCase());
			}

			if (field.getError() != null) {
				xmlField.setAttribute("error", field.getError().toString().toLowerCase());
			}

			if (field.getErrorVal() != null) {
				xmlField.setAttribute("errorVal", field.getErrorVal());
			}

			if (field.getValue() != null) {
				String value = "";
				Object objValue = field.getValue();

				if (objValue instanceof Date) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = sdf.format((Date) objValue);
				} else if (objValue instanceof Boolean) {
					value = ((Boolean) objValue) ? "Y" : "N";
				} else if (objValue instanceof DocAction) {
					value = ((DocAction) objValue).getValue();
				} else if (objValue instanceof DocStatus) {
					value = ((DocStatus) objValue).getValue();
				} else if (objValue instanceof byte[]) {
					value = Base64Util.encode((byte[]) objValue);
				} else {
					value = objValue.toString();
				}

				Element xmlVal = createXmlElement_0(doc, "val", value);
				xmlField.appendChild(xmlVal);
			}

			return xmlField;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML Fields container", e);
		}
	}

	/**
	 * Gets the xml for operation in composite interface
	 * 
	 * @param operation
	 *            Operation container
	 * @return Xml operation
	 * @throws RequestFactoryException
	 */
	private static Element buildXmlOperation(Operation operation) throws RequestFactoryException {
		try {
			Document doc = XMLUtil.newDocument();
			Element xmlOperation = createXmlElement_0(doc, "operation");
			xmlOperation.setAttribute("preCommit", Boolean.toString(operation.isPreCommit()).toLowerCase());
			xmlOperation.setAttribute("postCommit", Boolean.toString(operation.isPostCommit()).toLowerCase());

			Element xmlTargetPort = createXmlElement_0(doc, "TargetPort", operation.getWebService().getWebServiceMethod().toString());
			xmlOperation.appendChild(xmlTargetPort);

			xmlOperation.appendChild(doc.importNode(buildXmlModel(operation.getWebService()), true));
			return xmlOperation;
		} catch (RequestFactoryException fe) {
			throw fe;
		} catch (Exception e) {
			throw new RequestFactoryException("Error building XML Fields container", e);
		}
	}

}
