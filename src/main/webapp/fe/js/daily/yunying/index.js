$(document).ready(function(){
	init();
	initListener();
});

function tplMainItem(time){
	var mainItemTpl='<div class="main-item h-l" time="'+time+'">'+
						'<label class="radioFrame h-c" for="fontRadio'+time+'">'+
							'<div>文字</div>'+
							'<input id="fontRadio'+time+'" type="radio" name="contentType'+time+'">	'+
						'</label>'+
						'<label class="radioFrame h-c" for="picRadio'+time+'">'+
							'<div>图片</div>'+
							'<input id="picRadio'+time+'" type="radio" name="contentType'+time+'">	'+
						'</label>'+
						'<input type="textarea" placeholder="请输入" class="value-input" style="width:288px;">'+
						'<div class="delete">删除</div>'+
					'</div>';
	return mainItemTpl;
}

var contextObj=(typeof DailyContent =="undefined")?{
	"headTitle":"",
	"title":"",
	"time":"",
	"shareTitle":"",
	"shareDesc":"",
	"shareImg":"",
	"linkId":"521"
}:DailyContent;
var contextId=(typeof DailyId =="undefined")?null:DailyId;

function refreshByData(){
	initNormalMsg();
	initContentMsg();
}

function init(){
	refreshByData();
}


function initNormalMsg(){
	for(var key in contextObj){
		$("#"+key).val(contextObj[key]);
	}
}

function giveMeNormalData(){
	for(var key in contextObj){
		var val = $("#"+key).val();
		contextObj[key]= (val&&val!="")?val:contextObj[key];
	}
}

function initContentMsg(){
	var contentList = contextObj["content"];
	if(contentList){
		var contentItem = contentList[0];
		for(var i=0;contentItem;contentItem = contentList[++i]){
			if(i!=0){
				addMainItem();
			}
			renderMainItem(i,contentItem);
		}
	}
}

function renderMainItem(index,item){
	var mainItemObjs = $("#contentValues").find(".main-item");
	var itemObj = mainItemObjs[index];
	var time = $(itemObj).attr("time");
	if(item["type"]=="content"){
		$("#fontRadio"+time)[0].checked="true";
	}else if(item["type"]=="img"){
		$("#picRadio"+time)[0].checked = "true";
	}
	$(itemObj).find(".value-input").val(item["content"]);
	return item;
}

function giveMeContentItem(itemObj){
	var item = {};
	var time = $(itemObj).attr("time");
	if($("#fontRadio"+time)[0].checked){
		item["type"]="content";
	}else if($("#picRadio"+time)[0].checked){
		item["type"]="img";
	}else{
		alert("有单选按钮没有选中");
		return;
	}
	item["content"]=$(itemObj).find(".value-input").val();
	return item;
}

function giveMeContentData(){
	var content = [];
	var mainItemObjs = $("#contentValues").find(".main-item");
	var length = mainItemObjs.length;
	for(var i=0;i<length;i++){
		var mainItemObj = mainItemObjs[i];
		var item = giveMeContentItem(mainItemObj);
		if(item){
			content.push(item);
		}
	}
	contextObj["content"]=content;
}

function fetch(){
	var fetchData = {};
	fetchData["content"]=JSON.stringify(contextObj);
	if(contextId){
		fetchData["id"]=contextId;	
	}
	myUtil.api("/lovelulu/daily/save",fetchData,"POST",function(code,desc,data,res){
		console.log(res);
		if(code==0){
			alert("保存成功");
			if(!contextId){
				var dailyId = data.id;
				window.location = "/lovelulu/daily/yunying?dailyId="+dailyId;
			}
		}
	});
}

function save(){
	giveMeNormalData();
	giveMeContentData();
	fetch();
}

var itemCount = 0;

function addMainItem(){
	$("#contentValues").append(tplMainItem(itemCount++));
}

function deleteDaily(){
	myUtil.api("/lovelulu/daily/delete",{"id":contextId},"GET",function(code,desc,data,res){
		console.log(res);
		if(code==0){
			alert("删除成功");
			history.go(-1);
		}
	});
}

function initListener(){
	$("#contentAdd").click(function(){
		addMainItem();
	});
	$("#contentValues").on("click",".delete",function(){
		var length = $("#contentValues").find(".delete").length;
		if(length>1){
			$(this).parent().remove();
		}
	});
	$("#saveButton").click(function(){
		save();
	});
	$("#deleteButton").click(function(){
		if(!contextId){
			alert("还没有入库");
			return;
		}
		deleteDaily();
	});
}