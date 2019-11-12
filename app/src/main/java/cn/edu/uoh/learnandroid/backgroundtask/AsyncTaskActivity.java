package cn.edu.uoh.learnandroid.backgroundtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.edu.uoh.learnandroid.R;

public class AsyncTaskActivity extends AppCompatActivity {
    ProgressBar progressBar;
    MyTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        setTitle("AsyncTask");
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTask(null);
    }

    public void startTask(View view) {
        if (task!=null) {
            task.cancel(true);
        }
        task = new MyTask();
        task.execute("exec");
    }

    public void stopTask(View view) {
        if (task!=null) {
            task.cancel(true);
            task = null;
        }
    }

    class MyTask extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                for (int count=1; count<=100; count++) {
                    // 可调用publishProgress（）显示进度, 之后将执行onProgressUpdate（）
                    publishProgress(count);
                    // 模拟耗时任务, 随机耗时500-1000ms
                    long time = (long)(Math.random()*100 + 100);
                    Thread.sleep(time);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
                return "error: " + e;
            }
            return "finished";
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
        // 作用：接收线程任务执行结果、将执行结果显示到UI组件
        @Override
        protected void onPostExecute(String result) {
            // 执行完毕后，则更新UI
            Toast.makeText(AsyncTaskActivity.this, "异步任务完成", Toast.LENGTH_SHORT).show();
        }
    }
}
