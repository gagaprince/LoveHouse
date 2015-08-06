package wang.gagalulu.lovehouse.weixin.config;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.weixin.bean.WXAccessToken;
import wang.gagalulu.lovehouse.weixin.bean.WXbasic;
import wang.gagalulu.lovehouse.weixin.services.WeiXinDaoService;

@Service
public class WeiXinConfig {
	private Configuration config;
	private static final Logger logger =  Logger.getLogger(WeiXinConfig.class);
	
	private WXAccessToken wxAccessToken;
	private WXbasic wxBasic;
	
	private String jsapiTicket;
	
	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	private String jsapiTicketGetUrl ;
	@Autowired
	private WeiXinDaoService wxDaoService;
	
	public WXbasic getWxBasic() {
		if(wxBasic==null){
			wxBasic = new WXbasic();
			wxBasic.setAppId(get("appid"));
			wxBasic.setAppSecret(get("AppSecret"));
		}
		return wxBasic;
	}

	public void setWxBasic(WXbasic wxBasic) {
		this.wxBasic = wxBasic;
	}

	@PostConstruct
	private void init() {
		logger.info("weixin config init begin");
		try {
			config = new PropertiesConfiguration("weixin.properties");// src/main/
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		initData();
		initToken();
		logger.info("weixin config init end");
	}
	
	private void initData(){
		jsapiTicketGetUrl = get("jsapi_ticket_url");
	}
	
	public String getJsapiTicketGetUrl() {
		return jsapiTicketGetUrl;
	}

	public void setJsapiTicketGetUrl(String jsapiTicketGetUrl) {
		this.jsapiTicketGetUrl = jsapiTicketGetUrl;
	}

	private void initToken(){
		wxAccessToken = wxDaoService.getWXAccessTokenFromDb();
	}

	public String get(String key) {
		if (config == null) {
			init();
		}
		return config.getString(key);
	}
	
	
	public void setAccessToken(WXAccessToken token){
		wxAccessToken = token;
	}
	
	public WXAccessToken getAccessToken(){
		return wxAccessToken;
	}
}
