package cn.edu.uoh.learnandroid.util;

public class News163 {
	private String title;
	private String source;
	private String url;
	private String imgsrc;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	
	@Override
	public String toString() {
		return "News [title=" + title + ", source=" + source + ", url=" + url
				+ ", imgsrc=" + imgsrc + "]\n";
	}
}
