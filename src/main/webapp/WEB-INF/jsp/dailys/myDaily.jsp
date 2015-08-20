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

        <title><c:out value="${contextBean.headTitle}"></c:out></title>

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

        <link href="/fe/css/daily/daily.css" rel="stylesheet" />

    </head>

    <body>
        <div class="daily-frame v-c">
            <div class="daily-title font-yahei">
                ${contextBean.title}
            </div>
            <div class="daily-time-frame">
                <div class="daily-time">${contextBean.time}</div>
            </div>
            <div class="daily-img-frame h-c">
                <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/0.jpg">
            </div>
            <div class="daily-content-frame">
                <div class="daily-content">
                    <div class="daily-bg v-c">
                        <c:forEach var="dailyItem" items="${contextBean.dailyList}" varStatus="status">
                            <c:if test="${dailyItem.type=='content'}">
                                <c:if test="${status.index==0}">
                                    <div class="daily-duan-frame black-tou">
                                        ${dailyItem.content}
                                    </div>
                                </c:if>
                                <c:if test="${status.index!=0}">
                                    <div class="daily-duan-frame">
                                        ${dailyItem.content}
                                    </div>
                                </c:if>
                            </c:if>
                            <c:if test="${dailyItem.type=='img'}">
                                <div class="daily-img mt30">
                                    <img src="${dailyItem.content}">
                                </div>
                            </c:if>
                        </c:forEach>
                   </div>
                </div>
            </div>
            <div class="daily-img-frame h-c pb30">
                <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/1.jpg">
            </div>
        </div>
    </body>
    <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
    <%@include file="../wx/wxsharePage.jsp" %>
    <script src="/fe/js/daily/index.js"></script>
</html>
































