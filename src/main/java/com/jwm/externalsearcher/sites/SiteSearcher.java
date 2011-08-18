package com.jwm.externalsearcher.sites;

import com.jwm.externalsearcher.bing.BingEngine;
import com.jwm.externalsearcher.google.GoogleEngine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class SiteSearcher{

    public static void
        main(String []args)
        throws Exception{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
            for(String line = reader.readLine(); line != null; line = reader.readLine()){
                //System.out.println(line+": "+BingEngine.getTotalsFor(line, 0));
                writer.write("'"+line+"', '"+BingEngine.getTotalsFor(line, 0)+"'\n");
                //System.out.println(line+": "+GoogleEngine.getTotalsFor(line)); 
            }
            reader.close();
            writer.flush();
            writer.close();
        }
}
