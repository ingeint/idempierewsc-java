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

package org.idempiere.wsclienttest;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.idempiere.webservice.client.base.DataRow;
import org.idempiere.webservice.client.base.LoginRequest;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.CompositeOperationRequest;
import org.idempiere.webservice.client.request.CreateDataRequest;
import org.idempiere.webservice.client.response.CompositeResponse;

public class ExampleCreateImage {

	public static LoginRequest getLogin() {
		LoginRequest login = new LoginRequest();
		login.setUser("SuperUser");
		login.setPass("System");
		login.setClientID(11);
		login.setRoleID(102);
		login.setOrgID(0);
		return login;
	}

	public static String getUrlBase() {
		return "http://localhost:8031";
	}

	public static WebServiceConnection getClient() {
		WebServiceConnection client = new WebServiceConnection();
		client.setAttempts(3);
		client.setTimeout(5000);
		client.setAttemptsTimeout(5000);
		client.setUrl(getUrlBase());
		client.setAppName("Java Test WS Client");
		return client;
	}

	public static byte[] readFile(String file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];

		for (int readNum; (readNum = fis.read(buf)) != -1;) {
			bos.write(buf, 0, readNum);
		}

		bos.flush();
		byte[] temp = bos.toByteArray();
		bos.close();
		fis.close();
		return temp;
	}

	public static void main(String[] args) {
		// CREATE DE COMPOSITE WS
		CompositeOperationRequest compositeOperation = new CompositeOperationRequest();
		compositeOperation.setWebServiceType("CompositeBPartnerTest");

		// SET LOGIN
		compositeOperation.setLogin(getLogin());

		// CREATE WS FOR IMAGE
		CreateDataRequest createImage = new CreateDataRequest();
		createImage.setWebServiceType("CreateImageTest");

		String imageName = "img/idempiere-logo.png";

		DataRow data = new DataRow();
		data.addField("Name", imageName);
		data.addField("Description", "Test Create BPartner and Logo");

		// SET IMAGE
		try {
			byte[] file = readFile(imageName);
			data.addField("BinaryData", file);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		createImage.setDataRow(data);

		// CREATE WS FOR BPARTNER
		CreateDataRequest createBP = new CreateDataRequest();
		createBP.setWebServiceType("CreateBPartnerTest");

		DataRow dataBP = new DataRow();
		dataBP.addField("Name", "Test BPartner");
		dataBP.addField("Value", "Test_BPartner" + System.currentTimeMillis());
		dataBP.addField("TaxID", "123456");
		dataBP.addField("Logo_ID", "@AD_Image.AD_Image_ID");
		createBP.setDataRow(dataBP);

		compositeOperation.addOperation(createImage);
		compositeOperation.addOperation(createBP);

		// CREATE CLIENT
		WebServiceConnection client = getClient();

		try {
			// SEND REQUEST
			CompositeResponse response = client.sendRequest(compositeOperation);

			client.writeRequest(System.out);
			System.out.println();
			client.writeResponse(System.out);
			System.out.println();

			// GET DE RESPONSE
			if (response.getStatus() == WebServiceResponseStatus.Error) {
				System.out.println(response.getErrorMessage());
			} else {
				for (int i = 0; i < response.getResponsesCount(); i++) {
					if (response.getResponse(i).getStatus() == WebServiceResponseStatus.Error) {
						System.out.println(response.getResponse(i).getErrorMessage());
					} else {
						System.out.println(response.getResponse(i).getWebServiceResponseModel());
					}
				}
			}

			System.out.println("--------------------------");
			System.out.println("Web Service: CompositeBPartnerTest");
			System.out.println("Attempts: " + client.getAttemptsRequest());
			System.out.println("Time: " + client.getTimeRequest());
			System.out.println("--------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
