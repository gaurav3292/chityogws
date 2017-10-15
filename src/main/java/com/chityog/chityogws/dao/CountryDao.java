package com.chityog.chityogws.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.country.domain.CountryInfo;

@Repository
@Transactional
public class CountryDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<CountryInfo> getCountryList() {
		return sessionFactory.getCurrentSession()
				.createQuery("from CountryInfo").list();
	}

}
