package com.chityog.chityogws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.ForgotPasswordInfo;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.mail.MailMail;
import com.chityog.chityogws.security.MD5;
import com.chityog.chityogws.service.CountryService;
import com.chityog.chityogws.service.UserService;
import com.chityog.chityogws.utils.Config;
import com.chityog.chityogws.validations.UserValidations;

@RestController
public class Controller {

	@Autowired
	private CountryService countryService;

	@Autowired
	private UserService userService;

	/*
	 * Spring uses the Jackson JSON library to automatically marshal instances
	 * of type VivahBean into JSON and result of hitting this webservice will
	 * return list of json object containing Vivah info .
	 */

	@RequestMapping("/getCountries")
	public Map<String, Object> getCountries() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("countries", countryService.getCountryList());
		return map;
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Map<String, Object> signup(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("Creating User " + user.getName());
		map = UserValidations.validateUser(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {

			UserInfo userInfo = userService.checkExistingUser(user);
			if (userInfo != null) {
				map.put("status", Config.ERROR);
				map.put("msg", "Email Id already exists");

			} else if (userService.checkExistingPhone(user) != null) {
				map.put("status", Config.ERROR);
				map.put("msg", "Mobile number already exists");

			} else {

				userService.createUser(user);
				userInfo = userService.checkExistingUser(user);
				map.put("msg", "New user created successfully");
				map.put("user", userInfo);

			}

			return map;
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.validateLogin(user);

		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUser(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "Email id does not exits");
			} else if (!userInfo.getPassword().equalsIgnoreCase(
					MD5.encode(user.getPassword()))) {
				map.put("status", Config.ERROR);
				map.put("msg", "Incorrect Password");
			} else {
				map.put("msg", "Logged in user details");
				map.put("user", userInfo);
			}

			return map;
		}

	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public Map<String, Object> changePassword(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.validatePassword(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				if (!userInfo.getPassword().equalsIgnoreCase(
						MD5.encode(user.getOldPassword()))) {
					map.put("status", Config.ERROR);
					map.put("msg", "PLease enter correct old password");
				} else {
					int result = userService.updateUserPassword(user);
					if (result == 1) {
						map.put("msg", "Password has been changed successfully");

					} else {
						map.put("status", Config.ERROR);
						map.put("msg", "Database error occured");
					}
				}
			}
		}

		return map;

	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public Map<String, Object> forgotPassword(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();

		map = UserValidations.checkEmail(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUser(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "Email id does not exits");
			} else {
				String randomStr = "";
				Random r = new Random();
				for (int i = 0; i < 4; i++) {
					char c = (char) (r.nextInt(26) + 'a');
					randomStr = randomStr + String.valueOf(c);
				}

				ForgotPasswordInfo forgotPasswordInfo = userService
						.checkExistingCode(userInfo);
				int result;
				if (forgotPasswordInfo == null) {
					result = userService.createNewRandomPassword(userInfo,
							randomStr);
				} else {
					result = userService.updateRandomPassword(userInfo,
							forgotPasswordInfo, randomStr);
				}

				if (result == 1) {
					ApplicationContext context = new ClassPathXmlApplicationContext(
							"file:src/main/webapp/WEB-INF/chityogws-servlet.xml");

					MailMail mm = (MailMail) context.getBean("mailMail");
					mm.sendMail("gaurav3292@gmail.com", user.getEmail(),
							"Forgot Password", "Your verification code is "
									+ randomStr);

					map.put("msg",
							"Your verification code has been sent to your mail please check your Email.");

				} else {
					map.put("status", Config.ERROR);
					map.put("msg", "Database error occured");
				}
			}
		}

		return map;
	}

}
