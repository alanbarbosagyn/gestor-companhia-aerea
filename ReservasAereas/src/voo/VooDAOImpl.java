package voo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;

import reserva.Reserva;
import apoio.Conexao;
import apoio.ReservasDAOException;

public class VooDAOImpl implements VooDAO {
	
	private final Connection conn;

	public VooDAOImpl() throws ReservasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new ReservasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }

	@Override
	public Voo procurar(String codigo) throws ReservasDAOException{
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;
        Voo voo = new Voo();
        if (codigo == null) {
            throw new ReservasDAOException("Nenhum voo foi informado!");
        }
        try {
            String sql = "SELECT * FROM voo "
                    + "WHERE codigo=?";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new ReservasDAOException("Erro ao procurar voo!" + codigo);
            }
            voo.setCodigo(codigo);
            voo.setNumAssentos(rs.getInt("numassentos"));
            voo.setNumCPAerea(rs.getInt("num_cpaerea"));
            return voo;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Voo" + e);
        } finally {
            Conexao.closeConnection(conn1, ps);
        }
		
	}

	@Override
	public List listar() throws ReservasDAOException{
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;

        try {
            List<Voo> listVoo = new ArrayList<Voo>();
            String sql = "SELECT * FROM voo order by codigo";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("codigo");
                int numAssentos = rs.getInt("numassentos");
                int numCPAerea = rs.getInt("num_cpaerea");

                listVoo.add(new Voo(codigo, numAssentos, numCPAerea));
            }

            return listVoo;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Usuarios" + e);
        } finally {
            Conexao.closeConnection(conn1, ps, rs);
        }
		
	}

}
