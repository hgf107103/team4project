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

import Object.userVO;
import Module.cryptoModule.cryptoObject;
import Module.databaseModule.MyBatisConnectionFactory;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/user/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public loginServlet() {
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
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		String check = "fail";
		try {
			
			cryptoObject crypto = cryptoObject.getInstence(request.getParameter("userLoginPWD"));
			crypto.setSHA256String();
			
			//System.out.println("userLoginID : " + request.getParameter("userLoginID"));
			//System.out.println("userLoginPWD : " + crypto.getHashString());
			userVO userTemp = new userVO();
			userTemp.setUserID(request.getParameter("userLoginID"));
			userTemp.setUserPassword(crypto.getHashString());
			
			userVO userAdminCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
			
			if(userAdminCheck != null) {
				//System.out.println("admin Login : " + userAdminCheck.toString());
				session.setAttribute("userLogin", userAdminCheck);
				check = "admin";
			}
			
			else if(userAdminCheck == null) {
				userVO useridCheck = sqlse.selectOne("userMapper.isUserCheck", userTemp);
				
				if(useridCheck != null) {
					if(useridCheck.getUserOutCheck() == 0) {
						userVO userCheck = sqlse.selectOne("userMapper.isUserLogin", userTemp);
						
						if(userCheck != null) {
							//System.out.println("Login Success : " + userCheck.toString());
							check = "loginSuccess";
							session.setAttribute("userLogin", userCheck);
						}
						else if(userCheck == null) {
							//System.out.println("Login Fail : pwdWrong");
							check = "pwdWrong";
						}
					} else if(useridCheck.getUserOutCheck() == 1) {
						//System.out.println("Login Fail : stopUser");
						check = "stopUser";
					}
				}
				else if(useridCheck == null) {
					//System.out.println("Login Fail : idWrong");
					check = "idWrong";
				}
				
			}
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check + "\"");
			pw.write("}");
		} catch (Exception e) {
			System.out.println("로그인 오류 : " + e);
			sqlse.close();
			response.sendError(400);
		}
		
		
		
	}

}
