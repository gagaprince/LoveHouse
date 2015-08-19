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

        <title><c:out value="${title}"></c:out></title>

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
                那个我深爱的姑娘
            </div>
            <div class="daily-time-frame">
                <div class="daily-time">2015-08-19</div>
            </div>
            <div class="daily-img-frame h-c">
                <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/0.jpg">
            </div>
            <div class="daily-content-frame">
                <div class="daily-content">
                    <div class="daily-bg v-c">
                        <div class="daily-duan-frame black-tou">
                            她很纯真，她很美腻，她很聪明，她很专一，她很霸道，她很文艺，她很厉害，她很神秘，她很孝顺，她很贤惠，她很温柔，也很逗逼，她~是我的唯一！<br>她有数不尽的优点，<br>也有改不掉的强迫症。<br>我们的故事很长很复杂，<br>其实也很短很简单，<br>只是我喜欢她，<br>刚好她也喜欢我，
                            <br>这不就是传说中的幸福嘛！
                        </div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/20150819223328.jpg">
                        </div>
                        <div class="daily-duan-frame white-tou mt30">
                            这里是第一段，巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉
                        </div>
                        <div class="daily-duan-frame">
                            你的朋友圈总会有一些很奇怪又很有趣的东西蹦出来，时而搞怪，时而煽情，时而卖萌，时而文艺气息朦胧，
                        </div>
                        <div class="daily-line mt30"></div>
                        <div class="daily-img mt30">
                            <img src="http://gagaprince.oss-cn-beijing.aliyuncs.com/daily/IMG_1259.JPG">
                        </div>
                        <div class="daily-duan-frame">
                            这里是第一段，巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉
                        </div>
                        <div class="daily-duan-frame">
                            这里是第一段，巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉
                        </div>

                        <div class="daily-duan-frame">
                            这里是第一段，巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉
                        </div>

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
































