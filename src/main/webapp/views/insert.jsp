
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파라미터 받아오기
	String name = request.getParameter("name");
	String hp = request.getParameter("hp");
	String tel = request.getParameter("tel");
	// 파라미터 확인
	
	// DAO
	AddressDao dao = new AddressDaoImpl();
	
	// Vo 객체
 	AddressVo vo = new AddressVo();
	vo.setName(name);
	vo.setHp(hp);
	vo.setTel(tel);
	
	dao.insert(vo);

	response.sendRedirect(request.getContextPath() + "/addressbook/");
%>