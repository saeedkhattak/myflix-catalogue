<%-- 
    Document   : video
    Created on : Nov 1, 2015, 11:41:17 AM
    Author     : andy
--%>
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
        <title>myFlix Video</title>
    </head>
    <body>
        <h1>Your video</h1>
        <%
        Video video = (Video) request.getAttribute("Video");
            if (video != null) {
                
                    HashMap<String,String> fields= video.getfields();
                    String server=fields.get("server").replaceAll("\"", "");
                    String name=fields.get("Name").replaceAll("\"", "");
                    String pic=fields.get("pic").replaceAll("\"", "");
                    String file=fields.get("file").replaceAll("\"", "");
                    %>
                    <div id="instructions">

      <video id="my_video_1" class="video-js vjs-default-skin" width="640px" height="267px"
	     controls preload="none" poster='http://a41-stream<%=server%>.cloudapp.net/pics/<%=pic%>'
	     data-setup='{ "aspectRatio":"640:267", "playbackRates": [1, 1.5, 2] }'>
	<source src="rtmp://a41-stream<%=server%>.cloudapp.net:1935/vod2/<%=file%>" type='rtmp/mp4' />
	
      </video>
      </div>
                    <%
            }
           
            
        %>
    </body>
</html>
