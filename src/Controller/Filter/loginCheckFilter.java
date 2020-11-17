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
		System.out.println("loginCheckFilter 파괴");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("회원가입 서블릿 필터");
		
		if (request.getAttribute("userlogin") != null) {
			System.out.println("로그인 사용자 회원가입 페이지에 접근함을 감지");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
			return;
		}
		chain.doFilter(request, response);
		System.out.println("회원가입 서블릿 필터 작동 정상 종료");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("loginCheckFilter 생성");
	}

}
