<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
  function uploadImg()
  {
    if(document.getElementById("fileurl").value=="")
    {
       alert("请选择导入的学生文件！");
       return false;
    }
    var showload = window.parent.document.getElementById("op");
    showload.style.display="";
    document.upImgFrm.submit();
  }
  function file_change(e){
		document.getElementById("upfile").value = e;
	 }
</script>
</head>
<body bgcolor="#DFEDFF">
<form name="upImgFrm" method="post" action="Import_User.action" style="padding:0px;margin:0px;" enctype="multipart/form-data">
<input type="text" name="upfile" id="upfile" style="border:1px dotted #ccc;width:150px">  
<input type="file" id="fileurl" name="upload" style="filter:alpha(opacity=10);position:absolute;opacity:0;width:45px;height:21px" onchange="file_change(this.value)"/>
<input type="button" value="浏览" onclick="fileurl.click()" class="btnstyle" style="width:60px">&nbsp;&nbsp; &nbsp;  
<input type="button" class="btnstyle" value="导入" onclick="uploadImg();"/>&nbsp;
<br/>
<span id="upTip" style="color:red"></span>
</form>
<script language="javascript">
<!--  
  var flag = "${tip}";
  var errorInfo = "${tipInfo}";
  if(flag=='ok')
  {
    window.parent.document.getElementById("op").style.display="none";
    document.getElementById("upTip").innerHTML="导入成功！";
  }
  else if(flag=='no')
  {
    window.parent.document.getElementById("op").style.display="none";
    document.getElementById("upTip").innerHTML="导入失败！"+errorInfo;   
  }
-->        
</script>
</body>
</html>