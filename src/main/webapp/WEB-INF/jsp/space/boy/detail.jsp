<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>gaga的技术小屋</title>
<meta name="Keywords" content="" >
<meta name="Description" content="" >
<link href="/fe/css/space/index.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="/fe/js/space/modernizr.js"></script>
<![endif]-->
</head>
<body>
    <%@include file="./common/header.jsp" %>
<article>
    <%@include file="./common/left.jsp" %>
  <!--l_box end -->
  <div class="r_box">
    <li>
        <h3><a href="#">${daily.title}</a></h3>
        <p>${daily.content}</p>
    </li>
  </div>
</article>
</body>
</html>

