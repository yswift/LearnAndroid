package cn.edu.uoh.learnandroid.ui;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import cn.edu.uoh.learnandroid.R;

public class WeatherInfoAdapter
        extends RecyclerView.Adapter<WeatherInfoAdapter.ViewHolder> {

    private WeatherInfo[] weatherInfoList;

    public WeatherInfoAdapter(WeatherInfo[] weatherInfoList) {
        this.weatherInfoList = weatherInfoList;
    }

    @NonNull
    @Override
    public WeatherInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_weather_info, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherInfoAdapter.ViewHolder holder, int position) {
        // 获取数据
        WeatherInfo wi = weatherInfoList[position];

        CardView cardView = holder.cardView;
        // 绑定图像
        ImageView iv = cardView.findViewById(R.id.weather_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),
                wi.getImageResourceId());
        iv.setImageDrawable(drawable);
        // 绑定文本
        TextView tv = cardView.findViewById(R.id.weathe_date);
        tv.setText(wi.getDate());
        // 处理单击
        cardView.setOnClickListener((v) -> {
            Snackbar.make(v, "单击: " + wi.getDate(), Snackbar.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return weatherInfoList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }
}
