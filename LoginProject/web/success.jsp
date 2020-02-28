<%--
  Created by IntelliJ IDEA.
  User: wangshaonan
  Date: 2020/2/25
  Time: 下午3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    <h1>${requestScope.user},欢迎您</h1>   --%>
    <h1><%=request.getSession().getAttribute("user")%>,欢迎您</h1>
</body>
</html>
