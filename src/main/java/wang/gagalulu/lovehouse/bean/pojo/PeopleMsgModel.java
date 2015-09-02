package wang.gagalulu.lovehouse.bean.pojo;

import java.util.Date;

public class PeopleMsgModel {
	private Integer id;
	private String netName;
	private String name;
	private String oldHome;
	private String nowHome;
	private String work;
	private String interest;
	private String lovebook;
	private String lovemusic;
	private String constellation;//爱好
	private String desc;
	private Date createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOldHome() {
		return oldHome;
	}
	public void setOldHome(String oldHome) {
		this.oldHome = oldHome;
	}
	public String getNowHome() {
		return nowHome;
	}
	public void setNowHome(String nowHome) {
		this.nowHome = nowHome;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getLovebook() {
		return lovebook;
	}
	public void setLovebook(String lovebook) {
		this.lovebook = lovebook;
	}
	public String getLovemusic() {
		return lovemusic;
	}
	public void setLovemusic(String lovemusic) {
		this.lovemusic = lovemusic;
	}
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
