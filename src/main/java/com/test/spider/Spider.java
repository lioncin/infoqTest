package com.test.spider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Add by jianhan on 2017/12/28
 */
public class Spider {
    public static void saveHtml(String url, String savePath) {
        try {
            File dest = new File(savePath);
            //接收字节输入流
            InputStream is;
            //字节输出流
            FileOutputStream fos = new FileOutputStream(dest);

            URL temp = new URL(url);
            URLConnection uc = temp.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/5.0 (iPad; U; CPU OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5");
            is = temp.openStream();

            //为字节输入流加缓冲
            BufferedInputStream bis = new BufferedInputStream(is);
            //为字节输出流加缓冲
            BufferedOutputStream bos = new BufferedOutputStream(fos);

            int length;

            byte[] bytes = new byte[1024*20];
            while((length = bis.read(bytes, 0, bytes.length)) != -1){
                fos.write(bytes, 0, length);
            }

            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("openStream流错误，跳转get流");
            //如果上面的那种方法解析错误
            //那么就用下面这一种方法解析
            try{
                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; MALC)")
                        .timeout(3000)
                        .get();

                File dest = new File("src/temp_html/" + "我是名字.html");
                if(!dest.exists())
                    dest.createNewFile();
                FileOutputStream out=new FileOutputStream(dest,false);
                out.write(doc.toString().getBytes("utf-8"));
                out.close();

            }catch (IOException E) {
                E.printStackTrace();
                System.out.println("get流错误，请检查网址是否正确");
            }

        }
    }

    public static void getLocalhtml(String path) {
        //读取本地html的路径
        File file = new File(path);
        //生成一个数组用来存储这些路径下的文件名
        File[] array = file.listFiles();
        //写个循环读取这些文件的名字

        for(int i=0;i<array.length;i++){
            try{
                if(array[i].isFile()){
                    //文件名字
                    System.out.println("正在解析网址：" + array[i].getName());
                    //文件地址加文件名字
                    //System.out.println("#####" + array[i]);
                    //一样的文件地址加文件名字
                    //System.out.println("*****" + array[i].getPath());

                    //下面开始解析本地的html
                    Document doc = Jsoup.parse(array[i], "UTF-8");
                    // 扩展名为.png的图片
                    //Elements pngs = doc.select("img[src$=.png]");
                    //Element masthead = doc.select("div.masthead").first();
                    //得到html的新闻部分
                    Element content = doc.getElementById("content");
                    Elements news_type1s = content.getElementsByClass("news_type1");
                    Elements news_type2s = content.getElementsByClass("news_type2");
                    for( Element news_type1 : news_type1s) {
                        Element titleElement = news_type1.getElementsByTag("h2").first();
                        Element titleLink = titleElement.getElementsByTag("a").first();
                        String link = titleLink.attr("href");
                        String linkText = titleLink.text();
                        Element subjectElement = news_type1.getElementsByTag("p").first();
                        String subjectText = subjectElement.text();
                    }
                }
            }catch (Exception e) {
                System.out.println("网址：" + array[i].getName() + "解析出错");
                e.printStackTrace();
                continue;
            }
        }
    }
}
