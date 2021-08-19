<%@page import="com.test.Phonebook.vo.AddressVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//	요청 객체에서 list 속성 받아오기
List<PhoneBookVo> list = (List<PhoneBookVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소록 Servlet</title>
</head>
<body>
	<h1>목록</h1>
	
	<% for (AddressVo vo: list) { %>
	<table border="1">
		<!-- 행 -->
		<tr>
			<th>이름</th>
			<td><%= vo.getName() %></td>
		</tr>
		<tr>
			<th>휴대전화</th>
			<td><%= vo.getHp() %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%= vo.getTel() %></td>
		</tr>
		<tr>
			<td colspan="2">
			<!-- 삭제 폼 -->
				<form action="<%= request.getContextPath() %>/el"
					method="POST">
					<input type="hidden" name="a" value="delete" />
					<input type="hidden" name="no" value="<%= vo.getNo() %>" />	
					<input type="submit" value="삭제" />
				</form>
			</td>
		</tr>
	</table>
	<br />
	<% } %>
	
	<p>
		<a href="<%= request.getContextPath() %>/el?a=form">새 주소 추가</a>
	</p>
	
</body>
</html>