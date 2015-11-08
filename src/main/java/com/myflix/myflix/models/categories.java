/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myflix.myflix.models;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.myflix.myflix.stores.Category;
import com.myflix.myflix.stores.Video;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andy
 */
public class categories {
    public void categories() {

    }
    
    public LinkedList<Category> listcategories() {
        URL videos = null;
        LinkedList<Category> categorielist=new LinkedList();
        try {
            videos = new URL("http://a41-catalogue.cloudapp.net:8080/myflix/categories");
        } catch (Exception et) {
            System.out.println("Videos URL is broken");
            return null;
        }
        HttpURLConnection hc = null;
        try {
            hc = (HttpURLConnection) videos.openConnection();
            String login = "admin:admin";
            final byte[] authBytes = login.getBytes(StandardCharsets.UTF_8);
            final String encoded = Base64.getEncoder().encodeToString(authBytes);
            hc.addRequestProperty("Authorization", "Basic " + encoded);
            hc.setDoInput(true);
            //hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestMethod("GET");
            //hc.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
            hc.setRequestProperty("Content-Type", "application/hal+json");
            //hc.setRequestProperty("Accept", "application/json");
            hc.setRequestProperty("Accept", "application/json,text/html,application/hal+json,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*");
        } catch (Exception et) {
            System.out.println("Can't prepare http URL con");
            return (null);
        }
        BufferedReader br = null;
        try {
            OutputStreamWriter writer = new OutputStreamWriter(hc.getOutputStream());
            writer.write("");

        } catch (Exception et) {
            System.out.println("Can't get reader to videos stream");
        }
        String inputLine;
        String sJSON = null;

        try {
            int rc = hc.getResponseCode();
            if ((rc == HttpURLConnection.HTTP_OK) || (rc == HttpURLConnection.HTTP_CREATED)) {
                int Length = hc.getContentLength();
                String Content = hc.getContentType();
                String Encoding = hc.getContentEncoding();

                InputStreamReader in = new InputStreamReader((InputStream) hc.getInputStream());
                BufferedReader buff = new BufferedReader(in);

                StringBuffer response = new StringBuffer();
                String line = null;

                do {
                    line = buff.readLine();
                    if (line != null) {
                        response.append(line);
                    }
                } while (line != null);
                JsonObject obj = new JsonObject();
                //System.out.println(sBuff);
                try {

                    obj = JsonObject.readFrom(response.toString());
                    List<String> ll = obj.names();
                    JsonObject obj2 = obj.get("_embedded").asObject();
                    ll = obj2.names();
                    JsonArray items = obj2.get("rh:doc").asArray();
                    int number =items.size();
                    for (JsonValue item : items) {
                        JsonObject obj3 = item.asObject();
                        ll = obj3.names();
                        int i = 0;
                        for(String l:ll){
                            if (l.compareTo("category") == 0) {
                                HashMap<String,String> fields=new HashMap();
                                String category = obj3.get("category").asString();
                                
                                   
                              
                                Category vv=new Category();
                                vv.setCategory(category);
                                categorielist.add(vv);
                            }
                            
                        }
                        
                    }

                } catch (Exception et) {
                    System.out.println("JSON Parse error in " + response + ":" + et);
                    return null;
                }
            }
        } catch (Exception et) {
            System.out.println("Can't decode returned statment");
            return null;
        }

        return categorielist;
    }

    
}
