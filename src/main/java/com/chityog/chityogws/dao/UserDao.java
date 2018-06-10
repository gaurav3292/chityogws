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
import com.chityog.chityogws.utils.Config;

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
						"insert into user (NAME,EMAIL,PHONE,ADDRESS,GENDER,PASSWORD,DEVICE_TYPE,COUNTRY,DEVICE_TOKEN) values (:name,:email, :phone, :address, :gender, :password, :deviceType, :country, :deviceToken)");

		query.setString("name", user.getName());
		query.setString("email", user.getEmail());
		query.setString("phone", user.getPhone());
		query.setString("address", user.getAddress());
		query.setString("password", MD5.encode(user.getPassword()));
		query.setString("gender", user.getGender());
		query.setString("deviceType", user.getDeviceType());
		query.setString("country", user.getCountry());
		query.setString("deviceToken", user.getDeviceToken());

		query.executeUpdate();
	}

	public UserInfo getUserId(UserBean user) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from UserInfo u where u.userId = :id");
		query.setLong("id", user.getUserId());
		return (UserInfo) query.uniqueResult();
	}

	public UserInfo getUserId(Long userId) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from UserInfo u where u.userId = :id");
		query.setLong("id", userId);
		return (UserInfo) query.uniqueResult();
	}

	public int updateUserDevice(UserBean user, UserInfo userInfo) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.deviceType = :deviceType, u.deviceToken = :deviceToken  where u.userId = :id");
		query.setLong("id", userInfo.getUserId());
		query.setString("deviceType", user.getDeviceType());
		query.setString("deviceToken", user.getDeviceToken());
		query.executeUpdate();
		return query.executeUpdate();
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
	
	public ForgotPasswordInfo checkExistingCode(UserBean userBean) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM ForgotPasswordInfo where forgotPasswordCode = :forgotPasswordCode");
		query.setString("forgotPasswordCode", userBean.getToken());
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

	public int updateTrues(UserInfo userInfo, int NoOfTrue) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.numberOfTrue = :trues where u.userId = :id");
		query.setLong("id", userInfo.getUserId());
		query.setInteger("trues", NoOfTrue);
		query.executeUpdate();
		return query.executeUpdate();
	}

	public int updateToken(UserInfo userInfo, String token) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.token = :token where u.userId = :uid");
		query.setString("token", token);
		query.setLong("uid", userInfo.getUserId());
		query.executeUpdate();
		return query.executeUpdate();
	}

	public int updateNotificationCount(UserInfo userInfo) {

		int count = userInfo.getNotificationCount();
		count = count + 1;
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.notificationCount = :notificationCount, u.isNotificationRead = :isNotificationRead where u.userId = :uid");
		query.setInteger("notificationCount", count);
		query.setBoolean("isNotificationRead", false);
		query.setLong("uid", userInfo.getUserId());
		query.executeUpdate();
		return query.executeUpdate();

	}

	public int readNotification(UserInfo userInfo) {

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.notificationCount = :notificationCount, u.isNotificationRead = :isNotificationRead where u.userId = :uid");
		query.setInteger("notificationCount", 0);
		query.setBoolean("isNotificationRead", true);
		query.setLong("uid", userInfo.getUserId());
		query.executeUpdate();
		return query.executeUpdate();
	}

	public int updateUserEmailVerification(UserInfo userInfo) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserInfo u SET u.isEmailVerify = :str where u.userId = :uid");
		query.setString("str", userInfo.getIsEmailVerify());
		query.setLong("uid", userInfo.getUserId());
		query.executeUpdate();
		return query.executeUpdate();
	}

	public int updateProfile(UserInfo userInfo) {
		// TODO Auto-generated method stub

		if (userInfo.getProfilePic() == null) {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE  UserInfo u SET u.name = :name , u.address = :address , u.phone = :phone , u.gender = :gender  where u.userId = :uid");
			query.setString("name", userInfo.getName());
			query.setString("phone", userInfo.getPhone());
			query.setString("address", userInfo.getAddress());
			query.setString("gender", userInfo.getGender());
			query.setLong("uid", userInfo.getUserId());
			query.executeUpdate();
			return query.executeUpdate();
		} else {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE  UserInfo u SET u.name = :name , u.address = :address , u.phone = :phone , u.gender = :gender , u.profilePic = :profilePic  where u.userId = :uid");
			query.setString("name", userInfo.getName());
			query.setString("phone", userInfo.getPhone());
			query.setString("address", userInfo.getAddress());
			query.setString("gender", userInfo.getGender());

			query.setString("profilePic", userInfo.getProfilePic());
			query.setLong("uid", userInfo.getUserId());
			query.executeUpdate();
			return query.executeUpdate();
		}

	}

}
