package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	public int createLevelResult(UserLevelInfo userLevelInfo, double percent) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into level_result (USER_LEVEL_ID,LEVEL_RESULT_PERCENT) values (:userLevelId,:percent)");

		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		query.setDouble("percent", percent);
		return query.executeUpdate();
	}

	public int updateLevelResult(LevelResultInfo levelResultInfo,UserLevelInfo userLevelInfo,double percent){
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  LevelResultInfo l SET l.levelResultPercent = :levelResultPercent where l.levelResultId = :levelResultId and  userLevelInfo.userLevelId = :userLevelId");
		query.setDouble("levelResultPercent", percent);
		query.setLong("levelResultId", levelResultInfo.getLevelResultId());
		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		return query.executeUpdate();
	}
}
