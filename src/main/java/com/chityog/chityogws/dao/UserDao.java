package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.security.MD5;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public UserInfo getUser(UserBean user) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from UserInfo u where u.email = :email");
		query.setString("email", user.getEmail());
		return (UserInfo) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public UserInfo getUserPhone(UserBean user) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from UserInfo u where u.phone = :phone");
		query.setString("phone", user.getPhone());
		return (UserInfo) query.uniqueResult();
	}


	@SuppressWarnings("unchecked")
	public void createUser(UserBean user) {
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into user (NAME,EMAIL,PHONE,ADDRESS,GENDER,PASSWORD) values (:name,:email, :phone, :address, :gender, :password)");

		query.setString("name", user.getName());
		query.setString("email", user.getEmail());
		query.setString("phone", user.getPhone());
		query.setString("address", user.getAddress());
		query.setString("password", MD5.encode(user.getPassword()));
		query.setString("gender", "M");
		query.executeUpdate();
	}

}
