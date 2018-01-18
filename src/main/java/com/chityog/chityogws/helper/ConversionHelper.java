package com.chityog.chityogws.helper;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.bean.UserLevelBean;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;

public class ConversionHelper {

	public static UserBean convertUserInfoToUserBean(UserInfo userInfo,
			UserLevelInfo userLevelInfo) {

		UserBean userBean = new UserBean();
		UserLevelBean level = null;
		if(userLevelInfo!=null){
			level = new UserLevelBean();
			level.setUserLevelId(userLevelInfo.getUserLevelId());
			level.setUserLevel(userLevelInfo.getUserLevel());
			level.setTotalNumberOfDays(userLevelInfo.getTotalNumberOfDays());
			level.setCompletedNumberOfDays(userLevelInfo.getCompletedNumberOfDays());
			level.setSkippedNumberOfDays(userLevelInfo.getSkippedNumberOfDays());
			level.setStartDate(userLevelInfo.getStartDate());
			level.setIsResult(userLevelInfo.getIsResult());
			level.setNumberOfTrue(userLevelInfo.getNumberOfTrue());
			level.setTotalNumberOfQuestions(userLevelInfo.getTotalNumberOfQuestions());
			level.setAttendedNumberOfDays(userLevelInfo.getAttendedNumberOfDays());
			level.setUserSubLevel(userLevelInfo.getUserSubLevel());
		}
		

		userBean.setLevel(level);
		userBean.setAddress(userInfo.getAddress());
		userBean.setCountry(userInfo.getCountry());
		userBean.setDeviceType(userInfo.getDeviceType());
		userBean.setEmail(userInfo.getEmail());
		userBean.setGender(userInfo.getGender());
		userBean.setName(userInfo.getName());
		userBean.setNumberOfTrue(userInfo.getNumberOfTrue());
		userBean.setPassword(userInfo.getPassword());
		userBean.setPhone(userInfo.getPhone());
		userBean.setProfilePic(userInfo.getProfilePic());
		userBean.setUserId(userInfo.getUserId());
		userBean.setToken(userInfo.getToken());
		userBean.setIsEmailVerify(userInfo.getIsEmailVerify());

		return userBean;
	}

}
