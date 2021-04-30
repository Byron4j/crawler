package org.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 专栏
 */
@Slf4j
public class CsdnSpider4SpecialColumn {

    private static String url = "https://blog.csdn.net/yhahaha_/category_9286844.html";

    public static void main(String[] args) throws Throwable {
        RequestConfig config = RequestConfig.custom().setSocketTimeout(10000) // 设置读取超时时间

                .setConnectTimeout(5000)  // 设置连接超时时间

                .build();
        CloseableHttpClient httpClient = HttpClients.createDefault(); // 获取HttpClient实例

        CloseableHttpResponse response = null;
        try {

            HttpGet httpget = new HttpGet(url); // 创建httpget实例

            httpget.setConfig(config);

            response = httpClient.execute(httpget);

        } catch (ClientProtocolException e) {

            log.error(url + "-ClientProtocolException");

        } catch (IOException e) {

            log.error(url + "-IOException");

        }

        // 解析
        if (response != null) {
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                String webPageContent = EntityUtils.toString(entity, "utf-8");

                parseHomeWebPage(webPageContent);
            }else {
                log.error("访问失败,状态不是200 ok.");
            }
        }
    }

    private static void parseHomeWebPage(String webPageContent) throws IOException {

        if ("".equals(webPageContent)) {

            return;

        }

        Document doc = Jsoup.parse(webPageContent);


        Elements select = doc.select(".column_article_list li a");

        for (Element element : select) {
            String articleLinkUrl = element.attr("href");
            CsdnSpider4Article.spiderArticleByLinkUrl(articleLinkUrl);
        }

        log.info("-------------------- OVER --------------------");


    }

}
