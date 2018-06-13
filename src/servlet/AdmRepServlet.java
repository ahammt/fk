package servlet;

import com.sun.xml.internal.bind.v2.model.core.ID;
import dao.ConDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AdmRepServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("Id");
        new UserDAO().updataUser(userId,"power",0);
        response.sendRedirect("/select");
    }
}
