import com.test.spider.Spider;

import java.util.Date;

/**
 * Add by jianhan on 2017/12/28
 */
public class Test {
    public static void main(String[] args) {
        String url = "http://www.infoq.com/cn/java/articles/12";
        String savePath = System.getProperty("user.dir")+"/temp_html/" + "temphtml-" + new Date().getTime() + ".html";
        Spider.saveHtml(url, savePath);
    }
}
