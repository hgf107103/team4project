package controller.userController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*	회원가입을 담당하는 서블릿입니다.
 * 	회원가입시 비밀번호를 암호화하는 cryptoObject가 사용되었으며
 * 	GET방식으로 불러오는 경우 ERROR 페이지로 redirect 되도록 해 주십시오  */
@WebServlet("/signupServlet")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("signipServlet GET Call : 잘못된 영역 접근 입니다.");
		response.sendRedirect("error.jsp?errorMessage=잘못된영역접근");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
