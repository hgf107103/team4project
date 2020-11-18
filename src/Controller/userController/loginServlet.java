package Controller.userController;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/userLoginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public loginServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("loginServlet GET Call : 잘못된 영역 접근 입니다.");
		request.setAttribute("errorMessage", "잘못된 접근");
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String check = null;
		try {
			
			cryptoObject crypto = cryptoObject.getInstence(request.getParameter("userLoginPWD"));
			crypto.setSHA256String();
			
			System.out.println("userLoginID : " + request.getParameter("userLoginID"));
			System.out.println("userLoginPWD : " + crypto.getHashString());
			userVO userTemp = new userVO();
			userTemp.setUserID(request.getParameter("userLoginID"));
			userTemp.setUserPassword(crypto.getHashString());
			
			userVO userAdminCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
			
			if(userAdminCheck != null) {
				System.out.println("admin Login : " + userAdminCheck.toString());
				check = "admin";
			}
			
			else if(userAdminCheck == null) {
				userVO useridCheck = sqlse.selectOne("userMapper.isUserCheck", userTemp);
				
				if(useridCheck != null) {
					userVO userCheck = sqlse.selectOne("userMapper.isUserLogin", userTemp);
					
					if(userCheck != null) {
						System.out.println("Login Success : " + userCheck.toString());
						check = "loginSuccess";
						session.setAttribute("userLogin", userCheck);
					}
					else if(userCheck == null) {
						System.out.println("Login Fail : pwdWrong");
						check = "pwdWrong";
					}
				}
				else if(useridCheck == null) {
					System.out.println("Login Fail : idWrong");
					check = "idWrong";
				}
				
			}
		} catch (Exception e) {
			System.out.println("loginServlet POST Call : " + e);
			request.setAttribute("errorMessage", "로그인 오류발생");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} finally {
			System.out.println("로그인 종료");
			
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check + "\"");
			pw.write("}");
		};
		
		
		
	}

}
