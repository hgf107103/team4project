package Controller.boardController;

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

import module.DatabaseModule.MyBatisConnectionFactory;
import object.boardVO;
import object.commentVO;
import object.userVO;

/**
 * Servlet implementation class deleteBoardServlet
 */
@WebServlet("/contents/board/delete")
public class deleteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public deleteBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("deleteBoardServlet GET Call : 잘못된 영역 접근 입니다.");
		request.setAttribute("errorMessage", "잘못된 접근");
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
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
			int boardNumberParam = Integer.parseInt(request.getParameter("boardNumber"));
			int userNumberParam = Integer.parseInt(request.getParameter("userNumber"));
			
			//System.out.println("b : " + boardNumberParam);
			//System.out.println("u : " + userNumberParam);
			
			number = boardNumberParam;
			
			if (session.getAttribute("userLogin") == null) {
				//System.out.println("유저로그인 값없음");
				check = "notLogin";
				
			} else if (session.getAttribute("userLogin") != null) {
				
				userVO sessionUser = (userVO)(session.getAttribute("userLogin"));
				//System.out.println("세션 유저 불러옴 : " + sessionUser.toString());
				userVO userTemp = sqlse.selectOne("userMapper.isBoardUserCheck",userNumberParam);
				//System.out.println("DB유저가 있는지 확인함 : " + userTemp.toString());
				
				if (sessionUser.getUserID().equals(userTemp.getUserID()) && sessionUser.getUserNumber() == userTemp.getUserNumber()) {
					
					boardVO boardTemp = new boardVO();
					boardTemp.setBoardNumber(boardNumberParam);
					boardTemp.setUserNumber(sessionUser.getUserNumber());
					
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
					
				} else if (!sessionUser.getUserID().equals(userTemp.getUserID()) || sessionUser.getUserNumber() != userTemp.getUserNumber()) {
					check = "notYours";
				}
				
				
			}
			

		} catch (Exception e) {
			System.out.println("deleteCommentServlet POST Call : " + e);
			request.setAttribute("errorMessage", "댓글 삭제 오류");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} finally {
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check +"\",");
			pw.write("\"boardNumber\":\""+ number + "\"");
			pw.write("}");
		}
	}

}
