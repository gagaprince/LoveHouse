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

        <title>晚安运营后台</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta charset="UTF-8">

        <link href="/fe/css/daily/yunying.css" rel="stylesheet" />

    </head>

    <body>
    	<div class="content">
	        <h1 class="h-c">运营后台</h1>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l">文档标题</div>
	        	<div class="frame-item-value">
	        		<input type="text" placeholder="请输入文档标题" class="value-input">
	        	</div>
	        </div>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l">文章标题</div>
	        	<div class="frame-item-value">
	        		<input type="text" placeholder="请输入文章标题" class="value-input">
	        	</div>
	        </div>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l">时间</div>
	        	<div class="frame-item-value">
	        		<input type="text" placeholder="请输入时间" class="value-input">
	        	</div>
	        </div>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l">分享desc</div>
	        	<div class="frame-item-value">
	        		<input type="text" placeholder="请输入分享内容描述" class="value-input">
	        	</div>
	        </div>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l">分享图片</div>
	        	<div class="frame-item-value">
	        		<input type="text" placeholder="请输入分享图片地址" class="value-input">
	        	</div>
	        </div>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l">外链id</div>
	        	<div class="frame-item-value">
	        		<input type="text" placeholder="请输入外链id" class="value-input">
	        	</div>
	        </div>
	        <div class="frame-item h-l">
	        	<div class="frame-item-title h-l" style="cursor: pointer;" id="contentAdd">正文 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;添加</div>
	        	<div class="frame-item-value" style="padding:0;" id="contentValues">
	        		<div class="main-item h-l">
	        			<label class="radioFrame h-c" for="fontRadio">
	        				<div>文字</div>
	        				<input id="fontRadio" type="radio" name="contentType">	
	        			</label>
	        			<label class="radioFrame h-c" for="picRadio">
	        				<div>图片</div>
	        				<input id="picRadio" type="radio" name="contentType">	
	        			</label>
	        			<input type="textarea" placeholder="请输入" class="value-input" style="width:288px;">
	        			<div class="delete">删除</div>
	        		</div>
	        	</div>
	        </div>
	        <div class="button-frame h-c">
	        	<div style="width:550px;" class="h-r">
	        		<button id="saveButton" class="save-button">保存</button>
	        	</div>
	        </div>
	        
        </div>
    </body>
    <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
    <script src="/fe/js/daily/yunying/index.js"></script>
</html>
































