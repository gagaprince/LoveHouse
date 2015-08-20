package wang.gagalulu.lovehouse.daily.bean;

import java.util.List;

public class DailyContextBean {
	private String title;
	private String headTitle;
	private String time;
	private String shareTitle;
	private String shareDesc;
	private String shareImg;
	private String linkId;
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
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareDesc() {
		return shareDesc;
	}
	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}
	public String getShareImg() {
		return shareImg;
	}
	public void setShareImg(String shareImg) {
		this.shareImg = shareImg;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	
	
}
