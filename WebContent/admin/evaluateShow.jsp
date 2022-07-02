<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师评教信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
   document.info.action="Admin_listEvaluates.action";
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
       alert("请至少选择一个要删除的教师评教！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delEvaluates.action?paramsEvaluate.ids="+ids;
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
  document.info.action="Admin_listEvaluates.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listEvaluates.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教师评教&gt;&gt;教师评教查询</span>
</div>
<form name="info" id="info" action="Admin_listEvaluates.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="98%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">教师评教列表</td>
    <td width="" align="right">
            年份：
      <input type="text" style="width:100px;" id="paramsEvaluate.evaluate_year" name="paramsEvaluate.evaluate_year" value="${paramsEvaluate.evaluate_year}" class="inputstyle" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"/>&nbsp;
      <s:select name="paramsEvaluate.evaluate_year_half" id="paramsEvaluate.evaluate_year_half" 
      			list="#{1:'上半年',2:'下半年'}" value="%{#attr.paramsEvaluate.evaluate_year_half}" 
      			listKey="key" listValue="value" headerKey="0" headerValue="选择学期" 
      			cssClass="selectstyle" cssStyle="width:100px">
      </s:select>&nbsp;
            教师姓名：
      <input type="text" style="width:100px;" id="paramsEvaluate.teacher_name" name="paramsEvaluate.teacher_name" value="${paramsEvaluate.teacher_name}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
      <s:if test="#attr.admin.user_type==3">
      <input type="button" value="删除" onclick="del();" class="btnstyle"/>
      </s:if>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="98%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
   	 <s:if test="#attr.admin.user_type==3">
     <td width="40" align="center"><input type="checkbox" onclick="CheckAll(this);" style="vertical-align:text-bottom;" title="全选/取消全选"/></td>
     </s:if>
     <td width="40" align="center">序号</td>
     <td width="50" align="center">年份</td>
     <td width="50" align="center">学期</td>
     <td width="" align="center">教师</td>
     <td width="" align="center">综合素质</td>
     <td width="" align="center">岗位职责</td>
     <td width="" align="center">教学技能</td>
     <td width="" align="center">辅导情况</td>
     <td width="" align="center">备课情况</td>
     <td width="" align="center">教学效果</td>
     <td width="" align="center">讲课生动与否</td>
     <td width="" align="center">学识水平</td>
     <td width="" align="center">评分人</td>
   </tr>
   <s:if test="#attr.evaluates!=null && #attr.evaluates.size()>0">
   <s:iterator value="#attr.evaluates" id="evaluate" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <s:if test="#attr.admin.user_type==3">
     <td width="" align="center"><s:checkbox name="chkid" fieldValue="%{#evaluate.evaluate_id}" cssStyle="vertical-align:text-bottom;"/></td>
     </s:if>
     <td width="" align="center"><s:property value="#status.index+#attr.paramsEvaluate.start+1"/></td>
     <td width="" align="center"><s:property value="#evaluate.evaluate_year"/></td>
     <td width="" align="center"><s:property value="#evaluate.evaluate_year_halfDesc"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#evaluate.teacher_name"/></td>
     <td width="" align="center"><s:property value="#evaluate.quality_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.post_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.skill_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.coach_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.plan_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.effect_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.vivid_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.knowledge_score"/></td>
     <td width="" align="center"><s:property value="#evaluate.student_name"/></td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="15" align="center"><b>&lt;不存在教师评教信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>