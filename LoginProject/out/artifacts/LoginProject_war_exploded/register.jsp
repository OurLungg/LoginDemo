<%--
  Created by IntelliJ IDEA.
  User: wangshaonan
  Date: 2020/2/25
  Time: 下午5:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/daydayup/registerServlet" method="post">
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
           <td><input type="submit" value="确定注册"></td>
       </tr>
   </table>
</form>
<div><%=request.getAttribute("register_success") == null ? "" : request.getAttribute("register_success")%></div>
<div><%=request.getAttribute("register_error") == null ? "" : request.getAttribute("register_error")%></div>
</body>
</html>
