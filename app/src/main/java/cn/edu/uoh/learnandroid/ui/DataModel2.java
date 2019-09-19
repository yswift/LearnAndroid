package cn.edu.uoh.learnandroid.ui;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import cn.edu.uoh.learnandroid.BR;


public class DataModel2 extends BaseObservable {
    private String name;
    private int age;

    public DataModel2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(cn.edu.uoh.learnandroid.BR.name);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(cn.edu.uoh.learnandroid.BR.age);
    }
}
