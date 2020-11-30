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

import Object.boardVO;
import Object.userVO;
import Module.databaseModule.MyBatisConnectionFactory;

/**
 * Servlet implementation class masterUserBoardDeleteListServlet
 */
@WebServlet("/master/user/board/delete")
public class masterUserBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public masterUserBoardDeleteServlet() {
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
			
			System.out.println("masterUserBoardDeleteListServlet : " + request.getParameter("userNumber"));
			int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			int userNumber = Integer.parseInt(request.getParameter("userNumber"));
			String check = "fail";
			if (session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {
					pw.write("{");
					pw.write("\"check\":\"noAdmin\"");
					pw.write("}");
				}
				
				if (userCheck != null) {
					boardVO boardTemp = new boardVO();
					boardTemp.setBoardNumber(boardNumber);
					boardTemp.setUserNumber(userNumber);
					//이 보드 넘버에 해당하는 댓글이 있는지 확인.
					boardVO boardCommentCheck = sqlse.selectOne("boardMapper.callOneBoard", boardTemp);
					
					int nextCheck = 0;
					if (boardCommentCheck.getCommentCount() > 0) {
						int commentDeleteCheck = sqlse.delete("commentMapper.deleteAllComment", boardCommentCheck.getBoardNumber());
						if (commentDeleteCheck > 0) {
							sqlse.commit();
						} else if(commentDeleteCheck <= 0) {
							check = "commentDeleteFail";
							nextCheck = 1;
						}
					}
					
					if(nextCheck == 0) {
						int deleteResult = sqlse.delete("boardMapper.deleteBoard", boardTemp);
						if (deleteResult > 0) {
							sqlse.commit();
							check = "success";
						} else if (deleteResult <= 0) {
							check = "fail";
						}
					}
				}
			} else if(session.getAttribute("userLogin") == null) {
				check = "notLogin";
			}
			sqlse.close();
			pw.write("{");
			pw.write("\"check\":\""+ check +"\",");
			pw.write("\"boardNumber\":\""+ boardNumber + "\"");
			pw.write("}");
			
		} catch (Exception e) {
			System.out.println("masterUserBoardDeleteListServlet ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
