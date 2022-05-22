package com.example.reptile.service;

import com.example.reptile.entity.Review;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @description:
 * @author: XiaoGao
 * @time: 2022/5/22 13:06
 */
public class GetReview {

    public void begin() {
        GetReview t = new GetReview();
        List<Review> comments = t.getComments();
//        for (Review review : comments) {
//            System.out.println(review.readingNum + "\t" + review.time);
//        }
        t.readFile(comments);
    }

    private void readFile(List<Review> comments) {
        //用于存储html字符串
        StringBuilder sb = new StringBuilder();
        PrintStream printStream = null;
        try{
        //打开文件
//            String fileUrl = "C:\\Users\\xiaog\\Desktop\\0522.html";
            String fileUrl = "/0522gao.html";
            printStream = new PrintStream(new FileOutputStream(fileUrl));
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        List<String> formHead = new ArrayList<>();
        String title = getTitle();
        formHead.add("阅读量");
        formHead.add( "评论数");
        formHead.add("标题");
        formHead.add("发布时间");
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>"); //title
        sb.append(title);
        sb.append("</title>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
        sb.append("<style type=\"text/css\">");
        sb.append("TABLE{border-collapse:collapse;border-left:solid 1 #000000; border-top:solid 1 #000000;padding:5px;}");
        sb.append("TH{border-right:solid 1 #000000;border-bottom:solid 1 #000000;}");
        sb.append("TD{font:normal;border-right:solid 1 #000000;border-bottom:solid 1 #000000;}");
        sb.append("</style></head>");
        sb.append("<body bgcolor=\"#FFF8DC\">");
        sb.append("<div align=\"center\">");
        sb.append("<br/>");
        sb.append("<br/>");
//        List<Map<String, Object>> result1 = getRpt(sqlProperties
//                .getProperty("sql1"));
//        for (Map.Entry<String, Object> m : result1.get(0).entrySet()) {
//            sb.append(fileProperties.getProperty("file1"));
//            sb.append(m.getValue());
//        }
        sb.append("<table border=\"1\"><tr>");
        for (int i = 0; i < 4; i++) {//表头
            sb.append("<th>");
            sb.append(formHead.get(i));
            sb.append("</th>");
        }
        sb.append("</tr>");
        for (int i = 0; i < comments.size(); i++) {//行
            sb.append("<tr>");
            sb.append("<td>");
            Review review = comments.get(i);
            sb.append(comments.get(i).getReadingNum());
            sb.append("</td>");
            //readingNum
            sb.append("<td>");
            sb.append(comments.get(i).getCommentNum());
            sb.append("</td>");
            //commentNum
            sb.append("<td>");
            sb.append(comments.get(i).getTitle());
            sb.append("</td>");
            //title
            sb.append("<td>");
            sb.append(comments.get(i).getTime());
            sb.append("</td>");
            //time
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("<br/><br/>");
        sb.append("</div></body></html>");
        printStream.println(sb.toString());
    }

    public String getTitle() {
        String url = "http://guba.eastmoney.com/list,of003985_1.html";
        Document doc = getDocument(url);
        Element head = doc.select("head").first();
        String title1 = head.select("title").text();
        return title1;
    }

    public Document getDocument (String url){
        try {
            //5000是设置连接超时时间，单位ms
            return Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Review> getComments() {
        List<Review> list = new ArrayList<Review>();
        for (int i = 1; i <= 2; i++) {
            String url = "http://guba.eastmoney.com/list,of003985_" + i + ".html";
            Document doc = getDocument(url);
            Element head = doc.select("head").first();
            String title1 = head.select("title").text();
            Elements elements = doc.select("div.articleh ");
            for (Element element : elements) {
                Elements cls_l1 = element.select("[class=l1]");
                String readingVolume = cls_l1.text();
                int readingNum = Integer.parseInt(readingVolume);
                Elements cls_l2 = element.select("[class=l2]");
                String comments = cls_l2.text();
                int commentNum = Integer.parseInt(comments);
                Elements cls_l3 = element.select("[class=l3]");
                Element a = cls_l3.select("a").first();
                String title = a.attr("title");
                Element cls_l5 = element.select("[class=l5]").first();
                String time = cls_l5.text();
                Review review = new Review(readingNum, commentNum, title, time);
                list.add(review);
            }
        }
        return list;
    }

    public List<Review> sortComments(List<Review> list) {
        list.sort(new Comparator<Review>() {
            public int compare(Review o1, Review o2) {
                if (o1.getReadingNum() != o2.getReadingNum()) return o2.getReadingNum() - o1.getReadingNum();
                return o2.getCommentNum() - o1.getCommentNum();
            }
        });
        return list;
    }
}
