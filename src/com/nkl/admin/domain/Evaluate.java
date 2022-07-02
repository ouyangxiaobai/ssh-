package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Evaluate extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2314005237608205836L;
	private int evaluate_id; // 
	private int evaluate_year; // 
	private int evaluate_year_half; // 1-上半年 2-下半年
	private int teacher_id; // 
	private int student_id; // 
	private int quality_score; // 
	private int post_score; // 
	private int skill_score; // 
	private int coach_score; // 
	private int plan_score; // 
	private int effect_score; // 
	private int vivid_score; // 
	private int knowledge_score; // 

	private String teacher_name; //
	private String student_name; // 
	private double quality_score_avg; // 
	private double post_score_avg; // 
	private double skill_score_avg; // 
	private double coach_score_avg; // 
	private double plan_score_avg; // 
	private double effect_score_avg; // 
	private double vivid_score_avg; // 
	private double knowledge_score_avg; // 
	private double sum_avg; // 
	
	private String ids;
	private String random;

	public String getEvaluate_year_halfDesc(){
		switch (evaluate_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}
	
	public void setEvaluate_id(int evaluate_id){
		this.evaluate_id=evaluate_id;
	}

	public int getEvaluate_id(){
		return evaluate_id;
	}

	public void setEvaluate_year(int evaluate_year){
		this.evaluate_year=evaluate_year;
	}

	public int getEvaluate_year(){
		return evaluate_year;
	}

	public void setEvaluate_year_half(int evaluate_year_half){
		this.evaluate_year_half=evaluate_year_half;
	}

	public int getEvaluate_year_half(){
		return evaluate_year_half;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getQuality_score() {
		return quality_score;
	}

	public void setQuality_score(int quality_score) {
		this.quality_score = quality_score;
	}

	public int getPost_score() {
		return post_score;
	}

	public void setPost_score(int post_score) {
		this.post_score = post_score;
	}

	public int getSkill_score() {
		return skill_score;
	}

	public void setSkill_score(int skill_score) {
		this.skill_score = skill_score;
	}

	public int getCoach_score() {
		return coach_score;
	}

	public void setCoach_score(int coach_score) {
		this.coach_score = coach_score;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public double getQuality_score_avg() {
		return quality_score_avg;
	}

	public void setQuality_score_avg(double quality_score_avg) {
		this.quality_score_avg = quality_score_avg;
	}

	public double getPost_score_avg() {
		return post_score_avg;
	}

	public void setPost_score_avg(double post_score_avg) {
		this.post_score_avg = post_score_avg;
	}

	public double getSkill_score_avg() {
		return skill_score_avg;
	}

	public void setSkill_score_avg(double skill_score_avg) {
		this.skill_score_avg = skill_score_avg;
	}

	public double getCoach_score_avg() {
		return coach_score_avg;
	}

	public void setCoach_score_avg(double coach_score_avg) {
		this.coach_score_avg = coach_score_avg;
	}

	public double getSum_avg() {
		return sum_avg;
	}

	public void setSum_avg(double sum_avg) {
		this.sum_avg = sum_avg;
	}

	public int getPlan_score() {
		return plan_score;
	}

	public int getEffect_score() {
		return effect_score;
	}

	public int getVivid_score() {
		return vivid_score;
	}

	public int getKnowledge_score() {
		return knowledge_score;
	}

	public void setPlan_score(int plan_score) {
		this.plan_score = plan_score;
	}

	public void setEffect_score(int effect_score) {
		this.effect_score = effect_score;
	}

	public void setVivid_score(int vivid_score) {
		this.vivid_score = vivid_score;
	}

	public void setKnowledge_score(int knowledge_score) {
		this.knowledge_score = knowledge_score;
	}

	public double getPlan_score_avg() {
		return plan_score_avg;
	}

	public double getEffect_score_avg() {
		return effect_score_avg;
	}

	public double getVivid_score_avg() {
		return vivid_score_avg;
	}

	public double getKnowledge_score_avg() {
		return knowledge_score_avg;
	}

	public void setPlan_score_avg(double plan_score_avg) {
		this.plan_score_avg = plan_score_avg;
	}

	public void setEffect_score_avg(double effect_score_avg) {
		this.effect_score_avg = effect_score_avg;
	}

	public void setVivid_score_avg(double vivid_score_avg) {
		this.vivid_score_avg = vivid_score_avg;
	}

	public void setKnowledge_score_avg(double knowledge_score_avg) {
		this.knowledge_score_avg = knowledge_score_avg;
	}

}
