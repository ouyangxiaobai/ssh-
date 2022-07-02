package com.nkl.admin.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.nkl.admin.dao.ClazzDao;
import com.nkl.admin.dao.EvaluateDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Evaluate;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;

public class AdminManager {

	ClazzDao clazzDao = new ClazzDao();
	EvaluateDao evaluateDao = new EvaluateDao();
	UserDao userDao = new UserDao();
	

	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user, conn);

		BaseDao.closeDB(null, null, conn);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {
		Connection conn = BaseDao.getConnection();
		user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: addUserBatch
	 * @Description: 添加用户
	 * @param List<Score>
	 * @return void
	 * @throws SQLException 
	 */
	public void addUserBatch(List<User> users) throws SQLException {
		Connection conn = BaseDao.getConnection();
		conn.setAutoCommit(false);
		for (int i = 0; i < users.size(); i++) {
			userDao.addUser(users.get(i), conn);
			if ((i+1) % 500==0) {//每500行提交一次
				conn.commit();
			}
		}
		conn.commit();
		conn.setAutoCommit(true);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listClazzs
	 * @Description: 班级查询
	 * @param clazz
	 * @return List<Clazz>
	 */
	public List<Clazz> listClazzs(Clazz clazz, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = clazzDao.listClazzsCount(clazz, conn);
		}
		List<Clazz> clazzs = clazzDao.listClazzs(clazz, conn);

		BaseDao.closeDB(null, null, conn);
		return clazzs;
	}

	/**
	 * @Title: queryClazz
	 * @Description: 班级查询
	 * @param clazz
	 * @return Clazz
	 */
	public Clazz queryClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		Clazz _clazz = clazzDao.getClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
		return _clazz;
	}

	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @param clazz
	 * @return void
	 */
	public void addClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.addClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateClazz
	 * @Description: 更新班级信息
	 * @param clazz
	 * @return void
	 */
	public void updateClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.updateClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delClazzs
	 * @Description: 删除班级信息
	 * @param clazz
	 * @return void
	 */
	public void delClazzs(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.delClazzs(clazz.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listEvaluates
	 * @Description: 教务评定查询
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate> listEvaluates(Evaluate evaluate, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate, conn);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate, conn);

		BaseDao.closeDB(null, null, conn);
		return evaluates;
	}
	
	/**
	 * @Title: listEvaluatesSum
	 * @Description: 教务评定平均成绩查询
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate> listEvaluatesSum(Evaluate evaluate,int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate, conn);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluatesSum(evaluate, conn);

		BaseDao.closeDB(null, null, conn);
		return evaluates;
	}
	
	/**
	 * @Title: queryEvaluate
	 * @Description: 教务评定查询
	 * @param evaluate
	 * @return Evaluate
	 */
	public Evaluate queryEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		Evaluate _evaluate = evaluateDao.getEvaluate(evaluate, conn);
		BaseDao.closeDB(null, null, conn);
		return _evaluate;
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 添加教务评定
	 * @param evaluate
	 * @return void
	 */
	public void addEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		evaluateDao.addEvaluate(evaluate, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateEvaluate
	 * @Description: 更新教务评定信息
	 * @param evaluate
	 * @return void
	 */
	public void updateEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		evaluateDao.updateEvaluate(evaluate, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delEvaluates
	 * @Description: 删除教务评定信息
	 * @param evaluate
	 * @return void
	 */
	public void delEvaluates(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		evaluateDao.delEvaluates(evaluate.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
}
