package servlet;

import bean.Record;
import bean.User;
import dao.RecordDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
//用于打印出index页面中的消费记录表
//必须经由该页面跳转index
public class RecordServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException,IOException {
        //只读session的user
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        //dao 创建list<record>
        List<Record> records = new RecordDAO().getRecord(user.getUserId());
//        System.out.println(user.getUserId()); 测试用
        //存session 跳转
        request.getSession().setAttribute("records", records);
        response.sendRedirect("/index");
//        弃用方法
//        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
