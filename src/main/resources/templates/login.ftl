<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
    <head>
		<title>Login</title>
	</head>

	<body>
	    <h1>登录页面----</h1>
	    <form action="/login" commandName="user" method="post">
	        用户名：<input name="userName" />
	        <!--<errors path="username" cssClass="error" /> -->
	        <br />
	        密码：<input type="password" name="password" />
	        <!--<form:errors path="password" cssClass="error" /> -->
	        <br />
	        <input type="submit" name="button">提交</input>
	    </form>
	</body>
</html>