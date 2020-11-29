package Controller.masterController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
 * Servlet implementation class masterCallBoardListServlet
 */
@WebServlet("/master/user/board")
public class masterUserBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public masterUserBoardListServlet() {
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
		String check = "fail";
		try {
			//System.out.println("masterUserBoardListServlet : " + request.getParameter("userNumber"));
			int userNumber = Integer.parseInt(request.getParameter("userNumber"));
			
			if (session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {
					pw.write("{");
					pw.write("\"check\":\"noAdmin\"");
					pw.write("}");
				}
				
				if (userCheck != null) {
					userVO selectUserTemp = sqlse.selectOne("userMapper.isBoardUserCheck", userNumber);
					
					if(selectUserTemp == null) {
						pw.write("{");
						pw.write("\"check\":\"noSelected\"");
						pw.write("}");
					}
					
					if(selectUserTemp != null) {
						List<boardVO> boardList = sqlse.selectList("boardMapper.oneUserBoardList", userNumber);
						List<commentVO> commentList = sqlse.selectList("commentMapper.oneUserCommentList", userNumber);
						pw.write("{");
						
						pw.write("\"boardList\":[");
						for (int i = 0; i < boardList.size(); i++) {
							if (i == (boardList.size() - 1)) {
								pw.write("{");
								pw.write("\"number\":\"" + boardList.get(i).getBoardNumber() + "\",");
								pw.write("\"text\":\"" + boardList.get(i).getBoardText() + "\",");
								pw.write("\"date\":\"" + boardList.get(i).getBoardDate() + "\"");
								pw.write("}");
								break;
							}
							
							pw.write("{");
							pw.write("\"number\":\"" + boardList.get(i).getBoardNumber() + "\",");
							pw.write("\"text\":\"" + boardList.get(i).getBoardText() + "\",");
							pw.write("\"date\":\"" + boardList.get(i).getBoardDate() + "\"");
							pw.write("},");
						}
						pw.write("],");
						
						pw.write("\"commentList\":[");
						for (int i = 0; i < commentList.size(); i++) {
							if (i == (commentList.size() - 1)) {
								pw.write("{");
								pw.write("\"board\":\"" + commentList.get(i).getBoardNumber() + "\",");
								pw.write("\"number\":\"" + commentList.get(i).getCommentNumber() + "\",");
								pw.write("\"text\":\"" + commentList.get(i).getCommentText() + "\",");
								pw.write("\"date\":\"" + commentList.get(i).getCommentDate() + "\"");
								pw.write("}");
								break;
							}
							
							pw.write("{");
							pw.write("\"board\":\"" + commentList.get(i).getBoardNumber() + "\",");
							pw.write("\"number\":\"" + commentList.get(i).getCommentNumber() + "\",");
							pw.write("\"text\":\"" + commentList.get(i).getCommentText() + "\",");
							pw.write("\"date\":\"" + commentList.get(i).getCommentDate() + "\"");
							pw.write("},");
						}
						pw.write("],");
						
						pw.write("\"check\":\"success\"");
						pw.write("}");
					}
				}
			}
			
			sqlse.close();
		} catch (Exception e) {
			System.out.println("masterUserBoardListServlet ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
