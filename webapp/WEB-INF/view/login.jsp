<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post"action="login" enctype="application/x-www-form-urlencoded">
  <input type="hidden" name="origUrl"value="${origUrl }" />
  <c:if test="${not empty errInfo }">
	<p style="color:red;">${errInfo }</p>
  </c:if>
	<p><input type="text" name="account" value="${account }"></p>
	<p><input type="password" name="password" value="${password }"></p>
	<p><input type="submit" value="登录" ></p>
</form>
</body>
</html>