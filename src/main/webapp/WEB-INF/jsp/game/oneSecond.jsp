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
        <title>你能准确按出1秒么</title>
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

    <style type="text/css">
    * {margin:0; padding: 0;}
    html,body {width: 100%;height: 100%;overflow: hidden;}
    body {background:url(http://cgzp.cdn.novorunning.com/second/images/top_bg01.jpg) repeat; }
    #box {width:100%;height:100%;background:url(http://cgzp.cdn.novorunning.com/second/images/top_bg01.jpg) repeat;overflow: hidden;text-align: center;}
    #top {width: 200px ;height: 110px; background:url(http://cgzp.cdn.novorunning.com/second/images/second_logo.png);background-size: 100% 100%; margin: 10% auto 0}
    #box h1 {font-size: 16px;font-weight: normal;padding: 10px;color: #666;}
    #content {width: 280px;background: #fff;border-radius: 20px;margin: 0 auto;line-height: 24px;padding: 5px 3px;color: #666;font-size: 18px;}
    #content h2 {font-size: 24px;display: inline;color: #f5484b;}
    #btn_bg {width: 110px;height: 110px;border-radius: 55px;background:#eee; margin: 10px auto;position: relative;border:1px solid #f0d0d0;}
    #btn_bt {width: 90px;height: 90px; border-radius: 45px;background: #f5484b;line-height: 90px;position: absolute;top: 10px;left: 10px;border: 0;}
    #btn_bt span {color: #fff;font-size: 24px;}
    .active {-webkit-box-shadow:1px 1px 6px 2px rgba(0,0,0,0.4)  inset ;-ms-box-shadow:1px 1px 6px 2px rgba(0,0,0,0.4)  inset ; box-shadow: 1px 1px 6px 2px rgba(0,0,0,0.4) inset ; }
    #box .share-btn {border-radius: 5px;background-color: rgb(228, 103, 106);display: block;width:120px;height:20px;padding: 5px;text-decoration: none;font-size: 16px;color: #fff;margin: 0 auto;margin-top: 10px;}
    #share {width: 100%;height: 100%;background: rgba(0,0,0,0.9) url(http://cgzp.cdn.novorunning.com/second/images/second_share.png) no-repeat;background-position: top right; position: absolute;top:0;right: 0;z-index: 99;display: none;}
    #logo {width: 80px;height: 80px;background:url(http://cgzp.cdn.novorunning.com/second/images/Fiilogo.png);background-size: 100% 100%; margin: 10px auto;}
    /*.footer{position: fixed;bottom: 0;width: 100%;background-color: black;opacity: 0.8;padding: 10px 0;}
    .footer a{display: block;width: 300px;height:70px;margin: 0 auto;position: relative;}
    .footer a .icon{background-image:url(images/iShare_logo.png);background-size: cover;width: 32px;height: 32px;background-repeat: no-repeat;float: left;}
    .footer a .message{float: left;}*/
    </style>
    <style type="text/css">#yddContainer{display:block;font-family:Microsoft YaHei;position:relative;width:100%;height:100%;top:-4px;left:-4px;font-size:12px;border:1px solid}#yddTop{display:block;height:22px}#yddTopBorderlr{display:block;position:static;height:17px;padding:2px 28px;line-height:17px;font-size:12px;color:#5079bb;font-weight:bold;border-style:none solid;border-width:1px}#yddTopBorderlr .ydd-sp{position:absolute;top:2px;height:0;overflow:hidden}.ydd-icon{left:5px;width:17px;padding:0px 0px 0px 0px;padding-top:17px;background-position:-16px -44px}.ydd-close{right:5px;width:16px;padding-top:16px;background-position:left -44px}#yddKeyTitle{float:left;text-decoration:none}#yddMiddle{display:block;margin-bottom:10px}.ydd-tabs{display:block;margin:5px 0;padding:0 5px;height:18px;border-bottom:1px solid}.ydd-tab{display:block;float:left;height:18px;margin:0 5px -1px 0;padding:0 4px;line-height:18px;border:1px solid;border-bottom:none}.ydd-trans-container{display:block;line-height:160%}.ydd-trans-container a{text-decoration:none;}#yddBottom{position:absolute;bottom:0;left:0;width:100%;height:22px;line-height:22px;overflow:hidden;background-position:left -22px}.ydd-padding010{padding:0 10px}#yddWrapper{color:#252525;z-index:10001;background:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ab20.png);}#yddContainer{background:#fff;border-color:#4b7598}#yddTopBorderlr{border-color:#f0f8fc}#yddWrapper .ydd-sp{background-image:url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ydd-sprite.png)}#yddWrapper a,#yddWrapper a:hover,#yddWrapper a:visited{color:#50799b}#yddWrapper .ydd-tabs{color:#959595}.ydd-tabs,.ydd-tab{background:#fff;border-color:#d5e7f3}#yddBottom{color:#363636}#yddWrapper{min-width:250px;max-width:400px;}</style>

   </head>
   <body>
        <div id="box" style="width: 320px; height: 568px;">
            <div id="top"></div>
            <h1>你能精确地按出一秒吗？</h1>
            <p id="content"></p>
            <div id="btn_bg">
            <div id="btn_bt" class="">
                <span>按住</span>
            </div>
            </div>
            <div class="share-btn" style="margin-top:20px" id="share_a" onclick >炫耀一下</div>
            <div id="share" style="display: none;"></div>
        </div>
   </body>
   <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
   <%@include file="../wx/wxsharePage.jsp" %>
   <script src="/fe/js/game/oneSecond.js"></script>
   <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?dd6cf2650ab421bff4ad229df790894e";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
</html>
