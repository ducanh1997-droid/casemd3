<%--
  Created by IntelliJ IDEA.
  User: linh
  Date: 2/12/2023
  Time: 10:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ACCOUNT PROFILE</title>
</head>
<style>
    #successRegister {
        position: absolute;
        width: 1600px;
        height: 800px;
        z-index: 1;
        top:0;
        left: 0;
        background-color: rgba(0,0,0,0.9);
    }
    #success{
        text-align: center;
        vertical-align: center;
        position: absolute;
        background: linear-gradient(to bottom, white, white);
        padding: 0;
        top: 250px;
        left: 500px;
        border: 1px solid #888;
        border-radius: 15px;
        width: 350px;
        height: 100px;
    }
    #Close{
        float: right;
    }
</style>
<body>
<a href="/blogs" style="text-decoration: none">
    <button type="button">Back to home</button>
</a>
<div>
    <button id="accountDetail" onclick="showAccountDetail()">Account information</button>
    <button style="display: none" id="Blog" onclick="showMyBlog()" >My posted blog </button>
    <button style="display: none" id="myComment">My Comment</button>
    <button onclick="showSecurity()" id="security">Password and security</button>
</div>
<div id="accountInformation" style="display:
<c:choose>
<c:when test="${requestScope.accountDisplay=='block'}">
        block">
    </c:when>
    <c:otherwise>
        none">
    </c:otherwise>
    </c:choose>
    <form action="/AccountServlet?action=update&id=${requestScope.account.id}" method="post">
        <table>
            <tr>
                <td>Username:</td>
                <td><p id="displayUsername">${requestScope.account.userName}</p><input name="username" id="username" style=" display:none" value="${requestScope.account.userName}"></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><p id="displayEmail">${requestScope.account.email}</p><input id="email" name="email" style=" display:none"  value="${requestScope.account.email}"></td>
            </tr>
            <tr>
                <td>Full name:</td>
                <td>
                    <p id="displayFullName">${requestScope.account.fullName}</p>
                    <input name="fullName" id="fullName" style=" display:none" onchange="checkRegexFullName()" value="${requestScope.account.fullName}">
                </td>
            </tr>
            <tr>
                <td>PhoneNumber:</td>
                <td>
                    <p id="displayPhoneNumber">${requestScope.account.phoneNumber}</p>
                    <input name="phoneNumber" id="phoneNumber" onchange="checkRegexPhoneNumber()" style=" display:none" value="${requestScope.account.phoneNumber}">
                    <span id="phoneWarning" style="color: red;display: none">Your phone isn't suit with phone format  </span>
                </td>
            </tr>
            <tr>
                <td>Address:</td>
                <td>
                    <p id="displayAddress">${requestScope.account.address}</p>
                    <input name="address" id="address" onchange="checkRegexAddress()" style=" display:none" value="${requestScope.account.address}">
                    <span id="addressWarning" style="color: red;display: none">Your address isn't suit with address format  </span>
                </td>
            </tr>
            <tr>
                <td>Admin right:</td>
                <td id="adminRight" >
                    <c:choose>
                        <c:when test="${requestScope.account.role}">
                            Yes
                        </c:when>
                        <c:otherwise>
                            No
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
        <button id="editInformation" type="button" value="" onclick="editInform()">Edit your profile</button>
        <button id="confirm" type="submit" disabled style="display: none">Confirm change</button>
        <button id="abort" type="button" style="display: none" onclick="editInform()">Abort changing</button>
    </form>
</div>
<div id="myBlog" style="display:
<c:choose>
<c:when test="${requestScope.blogDisplay=='block'}">
        block">
    </c:when>
    <c:otherwise>
        none">
    </c:otherwise>
    </c:choose>
    <div>
        <table  style="border: solid">
            <tr>
                <th>Index</th>
                <th>Title</th>
                <th>Category</th>
                <th>Date</th>
            </tr>
            <c:forEach items="${requestScope.blogs}" var="b" varStatus="index1">
                <tr>
                    <th>${index1.index+1}</th>
                    <th><a href="/AccountServlet?action=loadBlog&blogId=${b.id}">${b.title}</a></th>
                    <th>${b.category.name}</th>
                    <th>${b.date}</th>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="blogReq" style="display:
    <c:choose>
    <c:when test="${requestScope.blogReq!= null}">
            block">
        </c:when>
        <c:otherwise>
            none">
        </c:otherwise>
        </c:choose>
        <c:set var="b" value="${requestScope.blogReq}"></c:set>
        <p>${b.title}</p>
        <p>${b.content}</p>
    </div>
</div>
<div id="successRegister"  style="
<c:choose>
<c:when test="${requestScope.status == 'success'}">
        display: block ">
    </c:when>
    <c:otherwise>
        display: none ">
    </c:otherwise>
    </c:choose>
    <div id="success" style="background-color: aliceblue">
        <button id="Close" >&times;</button>
        <p>Cập nhật thành công</p>
    </div>
</div>
<div id="mySecurity" style="display:
<c:choose>
<c:when test="${requestScope.Security=='block'}">
        block">
    </c:when>
    <c:otherwise>
        none">
    </c:otherwise>
    </c:choose>
    <select id="securityChange">
        <option></option>
        <option id="password"  >Change username,email and password </option>
        <option id="secondSecurity" >Set your two-step verification </option>
    </select>
    <button onclick="confirmChoice()">Confirm</button>
    <div id="verifyPassword" style="display: none">
        <label id="labelPassword1" for="password1">Input your password to continue</label><br>
        <input type="text" id="password1" onchange="checkPassword()">
        <span id="passWarning1" style="color: red;display: none">!!Wrong password!!</span>
    </div>
    <div id="securityEditForm" style="display: none">
        <form action="/AccountServlet?action=updatePassword&id=${requestScope.account.id}" method="post">
            <table>
                <tr>
                    <td>Username:</td>
                    <td>
                        <p id="displayUsername1" >${requestScope.account.userName}</p><input id="username1" style=" display:none" name="username1" value="${requestScope.account.userName}">
                        <span id="nameWarning" style="color: red;display: none">Your username invalid(from 8-15 characters contain 0 -9,a-z,A-Z,-,_, ) </span>
                    </td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>
                        <p id="displayEmail1">${requestScope.account.email}</p><input id="email1" style=" display:none" name="email1" value="${requestScope.account.email}">
                        <span id="emailWarning" style="color: red;display: none">Your email isn't suit with email format  </span>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td>
                        <p id="displayPassword">${requestScope.account.passWord}</p><input id="password2" name="password" style=" display:none" onchange="checkRegexPassword()" name="password2" value="${requestScope.account.passWord}">
                        <span id="passWarning" style="color: red;display: none">Your password isn't suit with the password format  </span>
                    </td>
                </tr>
                <tr>
                    <td id="repassTitle" style="display: none">Re-enter your new password:</td>
                    <td>
                        <input id="rePassword" onchange="checkRegexPassword()" style=" display:none" name="password2" value="${requestScope.account.passWord}">
                        <span id="warning" style="color: red;display: none">Your repass isn't match with the password you entered </span>
                    </td>
                </tr>
            </table>
            <button id="editSecurity" type="button" value="" onclick="editSecure()">Edit your profile</button>
            <button id="confirm1" type="submit" disabled style="display: none">Confirm change</button>
            <button id="abort1" type="button" style="display: none" onclick="editSecure()">Abort changing</button>
        </form>
    </div>
</div>
<script>
    window.onclick = function(event) {
        if (event.target ===document.getElementById("successRegister")||event.target ===document.getElementById("Close")) {
            document.getElementById("successRegister").style.display = "none";
        }
    }
    <%--Dành cho phần Account information --%>
    function showAccountDetail(){
        if (document.getElementById("accountInformation").style.display==="none"){
            document.getElementById("accountInformation").style.display="block";
            document.getElementById("myBlog").style.display="none";
            document.getElementById("mySecurity").style.display="none";
        }
    }
    function showMyBlog(){
        if (document.getElementById("myBlog").style.display==="none"){
            document.getElementById("myBlog").style.display="block";
            document.getElementById("accountInformation").style.display="none";
            document.getElementById("mySecurity").style.display="none";
            document.getElementById("blogReq").style.display="none";
        }
    }
    function showSecurity(){
        if (document.getElementById("mySecurity").style.display==="none"){
            document.getElementById("mySecurity").style.display="block";
            document.getElementById("accountInformation").style.display="none";
            document.getElementById("myBlog").style.display="none";
            document.getElementById("securityChange").value="";
        }
    }
    function editInform(){
        let confirm = document.getElementById("confirm");
        if(confirm.style.display==="none"){
            confirm.style.display="block";
            confirm.disabled = "true";
            document.getElementById("abort").style.display="block";
            document.getElementById("editInformation").style.display="none";
            document.getElementById("displayPhoneNumber").style.display="none";
            document.getElementById("displayAddress").style.display="none";
            document.getElementById("displayFullName").style.display="none";
            document.getElementById("phoneNumber").style.display="block";
            document.getElementById("address").style.display="block";
            document.getElementById("fullName").style.display="block";
            document.getElementById("phoneNumber").value=document.getElementById("displayPhoneNumber").innerHTML;
            document.getElementById("address").value=document.getElementById("displayAddress").innerHTML;
            document.getElementById("fullName").value=document.getElementById("displayFullName").innerHTML;
        }else {
            confirm.style.display="none";
            document.getElementById("abort").style.display="none";
            document.getElementById("editInformation").style.display="block";
            document.getElementById("displayPhoneNumber").style.display="block";
            document.getElementById("displayAddress").style.display="block";
            document.getElementById("displayFullName").style.display="block";
            document.getElementById("phoneNumber").style.display="none";
            document.getElementById("address").style.display="none";
            document.getElementById("fullName").style.display="none";
        }
    }
    function checkRegexUsername(){
        let username = document.getElementById("username1").value;
        let pattern = /^[a-z0-9_-]{8,15}$/;
        if (!pattern.test(username)){
            document.getElementById("nameWarning").style.display="block";
        }else{
            document.getElementById("nameWarning").style.display="none";
        }
        checkConfirmCondition1()
    }
    function checkRegexEmail(){
        let email = document.getElementById("email1").value;
        let pattern = /[^@]{2,64}@[^.]{2,253}\.[0-9a-z-.]{2,63}/;
        if (!pattern.test(email)){
            document.getElementById("emailWarning").style.display="block";
        }else{
            document.getElementById("emailWarning").style.display="none";
        }
        checkConfirmCondition1()
    }
    function checkRegexPassword(){
        check1()
        let password = document.getElementById("password2").value;
        let pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
        if (!pattern.test(password)){
            document.getElementById("passWarning").style.display="block";
        }else{
            document.getElementById("passWarning").style.display="none";
        }
        checkConfirmCondition1()
    }
    function checkRegexPhoneNumber(){
        let phone = document.getElementById("phoneNumber").value;
        let pattern = /^[0-9]{10}$/;
        if (!pattern.test(phone)){
            document.getElementById("phoneWarning").style.display="block";
        }else{
            document.getElementById("phoneWarning").style.display="none";
        }
        checkConfirmCondition()
    }
    function checkRegexAddress(){
        let address = document.getElementById("address").value;
        let pattern = /^(?=.*\w).+$/;
        if (!pattern.test(address)){
            document.getElementById("addressWarning").style.display="block";
        }else{
            document.getElementById("addressWarning").style.display="none";
        }
        checkConfirmCondition()
    }
    function checkRegexFullName(){
        checkConfirmCondition()
    }
    function checkConfirmCondition(){
        let aw= document.getElementById("addressWarning").style.display
        let phw= document.getElementById("phoneWarning").style.display
        let a= document.getElementById("address").value
        let ph= document.getElementById("phoneNumber").value
        let f= document.getElementById("fullName").value
        let check= (a!==document.getElementById("displayAddress").innerHTML)||(ph!==document.getElementById("displayPhoneNumber").innerHTML)||(f!==document.getElementById("displayFullName").innerHTML);
        document.getElementById("confirm").disabled = !(aw==="none"&&phw==="none"&&a!==""&&ph!==""&&f!==""&&check)
    }
    // -----------------------------------------------------------------
    // for Password and security
    function checkConfirmCondition1(){
        let pw= document.getElementById("passWarning").style.display
        let rpw=document.getElementById("warning").style.display
        let p= document.getElementById("password2").value
        let check=(p!==document.getElementById("displayPassword").innerHTML);
        document.getElementById("confirm1").disabled = !(pw==="none"&&rpw==="none"&&p!==""&&check)
    }
    function confirmChoice(){
        if (document.getElementById("securityChange").value==="Change username,email and password"){
            document.getElementById("verifyPassword").style.display="block";
        }else{
            document.getElementById("verifyPassword").style.display="none";
            document.getElementById("password1").value="";
            document.getElementById("passWarning1").style.display="none";
        }
    }
    function checkPassword(){
        if (document.getElementById("password1").value==="${requestScope.account.passWord}"){
            document.getElementById("password1").style.display="none";
            document.getElementById("labelPassword1").style.display="none";
            document.getElementById("password1").value="";
            document.getElementById("passWarning1").style.display="none";
            document.getElementById("verifyPassword").style.display="none";
            document.getElementById("securityEditForm").style.display="block";
        }else{
            document.getElementById("passWarning1").style.display="block";
        }
    }
    function check1(){
        if(document.getElementById("password2").value !== document.getElementById("rePassword").value){
            document.getElementById("warning").style.display="block";
        }else{
            document.getElementById("warning").style.display="none";
        }
    }
    function editSecure(){
        let confirm = document.getElementById("confirm1");
        if(confirm.style.display==="none"){
            confirm.style.display="block";
            confirm.disabled = "true";
            document.getElementById("abort1").style.display="block";
            document.getElementById("editSecurity").style.display="none";
            document.getElementById("displayPassword").style.display="none";
            document.getElementById("password2").style.display="block";
            document.getElementById("rePassword").style.display="block";
            document.getElementById("repassTitle").style.display="block";
            document.getElementById("username1").value=document.getElementById("displayUsername1").innerHTML;
            document.getElementById("email1").value=document.getElementById("displayEmail1").innerHTML;
            document.getElementById("password2").value=document.getElementById("displayPassword").innerHTML;
            document.getElementById("rePassword").value=document.getElementById("displayPassword").innerHTML;
        }else {
            confirm.style.display="none";
            document.getElementById("abort1").style.display="none";
            document.getElementById("editSecurity").style.display="block";
            document.getElementById("displayPassword").style.display="block";
            document.getElementById("password2").style.display="none";
            document.getElementById("rePassword").style.display="none";
        }
    }
    // -----------------------------------------------------------------
</script>
</body>
</html>
