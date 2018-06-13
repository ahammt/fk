package servlet;

import bean.User;
import dao.RecordDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession(false);
        //dao 创建list<record>
        List<User> users = new UserDAO().getUsers(userId);

        request.getSession().setAttribute("users", users);
        response.sendRedirect("/selectpage");

    }
}
