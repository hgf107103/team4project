package Controller.masterController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import module.DatabaseModule.MyBatisConnectionFactory;
import object.commentVO;
import object.userVO;


@WebServlet("/master/user/comment/delete")
public class masterUserCommentDeleteListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public masterUserCommentDeleteListServlet() {
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
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		try {
			
			System.out.println("masterUserCommentDeleteListServlet : " + request.getParameter("userNumber"));
			int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
			int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			int userNumber = Integer.parseInt(request.getParameter("userNumber"));
			String check = "fail";
			
			if (session.getAttribute("userLogin") != null) { //세션에 로그인 정보가 있을 경우
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {//어드민이 아닐경우
					pw.write("{");
					pw.write("\"check\":\"noAdmin\"");
					pw.write("}");
				}
				
				if (userCheck != null) {//어드민일 경우
					
					commentVO commentTemp = new commentVO();
					commentTemp.setBoardNumber(boardNumber);
					commentTemp.setCommentNumber(commentNumber);
					commentTemp.setUserNumber(userNumber);
					int deleteCheck = sqlse.delete("commentMapper.deleteComment", commentTemp);
					
					if(deleteCheck > 0) {
						check = "success";
						sqlse.commit();
					}
					if(deleteCheck <= 0) {
						check = "fail";
					}
				}
			} else if(session.getAttribute("userLogin") == null) { //세션에 로그인 정보가 없을 경우
				check = "notLogin";
			}
			
			sqlse.close();
			pw.write("{");
			pw.write("\"check\":\""+ check +"\",");
			pw.write("\"commentNumber\":\""+ commentNumber + "\"");
			pw.write("}");
			
		} catch (Exception e) {
			System.out.println("masterUserCommentDeleteListServlet ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
