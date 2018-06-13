package servlet;

import dao.UserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class RegisterServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
            String userId = request.getParameter("userId");
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
            if (!password1.equals(password2)){

                out.print("<script language='javascript'>alert('两次密码不同');window.location.href='registerpage';</script>");
                return;
            }
                Pattern pattern = Pattern.compile("^[1-9]\\d*$");
            if(!pattern.matcher(userId).matches()){
                out.print("<script language='javascript'>alert('账号必须为数字');window.location.href='registerpage';</script>");
            }
            if(new UserDAO().getUser(userId)){
                new UserDAO().insertUser(userId,password1);
                out.print("<script language='javascript'>alert('注册成功');window.location.href='loginpage';</script>");
            }
            else{
                out.print("<script language='javascript'>alert('已存在的用户');window.location.href='registerpage';</script>");
            }


    }
}
