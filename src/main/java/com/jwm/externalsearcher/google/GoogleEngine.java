package com.jwm.externalsearcher.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class GoogleEngine{

    private static int current = 0;

    private static String agents[] = new String[]{
        "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.2; Trident/4.0; Media Center PC 4.0; SLCC1; .NET CLR 3.0.04320)",
        "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 1.1.4322)",
        "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727)",
        "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; Media Center PC 6.0; InfoPath.3; MS-RTC LM 8; Zune 4.7",
        "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
        "Mozilla/5.0 (X11; U; Linux i586; de; rv:5.0) Gecko/20100101 Firefox/5.0",
        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:5.0) Gecko/20100101 Firefox/5.0",
        "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.2a1pre) Gecko/20110324 Firefox/4.2a1pre",
        "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b9pre) Gecko/20101228 Firefox/4.0b9pre",
        "Mozilla/5.0 (Windows; U; Windows NT 6.1; ru; rv:1.9.2.3) Gecko/20100401 Firefox/4.0 (.NET CLR 3.5.30729)",
        "Mozilla/5.0 (Windows NT 6.1; rv:2.0) Gecko/20110319 Firefox/4.0",
        "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.0.6) Gecko/2009011913 Firefox/3.0.6",
        "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.815.0 Safari/535.1",
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Ubuntu/11.04 Chromium/14.0.814.0 Chrome/14.0.814.0 Safari/535.1",
        "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.1 (KHTML, like Gecko) Ubuntu/10.04 Chromium/14.0.813.0 Chrome/14.0.813.0 Safari/535.1",
        "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.812.0 Safari/535.1",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.811.0 Safari/535.1",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.810.0 Safari/535.1",
        "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.810.0 Safari/535.1",
        "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.809.0 Safari/535.1",
        "Opera/9.80 (Windows NT 5.2; U; en) Presto/2.6.30 Version/10.63",
        "Opera/9.80 (X11; Linux x86_64; U; Ubuntu/10.10 (maverick); pl) Presto/2.7.62 Version/11.01",
        "Opera/9.80 (Windows NT 6.1; U; cs) Presto/2.7.62 Version/11.01",
        "Opera/9.80 (Windows NT 6.1; U; es-ES) Presto/2.9.181 Version/12.00",
        "Opera/9.80 (X11; Linux i686; U; es-ES) Presto/2.8.131 Version/11.11",
        "Mozilla/5.0 (Windows NT 5.1; U; en; rv:1.8.1) Gecko/20061208 Firefox/5.0 Opera 11.11",
        "Opera/9.80 (Windows NT 6.1 x64; U; en) Presto/2.7.62 Version/11.00"
    };


    public static void
        main(String []args) throws IOException{
            //search(googleURL, 0);
            System.out.println("#############");
            search("http://www.google.com/search?q=site:unam.mx&hl=en&biw=1600&bih=607&prmd=ivns&ei=omVHTr-8OcyisQLlwYCSCA&start={0}&sa=N",0);
            //System.out.println("URL>"+googleURL);
            //System.out.println(doc.select("td.cur").text());
            //System.out.println(doc.select("table#nav").select("a.fl"));
            //System.out.println("#################################################");
        }

    public static void
        search(String url, int seq) throws IOException{
            String innerurl = url.replace("{0}", ""+(seq * 10));
            System.out.println("Using url>"+innerurl);
            String agent = agents[new Random().nextInt(agents.length)];
            System.out.println("agent>"+agent);

            Document doc = 
                    Jsoup.connect(innerurl)
                    .userAgent(agent).get();
            System.out.println(doc.select("#resultStats").text());
            Elements elems = doc.select("li.g");
         
            for(Element elem : elems){
                System.out.println(">"+elem.select("a.l").text());
                System.out.println("\t"+elem.select("cite").text());
            }

            int curr = new Integer(doc.select("td.cur").text());

            System.out.println("curr: "+curr+", current:"+current);
            if(curr != current){
                current = curr;
                try{
                    long waitingTime = new Random().nextInt(16)*100l;
                    System.out.println("waiting for "+waitingTime+" ms");
                    Thread.sleep(waitingTime);
                }catch(Exception e){
                    e.printStackTrace();
                }
                search(url, curr);
            }
        }
}
