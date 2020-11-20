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
@WebServlet("/user/signup/check")
public class signupCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public signupCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			sqlse.close();
		} catch (Exception e) {
			sqlse.close();
			response.sendError(400);
			
		} finally {
			
			PrintWriter pw = response.getWriter();
			
			pw.write("{");
			pw.write("\"check\":\"" + check + "\"");
			pw.write("}");
		}
	}

}
