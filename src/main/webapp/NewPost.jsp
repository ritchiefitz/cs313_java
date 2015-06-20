<%-- 
    Document   : NewPost
    Created on : Jun 18, 2015, 3:32:51 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Enter New Post</title>
    </head>
    <body>
        <h1>Hello ${username}</h1>
        <a href="/ViewPosts">View Posts</a>
        <form action="AddPost" method="post">
            <label>Content:</label>
            <br>
            <textarea name="content" id="content" cols="30" rows="10"></textarea>
            <br>
            <br>
            <input type="hidden" name="username" value="${username}">
            <input type="submit" value="Add Post">
        </form>
    </body>
</html>
