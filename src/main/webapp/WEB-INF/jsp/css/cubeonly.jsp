<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
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
        <link rel="stylesheet" type="text/css" href="/fe/css/csstest/cubeonly.css">
    </head>
    <body>
    <div id="wall" class="wall">
        <div class="cube">
            <div class="side front" style="background-image: url(http://demo.lanrenzhijia.com/2015/pic0821/images/minion_2.jpg);"></div>
            <div class="side right" style="background-image: url(http://demo.lanrenzhijia.com/2015/pic0821/images/minion_3.jpg);"></div>
            <div class="side back"></div>
            <div class="side left" style="background-image: url(http://demo.lanrenzhijia.com/2015/pic0821/images/minion_3.jpg);"></div>
            <div class="side top" style="background-image: url(http://demo.lanrenzhijia.com/2015/pic0821/images/minion_3.jpg);"></div>
            <div class="side bottom" style="background-image: url(http://demo.lanrenzhijia.com/2015/pic0821/images/minion_3.jpg);"></div>
        </div>
    </div>
    </body>
    <script src="/fe/js/csstest/cubeonly.js"></script>
</html>
