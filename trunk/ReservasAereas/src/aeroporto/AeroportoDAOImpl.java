package aeroporto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import apoio.Conexao;
import apoio.ReservasDAOException;

public class AeroportoDAOImpl implements AeroportoDAO {
	
	private final Connection conn;

	public AeroportoDAOImpl() throws ReservasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new ReservasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }

	@Override
	public Aeroporto procurar(String numero) throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;
        Aeroporto aeroporto = new Aeroporto();
        if (numero == null) {
            throw new ReservasDAOException("Nenhum aeroporto foi informado!");
        }
        try {
            String sql = "SELECT * FROM aeroporto "
                    + "WHERE numero=?";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, numero);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new ReservasDAOException("Erro ao procurar aeroporto!" + numero);
            }
            aeroporto.setNumero(rs.getInt("numero"));
            aeroporto.setCidade(rs.getString("cidade"));
            aeroporto.setNome(rs.getString("nome"));
            aeroporto.setPais(rs.getString("pais"));
            
            return aeroporto;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Aeroporto" + e);
        } finally {
            Conexao.closeConnection(conn1, ps);
        }
	}

	@Override
	public List listar() throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;

        try {
            List<Aeroporto> listAeroporto = new ArrayList<Aeroporto>();
            String sql = "SELECT * FROM aeroporto";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
            	int numero = rs.getInt("numero");
            	String nome = rs.getString("nome");
            	String pais = rs.getString("pais");
            	String cidade = rs.getString("cidade");

            	listAeroporto.add(new Aeroporto(numero, nome, pais, cidade));
            }

            return listAeroporto;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao listar Aeroportos" + e);
        } finally {
            Conexao.closeConnection(conn1, ps, rs);
        }
	}

	@Override
	public List listarCidadesDestino(int num) throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;

        try {
            List<Aeroporto> listAeroporto = new ArrayList<Aeroporto>();
            String sql = "select a2.cidade, a2.numero from aeroporto a1, aeroporto a2, escala e " +
            		"where a1.numero = ? and a1.numero = e.num_aerptOrigem and e.num_aerptDestino = a2.numero;";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setInt(1, num);
            rs = ps.executeQuery();
            while (rs.next()) {
            	String cidade = rs.getString("cidade");
            	int numero = rs.getInt("numero");

            	listAeroporto.add(new Aeroporto(cidade, numero));
            }

            return listAeroporto;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao listar Cidades Destino" + e);
        } finally {
            Conexao.closeConnection(conn1, ps, rs);
        }
	}

}
