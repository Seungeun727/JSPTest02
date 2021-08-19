<%@page import="com.test.phonbook.vo.PhoneBookVo"%>
<%@page import="com.test.phonebook.dao.PhoneBookDaoImpl"%>
<%@page import="com.test.phonebook.dao.PhoneBookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파라미터 받아오기
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String tel = request.getParameter("tel");
	// 파라미터 확인
	
	// DAO
	PhoneBookDao dao = new PhoneBookDaoImpl();
	
	// Vo 객체
 	PhoneBookVo vo = new PhoneBookVo();
	vo.setName(name);
	vo.setHp(hp);
	vo.setTel(tel);
	
	dao.insert(vo);

	response.sendRedirect(request.getContextPath() + "/addressbook/");
%>