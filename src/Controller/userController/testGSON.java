package Controller.userController;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Module.databaseModule.MyBatisConnectionFactory;
import Object.boardVO;

/**
 * Servlet implementation class testGSON
 */
@WebServlet("/testGSON")
public class testGSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public testGSON() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		PrintWriter pw = response.getWriter();
		boardVO board = new boardVO();
		board.setBoardDate(new Date(System.currentTimeMillis()));
		board.setBoardNumber(2);
		board.setBoardText("¾Æ¾Æ¾Æ¾Ó");
		board.setCategoryNumber(1);
		board.setCommentCount(15);
		board.setUserNumber(9);
		
		Gson create = new GsonBuilder().create();
		pw.write(create.toJson(board));
		
	}

}
