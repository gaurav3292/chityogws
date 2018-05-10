package com.chityog.chityogws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chityog.chityogws.bean.LevelResultBean;
import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.ForgotPasswordInfo;
import com.chityog.chityogws.domain.LevelResultInfo;
import com.chityog.chityogws.domain.PaymentInfo;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;
import com.chityog.chityogws.helper.ConversionHelper;
import com.chityog.chityogws.mail.CustomMail;
import com.chityog.chityogws.mail.MailMail;
import com.chityog.chityogws.security.MD5;
import com.chityog.chityogws.service.CountryService;
import com.chityog.chityogws.service.LevelResultService;
import com.chityog.chityogws.service.NotificationService;
import com.chityog.chityogws.service.PaymentService;
import com.chityog.chityogws.service.UserLevelService;
import com.chityog.chityogws.service.UserService;
import com.chityog.chityogws.utils.Config;
import com.chityog.chityogws.utils.ImageUpload;
import com.chityog.chityogws.utils.LevelCal;
import com.chityog.chityogws.validations.UserValidations;
import com.chityog.chotyogws.http.Notifications;
import com.google.gson.Gson;

@RestController
public class Controller {

	@Autowired
	private CountryService countryService;

	@Autowired
	private UserLevelService userLevelService;

	@Autowired
	private LevelResultService levelResultService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserService userService;

	@Autowired
	private NotificationService notificationService;

	private CustomMail customMail = new CustomMail();

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

				customMail.sendMailToUserNewUserRegistration(userInfo);
				customMail.sendMailToAdminNewUserREgistration(userInfo);

				map.put("msg",
						"Thanks for registering, Now proceed with further instructions.");
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
				userService.updateUserDevice(user, userInfo);
				if (userInfo.getProfilePic() != null) {
					String imageUrl = Config.IMAGE_LIVE_URL + "/"
							+ userInfo.getProfilePic();
					userInfo.setProfilePic(imageUrl);
				}
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				UserBean userBean = ConversionHelper.convertUserInfoToUserBean(
						userInfo, userLevelInfo);
				map.put("user", userBean);
			}

			return map;
		}

	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public Map<String, Object> changePassword(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getOtp() == null) {
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
							map.put("msg",
									"Password has been changed successfully");

						} else {
							map.put("status", Config.ERROR);
							map.put("msg", "Database error occured");
						}
					}
				}
			}

			return map;
		} else {
			map = UserValidations.validatePasswordOtp(user);
			String status = (String) map.get("status");
			if (status.equalsIgnoreCase(Config.ERROR)) {
				return map;
			} else {
				UserInfo userInfo = userService.checkExistingUserId(user);
				if (userInfo == null) {
					map.put("status", Config.ERROR);
					map.put("msg", "User does not exits");
				} else {
					ForgotPasswordInfo forgotPasswordInfo = userService
							.checkExistingCode(userInfo);
					if (forgotPasswordInfo != null) {

						if (user.getOtp().equalsIgnoreCase(
								forgotPasswordInfo.getForgotPasswordCode())) {
							int result = userService.updateUserPassword(user);
							if (result == 1) {
								map.put("msg",
										"Password has been changed successfully");

							} else {
								map.put("status", Config.ERROR);
								map.put("msg", "Database error occured");
							}

						} else {
							map.put("status", Config.ERROR);
							map.put("msg",
									"Your verification code is not valid");
						}

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
							"spring_mail.xml");

					MailMail mm = (MailMail) context.getBean("mailMail");
					mm.sendMail(user.getEmail(), "Forgot Password",
							"Your verification code is " + randomStr, null);

					map.put("user", userInfo);
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

	@RequestMapping(value = "/selfTestResult", method = RequestMethod.POST)
	public Map<String, Object> selfTestResult(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkSelfTest(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				int result = userService.updateTrues(userInfo,
						user.getNumberOfTrue());
				if (result == 1) {
					String level;
					String msg;

					if (user.getNumberOfTrue() > 10) {
						level = "1";
						msg = "Congratulations! You have unlocked level 1";
					} else {
						level = "3";
						msg = "Congratulations! You have unlocked level 3";
					}

					UserLevelInfo userLevelInfo = userLevelService
							.checkExistingUserLevel(userInfo);
					int levelResult;
					if (userLevelInfo == null) {
						levelResult = userLevelService.createUserLevel(
								userInfo, level);

					} else {
						levelResult = userLevelService.updateUserLevel(
								userInfo, userLevelInfo, level, 30, null);
					}

					userLevelInfo = userLevelService
							.checkExistingUserLevel(userInfo);

					if (levelResult > 0) {
						map.put("status", Config.SUCCESS);
						map.put("msg", msg);
						map.put("level", userLevelInfo);
					} else {
						map.put("status", Config.ERROR);
						map.put("msg", "Database error occured");
					}

				} else {
					map.put("status", Config.ERROR);
					map.put("msg", "Database error occured");
				}
			}

		}

		return map;

	}

	@RequestMapping(value = "/getUserDetail", method = RequestMethod.POST)
	public Map<String, Object> getUserDetail(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();

		map = UserValidations.checkUserId(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				map.put("msg", "User details");
				if (userInfo.getProfilePic() != null) {
					String imageUrl = Config.IMAGE_LIVE_URL + "/"
							+ userInfo.getProfilePic();
					userInfo.setProfilePic(imageUrl);
				}
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				UserBean userBean = ConversionHelper.convertUserInfoToUserBean(
						userInfo, userLevelInfo);
				map.put("user", userBean);
			}
		}
		return map;
	}

	@RequestMapping(value = "/verifyEmail", method = RequestMethod.POST)
	public Map<String, Object> verifyEmail(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();

		map = UserValidations.checkEmailVerification(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUser(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");

			} else {
				String token = "";
				Random r = new Random();
				for (int i = 0; i < 10; i++) {
					char c = (char) (r.nextInt(26) + 'a');
					token = token + String.valueOf(c);
				}

				int tokenResult = userService.updateToken(userInfo, token);
				if (tokenResult == 1) {
					ApplicationContext context = new ClassPathXmlApplicationContext(
							"spring_mail.xml");

					MailMail mm = (MailMail) context.getBean("mailMail");
					mm.sendMail(user.getEmail(), "Verify your email",
							Config.EMAIL_VERIFY_STR + Config.LIVE_URL
									+ "/confirmVerification?token=" + token
									+ "&userId=" + user.getUserId(), null);

					map.put("user", userInfo);
					map.put("msg",
							"Your verification link has been sent to your mail please verify your email.");

				} else {
					map.put("status", Config.ERROR);
					map.put("msg", "Database error occured");
				}
			}
		}

		return map;

	}

	@RequestMapping("/confirmVerification")
	public String confirmVerification(@RequestParam("token") String token,
			@RequestParam("userId") Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkVerifyEmail(token, userId);
		String returnStr = null;
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			returnStr = (String) map.get("msg");
			return returnStr;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(userId);
			if (userInfo.getToken().equalsIgnoreCase(token)) {
				userInfo.setIsEmailVerify(Config.YES);
				int result = userService.updateUserEmailVerification(userInfo);
				if (result > 0) {
					returnStr = "Your email has been verified";
				} else {
					returnStr = "ERROR";
				}
			} else {
				returnStr = "INVALID TOKEN";
			}
		}

		return returnStr;

	}

	@RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public Map<String, Object> editProfile(@RequestBody UserBean user,
			HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();

		map = UserValidations.validateUserProfile(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {

			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				userInfo.setAddress(user.getAddress());
				userInfo.setPhone(user.getPhone());
				userInfo.setName(user.getName());

				if (user.getProfilePic() != null) {
					ImageUpload imageUpload = new ImageUpload(user);
					String filename = imageUpload.uploadImage(request);
					userInfo.setProfilePic(filename);
				} else {
					userInfo.setProfilePic(null);
				}

				int result = userService.updateProfile(userInfo);
				if (result > 0) {
					userInfo = userService.checkExistingUserId(user);
					if (userInfo.getProfilePic() != null) {
						String imageUrl = Config.IMAGE_LIVE_URL + "/"
								+ userInfo.getProfilePic();
						userInfo.setProfilePic(imageUrl);
					}
					UserLevelInfo userLevelInfo = userLevelService
							.checkExistingUserLevel(userInfo);
					UserBean userBean = ConversionHelper
							.convertUserInfoToUserBean(userInfo, userLevelInfo);
					map.put("user", userBean);

					map.put("status", Config.SUCCESS);
					map.put("msg", "Your profile has been updated successfully");
				}

			}

		}

		return map;

	}

	@RequestMapping(value = "/startTest", method = RequestMethod.POST)
	public Map<String, Object> startTest(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkStartTest(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				int result = userLevelService.updateUserLevelInfo(userInfo,
						user);
				if (result > 0) {
					UserLevelInfo userLevelInfo = userLevelService
							.checkExistingUserLevel(userInfo);

					map.put("msg",
							"Your routine has been start up you can give test from tomorrow");
					map.put("level", userLevelInfo);
				} else {
					map.put("status", Config.ERROR);
					map.put("msg", "Database error occured");
				}
			}
		}
		return map;

	}

	@RequestMapping(value = "/submitRating", method = RequestMethod.POST)
	public Map<String, Object> submitRating(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkRating(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				Map<String, Object> updatedLevelMap = LevelCal.getRatingLevel(
						userLevelInfo, user);

				String levelStr = (String) updatedLevelMap.get("level");
				String subLevel = (String) updatedLevelMap.get("sub_level");
				int totalNoOfDays = (int) updatedLevelMap.get("days");
				if (totalNoOfDays == 0) {
					user.setDate(null);
				}

				int r = userLevelService.updateUserLevel(userInfo,
						userLevelInfo, levelStr, totalNoOfDays, user.getDate(),
						subLevel);
				if (r > 0) {
					userLevelInfo = userLevelService
							.checkExistingUserLevel(userInfo);
					map.put("level", userLevelInfo);
					map.put("msg", updatedLevelMap.get("msg"));
				}
			}
		}
		return map;
	}

	@RequestMapping(value = "/submitProgramme", method = RequestMethod.POST)
	public Map<String, Object> submitProgramme(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkStartTest(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				if (true) {
					LevelResultInfo levelResultInfo = levelResultService
							.checkExistingLevelResult(userLevelInfo);
					int levelResult = 0;
					if (levelResultInfo == null) {
						int result = userLevelService.updateLevelTestProgramme(
								userLevelInfo, user);

						Notifications noti = new Notifications();
						Map<String, Object> notiResponse = noti.checkUserData(
								userLevelInfo, userInfo);
						int response = (int) notiResponse.get("value");
						String m = (String) notiResponse.get("msg");

						if (response == 200) {
							userLevelService.updateNotification(userLevelInfo);
							userService.updateNotificationCount(userInfo);
							notificationService.createNotification(userInfo,
									user, m, user.getDate());
							userLevelInfo = userLevelService
									.checkExistingUserLevel(userInfo);

						}

						userLevelInfo = userLevelService
								.checkExistingUserLevel(userInfo);
						double percent = LevelCal
								.getLevelProgramResult(userLevelInfo);
						levelResult = levelResultService.createLevelResult(
								userLevelInfo, percent, user);
					} else {
						if (levelResultInfo.getLastSubmittionDate().getDate() == user
								.getDate().getDate()) {

						} else {
							int result = userLevelService
									.updateLevelTestProgramme(userLevelInfo,
											user);

							Notifications noti = new Notifications();
							Map<String, Object> notiResponse = noti
									.checkUserData(userLevelInfo, userInfo);
							int response = (int) notiResponse.get("value");
							String m = (String) notiResponse.get("msg");

							if (response == 200) {
								userLevelService
										.updateNotification(userLevelInfo);
								userService.updateNotificationCount(userInfo);
								notificationService.createNotification(
										userInfo, user, m, user.getDate());
								userLevelInfo = userLevelService
										.checkExistingUserLevel(userInfo);

							}

							userLevelInfo = userLevelService
									.checkExistingUserLevel(userInfo);
							double percent = LevelCal
									.getLevelProgramResult(userLevelInfo);
							levelResult = levelResultService.updateLevelResult(
									levelResultInfo, userLevelInfo, percent,
									user);
						}
					}
					if (levelResult > 0) {

						levelResultInfo = levelResultService
								.checkExistingLevelResult(userLevelInfo);

						Gson gson = new Gson();
						String jsonObject = gson.toJson(levelResultInfo);
						LevelResultBean levelResultBean = gson.fromJson(
								jsonObject, LevelResultBean.class);

						if (userLevelInfo.getTotalNumberOfDays() <= userLevelInfo
								.getCompletedNumberOfDays()) {
							Map<String, Object> updatedLevelMap = LevelCal
									.getUpdatedLevel(userLevelInfo,
											levelResultInfo);

							String levelStr = (String) updatedLevelMap
									.get("level");
							String subLevel = null;
							try {
								subLevel = (String) updatedLevelMap
										.get("sub_level");
							} catch (NullPointerException e) {
								// TODO: handle exception.
								e.printStackTrace();
							}
							int totalNoOfDays = (int) updatedLevelMap
									.get("days");
							if (totalNoOfDays == 0) {
								user.setDate(null);
							}
							int r = userLevelService.updateUserLevel(userInfo,
									userLevelInfo, levelStr, totalNoOfDays,
									user.getDate(), subLevel);
							if (r > 0) {
								userLevelInfo = userLevelService
										.checkExistingUserLevel(userInfo);
								map.put("level", userLevelInfo);
								map.put("result", levelResultBean);
								map.put("msg", updatedLevelMap.get("msg"));
							}

						} else {

							map.put("level", userLevelInfo);
							map.put("result", levelResultBean);
							map.put("msg",
									"Thanks for submitting the your programme");
						}
					} else {
						map.put("status", Config.ERROR);
						map.put("msg",
								"You have already submitted test for this day");
					}
				}
			}
		}
		return map;

	}

	@RequestMapping(value = "/checkSubmittion", method = RequestMethod.POST)
	public Map<String, Object> checkSubmittion(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkStartTest(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				if (userLevelInfo.getStartDate().getDate() == user.getDate()
						.getDate()) {
					map.put("status", Config.ERROR);
					map.put("msg", "Start submitting your test from tomorrow");

				} else {
					LevelResultInfo levelResultInfo = levelResultService
							.checkExistingLevelResult(userLevelInfo);
					if (levelResultInfo != null) {
						if (levelResultInfo.getLastSubmittionDate().getDate() == user
								.getDate().getDate()) {
							map.put("status", Config.ERROR);
							map.put("msg",
									"You have already submitted the test for today");
						}
					} else {
						map.put("msg", "Proceed");
					}
				}
			}
		}

		return map;
	}

	@RequestMapping(value = "/updatePayment", method = RequestMethod.POST)
	public Map<String, Object> updatePayment(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkSelfTest(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);

				int result = paymentService.createPayment(userInfo, user);
				if (result > 0) {

					PaymentInfo paymentInfo = paymentService
							.checkExistingPaymentStatus(userInfo, user);
					if (paymentInfo != null) {
						if (paymentInfo.getState().equalsIgnoreCase("approved")) {
							int payResult = userLevelService
									.updateUserLevelPaymentStatus(userLevelInfo);
							if (payResult > 0) {

								customMail.sendPaymentReceivedMailToAdmin(
										userInfo, userLevelInfo);
								customMail.sendPaymentReceivedMailToUser(
										userInfo, userLevelInfo);

								map.put("msg",
										"You payment information has been saved successfully");
							}
						} else {
							map.put("status", Config.ERROR);
							map.put("msg",
									"Your payment information has not been approved");
						}
					} else {
						map.put("status", Config.ERROR);
						map.put("msg", "User does not exits");
					}
				} else {

					map.put("msg", "Error creating payment status");
				}

			}

		}

		return map;

	}

	@RequestMapping(value = "/submitTest", method = RequestMethod.POST)
	public Map<String, Object> submitTest(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String returnMsg = "Thanks for submitting the test";
		map = UserValidations.checkStartTest(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				if (userLevelInfo.getStartDate().getTime() == user.getDate()
						.getTime()) {
					map.put("status", Config.ERROR);
					map.put("msg", "Start submitting your test from tomorrow");

				} else {
					int daysFromStartDate = LevelCal.getDatesDifference(
							userLevelInfo.getStartDate(), user.getDate());
					switch (daysFromStartDate) {
					default:
						LevelResultInfo levelResultInfo = levelResultService
								.checkExistingLevelResult(userLevelInfo);

						if (true) {

							int levelResult = 0;

							if (levelResultInfo == null) {

								int result = userLevelService
										.updateLevelTestSubmittion(
												userLevelInfo, user,
												daysFromStartDate);
								userLevelInfo = userLevelService
										.checkExistingUserLevel(userInfo);
								double percent = LevelCal
										.getLevelResult(userLevelInfo);

								levelResult = levelResultService
										.createLevelResult(userLevelInfo,
												percent, user);

							} else {

								if (levelResultInfo.getLastSubmittionDate()
										.getTime() == user.getDate().getTime()) {

								} else {
									int result = userLevelService
											.updateLevelTestSubmittion(
													userLevelInfo, user,
													daysFromStartDate);
									userLevelInfo = userLevelService
											.checkExistingUserLevel(userInfo);

									Notifications noti = new Notifications();
									Map<String, Object> notiResponse = noti
											.checkUserData(userLevelInfo,
													userInfo);
									int response = (int) notiResponse
											.get("value");
									String m = (String) notiResponse.get("msg");

									if (response == 200) {
										userLevelService
												.updateNotification(userLevelInfo);
										userService
												.updateNotificationCount(userInfo);
										notificationService.createNotification(
												userInfo, user, m,
												user.getDate());
										userLevelInfo = userLevelService
												.checkExistingUserLevel(userInfo);

									}

									double percent = LevelCal
											.getLevelResult(userLevelInfo);

									levelResult = levelResultService
											.updateLevelResult(levelResultInfo,
													userLevelInfo, percent,
													user);
									if (userLevelInfo.getIsExtraResult() == null)
										if (user.getLevelNumber()
												.equalsIgnoreCase("1")
												&& userLevelInfo
														.getCompletedNumberOfDays() >= 15
												&& percent >= 50) {
											userLevelService.updateExtraResult(
													userInfo, userLevelInfo);
											returnMsg = "You are making good progress in following this programme. Perhaps you’d like to add a few more routines to address some specific issues that might be dis-connecting you from your Chit.";
											userLevelInfo = userLevelService
													.checkExistingUserLevel(userInfo);
										}
								}

							}

							if (levelResult > 0) {

								levelResultInfo = levelResultService
										.checkExistingLevelResult(userLevelInfo);

								Gson gson = new Gson();
								String jsonObject = gson
										.toJson(levelResultInfo);
								LevelResultBean levelResultBean = gson
										.fromJson(jsonObject,
												LevelResultBean.class);

								if (userLevelInfo.getTotalNumberOfDays() <= userLevelInfo
										.getCompletedNumberOfDays()) {
									Map<String, Object> updatedLevelMap = LevelCal
											.getUpdatedLevel(userLevelInfo,
													levelResultInfo);

									String levelStr = (String) updatedLevelMap
											.get("level");
									String subLevel = null;
									try {
										subLevel = (String) updatedLevelMap
												.get("sub_level");
									} catch (NullPointerException e) {
										// TODO: handle exception.
										e.printStackTrace();
									}
									int totalNoOfDays = (int) updatedLevelMap
											.get("days");
									if (totalNoOfDays == 0) {
										user.setDate(null);
									}
									int r = userLevelService.updateUserLevel(
											userInfo, userLevelInfo, levelStr,
											totalNoOfDays, user.getDate(),
											subLevel);
									if (r > 0) {
										userLevelInfo = userLevelService
												.checkExistingUserLevel(userInfo);
										map.put("level", userLevelInfo);
										map.put("result", levelResultBean);
										map.put("msg",
												updatedLevelMap.get("msg"));
									}

								} else {

									map.put("level", userLevelInfo);
									map.put("result", levelResultBean);
									map.put("msg", returnMsg);
								}
							} else {
								map.put("status", Config.ERROR);
								map.put("msg",
										"You have already submitted test for this day");
							}

						}
						return map;

					}
				}

			}
		}
		return map;
	}

	@RequestMapping(value = "/levelResult", method = RequestMethod.POST)
	public Map<String, Object> levelResult(@RequestBody UserBean user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkLevelResult(user);
		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				UserLevelInfo userLevelInfo = userLevelService
						.checkExistingUserLevel(userInfo);
				if (userLevelInfo != null) {
					LevelResultInfo levelResultInfo = levelResultService
							.checkExistingLevelResult(userLevelInfo);
					if (levelResultInfo != null) {
						Gson gson = new Gson();
						String jsonObject = gson.toJson(levelResultInfo);
						LevelResultBean levelResultBean = gson.fromJson(
								jsonObject, LevelResultBean.class);

						map.put("result", levelResultBean);
						map.put("level", userLevelInfo);
						map.put("msg", "Your level result");
					} else {
						map.put("status", Config.ERROR);
						map.put("msg", "Result not found!");
					}

				} else {
					map.put("status", Config.ERROR);
					map.put("msg", "User level not found!");
				}

			}
		}
		return map;

	}

	@RequestMapping(value = "/getNotifications", method = RequestMethod.POST)
	public Map<String, Object> getNotifications(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkSelfTest(user);

		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				map.put("notifications",
						notificationService.getNotifications(userInfo));
				map.put("notification_count", userInfo.getNotificationCount());
			}
		}

		return map;

	}

	@RequestMapping(value = "/readNotifications", method = RequestMethod.POST)
	public Map<String, Object> readNotifications(@RequestBody UserBean user) {

		Map<String, Object> map = new HashMap<String, Object>();
		map = UserValidations.checkSelfTest(user);

		String status = (String) map.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return map;
		} else {
			UserInfo userInfo = userService.checkExistingUserId(user);
			if (userInfo == null) {
				map.put("status", Config.ERROR);
				map.put("msg", "User does not exits");
			} else {
				userService.readNotification(userInfo);
				map.put("msg", "Notification read");
			}
		}

		return map;

	}

	@RequestMapping("/sendNotification")
	public Map<String, Object> setNotification() {
		Map<String, Object> map = new HashMap<String, Object>();
		Notifications noti = new Notifications();
		int response = noti
				.sendNotificationToAndroid(
						"hi",
						"fasQjwtw_GM:APA91bElIS2scRIKc8tgW33fR_vkIqrwi5JcLZSirY3H3ljYqGl3qFM8fjnzAuL3_1YvnL3-bX-8paxMdih_zXsXL4H-FncN9YTvCgy1Y53jd6If6wCqY8tMwUBGcg9b3WNFAYfnNPEZ");
		map.put("response", response);
		return map;
	}

}
