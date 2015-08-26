package wang.gagalulu.lovehouse.bean.pojo;

public class ResultJsonModel {
	private Status bstatus;
	private Object data;
	
	public ResultJsonModel(){
		bstatus = new Status();
	}
	
	public Status getBstatus() {
		return bstatus;
	}


	public void setBstatus(Status bstatus) {
		this.bstatus = bstatus;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public class Status{
		private int code;
		private String desc;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Status(){
			this.code=0;
			this.desc="";
		}
	}
}
