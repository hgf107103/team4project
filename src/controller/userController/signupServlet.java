package Controller.userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import module.CryptoModule.cryptoObject;
import module.DatabaseModule.MyBatisConnectionFactory;
import object.userVO;

/*	ȸ�������� ����ϴ� �����Դϴ�.
 * 	ȸ�����Խ� ��й�ȣ�� ��ȣȭ�ϴ� cryptoObject�� ���Ǿ�����
 * 	GET������� �ҷ����� ��� ERROR �������� redirect �ǵ��� �� �ֽʽÿ�  */
@WebServlet("/signup")
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
		
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			userVO userTemp = new userVO();
			Date date = new Date(System.currentTimeMillis());
			
			cryptoObject crypto = cryptoObject.getInstence(request.getParameter("signupPWD"));
			crypto.setSHA256String();
			
			userTemp.setUserID(request.getParameter("signupID"));
			userTemp.setUserPassword(crypto.getHashString());
			userTemp.setUserName(request.getParameter("signupName"));
			userTemp.setUserNickname(request.getParameter("signupNickName"));
			userTemp.setUserCreateDate(date);
			System.out.println(userTemp.toString());
			
			int sqlCheck = sqlse.insert("userMapper.insertUserID", userTemp);
			System.out.println(sqlCheck);
			
			sqlse.commit();
			sqlse.close();
			PrintWriter pw = response.getWriter();
			
			if(sqlCheck <= 0) {
				pw.write("<script>");
				pw.write("alert('ȸ������ ���� �߻��߽��ϴ�.');");
				pw.write("location.href='index.jsp';");
				pw.write("</script>");
				return;
			}
			pw.write("<script>");
			pw.write("alert('ȸ������ �����߽��ϴ�.');");
			pw.write("location.href='index.jsp';");
			pw.write("</script>");
			return;
		} catch (Exception e) {
			System.out.println("signipServlet POST Call : " + e);
			request.setAttribute("errorMessage", "ȸ������ ���� �߻��߽��ϴ�.");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
	}

}
