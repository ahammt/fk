package servlet;

import bean.User;
import dao.UserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class SaveServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        //通过正则判断输入是否为数字
        String save = request.getParameter("save");
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        //是数字则执行操作
        if(pattern.matcher(save).matches()){
            //从session中取得user对象
            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            int b = user.getBalance();//余额
            b =b+Integer.parseInt(save);
            user.setBalance(b);
            //调用dao中更新数据方法
            new UserDAO().updataUser(user,"balance",b);
            //...
            request.getSession().setAttribute("user",user);
            response.sendRedirect("/record");
        }
        else {
            //不是则跳转至index并弹窗
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out=response.getWriter();
            out.print("<script language='javascript'>alert('充值失败');window.location.href='index';</script>");
        }
    }
}
