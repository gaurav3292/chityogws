package com.chityog.chotyogws.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class Notifications {

	public String sendNotification() {

		String value = null;
		URL url;
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");

			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			httpCon.setRequestProperty ("Authorization", "key=AIzaSyA5dTIpegtOlIPRRRKM-xX6EmEKY6U3A7Q");
			JSONObject params   = new JSONObject();
			JSONObject dataObj = new JSONObject();
			dataObj.put("title", "PUCH NOTIFICATION");
			dataObj.put("detail", "ANDROID");
			
			params.put("to", "fasQjwtw_GM:APA91bElIS2scRIKc8tgW33fR_vkIqrwi5JcLZSirY3H3ljYqGl3qFM8fjnzAuL3_1YvnL3-bX-8paxMdih_zXsXL4H-FncN9YTvCgy1Y53jd6If6wCqY8tMwUBGcg9b3WNFAYfnNPEZ");
      			
			params.put("data",dataObj.toString());
			
			String input = "{ \"data\": {\"resourceId\": \"sd\",\"position\": 0},\"to\":\"fasQjwtw_GM:APA91bElIS2scRIKc8tgW33fR_vkIqrwi5JcLZSirY3H3ljYqGl3qFM8fjnzAuL3_1YvnL3-bX-8paxMdih_zXsXL4H-FncN9YTvCgy1Y53jd6If6wCqY8tMwUBGcg9b3WNFAYfnNPEZ\"}";
			
			OutputStream out = httpCon.getOutputStream();
			out.write(input.getBytes("UTF-8"));
			out.flush();
			System.out.println(httpCon.getResponseCode());
			System.out.println(httpCon.getResponseMessage());
			value = httpCon.getResponseMessage();
			out.close();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return value;

	}

}
