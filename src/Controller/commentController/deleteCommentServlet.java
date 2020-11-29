package Controller.commentController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import Object.boardVO;
import Object.commentVO;
import Object.userVO;
import Module.databaseModule.MyBatisConnectionFactory;

/**
 * Servlet implementation class deleteCommentServlet
 */
@WebServlet("/contents/board/comment/delete")
public class deleteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public deleteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		PrintWriter pw = response.getWriter();
		String check = "false";
		int number = 0;
		try {
			commentVO commentTemp = new commentVO();
			int boardNumberParam = Integer.parseInt(request.getParameter("boardNumber"));
			int commentNumberParam = Integer.parseInt(request.getParameter("commentNumber"));
			int userNumberParam = Integer.parseInt(request.getParameter("userNumber"));
			commentTemp.setBoardNumber(boardNumberParam);
			number = boardNumberParam;
			
			if (session.getAttribute("userLogin") == null) {
				
				check = "notLogin";
				
			} else if (session.getAttribute("userLogin") != null) {
				userVO sessionUser = (userVO)(session.getAttribute("userLogin"));
				userVO userTemp = sqlse.selectOne("userMapper.isBoardUserCheck",userNumberParam);
				
				if (sessionUser.getUserID().equals(userTemp.getUserID()) && sessionUser.getUserNumber() == userTemp.getUserNumber()) {
					
					commentTemp.setBoardNumber(boardNumberParam);
					commentTemp.setCommentNumber(commentNumberParam);
					commentTemp.setUserNumber(sessionUser.getUserNumber());
					int deleteResult = sqlse.delete("commentMapper.deleteComment", commentTemp);
					if (deleteResult > 0) {
						sqlse.commit();
						check = "success";
					} else if (deleteResult <= 0) {
						check = "fail";
					}
				} else if (!sessionUser.getUserID().equals(userTemp.getUserID()) || sessionUser.getUserNumber() != userTemp.getUserNumber()) {
					check = "notYours";
				}
				
				
			}
			
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check +"\",");
			pw.write("\"boardNumber\":\""+ number + "\"");
			pw.write("}");
		} catch (Exception e) {
			sqlse.close();
			response.sendError(403);
		}
	}

}
