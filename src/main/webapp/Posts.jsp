<%-- 
    Document   : Posts
    Created on : Jun 19, 2015, 1:15:21 PM
    Author     : ritchiefitzgerald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thread Posts</title>
    </head>
    <body>
        <h1>Discussion Thread</h1>
        <a href="/FirstProject/NewPost.jsp">New Post</a>
        <div class="comments">
            <c:forEach var="post" items="${posts}">
                <div class="comment">
                    <div class="comment-header">
                        <div class="username">${post.username}</div>
                        <div class="date">${post.date}</div>
                    </div>
                    <p class="content">${post.content}</p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
