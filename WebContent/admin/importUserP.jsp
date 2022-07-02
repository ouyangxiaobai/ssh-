<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导入学生信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script charset="utf-8" src="editor/kindeditor.js"></script>
<script language="javascript" type="text/javascript">
function trimStr(str)     
{     
    if ((typeof(str) != "string") || !str)   
    {    
        return "";    
    }   
    return str.replace(/(^\s*)|(\s*$)/g, "");    
} 
 
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">学生管理&gt;&gt;导入学生</span>
</div>
<table width="600" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">导入学生信息</TD>
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
		  <td width="30%" align="right" style="padding-right:5px">学生文件：</td>
	      <td align="left"> 
	          <iframe name="uploadPage" src="importUser.jsp" width="100%" height="60" marginheight="0" marginwidth="0" scrolling="no" frameborder="0"></iframe>            
	       </td>
	       <td align="left">
		    <span id="op" style="display:none"><img src="images/loading004.gif"  height="58" /></span>
	      </td>
	  </tr>
	  <tr>
          <td width="30%" align="right" style="padding-right:5px">导入提示：</td>
          <td colspan="2" style="color:red">导入Excel文件中的【学号】和【班级名】必须在系统中存在！</td>  
       </tr> 
             
     </table>  
     </td> 
   </tr>   
</table>
</body>
</html>