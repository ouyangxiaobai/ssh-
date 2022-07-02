
package com.nkl.admin.action;

import java.util.HashMap;
import java.util.List;

import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;

public class ExportExcelAction extends BaseAction{

	private static final long serialVersionUID = 8143993190677252109L;
	AdminManager adminManager =  new AdminManager();
	
	//导出参数
	User paramsUser;
	HashMap<String, Object> report = new HashMap<String, Object>();

	/**
	 * @Title: exportUsers
	 * @Description: 导出学生信息
	 * @return String
	 */
	public String exportUsers(){
		try{
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//设置分页信息
			paramsUser.setStart(-1);
			//查询学生列表
			paramsUser.setUser_type(1);
			List<User> users = adminManager.listUsers(paramsUser,null); 
			
			report.put("users", users);
			
			//设置导出文件名
			setExportExcelName("学生信息.xls");
		}
        catch(Exception e){
            setErrorReason("导出异常，请稍后再试", e);
            return ERROR;
        }
        return SUCCESS; 
	}
	
	 

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public HashMap<String, Object> getReport() {
		return report;
	}

	public void setReport(HashMap<String, Object> report) {
		this.report = report;
	}

}
