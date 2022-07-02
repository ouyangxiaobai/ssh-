<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}


function serch()
{
   document.info.action="Admin_listUsers.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的学生！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delUsers.action?paramsUser.ids="+ids;
       document.info.submit();
    }
    
}
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listUsers.action";
  document.info.submit();
}
$(document).ready(function(){
	$("#export").bind('click',function(){
		var aQuery = $("#info").serialize();  
		$("#info").attr('target','_blank').attr('action','exportUsers.action').submit();;
	});
});
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生管理&gt;&gt;学生查询</span>
</div>
<form name="info" id="info" action="Admin_listUsers.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">学生列表</td>
    <td width="" align="right">
            学号：
      <input type="text" id="paramsUser.user_name" name="paramsUser.user_name" value="${paramsUser.user_name}" class="inputstyle" style="width:100px"/>&nbsp;
            姓名：
      <input type="text" id="paramsUser.real_name" name="paramsUser.real_name" value="${paramsUser.real_name}" class="inputstyle" style="width:100px"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.admin.user_type==3">
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>&nbsp;
      <input type="button" value="导出" id="export" class="btnstyle"/>
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <s:if test="#attr.admin.user_type==3">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>
     <td width="40" align="center">序号</td>
     <td width="" align="center">学号</td>
     <td width="" align="center">姓名</td>
     <td width="" align="center">性别</td>
     <td width="" align="center">年龄</td>
     <td width="" align="center">班级</td>
     <td width="" align="center">平均成绩</td>
     <td width="" align="center">G点分</td>
     <td width="" align="center">添加时间</td>
     <s:if test="#attr.admin.user_type==3">
     <td width="" align="center">操作</td>
     </s:if>
   </tr>
   <s:if test="#attr.users!=null && #attr.users.size()>0">
   <s:iterator value="#attr.users" id="user" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.admin.user_type==3">
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#user.user_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+#attr.paramsUser.start+1"/></td>
     <td width="" align="center"><s:property value="#user.user_name"/></td>
     <td width="" align="center"><s:property value="#user.real_name"/></td>
     <td width="" align="center"><s:property value="#user.user_sexDesc"/></td>
     <td width="" align="center"><s:property value="#user.user_age"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#user.clazz_name"/></td>
     <td width="" align="center"><s:property value="#user.user_score"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#user.user_gscore"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#user.reg_date.substring(0,19)"/></td>  
     <s:if test="#attr.admin.user_type==3">
     <td width="" align="center">
       <img src="images/edit.png"/>&nbsp;<s:a href="Admin_editUser.action?paramsUser.user_id=%{#user.user_id}">编辑</s:a>
     </td>
     </s:if>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="12" align="center"><b>&lt;不存在学生信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>