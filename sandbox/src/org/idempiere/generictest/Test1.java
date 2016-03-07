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
import java.util.ArrayList;
import java.util.List;

/**
 * This class test the HttpURLConnection
 */
public class Test1 {

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

	public static List<String> getXML() {
		List<String> list = new ArrayList<String>();
		list.add("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:_0=\"http://idempiere.org/ADInterface/1_0\">");
		list.add("<soapenv:Header/>");
		list.add("<soapenv:Body>");
		list.add("<_0:queryData>");
		list.add("<_0:ModelCRUDRequest>");
		list.add("<_0:ModelCRUD>");
		list.add("<_0:serviceType>QueryBPartner</_0:serviceType>");
		list.add("</_0:ModelCRUD>");
		list.add("<_0:ADLoginRequest>");
		list.add("<_0:user>SuperUser</_0:user>");
		list.add("<_0:pass>System</_0:pass>");
		list.add("<_0:ClientID>11</_0:ClientID>");
		list.add("<_0:RoleID>102</_0:RoleID>");
		list.add("</_0:ADLoginRequest>");
		list.add("</_0:ModelCRUDRequest>");
		list.add("</_0:queryData>");
		list.add("</soapenv:Body>");
		list.add("</soapenv:Envelope>");
		return list;
	}

	public static void main(String[] args) {

		try {

			URL u = new URL("http://localhost:8080/ADInterface/services/ModelADService");
			URLConnection uc = u.openConnection();
			HttpURLConnection connection = (HttpURLConnection) uc;

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");

			OutputStream out = connection.getOutputStream();
			Writer wout = new OutputStreamWriter(out);

			List<String> file = readFileToList(new File("../documents/QueryBPartnerTest_request.xml"), "UTF-8");
			// List<String> file = getXML();

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

		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
