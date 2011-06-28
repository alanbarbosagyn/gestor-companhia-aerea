package escala;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;

import apoio.Conexao;
import apoio.ReservasDAOException;

public class EscalaDAOImpl implements EscalaDAO {
	
	private final Connection conn;

	public EscalaDAOImpl() throws ReservasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new ReservasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }

	@Override
	public Escala procurar(String codVoo, int numAerpOrigem, int numAerpDestino)
			throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;
        Escala escala = new Escala();
        if (codVoo == null || numAerpOrigem <= 0 || numAerpDestino <= 0) {
            throw new ReservasDAOException("Nenhuma escala foi informada!");
        }
        try {
            String sql = "SELECT * FROM escala "
                    + "WHERE cod_voo=? AND num_aerptOrigem = ? " +
                    "AND num_aerptDestino = ?";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, codVoo);
            ps.setInt(2, numAerpOrigem);
            ps.setInt(3, numAerpDestino);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new ReservasDAOException("Erro ao procurar escala!");
            }
            escala.setCodVoo(codVoo);
            escala.setHoraSaida(rs.getDate("horasaida"));
            escala.setHoraChegada(rs.getDate("horachegada"));
            escala.setValor(rs.getDouble("valor"));
            escala.setNumAerpOrigem(rs.getInt("num_aerptorigem"));
            escala.setNumAerpDestino(rs.getInt("num_aerptdestino"));
            return escala;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Escala" + e);
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
            List<Escala> listEscala = new ArrayList<Escala>();
            String sql = "SELECT * FROM escala order by cod_voo";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String codVoo = rs.getString("cod_voo");
                Date horaSaida = rs.getDate("horasaida");
                Date horaChegada = rs.getDate("horachegada");
                double valor = rs.getDouble("valor");
                int numAerpOrigem = rs.getInt("num_aerptorigem");
                int numAerpDestino = rs.getInt("num_aerptdestino");

                listEscala.add(new Escala(codVoo, horaSaida, horaChegada, valor, numAerpOrigem, numAerpDestino));
            }

            return listEscala;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Escalas" + e);
        } finally {
            Conexao.closeConnection(conn1, ps, rs);
        }
	}

}
