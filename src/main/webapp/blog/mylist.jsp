<%--
  Created by IntelliJ IDEA.
  User: Kho may tinh HN
  Date: 2/12/2023
  Time: 7:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
  <link rel="stylesheet" href="css/reset">
  <link rel="stylesheet" href="css/home">
</head>
<style>
  *{
    margin: 0;
    padding: 0;
    font-size: 20px;     /* Opera/IE 8+ */
    font-family: Arial, Helvetica, sans-serif;
    line-height: 1.6;
  }
  a{text-decoration: none;}
  #login-button,#register-button{
    padding: 8px 10px;
    cursor: pointer;
    font-weight: 400;
    background: #15191d;
    color: #cbcdd0;
    text-decoration: none;
    display: inline-block;
  }

  #account-wrapper a:hover{
    background: #2d3238;
  }

  body a{
    text-decoration: none;
    color: #1c67b3;
  }
</style>
<body>
<a href="/blogs?action=create"><button>Create new post</button></a>
<a href="/blogs" style="text-decoration: none">
  <button type="button">Back to home</button>
</a>
<ul>
  <c:forEach var="blog" items="${blogs}">
    <li>
      <a href="/blogs?action=detail&id=${blog.id}&account_comment_id=${1}">${blog.title}</a>
      <a href="/blogs?action=updateMyBlog&id=${blog.id}">
        <button>Update</button>
      </a>
      <a href="/blogs?action=deleteMyBlog&id=${blog.id}">
        <button>Delete</button>
      </a>
    </li>
  </c:forEach>
</ul>
</body>
</html>
