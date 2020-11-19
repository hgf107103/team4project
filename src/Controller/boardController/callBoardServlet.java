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

import module.DatabaseModule.MyBatisConnectionFactory;
import object.boardVO;
import object.userVO;

/**
 * Servlet implementation class callBoardServlet
 */
@WebServlet("/callBoardServlet")
public class callBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public callBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("callBoardServlet GET Call : �߸��� ���� ���� �Դϴ�.");
		request.setAttribute("errorMessage", "�߸��� ����");
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		try {
			boardVO boardTemp = new boardVO();
			int boardNumberParam = Integer.parseInt(request.getParameter("boardNumber"));
			boardTemp.setBoardNumber(boardNumberParam);
			int categoryNumber = 0;

			System.out.println(2);
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
			
			boardTemp.setCategoryNumber(categoryNumber);
			List<boardVO> list = sqlse.selectList("boardMapper.callBoardList", boardTemp);
			
			
			int lastNumber = 1;
			if (list != null) {
				pw.write("{");
				pw.write("\"check\":\"success\",");
				pw.write("\"board\":[");
				for (int i = 0; i < list.size(); i++) {
					userVO temp = sqlse.selectOne("userMapper.isBoardUserCheck", list.get(i).getUserNumber());
					if (i == (list.size() - 1)) {
						lastNumber = list.get(i).getBoardNumber();
						pw.write("{");
						pw.write("\"boardNumber\":\"" + list.get(i).getBoardNumber() + "\",");
						pw.write("\"userName\":\"" + temp.getUserNickname() + "\",");
						pw.write("\"boardText\":\"" + list.get(i).getBoardText() + "\",");
						pw.write("\"boardDate\":\"" + list.get(i).getBoardDate() + "\"");
						pw.write("}");
						break;
					}
					pw.write("{");
					pw.write("\"boardNumber\":\"" + list.get(i).getBoardNumber() + "\",");
					pw.write("\"userName\":\"" + temp.getUserNickname() + "\",");
					pw.write("\"boardText\":\"" + list.get(i).getBoardText() + "\",");
					pw.write("\"boardDate\":\"" + list.get(i).getBoardDate() + "\"");
					pw.write("},");
				}
				pw.write("],");
				pw.write("\"lastNumber\":\"" + lastNumber + "\"");
				pw.write("}");
			}
			sqlse.close();

		} catch (Exception e) {
			/*System.out.println("callBoardServlet POST Call : " + e);
			request.setAttribute("errorMessage", "�� �ҷ����� ����");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);*/
		}
	}

}
