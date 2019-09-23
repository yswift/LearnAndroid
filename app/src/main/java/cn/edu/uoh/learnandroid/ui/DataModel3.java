package cn.edu.uoh.learnandroid.ui;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class DataModel3 extends BaseObservable {
    private String name;

    public DataModel3(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(cn.edu.uoh.learnandroid.BR.name);
    }
}
