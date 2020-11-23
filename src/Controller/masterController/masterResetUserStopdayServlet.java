package Controller.masterController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import module.DatabaseModule.MyBatisConnectionFactory;
import object.userVO;

/**
 * Servlet implementation class masterResetUserStopdayServlet
 */
@WebServlet("/master/user/stopReset")
public class masterResetUserStopdayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public masterResetUserStopdayServlet() {
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
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		PrintWriter pw = response.getWriter();
		String check = "fail";
		try {
			
			int userNumberParam = Integer.parseInt(request.getParameter("userNumber"));
			System.out.println(1);
			if (session.getAttribute("userLogin") == null) {
				//System.out.println("유저로그인 값없음");
				check = "notLogin";
				
			} else if (session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {//지금 로그인된 계정이 어드민이 아닐 경우
					check = "notAdmin";
				}
				
				if (userCheck != null) {//지금 로그인된 계정이 어드민일 경우
					
					//파라미터로 받은 유저넘버로 VO객체 생성
					userVO userMain = sqlse.selectOne("userMapper.isBoardUserCheck", userNumberParam);
					if(userMain == null) {//해당하는 유저가 없을 경우
						check = "fail";
					}
					if(userMain != null) {//해당하는 유저가 있을 경우
						
						if(userMain.getUserOutCheck() == 1) {//영구정지된 유저일 경우
							check = "outUser";
						}
						if(userMain.getUserOutCheck() == 0) {
							
							Date dateTemp = new Date(System.currentTimeMillis() - (1 * (24 * 60 * 60 * 1000)));
							userVO userDateTemp = new userVO();
							userDateTemp.setUserNumber(userNumberParam);
							userDateTemp.setUserStopDay(dateTemp);
							int updateCheck = sqlse.update("userMapper.updateUserStopDay", userDateTemp);
							
							if(updateCheck > 0) {
								check = "success";
								sqlse.commit();
							}
							if(updateCheck <= 0) {
								check = "fail";
							}
						}
						
					}
				}
			}
			
			sqlse.close();
			pw.write("{");
			pw.write("\"check\":\""+ check +"\"");
			pw.write("}");
		} catch (Exception e) {
			System.out.println("masterResetUserStopdayServlet ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
