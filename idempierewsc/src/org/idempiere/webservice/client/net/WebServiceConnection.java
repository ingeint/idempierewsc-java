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

package org.idempiere.webservice.client.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.idempiere.webservice.client.base.ComponentInfo;
import org.idempiere.webservice.client.base.CompositeRequest;
import org.idempiere.webservice.client.base.Operation;
import org.idempiere.webservice.client.base.WebServiceRequest;
import org.idempiere.webservice.client.base.WebServiceResponse;
import org.idempiere.webservice.client.exceptions.RequestFactoryException;
import org.idempiere.webservice.client.exceptions.ResponseFactoryException;
import org.idempiere.webservice.client.exceptions.WebServiceException;
import org.idempiere.webservice.client.exceptions.WebServiceTimeoutException;
import org.idempiere.webservice.client.exceptions.XMLWriteException;
import org.idempiere.webservice.client.request.CompositeOperationRequest;
import org.idempiere.webservice.client.request.CreateDataRequest;
import org.idempiere.webservice.client.request.CreateUpdateDataRequest;
import org.idempiere.webservice.client.request.DeleteDataRequest;
import org.idempiere.webservice.client.request.GetListRequest;
import org.idempiere.webservice.client.request.QueryDataRequest;
import org.idempiere.webservice.client.request.ReadDataRequest;
import org.idempiere.webservice.client.request.RequestFactory;
import org.idempiere.webservice.client.request.RunProcessRequest;
import org.idempiere.webservice.client.request.SetDocActionRequest;
import org.idempiere.webservice.client.request.UpdateDataRequest;
import org.idempiere.webservice.client.response.CompositeResponse;
import org.idempiere.webservice.client.response.ResponseFactory;
import org.idempiere.webservice.client.response.RunProcessResponse;
import org.idempiere.webservice.client.response.StandardResponse;
import org.idempiere.webservice.client.response.WindowTabDataResponse;
import org.idempiere.webservice.client.util.XMLUtil;

import org.w3c.dom.Document;

/**
 * Client class for soap protocol. This class send a stream data xml.
 */
public class WebServiceConnection {

	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String DEFAULT_CONTENT_TYPE = "text/xml; charset=UTF-8";
	public static final String DEFAULT_REQUEST_METHOD = "POST";
	public static final int DEFAULT_TIMEOUT = 5000;
	public static final int DEFAULT_ATTEMPTS = 1;
	public static final int DEFAULT_ATTEMPTS_TIMEOUT = 500;

	private String appName;
	private String url;
	private String contentType;
	private String requestMethod;
	private int attempts;
	private int timeout;
	private int attemptsTimeout;
	private int timeRequest;
	private int attemptsRequest;
	private Proxy proxy;
	private Document xmlRequest;
	private Document xmlResponse;
	private WebServiceRequest request;

	/**
	 * Gets the xmlRequest
	 * 
	 * @return The xmlRequest
	 */
	public Document getXmlRequest() {
		return xmlRequest;
	}

	/**
	 * Gets the xmlResponse
	 * 
	 * @return The xmlResponse
	 */
	public Document getXmlResponse() {
		return xmlResponse;
	}

	/**
	 * Writes de xml request to stream
	 * 
	 * @param out
	 *            Output Stream
	 * @throws XMLWriteException
	 * @throws XMLStreamException
	 */
	public void writeRequest(OutputStream out) throws XMLWriteException {
		XMLUtil.writeXml(xmlRequest, out);
	}

	/**
	 * Save request to file
	 * 
	 * @param fileName
	 *            File to save
	 * @throws XMLStreamException
	 */
	public void writeRequest(String fileName) throws XMLWriteException {
		try {
			writeRequest(new FileOutputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			throw new XMLWriteException("Save request error", e);
		}
	}

	/**
	 * Writes de xml response to stream
	 * 
	 * @param out
	 *            Output Stream
	 * @throws XMLStreamException
	 */
	public void writeResponse(OutputStream out) throws XMLWriteException {
		XMLUtil.writeXml(xmlResponse, out);
	}

	/**
	 * Save response to file
	 * 
	 * @param fileName
	 *            File to save
	 * @throws XMLStreamException
	 */
	public void writeResponse(String fileName) throws XMLWriteException {
		try {
			writeResponse(new FileOutputStream(new File(fileName)));
		} catch (FileNotFoundException e) {
			throw new XMLWriteException("Save response error", e);
		}
	}

	/**
	 * Gets full user agent
	 * 
	 * @return Full user agent name
	 */
	public final String getUserAgent() {
		return String.format("%s (%s/%s/%s/%s %s %s) %s", ComponentInfo.NAME, ComponentInfo.COMPONENT_NAME, ComponentInfo.VERSION, "Java", System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch"), getAppName()).trim();
	}

	/**
	 * Gets the user agent product. The product using the lib
	 * 
	 * @return The user agent product
	 */
	public String getAppName() {
		if (appName == null)
			return "";
		return appName;
	}

	/**
	 * Sets the user agent product. The product using the lib
	 * 
	 * @return The user agent product
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Gets the attempts to connect
	 * 
	 * @return The attempts
	 */
	public int getAttempts() {
		if (attempts <= 0)
			return DEFAULT_ATTEMPTS;
		return attempts;
	}

	/**
	 * Sets the attempts to connect
	 * 
	 * @param attempts
	 *            The attempts to set
	 */
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	/**
	 * Timeout between attempt
	 * 
	 * @return The attempts timeout
	 */
	public int getAttemptsTimeout() {
		if (attemptsTimeout <= 0)
			return DEFAULT_ATTEMPTS_TIMEOUT;
		return attemptsTimeout;
	}

	/**
	 * Sets timeout between attempt
	 * 
	 * @param attemptsTimeout
	 *            The attempts timeout to set
	 */
	public void setAttemptsTimeout(int attemptsTimeout) {
		this.attemptsTimeout = attemptsTimeout;
	}

	/**
	 * Gets the timeout for connection
	 * 
	 * @return The timeout
	 */
	public int getTimeout() {
		if (timeout <= 0)
			return DEFAULT_TIMEOUT;
		return timeout;
	}

	/**
	 * Sets the timeout for connection
	 * 
	 * @param timeout
	 *            The timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Gets the URL connection
	 * 
	 * @return The URL
	 */
	public String getUrl() {
		if (url == null)
			return "";
		return url;
	}

	/**
	 * Sets the URL connection
	 * 
	 * @param url
	 *            the URL to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Protected method, gets the path of web services
	 * 
	 * @return Path
	 */
	private String getPath() {
		if (request == null)
			return null;
		return String.format("/ADInterface/services/%s", request.getWebServiceDefinition());
	}

	/**
	 * Build the url for web service
	 * 
	 * @return Url
	 */
	private String getWebServiceUrl() {

		if (getPath() == null)
			return getUrl();

		String url = getUrl();
		if (url.endsWith("/"))
			url = url.substring(0, url.length() - 1);

		String path = getPath();
		if (path.startsWith("/"))
			path = path.substring(1);

		return String.format("%s/%s", url, path);
	}

	/**
	 * Gets the content type header
	 * 
	 * @return The content type
	 */
	public String getContentType() {
		if (contentType == null)
			return DEFAULT_CONTENT_TYPE;
		return contentType;
	}

	/**
	 * Sets the content type header
	 * 
	 * @param contentType
	 *            the content Type to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Gets the request method
	 * 
	 * @return The request method
	 */
	public String getRequestMethod() {
		if (requestMethod == null)
			return DEFAULT_REQUEST_METHOD;
		return requestMethod;
	}

	/**
	 * Sets the request method
	 * 
	 * @param requestMethod
	 *            The request method to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	/**
	 * Gets time to request finish in millis
	 * 
	 * @return The time to execute request
	 */
	public int getTimeRequest() {
		return timeRequest;
	}

	/**
	 * Gets the attempts request
	 * 
	 * @return The attempts request
	 */
	public int getAttemptsRequest() {
		return attemptsRequest;
	}

	/**
	 * Gets the proxy
	 * 
	 * @return The proxy
	 */
	public Proxy getProxy() {
		return proxy;
	}

	/**
	 * Sets the proxy
	 * 
	 * @param proxy
	 *            The proxy to set
	 */
	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	/**
	 * Default constructor
	 */
	public WebServiceConnection() {
		requestMethod = DEFAULT_REQUEST_METHOD;
		attempts = DEFAULT_ATTEMPTS;
		attemptsTimeout = DEFAULT_ATTEMPTS_TIMEOUT;
		timeout = DEFAULT_TIMEOUT;
		contentType = DEFAULT_CONTENT_TYPE;
		url = "";
	}

	/**
	 * Send string data request
	 * 
	 * @param dataRequest
	 *            Data request
	 * @return Response in XML format
	 */
	public Document sendRequest(String dataRequest) throws WebServiceException {

		if (getUrl() == null || getUrl().isEmpty())
			throw new WebServiceException("URL must be different than empty or null");

		try {
			if (getUrl().toLowerCase().startsWith("https")) {
				HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifierAll());
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new X509TrustManagerAll() }, new SecureRandom());
				HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			}
		} catch (Exception e) {
			throw new WebServiceException("Error sending request, SSL error", e);
		}

		long startTime = System.currentTimeMillis();
		attemptsRequest = 0;
		boolean successful = false;
		String dataResponse = "";

		while (!successful) {
			attemptsRequest++;

			try {
				URL u = new URL(getWebServiceUrl());
				URLConnection uc = null;

				if (proxy != null)
					uc = u.openConnection(proxy);
				else
					uc = u.openConnection();

				HttpURLConnection connection = (HttpURLConnection) uc;
				connection.setRequestMethod(getRequestMethod());
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestProperty("Content-Type", getContentType());
				connection.setRequestProperty("User-Agent", getUserAgent());
				connection.setConnectTimeout(getTimeout());
				connection.setReadTimeout(getTimeout());

				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), CHARSET_UTF8);
				out.write(dataRequest);
				out.flush();
				out.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), CHARSET_UTF8));
				String inString;
				while ((inString = in.readLine()) != null) {
					dataResponse += inString;
				}
				in.close();

				successful = true;
			} catch (Exception e) {
				if (attemptsRequest >= getAttempts()) {
					timeRequest = ((int) (System.currentTimeMillis() - startTime));
					if (e.getClass().equals(SocketTimeoutException.class))
						throw new WebServiceTimeoutException("Timeout exception, operation has expired", e);
					throw new WebServiceException("Error sending request", e);
				} else {
					try {
						Thread.sleep(getAttemptsTimeout());
						continue;
					} catch (InterruptedException ie) {
						throw new WebServiceException("Error sending request, sleep thread", ie);
					}
				}
			}
		}

		Document doc;
		try {
			doc = XMLUtil.stringToXml(dataResponse);
			doc.setXmlStandalone(true);
		} catch (Exception e) {
			throw new WebServiceException("Error converting response to Document", e);
		}

		timeRequest = ((int) (System.currentTimeMillis() - startTime));
		return doc;
	}

	/**
	 * Send XML Document data request
	 * 
	 * @param dataRequest
	 *            XML Data request
	 * @return Response in XML format
	 * @throws Exception
	 */
	public Document sendRequest(Document dataRequest) throws WebServiceException {
		Document document = null;
		try {
			document = sendRequest(XMLUtil.xmlToString(dataRequest, false));
		} catch (WebServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new WebServiceException("Error sending request", e);
		}
		return document;
	}

	/**
	 * Generic send request
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public WebServiceResponse sendRequest(WebServiceRequest request) throws WebServiceException {
		this.request = request;

		Document xmlRequest = null;
		try {
			xmlRequest = RequestFactory.createRequest(request);
			this.xmlRequest = xmlRequest;
		} catch (RequestFactoryException e) {
			throw new WebServiceException("Request Factory error", e);
		}

		Document xmlResponse = sendRequest(xmlRequest);
		this.xmlResponse = xmlResponse;

		try {
			WebServiceResponse response = ResponseFactory.createResponse(request.getWebServiceResponseModel(), xmlResponse);

			if ((response instanceof CompositeResponse) && (request instanceof CompositeRequest)) {
				CompositeResponse compositeResponse = (CompositeResponse) response;
				CompositeRequest compositeRequest = (CompositeRequest) request;

				if (compositeResponse.getResponsesCount() > 0) {
					List<WebServiceResponse> responses = compositeResponse.getResponses();
					List<Operation> operations = compositeRequest.getOperations();

					for (int i = 0; i < responses.size(); i++) {
						WebServiceResponse tempResponse = responses.get(i);
						WebServiceRequest temRequest = operations.get(i).getWebService();
						tempResponse.setWebServiceType(temRequest.getWebServiceType());
					}
				}
			}

			response.setWebServiceType(request.getWebServiceType());
			return response;
		} catch (ResponseFactoryException e) {
			throw new WebServiceException("Response Factory error", e);
		}
	}

	/**
	 * Send request for composite web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public CompositeResponse sendRequest(CompositeOperationRequest request) throws WebServiceException {
		return (CompositeResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for run process web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public RunProcessResponse sendRequest(RunProcessRequest request) throws WebServiceException {
		return (RunProcessResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for read data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public WindowTabDataResponse sendRequest(ReadDataRequest request) throws WebServiceException {
		return (WindowTabDataResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for query data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public WindowTabDataResponse sendRequest(QueryDataRequest request) throws WebServiceException {
		return (WindowTabDataResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for getlist data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public WindowTabDataResponse sendRequest(GetListRequest request) throws WebServiceException {
		return (WindowTabDataResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for delete data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public StandardResponse sendRequest(DeleteDataRequest request) throws WebServiceException {
		return (StandardResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for create data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public StandardResponse sendRequest(CreateDataRequest request) throws WebServiceException {
		return (StandardResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for create update data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public StandardResponse sendRequest(CreateUpdateDataRequest request) throws WebServiceException {
		return (StandardResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for update data web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public StandardResponse sendRequest(UpdateDataRequest request) throws WebServiceException {
		return (StandardResponse) sendRequest((WebServiceRequest) request);
	}

	/**
	 * Send request for doc action web service
	 * 
	 * @param request
	 *            Request to send
	 * @return Response model
	 * @throws WebServiceException
	 */
	public StandardResponse sendRequest(SetDocActionRequest request) throws WebServiceException {
		return (StandardResponse) sendRequest((WebServiceRequest) request);
	}

}
