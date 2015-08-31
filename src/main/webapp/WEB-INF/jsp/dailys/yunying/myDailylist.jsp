<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html>
    <head>

        <title>晚安运营后台-文章列表</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta charset="UTF-8">

        <link href="/fe/css/daily/yunying.css" rel="stylesheet" />

    </head>

    <body>
    	<div class="content">
	        <h1 class="h-c">运营后台</h1>
	        <c:forEach var="dailyItem" items="${dailyList}" varStatus="status">
	        	<div class="frame-item h-l">
		        	<div class="frame-item-title h-l">${status.index+1}</div>
		        	<div class="frame-item-value h-l" style="position:relative;">
		        		<a href="/lovelulu/daily/yunying?dailyId=${dailyItem.id}" target="_blank">
		        			${dailyItem.title}
		        		</a>
		        		<a class="pageitem deletebtn" data-delid="${dailyItem.id}"  style="right:0;position:absolute;top:25%;">删除</a>
		        	</div>
		        </div>
	        </c:forEach>
	        
	        <c:set var="listpageUri" value="/lovelulu/daily/list?psize=10&pno="></c:set>
	        
	        <%@include file="../../utils/listPage.jsp" %>
	        <a class="pageitem" href="/lovelulu/daily/yunying" target="_blank" style="float:right;margin:0 15% 0 0;">新增</a>
        </div>  
    </body>
    <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
    <script src="/fe/js/common/base.js"></script>
    <script>
	    function deleteDaily(id){
			myUtil.api("/lovelulu/daily/delete",{"id":id},"GET",function(code,desc,data,res){
				console.log(res);
				if(code==0){
					alert("删除成功");
					window.location.reload();
				}
			});
		}
    	$(".deletebtn").click(function(){
    		var id = $(this).attr("data-delid");
    		deleteDaily(id);
    	});
    </script>
</html>