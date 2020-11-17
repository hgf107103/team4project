package Controller.userController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import module.DatabaseModule.MyBatisConnectionFactory;
import object.userVO;

/**
 * Servlet implementation class signupCheckServlet
 */
@WebServlet("/signupCheckServlet")
public class signupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public signupCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		boolean check = false;
		try {
			userVO userTemp = new userVO();
			userTemp.setUserID(request.getParameter("userid"));
			userVO userOut = sqlse.selectOne("userMapper.isUserCheck", userTemp);
			
			if(userOut == null || userOut.getUserID() == null) check = true;
			
			System.out.println(request.getParameter("userid") + "아이디 체크 결과 : " + check);
		} catch (Exception e) {
			
			System.out.println("signidCheckServlet GET Call : " + e);
			request.setAttribute("errorMessage", "회원가입 체크 오류발생");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
			
		} finally {
			sqlse.close();
			
			PrintWriter pw = response.getWriter();
			
			pw.write("{");
			pw.write("\"check\":\"" + check + "\"");
			pw.write("}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("signipServlet GET Call : 잘못된 영역 접근 입니다.");
		request.setAttribute("errorMessage", "잘못된 접근");
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	}

}
