package cn.edu.uoh.learnandroid.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
	public static String getString(String url) throws IOException {
		return getString(url, "UTF-8");
	}

	public static String getString(String url, String encoding)	throws IOException {
		ByteArrayOutputStream bos = getResultStream(url);
		return bos.toString(encoding);
	}

	public static byte[] getBytes(String url) throws IOException {
		ByteArrayOutputStream bos = getResultStream(url);
		return bos.toByteArray();
	}

	private static ByteArrayOutputStream getResultStream(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		// 发送请求
		connection.connect();
		// 通过connection连接，获取输入流
		if (connection.getResponseCode() != 200) {
			return new ByteArrayOutputStream(0);
		}
		try (InputStream is = connection.getInputStream()) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream(connection.getContentLength());
			byte[] buf = new byte[1024];
			for (;;) {
				int rlen = is.read(buf);
				if (rlen == -1) {
					break;
				}
				bos.write(buf, 0, rlen);
			}
			connection.disconnect();
			return bos;
		}
	}
}
