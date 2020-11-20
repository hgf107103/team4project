package Controller.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("*")
public class errorFilter implements Filter {

    public errorFilter() {
        // TODO Auto-generated constructor stub
    }
	public void destroy() {
		System.out.println("ERROR FILTER : destroy");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse inResponse = (HttpServletResponse)response;
		HttpServletRequest inRequest = (HttpServletRequest)request;
		
		if(inResponse.getStatus() == 404) {
			System.out.println("À×");
		}
		
		chain.doFilter(request, response);
		if(inResponse.getStatus() == 404) {
			inRequest.setAttribute("errorMessage", "404¿À·ù");
		}
		
	}
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("ERROR FILTER : init");
	}

}
