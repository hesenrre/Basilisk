package com.jwm.externalsearcher.google;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class GoogleEngine{
    public static void
        main(String []args ) throws IOException{
            String googleURL = "http://www.google.com/search?hl=en&"+
                                "q=site:jwmsolutions.com&num=50&btnG=Google+Search";
            System.out.println("URL>"+googleURL);
            Document doc = 
                    Jsoup.connect(googleURL)
                    .userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.6) "+
                            "Gecko/2009011913 Firefox/3.0.6").get();        
            System.out.println(doc.select("#resultStats").text());            
            for(Element elem : doc.select("li.g")){
                System.out.println(">"+elem.select("a.l").text());
                System.out.println("\t"+elem.select("cite").text());
            }
            System.out.println("#################################################");
        }
}
