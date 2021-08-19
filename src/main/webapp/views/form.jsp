<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 Servlet</title>
</head>
<body>
	<h1>주소록 Servlet</h1>

	<h3>새 주소 등록</h3>
	
	<form action="<%= request.getContextPath() %>/el"
		method="POST">
	
		<label for="name">이름</label>
		<input type="text" name="name" id="name" /><br />
		<label for="hp">휴대전화</label>
		<input type="text" name="" id=hp" /><br />
		<label for="tel">집전화</label>
		<input type="text" name="" id="tel" /><br />
	
		
		<input type="submit" value="등록" />
	</form>

	<p>
		<a href="<%= request.getContextPath() %>/con?a=reset/">메인화면으로</a>
	</p>
</body>
</html>