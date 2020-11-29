package Controller.boardController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import Object.userVO;
import Module.databaseModule.MyBatisConnectionFactory;

/**
 * Servlet implementation class callBoardServlet
 */
@WebServlet("/contents/board/call")
public class callBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public callBoardServlet() {
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
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		PrintWriter pw = response.getWriter();
		try {
			boardVO boardTemp = new boardVO();
			int boardNumberParam = Integer.parseInt(request.getParameter("boardNumber"));
			//System.out.println(1);
			int categoryNumber = 0;
			boardTemp.setBoardNumber(boardNumberParam);
			String userID = "null";
			//System.out.println(2);
			if(session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userID = userTemp.getUserName();
			}
			//System.out.println(3);
			switch (request.getParameter("categoryName")) {
			case "LOL":
				categoryNumber = 1;
				break;
			case "BG":
				categoryNumber = 2;
				break;
			case "OW":
				categoryNumber = 3;
				break;

			default:
				break;
			}
			//System.out.println(4);
			boardTemp.setCategoryNumber(categoryNumber);
			List<boardVO> list = sqlse.selectList("boardMapper.callBoardList", boardTemp);
			//System.out.println(5);
			
			
			int lastNumber = 1;
			if (list != null) {
				pw.write("{");
				pw.write("\"check\":\"success\",");
				pw.write("\"board\":[");
				//System.out.println(6);
				for (int i = 0; i < list.size(); i++) {
					userVO temp = sqlse.selectOne("userMapper.isBoardUserCheck", list.get(i).getUserNumber());
					//System.out.println(i + " : " + temp + " // " + list.get(i));
					if (i == (list.size() - 1)) {
						lastNumber = list.get(i).getBoardNumber();
						pw.write("{");
						pw.write("\"boardNumber\":\"" + list.get(i).getBoardNumber() + "\",");
						pw.write("\"userNumber\":\"" + temp.getUserNumber() + "\",");
						pw.write("\"userName\":\"" + temp.getUserNickname() + "\",");
						pw.write("\"boardText\":\"" + list.get(i).getBoardText() + "\",");
						pw.write("\"boardDate\":\"" + list.get(i).getBoardDate() + "\",");
						pw.write("\"commentCount\":\"" + list.get(i).getCommentCount() + "\"");
						pw.write("}");
						break;
					}
					pw.write("{");
					pw.write("\"boardNumber\":\"" + list.get(i).getBoardNumber() + "\",");
					pw.write("\"userNumber\":\"" + temp.getUserNumber() + "\",");
					pw.write("\"userName\":\"" + temp.getUserNickname() + "\",");
					pw.write("\"boardText\":\"" + list.get(i).getBoardText() + "\",");
					pw.write("\"boardDate\":\"" + list.get(i).getBoardDate() + "\",");
					pw.write("\"commentCount\":\"" + list.get(i).getCommentCount() + "\"");
					pw.write("},");
				}
				pw.write("],");
				pw.write("\"lastNumber\":\"" + lastNumber + "\",");
				pw.write("\"sessionID\":\"" + userID + "\"");
				pw.write("}");
			}
			sqlse.close();

		} catch (Exception e) {
			System.out.println("callBoardServlet POST Call : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
