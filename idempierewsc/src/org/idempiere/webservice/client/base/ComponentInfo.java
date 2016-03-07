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

import java.util.HashMap;
import java.util.Map;

/**
 * Component Info Class
 */
public class ComponentInfo {

	public static final String NAME = "iDempiere Web Service Client";
	public static final String COMPONENT_NAME = "idempierewsc";
	public static final String VERSION = "1.6.0";

	/**
	 * Get Component info
	 * 
	 * @return Map info
	 */
	public static Map<String, String> ToMap() {
		Map<String, String> info = new HashMap<String, String>();
		info.put("NAME", NAME);
		info.put("COMPONENT_NAME", COMPONENT_NAME);
		info.put("VERSION", VERSION);
		return info;
	}
}
