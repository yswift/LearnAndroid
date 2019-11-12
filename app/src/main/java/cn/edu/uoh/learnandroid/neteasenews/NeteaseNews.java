package cn.edu.uoh.learnandroid.neteasenews;

public class NeteaseNews {
    private String title;
    // 新闻时间
    private String mtime;
    // 新闻图片url
    private String imgsrc;
    // 新闻url
    private String url;
    // 简短内容
    private String digest;
    // 来源
    private String source;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "\nNeteaseNews{" +
                "title='" + title + '\'' +
                ", mtime='" + mtime + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", url='" + url + '\'' +
                ", digest='" + digest + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
