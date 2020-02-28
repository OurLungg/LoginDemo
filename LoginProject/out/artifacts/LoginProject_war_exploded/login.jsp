<%--
  Created by IntelliJ IDEA.
  User: wangshaonan
  Date: 2020/2/25
  Time: 下午2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <script>
        window.onload=function(){
            document.getElementById("img").onclick=function(){
                this.src = "/daydayup/checkCodeServlet?time" + new Date().getTime();
            }
        }

        function reg() {
            window.open("register.jsp");
        }
    </script>
    <style>
        div{
            color: red;
        }
    </style>
</head>
<body>

<form action="/daydayup/loginServlet" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td><input type="text" name="checkcode"></td>
        </tr>
        <tr>
            <td colspan="2"><img id="img" src="/daydayup/checkCodeServlet" alt=""></td>
        </tr>
        <tr>
            <td><input type="submit" value="登录"></td>
            <td><input type="button" value="注册" onclick="reg()" ></td>

        </tr>
    </table>
</form>

<div>${requestScope.login_error}</div>
<div>${requestScope.checkcode_error}</div>
</body>
</html>
