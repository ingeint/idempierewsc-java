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

package org.idempiere.webservice.client.base;

import org.idempiere.webservice.client.base.Enums.Language;

/**
 * Class to abstract the iDempiere Web Service Login
 */
public class LoginRequest {

	private String user;
	private String pass;
	private Language lang;
	private Integer clientID;
	private Integer roleID;
	private Integer orgID;
	private Integer warehouseID;
	private Integer stage;

	/**
	 * Gets the user
	 * 
	 * @return The user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Sets the iDempiere User (select name from ad_user)
	 * 
	 * @param user
	 *            The user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gets the pass
	 * 
	 * @return The pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * Sets the iDempiere Password (select password from ad_user)
	 * 
	 * @param pass
	 *            The pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * Gets the clientID
	 * 
	 * @return The clientID
	 */
	public Integer getClientID() {
		return clientID;
	}

	/**
	 * Sets the clientID. Client (select ad_client_id from ad_client)
	 * 
	 * @param clientID
	 *            The clientID to set
	 */
	public void setClientID(Integer clientID) {
		this.clientID = clientID;
	}

	/**
	 * Gets the roleID
	 * 
	 * @return The roleID
	 */
	public Integer getRoleID() {
		return roleID;
	}

	/**
	 * Sets the roleID. Role (select ad_role_id from ad_role)
	 * 
	 * @param roleID
	 *            The roleID to set
	 */
	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	/**
	 * Gets the orgID
	 * 
	 * @return The orgID
	 */
	public Integer getOrgID() {
		return orgID;
	}

	/**
	 * Sets the orgID. Organization (select ad_org_id from ad_org)
	 * 
	 * @param orgID
	 *            The orgID to set
	 */
	public void setOrgID(Integer orgID) {
		this.orgID = orgID;
	}

	/**
	 * Gets the warehouseID
	 * 
	 * @return The warehouseID
	 */
	public Integer getWarehouseID() {
		return warehouseID;
	}

	/**
	 * Sets the warehouseID. Warehouse (select m_warehouse_id from m_warehouse)
	 * 
	 * @param warehouseID
	 *            The warehouseID to set
	 */
	public void setWarehouseID(Integer warehouseID) {
		this.warehouseID = warehouseID;
	}

	/**
	 * Gets the stage
	 * 
	 * @return The stage
	 */
	public Integer getStage() {
		return stage;
	}

	/**
	 * Sets the stage
	 * 
	 * @param stage
	 *            The stage to set
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**
	 * Sets the language. Example: Language.es_CO, Language.en_US
	 * 
	 * @param lang
	 *            The lang to set
	 */
	public void setLang(Language lang) {
		this.lang = lang;
	}

	/**
	 * Gets the lang
	 * 
	 * @return The lang
	 */
	public Language getLang() {
		return lang;
	}

}
