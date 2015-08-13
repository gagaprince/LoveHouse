package wang.gagalulu.lovehouse.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
@Service
public class CookieUtil {
	public void addCookie(HttpServletResponse response,String key,String value,String path,int time){
		Cookie cookie = new Cookie(key,value);
		cookie.setPath(path);
		cookie.setMaxAge(time);
		response.addCookie(cookie);
	}
	public void addCookie(HttpServletResponse response,String key,String value,String path){
		Cookie cookie = new Cookie(key,value);
		cookie.setPath(path);
		response.addCookie(cookie);
	}
	
	public String getCookie(HttpServletRequest requst,String key){
		Cookie[] cookies = requst.getCookies();
		if(null!=cookies){
			for(Cookie cookie:cookies){
				if(key.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
