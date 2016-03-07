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

package org.idempiere.webservice.client.util;

public class Base64Util {

	private static final String CODES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

	/**
	 * Copyright Wikipedia. This implementation is designed to illustrate the mechanics of Base64 encoding and decoding, not necessarily for memory or time efficiency. https://en.wikipedia.org/wiki/Base64
	 * 
	 * @param input
	 * @return Decode
	 */
	public static byte[] decode(String input) {
		if (input.length() % 4 != 0) {
			throw new IllegalArgumentException("Invalid base64 input");
		}
		byte decoded[] = new byte[((input.length() * 3) / 4) - (input.indexOf('=') > 0 ? (input.length() - input.indexOf('=')) : 0)];
		char[] inChars = input.toCharArray();
		int j = 0;
		int b[] = new int[4];
		for (int i = 0; i < inChars.length; i += 4) {
			b[0] = CODES.indexOf(inChars[i]);
			b[1] = CODES.indexOf(inChars[i + 1]);
			b[2] = CODES.indexOf(inChars[i + 2]);
			b[3] = CODES.indexOf(inChars[i + 3]);
			decoded[j++] = (byte) ((b[0] << 2) | (b[1] >> 4));
			if (b[2] < 64) {
				decoded[j++] = (byte) ((b[1] << 4) | (b[2] >> 2));
				if (b[3] < 64) {
					decoded[j++] = (byte) ((b[2] << 6) | b[3]);
				}
			}
		}

		return decoded;
	}

	/**
	 * Copyright Wikipedia. This implementation is designed to illustrate the mechanics of Base64 encoding and decoding, not necessarily for memory or time efficiency. https://en.wikipedia.org/wiki/Base64
	 * 
	 * @param input
	 *            Bytes to encode
	 * @return String base 64
	 */
	public static String encode(byte[] input) {
		StringBuffer out = new StringBuffer((input.length * 4) / 3);
		int b;
		for (int i = 0; i < input.length; i += 3) {
			b = (input[i] & 0xFC) >> 2;
			out.append(CODES.charAt(b));
			b = (input[i] & 0x03) << 4;
			if (i + 1 < input.length) {
				b |= (input[i + 1] & 0xF0) >> 4;
				out.append(CODES.charAt(b));
				b = (input[i + 1] & 0x0F) << 2;
				if (i + 2 < input.length) {
					b |= (input[i + 2] & 0xC0) >> 6;
					out.append(CODES.charAt(b));
					b = input[i + 2] & 0x3F;
					out.append(CODES.charAt(b));
				} else {
					out.append(CODES.charAt(b));
					out.append('=');
				}
			} else {
				out.append(CODES.charAt(b));
				out.append("==");
			}
		}
		return out.toString();
	}
}
