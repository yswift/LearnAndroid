package cn.edu.uoh.learnandroid.ui;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

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

//    @BindingAdapter("android:text")
//    public static void setText(TextView view, int value) {
//        view.setText(Integer.toString(value));
//    }
//
//    @InverseBindingAdapter(attribute = "android:text")
//    public static int getText(TextView view) {
//        return Integer.parseInt(view.getText().toString());
//    }
}
