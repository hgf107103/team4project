package Controller.boardController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class inBoardServlet
 */
@WebServlet("/contents/board")
public class inBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public inBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			if (request.getParameter("categoryName") == null) {
				response.sendError(400);
				return;
			}
			
			request.setAttribute("categoryName", request.getParameter("categoryName"));
			switch (request.getParameter("categoryName")) {
			case "LOL":
				request.setAttribute("categoryALLName", "리그 오브 레전드");
				break;
			case "BG":
				request.setAttribute("categoryALLName", "배틀그라운드");
				break;
			case "OW":
				request.setAttribute("categoryALLName", "오버워치");
				break;
			default:
				response.sendError(404);
				return;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/VIEW/BoardVIEW/boardPage.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			response.sendError(403);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400);
	}

}
