package com.weex.test.androidutilstestproject.utils;

import android.text.TextUtils;


import com.apkfuns.logutils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


public class HtmlUtils {
    public static Document doc;

    public static String Inputstr2Str_byteArr(InputStream in, String encode) {
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
        int len = 0;
        try {
            if (encode == null || encode.equals("")) {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            while ((len = in.read(b)) != -1) {
                sb.append(new String(b, 0, len, encode));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getHtmlString(String urlString) {
        Map<String, String> contentMap = new HashMap<>();
        try {
            URL url = null;
            url = new URL(urlString);

            URLConnection ucon = null;
            ucon = url.openConnection();

            InputStream instr = null;
            instr = ucon.getInputStream();
            String charSet = getCharset(urlString);
            if (TextUtils.isEmpty(charSet)) {
                charSet = "utf-8";
            }
            String urlContent = Inputstr2Str_byteArr(instr, charSet);
            LogUtils.d("整个html页面内容----" + urlContent);
            return urlContent;
        } catch (Exception e) {
            LogUtils.d(e.toString());
            return null;
        }
    }

    public static Map<String, String> parseHtmlString(String urlString, String urlContent) {
        Map<String, String> contentMap = new HashMap<>();
        Document document = null;
        try {
            document = Jsoup.parse(urlContent, urlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (document == null)
            return null;
        Elements elements = document.getElementsByTag("meta");
//        String content=document.firstElementSibling().text();
        contentMap.put("title", document.head().text());
        contentMap.put("url", urlString);
        LogUtils.d("头部：" + document.head());
        for (Element element : elements) {
            if (element.attr("name").equals("description")) {
                LogUtils.d("描述：" + element.attr("content"));
                contentMap.put("description", element.attr("content"));
            } else if (element.attr("name").equals("keywords")) {
                LogUtils.d("关键字" + element.attr("content"));
                contentMap.put("keywords", element.attr("content"));
            }
        }
        Elements link = document.getElementsByTag("link");
        for (Element element : link) {
            if (element.attr("href").endsWith(".ico")) {
                contentMap.put("ico", element.attr("href"));
            }
        }
        Elements media = document.select("[src]");
        int i = 0;
        for (Element src : media) {
            if (src.tagName().equals("img")) {
                if (src.attr("abs:src").contains("logo") || src.attr("abs:src").contains("icon")) {
                    LogUtils.d("网页的logo----" + src.attr("abs:src"));
                    contentMap.put("logo", src.attr("abs:src"));
                } else {
                    contentMap.put("logo" + i++, src.attr("abs:src"));
                }
            }
        }
        LogUtils.d(contentMap);
        return contentMap;
    }

    public static String getCharset(String link) {
        String result = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            conn.connect();
            String contentType = conn.getContentType();
            //在header里面找charset
            result = findCharset(contentType);
            //如果没找到的话，则一行一行的读入页面的html代码，从html代码中寻找
            if (result == null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = reader.readLine();
                while (line != null) {
                    if (line.contains("Content-Type")) {
                        result = findCharset(line);
                        break;
                    }
                    line = reader.readLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        LogUtils.d("");
        return result;
    }

    //辅助函数
    public static String findCharset(String line) {
        System.out.println(line);
        int x = line.indexOf("charset=");
        int y = line.lastIndexOf('\"');
        if (x < 0)
            return null;
        else if (y >= 0)
            return line.substring(x + 8, y);
        else
            return line.substring(x + 8);
    }
}
