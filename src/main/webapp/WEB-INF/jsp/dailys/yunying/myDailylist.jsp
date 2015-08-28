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
		        	<div class="frame-item-value h-l">
		        		<a href="/lovelulu/daily/yunying?dailyId=${dailyItem.id}">
		        			${dailyItem.title}
		        		</a>
		        	</div>
		        </div>
	        </c:forEach>
	        
	        <c:if test="${allPage<=6}">
	        	<div class="pagelist h-c">
	        		<a class="pageitem">上一页</a>
	        	<c:forEach var="i" begin="1" end="${allPage}" step="1"> 
			      	<a class="pageitem">${pno+i}</a>
		    	</c:forEach>
			    	<a class="pageitem">下一页</a>
		        </div>
	        </c:if>
	        
	        
	        	
	        	
	        	
	        	
        </div>  
    </body>
    <script type="text/javascript" src="/fe/js/lib/zepto-1.1.4.js"></script>
</html>
































