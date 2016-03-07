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

import org.idempiere.webservice.client.base.DataRow;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.CreateUpdateDataRequest;
import org.idempiere.webservice.client.response.StandardResponse;

public class TestCreateUpdateData extends AbstractTestWS {

	@Override
	public String getWebServiceType() {
		return "CreateUpdateBPartnerTest";
	}

	@Override
	public void testPerformed() {
		CreateUpdateDataRequest createData = new CreateUpdateDataRequest();
		createData.setLogin(getLogin());
		createData.setWebServiceType(getWebServiceType());

		DataRow data = new DataRow();
		data.addField("Name", "Test BPartner 2");
		data.addField("Value", "Test_BPartner_CreateUpdateTest");
		data.addField("TaxID", "123456");
		createData.setDataRow(data);

		WebServiceConnection client = getClient();

		try {
			StandardResponse response = client.sendRequest(createData);

			if (response.getStatus() == WebServiceResponseStatus.Error) {
				System.out.println(response.getErrorMessage());
			} else {

				System.out.println("RecordID: " + response.getRecordID());
				System.out.println();

				for (int i = 0; i < response.getOutputFields().getFieldsCount(); i++) {
					System.out.println("Column" + (i + 1) + ": " + response.getOutputFields().getField(i).getColumn() + " = " + response.getOutputFields().getField(i).getValue());
				}
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
