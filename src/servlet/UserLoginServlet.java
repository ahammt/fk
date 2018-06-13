package servlet;

import bean.User;
import dao.UserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录跳转页面
public class UserLoginServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException,IOException{
        //获取账号密码
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        //调用dao中查询，并new一个user，存入session
        User user = new UserDAO().getUser(userId,password);
        if(null!=user){
            request.getSession().setAttribute("user",user);
            response.sendRedirect("/record");
        }else

//            response.sendRedirect("/login.jsp");
            response.sendRedirect("login.jsp?error=yes");
    }
}
