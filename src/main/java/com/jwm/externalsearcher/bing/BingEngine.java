package com.jwm.externalsearcher.bing;

import org.json.JSONObject;
import org.json.JSONArray;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class BingEngine{

    private static String 
        urlTemplate = "http://api.search.live.net/json.aspx?Query=site:{0}&Sources=web"+
                    "AppId=D166077637411C98AB1C1D2E99AE33D87F443A4F&Web.Count=50&Web.Offset={1}";

    private static int total = 0;
    private static int offset = 0;

    public static void 
        main(String args[])
        throws Exception{
            search("matem.unam.mx", 0); 
        }

    public static void
        search(String site, int offset)
        throws Exception{
            URL url = new URL(urlTemplate.replaceAll("{0}",site).replaceAll("{1}", offset+""));
            URLConnection con = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            System.out.println("Connection done reading");
            String line = reader.readLine();
            
            JSONObject json = new JSONObject(line);
            JSONObject web = json.getJSONObject("SearchResponse").getJSONObject("Web");
            JSONArray array = web.getJSONArray("Results");
            total = web.getInt("Total");
            offset = web.getInt("Offset")+50;
            
            for(int i = 0; i < array.length(); i++){
                JSONObject searchItem = array.getJSONObject(i);
                if(searchItem.has("Description")){
                    System.out.println((i+1)+">"+searchItem.getString("Description"));
                }
                if(searchItem.has("Url")){ 
                    System.out.println(searchItem.getString("Url"));
                }
                System.out.println("####################################");
            }


            System.out.println("*******************************************************");
            reader.close();
            if(offset >= total){
                return;
            }else{
                search(site, offset);
            }
        }

} 
