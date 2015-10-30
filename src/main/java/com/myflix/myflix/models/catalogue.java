/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myflix.myflix.models;

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
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author andy
 */
public class catalogue {

    public void catalogue() {

    }

    public java.util.LinkedList<Video> videos() {
        URL videos = null;
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
                String Content=hc.getContentType();
                String Encoding= hc.getContentEncoding();
                
                InputStreamReader in = new InputStreamReader((InputStream) hc.getInputStream());
                BufferedReader buff = new BufferedReader(in);

                StringBuffer response = new StringBuffer();
                String line = null;
                
                do {
                    line = buff.readLine();
                    response.append(line);
                } while (line != null);
                JSONObject obj=null;
                //System.out.println(sBuff);
                try {
                    obj = new JSONObject(response);
                } catch (JSONException et) {
                    System.out.println("JSON Parse error in " + response+":"+et);
                    return null;
                }
            }
        } catch (Exception et) {
            System.out.println("Can't decode returned statment");
            return null;
        }
        return null;
    }

}
