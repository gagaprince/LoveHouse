$(document).ready(function(){
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

function initListener(){
	$("#contentAdd").click(function(){
		$("#contentValues").append(tplMainItem(new Date().getTime()));
	});
	$("#contentValues").on("click",".delete",function(){
		var length = $("#contentValues").find(".delete").length;
		if(length>1){
			$(this).parent().remove();
		}
	});
}