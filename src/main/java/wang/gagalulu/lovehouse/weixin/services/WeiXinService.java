package wang.gagalulu.lovehouse.weixin.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.util.HttpUtil;
import wang.gagalulu.lovehouse.weixin.bean.WXAccessToken;
import wang.gagalulu.lovehouse.weixin.bean.WXIps;
import wang.gagalulu.lovehouse.weixin.bean.WXbasic;
import wang.gagalulu.lovehouse.weixin.config.WeiXinConfig;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class WeiXinService {
	@Autowired
	private WeiXinDaoService wxDaoService;
	@Autowired
	private WeiXinConfig wxConfig;
	
	private static final Logger logger =  Logger.getLogger(WeiXinService.class);
	/**
	 * 获取一个wxToken
	 * @return
	 */
	public WXAccessToken iNeedAToken(){
		WXAccessToken wxToken = giveMeNoPreparedToken();
		getWeiXinAccessToken(wxToken);
		return wxToken;
	}
	/**
	 * 通过http请求获取token完整信息
	 */
	private void getWeiXinAccessToken(WXAccessToken wxAccessToken){
		String url = getWeixinAccessTokenUrl(wxAccessToken);
		String result = HttpUtil.getContentByUrl(url);
		logger.info("get weixin accessToken:"+result);
		JSONObject accessJson = JSONObject.parseObject(result);
		String accessToken = (String)accessJson.get("access_token");
		if(accessToken!=null){
			wxAccessToken.setAccessToken(accessToken);
			wxConfig.setAccessToken(wxAccessToken);
			wxDaoService.saveTokenInDb(wxAccessToken);
		}else{
			logger.error((String)accessJson.get("errmsg"));
		}
		
	}
	/**
	 * 获取一个微信基本信息bean
	 * @return
	 */
	private WXbasic giveMeTheWXbasic(){
		WXbasic wxBasic = wxConfig.getWxBasic();
		return wxBasic;
	}
	/**
	 * 获取一个初始化token 没有token信息
	 * @return
	 */
	private WXAccessToken giveMeNoPreparedToken(){
		WXAccessToken wxAccessToken = new WXAccessToken();
		wxAccessToken.setwXbasic(giveMeTheWXbasic());
		wxAccessToken.setGetUrl(wxConfig.get("tokenUrl"));
		wxAccessToken.setGrantType(wxConfig.get("grant_type"));
		return wxAccessToken;
	}
	/**
	 * 拼装微信获取token的url
	 * @param wxAccessToken
	 * @return
	 */
	private String getWeixinAccessTokenUrl(WXAccessToken wxAccessToken){
		WXbasic wxBasic = wxAccessToken.getwXbasic();
		String url = wxAccessToken.getGetUrl()+"?grant_type="+wxAccessToken.getGrantType()+"&appid="+wxBasic.getAppId()+"&secret="+wxBasic.getAppSecret();
		return url;
	}
	
	public WXIps iNeedWxIps(){
		WXAccessToken wxAccessToken = getCanUseToken();
		WXIps wxIps = new WXIps();
		wxIps.setGetUrl(wxConfig.get("ipUrl"));
		wxIps.setWxAccessToken(wxAccessToken);
		getWeiXinIps(wxIps);
		return wxIps;
	}
	
	private void getWeiXinIps(WXIps wxIps){
		String url = getWeixinIpsUrl(wxIps);
		String result = HttpUtil.getContentByUrl(url);
		logger.info("get weixin ips:"+result);
		JSONObject ipsJson = JSONObject.parseObject(result);
		JSONArray ipsArray = ipsJson.getJSONArray("ip_list");
		if(ipsArray!=null){
			List<String> ips = new ArrayList<String>();
			for(int i=0;i<ipsArray.size();i++){
				String ip = ipsArray.getString(i);
				ips.add(ip);
			}
			wxIps.setIps(ips);
		}else{
			logger.error((String)ipsJson.get("errmsg"));
		}
	}
	
	public WXAccessToken getCanUseToken(){
		WXAccessToken token = wxConfig.getAccessToken();
		if(token==null){
			token = iNeedAToken();
		}
		return token;
	}
	
	private String getWeixinIpsUrl(WXIps wxIps){
		String url = wxIps.getGetUrl()+"?access_token="+wxIps.getWxAccessToken().getAccessToken();
		return url;
	}
	
	public void reloadToken(){
		wxConfig.setAccessToken(null);
	}
}
