<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
  	var wxShareConfig = ${wxShareConfig};
  </script>
<script>
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
</script>