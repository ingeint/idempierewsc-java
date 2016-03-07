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

package org.idempiere.webservice.client.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.idempiere.webservice.client.exceptions.XMLParseException;
import org.idempiere.webservice.client.exceptions.XMLDocumentException;
import org.idempiere.webservice.client.exceptions.XMLToStringException;
import org.idempiere.webservice.client.exceptions.XMLWriteException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 * XMLUtil
 */
public class XMLUtil {

	public static final String CHARSET_UTF8 = "UTF-8";

	/**
	 * Convert XML to String
	 * 
	 * @param document
	 *            XML Document
	 * @param indent
	 * @return XML Document String
	 * @throws XMLToStringException
	 */
	public static String xmlToString(Document document, boolean indent) throws XMLToStringException {
		String xmlToString = "";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			writeXml(document, os, indent);
			xmlToString = os.toString();
		} catch (Exception e) {
			throw new XMLToStringException("Error converting XML", e);
		}
		return xmlToString;
	}

	/**
	 * Convert XML to String
	 * 
	 * @param document
	 *            XML Document
	 * @return XML Document String
	 * @throws XMLToStringException
	 */
	public static String xmlToString(Document document) throws XMLToStringException {
		return xmlToString(document, true);
	}

	/**
	 * Convert String to XML Document
	 * 
	 * @param stringDocument
	 *            String to convert
	 * @return Document
	 * @throws XMLParseException
	 */
	public static Document stringToXml(String stringDocument) throws XMLParseException {
		Document doc = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(new InputSource(new StringReader(stringDocument)));
		} catch (Exception e) {
			throw new XMLParseException("Error converting string to XML", e);
		}
		return doc;
	}

	/**
	 * Writes a document XML to stream, example: ByteArrayOutputStream, System.out, FileOutputStream
	 * 
	 * @param document
	 *            XML document
	 * @param out
	 *            Stream
	 * @throws XMLWriteException
	 */
	public static void writeXml(Document document, OutputStream out) throws XMLWriteException {
		writeXml(document, out, true);
	}

	/**
	 * Writes a document XML to stream, example: ByteArrayOutputStream, System.out, FileOutputStream
	 * 
	 * @param document
	 *            XML document
	 * @param out
	 *            Stream
	 * @param indent
	 * @throws XMLWriteException
	 */
	public static void writeXml(Document document, OutputStream out, boolean indent) throws XMLWriteException {
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes" /* no */);
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.ENCODING, CHARSET_UTF8);
			// transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
			if (indent) {
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			}
			transformer.transform(new DOMSource(document), new StreamResult(new OutputStreamWriter(out, CHARSET_UTF8)));
		} catch (Exception e) {
			throw new XMLWriteException("Error writing xml", e);
		}
	}

	/**
	 * Dets new document
	 * 
	 * @return A new Document
	 * @throws XMLDocumentException
	 * @throws ParserConfigurationException
	 */
	public static Document newDocument() throws XMLDocumentException {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.newDocument();
		} catch (Exception e) {
			throw new XMLDocumentException("Error in new xml document", e);
		}
	}
}
