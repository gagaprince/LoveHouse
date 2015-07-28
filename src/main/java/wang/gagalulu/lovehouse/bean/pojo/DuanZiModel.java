package wang.gagalulu.lovehouse.bean.pojo;

public class DuanZiModel {
	private String content;
	private Long id ;
	private String date;
	private String updateTime;
	private String fromId;
	private int type;
	
	public DuanZiModel(){}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFromId() {
		return fromId;
	}

	public void setFromId(String fromId) {
		this.fromId = fromId;
	}

	public DuanZiModel(String content,String date,String updateTime,String fromId,int type){
		this.content = content;
		this.date = date;
		this.updateTime = updateTime;
		this.fromId = fromId;
		this.type = type;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
