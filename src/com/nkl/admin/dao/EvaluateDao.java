package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Evaluate;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class EvaluateDao {

	public int addEvaluate(Evaluate evaluate, Connection conn){
		String sql = "INSERT INTO evaluate(evaluate_id,evaluate_year,evaluate_year_half,teacher_id,student_id,quality_score,post_score," +
				"skill_score,coach_score,plan_score,effect_score,vivid_score,knowledge_score) values(null,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			evaluate.getEvaluate_year(),
			evaluate.getEvaluate_year_half(),
			evaluate.getTeacher_id(),
			evaluate.getStudent_id(),
			evaluate.getQuality_score(),
			evaluate.getPost_score(),
			evaluate.getSkill_score(),
			evaluate.getCoach_score(),
			evaluate.getPlan_score(),
			evaluate.getEffect_score(),
			evaluate.getVivid_score(),
			evaluate.getKnowledge_score()
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delEvaluate(String evaluate_id, Connection conn){
		String sql = "DELETE FROM evaluate WHERE evaluate_id=?";

		Object[] params = new Object[] { new Integer(evaluate_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delEvaluates(String[] evaluate_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <evaluate_ids.length; i++) {
			sBuilder.append("?");
			if (i !=evaluate_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM evaluate WHERE evaluate_id IN(" +sBuilder.toString()+")";

		Object[] params = evaluate_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateEvaluate(Evaluate evaluate, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE evaluate SET evaluate_id = " + evaluate.getEvaluate_id() +" ");
		sBuilder.append("where evaluate_id = " + evaluate.getEvaluate_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Evaluate getEvaluate(Evaluate evaluate, Connection conn){
		Evaluate _evaluate=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT p.*,u1.real_name teacher_name,u2.real_name student_name FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id ");
		sBuilder.append("  join user u2 on p.student_id=u2.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Evaluate.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _evaluate = (Evaluate)list.get(0);
		}
		return _evaluate;
	}

	public List<Evaluate>  listEvaluates(Evaluate evaluate, Connection conn){
		List<Evaluate> evaluates = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT p.*,u1.real_name teacher_name,u2.real_name student_name FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id ");
		sBuilder.append("  join user u2 on p.student_id=u2.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and u1.real_name like '%" + evaluate.getTeacher_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(evaluate.getStudent_name())) {
			sBuilder.append(" and u2.real_name like '%" + evaluate.getStudent_name() +"%' ");
		}
		sBuilder.append(" order by evaluate_id asc) t");

		if (evaluate.getStart() != -1) {
			sBuilder.append(" limit " + evaluate.getStart() + "," + evaluate.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Evaluate.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			evaluates = new ArrayList<Evaluate>();
			for (Object object : list) {
				evaluates.add((Evaluate)object);
			}
		}
		return evaluates;
	}

	public int  listEvaluatesCount(Evaluate evaluate, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id ");
		sBuilder.append("  join user u2 on p.student_id=u2.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and u1.real_name like '%" + evaluate.getTeacher_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(evaluate.getStudent_name())) {
			sBuilder.append(" and u2.real_name like '%" + evaluate.getStudent_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public List<Evaluate>  listEvaluatesSum(Evaluate evaluate, Connection conn){
		List<Evaluate> evaluates = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT p.evaluate_year,p.evaluate_year_half,u1.real_name teacher_name, ");
		sBuilder.append("       avg(quality_score) quality_score_avg, ");
		sBuilder.append("       avg(post_score) post_score_avg, ");
		sBuilder.append("       avg(skill_score) skill_score_avg, ");
		sBuilder.append("       avg(coach_score) coach_score_avg, ");
		sBuilder.append("       avg(plan_score) plan_score_avg, ");
		sBuilder.append("       avg(effect_score) effect_score_avg, ");
		sBuilder.append("       avg(vivid_score) vivid_score_avg, ");
		sBuilder.append("       avg(knowledge_score) knowledge_score_avg, ");
		sBuilder.append("       avg(quality_score + post_score + skill_score + coach_score + plan_score + effect_score + vivid_score + knowledge_score) sum_avg ");
		sBuilder.append("  FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and u1.real_name like '%" + evaluate.getTeacher_name() +"%' ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}
		sBuilder.append(" group by p.evaluate_year,p.evaluate_year_half,u1.real_name ");
		sBuilder.append(" order by p.evaluate_year,p.evaluate_year_half,u1.real_name ) t");

		if (evaluate.getStart() != -1) {
			sBuilder.append(" limit " + evaluate.getStart() + "," + evaluate.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Evaluate.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			evaluates = new ArrayList<Evaluate>();
			for (Object object : list) {
				evaluates.add((Evaluate)object);
			}
		}
		return evaluates;
	}
	
	public int  listEvaluatesSumCount(Evaluate evaluate, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and u1.real_name like '%" + evaluate.getTeacher_name() +"%' ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}
		sBuilder.append(" group by p.evaluate_year,p.evaluate_year_half,u1.real_name ");

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
