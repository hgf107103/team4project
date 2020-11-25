package Controller.userController;

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

import module.DatabaseModule.MyBatisConnectionFactory;
import object.boardVO;
import object.commentVO;
import object.userVO;

/**
 * Servlet implementation class myPageCallboardListServlet
 */
@WebServlet("/user/mypage/board")
public class myPageCallboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public myPageCallboardListServlet() {
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
			List<boardVO> list = sqlse.selectList("boardMapper.myPageBoardList", userNumberTemp);
			
			
			if (list != null) {
				pw.write("{");
				pw.write("\"check\":\"success\",");
				pw.write("\"board\":[");
				//System.out.println(6);
				for (int i = 0; i < list.size(); i++) {
					//System.out.println(i + " : " + temp + " // " + list.get(i));
					if (i == (list.size() - 1)) {
						
						pw.write("{");
						pw.write("\"boardNumber\":\"" + list.get(i).getBoardNumber() + "\",");
						pw.write("\"boardText\":\"" + list.get(i).getBoardText() + "\",");
						pw.write("\"boardDate\":\"" + list.get(i).getBoardDate() + "\"");
						pw.write("}");
						break;
					}
					pw.write("{");
					pw.write("\"boardNumber\":\"" + list.get(i).getBoardNumber() + "\",");
					pw.write("\"boardText\":\"" + list.get(i).getBoardText() + "\",");
					pw.write("\"boardDate\":\"" + list.get(i).getBoardDate() + "\"");
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
