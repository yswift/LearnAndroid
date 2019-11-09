package cn.edu.uoh.learnandroid.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class HttpTools {
    /**
     * 访问url，获取string类型的结果，一般是html或json
     * @param url 目标url
     * @return string类型的结果，一般是html或json
     * @throws IOException 获取失败
     */
    public static String getString(String url) throws IOException {
        try (InputStream is = doGet(url)) {
            return readString(is, "UTF8");
        }
    }

    /**
     * 访问url，获取byte[]类型的结果，一般图像
     * @param url 目标url
     * @return byte[]类型的结果，一般图像
     * @throws IOException 获取失败
     */
    public static byte[] getBytes(String url) throws IOException {
        try (InputStream is = doGet(url)) {
            return readBytes(is);
        }
    }

    public static InputStream doGet(String url) throws IOException {
        URL uurl = new URL(url);
        HttpURLConnection urlc = (HttpURLConnection) uurl.openConnection();
        // 把自己伪装成浏览器
        urlc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
        urlc.connect();
        if (urlc.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("链接失败, ResponseCode = " + urlc.getResponseCode());
        }
        InputStream is = urlc.getInputStream();
        // 检查获取的结果是否是被压缩过的
        if ("gzip".equals(urlc.getContentEncoding())) {
            // 如果是用GZIPInputStream包裹节压缩
            is = new GZIPInputStream(is);
        }
        return is;
    }

    /**
     * 从流中读取所有内容，直到流结束，并转换为String格式。
     *
     * @param is
     *            输入流
     * @return 读取到字符串
     * @throws IOException
     */
    private static String readString(InputStream is, String charset) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        readTo(is, bos);
        return bos.toString(charset);
    }

    /**
     * 流中读取所有内容，直到流结束。
     * @param is
     * @return byte数组
     * @throws IOException
     */
    private static byte[] readBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        readTo(is, bos);
        return bos.toByteArray();
    }

    /**
     * 从流中读取所有内容，并写入到另外的流中。
     *
     * @param is
     *            输入流
     * @param os
     *            输出流
     * @throws IOException
     */
    private static void readTo(InputStream is, OutputStream os) throws IOException {
        // 定义读取的缓冲区
        final int len = 1024;
        byte data[] = new byte[len];
        while (true) {
            int rlen = is.read(data);
            if (rlen == -1) {
                break;
            }
            os.write(data, 0, rlen);
        }
    }
}
