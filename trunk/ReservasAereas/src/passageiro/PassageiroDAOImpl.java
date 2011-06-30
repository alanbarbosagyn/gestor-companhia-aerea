package passageiro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import reserva.Reserva;

import apoio.Conexao;
import apoio.ReservasDAOException;
import cliente.Cliente;

public class PassageiroDAOImpl implements PassageiroDAO {

	private final Connection conn;

	public PassageiroDAOImpl() throws ReservasDAOException {
		try {
			this.conn = Conexao.getConnection();
		} catch (Exception e) {
			throw new ReservasDAOException("Erro: " + ":\n" + e.getMessage());
		}
	}

	@Override
	public void gravar(Passageiro passageiro) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (passageiro == null)
			throw new ReservasDAOException("Nenhum passageiro foi informado!");
		try {
			String sql = "INSERT INTO passageiro (cod_reserva, nome, assentoida, assentovolta)"
					+ "values (?, ?, ?, ? )";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, passageiro.getCodReserva().trim());
			ps.setString(2, passageiro.getNome());
			ps.setString(3, passageiro.getAssentoIda().trim());
			if (passageiro.getAssentoIda() != null
					|| !passageiro.getAssentoIda().equalsIgnoreCase("")) {
				ps.setString(4, passageiro.getAssentoVolta().trim());
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao inserir passageiro " + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	public void gravarLista(ArrayList<Passageiro> passageiros)
			throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (passageiros == null)
			throw new ReservasDAOException("Nenhuma lista de passageiros foi informado!");

		try {
			for (Passageiro passageiro : passageiros) {
				String sql = "INSERT INTO passageiro (cod_reserva, nome, assentoida, assentovolta)"
						+ "values (?, ?, ?, ? )";
				conn1 = this.conn;
				ps = conn1.prepareStatement(sql);
				ps.setString(1, passageiro.getCodReserva().trim());
				ps.setString(2, passageiro.getNome());
				ps.setString(3, passageiro.getAssentoIda().trim());
				if (passageiro.getAssentoIda() != null
						|| !passageiro.getAssentoIda().equalsIgnoreCase("")) {
					ps.setString(4, passageiro.getAssentoVolta().trim());
				}

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao inserir lista de passageiros " + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	@Override
	public void atualizar(Passageiro passageiro) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (passageiro == null) {
			throw new ReservasDAOException("Nenhum passageiro foi informado!");
		}
		try {
			String sql = "UPDATE passageiro SET cod_reserva=?, assentoida=?, "
					+ "nome=? assentovolta=? "
					+ "WHERE cod_reserva=? AND assentoida=?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, passageiro.getCodReserva());
			ps.setString(2, passageiro.getAssentoIda());
			ps.setString(3, passageiro.getNome());
			ps.setString(4, passageiro.getAssentoVolta());
			ps.setString(5, passageiro.getCodReserva());
			ps.setString(6, passageiro.getAssentoIda());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao atualizar Passageiro" + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	@Override
	public void excluir(String codReserva, String assentoIda)
			throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (codReserva == null || codReserva.equalsIgnoreCase("")
				|| assentoIda == null || codReserva.equalsIgnoreCase("")) {
			throw new ReservasDAOException("Nenhum passageiro foi informado!");
		}
		try {
			String sql = "DELETE FROM passageiro WHERE cod_reserva = ? AND assentoida = ?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, codReserva);
			ps.setString(2, assentoIda);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao excluir passageiro " + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	@Override
	public Passageiro procurar(String codReserva, String assentoIda)
			throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;
		Passageiro passageiro = new Passageiro();
		if (codReserva == null || assentoIda == null) {
			throw new ReservasDAOException("Nenhum passageiro foi informado!");
		}
		try {
			String sql = "SELECT * FROM passageiro "
					+ "WHERE cod_reserva=? AND assentoida=?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, codReserva);
			ps.setString(2, assentoIda);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ReservasDAOException("Erro ao procurar passageiro!");
			}
			passageiro.setAssentoIda(rs.getString("assentoida"));
			passageiro.setAssentoVolta(rs.getString("assentovolta"));
			passageiro.setCodReserva(rs.getString("cod_reserva"));
			passageiro.setNome(rs.getString("nome"));
			return passageiro;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao procurar Passageiro" + e);
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
			List<Passageiro> listPassageiro = new ArrayList<Passageiro>();
			String sql = "SELECT * FROM passageiro order by nome";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String codReserva = rs.getString("cod_reserva");
				String assentoIda = rs.getString("assentoida");
				String assentoVolta = rs.getString("assentovolta");

				listPassageiro.add(new Passageiro(nome, codReserva, assentoIda,
						assentoVolta));
			}

			return listPassageiro;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao procurar Usuarios" + e);
		} finally {
			Conexao.closeConnection(conn1, ps, rs);
		}
	}

	@Override
	public List listarPassDaReserva(String email, String codigo) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;

		try {
			List<Passageiro> listPassageiro = new ArrayList<Passageiro>();
			String sql = "SELECT p.cod_reserva, p.assentoida, p.nome, p.assentovolta"
					+ " FROM passageiro p, cliente c, reserva r"
					+ " where c.email= ? and c.email = r.cliente_email and r.codigo = p.cod_reserva and r.codigo = ?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, codigo);
			rs = ps.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("nome");
				String codReserva = rs.getString("cod_reserva");
				String assentoIda = rs.getString("assentoida");
				String assentoVolta = rs.getString("assentovolta");

				listPassageiro.add(new Passageiro(nome, codReserva, assentoIda,
						assentoVolta));
			}

			return listPassageiro;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao procurar Usuarios" + e);
		} finally {
			Conexao.closeConnection(conn1, ps, rs);
		}
	}

	@Override
	public void atualizarLista(ArrayList<Passageiro> passageiros) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (passageiros == null) {
			throw new ReservasDAOException("Nenhum Lista de passageiros foi informada!");
		}
		try {
			for (Passageiro passageiro : passageiros) {

				String sql = "UPDATE passageiro SET cod_reserva=?, assentoida=?, "
						+ "nome=? assentovolta=? "
						+ "WHERE cod_reserva=? AND assentoida=?";
				conn1 = this.conn;
				ps = conn1.prepareStatement(sql);
				ps.setString(1, passageiro.getCodReserva());
				ps.setString(2, passageiro.getAssentoIda());
				ps.setString(3, passageiro.getNome());
				ps.setString(4, passageiro.getAssentoVolta());
				ps.setString(5, passageiro.getCodReserva());
				ps.setString(6, passageiro.getAssentoIda());

				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao atualizar lista de Passageiros" + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}
	}

}
