package com.chityog.chityogws.validations;

import java.util.HashMap;
import java.util.Map;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.utils.Config;

public class UserValidations {

	public static Map<String, Object> validateUser(UserBean user) {

		String status = Config.SUCCESS;

		Map<String, Object> map = new HashMap<String, Object>();
		if (user.getEmail() == null && user.getName() == null
				&& user.getAddress() == null && user.getPhone() == null) {
			status = Config.ERROR;
			map.put("msg", "All fields are required");
		} else if (user.getEmail() == null || user.getName() == null
				|| user.getAddress() == null || user.getPhone() == null) {
			status = Config.ERROR;

			if (user.getName() == null) {
				map.put("msg", "Name field is required");

			} else if (user.getEmail() == null) {
				map.put("msg", "Email filed is required");

			} else if (user.getPhone() == null) {
				map.put("msg", "Phone number is required");

			} else if (user.getAddress() == null) {
				map.put("msg", "Address field is required");

			}

		} else {
			if (user.getEmail().length() > 0
					&& user.getEmail().matches(Config.EMAIL_REGEX)) {

			} else {
				status = Config.ERROR;
				map.put("msg", "Invalid email address");
			}
		}

		map.put("status", status);
		return map;

	}

}
