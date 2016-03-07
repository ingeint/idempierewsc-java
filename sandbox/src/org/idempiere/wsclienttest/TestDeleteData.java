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

import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.DeleteDataRequest;
import org.idempiere.webservice.client.response.StandardResponse;

public class TestDeleteData extends AbstractTestWS {

	@Override
	public String getWebServiceType() {
		return "DeleteBPartnerTest";
	}

	@Override
	public void testPerformed() {
		DeleteDataRequest deleteData = new DeleteDataRequest();
		deleteData.setLogin(getLogin());
		deleteData.setWebServiceType(getWebServiceType());
		deleteData.setRecordID(1000003);

		WebServiceConnection client = getClient();

		try {
			StandardResponse response = client.sendRequest(deleteData);

			if (response.getStatus() == WebServiceResponseStatus.Error) {
				System.out.println(response.getErrorMessage());
			} else {
				System.out.println("RecordID: " + response.getRecordID());
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
