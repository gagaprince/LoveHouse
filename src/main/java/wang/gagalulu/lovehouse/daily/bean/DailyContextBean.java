package wang.gagalulu.lovehouse.daily.bean;

import java.util.List;

public class DailyContextBean {
	private String title;
	private String headTitle;
	private String time;
	private List<DailyBean> dailyList;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public List<DailyBean> getDailyList() {
		return dailyList;
	}
	public void setDailyList(List<DailyBean> dailyList) {
		this.dailyList = dailyList;
	}
	
	
}
