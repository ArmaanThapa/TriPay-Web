package com.tripayweb.app.api.impl;

import java.io.StringReader;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.gcm.utils.APIConstants;
import com.tripayweb.app.model.request.VNetRequest;
import com.tripayweb.app.model.response.VNetResponse;
import com.tripayweb.app.model.response.VRedirectResponse;
import com.thirdparty.model.ResponseDTO;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripayweb.app.api.ILoadMoneyApi;
import com.tripayweb.app.metadatas.UrlMetadatas;
import com.tripayweb.app.model.Device;
import com.tripayweb.app.model.Language;
import com.tripayweb.app.model.Role;
import com.tripayweb.app.model.Version;
import com.tripayweb.app.model.request.LoadMoneyRequest;
import com.tripayweb.app.model.response.EBSRedirectResponse;
import com.tripayweb.app.model.response.LoadMoneyResponse;
import com.tripayweb.app.utils.SecurityUtils;
import com.tripayweb.util.JSONParserUtil;
import com.tripayweb.util.LogCat;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LoadMoneyApi implements ILoadMoneyApi {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public LoadMoneyResponse loadMoneyRequest(LoadMoneyRequest request) {
		LoadMoneyResponse resp = new LoadMoneyResponse();
		try {
			JSONObject payload = new JSONObject();
			payload.put("sessionId", request.getSessionId());
			payload.put("channel", request.getChannel());
			payload.put("account_id", request.getAccountId());
			
			payload.put("reference_no", request.getReferenceNo());
			
			payload.put("amount", request.getAmount());
			payload.put("mode", request.getMode());
			
			payload.put("currency", request.getCurrency());
			payload.put("description", request.getDescription());
			payload.put("return_url", request.getReturnUrl());
			
			payload.put("name", request.getName());
			payload.put("address", request.getAddress());
			payload.put("city", request.getCity());
			payload.put("state", request.getState());
			payload.put("country", request.getCountry());
			payload.put("postal_code", request.getPostalCode());
			payload.put("phone", request.getPhone());
			payload.put("email", request.getEmail());
			
			payload.put("ship_name", request.getShipName());
			payload.put("ship_address", request.getShipAddress());
			payload.put("ship_city", request.getShipCity());
			payload.put("ship_state", request.getShipState());
			payload.put("ship_country", request.getShipCountry());
			payload.put("ship_postal_code", request.getShipPostalCode());
			payload.put("ship_phone", request.getShipPhone());
			System.err.println(request.getSessionId());
			   LogCat.print("Load Money Payload :: " + payload.toString());
			Client client = Client.create();
			client.addFilter(new LoggingFilter(System.out));
			WebResource webResource = client.resource(
					UrlMetadatas.getLoadMoneyUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(payload.toString())).post(ClientResponse.class, payload);

			if (response.getStatus() != 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
			} else {
				String strResponse = response.getEntity(String.class);
				org.json.JSONObject jObj = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(jObj, "code");
				if (code.equalsIgnoreCase("S00")) {
					org.json.JSONObject details = JSONParserUtil.getObject(jObj, "details");
					if (details != null) {
						resp.setAccountId(JSONParserUtil.getString(details, "account_id"));
						resp.setAddress(JSONParserUtil.getString(details, "address"));
						resp.setAmount(JSONParserUtil.getString(details, "amount"));
						resp.setChannel(JSONParserUtil.getString(details, "channel"));
						resp.setCity(JSONParserUtil.getString(details, "city"));
						resp.setCountry(JSONParserUtil.getString(details, "country"));
						resp.setCurrency(JSONParserUtil.getString(details, "currency"));
						resp.setDescription(JSONParserUtil.getString(details, "description"));
						resp.setEmail(JSONParserUtil.getString(details, "email"));
						resp.setMode(JSONParserUtil.getString(details, "mode"));
						resp.setName(JSONParserUtil.getString(details, "name"));
						resp.setPhone(JSONParserUtil.getString(details, "phone"));
						resp.setPostalCode(JSONParserUtil.getString(details, "postal_code"));
						resp.setReferenceNo(JSONParserUtil.getString(details, "reference_no"));
						resp.setReturnUrl(JSONParserUtil.getString(details, "return_url"));
						resp.setSecureHash(JSONParserUtil.getString(details, "secure_hash"));
						resp.setShipAddress(JSONParserUtil.getString(details, "ship_address"));
						resp.setShipCity(JSONParserUtil.getString(details, "ship_city"));
						resp.setShipCountry(JSONParserUtil.getString(details, "ship_country"));
						resp.setShipName(JSONParserUtil.getString(details, "ship_name"));
						resp.setShipPhone(JSONParserUtil.getString(details, "ship_phone"));
						resp.setShipPostalCode(JSONParserUtil.getString(details, "ship_postal_code"));
						resp.setShipState(JSONParserUtil.getString(details, "ship_state"));
						resp.setState(JSONParserUtil.getString(details, "state"));
						resp.setSuccess(true);
					} else{
						    resp.setSuccess(false);
						}
					} else if(code.equalsIgnoreCase("F00")){
                    System.err.println("got F00");
                    org.json.JSONObject failureDetails = JSONParserUtil.getObject(jObj,"details");
                    if(failureDetails != null){
                        System.out.print("failure details is not null");
                        resp.setDescription(JSONParserUtil.getString(failureDetails,"message"));
                        System.out.println("Response in Load money api is ::"+resp.getDescription());
                    }
				} else {
					resp.setSuccess(false);
				}
				logger.info("Load money response ::" + strResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
		}
		return resp;

	}

	@Override
	public EBSRedirectResponse processRedirectResponse(HttpServletRequest request) {
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		EBSRedirectResponse redirectResponse = new EBSRedirectResponse();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String paramValue = request.getParameter(paramName);


			if (paramName.equalsIgnoreCase("responseCode") && paramValue.equalsIgnoreCase("0")) {
				redirectResponse.setSuccess(true);
			} else if (paramName.equalsIgnoreCase("responseCode") && paramValue.equalsIgnoreCase("1")) {
				redirectResponse.setSuccess(false);
			}
			formData.add(paramName, paramValue);
		}

		try {
			Client client = Client.create();
			WebResource resource = client.resource(UrlMetadatas.getLoadMoneyResponseUrl(Version.VERSION_1, Role.USER,
					Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = resource.accept("application/json").post(ClientResponse.class, formData);
			String strResponse = response.getEntity(String.class);
			logger.info("string response ::" + strResponse);

			if (response.getStatus() == 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				org.json.JSONObject jObj = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(jObj, "code");
				if (code.equalsIgnoreCase("S00")) {
					redirectResponse.setSuccess(true);

                    org.json.JSONObject details = JSONParserUtil.getObject(jObj,"details");
					redirectResponse.setDetails(String.valueOf(details));
				} else {
					redirectResponse.setResponseCode("1");
					redirectResponse.setSuccess(false);
				}
			} else {
				redirectResponse.setResponseCode("1");
				redirectResponse.setSuccess(false);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirectResponse;
	}

	@Override
	public VNetResponse initiateVnetBanking(VNetRequest request) {
		VNetResponse resp = new VNetResponse();
		try {
			Client client = Client.create();
			client.addFilter(new LoggingFilter(System.out));
			WebResource webResource = client.resource(
					UrlMetadatas.getLoadMoneyVNetUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(request.toJSON().toString())).post(ClientResponse.class, request.toJSON());

			if (response.getStatus() != 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				resp.setSuccess(false);
			} else {
				String strResponse = response.getEntity(String.class);
				org.json.JSONObject jObj = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(jObj, "code");
				if (code.equalsIgnoreCase("S00")) {
					org.json.JSONObject details = JSONParserUtil.getObject(jObj, "details");
					if (details != null) {
						resp.setMerchantName(JSONParserUtil.getString(details,"merchantName"));
						resp.setReturnUrl(JSONParserUtil.getString(details,"returnURL"));
						resp.setItc(JSONParserUtil.getString(details,"itc"));
						resp.setAmount(JSONParserUtil.getString(details,"amount"));
						resp.setCrn(JSONParserUtil.getString(details,"crnNo"));
						resp.setPrn(JSONParserUtil.getString(details,"prnNo"));
						resp.setPid(JSONParserUtil.getString(details,"pid"));
						resp.setMid(JSONParserUtil.getString(details,"mid"));
						resp.setSuccess(true);
					} else{
						resp.setSuccess(false);
					}
				} else if(code.equalsIgnoreCase("F00")){
					System.err.println("got F00");
					org.json.JSONObject failureDetails = JSONParserUtil.getObject(jObj,"details");
					if(failureDetails != null){
						System.out.print("failure details is not null");
						resp.setMessage(JSONParserUtil.getString(failureDetails,"message"));
						System.out.println("Response in Load money api is ::"+resp.getMessage());
					}
				} else {
					resp.setSuccess(false);
				}
				logger.info("Load money response ::" + strResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setSuccess(false);
		}
		return resp;

	}

	@Override
	public ResponseDTO handleRedirectRequest(VRedirectResponse dto) {
		ResponseDTO result = new ResponseDTO();
		try {
			Client client = Client.create();
			client.addFilter(new LoggingFilter(System.out));
			WebResource webResource = client.resource(
					UrlMetadatas.getLoadMoneyVNetResponseUrl(Version.VERSION_1, Role.USER, Device.WEBSITE, Language.ENGLISH));

			ClientResponse response = webResource.accept("application/json").type("application/json")
					.header("hash", SecurityUtils.getHash(dto.toJSON().toString())).post(ClientResponse.class, dto.toJSON());

			if (response.getStatus() != 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				result.setSuccess(false);
			} else {
				String strResponse = response.getEntity(String.class);
				org.json.JSONObject jObj = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(jObj, "code");
				if (code.equalsIgnoreCase("S00")) {
						result.setSuccess(true);
						result.setMessage(JSONParserUtil.getString(jObj,"message"));
						result.setCode(JSONParserUtil.getString(jObj,"code"));
				} else{
						result.setSuccess(false);
					}

				logger.info("Load money response ::" + strResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public ResponseDTO verifyEBSTransaction(EBSRedirectResponse resp) {
		final ResponseDTO result = new ResponseDTO();
		try {
			Client client = Client.create();
			client.addFilter(new LoggingFilter(System.out));
			WebResource webResource = client.resource(com.tripayweb.api.constants.APIConstants.URL.EBS_VERIFICATION);

			MultivaluedMapImpl formData = new MultivaluedMapImpl();
			formData.add("Action","statusByRef");
			formData.add("AccountID","20696");
			formData.add("SecretKey","6496e4db9ebf824ffe2269afee259447");
			formData.add("RefNo",resp.getMerchantRefNo());
			ClientResponse response = webResource.post(ClientResponse.class, formData);
			String strResponse = response.getEntity(String.class);
			if (response.getStatus() != 200) {
				logger.info("RESPONSE  :: " + strResponse);
				result.setSuccess(false);
			} else {
				logger.info("RESPONSE  :: " + strResponse);
				SAXParserFactory factory = SAXParserFactory.newInstance();
				SAXParser saxParser = factory.newSAXParser();
				DefaultHandler handler = new DefaultHandler(){

					boolean output = false;

					public void startElement(String uri, String localName,String qName,
											 Attributes attributes) throws SAXException {

						System.out.println("Start Element :" + qName);

						if (qName.equalsIgnoreCase("output")) {
							output = true;
						}
						if(output){
							System.out.println(attributes.getValue("paymentId"));
							System.out.println(attributes.getValue("status"));
							String status = attributes.getValue("status");
							String error = attributes.getValue("error");
							String transactionType = attributes.getValue("transactionType");
							if(error == null) {
								if (transactionType.equalsIgnoreCase("Authorized")) {
									if(status.equalsIgnoreCase("Processed")) {
										result.setSuccess(true);
										result.setCode("S00");
									}
								} else {
									result.setSuccess(false);
									result.setCode("F00");
									result.setMessage("Transaction is " + transactionType);
								}
							}else {
								result.setSuccess(false);
								result.setCode("F00");
								result.setMessage(error);
							}
						}
					}
				};

				saxParser.parse(new InputSource(new StringReader(strResponse)),handler);



			logger.info("Load money response ::" + strResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}

	@Override
	public EBSRedirectResponse processRedirectSDK(EBSRedirectResponse redirectResponse) {
		try {
			MultivaluedMapImpl formData = new MultivaluedMapImpl();
			formData.add("responseCode",redirectResponse.getResponseCode());
			formData.add("merchantRefNo",redirectResponse.getMerchantRefNo());
			Client client = Client.create();
			WebResource resource = client.resource(UrlMetadatas.getLoadMoneyResponseUrl(Version.VERSION_1, Role.USER,
					Device.WEBSITE, Language.ENGLISH));
			ClientResponse response = resource.accept("application/json").post(ClientResponse.class, formData);
			String strResponse = response.getEntity(String.class);
			logger.info("string response ::" + strResponse);

			if (response.getStatus() == 200) {
				logger.info("RESPONSE CODE :: " + response.getStatus());
				org.json.JSONObject jObj = new org.json.JSONObject(strResponse);
				final String code = JSONParserUtil.getString(jObj, "code");
				if (code.equalsIgnoreCase("S00")) {
					redirectResponse.setSuccess(true);
					org.json.JSONObject details = JSONParserUtil.getObject(jObj,"details");
					redirectResponse.setDetails(String.valueOf(details));
				} else {
					redirectResponse.setResponseCode("1");
					redirectResponse.setSuccess(false);
				}
			} else {
				redirectResponse.setResponseCode("1");
				redirectResponse.setSuccess(false);
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirectResponse;
	}

}
