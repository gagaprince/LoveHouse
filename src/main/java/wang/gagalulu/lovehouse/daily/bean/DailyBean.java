package wang.gagalulu.lovehouse.daily.bean;

public class DailyBean {
	public final static String DAILY_LINE="line";
	public final static String DAILY_IMG="img";
	public final static String DAILY_CONTENT="content";
	public final static String DAILY_CONTENT_TITLE="title";
	//daily元素的type
	private String type;
	private String content;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public DailyBean(){}
	public DailyBean(String type,String content){
		this.type = type;
		this.content = content;
	}
}
