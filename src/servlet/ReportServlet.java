package servlet;

import bean.User;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        user.setPower(-1);
        session.setAttribute("user",user);
        new UserDAO().updataUser(user,"power",-1);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print("<script language='javascript'>alert('挂失成功');window.location.href='record';</script>");
    }
}
