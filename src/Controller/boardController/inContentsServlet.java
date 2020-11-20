package Controller.boardController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contents")
public class inContentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public inContentsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		try {
			if (request.getParameter("categoryName") == null) {
				response.sendError(400);
				return;
			}
			
			request.setAttribute("categoryName", request.getParameter("categoryName"));
			switch (request.getParameter("categoryName")) {
			case "LOL":
				request.setAttribute("categoryALLName", "���� ���� ������");
				break;
			case "BG":
				request.setAttribute("categoryALLName", "��Ʋ�׶���");
				break;
			case "OW":
				request.setAttribute("categoryALLName", "������ġ");
				break;
			default:
				response.sendError(404);
				return;
			}
			RequestDispatcher rd = request.getRequestDispatcher("VIEW/BoardVIEW/contentsSelectPage.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			System.out.println("inContentsServlet GET Call : �߸��� ���� ���� �Դϴ�.");
			request.setAttribute("errorMessage", "������ ���� ����");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(400);
	}

}
