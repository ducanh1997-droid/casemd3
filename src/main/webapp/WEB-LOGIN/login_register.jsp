<%--
  Created by IntelliJ IDEA.
  User: linh
  Date: 2/9/2023
  Time: 10:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title></title>
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
<div id="login" style="
<c:choose>
<c:when test="${requestScope.status == 'success'|| requestScope.status == null}">
        display: block ">
  </c:when>
  <c:when test="${requestScope.status == 'false' && requestScope.condition == 'login'}">
    display: block ">
  </c:when>
  <c:otherwise>
    display: none ">
  </c:otherwise>
  </c:choose>
  <form action="/AccountServlet?action=login" method="post">
    <p>Log in</p>
    <br>
    <label for="username">Your name or email address</label>
    <br>
    <input type="text" id="username" name="username">
    <br>
    <label for="password">Your password</label>
    <br>
    <input type="password" id="password" name="password">
    <br>
    <input type="submit" value="Login">
    <input type="button"  onclick="moveToRegister()" value="Register">
    <span id="failLogin" style="color: red;
    <c:choose>
    <c:when test="${requestScope.status == 'false' && requestScope.condition == 'login'}">
            display: block ">
    </c:when>
    <c:otherwise>
      display: none ">
    </c:otherwise>
    </c:choose>!!Fail to log in.Wrong username or email or password !!</span>
  </form>
</div>
<div id="Register" style="<c:choose>
<c:when test="${requestScope.status == 'success'|| requestScope.status == null|| requestScope.condition == 'login'}">
        display: none ">
  </c:when>
  <c:otherwise>
    display: block ">
  </c:otherwise>
  </c:choose>
  <form action="/AccountServlet?action=register" method="post">
    <p>REGISTER</p>
    <br>
    <label for="username1">Username</label>
    <br>
    <input type="text" id="username1" name="name" onchange="checkRegexUsername()" >
    <span id="nameWarning" style="color: red;display: none">Your username invalid(from 8-15 characters contain 0 -9,a-z,A-Z,-,_, ) </span>
    <br>
    <label for="email">Email</label>
    <br>
    <input type="text" id="email" name="email" onchange="checkRegexEmail()">
    <span id="emailWarning" style="color: red;display: none">Your email isn't suit with email format  </span>
    <br>
    <label for="password1">Your password</label>
    <br>
    <input type="password" id="password1" name="password" onchange="checkRegexPassword()" value=${requestScope.password}><button type="button" onclick="showPassword()"><img id="passwordIcon" style="width: 10px;height: 12px" src="hidepw.png" alt=""></button>
    <span id="passWarning" style="color: red;display: none">Your password isn't suit with the password format  </span>
    <br>
    <label for="rePassword">Re-enter your password</label>
    <br>
    <input type="password" id="rePassword" onchange="check()" value=${requestScope.password}>
    <span id="warning" style="color: red;display: none">Your repass isn't match with the password you entered </span>
    <br>
    <label for="fullName">Full name</label>
    <br>
    <input type="text" id="fullName" name="fullName" value=${requestScope.fullName}>
    <br>
    <label for="phoneNumber">PhoneNumber</label>
    <br>
    <input type="text" id="phoneNumber" name="phoneNumber" onchange="checkRegexPhoneNumber()" value=${requestScope.phoneNumber}>
    <span id="phoneWarning" style="color: red;display: none">Your phone isn't suit with phone format  </span>
    <br>
    <label for="address">Address</label>
    <br>
    <input type="text" id="address" name="address"  onchange="checkRegexAddress()" value=${requestScope.address}>
    <span id="addressWarning" style="color: red;display: none">Your address isn't suit with address format  </span>
    <br>
    <input type="button" onclick="moveToLogin()"  value="Back to Login">
    <input id="registerButton" type="submit" value="Register"><br><br>
    <span id="failRegister" style="color: red;
    <c:choose>
    <c:when test="${requestScope.status == 'success'|| requestScope.status == null}">
            display: none ">
    </c:when>
    <c:otherwise>
      display: block ">
    </c:otherwise>
    </c:choose>!!Fail to register new register.Already exist account with same username or email !!</span>
  </form>
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
    <p>Đăng ký thành công</p>
  </div>
</div>
<script>
  document.getElementById("registerButton").disabled = true;
  window.onclick = function(event) {
    if (event.target ===document.getElementById("successRegister")||event.target ===document.getElementById("Close")) {
      document.getElementById("successRegister").style.display = "none";
    }
    document.getElementById("failRegister").style.display="none";
    document.getElementById("failLogin").style.display="none";
  }
  function moveToRegister(){
    document.getElementById("login").style.display="none";
    document.getElementById("Register").style.display="block";
  }
  function moveToLogin(){
    document.getElementById("login").style.display="block";
    document.getElementById("Register").style.display="none";
  }
  function check(){
    if(document.getElementById("password1").value !== document.getElementById("rePassword").value){
      document.getElementById("warning").style.display="block";
    }else{
      document.getElementById("warning").style.display="none";
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
    checkRegisterCondition()
  }
  function checkRegexEmail(){
    let email = document.getElementById("email").value;
    let pattern = /[^@]{2,64}@[^.]{2,253}\.[0-9a-z-.]{2,63}/;
    if (!pattern.test(email)){
      document.getElementById("emailWarning").style.display="block";
    }else{
      document.getElementById("emailWarning").style.display="none";
    }
    checkRegisterCondition()
  }
  function checkRegexPassword(){
    check()
    let password = document.getElementById("password1").value;
    let pattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    if (!pattern.test(password)){
      document.getElementById("passWarning").style.display="block";
    }else{
      document.getElementById("passWarning").style.display="none";
    }
    checkRegisterCondition()
  }
  function checkRegexPhoneNumber(){
    let phone = document.getElementById("phoneNumber").value;
    let pattern = /^[0-9]{10}$/;
    if (!pattern.test(phone)){
      document.getElementById("phoneWarning").style.display="block";
    }else{
      document.getElementById("phoneWarning").style.display="none";
    }
    checkRegisterCondition()
  }
  function checkRegexAddress(){
    let address = document.getElementById("address").value;
    let pattern = /^(?=.*\w).+$/;
    if (!pattern.test(address)){
      document.getElementById("addressWarning").style.display="block";
    }else{
      document.getElementById("addressWarning").style.display="none";
    }
    checkRegisterCondition()
  }
  function showPassword(){
    let type=document.getElementById("password1").type;
    if (type === "password") {
      document.getElementById("password1").type="text";
      document.getElementById("rePassword").type="text";
      document.getElementById("passwordIcon").src="showpw.png";
    }else{
      document.getElementById("password1").type="password";
      document.getElementById("rePassword").type="password";
      document.getElementById("passwordIcon").src="hidepw.png";
    }
  }
  function checkRegisterCondition(){
    let aw= document.getElementById("addressWarning").style.display
    let uw= document.getElementById("nameWarning").style.display
    let pw= document.getElementById("passWarning").style.display
    let phw= document.getElementById("phoneWarning").style.display
    let ew= document.getElementById("emailWarning").style.display
    let a= document.getElementById("address").value
    let u= document.getElementById("username1").value
    let p= document.getElementById("password1").value
    let ph= document.getElementById("phoneNumber").value
    let e= document.getElementById("email").value
    let f= document.getElementById("fullName").value
    document.getElementById("registerButton").disabled = !(a !== "" && u !== "" && p !== "" && ph !== "" && e !== ""&& f!==""&&aw === "none" && uw === "none" && pw === "none" && phw === "none" && ew === "none");
  }
</script>
</body>
</html>