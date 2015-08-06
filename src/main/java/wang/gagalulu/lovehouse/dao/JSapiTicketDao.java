package wang.gagalulu.lovehouse.dao;

import wang.gagalulu.lovehouse.bean.pojo.JsapiTicket;

public interface JSapiTicketDao {
	public void save(JsapiTicket ticket);
	public JsapiTicket getLastestTicket();
}
