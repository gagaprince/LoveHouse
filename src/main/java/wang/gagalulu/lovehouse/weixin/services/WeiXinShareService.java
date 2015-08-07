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
	
	private static final Logger logger =  Logger.getLogger(WeiXinShareService.class);
	public WXShareConfigModel getCanUseShareConfig(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		logger.info("url:"+url);
		WXShareConfigModel shareModel = new WXShareConfigModel();
		shareModel.setAppId(wxConfig.getWxBasic().getAppId());
		shareModel.setTimestamp(new Date().getTime()+"");
		shareModel.setNonceStr(getRandomString());
		shareModel.setUrl(url);
		signat(shareModel);
		return shareModel;
	}
	
	private void signat(WXShareConfigModel shareModel){
		List<String> signList = new ArrayList<String>();
		signList.add(shareModel.getAppId());
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
