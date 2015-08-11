var wxBridge = (function(wx,$){
	
	var shareObj = {
		"title":document.title,
		"link":self.location.href+"",
		"desc":document.title,
		"imgUrl":"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2117727038,2641018931&fm=116&gp=0.jpg"
	}
	
	wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: wxShareConfig.appId, // 必填，公众号的唯一标识
	    timestamp: wxShareConfig.timestamp, // 必填，生成签名的时间戳
	    nonceStr: wxShareConfig.nonceStr, // 必填，生成签名的随机串
	    signature: wxShareConfig.signature,// 必填，签名，见附录1
	    jsApiList: [
	    	"onMenuShareTimeline",//分享到朋友圈
	    	"onMenuShareAppMessage"//分享到朋友
	    ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
		onWXReady();
	});
	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
	function onWXReady(){
		wx.checkJsApi({
		    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
		    success: function(res) {
		    	var result = res.checkResult;
		    	if(result["onMenuShareTimeline"]){
		    		initMenuShareTimelineListener();
		    	}
				if(result["onMenuShareAppMessage"]){
		    		initMenuShareTimelineListener();
		    	}		    	
		        // 以键值对的形式返回，可用的api值true，不可用为false
		        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		    }
		});
	}
	
	function initMenuShareTimelineListener(){
		wx.onMenuShareTimeline({
		    title: shareObj["title"]||"", // 分享标题
		    link: shareObj["link"]||"", // 分享链接
		    imgUrl: shareObj["imgUrl"], // 分享图标
		    success: function () { 
		        
		    },
		    cancel: function () { 
		        
		    }
		});
	
	}
	
	function initMenuShareTimelineListener(){
		wx.onMenuShareAppMessage({
		    title: shareObj["title"]||"", // 分享标题
		    desc: shareObj["desc"]||"", // 分享描述
		    link: shareObj["link"]||"", // 分享链接
		    imgUrl: shareObj["imgUrl"], // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        
		    },
		    cancel: function () { 
				
		    }
		});
	}
	
	
	
	//out function 
	function setTitle(title){
		shareObj["title"] = title||shareObj["title"];
	}
	function setDesc(desc){
		shareObj["desc"] = desc||shareObj["desc"];
	}
    function setLink(link){
        shareObj["link"] = link||shareObj["link"];
    }
    function setImgUrl(imgUrl){
        shareObj["imgUrl"] = imgUrl||shareObj["imgUrl"];
    }
    function setShareObj(shareObjSet){
        shareObj = shareObjSet;
    }
	
	return {
        setTitle:setTitle,
        setDesc:setDesc,
        setLink:setLink,
        setImgUrl:setImgUrl,
        setShareObj:setShareObj
	}
	
})(wx,$);
	