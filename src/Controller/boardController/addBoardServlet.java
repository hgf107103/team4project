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
import object.userInfoVO;
import object.userVO;

/**
 * Servlet implementation class addBoardServlet
 */
@WebServlet("/contents/board/add")
public class addBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public addBoardServlet() {
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
			boardVO boardTemp = new boardVO();
			
			if(session.getAttribute("userLogin") == null) {
				System.out.println("addBoardServlet POST Call : No Login");
				request.setAttribute("errorMessage", "비로그인 접근");
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
			
			userInfoVO infoTemp = sqlse.selectOne("userInfoMapper.callUserInfo", userTemp.getUserNumber());
			
			
			
			/*if (infoTemp.getBoardCount() >= 30) {
				check = "overCount";
				int commentDeleteCheck = sqlse.delete("commentMapper.deleteAllComment", userTemp.getUserNumber());
			}*/
			
			
			
			Date dateTemp = new Date(System.currentTimeMillis());
			Date date = new Date((dateTemp.getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
			
			
			if(userTemp.getUserStopDay().getTime() <= date.getTime()) {
				boardTemp.setUserNumber(userTemp.getUserNumber());
				boardTemp.setCategoryNumber(categoryNumber);
				boardTemp.setBoardText(request.getParameter("boardText"));
				boardTemp.setBoardDate(date);
				
				int dbResultCheck = sqlse.insert("boardMapper.insertBoard", boardTemp);
				
				if(dbResultCheck > 0) {
					if(!check.equals("overCount")) {
						check = "success";
					}
					sqlse.commit();
				}
				
				System.out.println(boardTemp.toString());
			}
			else if(userTemp.getUserStopDay().getTime() >= date.getTime()) {
				check = "userStop";
				dateString = userTemp.getUserStopDay().toString();
			}
			
			
		} catch (Exception e) {
			System.out.println("addBoardServlet POST Call : " + e);
			request.setAttribute("errorMessage", "새 글 쓰기 오류 발생");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} finally {
			System.out.println("글쓰기 종료");
			
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check + "\",");
			pw.write("\"dateString\":\""+ dateString + "\"");
			pw.write("}");
		};
		
	}

}
