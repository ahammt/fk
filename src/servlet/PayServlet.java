package servlet;

import bean.Record;
import bean.User;
import dao.RecordDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class PayServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out=response.getWriter();
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user.getPower()==-1){
            out.print("<script language='javascript'>alert('该卡已挂失 禁止消费');window.location.href='index';</script>");
            return;
        }

        //获取post的记录
        String date = request.getParameter("date");
        String message = request.getParameter("message");
        String pay = request.getParameter("pay");
        //检查金额是否为数字
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        if(!pattern.matcher(pay).matches()){

            out.print("<script language='javascript'>alert('金额必须为数字');window.location.href='index';</script>");
            return;
        }
        //获取session中的余额,检查输入金额是否大于余额


        int b = user.getBalance();
        int p = Integer.parseInt(pay);
        if(b<p){

            out.print("<script language='javascript'>alert('余额不足');window.location.href='index';</script>");
            return;
        }
        //使用save中相同的方法修改余额(感觉蠢，但是不知道怎么优化)
        b=b-p;
        user.setBalance(b);
        new UserDAO().updataUser(user,"balance",b+"");
        request.getSession().setAttribute("user",user);
        //插入记录(由于recordservlet会自动查询并存入记录,所以在此不需要修改session中内容)
        new RecordDAO().insertRecord(user.getUserId(),date,message,p);
        // todo(解决方案：在recordservlet中加入 查询余额并写入session的代码 )
        //跳转至record，重新查询
        response.sendRedirect("/record");
    }
}
