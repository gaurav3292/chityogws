package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.LevelResultInfo;
import com.chityog.chityogws.domain.UserLevelInfo;

@Repository
@Transactional
public class LevelResultDao {

	@Autowired
	private SessionFactory sessionFactory;

	public LevelResultInfo checkExistingLevelResult(UserLevelInfo userLevelInfo) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM LevelResultInfo where userLevelInfo.userLevelId = :userLevelId");
		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		return (LevelResultInfo) query.uniqueResult();
	}

	public int createLevelResult(UserLevelInfo userLevelInfo, double percent, UserBean user) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into level_result (USER_LEVEL_ID,LEVEL_RESULT_PERCENT,LAST_SUBMITTION_DATE) values (:userLevelId,:percent,:lastSubmittionDate)");

		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		query.setDouble("percent", percent);
		query.setDate("lastSubmittionDate", user.getDate());
		return query.executeUpdate();
	}

	public int updateLevelResult(LevelResultInfo levelResultInfo,UserLevelInfo userLevelInfo,double percent, UserBean user){
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  LevelResultInfo l SET l.levelResultPercent = :levelResultPercent , l.lastSubmittionDate = :lastSubmittionDate where l.levelResultId = :levelResultId and  userLevelInfo.userLevelId = :userLevelId");
		query.setDouble("levelResultPercent", percent);
		query.setLong("levelResultId", levelResultInfo.getLevelResultId());
		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		query.setDate("lastSubmittionDate", user.getDate());
		return query.executeUpdate();
	}
}
