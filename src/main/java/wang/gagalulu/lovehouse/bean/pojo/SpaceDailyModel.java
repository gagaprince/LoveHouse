package wang.gagalulu.lovehouse.bean.pojo;

import java.util.Date;

public class SpaceDailyModel {
	private Long id;
	private int peopleId;
	private String title;
	private String content;
	private String cate;
	private Date createTime;
	private String preContent;
	
	public String getPreContent() {
		return preContent;
	}
	public void setPreContent(String preContent) {
		this.preContent = preContent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPeopleId() {
		return peopleId;
	}
	public void setPeopleId(int peopleId) {
		this.peopleId = peopleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
