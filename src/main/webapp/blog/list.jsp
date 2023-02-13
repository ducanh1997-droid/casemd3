<%--
  Created by IntelliJ IDEA.
  User: Kho may tinh HN
  Date: 2/9/2023
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
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
    #login,#register-button{
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
    #blog-list li{
        border-bottom: 1px solid #dbe4ec;
        min-height: 30px;
    }
</style>
<body>
<c:choose>
    <c:when test="${empty account}">
        <div id="account-wrapper">
            <a id="login" href="/AccountServlet?action=login">Log in</a>
        </div>
    </c:when>
    <c:otherwise>
        <a href="/AccountServlet?id=${account.id}">${account.fullName}</a>
        <a id="login-button" href="/AccountServlet?action=logout">Log out</a><br><br>
        <a href="/blogs?action=create"><button>Create new post</button></a>
        <a href="/blogs?action=mylist"><button>My post manager</button></a>
        <c:if test="${account.role}">
            <a href="/blogs?action=postManager">Manager all post</a>
        </c:if>
    </c:otherwise>
</c:choose>

    <c:forEach var="c" items="${categories}">
            <div id="category-block">
                <h3>${c.name}</h3>
                <ul id="blog-list">
                    <c:forEach var="blog" items="${blogs}">
                        <c:if test="${blog.category.name == c.name}">
                            <li>
                                <a href="/blogs?action=detail&id=${blog.id}&account_comment_id=${blog.account.id}">${blog.title}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                </ul>
            </div>
    </c:forEach>
</body>
<script>

</script>
</html>
