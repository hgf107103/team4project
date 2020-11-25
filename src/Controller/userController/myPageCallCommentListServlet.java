package Controller.userController;

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

import module.DatabaseModule.MyBatisConnectionFactory;
import object.boardVO;
import object.commentVO;
import object.userVO;

/**
 * Servlet implementation class myPageCallCommentListServlet
 */
@WebServlet("/user/mypage/comment")
public class myPageCallCommentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public myPageCallCommentListServlet() {
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
		try {
			
			if(session.getAttribute("userLogin") == null) {
				response.sendError(400);
				return;
			}
			userVO userTemp = (userVO)(session.getAttribute("userLogin"));
			
			int userNumberTemp = userTemp.getUserNumber();
			List<commentVO> list = sqlse.selectList("commentMapper.myPageCommentList", userNumberTemp);
			
			
			if (list != null) {
				pw.write("{");
				pw.write("\"check\":\"success\",");
				pw.write("\"comment\":[");
				//System.out.println(6);
				for (int i = 0; i < list.size(); i++) {
					//System.out.println(i + " : " + temp + " // " + list.get(i));
					if (i == (list.size() - 1)) {
						
						pw.write("{");
						pw.write("\"commentNumber\":\"" + list.get(i).getCommentNumber() + "\",");
						pw.write("\"commentText\":\"" + list.get(i).getCommentText() + "\",");
						pw.write("\"commentDate\":\"" + list.get(i).getCommentDate() + "\"");
						pw.write("}");
						break;
					}
					pw.write("{");
					pw.write("\"commentNumber\":\"" + list.get(i).getCommentNumber() + "\",");
					pw.write("\"commentText\":\"" + list.get(i).getCommentText() + "\",");
					pw.write("\"commentDate\":\"" + list.get(i).getCommentDate() + "\"");
					pw.write("},");
				}
				pw.write("]}");
			}
			sqlse.close();

		} catch (Exception e) {
			System.out.println("myPageCallboardListServlet POST ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
