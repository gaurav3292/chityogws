package com.chityog.chityogws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.dao.LevelResultDao;
import com.chityog.chityogws.domain.LevelResultInfo;
import com.chityog.chityogws.domain.UserLevelInfo;

@Service
public class LevelResultService {

	@Autowired
	private LevelResultDao levelResultDao;

	public LevelResultInfo checkExistingLevelResult(UserLevelInfo userLevelInfo) {
		return levelResultDao.checkExistingLevelResult(userLevelInfo);
	}

	public int createLevelResult(UserLevelInfo userLevelInfo, double percent) {
		// TODO Auto-generated method stub
		return levelResultDao.createLevelResult(userLevelInfo, percent);
	}

	public int updateLevelResult(LevelResultInfo levelResultInfo,
			UserLevelInfo userLevelInfo, double percent) {
		return levelResultDao.updateLevelResult(levelResultInfo, userLevelInfo,
				percent);
	}

}
