package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.phonbook.vo.PhoneBookVo;
import com.test.phonebook.dao.PhoneBookDao;
import com.test.phonebook.dao.PhoneBookDaoImpl;


public class PhoneBookServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 확인
		// a=form이면 가입폼으로 FORWARD
		String actionName = req.getParameter("a");

		if ("form".equals(actionName)) {
			// a=form이면
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/form.jsp");
			// 전달
			rd.forward(req, resp);
		} else {
			// DAO에서 목록을 받아서 jsp로 전달
			PhoneBookDao dao = new PhoneBookDaoImpl();
			List<PhoneBookVo> list = dao.getList();

			// 요청에 list를 추가
			// list 객체를 list 키로 추가
			req.setAttribute("list", list);
			// JSP로 요청을 전달(FORWARD)

			// Dispatcher 확보
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/emaillist/index.jsp");
			// FORWARD
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
		
		if ("add".equals(actionName)) {	//	a=add
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			//	VO 객체 생성
			PhoneBookVo vo = new PhoneBookVo();
			vo.setName(name);
			vo.setHp(hp);
			vo.setTel(tel);
			
			//	INSERT 처리
			PhoneBookDao dao = new PhoneBookDaoImpl();
			int insertedCount = dao.insert(vo);
			
			//	처리 후 list페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/el");
		} else if ("delete".equals(actionName)) {
			//	a=delete면
			Long no = Long.valueOf(req.getParameter("no"));
			
			PhoneBookDao dao = new PhoneBookDaoImpl();
			int deletedCount = dao.delete(no);
			
			//	리스트 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/el");
		} else if ("reset".equals(actionName)){
			rd.forward(req, resp);
		}
	}

}