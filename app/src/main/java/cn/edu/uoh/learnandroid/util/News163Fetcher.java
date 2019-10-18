package cn.edu.uoh.learnandroid.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class News163Fetcher {
	private static final String host = "https://3g.163.com";
	// 网易新闻API的URL，需要两个参数：新闻开始位置，新闻条数 
	private static final String newsUrl = host + "/touch/reconstruct/article/list/BBM54PGAwangning/%d-%d.html";
	
//	娱乐：/touch/reconstruct/article/list/BA10TA81wangning/0-10.html
//	体育：/touch/reconstruct/article/list/BA8E6OEOwangning/0-10.html
//	财经：/touch/reconstruct/article/list/BA8EE5GMwangning/0-10.html
//	军事：/touch/reconstruct/article/list/BAI67OGGwangning/0-10.html
//	科技：/touch/reconstruct/article/list/BA8D4A3Rwangning/0-10.html
//	手机：/touch/reconstruct/article/list/BAI6I0O5wangning/0-10.html
//	数码：/touch/reconstruct/article/list/BAI6JOD9wangning/0-10.html
//	时尚：/touch/reconstruct/article/list/BA8F6ICNwangning/0-10.html
//	游戏：/touch/reconstruct/article/list/BAI6RHDKwangning/0-10.html
//	教育：/touch/reconstruct/article/list/BA8FF5PRwangning/0-10.html
//	健康：/touch/reconstruct/article/list/BDC4QSV3wangning/0-10.html
//	旅游：/touch/reconstruct/article/list/BEO4GINLwangning/0-10.html
	
	/**
	 * 获取新闻列表
	 * @param startNum 新闻开始位置
	 * @param size 获取新闻条数
	 * @return 新闻列表
	 * @throws IOException
	 */
	public ArrayList<News163> getNewsList(int startNum, int size) throws IOException {
		String url = String.format(newsUrl, startNum, size);
		String result = HttpClient.getString(url);
		return parse(result);
	}
	
	/**
	 * 把string的新闻，解析成新闻列表对象
	 * @param result 网络获取的string类型数据
	 * @return 新闻列表
	 */
	private ArrayList<News163> parse(String result) {
		// 使用正则表达式处理
		Pattern p = Pattern.compile("artiList\\(\\{\\\"BBM54PGAwangning\\\":(.+)\\}\\)");
		Matcher m = p.matcher(result);
		if (!m.matches()) {
			return new ArrayList<>(0);
		}
		String json = m.group(1);
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<ArrayList<News163>>(){}.getType());
	}
}
