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
import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.CompositeOperationRequest;
import org.idempiere.webservice.client.request.CreateDataRequest;
import org.idempiere.webservice.client.response.CompositeResponse;

public class TestCreateImage extends AbstractTestWS {

	public byte[] readFile(String file) throws IOException {
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

	@Override
	public String getWebServiceType() {
		return "CompositeBPartnerTest";
	}

	@Override
	public void testPerformed() {

		CompositeOperationRequest compositeOperation = new CompositeOperationRequest();
		compositeOperation.setLogin(getLogin());
		compositeOperation.setWebServiceType(getWebServiceType());

		CreateDataRequest createImage = new CreateDataRequest();
		createImage.setWebServiceType("CreateImageTest");

		String imageName = "img/idempiere-logo.png";

		DataRow data = new DataRow();
		data.addField("Name", imageName);
		data.addField("Description", "Test Create BPartner and Logo");

		try {
			byte[] file = readFile(imageName);
			data.addField("BinaryData", file);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		createImage.setDataRow(data);

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

		WebServiceConnection client = getClient();

		try {
			CompositeResponse response = client.sendRequest(compositeOperation);

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

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
