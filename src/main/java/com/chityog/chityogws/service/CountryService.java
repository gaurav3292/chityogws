package com.chityog.chityogws.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.country.domain.CountryInfo;
import com.chityog.chityogws.dao.CountryDao;

@Service
public class CountryService {
	
	@Autowired
	private CountryDao countryDao;

	public List<CountryInfo> getCountryList() {
		return countryDao.getCountryList();
	}


}
