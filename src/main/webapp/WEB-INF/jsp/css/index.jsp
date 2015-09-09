<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();//"/lovelulu/css";
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>css特效</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no,minimal-ui" name="viewport">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta content="telephone=no" name="format-detection">
        <!-- UC默认竖屏 ，UC强制全屏 -->
        <meta name="full-screen" content="yes">
        <meta name="browsermode" content="application">
        <!-- QQ强制竖屏 QQ强制全屏 -->
        <meta name="x5-orientation" content="portrait">
        <meta name="x5-fullscreen" content="true">
        <meta name="x5-page-mode" content="app">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="/fe/css/base/base.css">
        <link rel="stylesheet" type="text/css" href="/fe/css/csstest/index.css">
    </head>
    <body>
        <div class="content">
            <div class="linkitem v-c"><a href="/lovelulu/css/cubeonly">立体式</a></div>
            <div class="linkitem v-c"><a href="/lovelulu/css/cube">立体式动画</a></div>
            <div class="linkitem v-c"><a href="/lovelulu/css/clock">时钟</a></div>
        </div>
    </body>
</html>
