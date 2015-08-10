package wang.gagalulu.lovehouse.weixin.services;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

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
//		logger.info("url:"+url);
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
		String uri = "http://"+wxConfig.getWxShareHost()+request.getRequestURI();
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
		Map<String,String> params = new TreeMap<String, String>();
		params.put("jsapi_ticket", shareModel.getTicket());
		params.put("noncestr", shareModel.getNonceStr());
		params.put("timestamp", shareModel.getTimestamp());
		params.put("url", shareModel.getUrl());
		
		Set<String> keySet = params.keySet();

		Iterator<String> it = keySet.iterator();
		
		StringBuffer sb = new StringBuffer("");
		while(it.hasNext()){
			String key = it.next();
			sb.append(key+"="+params.get(key));
			sb.append("&");
		}
		String desStr = sb.deleteCharAt(sb.length()-1).toString();
		logger.info("排序后的字串"+desStr);
		desStr = DigestUtils.sha1Hex(desStr);
		shareModel.setSignature(desStr);
	}
	
	private String getRandomString(){
		int randomValue = new Random().nextInt(1000);
		return DigestUtils.md5Hex(randomValue+"").substring(0,16);
	}
}
