<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
  	var wxShareConfig = ${wxShareConfig};
  </script>
<script>
	//alert("wxShareConfig url:"+wxShareConfig.url);
	//alert("current page url:"+location.href.split('#')[0]);


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
		    jsApiList: ['onMenuShareTimeline'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
		    success: function(res) {
		    	alert(JSON.stringify(res));
		        // 以键值对的形式返回，可用的api值true，不可用为false
		        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
		    }
		});
	}
	
</script>