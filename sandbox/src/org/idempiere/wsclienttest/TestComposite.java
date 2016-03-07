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
import org.idempiere.webservice.client.base.Enums.DocAction;
import org.idempiere.webservice.client.base.Enums.WebServiceResponseStatus;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.CompositeOperationRequest;
import org.idempiere.webservice.client.request.CreateDataRequest;
import org.idempiere.webservice.client.request.SetDocActionRequest;
import org.idempiere.webservice.client.response.CompositeResponse;

public class TestComposite extends AbstractTestWS {

	@Override
	public String getWebServiceType() {
		return "CompositeMovementTest";
	}

	@Override
	public void testPerformed() {
		CompositeOperationRequest compositeOperation = new CompositeOperationRequest();
		compositeOperation.setLogin(getLogin());
		compositeOperation.setWebServiceType(getWebServiceType());

		CreateDataRequest createMovement = new CreateDataRequest();
		createMovement.setWebServiceType("CreateMovementTest");
		DataRow data = new DataRow();
		data.addField("C_DocType_ID", "143");
		data.addField("MovementDate", "2015-10-25 00:00:00");
		data.addField("AD_Org_ID", "11");
		createMovement.setDataRow(data);

		CreateDataRequest createMovementLine = new CreateDataRequest();
		createMovementLine.setWebServiceType("CreateMovementLineTest");
		DataRow dataLine = new DataRow();
		dataLine.addField("M_Movement_ID", "@M_Movement.M_Movement_ID");
		dataLine.addField("M_Product_ID", "138");
		dataLine.addField("MovementQty", "1");
		dataLine.addField("M_Locator_ID", "50001");
		dataLine.addField("M_LocatorTo_ID", "50000");
		dataLine.addField("AD_Org_ID", "11");
		createMovementLine.setDataRow(dataLine);

		SetDocActionRequest docAction = new SetDocActionRequest();
		docAction.setDocAction(DocAction.Complete);
		docAction.setWebServiceType("DocActionMovementTest");
		docAction.setRecordIDVariable("@M_Movement.M_Movement_ID");

		compositeOperation.addOperation(createMovement);
		compositeOperation.addOperation(createMovementLine);
		compositeOperation.addOperation(docAction);

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
						System.out.println("Response: " + response.getResponse(i).getWebServiceResponseModel());
						System.out.println("Request: " + response.getResponse(i).getWebServiceType());
					}
					System.out.println();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
