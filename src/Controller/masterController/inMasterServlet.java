package Controller.masterController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import module.DatabaseModule.MyBatisConnectionFactory;
import object.userVO;

/**
 * Servlet implementation class inMasterServlet
 */
@WebServlet("/master")
public class inMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inMasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		try {
			if (session.getAttribute("userLogin") != null) {
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("isAdminUserCheck", userTemp);
				
				if (userCheck == null) {
					response.sendError(403);
				}
				if (userCheck != null) {
					RequestDispatcher rd = request.getRequestDispatcher("/VIEW/MasterVIEW/masterPage.jsp");
					rd.forward(request, response);
				}
				
			}
			if (session.getAttribute("userLogin") == null) {
				response.sendError(403);
			}
		} catch (Exception e) {
			response.sendError(403);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(403);
	}

}
