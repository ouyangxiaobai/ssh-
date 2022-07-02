package com.nkl.admin.action;

import java.util.Date;
import java.util.List;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Evaluate;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;

public class AdminAction  extends BaseAction {

	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();
	
	//抓取页面参数
	User paramsUser;
	Clazz paramsClazz;
	Evaluate paramsEvaluate;
	
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("编辑成功", "modifyInfo.jsp");
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			//paramsUser.setUser_pass(Md5.makeMd5(paramsUser.getUser_pass()));
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: queryQuestion
	 * @Description: 根据用户名查找密保问题
	 * @return String
	 */
	public String inputUserName(){
		return "inputUserName";
	}
	public String queryQuestion(){
		try {
			 //得到账号信息
			User user = adminManager.queryUser(paramsUser);
			if (user==null) {
				tip = "该用户名不存在!";
				Param.setAttribute("user_name", paramsUser.getUser_name());
				return "inputUserName";
			}
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			tip = "查找用户名异常!";
			Param.setAttribute("user_name", paramsUser.getUser_name());
			return "inputUserName";
		}
		
		return "inputUserAnswer";
	}
	
	/**
	 * @Title: validAnswer
	 * @Description: 验证密保问题
	 * @return String
	 */
	public String validAnswer(){
		try {
			 //得到账号信息
			String answer = paramsUser.getUser_answer();
			User user = adminManager.queryUser(paramsUser);
			if (!answer.equals(user.getUser_answer())) {
				tip = "密保答案错误!";
				Param.setAttribute("user_answer", answer);
				Param.setAttribute("user", user);
				return "inputUserAnswer";
			}
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			tip = "密保答案错误!";
			Param.setAttribute("user_answer", paramsUser.getUser_answer());
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "inputUserAnswer";
		}
		
		return "resetPass";
	}
	
	/**
	 * @Title: resetPass
	 * @Description: 重置密码
	 * @return String
	 */
	public String resetPass(){
		try {
			 //重置密码
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip = "密码重置异常!";
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "resetPass";
		}
		setSuccessTip("密码重置成功", "login.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询学生
	 * @return String
	 */
	public String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查看学生信息
			paramsUser.setUser_type(1);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询学生列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加学生页面
	 * @return String
	 */
	public String addUserShow(){
		//查询班级字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);
		
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加学生
	 * @return String
	 */
	public String addUser(){
		try {
			//检查学生是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该学号已经存在！";
				Param.setAttribute("user", paramsUser);
				
				//查询班级字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				return "userEdit";
			}
			
			 //添加学生
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑学生
	 * @return String
	 */
	public String editUser(){
		try {
			 //得到学生
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑学生
	 * @return String
	 */
	public String saveUser(){
		try {
			 //保存编辑学生
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除学生
	 * @return String
	 */
	public String delUsers(){
		try {
			 //删除学生
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listTeachers
	 * @Description: 查询教师
	 * @return String
	 */
	public String listTeachers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(2);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询教师列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "main.jsp");
			return "infoTip";
		}
		
		return "teacherShow";
	}
	
	/**
	 * @Title: addTeacherShow
	 * @Description: 显示添加教师页面
	 * @return String
	 */
	public String addTeacherShow(){
		return "teacherEdit";
	}
	
	/**
	 * @Title: addTeacher
	 * @Description: 添加教师
	 * @return String
	 */
	public String addTeacher(){
		try {
			//检查登录名是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "teacherEdit";
			}
			 //添加教师
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("添加教师异常", "Admin_listTeachers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editTeacher
	 * @Description: 编辑教师
	 * @return String
	 */
	public String editTeacher(){
		try {
			 //得到教师
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "Admin_listTeachers.action");
			return "infoTip";
		}
		
		return "teacherEdit";
	}
	
	/**
	 * @Title: saveTeacher
	 * @Description: 保存编辑教师
	 * @return String
	 */
	public String saveTeacher(){
		try {
			 //保存编辑教师
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delTeachers
	 * @Description: 删除教师
	 * @return String
	 */
	public String delTeachers(){
		try {
			 //删除教师
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除教师成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("删除教师异常", "Admin_listTeachers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listClazzs
	 * @Description: 查询班级
	 * @return String
	 */
	public String listClazzs(){
		try {
			if (paramsClazz==null) {
				paramsClazz = new Clazz();
			}
			//设置分页信息
			setPagination(paramsClazz);
			//总的条数
			int[] sum={0};
			//查询班级列表
			List<Clazz> clazzs = adminManager.listClazzs(paramsClazz,sum); 
			
			Param.setAttribute("clazzs", clazzs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询班级异常", "main.jsp");
			return "infoTip";
		}
		
		return "clazzShow";
	}
	
	/**
	 * @Title: addClazzShow
	 * @Description: 显示添加班级页面
	 * @return String
	 */
	public String addClazzShow(){
		return "clazzEdit";
	}
	
	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @return String
	 */
	public String addClazz(){
		try {
			 //添加班级
			adminManager.addClazz(paramsClazz);
			
			setSuccessTip("添加成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("添加班级异常", "Admin_listClazzs.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editClazz
	 * @Description: 编辑班级
	 * @return String
	 */
	public String editClazz(){
		try {
			 //得到班级
			Clazz clazz = adminManager.queryClazz(paramsClazz);
			Param.setAttribute("clazz", clazz);
		} catch (Exception e) {
			setErrorTip("查询班级异常", "Admin_listClazzs.action");
			return "infoTip";
		}
		
		return "clazzEdit";
	}
	
	/**
	 * @Title: saveClazz
	 * @Description: 保存编辑班级
	 * @return String
	 */
	public String saveClazz(){
		try {
			 //保存编辑班级
			adminManager.updateClazz(paramsClazz);
			
			setSuccessTip("编辑成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("clazz", paramsClazz);
			return "clazzEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delClazzs
	 * @Description: 删除班级
	 * @return String
	 */
	public String delClazzs(){
		try {
			 //删除班级
			adminManager.delClazzs(paramsClazz);
			
			setSuccessTip("删除班级成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("删除班级异常", "Admin_listClazzs.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listEvaluates
	 * @Description: 查询教师评教
	 * @return String
	 */
	public String listEvaluates(){
		try {
			if (paramsEvaluate==null) {
				paramsEvaluate = new Evaluate();
			}
			//设置分页信息
			setPagination(paramsEvaluate);
			//总的条数
			int[] sum={0};
			//查询教师评教列表
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==1) {
				paramsEvaluate.setStudent_id(admin.getUser_id());//设置学生为当前用户
			}
			List<Evaluate> evaluates = adminManager.listEvaluates(paramsEvaluate,sum); 
			
			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "main.jsp");
			return "infoTip";
		}
		
		return "evaluateShow";
	}
	
	/**
	 * @Title: listEvaluatesSum
	 * @Description: 教学评教平均得分
	 * @return String
	 */
	public String listEvaluatesSum(){
		try {
			if (paramsEvaluate==null) {
				paramsEvaluate = new Evaluate();
			}
			//设置分页信息
			setPagination(paramsEvaluate);
			//总的条数
			int[] sum={0};
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==2) {
				paramsEvaluate.setTeacher_id(admin.getUser_id());//设置教师为当前用户
			}
			//查询教师评教列表
			List<Evaluate> evaluates = adminManager.listEvaluatesSum(paramsEvaluate,sum); 
			
			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "main.jsp");
			return "infoTip";
		}
		
		return "evaluateSumShow";
	}
	
	/**
	 * @Title: addEvaluateShow
	 * @Description: 显示添加教师评教页面
	 * @return String
	 */
	public String addEvaluateShow(){
		//查询教师字典
		User user = new User();
		user.setStart(-1);
		user.setUser_type(2);
		List<User> users = adminManager.listUsers(user, null);
		Param.setAttribute("teachers", users);
		
		return "evaluateEdit";
	}
	
	/**
	 * @Title: addEvaluate
	 * @Description: 添加教师评教
	 * @return String
	 */
	public String addEvaluate(){
		try {
			//判断教师评教是否已经添加
			Evaluate evaluate = adminManager.queryEvaluate(paramsEvaluate);
			if (evaluate!=null) {
				tip = "失败，本次教师评教已经存在！";
				Param.setAttribute("evaluate", paramsEvaluate);
				
				//查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("teachers", users);
				
				return "evaluateEdit";
			}
			
			//添加教师评教
			adminManager.addEvaluate(paramsEvaluate);
			
			setSuccessTip("添加教师评教成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			setErrorTip("添加教师评教异常", "Admin_listEvaluates.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editEvaluate
	 * @Description: 编辑教师评教
	 * @return String
	 */
	public String editEvaluate(){
		try {
			 //得到教师评教
			Evaluate evaluate = adminManager.queryEvaluate(paramsEvaluate);
			Param.setAttribute("evaluate", evaluate);
			
			//查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", users);
			
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "Admin_listEvaluates.action");
			return "infoTip";
		}
		
		return "evaluateEdit";
	}
	
	/**
	 * @Title: saveEvaluate
	 * @Description: 保存编辑教师评教
	 * @return String
	 */
	public String saveEvaluate(){
		try {
			 //保存编辑教师评教
			adminManager.updateEvaluate(paramsEvaluate);
			
			setSuccessTip("编辑成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("evaluate", paramsEvaluate);
			
			//查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", users);
			
			return "evaluateEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delEvaluates
	 * @Description: 删除教师评教
	 * @return String
	 */
	public String delEvaluates(){
		try {
			 //删除教师评教
			adminManager.delEvaluates(paramsEvaluate);
			
			setSuccessTip("删除教师评教成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			setErrorTip("删除教师评教异常", "Admin_listEvaluates.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Clazz getParamsClazz() {
		return paramsClazz;
	}

	public void setParamsClazz(Clazz paramsClazz) {
		this.paramsClazz = paramsClazz;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Evaluate getParamsEvaluate() {
		return paramsEvaluate;
	}

	public void setParamsEvaluate(Evaluate paramsEvaluate) {
		this.paramsEvaluate = paramsEvaluate;
	}
	
	 
	
}
