<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    <c:if test="${wx_user_msg!=''}">
        var wxUserMsg = ${wx_user_msg};
    </c:if>
  </script>
<script src="/fe/js/wx/wxUserMsg.js"></script>