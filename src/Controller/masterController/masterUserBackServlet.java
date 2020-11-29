package Controller.masterController;

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

import Object.userVO;
import Module.databaseModule.MyBatisConnectionFactory;

/**
 * Servlet implementation class masterUserBackServlet
 */
@WebServlet("/master/user/back")
public class masterUserBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public masterUserBackServlet() {
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
		HttpSession session = request.getSession();
		SqlSession sqlse = MyBatisConnectionFactory.getSqlSession();
		PrintWriter pw = response.getWriter();
		String check = "fail";
		try {
			
			int userNumber = Integer.parseInt(request.getParameter("userNumber"));
			if(userNumber <= 1) {
				return;
			}
			
			if (session.getAttribute("userLogin") == null) {//�α��ε� ������ ������� �ƿ�
				//System.out.println("�����α��� ������");
				check = "notLogin";
				
			} else if (session.getAttribute("userLogin") != null) {//�α��ε� ������ ������� ��������
				userVO userTemp = (userVO)(session.getAttribute("userLogin"));
				userVO userCheck = sqlse.selectOne("userMapper.isAdminUserCheck", userTemp);
				
				if (userCheck == null) {//�α��ε� ������ ������ �ƴҰ�� �ƿ�
					check = "notAdmin";
				}
				
				if (userCheck != null) {//�α��ε� ������ ������ ��� ����
				
					userVO selectUserTemp = sqlse.selectOne("userMapper.isBoardUserCheck", userNumber);
					
					if (selectUserTemp != null) {
						
						if(selectUserTemp.getUserOutCheck() == 1) {
							int updateCheck = sqlse.update("userMapper.updateUserBackDay", userNumber);
							
							if(updateCheck <= 0) {
								check = "fail";
							}
							if(updateCheck > 0) {
								int dateUpdate = 1;
								Date dateTemp = new Date((System.currentTimeMillis() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
								if(selectUserTemp.getUserStopDay().getTime() >= dateTemp.getTime()) {
									userVO userDateUpdate = new userVO();
									userDateUpdate.setUserStopDay(new Date(dateTemp.getTime() - (1 * (24 * 60 * 60 * 1000))));
									userDateUpdate.setUserNumber(userNumber);
									
									dateUpdate = sqlse.update("userMapper.updateUserStopDay", userDateUpdate);
								}
								
								//System.out.println(dateUpdate);
								if(dateUpdate <= 0) {
									check = "fail";
								}
								if(dateUpdate > 0) {
									check = "success";
									sqlse.commit();
								}
							}
						}
						
						if(selectUserTemp.getUserOutCheck() == 0) {
							check = "alreadyUser";
						}
						
					}
					if (selectUserTemp == null) {
						check = "fail";
					}
					
				}
			}
			
			sqlse.close();
			
			pw.write("{");
			pw.write("\"check\":\""+ check +"\"");
			pw.write("}");
			
		}catch (Exception e) {
			System.out.println("masterUserBackServlet ERROR : " + e);
			sqlse.close();
			response.sendError(400);
		}
	}

}
