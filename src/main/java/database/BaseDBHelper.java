package database;
import commons.GlobalConstants;

import java.sql.*;

public class BaseDBHelper {
    private static Connection conn;
    public static void connect() throws SQLException {
        conn = DriverManager.getConnection(GlobalConstants.DATABASE_TESTING_URL, GlobalConstants.DB_USER, GlobalConstants.DB_PASSWORD);
    }

    public static void disconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    public static void executeUpdate(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }
    public static boolean recordExists(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        boolean exists = false;
        if (rs.next() && rs.getInt(1) > 0) {
            exists = true;
        }
        rs.close();
        stmt.close();
        return exists;
    }
    public static ResultSet executeQuery(String query, String... params) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            pstmt.setString(i + 1, params[i]);
        }
        return pstmt.executeQuery();
    }
}
