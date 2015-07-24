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
    
    <title>我的小屋</title>
    
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

  </head>
  
  <body>
    欢迎光临<c:out value="${name}"></c:out>的s小屋
	<button id="postBtn">postTest</button> 
  </body>
  <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
  <script>
  	var api = (function(){
    /**
     * @param useMock {Boolean} 是否使用测试桩。
     */
    return function(t, params, callback, useMock,method){
        if(!callback){
            callback=$.noop;
        }

        var doneFns=[];
        var stepFns=[callback];

        var e={
            done:function(fn){
                doneFns.push(fn);
                return this;
            },
            on:function(fn){
                stepFns.push(fn);
                return this;
            },
            resolve:function(){
                e.status='done';
                for (var i = 0; i < doneFns.length; i++) {
                    doneFns[i].apply(e,arguments);
                }
                return this;
            }
        };
        var ajaxOption={
            url : t,
            type : method||'GET',
            data : params,
            contentType:"application/json; charset=UTF-8",
            dataType : 'json',
            timeout : 3e4,
            success : function(res){
                // 当接口挂了
                e.status='';
                for (var i = 0; i < stepFns.length; i++) {
                    stepFns[i].call(e,res.bstatus&&res.bstatus.code==0,res.bstatus&&res.bstatus.des,res.data,res);
                }
            },
            error : function(res){
                e.status='fail';
                // time out 的status 也是0
                for (var i = 0; i < stepFns.length; i++) {
                    stepFns[i].call(e,-res.status||-1,'网络错误',res.responseText);
                }
            }
        };
        if(useMock){// 使用测试桩数据
            ajaxOption.url = '/pc/js/mock/' + t + '.json' + '?rdm=' + Math.random();
            ajaxOption.type = 'GET';
        }
        e.retry=function(){
            if(e.status==='loading'){
                return;
            }
            e.status='loading';
            e.ajax=$.ajax(ajaxOption);
            return this;
        };

        e.retry();
        return e;
    };
})();
	var data = '<xml> <ToUserName><![CDATA[toUser]]></ToUserName> <FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime> <MsgType><![CDATA[image]]></MsgType> <PicUrl><![CDATA[this is a url]]></PicUrl> <MediaId><![CDATA[media_id]]></MediaId> <MsgId>1234567890123456</MsgId> </xml>';
  	api("/lovelulu/wx/index",data,function(flag,des,data,res){
  		
  	},false,"POST");
  </script>
</html>
