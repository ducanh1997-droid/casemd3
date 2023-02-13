<%--
  Created by IntelliJ IDEA.
  User: Kho may tinh HN
  Date: 2/9/2023
  Time: 1:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/blogs?action=updateMyBlog&blog_id=${blog.id}" method="post">
    <table>
        <tr>
            <td><label for="title">Title:</label></td>
            <td><input type="text" name="title" id="title" value="${blog.title}"></td>
        </tr>
        <tr>
            <td><label for="content">Content:</label></td>
            <td>
                <textarea name="content" id="content" cols="30" rows="10">${blog.content}</textarea>
            </td>
        </tr>
        <tr>
            <td><label for="category">Category:</label></td>
            <td><select name="category" id="category">
                <c:forEach items="${categories}" var="c">
                    <option value="${c.id}">${c.name}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td><label for="image">Image:</label></td>
            <td><input type="text" name="image" id="image" value="${blog.image}"></td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Update</button>
                <a href="/blogs" style="text-decoration: none">
                    <button type="button">Back to home</button>
                </a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
