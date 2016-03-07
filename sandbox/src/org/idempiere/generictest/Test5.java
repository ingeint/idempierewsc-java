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

package org.idempiere.generictest;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.idempiere.webservice.client.exceptions.WebServiceException;
import org.idempiere.webservice.client.exceptions.XMLToStringException;
import org.idempiere.webservice.client.exceptions.XMLWriteException;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.util.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Create XML Test
 */
public class Test5 {

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

	public static void main(String[] args) throws ParserConfigurationException, XMLWriteException, XMLToStringException, WebServiceException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		doc.setXmlStandalone(true);

		Element envelope = doc.createElementNS(namespaceSoapenv, "Envelope");
		envelope.setPrefix(prefixSoapenv);
		envelope.setAttributeNS(namespaceXmlns, attributeXmlns + ":" + prefix_0, namespace_0);

		envelope.appendChild(createXmlElementSoapenv(doc, "Header"));

		Element Body = createXmlElementSoapenv(doc, "Body");
		envelope.appendChild(Body);

		Element queryData = createXmlElement_0(doc, "queryData");
		Body.appendChild(queryData);

		Element ModelCRUDRequest = createXmlElement_0(doc, "ModelCRUDRequest");
		queryData.appendChild(ModelCRUDRequest);

		Element ModelCRUD = createXmlElement_0(doc, "ModelCRUD");
		ModelCRUDRequest.appendChild(ModelCRUD);

		Element serviceType = createXmlElement_0(doc, "serviceType");
		serviceType.setTextContent("QueryBPartnerTest");
		ModelCRUD.appendChild(serviceType);

		Element ADLoginRequest = createXmlElement_0(doc, "ADLoginRequest");
		ModelCRUDRequest.appendChild(ADLoginRequest);

		Element user = createXmlElement_0(doc, "user");
		user.setTextContent("SuperUser");
		ADLoginRequest.appendChild(user);

		Element pass = createXmlElement_0(doc, "pass");
		pass.setTextContent("System");
		ADLoginRequest.appendChild(pass);

		Element ClientID = createXmlElement_0(doc, "ClientID");
		ClientID.setTextContent("11");
		ADLoginRequest.appendChild(ClientID);

		Element RoleID = createXmlElement_0(doc, "RoleID", "102");
		ADLoginRequest.appendChild(RoleID);

		doc.appendChild(envelope);

		doc.getDocumentElement().normalize();

		XMLUtil.writeXml(doc, System.out);

		WebServiceConnection conn = new WebServiceConnection();

		conn.setTimeout(30000);
		conn.setAttemptsTimeout(1500);
		conn.setAttempts(20);
		conn.setAppName("AppName/2.0");
		conn.setUrl("http://localhost:8080/ADInterface/services/ModelADService");

		System.out.println();
		System.out.println(XMLUtil.xmlToString(conn.sendRequest(doc), false));
		System.out.println(conn.getTimeRequest());
	}

}
