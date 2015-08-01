package wang.gagalulu.lovehouse.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import wang.gagalulu.lovehouse.weixin.bean.WXAccessToken;
import wang.gagalulu.lovehouse.weixin.bean.WXIps;
import wang.gagalulu.lovehouse.weixin.services.WeiXinInService;
import wang.gagalulu.lovehouse.weixin.services.WeiXinMsgService;
import wang.gagalulu.lovehouse.weixin.services.WeiXinService;

import com.alibaba.fastjson.JSON;

/**
 * 用来测试和刷新微信的操作 返回结果
 * @author zidong.wang
 *
 */


@Controller
@RequestMapping("/lovelulu/wx")
public class WXController {
	@Autowired
	private WeiXinService wxService;
	@Autowired
	private WeiXinInService wxInService;
	@Autowired
	private WeiXinMsgService wxMsgService;
	
	private static final Logger logger =  Logger.getLogger(WXController.class);
	
	@RequestMapping(value = "/index")
	@ResponseBody
	public String wxCall(HttpServletRequest request,@RequestBody String xml){
		String rspStr = "";
		String method = request.getMethod();
		if("GET".equals(method)){
			logger.info("新的号码要接入了");
			//微信验证开发者
			rspStr = wxInService.doExcuteInWeiXin(request);
		}else if("POST".equals(method)){
			//接收微信消息
			rspStr = wxMsgService.doExcuteMsg(request,xml);
		}
		return rspStr;
	}
	
	@RequestMapping(value = "/getToken", method = RequestMethod.GET)
	@ResponseBody
	public String getWeiXinAccessToken(HttpServletRequest request, Model model){
		WXAccessToken wxAccessToken = wxService.getCanUseToken();
		return wxAccessToken.getAccessToken();
	}
	@RequestMapping(value = "/reloadToken", method = RequestMethod.GET)
	@ResponseBody
	public String reloadWeiXinAccessToken(HttpServletRequest request, Model model){
		wxService.reloadToken();
		WXAccessToken wxAccessToken = wxService.getCanUseToken();
		return wxAccessToken.getAccessToken();
	}
	
	@RequestMapping(value = "/getWeiXinIps", method = RequestMethod.GET)
	@ResponseBody
	public String getWeiXinIps(HttpServletRequest request, Model model){
		WXIps wxIps = wxService.iNeedWxIps();
		return JSON.toJSONString(wxIps);
	}
}
