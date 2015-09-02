    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>日志编辑</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="/fe/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/fe/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/fe/js/ueditor/lang/zh-cn/zh-cn.js"></script>

    <style type="text/css">
        div{
            width:100%;
        }
        .content{
        	text-align:center;
        }
        #editor{
        	margin:auto;
        }
        #btns{
        	margin:auto;
        	width:800px;
        }
        .btn{
        	width:800px;
        	margin:auto;
        }
        .btn button{
        	float:right;
        	margin:10px 0;
        }
        .head-frame{
        	width:800px;
        	margin:10px auto;
        	height:50px;
        }
        .title-frame{
        	width:700px;
        	float:left;
        }
        .title-frame .input-title{
        	width:100%;
        	height:50px;
        	border:1px solid #aaa;
        	font-size:18px;
        	font-weight:bolder;
        	border-radius:3px;
		    -o-border-radius:3px;
		    -moz-border-radius:3px;
		    -webkit-border-radius:3px;
		    padding: 3px 10px;
        }
        .cate-frame{
        	width:100px;
        	height:50px;
        	float:left;
        	overflow:hidden;
        }
        .cate-frame select{
        	width:100%;
        	height:100%;
        	font-size:18px;
        	font-weight:bolder;
        	border-radius:3px;
		    -o-border-radius:3px;
		    -moz-border-radius:3px;
		    -webkit-border-radius:3px;
        }
    </style>
</head>
<body>
<div class="content">
    <h1>日志编辑</h1>
    <div class="head-frame">
	    <div class="title-frame">
	    	<input type="text" id="title" placeholder="输入日志标题" class="input-title">
	    </div>
	    <div class="cate-frame">
	    	<select id="cateSelect" name="daily_cate">
	    		<option value="技术人生">技术人生</option>
	    		<option value="慢生活">慢生活</option>
	    	</select>
	    </div>
    </div>
    <script id="editor" type="text/plain" style="width:800px;height:500px;"></script>
</div>
<div id="btns" style="display:none;">
    <!--<div>
        <button onclick="getAllHtml()">获得整个html的内容</button>
        <button onclick="getContent()">获得内容</button>
        <button onclick="setContent()">写入内容</button>
        <button onclick="setContent(true)">追加内容</button>
        <button onclick="getContentTxt()">获得纯文本</button>
        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
        <button onclick="hasContent()">判断是否有内容</button>
        <button onclick="setFocus()">使编辑器获得焦点</button>
        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
        <button onmousedown="setblur(event)" >编辑器失去焦点</button>

    </div>
    <div>
        <button onclick="getText()">获得当前选中的文本</button>
        <button onclick="insertHtml()">插入给定的内容</button>
        <button id="enable" onclick="setEnabled()">可以编辑</button>
        <button onclick="setDisabled()">不可编辑</button>
        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
    </div>

    <div>
        <button onclick="getLocalData()" >获取草稿箱内容</button>
        <button onclick="clearLocalData()" >清空草稿箱</button>
    </div>
	<div>
	    <button onclick="createEditor()">
	    创建编辑器</button>
	    <button onclick="deleteEditor()">
	    删除编辑器</button>
	</div>-->
</div>
<div class="btn">
	<button onclick="save()">保存</button>
</div>

<script type="text/javascript" src="/fe/js/lib/jquery-1.11.2.min.js"></script>
<script src="/fe/js/common/base.js"></script>
<script src="/fe/js/space/dailyeditor.js"></script>
<c:if test="${!empty dailyData}">
    <script>
        var DailyObj = JSON.parse('${dailyData}');
        setTimeout(function(){
            setContent(DailyObj.content);
        },1000);
        $("#title").val(DailyObj.title);
        setSelector("cateSelect",DailyObj.cate);
    </script>
</c:if>
</body>
</html>