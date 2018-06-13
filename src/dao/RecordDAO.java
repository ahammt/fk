package dao;

import bean.Record;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordDAO {
    public static void main(String[] args) {
        System.out.println(new RecordDAO());
    }
    public List<Record> getRecord(String userId){
        List<Record> records = new ArrayList<Record>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fanka?characterEncoding=UTF-8",
                    "root", "root");
            String sql = "select * from record where userId = ? order by date";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            int i = 6;
            while (rs.next()&&i>0){
                Record record = new Record();
                String message = rs.getString(2);
                String date = rs.getString(4);
                int pay = rs.getInt(3);
                record.setUserId(userId);
                record.setMessage(message);
                record.setDate(date);
                record.setPay(pay);
                records.add(record);
                i--;
            }

            ps.close();

            c.close();
        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return records;
    }
    public void insertRecord(String userId, String date, String message, int pay){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fanka?characterEncoding=UTF-8",
                    "root", "root");
            String sql = "insert into record values(?,?,?,?) ";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1,userId);
            ps.setString(2,message);
            ps.setInt(3,pay);
            System.out.println(date);
            java.sql.Date Date=java.sql.Date.valueOf(date);
            ps.setDate(4,Date);
            ps.execute();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

