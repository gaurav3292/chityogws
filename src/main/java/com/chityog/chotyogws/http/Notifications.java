package com.chityog.chotyogws.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.LevelResultInfo;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;
import com.chityog.chityogws.utils.LevelCal;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class Notifications {
	
	private static String PATH_TO_P12_CERT = "src/main/resources/Certificates_production.p12";

	public Map<String, Object> checkUserData(UserLevelInfo userLevelInfo,
			UserInfo userInfo) {

		Map<String, Object> map = new HashMap<String, Object>();

		int value = 0;
		String message = "Keep going with your great spirit. Keep motivated and focused";
		;
		switch (userLevelInfo.getUserLevel()) {
		case "1":

			if (userLevelInfo.getCompletedNumberOfDays() >= 10
					&& userLevelInfo.getIsNotification() == false) {

				if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
					value = sendNotificationToAndroid(message,
							userInfo.getDeviceToken());
				} else {
					sendNotificationToIOS(message, userInfo.getDeviceToken());
					value = 200;
				}
			}

			break;
		case "2":

			if (userLevelInfo.getCompletedNumberOfDays() >= 10
					&& userLevelInfo.getIsNotification() == false) {

				if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
					value = sendNotificationToAndroid(message,
							userInfo.getDeviceToken());
				} else {
					sendNotificationToIOS(message, userInfo.getDeviceToken());
					value = 200;
				}
			}

			break;
		case "3":

			if (userLevelInfo.getCompletedNumberOfDays() >= 10
					&& userLevelInfo.getIsNotification() == false) {

				if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
					value = sendNotificationToAndroid(message,
							userInfo.getDeviceToken());
				}else{
					sendNotificationToIOS(message, userInfo.getDeviceToken());
					value = 200;
				}
			}

			break;
		case "41":
			if (userLevelInfo.getCompletedNumberOfDays() >= 10
					&& userLevelInfo.getIsNotification() == false) {

				if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
					value = sendNotificationToAndroid(message,
							userInfo.getDeviceToken());
				}else{
					sendNotificationToIOS(message, userInfo.getDeviceToken());
					value = 200;
				}
			}

			break;
		case "42":

			if (userLevelInfo.getCompletedNumberOfDays() >= 10
					&& userLevelInfo.getIsNotification() == false) {

				if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
					value = sendNotificationToAndroid(message,
							userInfo.getDeviceToken());
				}else{
					sendNotificationToIOS(message, userInfo.getDeviceToken());
					value = 200;
				}
			}

			break;
		case "5":

			message = "Keep following your meditation program to get connected to you chit.";

			if (userLevelInfo.getCompletedNumberOfDays() >= 10
					&& userLevelInfo.getIsNotification() == false) {

				if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
					value = sendNotificationToAndroid(message,
							userInfo.getDeviceToken());
				}else{
					sendNotificationToIOS(message, userInfo.getDeviceToken());
					value = 200;
				}
			}
			break;

		default:
			break;
		}

		map.put("value", value);
		map.put("msg", message);

		return map;
	}

	public String sendNotification() {

		String value = null;
		URL url;
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");

			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			httpCon.setRequestProperty("Authorization",
					"key=AIzaSyA5dTIpegtOlIPRRRKM-xX6EmEKY6U3A7Q");
			JSONObject params = new JSONObject();
			JSONObject dataObj = new JSONObject();
			dataObj.put("title", "PUCH NOTIFICATION");
			dataObj.put("detail", "ANDROID");

			params.put(
					"to",
					"fasQjwtw_GM:APA91bElIS2scRIKc8tgW33fR_vkIqrwi5JcLZSirY3H3ljYqGl3qFM8fjnzAuL3_1YvnL3-bX-8paxMdih_zXsXL4H-FncN9YTvCgy1Y53jd6If6wCqY8tMwUBGcg9b3WNFAYfnNPEZ");

			params.put("data", dataObj.toString());

			String input = "{ \"data\": {\"msg\": \"sd\"},\"to\":\"fasQjwtw_GM:APA91bElIS2scRIKc8tgW33fR_vkIqrwi5JcLZSirY3H3ljYqGl3qFM8fjnzAuL3_1YvnL3-bX-8paxMdih_zXsXL4H-FncN9YTvCgy1Y53jd6If6wCqY8tMwUBGcg9b3WNFAYfnNPEZ\"}";

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

	public int sendNotificationToAndroid(String message, String token) {

		int value = 0;
		URL url;
		try {
			url = new URL("https://fcm.googleapis.com/fcm/send");

			HttpURLConnection httpCon = (HttpURLConnection) url
					.openConnection();
			httpCon.setDoOutput(true);
			httpCon.setRequestMethod("POST");
			httpCon.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			httpCon.setRequestProperty("Authorization",
					"key=AIzaSyA5dTIpegtOlIPRRRKM-xX6EmEKY6U3A7Q");
			JSONObject params = new JSONObject();
			JSONObject dataObj = new JSONObject();
			dataObj.put("title", "PUCH NOTIFICATION");
			dataObj.put("detail", "ANDROID");

			params.put(
					"to",
					"fasQjwtw_GM:APA91bElIS2scRIKc8tgW33fR_vkIqrwi5JcLZSirY3H3ljYqGl3qFM8fjnzAuL3_1YvnL3-bX-8paxMdih_zXsXL4H-FncN9YTvCgy1Y53jd6If6wCqY8tMwUBGcg9b3WNFAYfnNPEZ");

			params.put("data", dataObj.toString());

			String input = "{ \"data\": {\"msg\": \"%s\"},\"to\":\"%s\"}";

			String finalValue = String.format(input, message, token);

			OutputStream out = httpCon.getOutputStream();
			out.write(finalValue.getBytes("UTF-8"));
			out.flush();
			System.out.println(httpCon.getResponseCode());
			System.out.println(httpCon.getResponseMessage());
			value = httpCon.getResponseCode();
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

	public void sendNotificationToIOS(String message, String token) {

		ApnsService service = APNS.newService()
				.withCert(this.getClass().getResourceAsStream("/Certificates_production.p12"), "chityog_1234")
				.withProductionDestination().build();
		String payload = APNS.newPayload().alertBody(message)
				.alertTitle("Chityog").build();

		service.push(token, payload);

	}

	public Map<String, Object> checkDatesDifference(
			UserLevelInfo userLevelInfo, LevelResultInfo levelResultInfo,
			UserBean userBean, UserInfo userInfo) {
		// TODO Auto-generated method stub
		int value = 0;
		Map<String, Object> map = new HashMap<String, Object>();

		String message = "You are lagging behind in your Program. For any help you can write us at enquires@chityogsadhana.com";

		int daysFromStartDate = LevelCal.getDatesDifference(
				levelResultInfo.getLastSubmittionDate(), userBean.getDate());
		if (daysFromStartDate >= 5) {

			if (userInfo.getDeviceType().equalsIgnoreCase("android")) {
				value = sendNotificationToAndroid(message,
						userInfo.getDeviceToken());
			}else{
				sendNotificationToIOS(message, userInfo.getDeviceToken());
				value = 200;
			}

		}

		map.put("value", value);
		map.put("msg", message);

		return map;
	}

}
