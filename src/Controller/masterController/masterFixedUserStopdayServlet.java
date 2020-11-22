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
import object.boardVO;
import object.userVO;

/**
 * Servlet implementation class masterFixedUserStopdayServlet
 */
@WebServlet("/master/user/board/stopDay")
public class masterFixedUserStopdayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public masterFixedUserStopdayServlet() {
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
		int number = 0;
		try {
			int userNumberParam = Integer.parseInt(request.getParameter("userNumber"));
			int addDay = Integer.parseInt(request.getParameter("addDay"));
			
			if (session.getAttribute("userLogin") == null) {
				//System.out.println("유저로그인 값없음");
				check = "notLogin";
				
			} else if (session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				if (userCheck == null) {
					check = "notAdmin";
				}
				
				if (userCheck != null) {
					
					userVO userMain = sqlse.selectOne("userMapper.isBoardUserCheck", userNumberParam);
					if(userMain == null) {
						check = "fail";
					}
					if(userMain != null) {
						
						Date dateTemp = new Date((System.currentTimeMillis() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
						//현재시간 나머지 잔시간 배제
						
						//현재 시간이 userMain의 시간보다 적으면 dateTemp를 userMain의 시간으로 변경함
						if(dateTemp.getTime() < userMain.getUserStopDay().getTime()) {
							dateTemp.setTime(userMain.getUserStopDay().getTime());
						}
						//System.out.println(dateTemp);
						//System.out.println(dateTemp.getTime());
						
						//현재시간 + (추가할 날짜 * 밀리초)
						Date lastDateTemp = new Date(dateTemp.getTime() + (addDay * (24 * 60 * 60 * 1000)));
						//System.out.println(lastDateTemp);
						//System.out.println(lastDateTemp.getTime());
						
						if(addDay >= 30) {
							if(addDay == 30) {
								lastDateTemp.setTime(dateTemp.getTime());
								lastDateTemp.setMonth(lastDateTemp.getMonth() + 1);
							}
							else if(addDay == 365) {
								lastDateTemp.setTime(dateTemp.getTime());
								lastDateTemp.setYear(lastDateTemp.getYear() + 1);
							}
							//System.out.println("asd" + lastDateTemp);
							//System.out.println("asd" + lastDateTemp.getTime());
						}
						
						//유저 스톱시간과 유저 넘버를 조정
						userVO lastUserTemp = new userVO();
						lastUserTemp.setUserStopDay(lastDateTemp);
						lastUserTemp.setUserNumber(userNumberParam);
						//System.out.println(lastUserTemp.toString());
						
						//DB작업
						int updateCheck = sqlse.update("userMapper.updateUserStopDay", lastUserTemp);
						System.out.println(updateCheck);
						if(updateCheck > 0) {
							check = "success";
							sqlse.commit();
							
							number = (int) ((lastDateTemp.getTime() / (24 * 60 * 60 * 1000)) - (dateTemp.getTime() / (24 * 60 * 60 * 1000)));
							
							if(number < 0) {
								number = 0;
							}
							
						}
					}
				}
			}

			sqlse.close();
			pw.write("{");
			pw.write("\"check\":\""+ check +"\",");
			pw.write("\"plusDay\":"+ number);
			pw.write("}");
		} catch (Exception e) {
			System.out.println(e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
