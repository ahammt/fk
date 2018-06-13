package dao;

import bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static void main(String[] args) {
        System.out.println(new UserDAO());
    }

    public User getUser(String userId, String password) { //通过传入的用户名和密码，从数据库中获取用户信息，存入User，并return
        User result = null;
        try {
            Connection c = new ConDAO().c();
            String sql = "select * from user where userId = ? and password = ?";
            PreparedStatement ps = c.prepareStatement(sql);

            ps.setString(1, userId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

//            System.out.println("余额"+balance+"权限"+power);
            if (rs.next()) {
                int balance = rs.getInt(3);
                int power = rs.getInt(4);
                result = new User();
                result.setPassword(password);
                result.setUserId(userId);
                result.setBalance(balance);
                result.setPower(power);
            }

            ps.close();

            c.close();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return result;
    }

    //更新数据方法，i为要修改的参数,v为参数的值，i可为password/
    public void updataUser(User user, String i, int v) {
        try {
            Connection c = new ConDAO().c();
//            String sql = "updata user set "+i+"=? where userId = ?";
            String sql = "update user set " + i + " = ? where userId = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, v);
            ps.setString(2, user.getUserId());
            ps.execute();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updataUser(String user, String i, int v) {
        try {
            Connection c = new ConDAO().c();
//            String sql = "updata user set "+i+"=? where userId = ?";
            String sql = "update user set " + i + " = ? where userId = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, v);
            ps.setString(2, user);
            ps.execute();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updataUser(User user, String i, String v) {
        try {
            Connection c = new ConDAO().c();
//            String sql = "updata user set "+i+"=? where userId = ?";
            String sql = "update user set " + i + " = ? where userId = ?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, v);
            ps.setString(2, user.getUserId());
            ps.execute();

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean getUser(String userId) {
        boolean f = false;
        try {
            Connection c = new ConDAO().c();
            String sql = "select * from user where userId = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f = false;
            } else {
                f = true;
            }
            ps.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }

    public void insertUser(String userId, String password) {
        try {
            Connection c = new ConDAO().c();
            String sql = "insert into user value(?,?,?,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, password);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delUser(String userId) {
        try {

            Connection c = new ConDAO().c();
            String sql = "delete from user where userId = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userId);
            ps.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers(String userId) {
        List<User> users = new ArrayList<>();
        try {
            Connection c = new ConDAO().c();
            String sql = "select * from user where userId like ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%"+userId+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString(1));
                user.setPower(rs.getInt(4));
                users.add(user);
            }
            ps.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }
}
