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

import java.io.FileOutputStream;
import java.io.IOException;

import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.base.Field;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.ReadDataRequest;
import org.idempiere.webservice.client.response.WindowTabDataResponse;

public class TestReadImage extends AbstractTestWS {

	public void writeFile(String file, byte[] bytes) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(bytes);
		fos.flush();
		fos.close();
	}

	@Override
	public String getWebServiceType() {
		return "ReadImageTest";
	}

	@Override
	public void testPerformed() {

		ReadDataRequest ws = new ReadDataRequest();
		ws.setWebServiceType(getWebServiceType());
		ws.setLogin(getLogin());
		ws.setRecordID(1000002);

		WebServiceConnection client = getClient();

		try {
			WindowTabDataResponse response = client.sendRequest(ws);

			if (response.getStatus() == WebServiceResponseStatus.Error) {
				System.out.println(response.getErrorMessage());
			} else if (response.getStatus() == WebServiceResponseStatus.Unsuccessful) {
				System.out.println("Unsuccessful");
			} else {
				System.out.println("Total rows: " + response.getNumRows());
				System.out.println();
				for (int i = 0; i < response.getDataSet().getRowsCount(); i++) {
					System.out.println("Row: " + (i + 1));
					for (int j = 0; j < response.getDataSet().getRow(i).getFieldsCount(); j++) {
						Field field = response.getDataSet().getRow(i).getFields().get(j);
						System.out.println("Column: " + field.getColumn() + " = " + field.getValue());

						if (field.getColumn().equals("BinaryData") && !field.getValue().toString().isEmpty()) {
							writeFile("img/ReadImageTest.png", field.getByteValue());
						}

					}
					System.out.println();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
