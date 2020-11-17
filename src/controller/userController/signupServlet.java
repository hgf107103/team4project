package Controller.userController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*	ȸ�������� ����ϴ� �����Դϴ�.
 * 	ȸ�����Խ� ��й�ȣ�� ��ȣȭ�ϴ� cryptoObject�� ���Ǿ�����
 * 	GET������� �ҷ����� ��� ERROR �������� redirect �ǵ��� �� �ֽʽÿ�  */
@WebServlet("/signupServlet")
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("signipServlet GET Call : �߸��� ���� ���� �Դϴ�.");
		request.setAttribute("errorMessage", "�߸��� ����");
		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
