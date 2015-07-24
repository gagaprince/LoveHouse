package wang.gagalulu.lovehouse.dao;

import wang.gagalulu.lovehouse.bean.pojo.WeiXinToken;

public interface WeiXinTokenDao {
	public void save(WeiXinToken wxtoken);
	public WeiXinToken getLastestToken();
}
