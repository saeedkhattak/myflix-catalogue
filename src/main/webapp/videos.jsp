<%-- 
    Document   : videos
    Created on : Nov 1, 2015, 10:38:29 AM
    Author     : andy
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.myflix.myflix.stores.Video"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="http://vjs.zencdn.net/4.11/video-js.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/4.11/video.js"></script>
        <link href="http://vjs.zencdn.net/4.11/video-js.css" rel="stylesheet">
    <script src="http://vjs.zencdn.net/4.11/video.js"></script>
        <title>Videos</title>
    </head>
    <body>
         <h1>Your Vidoes</h1>
         <%
            LinkedList<Video> lsVideos = (java.util.LinkedList<Video>) request.getAttribute("Videos");
            if (lsVideos != null) {
                for (Video video:lsVideos){
                    HashMap<String,String> fields= video.getfields();
                    String server=fields.get("server").replaceAll("\"", "");
                    String name=fields.get("Name").replaceAll("\"", "");
                    String thumb=fields.get("thumb").replaceAll("\"", "");
                    %>
                    <h2 class="name"><%=name%></h2>
                    <img src="http://a41-stream<%=server%>.cloudapp.net/pics/<%=thumb%>">
                    <%
                }
            }
            
        %>
    </body>
</html>
