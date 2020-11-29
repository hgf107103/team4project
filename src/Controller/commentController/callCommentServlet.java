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
 * Servlet implementation class callCommentServlet
 */
@WebServlet("/contents/board/comment/call")
public class callCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public callCommentServlet() {
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
		PrintWriter pw = response.getWriter();
		try {
			int boardNumberParam = Integer.parseInt(request.getParameter("boardNumber"));
			List<commentVO> list = sqlse.selectList("commentMapper.callCommentList", boardNumberParam);
			System.out.println(list);
			if (list != null) {
				pw.write("{");
				pw.write("\"check\":\"success\",");
				pw.write("\"comment\":[");
				for (int i = 0; i < list.size(); i++) {
					userVO temp = sqlse.selectOne("userMapper.isBoardUserCheck", list.get(i).getUserNumber());
					if (i == (list.size() - 1)) {
						pw.write("{");
						pw.write("\"userNumber\":\"" + list.get(i).getUserNumber() + "\",");
						pw.write("\"commentNumber\":\"" + list.get(i).getCommentNumber() + "\",");
						pw.write("\"commentText\":\"" + list.get(i).getCommentText() + "\",");
						pw.write("\"commentWriter\":\"" + temp.getUserNickname() + "\"");
						pw.write("}");
						break;
					}
					pw.write("{");
					pw.write("\"userNumber\":\"" + list.get(i).getUserNumber() + "\",");
					pw.write("\"commentNumber\":\"" + list.get(i).getCommentNumber() + "\",");
					pw.write("\"commentText\":\"" + list.get(i).getCommentText() + "\",");
					pw.write("\"commentWriter\":\"" + temp.getUserNickname() + "\"");
					pw.write("},");
				}
				pw.write("],");
				pw.write("\"boardNumber\":\"" + boardNumberParam + "\"");
				pw.write("}");
			}
			sqlse.close();

		} catch (Exception e) {
			response.sendError(400);
		}
	}

}
