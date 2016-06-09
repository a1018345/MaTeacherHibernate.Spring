package Customer.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import BussinessService.CustomerService;
import Customer.model.CustomerBean;

import Customer.model.CustomerHibernateDAO;
import hibernate.util.HibernateUtil;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerBean bean = new CustomerBean();
	CustomerService cs ;
	@Override
	public void init() throws ServletException {
		cs=new CustomerService(new CustomerHibernateDAO(HibernateUtil.getSessionFactory()));
		
	}

	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String contextPath = getServletContext().getContextPath();
		HttpSession session = request.getSession();
		Map<String, String> list = new HashMap<String, String>();
		int errorCount = 0;

		if (password.trim().equals("")) {

			list.put("pwd", "密碼不可空白");

			session.setAttribute("errorMSG", list);

		}

		if (userName.trim().equals("")) {

			list.put("id", "帳號不可空白");
			session.setAttribute("errorMSG", list);

		}
		System.out.println(list.size());

		if (list.isEmpty()) {
			System.out.println(list.size() + "123312");
			bean = cs.Login(userName, password);
			if (bean != null) {
				session.setAttribute("LoginOk", bean);

				String path = (String) session.getAttribute("path");
				if (path != null) {
					response.sendRedirect(contextPath + path);
					session.removeAttribute("path");
					session.removeAttribute("id");
					session.removeAttribute("pwd");
					session.removeAttribute("error");

				} else {

					RequestDispatcher rd = request.getRequestDispatcher("/pages/product.jsp");
					rd.forward(request, response);
					// response.sendRedirect(contextPath +
					// "/pages/product.jsp");

				}

				System.out.println("成功");

			} else {
				list.put("error", "帳號密碼錯誤");
				session.setAttribute("errorMSG", list);
				RequestDispatcher rd = request.getRequestDispatcher("/secure/login.jsp");
				rd.forward(request, response);
				errorCount++;
				session.setAttribute("errorCount", errorCount);
			}

		} else {

			RequestDispatcher rd = request.getRequestDispatcher("/secure/login.jsp");
			rd.forward(request, response);

		}

		if (!list.isEmpty()) {
			errorCount++;
			session.setAttribute("errorCount", errorCount);

		}

	}
}
