package Controller.userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import module.CryptoModule.cryptoObject;
import module.DatabaseModule.MyBatisConnectionFactory;
import object.userVO;

/*	회원가입을 담당하는 서블릿입니다.
 * 	회원가입시 비밀번호를 암호화하는 cryptoObject가 사용되었으며
 * 	GET방식으로 불러오는 경우 ERROR 페이지로 redirect 되도록 해 주십시오  */
@WebServlet("/user/signup")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		String check = "fail";
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			System.out.println(1);
			
			if (session.getAttribute("userLogin") != null) {
				response.sendError(400);
				return;
			}
			
			userVO userTemp = new userVO();
			Date date = new Date(System.currentTimeMillis());
			
			cryptoObject crypto = cryptoObject.getInstence(request.getParameter("signupPWD"));
			crypto.setSHA256String();
			
			userTemp.setUserID(request.getParameter("signupID"));
			userTemp.setUserPassword(crypto.getHashString());
			userTemp.setUserName(request.getParameter("signupName"));
			userTemp.setUserNickname(request.getParameter("signupNickName"));
			userTemp.setUserCreateDate(date);
			userTemp.setUserStopDay(new Date(((date.getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000)) - 1 * (24 * 60 * 60 * 1000)));
			System.out.println(userTemp.toString());
			
			int sqlCheck = sqlse.insert("userMapper.insertUserID", userTemp);
			System.out.println(sqlCheck);
			
			sqlse.commit();
			
			PrintWriter pw = response.getWriter();
			
			if(sqlCheck <= 0) {
				check = "fail";
			}
			if(sqlCheck > 0) {
				check = "success";
			}
			
			sqlse.close();
			pw.write("{");
			pw.write("\"check\":\"" + check + "\"");
			pw.write("}");
			return;
		} catch (Exception e) {
			System.out.println(e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
