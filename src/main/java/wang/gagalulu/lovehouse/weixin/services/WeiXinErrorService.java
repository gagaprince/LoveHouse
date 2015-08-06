package wang.gagalulu.lovehouse.weixin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wang.gagalulu.lovehouse.weixin.config.WeiXinConfig;
import wang.gagalulu.lovehouse.weixin.enums.WeiXinError;

@Service
public class WeiXinErrorService {
	@Autowired
	private WeiXinConfig wxConfig;
	public Object filter(int errorCode,ErrorCallBack callBack){
		Object returnO = null;
		switch (errorCode) {
			case WeiXinError.TOKEN_EXPIRED:
				returnO = doWhenTokenExpired(callBack);
				break;
		}
		return returnO;
	}
	
	private Object doWhenTokenExpired(ErrorCallBack callBack){
		wxConfig.setAccessToken(null);
		return callBack.onErrorFinish();
	}
	
	public interface ErrorCallBack{
		public Object onErrorFinish();
	}
}
