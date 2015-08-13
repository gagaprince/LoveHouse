package wang.gagalulu.lovehouse.weixin.config;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.JsapiTicket;
import wang.gagalulu.lovehouse.dao.JSapiTicketDao;
import wang.gagalulu.lovehouse.weixin.bean.WXAccessToken;
import wang.gagalulu.lovehouse.weixin.bean.WXbasic;
import wang.gagalulu.lovehouse.weixin.services.WeiXinDaoService;

@Service
public class WeiXinConfig {
	private Configuration config;
	private static final Logger logger =  Logger.getLogger(WeiXinConfig.class);
	
	private WXAccessToken wxAccessToken;
	private WXbasic wxBasic;
	private String wxShareHost;
	private String userMsgUrl;
	
	
	public String getUserMsgUrl() {
		return userMsgUrl;
	}

	public void setUserMsgUrl(String userMsgUrl) {
		this.userMsgUrl = userMsgUrl;
	}

	public String getWxShareHost() {
		return wxShareHost;
	}

	public void setWxShareHost(String wxShareHost) {
		this.wxShareHost = wxShareHost;
	}

	private String jsapiTicket;
	
	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	private String jsapiTicketGetUrl ;
	private String loginAccessTokenUrl;
	
	
	@Autowired
	private WeiXinDaoService wxDaoService;
	@Autowired
	private JSapiTicketDao jsapiTicketDao;
	
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
		logger.info("weixin config init end");
	}
	
	private void initData(){
		jsapiTicketGetUrl = get("jsapi_ticket_url");
		loginAccessTokenUrl = get("login_access_token");
		userMsgUrl = get("login_usermsgurl");
		wxShareHost = get("AppHost");
		initTicket();
		initToken();
	}
	
	public String getLoginAccessTokenUrl() {
		return loginAccessTokenUrl;
	}

	public void setLoginAccessTokenUrl(String loginAccessTokenUrl) {
		this.loginAccessTokenUrl = loginAccessTokenUrl;
	}

	public String getJsapiTicketGetUrl() {
		return jsapiTicketGetUrl;
	}

	public void setJsapiTicketGetUrl(String jsapiTicketGetUrl) {
		this.jsapiTicketGetUrl = jsapiTicketGetUrl;
	}

	private void initTicket(){
		JsapiTicket ticketModel = jsapiTicketDao.getLastestTicket();
		if(ticketModel!=null){
			jsapiTicket = ticketModel.getTicket();
		}
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
