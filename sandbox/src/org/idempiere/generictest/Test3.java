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

import java.io.*;
import java.net.*;
import java.security.cert.*;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.*;

/**
 * This class test the HttpsURLConnection
 */
public class Test3 {

	public static List<String> readFileToList(File file, String charset) throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
		String line;
		while ((line = br.readLine()) != null) {
			list.add(line);
		}
		br.close();
		return list;
	}

	public static void main(String[] args) {

		try {

			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

				public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
					if (hostname.equals("localhost")) {
						return true;
					}
					return false;
				}
			});

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			URL u = new URL("https://localhost:8443/ADInterface/services/ModelADService");
			URLConnection uc = u.openConnection();
			HttpsURLConnection connection = (HttpsURLConnection) uc;

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");

			OutputStream out = connection.getOutputStream();
			Writer wout = new OutputStreamWriter(out);

			List<String> file = readFileToList(new File("../documents/QueryBPartnerTest_request.xml"), "UTF-8");

			for (String string : file) {
				wout.write(string);
			}

			wout.flush();
			wout.close();

			InputStream in = connection.getInputStream();
			int c;
			while ((c = in.read()) != -1)
				System.out.write(c);
			in.close();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
