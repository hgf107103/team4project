package Controller.boardController;

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
import object.userVO;

/**
 * Servlet implementation class addBoardServlet
 */
@WebServlet("/addBoardServlet")
public class addBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public addBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("addBoardServlet GET Call : �߸��� ���� ���� �Դϴ�.");
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
		String check = "false";
		try {
			boardVO boardTemp = new boardVO();
			
			if(session.getAttribute("userLogin") == null) {
				System.out.println("addBoardServlet POST Call : No Login");
				request.setAttribute("errorMessage", "��α��� ����");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
				return;
			}
			
			int categoryNumber = 0;
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
			userVO userTemp = (userVO)session.getAttribute("userLogin");
			Date date = new Date(System.currentTimeMillis());

			boardTemp.setUserNumber(userTemp.getUserNumber());
			boardTemp.setCategoryNumber(categoryNumber);
			boardTemp.setBoardText(request.getParameter("boardText"));
			boardTemp.setBoardDate(date);
			
			int dbResultCheck = sqlse.insert("boardMapper.insertBoard", boardTemp);
			
			if(dbResultCheck > 0) {
				check = "success";
				sqlse.commit();
			}
			
			System.out.println(boardTemp.toString());
			
		} catch (Exception e) {
			System.out.println("addBoardServlet POST Call : " + e);
			request.setAttribute("errorMessage", "�� �� ���� ���� �߻�");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} finally {
			System.out.println("�۾��� ����");
			
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check + "\"");
			pw.write("}");
		};
		
	}

}