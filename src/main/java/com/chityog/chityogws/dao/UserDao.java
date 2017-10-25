package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.ForgotPasswordInfo;
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
						"insert into user (NAME,EMAIL,PHONE,ADDRESS,GENDER,PASSWORD,DEVICE_TYPE,COUNTRY) values (:name,:email, :phone, :address, :gender, :password, :deviceType, :country)");

		query.setString("name", user.getName());
		query.setString("email", user.getEmail());
		query.setString("phone", user.getPhone());
		query.setString("address", user.getAddress());
		query.setString("password", MD5.encode(user.getPassword()));
		query.setString("gender", user.getGender());
		query.setString("deviceType", user.getDeviceType());
		query.setString("country", user.getCountry());

		query.executeUpdate();
	}

	public UserInfo getUserId(UserBean user) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from UserInfo u where u.userId = :id");
		query.setLong("id", user.getUserId());
		return (UserInfo) query.uniqueResult();
	}

	public int updateUserPassword(UserBean user) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.password = :password where u.userId = :id");
		query.setLong("id", user.getUserId());
		query.setString("password", MD5.encode(user.getNewPassword()));
		query.executeUpdate();
		return query.executeUpdate();
	}

	public int updateRandomPassword(UserInfo userInfo,
			ForgotPasswordInfo forgotPasswordInfo, String randomStr) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  ForgotPasswordInfo f SET f.forgotPasswordCode = :code where f.forgotPasswordId = :fid and userInfo.userId = :uid");
		query.setString("code", randomStr);
		query.setLong("fid", forgotPasswordInfo.getForgotPasswordId());
		query.setLong("uid", userInfo.getUserId());
		query.executeUpdate();
		return query.executeUpdate();

	}

	public ForgotPasswordInfo checkExistingCode(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM ForgotPasswordInfo where userInfo.userId = :userId");
		query.setLong("userId", userInfo.getUserId());
		return (ForgotPasswordInfo) query.uniqueResult();
	}

	public int createNewRandomPassword(UserInfo userInfo, String randomStr) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into forgot_password (USER_ID,FORGOT_PASSWORD_CODE) values (:userId,:forgotPasswordCode)");

		query.setLong("userId", userInfo.getUserId());
		query.setString("forgotPasswordCode", randomStr);
		return query.executeUpdate();
	}

}
