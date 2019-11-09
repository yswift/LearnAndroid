package cn.edu.uoh.learnandroid.net;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BingImageUrlTools {
    final static Pattern imageUrlPattern = Pattern.compile("g_img=\\{url:\"(\\S*)\"\\}");
    /**
     * 获取bing的背景图像url
     * @param html bing html文本
     * @return 背景图像url
     */
    public static String parseImageUrl(String html) {
        Matcher matcher = imageUrlPattern.matcher(html);
        if (matcher.find()) {
            String ret = matcher.group(1);
            ret = ret.replace("\\u0026", "&");
            return ret;
        }
        return null;
    }
}
