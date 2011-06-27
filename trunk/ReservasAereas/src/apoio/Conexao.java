package apoio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexao {

    public static Connection getConnection() throws ReservasDAOException {
        try {
            Class.forName("org.postgresql.Driver");
            String bd = "jdbc:postgresql://localhost:5432/trabalhoBD";
            String usr = "financiall";
            String pswrd = "financiall";
            return DriverManager.getConnection(bd, usr, pswrd);
        } catch (Exception e) {                                 
            throw new ReservasDAOException(e.getMessage());
        }
        
    }

    public static void closeConnection(Connection conn, Statement stat, ResultSet rs)
            throws ReservasDAOException {
        close(conn, stat, rs);
    }

    public static void closeConnection(Connection conn, Statement stat)
            throws ReservasDAOException {
        close(conn, stat);
    }

    public static void closeConnection(Connection conn) throws ReservasDAOException {
        close(conn);
    }

    private static void close(Connection conn, Statement stat, ResultSet rs)
            throws ReservasDAOException {
        try {
            if (conn != null) conn.close();
            if (stat != null) stat.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            throw new ReservasDAOException(e.getMessage());
        }
    }

    private static void close(Connection conn, Statement stat)
            throws ReservasDAOException {
        try {
            if (conn != null) conn.close();
            if (stat != null) stat.close();
        } catch (Exception e) {
            throw new ReservasDAOException(e.getMessage());
        }
    }

    private static void close(Connection conn) throws ReservasDAOException {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
            throw new ReservasDAOException(e.getMessage());
        }
    }

}