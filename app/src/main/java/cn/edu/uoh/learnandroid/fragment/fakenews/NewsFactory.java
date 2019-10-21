package cn.edu.uoh.learnandroid.fragment.fakenews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsFactory {
    static List<News> getNews() {
        List<News> newsList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            String title = "This is news title " + i;
            String content = getRandomLengthContent("This is news content " + i + ". ");
            News news = new News(title, content);
            newsList.add(news);
        }
        return newsList;
    }

    static String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }
}
