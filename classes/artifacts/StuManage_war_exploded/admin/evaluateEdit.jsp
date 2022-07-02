<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.evaluate!=null && #attr.evaluate.evaluate_id!=0">编辑</s:if><s:else>添加</s:else>教师评教信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 var quality_score = "<s:property value='#attr.evaluate.quality_score' />";
	 if(quality_score!='' && quality_score!=0){
		 $("#quality_score"+quality_score).attr('checked','checked');
	 }else{
		 $("#quality_score1").attr('checked','checked');
	 }
	 var post_score = "<s:property value='#attr.evaluate.post_score' />";
	 if(post_score!='' && post_score!=0){
		 $("#post_score"+post_score).attr('checked','checked');
	 }else{
		 $("#post_score1").attr('checked','checked');
	 }
	 var skill_score = "<s:property value='#attr.evaluate.skill_score' />";
	 if(skill_score!='' && skill_score!=0){
		 $("#skill_score"+skill_score).attr('checked','checked');
	 }else{
		 $("#skill_score1").attr('checked','checked');
	 }
	 var coach_score = "<s:property value='#attr.evaluate.coach_score' />";
	 if(coach_score!='' && coach_score!=0){
		 $("#coach_score"+coach_score).attr('checked','checked');
	 }else{
		 $("#coach_score1").attr('checked','checked');
	 }
	 var plan_score = "<s:property value='#attr.evaluate.plan_score' />";
	 if(plan_score!='' && plan_score!=0){
		 $("#plan_score"+plan_score).attr('checked','checked');
	 }else{
		 $("#plan_score1").attr('checked','checked');
	 }
	 var effect_score = "<s:property value='#attr.evaluate.effect_score' />";
	 if(effect_score!='' && effect_score!=0){
		 $("#effect_score"+effect_score).attr('checked','checked');
	 }else{
		 $("#effect_score1").attr('checked','checked');
	 }
	 var vivid_score = "<s:property value='#attr.evaluate.vivid_score' />";
	 if(vivid_score!='' && vivid_score!=0){
		 $("#vivid_score"+vivid_score).attr('checked','checked');
	 }else{
		 $("#vivid_score1").attr('checked','checked');
	 }
	 var knowledge_score = "<s:property value='#attr.evaluate.knowledge_score' />";
	 if(knowledge_score!='' && knowledge_score!=0){
		 $("#knowledge_score"+knowledge_score).attr('checked','checked');
	 }else{
		 $("#knowledge_score1").attr('checked','checked');
	 }
	
	
	 var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsEvaluate\\.evaluate_year").val()==''){
			alert('年份不能为空');
			return;
		}
		if($("#paramsEvaluate\\.teacher_id").val()=='0'){
			alert('教师不能为空');
			return;
		}
		$("#paramsEvaluate\\.evaluate_id").val(0);
		$("#info").attr('action','Admin_addEvaluate.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
	 	if($("#paramsEvaluate\\.evaluate_year").val()==''){
			alert('年份不能为空');
			return;
		}
		if($("#paramsEvaluate\\.teacher_id").val()=='0'){
			alert('教师不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveEvaluate.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教师评教&gt;&gt;<s:if test="#attr.evaluate!=null && #attr.evaluate.evaluate_id!=0">编辑</s:if><s:else>添加</s:else>教师评教</span>
</div>
<form id="info" name="info" action="Admin_addEvaluate.action" method="post">   
<s:hidden id="paramsEvaluate.evaluate_id" name="paramsEvaluate.evaluate_id" value="%{#attr.evaluate.evaluate_id}" /> 
<s:hidden id="paramsEvaluate.student_id" name="paramsEvaluate.student_id" value="%{#attr.admin.user_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.evaluate!=null && #attr.evaluate.evaluate_id!=0">编辑</s:if><s:else>添加</s:else>教师评教</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 年份：</td>
          <td width="65%"> 
           <s:textfield name="paramsEvaluate.evaluate_year" id="paramsEvaluate.evaluate_year" value="%{#attr.evaluate.evaluate_year}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"/>
          </td> 
	   </tr>
	   <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 学期：</td>
          <td>
         	<s:select name="paramsEvaluate.evaluate_year_half" id="paramsEvaluate.evaluate_year_half"   value="%{#attr.evaluate.evaluate_year_half}"
         		list="#{1:'上半年',2:'下半年'}"  listKey="key" listValue="value" emptyOption="false"  cssStyle="width:155px">
         	</s:select>
          </td>
	   </tr>
	   <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 教师：</td>
          <td>
          	<s:select name="paramsEvaluate.teacher_id" id="paramsEvaluate.teacher_id"  value="%{#attr.evaluate.teacher_id}" 
          		list="#attr.teachers" listKey="user_id" listValue="real_name" headerKey="0" headerValue="请选择" cssStyle="width:155px">
            </s:select>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 综合素质：</td>
          <td>
          	 <input type="radio" id="quality_score1" name="paramsEvaluate.quality_score" value="1"/>1分&nbsp;
             <input type="radio" id="quality_score2" name="paramsEvaluate.quality_score" value="2"/>2分&nbsp;
             <input type="radio" id="quality_score3" name="paramsEvaluate.quality_score" value="3"/>3分&nbsp;
             <input type="radio" id="quality_score4" name="paramsEvaluate.quality_score" value="4"/>4分&nbsp;
             <input type="radio" id="quality_score5" name="paramsEvaluate.quality_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 岗位职责：</td>
          <td>
          	 <input type="radio" id="post_score1" name="paramsEvaluate.post_score" value="1"/>1分&nbsp;
             <input type="radio" id="post_score2" name="paramsEvaluate.post_score" value="2"/>2分&nbsp;
             <input type="radio" id="post_score3" name="paramsEvaluate.post_score" value="3"/>3分&nbsp;
             <input type="radio" id="post_score4" name="paramsEvaluate.post_score" value="4"/>4分&nbsp;
             <input type="radio" id="post_score5" name="paramsEvaluate.post_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 教学技能：</td>
          <td>
          	 <input type="radio" id="skill_score1" name="paramsEvaluate.skill_score" value="1"/>1分&nbsp;
             <input type="radio" id="skill_score2" name="paramsEvaluate.skill_score" value="2"/>2分&nbsp;
             <input type="radio" id="skill_score3" name="paramsEvaluate.skill_score" value="3"/>3分&nbsp;
             <input type="radio" id="skill_score4" name="paramsEvaluate.skill_score" value="4"/>4分&nbsp;
             <input type="radio" id="skill_score5" name="paramsEvaluate.skill_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 辅导情况：</td>
          <td>
          	 <input type="radio" id="coach_score1" name="paramsEvaluate.coach_score" value="1"/>1分&nbsp;
             <input type="radio" id="coach_score2" name="paramsEvaluate.coach_score" value="2"/>2分&nbsp;
             <input type="radio" id="coach_score3" name="paramsEvaluate.coach_score" value="3"/>3分&nbsp;
             <input type="radio" id="coach_score4" name="paramsEvaluate.coach_score" value="4"/>4分&nbsp;
             <input type="radio" id="coach_score5" name="paramsEvaluate.coach_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 备课情况：</td>
          <td>
          	 <input type="radio" id="plan_score1" name="paramsEvaluate.plan_score" value="1"/>1分&nbsp;
             <input type="radio" id="plan_score2" name="paramsEvaluate.plan_score" value="2"/>2分&nbsp;
             <input type="radio" id="plan_score3" name="paramsEvaluate.plan_score" value="3"/>3分&nbsp;
             <input type="radio" id="plan_score4" name="paramsEvaluate.plan_score" value="4"/>4分&nbsp;
             <input type="radio" id="plan_score5" name="paramsEvaluate.plan_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 教学效果：</td>
          <td>
          	 <input type="radio" id="effect_score1" name="paramsEvaluate.effect_score" value="1"/>1分&nbsp;
             <input type="radio" id="effect_score2" name="paramsEvaluate.effect_score" value="2"/>2分&nbsp;
             <input type="radio" id="effect_score3" name="paramsEvaluate.effect_score" value="3"/>3分&nbsp;
             <input type="radio" id="effect_score4" name="paramsEvaluate.effect_score" value="4"/>4分&nbsp;
             <input type="radio" id="effect_score5" name="paramsEvaluate.effect_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 讲课生动与否：</td>
          <td>
          	 <input type="radio" id="vivid_score1" name="paramsEvaluate.vivid_score" value="1"/>1分&nbsp;
             <input type="radio" id="vivid_score2" name="paramsEvaluate.vivid_score" value="2"/>2分&nbsp;
             <input type="radio" id="vivid_score3" name="paramsEvaluate.vivid_score" value="3"/>3分&nbsp;
             <input type="radio" id="vivid_score4" name="paramsEvaluate.vivid_score" value="4"/>4分&nbsp;
             <input type="radio" id="vivid_score5" name="paramsEvaluate.vivid_score" value="5"/>5分
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 学识水平：</td>
          <td>
          	 <input type="radio" id="knowledge_score1" name="paramsEvaluate.knowledge_score" value="1"/>1分&nbsp;
             <input type="radio" id="knowledge_score2" name="paramsEvaluate.knowledge_score" value="2"/>2分&nbsp;
             <input type="radio" id="knowledge_score3" name="paramsEvaluate.knowledge_score" value="3"/>3分&nbsp;
             <input type="radio" id="knowledge_score4" name="paramsEvaluate.knowledge_score" value="4"/>4分&nbsp;
             <input type="radio" id="knowledge_score5" name="paramsEvaluate.knowledge_score" value="5"/>5分
          </td>
        </tr>
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<s:if test="#attr.evaluate!=null && #attr.evaluate.evaluate_id!=0">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
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