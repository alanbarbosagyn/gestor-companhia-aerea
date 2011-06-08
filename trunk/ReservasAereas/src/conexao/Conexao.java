/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package conexao;

import java.sql.*;

/**
 *
 * @author ricardo_chacal
 */
public class Conexao {

    public static Connection getConnection() throws RAereasDAOException {
        try {
            Class.forName("org.postgresql.Driver");
            String bd = "jdbc:postgresql://localhost/financiall";
            String usr = "financiall";
            String pswrd = "financiall";
            return DriverManager.getConnection(bd, usr, pswrd);
        } catch (Exception e) {                                 
            throw new RAereasDAOException(e.getMessage());
        }
        
    }

    public static void closeConnection(Connection conn, Statement stat, ResultSet rs)
            throws RAereasDAOException {
        close(conn, stat, rs);
    }

    public static void closeConnection(Connection conn, Statement stat)
            throws RAereasDAOException {
        close(conn, stat);
    }

    public static void closeConnection(Connection conn) throws RAereasDAOException {
        close(conn);
    }

    private static void close(Connection conn, Statement stat, ResultSet rs)
            throws RAereasDAOException {
        try {
            if (conn != null) conn.close();
            if (stat != null) stat.close();
            if (rs != null) rs.close();
        } catch (Exception e) {
            throw new RAereasDAOException(e.getMessage());
        }
    }

    private static void close(Connection conn, Statement stat)
            throws RAereasDAOException {
        try {
            if (conn != null) conn.close();
            if (stat != null) stat.close();
        } catch (Exception e) {
            throw new RAereasDAOException(e.getMessage());
        }
    }

    private static void close(Connection conn) throws RAereasDAOException {
        try {
            if (conn != null) conn.close();
        } catch (Exception e) {
            throw new RAereasDAOException(e.getMessage());
        }
    }

}
