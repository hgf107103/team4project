package Controller.masterController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import Object.userVO;
import Module.databaseModule.MyBatisConnectionFactory;


@WebServlet("/master/user/list")
public class masterUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public masterUserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("abc");
		response.sendError(400);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		String check = "fail";
		try {
			if (session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {
					pw.write("{");
					pw.write("\"check\":\"noAdmin\"");
					pw.write("}");
				}
				if (userCheck != null) {
					
					List<userVO> list = sqlse.selectList("userMapper.isUserList");
					
					if(list.size() > 0) {
						Date dateTemp = new Date(System.currentTimeMillis());
						Date date = new Date((dateTemp.getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
						
						pw.write("{");
						pw.write("\"check\":\"success\",");
						pw.write("\"userList\":[");
						for (int i = 0; i < list.size(); i++) {
							int userStopDayTemp = 0;
							String userStatus = "활성";
							
							if(date.getTime() < list.get(i).getUserStopDay().getTime()) {
								userStopDayTemp = (int) ((list.get(i).getUserStopDay().getTime() - date.getTime()) / (24 * 60 * 60 * 1000));
								userStatus = "제한";
							}
							if(list.get(i).getUserOutCheck() != 0) {
								userStatus = "영구정지";
							}
							
							if (i == (list.size() - 1)) {
								pw.write("{");
								pw.write("\"userNumber\":\"" + list.get(i).getUserNumber() + "\",");
								pw.write("\"userID\":\"" + list.get(i).getUserID() + "\",");
								pw.write("\"userName\":\"" + list.get(i).getUserName() + "\",");
								pw.write("\"userNickname\":\"" + list.get(i).getUserNickname() + "\",");
								pw.write("\"userStopDay\":\"" + userStopDayTemp + "\",");
								pw.write("\"userStatus\":\"" + userStatus + "\"");
								pw.write("}");
								break;
							}
							
							pw.write("{");
							pw.write("\"userNumber\":\"" + list.get(i).getUserNumber() + "\",");
							pw.write("\"userID\":\"" + list.get(i).getUserID() + "\",");
							pw.write("\"userName\":\"" + list.get(i).getUserName() + "\",");
							pw.write("\"userNickname\":\"" + list.get(i).getUserNickname() + "\",");
							pw.write("\"userStopDay\":\"" + userStopDayTemp + "\",");
							pw.write("\"userStatus\":\"" + userStatus + "\"");
							pw.write("},");
						}
						pw.write("]}");
						
					}
					if(list.size() <= 0) {
						pw.write("{");
						pw.write("\"check\":\"noUser\"");
						pw.write("}");
					}
				}
				
			}
			if (session.getAttribute("userLogin") == null) {
				pw.write("{");
				pw.write("\"check\":\"noAdmin\"");
				pw.write("}");
			}
			
			sqlse.close();
			
			
		} catch (Exception e) {
			sqlse.close();
			response.sendError(400);
			
		}
	}

}
