package com.chityog.chityogws.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.service.CountryService;
import com.chityog.chityogws.utils.Config;
import com.chityog.chityogws.validations.UserValidations;

@RestController
public class Controller {

	@Autowired
	private CountryService countryService;

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
		Map<String, Object> errorMap = UserValidations.validateUser(user);
		String status = (String) errorMap.get("status");
		if (status.equalsIgnoreCase(Config.ERROR)) {
			return errorMap;
		} else {
			return errorMap;
		}

	}

}
