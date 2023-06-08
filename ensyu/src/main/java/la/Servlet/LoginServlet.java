package la.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import la.bean.ProfileBean;
import la.dao.DAOException;
import la.dao.getProfileDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private void gotoPage(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		getProfileDAO dao = new getProfileDAO();
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		ProfileBean person = new ProfileBean(email,pass);
		
		try {
			person = dao.getProfile(person);
			
			
			
		} catch (DAOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		
		
		//パスワードが正しいか
		if(email == null || email.length() == 0 || pass == null || pass.length() == 0 ) {
			request.setAttribute("message", "入力されていません。");
			gotoPage(request, response, "/errInternal.jsp");
			return;
		}else if (person.getName() == null) {
			request.setAttribute("message", "メールアドレスかパスワードが間違っています。");
			gotoPage(request, response, "/errInternal.jsp");
			
		//セッション中かどうか
		}else if(session.getAttribute("isLogin") != null && session.getAttribute("isLogin").equals("true")){
			request.setAttribute("message", person.getName()+"さんはすでにログインしています");
			gotoPage(request, response, "/errInternal.jsp");
			
		}else {
			session = request.getSession();
			session.setAttribute("person", person);
			session.setAttribute("isLogin", "true");
			gotoPage(request, response, "/loginSuccess.jsp");
		}
		
		//パスワードは正しい、セッションを保存
		//すでにセッション中
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if(session != null) {
			session.invalidate();
			gotoPage(request, response, "/login.jsp");
		}
//		doPost(request, response);
	}

}
