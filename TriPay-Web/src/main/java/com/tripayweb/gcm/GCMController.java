package com.tripayweb.gcm;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class GCMController {

	 @RequestMapping(value="/sendNoti")
	public void NOTY() {
		try{
			
			 String registrationId = "APA91bFlslj8c7YbKAkViZ_aReVlZXwDfq-NIg3KRe8g8NA7_NG1dvC0wCXLm6xHQsOujAEf9ocYsqQTWPuvSbT4sONKrHuKVWjquFYRMqEHJ2f0FLPwssg";
			 String title = "Test";
			 String message = "Test Message";
			 String picPath="http://fgmtest.firstglobalmoney.com:8034/resources/images/vijayalogo.png";
			 HttpURLConnection con = (HttpURLConnection) new
			 URL("https://android.googleapis.com/gcm/send").openConnection();
			 con.setRequestMethod("POST");
			 con.setRequestProperty("Content-Type", "application/json");
			 con.setRequestProperty("Authorization", "key=" +
			 "AIzaSyAD8zS7ELihlHZbkXYlBiR3vGjDsJy98Nw");
			 con.setDoOutput(true);
			 String input = "{\"registration_ids\" : [" + "\"registrationId\"" + "],\"data\" : {\"title\":\"" + title
			 + "\",\"message\": \"" + message + "\",\"image\":\"" + picPath + "\"}}";
			 System.out.println(input);
			
			 con.getOutputStream().write(input.getBytes());
			 con.getOutputStream().flush();
			
			 con.getOutputStream().close();
			
			 int responseCode = con.getResponseCode();
			 StringBuilder str = new StringBuilder();
			 str.append("Response Code : " + responseCode);
			 str.append("\nResponse Message : " + con.getResponseMessage());
			 System.err.println("Response :: "+str);
			 
		}catch(Exception e){
			
		}
	}
}
