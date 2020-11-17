package Controller.boardController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import module.DatabaseModule.MyBatisConnectionFactory;
import object.userVO;

/**
 * Servlet implementation class selectUser
 */
@WebServlet("/select")
public class selectUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
			System.out.println("1");
			
			userVO a = new userVO();
			System.out.println("2");
			
			userVO out = sqlse.selectOne("userMapper.isUserCheck", null);
			System.out.println("3");
			
			System.out.println(out.toString());
			sqlse.commit();
			sqlse.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("서블릿 오류 발생");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
