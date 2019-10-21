package cn.edu.uoh.learnandroid.fragment.fakenews;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class News extends BaseObservable {
    private String title;

    private String content;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(cn.edu.uoh.learnandroid.BR.title);
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(cn.edu.uoh.learnandroid.BR.content);
    }
}
