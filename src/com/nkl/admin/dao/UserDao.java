package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class UserDao {

	public int addUser(User user, Connection conn){
		String sql = "INSERT INTO user(user_id,user_name,user_pass,real_name,user_sex,user_age,clazz_id,reg_date,user_type,user_question,user_answer,note,user_score,user_gscore) values(null,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			user.getUser_name(),
			user.getUser_pass(),
			user.getReal_name(),
			user.getUser_sex(),
			user.getUser_age(),
			user.getClazz_id(),
			user.getReg_date(),
			user.getUser_type(),
			user.getUser_question(),
			user.getUser_answer(),
			user.getNote(),
			user.getUser_score(),
			user.getUser_gscore()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}
	
	public int delUser(String user_id, Connection conn){
		String sql = "DELETE FROM score WHERE user_id=?";

		Object[] params = new Object[] { new Integer(user_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delUsers(String[] user_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <user_ids.length; i++) {
			sBuilder.append("?");
			if (i !=user_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM user WHERE user_id IN(" +sBuilder.toString()+")";

		Object[] params = user_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateUser(User user, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE user SET user_id = " + user.getUser_id() +" ");
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" ,user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" ,real_name ='" + user.getReal_name() +"' ");
		}
		if (user.getUser_sex()!=0) {
			sBuilder.append(" ,user_sex =" + user.getUser_sex() +" ");
		}
		if (user.getUser_age()!=0) {
			sBuilder.append(" ,user_age =" + user.getUser_age() +" ");
		}
		if (user.getClazz_id()!=0) {
			sBuilder.append(" ,clazz_id =" + user.getClazz_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_question())) {
			sBuilder.append(" ,user_question ='" + user.getUser_question() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_answer())) {
			sBuilder.append(" ,user_answer ='" + user.getUser_answer() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_score())) {
			sBuilder.append(" ,user_score ='" + user.getUser_score() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_gscore())) {
			sBuilder.append(" ,user_gscore ='" + user.getUser_gscore() +"' ");
		}

		sBuilder.append("where user_id = " + user.getUser_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public User getUser(User user, Connection conn){
		User _user=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT u.*,c.clazz_name from user u left join clazz c on u.clazz_id=c.clazz_id WHERE 1=1");
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name ='" + user.getUser_name() +"' ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and u.user_type =" + user.getUser_type() +" ");
		}

		List<Object> list = BaseDao.executeQuery(User.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _user = (User)list.get(0);
		}
		return _user;
	}

	public List<User>  listUsers(User user, Connection conn){
		List<User> users = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT u.*,c.clazz_name from user u left join clazz c on u.clazz_id=c.clazz_id WHERE 1=1");

		if (user.getUser_id()!=0) {
			sBuilder.append(" and u.user_id = " + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and u.user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + user.getReal_name() +"%' ");
		}
		if (user.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id =" + user.getClazz_id() +" ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and u.user_type =" + user.getUser_type() +" ");
		}
		if (user.getCourse_id()!=0) {
			sBuilder.append(" and u.clazz_id in (select p.clazz_id from plan p where p.course_id= " + user.getCourse_id());
			sBuilder.append(" and p.plan_year= " + user.getScore_year() +" and p.plan_year_half= " + user.getScore_year_half() +") ");
		}
		
		sBuilder.append(" and u.user_type!=3 order by u.user_id asc) t");

		if (user.getStart() != -1) {
			sBuilder.append(" limit " + user.getStart() + "," + user.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(User.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			users = new ArrayList<User>();
			for (Object object : list) {
				users.add((User)object);
			}
		}
		return users;
	}

	public int  listUsersCount(User user, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM user u WHERE 1=1");

		if (user.getUser_id()!=0) {
			sBuilder.append(" and u.user_id = " + user.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and u.user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + user.getReal_name() +"%' ");
		}
		if (user.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id =" + user.getClazz_id() +" ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and u.user_type =" + user.getUser_type() +" ");
		}
		if (user.getCourse_id()!=0) {
			sBuilder.append(" and u.clazz_id in (select p.clazz_id from plan p where p.course_id= " + user.getCourse_id());
			sBuilder.append(" and p.plan_year= " + user.getScore_year() +" and p.plan_year_half= " + user.getScore_year_half() +") ");
		}
		
		sBuilder.append(" and u.user_type!=3 ");

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
