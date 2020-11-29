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

import Object.userVO;
import Module.databaseModule.MyBatisConnectionFactory;

/**
 * Servlet implementation class masterUserBackServlet
 */
@WebServlet("/master/user/back")
public class masterUserBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public masterUserBackServlet() {
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
			
			int userNumber = Integer.parseInt(request.getParameter("userNumber"));
			if(userNumber <= 1) {
				return;
			}
			
			if (session.getAttribute("userLogin") == null) {//로그인된 유저가 없을경우 아웃
				//System.out.println("유저로그인 값없음");
				check = "notLogin";
				
			} else if (session.getAttribute("userLogin") != null) {//로그인된 유저가 있을경우 다음으로
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {//로그인된 유저가 어드민이 아닐경우 아웃
					check = "notAdmin";
				}
				
				if (userCheck != null) {//로그인된 유저가 어드민일 경우 실행
				
					userVO selectUserTemp = sqlse.selectOne("userMapper.isBoardUserCheck", userNumber);
					
					if (selectUserTemp != null) {
						
						if(selectUserTemp.getUserOutCheck() == 1) {
							int updateCheck = sqlse.update("userMapper.updateUserBackDay", userNumber);
							
							if(updateCheck <= 0) {
								check = "fail";
							}
							if(updateCheck > 0) {
								int dateUpdate = 1;
								Date dateTemp = new Date((System.currentTimeMillis() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
								if(selectUserTemp.getUserStopDay().getTime() >= dateTemp.getTime()) {
									userVO userDateUpdate = new userVO();
									userDateUpdate.setUserStopDay(new Date(dateTemp.getTime() - (1 * (24 * 60 * 60 * 1000))));
									userDateUpdate.setUserNumber(userNumber);
									
									dateUpdate = sqlse.update("userMapper.updateUserStopDay", userDateUpdate);
								}
								
								//System.out.println(dateUpdate);
								if(dateUpdate <= 0) {
									check = "fail";
								}
								if(dateUpdate > 0) {
									check = "success";
									sqlse.commit();
								}
							}
						}
						
						if(selectUserTemp.getUserOutCheck() == 0) {
							check = "alreadyUser";
						}
						
					}
					if (selectUserTemp == null) {
						check = "fail";
					}
					
				}
			}
			
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check +"\"");
			pw.write("}");
			
		}catch (Exception e) {
			System.out.println("masterUserBackServlet ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
