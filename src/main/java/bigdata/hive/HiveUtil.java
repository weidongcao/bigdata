package bigdata.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class HiveUtil {
    public static final String URL = "jdbc:hive2://spark.don.com:10000/default";
    public static final String USERNAME = "dong";
    public static final String PASSWORD = "123123";
    public static final String DRIVERNAME = "org.apache.hive.jdbc.HiveDriver";

    public static Connection getConnection(String driverName, String url, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static void closeConnection(Connection conn, Statement stmt) throws SQLException {
        stmt.close();
        conn.close();
    }
}
