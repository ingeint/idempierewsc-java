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

import javax.xml.soap.*;

/**
 * This class test the SOAPConnectionFactory
 */
public class Test2 {

	  public static void main(String args[]) throws Exception {
	        // Create SOAP Connection
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	        
	        // Send SOAP Message to SOAP Server
	        String url = "http://localhost:8080/ADInterface/services/ModelADService";
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), url);

	        // Print SOAP Response
	        System.out.print("Response SOAP Message:");
	        soapResponse.writeTo(System.out);
	        
	        soapConnection.close();
	    }

	    private static SOAPMessage createSOAPRequest() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://idempiere.org/ADInterface/1_0";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("_0", serverURI);
	        SOAPBody soapBody = envelope.getBody();
	        
	        
	        SOAPElement soapBodyElem = soapBody.addChildElement("queryData", "_0");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("ModelCRUDRequest", "_0");
	        SOAPElement soapBodyElem2 = soapBodyElem1.addChildElement("ModelCRUD", "_0");
	        SOAPElement soapBodyElem3 = soapBodyElem2.addChildElement("serviceType", "_0");
	        soapBodyElem3.addTextNode("QueryBPartner");
	        SOAPElement soapBodyElem4 = soapBodyElem1.addChildElement("ADLoginRequest", "_0");
	        SOAPElement soapBodyElem5 = soapBodyElem4.addChildElement("user", "_0");
	        soapBodyElem5.addTextNode("SuperUser");
	        SOAPElement soapBodyElem6 = soapBodyElem4.addChildElement("pass", "_0");
	        soapBodyElem6.addTextNode("System");
	        SOAPElement soapBodyElem7 = soapBodyElem4.addChildElement("ClientID", "_0");
	        soapBodyElem7.addTextNode("11");
	        SOAPElement soapBodyElem8 = soapBodyElem4.addChildElement("RoleID", "_0");
	        soapBodyElem8.addTextNode("102");

	        soapMessage.saveChanges();

	        // Print the request message 
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }

}
