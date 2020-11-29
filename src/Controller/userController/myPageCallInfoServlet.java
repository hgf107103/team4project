package Controller.userController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import Module.databaseModule.MyBatisConnectionFactory;
import Object.userInfoVO;
import Object.userVO;

@WebServlet("/user/mypage/info")
public class myPageCallInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public myPageCallInfoServlet() {
        super();
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
		try {
			
			if (session.getAttribute("userLogin") == null) {
				//System.out.println("유저로그인 값없음");
				pw.write("{");
				pw.write("\"check\":\"notLogin\"");
				pw.write("}");
				
			} else if (session.getAttribute("userLogin") != null) {
				
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				
				userInfoVO info = sqlse.selectOne("userInfoMapper.callUserInfo", userTemp.getUserNumber());
				
				if(info == null) {
					pw.write("{");
					pw.write("\"check\":\"fail\"");
					pw.write("}");
				}
				if(info != null) {
					System.out.println(info.toString());
					pw.write("{");
					pw.write("\"check\":\"success\",");
					pw.write("\"boardCount\":\""+ info.getBoardCount() + "\",");
					pw.write("\"commentCount\":\""+ info.getCommentCount() + "\"");
					pw.write("}");
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("masterResetUserStopdayServlet POST ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
