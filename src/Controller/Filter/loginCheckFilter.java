package Controller.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class loginCheckFilter
 */
@WebFilter("/signupServlet")
public class loginCheckFilter implements Filter {

    public loginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		System.out.println("loginCheckFilter �ı�");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("ȸ������ ���� ����");
		
		if (request.getAttribute("userlogin") != null) {
			System.out.println("�α��� ����� ȸ������ �������� �������� ����");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}
		chain.doFilter(request, response);
		System.out.println("ȸ������ ���� ���� �۵� ���� ����");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("loginCheckFilter ����");
	}

}
