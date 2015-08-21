package wang.gagalulu.lovehouse.weixin.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import wang.gagalulu.lovehouse.bean.pojo.MenuModel;
import wang.gagalulu.lovehouse.dao.MenuDao;
import wang.gagalulu.lovehouse.util.HttpUtil;
import wang.gagalulu.lovehouse.weixin.bean.WXAccessToken;
import wang.gagalulu.lovehouse.weixin.config.WeiXinConfig;
import wang.gagalulu.lovehouse.weixin.services.WeiXinErrorService.ErrorCallBack;

@Service
public class WXMenuService {
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private WeiXinConfig wxConfig;
	@Autowired
	private WeiXinService wxService;
	@Autowired
	private HttpUtil httpUtil;
	@Autowired
	private WeiXinErrorService wxErrorService;
	
	
	private static final Logger logger =  Logger.getLogger(WXMenuService.class);
	public String createMenu(String menuStr){
		if(null == menuStr){
			MenuModel menuModel = menuDao.getLastMenu();
			menuStr = menuModel.getMenu();
		}
		final String menuStrFinal = menuStr;
		String postUrl=giveMePostUrl();
		logger.info("创建菜单的菜单内容："+menuStrFinal);
		String result = httpUtil.getContentByPost(postUrl, menuStrFinal);
		JSONObject menuJson = JSONObject.parseObject(result);
		int menuErrorCode = menuJson.getInteger("errcode");
		if(menuErrorCode!=0){
			logger.error("出错啦："+menuJson.getString("errmsg"));
			result = (String)wxErrorService.filter(menuErrorCode,new ErrorCallBack() {
				@Override
				public Object onErrorFinish() {
					return createMenu(menuStrFinal);
				}
			});
		}
		return result;
	}
	
	private String giveMePostUrl(){
		String menuUrl = wxConfig.get("menu_create_url");
		WXAccessToken token = wxService.getCanUseToken();
		String accessToken = token.getAccessToken();
		menuUrl = menuUrl+"?access_token="+accessToken;
		logger.info("创建菜单请求的url："+menuUrl);
		return menuUrl;
	}
}
