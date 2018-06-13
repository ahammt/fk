package filter;

import bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                        throws IOException,ServletException{
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse=(HttpServletResponse)servletResponse;
        HttpSession session=httpRequest.getSession();
        String uri = httpRequest.getRequestURI();
        User user = (User)session.getAttribute("user");
//        String passUrl = "";
        if (uri.endsWith(".css") || uri.endsWith(".js")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (uri.endsWith("loginpage")||uri.endsWith("registerpage")||uri.endsWith("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (uri.endsWith("selectpage")&&user.getPower()!=1){
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/index");
        }
        else if(null!=user){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            httpResponse.sendRedirect(httpRequest.getContextPath()+"/loginpage");
        }
    }
}
