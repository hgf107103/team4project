package Controller.commentController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

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
 * Servlet implementation class addCommentServlet
 */
@WebServlet("/contents/board/comment/add")
public class addCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public addCommentServlet() {
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
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		String check = "false";
		String dateString = "0";
		try {
			commentVO commentTemp = new commentVO();
			int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			
			if(session.getAttribute("userLogin") == null) {
				response.sendError(403);
				return;
			}
			
			userVO userTemp = (userVO)session.getAttribute("userLogin");
			Date dateTemp = new Date(System.currentTimeMillis());
			Date date = new Date((dateTemp.getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
			
			
			if(userTemp.getUserStopDay().getTime() < date.getTime()) {
				commentTemp.setBoardNumber(boardNumber);
				commentTemp.setUserNumber(userTemp.getUserNumber());
				commentTemp.setCommentText(request.getParameter("commentText"));
				commentTemp.setCommentDate(date);
				
				int dbResultCheck = sqlse.insert("commentMapper.insertComment", commentTemp);
				
				if(dbResultCheck > 0) {
					check = "success";
					sqlse.commit();
				}
				
				System.out.println(commentTemp.toString());
			}
			else if(userTemp.getUserStopDay().getTime() >= date.getTime()) {
				check = "userStop";
				dateString = userTemp.getUserStopDay().toString();
			}
			
			sqlse.close();
			
		} catch (Exception e) {
			sqlse.close();
			response.sendError(400);
		} finally {
			System.out.println("댓글쓰기 종료");
			
			
			pw.write("{");
			pw.write("\"check\":\""+ check + "\",");
			pw.write("\"dateString\":\""+ dateString + "\"");
			pw.write("}");
		};
	}

}
