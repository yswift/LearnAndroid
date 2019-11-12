package cn.edu.uoh.learnandroid.net;

import android.content.Context;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

/**
 * 如果在item view中存在NetWorkImageView（或者类似框架控件）时，偶尔会出现将一个条目刚刚滑动到看不见，
 * 然后将其拉回，其中的图片变为空白。
 *
 * 原因是，网络图片框架自定的ImageView中，覆写了onDetachedFromWindow方法，目的是为了在ImageView
 * 退出屏幕时及时回收Bitmap。而在RecyclerView中，当一个item被滑动到刚好看不见的位置时，触发了该item
 * 及其子View的onDetachedFromWindow，同样也就调用了setImageDrawable(null)。但是，
 * RecyclerView.Adapter.onViewRecycled方法没有立刻被调用，而要等到继续滑动RecyclerView时才调用。
 * 也就是说，RecyclerView没有立即回收已经不在显示区域的item。如果此时将该item拉回，也不会再调用
 * RecyclerView.Adapter.onBindViewHolder，也就是图片消失之后就不会再显示了，出现了开头提到的问题。
 *
 * 针对这个问题，自定义了一个ImageView，覆写onDetachFromWindown方法，
 * 让后通过Adapter中的onViewRecycled主动释放图片资源
 */
public class RecyclerViewNetworkImageView extends NetworkImageView {
    public RecyclerViewNetworkImageView(Context context) {
        super(context);
    }

    public RecyclerViewNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 防止在不显示的时候，取消图片加载请求
    }
}
