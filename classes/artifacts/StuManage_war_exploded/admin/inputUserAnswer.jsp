<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证密保问题</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	 
	 $("#saveBtn").bind('click',function(){
		if($("#paramsUser\\.user_answer").val()==''){
			alert('请输入密保答案');
			return;
		}
		$("#info").submit();
		 
	 });
	
});	 
	
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">首页&gt;&gt;验证密保问题</span>
</div>
<form id="info" name="info" action="Admin_validAnswer.action" method="post">
<input type="hidden" id="paramsUser.user_name" name="paramsUser.user_name" value="<s:property value='#attr.user.user_name'/>" />    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">验证密保问题</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
    	 <tr>
          <td width="30%" align="right" style="padding-right:5px">密保问题：</td>
          <td>
            <s:property value='#attr.user.user_question'/>
          </td>
        </tr>
        <tr>
          <td width="30%" align="right" style="padding-right:5px"><font color="red">*</font> 密保答案：</td>
          <td>
            <input type="text" id="paramsUser.user_answer" name="paramsUser.user_answer" value="<s:property value='#attr.user_answer'/>" />
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
           <td width="30%" align="right" style="padding-right:5px"></td>
           <td  height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="下一步"/> 
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>