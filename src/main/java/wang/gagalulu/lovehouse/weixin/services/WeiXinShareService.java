package wang.gagalulu.lovehouse.weixin.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.weixin.bean.WXShareConfigModel;
import wang.gagalulu.lovehouse.weixin.config.WeiXinConfig;

@Service
public class WeiXinShareService {
	@Autowired
	private WeiXinConfig wxConfig;
	@Autowired
	private WeiXinService wxService;
	
	private static final Logger logger =  Logger.getLogger(WeiXinShareService.class);
	public WXShareConfigModel getCanUseShareConfig(HttpServletRequest request){
		String url = getUrlFromReq(request);
		logger.info("url:"+url);
		WXShareConfigModel shareModel = new WXShareConfigModel();
		shareModel.setAppId(wxConfig.getWxBasic().getAppId());
		shareModel.setTimestamp(new Date().getTime()+"");
		shareModel.setNonceStr(getRandomString());
		shareModel.setUrl(url);
		shareModel.setTicket(wxService.getCanUseTicket());
		signat(shareModel);
		return shareModel;
	}
	
	private String getUrlFromReq(HttpServletRequest request){
		String uri = request.getRequestURL().toString();
		String search = request.getQueryString();
		String url = "";
		if(search==null||"".equals(search)){
			url = uri;
		}else{
			url = uri+"?"+search;
		}
		return url;
	}
	
	private void signat(WXShareConfigModel shareModel){
		List<String> signList = new ArrayList<String>();
		signList.add(shareModel.getTicket());
		signList.add(shareModel.getNonceStr());
		signList.add(shareModel.getTimestamp());
		signList.add(shareModel.getUrl());
		Collections.sort(signList);
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<signList.size();i++){
			sb.append(signList.get(i));
			sb.append("&");
		}
		String desStr = sb.deleteCharAt(sb.length()-1).toString();
		desStr = DigestUtils.sha1Hex(desStr);
		shareModel.setSignature(desStr);
	}
	
	private String getRandomString(){
		int randomValue = new Random().nextInt(1000);
		return DigestUtils.md5Hex(randomValue+"").substring(0,16);
	}
}
