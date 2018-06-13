package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConDAO {
    public Connection c()
            throws SQLException,ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fanka?characterEncoding=UTF-8",
                "root", "root");
        return c;
    }
}
