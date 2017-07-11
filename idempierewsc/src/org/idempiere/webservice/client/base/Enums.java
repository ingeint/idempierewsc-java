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

/**
 * Enums for web service
 */
public class Enums {

	/**
	 * WebService Type Definition
	 */
	public enum WebServiceDefinition {
		ModelADService, compositeInterface
	}

	/**
	 * WebService Method
	 */
	public enum WebServiceMethod {
		runProcess, createData, createUpdateData, deleteData, getList, queryData, readData, setDocAction, updateData, compositeOperation
	}

	/**
	 * Response Model
	 */
	public enum WebServiceResponseModel {
		StandardResponse, RunProcessResponse, WindowTabDataResponse, CompositeResponse
	}

	/**
	 * Response Status
	 */
	public enum WebServiceResponseStatus {
		Error, Successful, Unsuccessful
	}

	/**
	 * Request Model
	 */
	public enum WebServiceRequestModel {
		ModelCRUDRequest, ModelGetListRequest, ModelRunProcessRequest, ModelSetDocActionRequest, CompositeRequest
	}

	/**
	 * For field container
	 */
	public enum FieldsContainerType {
		DataRow, ParamValues
	}

	/**
	 * ModelCRUD Action Values
	 */
	public enum ModelCRUDAction {
		Read, Create, CreateUpdate, Delete, Update
	}

	/**
	 * iDempiere Document Action Values
	 */
	public enum DocAction {

		/**
		 * Complete = CO
		 */
		Complete("CO"),

		/**
		 * Wait Complete = WC
		 */
		WaitComplete("WC"),

		/**
		 * Approve = AP
		 */
		Approve("AP"),

		/**
		 * Reject = RJ
		 */
		Reject("RJ"),

		/**
		 * Post = PO
		 */
		Post("PO"),

		/**
		 * Void = VO
		 */
		Void("VO"),

		/**
		 * Close = CL
		 */
		Close("CL"),

		/**
		 * Reverse - Correct = RC
		 */
		ReverseCorrect("RC"),

		/**
		 * Reverse - Accrual = RA
		 */
		ReverseAccrual("RA"),

		/**
		 * ReActivate = RE
		 */
		ReActivate("RE"),

		/**
		 * <None> = --
		 */
		None("--"),

		/**
		 * Prepare = PR
		 */
		Prepare("PR"),

		/**
		 * Unlock = XL
		 */
		Unlock("XL"),

		/**
		 * Invalidate = IN
		 */
		Invalidate("IN"),

		/**
		 * ReOpen = OP
		 */
		ReOpen("OP");

		private final String value;

		/**
		 * Gets the iDempiere Document Action Value
		 * 
		 * @return The iDempiere Document Action Value
		 */
		public final String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            iDempiere Document Action Value
		 */
		private DocAction(String value) {
			this.value = value;
		}
	}

	/**
	 * iDempiere Document Status Values
	 */
	public enum DocStatus {

		/**
		 * Drafted = DR
		 */
		Drafted("DR"),

		/**
		 * Completed = CO
		 */
		Completed("CO"),

		/**
		 * Approved = AP
		 */
		Approved("AP"),

		/**
		 * Invalid = IN
		 */
		Invalid("IN"),

		/**
		 * Not Approved = NA
		 */
		NotApproved("NA"),

		/**
		 * Voided = VO
		 */
		Voided("VO"),

		/**
		 * Reversed = RE
		 */
		Reversed("RE"),

		/**
		 * Closed = CL
		 */
		Closed("CL"),

		/**
		 * Unknown = ??
		 */
		Unknown("??"),

		/**
		 * In Progress = IP
		 */
		InProgress("IP"),

		/**
		 * Waiting Payment = WP
		 */
		WaitingPayment("WP"),

		/**
		 * Waiting Confirmation = WC
		 */
		WaitingConfirmation("WC");

		private final String value;

		/**
		 * Gets the iDempiere Document Status Value
		 * 
		 * @return The iDempiere Document Status Value
		 */
		public final String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            iDempiere Document Status Value
		 */
		private DocStatus(String value) {
			this.value = value;
		}
	}

	/**
	 * iDempiere Language Values
	 */
	public enum Language {

		/**
		 * Arabic (United Arab Emirates) [Language ISO = ar, Country Code = AE]
		 */
		ar_AE,

		/**
		 * Arabic (Bahrain) [Language ISO = ar, Country Code = BH]
		 */
		ar_BH,

		/**
		 * Arabic (Algeria) [Language ISO = ar, Country Code = DZ]
		 */
		ar_DZ,

		/**
		 * Arabic (Egypt) [Language ISO = ar, Country Code = EG]
		 */
		ar_EG,

		/**
		 * Arabic (Iraq) [Language ISO = ar, Country Code = IQ]
		 */
		ar_IQ,

		/**
		 * Arabic (Jordan) [Language ISO = ar, Country Code = JO]
		 */
		ar_JO,

		/**
		 * Arabic (Kuwait) [Language ISO = ar, Country Code = KW]
		 */
		ar_KW,

		/**
		 * Arabic (Lebanon) [Language ISO = ar, Country Code = LB]
		 */
		ar_LB,

		/**
		 * Arabic (Libya) [Language ISO = ar, Country Code = LY]
		 */
		ar_LY,

		/**
		 * Arabic (Morocco) [Language ISO = ar, Country Code = MA]
		 */
		ar_MA,

		/**
		 * Arabic (Oman) [Language ISO = ar, Country Code = OM]
		 */
		ar_OM,

		/**
		 * Arabic (Qatar) [Language ISO = ar, Country Code = QA]
		 */
		ar_QA,

		/**
		 * Arabic (Saudi Arabia) [Language ISO = ar, Country Code = SA]
		 */
		ar_SA,

		/**
		 * Arabic (Sudan) [Language ISO = ar, Country Code = SD]
		 */
		ar_SD,

		/**
		 * Arabic (Syria) [Language ISO = ar, Country Code = SY]
		 */
		ar_SY,

		/**
		 * Arabic (Tunisia) [Language ISO = ar, Country Code = TN]
		 */
		ar_TN,

		/**
		 * Arabic (Yemen) [Language ISO = ar, Country Code = YE]
		 */
		ar_YE,

		/**
		 * Byelorussian (Belarus) [Language ISO = be, Country Code = BY]
		 */
		be_BY,

		/**
		 * Bulgarian (Bulgaria) [Language ISO = bg, Country Code = BG]
		 */
		bg_BG,

		/**
		 * Catalan (Spain) [Language ISO = ca, Country Code = ES]
		 */
		ca_ES,

		/**
		 * Czech (Czech Republic) [Language ISO = cs, Country Code = CZ]
		 */
		cs_CZ,

		/**
		 * Danish (Denmark) [Language ISO = da, Country Code = DK]
		 */
		da_DK,

		/**
		 * German (Austria) [Language ISO = de, Country Code = AT]
		 */
		de_AT,

		/**
		 * German (Switzerland) [Language ISO = de, Country Code = CH]
		 */
		de_CH,

		/**
		 * German (Germany) [Language ISO = de, Country Code = DE]
		 */
		de_DE,

		/**
		 * German (Luxembourg) [Language ISO = de, Country Code = LU]
		 */
		de_LU,

		/**
		 * Greek (Cyprus) [Language ISO = el, Country Code = CY]
		 */
		el_CY,

		/**
		 * Greek (Greece) [Language ISO = el, Country Code = GR]
		 */
		el_GR,

		/**
		 * English (Australia) [Language ISO = en, Country Code = AU]
		 */
		en_AU,

		/**
		 * English (Canada) [Language ISO = en, Country Code = CA]
		 */
		en_CA,

		/**
		 * English (United Kingdom) [Language ISO = en, Country Code = GB]
		 */
		en_GB,

		/**
		 * English (Ireland) [Language ISO = en, Country Code = IE]
		 */
		en_IE,

		/**
		 * English (India) [Language ISO = en, Country Code = IN]
		 */
		en_IN,

		/**
		 * English (Malta) [Language ISO = en, Country Code = MT]
		 */
		en_MT,

		/**
		 * English (New Zealand) [Language ISO = en, Country Code = NZ]
		 */
		en_NZ,

		/**
		 * English (Philippines) [Language ISO = en, Country Code = PH]
		 */
		en_PH,

		/**
		 * English (Singapore) [Language ISO = en, Country Code = SG]
		 */
		en_SG,

		/**
		 * English (USA) [Language ISO = en, Country Code = US]
		 */
		en_US,

		/**
		 * English (South Africa) [Language ISO = en, Country Code = ZA]
		 */
		en_ZA,

		/**
		 * Spanish (Argentina) [Language ISO = es, Country Code = AR]
		 */
		es_AR,

		/**
		 * Spanish (Bolivia) [Language ISO = es, Country Code = BO]
		 */
		es_BO,

		/**
		 * Spanish (Chile) [Language ISO = es, Country Code = CL]
		 */
		es_CL,

		/**
		 * Spanish (Colombia) [Language ISO = es, Country Code = CO]
		 */
		es_CO,

		/**
		 * Spanish (Costa Rica) [Language ISO = es, Country Code = CR]
		 */
		es_CR,

		/**
		 * Spanish (Dominican Republic) [Language ISO = es, Country Code = DO]
		 */
		es_DO,

		/**
		 * Spanish (Ecuador) [Language ISO = es, Country Code = EC]
		 */
		es_EC,

		/**
		 * Spanish (Spain) [Language ISO = es, Country Code = ES]
		 */
		es_ES,

		/**
		 * Spanish (Guatemala) [Language ISO = es, Country Code = GT]
		 */
		es_GT,

		/**
		 * Spanish (Honduras) [Language ISO = es, Country Code = HN]
		 */
		es_HN,

		/**
		 * Spanish (Mexico) [Language ISO = es, Country Code = MX]
		 */
		es_MX,

		/**
		 * Spanish (Nicaragua) [Language ISO = es, Country Code = NI]
		 */
		es_NI,

		/**
		 * Spanish (Panama) [Language ISO = es, Country Code = PA]
		 */
		es_PA,

		/**
		 * Spanish (Peru) [Language ISO = es, Country Code = PE]
		 */
		es_PE,

		/**
		 * Spanish (Puerto Rico) [Language ISO = es, Country Code = PR]
		 */
		es_PR,

		/**
		 * Spanish (Paraguay) [Language ISO = es, Country Code = PY]
		 */
		es_PY,

		/**
		 * Spanish (El Salvador) [Language ISO = es, Country Code = SV]
		 */
		es_SV,

		/**
		 * Spanish (USA) [Language ISO = es, Country Code = US]
		 */
		es_US,

		/**
		 * Spanish (Uruguay) [Language ISO = es, Country Code = UY]
		 */
		es_UY,

		/**
		 * Spanish (Venezuela) [Language ISO = es, Country Code = VE]
		 */
		es_VE,

		/**
		 * Estonian (Estonia) [Language ISO = et, Country Code = EE]
		 */
		et_EE,

		/**
		 * Farsi (Iran) [Language ISO = fa, Country Code = IR]
		 */
		fa_IR,

		/**
		 * Finnish (Finland) [Language ISO = fi, Country Code = FI]
		 */
		fi_FI,

		/**
		 * French (Belgium) [Language ISO = fr, Country Code = BE]
		 */
		fr_BE,

		/**
		 * French (Canada) [Language ISO = fr, Country Code = CA]
		 */
		fr_CA,

		/**
		 * French (Switzerland) [Language ISO = fr, Country Code = CH]
		 */
		fr_CH,

		/**
		 * French (France) [Language ISO = fr, Country Code = FR]
		 */
		fr_FR,

		/**
		 * French (Luxembourg) [Language ISO = fr, Country Code = LU]
		 */
		fr_LU,

		/**
		 * Irish (Ireland) [Language ISO = ga, Country Code = IE]
		 */
		ga_IE,

		/**
		 * Hindi (India) [Language ISO = hi, Country Code = IN]
		 */
		hi_IN,

		/**
		 * Croatian (Croatia) [Language ISO = hr, Country Code = HR]
		 */
		hr_HR,

		/**
		 * Hungarian (Hungary) [Language ISO = hu, Country Code = HU]
		 */
		hu_HU,

		/**
		 * Indonesian (Indonesia) [Language ISO = in, Country Code = ID]
		 */
		in_ID,

		/**
		 * Icelandic (Iceland) [Language ISO = is, Country Code = IS]
		 */
		is_IS,

		/**
		 * Italian (Switzerland) [Language ISO = it, Country Code = CH]
		 */
		it_CH,

		/**
		 * Italian (Italy) [Language ISO = it, Country Code = IT]
		 */
		it_IT,

		/**
		 * Hebrew (Israel) [Language ISO = iw, Country Code = IL]
		 */
		iw_IL,

		/**
		 * Japanese (Japan) [Language ISO = ja, Country Code = JP]
		 */
		ja_JP,

		/**
		 * Korean (South Korea) [Language ISO = ko, Country Code = KR]
		 */
		ko_KR,

		/**
		 * Lithuanian (Lithuania) [Language ISO = lt, Country Code = LT]
		 */
		lt_LT,

		/**
		 * Latvian (Lettish) (Latvia) [Language ISO = lv, Country Code = LV]
		 */
		lv_LV,

		/**
		 * Macedonian (Macedonia) [Language ISO = mk, Country Code = MK]
		 */
		mk_MK,

		/**
		 * Malay (Malaysia) [Language ISO = ms, Country Code = MY]
		 */
		ms_MY,

		/**
		 * Maltese (Malta) [Language ISO = mt, Country Code = MT]
		 */
		mt_MT,

		/**
		 * Dutch (Belgium) [Language ISO = nl, Country Code = BE]
		 */
		nl_BE,

		/**
		 * Dutch (Netherlands) [Language ISO = nl, Country Code = NL]
		 */
		nl_NL,

		/**
		 * Norwegian (Norway) [Language ISO = no, Country Code = NO]
		 */
		no_NO,

		/**
		 * Polish (Poland) [Language ISO = pl, Country Code = PL]
		 */
		pl_PL,

		/**
		 * Portuguese (Brazil) [Language ISO = pt, Country Code = BR]
		 */
		pt_BR,

		/**
		 * Portuguese (Portugal) [Language ISO = pt, Country Code = PT]
		 */
		pt_PT,

		/**
		 * Romanian (Romania) [Language ISO = ro, Country Code = RO]
		 */
		ro_RO,

		/**
		 * Russian (Russia) [Language ISO = ru, Country Code = RU]
		 */
		ru_RU,

		/**
		 * Serbo-Croatian (Yugoslavia) [Language ISO = sh, Country Code = YU]
		 */
		sh_YU,

		/**
		 * Slovak (Slovakia) [Language ISO = sk, Country Code = SK]
		 */
		sk_SK,

		/**
		 * Slovenian (Slovenia) [Language ISO = sl, Country Code = SI]
		 */
		sl_SI,

		/**
		 * Albanian (Albania) [Language ISO = sq, Country Code = AL]
		 */
		sq_AL,

		/**
		 * Serbian (Bosnia and Herzegovina) [Language ISO = sr, Country Code = BA]
		 */
		sr_BA,

		/**
		 * Serbian (Serbia and Montenegro) [Language ISO = sr, Country Code = CS]
		 */
		sr_CS,

		/**
		 * Serbian (Montenegro) [Language ISO = sr, Country Code = ME]
		 */
		sr_ME,

		/**
		 * Serbian (Serbia) [Language ISO = sr, Country Code = RS]
		 */
		sr_RS,

		/**
		 * Serbian (Yugoslavia) [Language ISO = sr, Country Code = YU]
		 */
		sr_YU,

		/**
		 * Swedish (Sweden) [Language ISO = sv, Country Code = SE]
		 */
		sv_SE,

		/**
		 * Thai (Thailand) [Language ISO = th, Country Code = TH]
		 */
		th_TH,

		/**
		 * Turkish (Turkey) [Language ISO = tr, Country Code = TR]
		 */
		tr_TR,

		/**
		 * Ukrainian (Ukraine) [Language ISO = uk, Country Code = UA]
		 */
		uk_UA,

		/**
		 * Vietnamese [Language ISO = vi, Country Code = VN]
		 */
		vi_VN,

		/**
		 * Chinese (China) [Language ISO = zh, Country Code = CN]
		 */
		zh_CN,

		/**
		 * Chinese (Hong Kong) [Language ISO = zh, Country Code = HK]
		 */
		zh_HK,

		/**
		 * Chinese (Singapore) [Language ISO = zh, Country Code = SG]
		 */
		zh_SG,

		/**
		 * Chinese (Taiwan) [Language ISO = zh, Country Code = TW]
		 */
		zh_TW
	}
	
	/**
	 * Error types on response
	 * 
	 * @author antunesleo
	 *
	 */
	public enum ErrorType {
		RECORD_NOT_EXISTS,
		SERVICE_TYPE_NOT_EXISTS,
		UNKNOW_ERROR,
		EMPTY_ERROR
	}
}
