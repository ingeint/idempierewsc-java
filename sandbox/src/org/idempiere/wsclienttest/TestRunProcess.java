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
import org.idempiere.webservice.client.base.ParamValues;
import org.idempiere.webservice.client.net.WebServiceConnection;
import org.idempiere.webservice.client.request.RunProcessRequest;
import org.idempiere.webservice.client.response.RunProcessResponse;

public class TestRunProcess extends AbstractTestWS {

	@Override
	public String getWebServiceType() {
		return "RunProcessValidateBPartnerTest";
	}

	@Override
	public void testPerformed() {
		RunProcessRequest process = new RunProcessRequest();
		process.setWebServiceType(getWebServiceType());
		process.setLogin(getLogin());

		ParamValues params = new ParamValues();
		params.addField("C_BPartner_ID", "50003");
		process.setParamValues(params);

		WebServiceConnection client = getClient();

		try {
			RunProcessResponse response = client.sendRequest(process);

			if (response.getStatus() == WebServiceResponseStatus.Error) {
				System.out.println(response.getErrorMessage());
			} else {
				System.out.println(response.getSummary());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
