package com.thirdparty.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tripayweb.thirdparty.request.SMSRequest;

@Controller
@RequestMapping("/SendSMS")
public class SMSController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/Now/{mobileNumber}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> sendSMSNow(@PathVariable(value = "mobileNumber") String mobileNumber) {
		String response = "";
		final String destination = mobileNumber;
		try {
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						URL sendUrl = new URL("https://sms6.routesms.com:8443/bulksms/bulksms?");
						HostnameVerifier hostVerifier = new HostnameVerifier() {

							@Override
							public boolean verify(String hostname, SSLSession session) {
								return true;
							}
						};
						trustAllHttpsCertificates();
						HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection();
						httpConnection.setRequestMethod("POST");
						httpConnection.setDoInput(true);
						httpConnection.setDoOutput(true);
						httpConnection.setUseCaches(false);

						DataOutputStream dataStreamToServer = new DataOutputStream(httpConnection.getOutputStream());
						dataStreamToServer.writeBytes("username=" + URLEncoder.encode("msewaOTP", "UTF-8")
								+ "&password=" + URLEncoder.encode("Hnfk5qzx", "UTF-8") + "&type="
								+ URLEncoder.encode("0", "UTF-8") + "&dlr=" + URLEncoder.encode("1", "UTF-8")
								+ "&destination=" + URLEncoder.encode(destination, "UTF-8") + "&source="
								+ URLEncoder.encode("VPYQWK", "UTF-8") + "&message="
								+ URLEncoder.encode("Testing", "UTF-8"));

						dataStreamToServer.flush();
						dataStreamToServer.close();

						BufferedReader dataStreamFromUrl = new BufferedReader(
								new InputStreamReader(httpConnection.getInputStream()));
						String smsResponse = "", dataBuffer = "";

						while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
							smsResponse += dataBuffer;
						}

						dataStreamFromUrl.close();
						logger.info("sms response is :" + smsResponse);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
			t.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<String> sendSMS(final SMSRequest dto) {
		String response = "";
		try {
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						URL sendUrl = new URL("https://sms6.routesms.com:8443/bulksms/bulksms?");

						HostnameVerifier hostVerifier = new HostnameVerifier() {
							@Override
							public boolean verify(String hostname, SSLSession session) {
								return true;
							}
						};
						trustAllHttpsCertificates();
						HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection();
						httpConnection.setRequestMethod("POST");
						httpConnection.setDoInput(true);
						httpConnection.setDoOutput(true);
						httpConnection.setUseCaches(false);

						DataOutputStream dataStreamToServer = new DataOutputStream(httpConnection.getOutputStream());
						dataStreamToServer.writeBytes("username=" + URLEncoder.encode("msewaOTP", "UTF-8")
								+ "&password=" + URLEncoder.encode("Hnfk5qzx", "UTF-8") + "&type="
								+ URLEncoder.encode("0", "UTF-8") + "&dlr="
								+ URLEncoder.encode("1", "UTF-8") + "&destination="
								+ URLEncoder.encode(dto.getDestination(), "UTF-8") + "&source="
								+ URLEncoder.encode("VPYQWK", "UTF-8") + "&message="
								+ URLEncoder.encode(dto.getMessage(), "UTF-8"));

						dataStreamToServer.flush();
						dataStreamToServer.close();
						BufferedReader dataStreamFromUrl = new BufferedReader(
								new InputStreamReader(httpConnection.getInputStream()));
						String smsResponse = "", dataBuffer = "";

						while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
							smsResponse += dataBuffer;
						}
						dataStreamFromUrl.close();
						System.err.print("sms response is :" + smsResponse);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
			t.start();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("", HttpStatus.OK);
	}

	private static void trustAllHttpsCertificates() throws Exception {
		// Create a trust manager that does not validate certificate chains:
		javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
		javax.net.ssl.TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}

	public static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
		public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}

		public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
				throws java.security.cert.CertificateException {
			return;
		}
	}
}
