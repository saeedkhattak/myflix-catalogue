/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myflix.myflix.models;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.myflix.myflix.stores.Video;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andy
 */
public class catalogue {

    public void catalogue() {

    }

    
    public Video video(String sUUID){
       URL videos = null;
        Video videol=new Video();
        try {
            videos = new URL("http://a41-catalogue.cloudapp.net:8080/myflix/videos?filter={\"video.uuid\":\""+sUUID+"\"}");
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
                            if (l.compareTo("video") == 0) {
                                HashMap<String,String> fields=new HashMap();
                                JsonObject video = obj3.get("video").asObject();
                                List<String>  names=video.names();
                                for (String name:names){
                                   JsonValue Value=video.get(name);
                                   String sValue=Value.toString();
                                   fields.put(name,sValue);
                                   
                                }
                               
                                videol.setFields(fields);
                                
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

        return videol;
     
    }
    
    public LinkedList<Video> videos() {
        URL videos = null;
        LinkedList<Video> videolist=new LinkedList();
        try {
            videos = new URL("http://a41-catalogue.cloudapp.net:8080/myflix/videos");
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
                            if (l.compareTo("video") == 0) {
                                HashMap<String,String> fields=new HashMap();
                                JsonObject video = obj3.get("video").asObject();
                                List<String>  names=video.names();
                                for (String name:names){
                                   JsonValue Value=video.get(name);
                                   String sValue=Value.toString();
                                   fields.put(name,sValue);
                                   
                                }
                                Video vv=new Video();
                                vv.setFields(fields);
                                videolist.add(vv);
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

        return videolist;
    }

}
