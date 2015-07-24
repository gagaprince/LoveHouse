package wang.gagalulu.lovehouse.weixin.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.bean.pojo.WeiXinToken;
import wang.gagalulu.lovehouse.dao.WeiXinTokenDao;
import wang.gagalulu.lovehouse.weixin.bean.WXAccessToken;

@Service
public class WeiXinDaoService {
	@Autowired
	private WeiXinTokenDao wxTokenDao;
	
	public void saveTokenInDb(WXAccessToken wxAccessToken){
		WeiXinToken wxToken = new WeiXinToken();
		wxToken.setToken(wxAccessToken.getAccessToken());
		wxToken.setCreateTime(new Date());
		wxTokenDao.save(wxToken);
	}
	
	public WXAccessToken getWXAccessTokenFromDb(){
		WeiXinToken wxToken = wxTokenDao.getLastestToken();
		if(wxToken==null){
			return null;
		}
		WXAccessToken wxAccessToken = new WXAccessToken();
		wxAccessToken.setAccessToken(wxToken.getToken());
		return wxAccessToken;
	}
}
