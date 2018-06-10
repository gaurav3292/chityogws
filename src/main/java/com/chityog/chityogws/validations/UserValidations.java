package com.chityog.chityogws.validations;

import java.util.HashMap;
import java.util.Map;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.utils.Config;

public class UserValidations {

	private static String PASSWORD_REGEX = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";

	public static Map<String, Object> validateUser(UserBean user) {

		String status = Config.SUCCESS;

		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getEmail() == null && user.getName() == null
				&& user.getAddress() == null && user.getPhone() == null
				&& user.getPassword() == null) {
			status = Config.ERROR;
			map.put("msg", "All fields are required");
		} else if (user.getEmail() == null || user.getName() == null
				|| user.getAddress() == null || user.getPhone() == null
				|| user.getPassword() == null) {
			status = Config.ERROR;

			if (user.getName() == null) {
				map.put("msg", "Name field is required");

			} else if (user.getEmail() == null) {
				map.put("msg", "Email is required");

			} else if (user.getPhone() == null) {
				map.put("msg", "Phone number is required");

			} else if (user.getAddress() == null) {
				map.put("msg", "Address is required");

			} else if (user.getPassword() == null) {
				map.put("msg", "Password is required");

			} else if (user.getDeviceToken() == null) {
				status = Config.ERROR;
				map.put("msg", "Device token is required");
			} else if (user.getDeviceType() == null) {
				status = Config.ERROR;
				map.put("msg", "Device type is required");
			}

		}

		map.put("status", status);
		return map;

	}

	public static Map<String, Object> validateLogin(UserBean user) {
		// TODO Auto-generated method stub

		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();

		if (user.getEmail() == null) {
			status = Config.ERROR;

			map.put("msg", "Email is required");

		} else if (user.getPassword() == null) {
			status = Config.ERROR;
			map.put("msg", "Password is required");
		} else if (user.getDeviceToken() == null) {
			status = Config.ERROR;
			map.put("msg", "Device token is required");
		} else if (user.getDeviceType() == null) {
			status = Config.ERROR;
			map.put("msg", "Device type is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> validatePassword(UserBean user) {
		// TODO Auto-generated method stub

		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getOldPassword() == null && user.getNewPassword() == null) {

			status = Config.ERROR;

			map.put("msg", "Please fill the required fields");

		} else if (user.getOldPassword() == null
				|| user.getNewPassword() == null) {

			if (user.getOldPassword() == null) {
				status = Config.ERROR;

				map.put("msg", "Old password is required");
			} else {
				status = Config.ERROR;

				map.put("msg", "New password is required");
			}

		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkEmail(UserBean user) {
		// TODO Auto-generated method stub

		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getEmail() == null) {

			status = Config.ERROR;

			map.put("msg", "Email Id is required");

		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> validatePasswordOtp(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getOtp() == null && user.getNewPassword() == null) {

			status = Config.ERROR;

			map.put("msg", "Please fill the required fields");

		} else if (user.getOtp() == null || user.getNewPassword() == null) {

			if (user.getOtp() == null) {
				status = Config.ERROR;

				map.put("msg", "Verification code is required");
			} else {
				status = Config.ERROR;

				map.put("msg", "New password is required");
			}

		}
		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkSelfTest(UserBean user) {
		// TODO Auto-generated method stub

		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserId() == null) {
			status = Config.ERROR;
			map.put("msg", "User Id is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkUserId(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserId() == null) {
			status = Config.ERROR;
			map.put("msg", "User Id is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkEmailVerification(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserId() == null) {
			status = Config.ERROR;
			map.put("msg", "User Id is required");
		} else if (user.getEmail() == null) {
			status = Config.ERROR;
			map.put("msg", "Email Id is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkVerifyEmail(String token, Long userId) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (userId == null) {
			status = Config.ERROR;
			map.put("msg", "User Id is required");
		} else if (token == null) {
			status = Config.ERROR;
			map.put("msg", "Token is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> validateUserProfile(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;

		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getName() == null && user.getAddress() == null
				&& user.getPhone() == null && user.getGender() == null) {
			status = Config.ERROR;
			map.put("msg", "All fields are required");
		} else if (user.getGender() == null || user.getName() == null
				|| user.getAddress() == null || user.getPhone() == null) {
			status = Config.ERROR;

			if (user.getName() == null) {
				map.put("msg", "Name field is required");

			} else if (user.getGender() == null) {
				map.put("msg", "Gender is required");

			} else if (user.getPhone() == null) {
				map.put("msg", "Phone number is required");

			} else if (user.getAddress() == null) {
				map.put("msg", "Address is required");

			}

		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkStartTest(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;

		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserId() == null) {
			status = Config.ERROR;
			map.put("msg", "User id is required");
		} else if (user.getLevelNumber() == null) {
			status = Config.ERROR;
			map.put("msg", "Level is required");
		} else if (user.getDate() == null) {
			status = Config.ERROR;
			map.put("msg", "Date is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkLevelResult(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserId() == null) {
			status = Config.ERROR;
			map.put("msg", "User id is required");
		} else if (user.getLevelNumber() == null) {
			status = Config.ERROR;
			map.put("msg", "Level is required");
		}
		map.put("status", status);
		return map;
	}

	public static Map<String, Object> checkRating(UserBean user) {
		// TODO Auto-generated method stub
		String status = Config.SUCCESS;

		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getUserId() == null) {
			status = Config.ERROR;
			map.put("msg", "User id is required");
		} else if (user.getLevelNumber() == null) {
			status = Config.ERROR;
			map.put("msg", "Level is required");
		} else if (user.getLineRating() == null) {
			status = Config.ERROR;
			map.put("msg", "Rating order is required");
		}

		map.put("status", status);
		return map;
	}

	public static Map<String, Object> validateResetPassword(UserBean user) {
		// TODO Auto-generated method stub

		String status = Config.SUCCESS;
		Map<String, Object> map = new HashMap<String, Object>();
		if (!user.getNewPassword().equalsIgnoreCase(user.getConfirmPassword())) {

			status = Config.ERROR;

			map.put("msg", "Passwords do not match");

		} else if (checkPassword(user.getNewPassword())) {

		} else {
			status = Config.ERROR;

			map.put("msg",
					"Password should contains eight characters including one uppercase letter, one lowercase letter, and one number or special character");
		}

		map.put("status", status);
		return map;
	}

	public static boolean checkPassword(String password) {
		Boolean b = password.matches(PASSWORD_REGEX);
		return b;

	}

}
