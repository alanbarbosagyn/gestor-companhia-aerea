package reserva;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;

import apoio.Conexao;
import apoio.ReservasDAOException;

public class ReservaDAOImpl implements ReservaDAO {

	private final Connection conn;

	public ReservaDAOImpl() throws ReservasDAOException {
		try {
			this.conn = Conexao.getConnection();
		} catch (Exception e) {
			throw new ReservasDAOException("Erro: " + ":\n" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		java.util.Date data = new java.util.Date();

		System.out.println(formatador.format(data));
	}

	@Override
	public void gravar(Reserva reserva) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		java.util.Date dataJava = new java.util.Date();
		java.sql.Date dataReserva = new java.sql.Date(dataJava.getTime());
		
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
		java.util.Date data = new java.util.Date();
		String horaReserva = formatador.format(data);
		
		if (reserva == null)
			throw new ReservasDAOException("Nenhuma reserva foi informado!");
		try {
			String sql = "INSERT INTO reserva (codigo, tipo, datareserva, horareserva, "
					+ "numparcelas, numpassageiros, formapagamento, valortotal, cod_vooida, "
					+ "cod_voovolta, num_aerptorigem, num_aerptdestino, cliente_email)"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, reserva.getCodigo());
			ps.setString(2, reserva.getTipo());
			ps.setDate(3, (Date) dataReserva);
			ps.setString(4, horaReserva);
			ps.setInt(5, reserva.getNumParcelas());
			ps.setInt(6, reserva.getNumPassageiros());
			ps.setString(7, reserva.getFormaPagamento());
			ps.setDouble(8, reserva.getValor());
			ps.setString(9, reserva.getCodVooIda());
			ps.setString(10, reserva.getCodVooVolta());
			ps.setInt(11, reserva.getCodAerpIda());
			ps.setInt(12, reserva.getCodAerpVolta());
			ps.setString(13, reserva.getClienteEmail());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao inserir reserva " + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	@Override
	public void atualizar(Reserva reserva) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (reserva == null) {
			throw new ReservasDAOException("Nenhuma reserva foi informado!");
		}
		try {
			String sql = "UPDATE reserva (tipo, "
					+ "numparcelas, numpassageiros, formapagamento, valortotal, cod_vooida, "
					+ "cod_voovolta, num_aerptorigem, num_aerptdestino, cliente_email)"
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, )"
					+ "WHERE codigo = ?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, reserva.getTipo());
			ps.setInt(2, reserva.getNumParcelas());
			ps.setInt(3, reserva.getNumPassageiros());
			ps.setString(4, reserva.getFormaPagamento());
			ps.setDouble(5, reserva.getValor());
			ps.setString(6, reserva.getCodVooIda());
			ps.setString(7, reserva.getCodVooVolta());
			ps.setInt(8, reserva.getCodAerpIda());
			ps.setInt(9, reserva.getCodAerpVolta());
			ps.setString(10, reserva.getClienteEmail());
			ps.setString(11, reserva.getCodigo());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao atualizar Reserva" + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	@Override
	public void excluir(String codigo) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		if (codigo == null || codigo.equalsIgnoreCase("")) {
			throw new ReservasDAOException("Nenhuma reserva foi informada!");
		}
		try {
			String sql = "DELETE FROM reserva WHERE codigo = ?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, codigo);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao excluir reserva " + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}

	}

	@Override
	public Reserva procurar(String codigo) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;
		Reserva reserva = new Reserva();
		if (codigo == null) {
			throw new ReservasDAOException("Nenhuma reserva foi informada!");
		}
		try {
			String sql = "SELECT * FROM reserva " + "WHERE codigo=?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, codigo);
			rs = ps.executeQuery();
			if (!rs.next()) {
				throw new ReservasDAOException("Erro ao procurar reserva!"
						+ codigo);
			}
			reserva.setTipo(rs.getString("tipo"));
			reserva.setDataReserva(rs.getDate("datareserva"));
			reserva.setHoraReserva(rs.getString("horareserva"));
			reserva.setNumParcelas(rs.getInt("numparcelas"));
			reserva.setNumPassageiros(rs.getInt("numpassageiros"));
			reserva.setFormaPagamento(rs.getString("formapagamento"));
			reserva.setValor(rs.getDouble("valortotal"));
			reserva.setCodVooIda(rs.getString("cod_vooida"));
			reserva.setCodVooVolta(rs.getString("cod_voovolta"));
			reserva.setCodAerpIda(rs.getInt("num_aerptorigem"));
			reserva.setCodAerpVolta(rs.getInt("num_aerptdestino"));
			reserva.setClienteEmail(rs.getString("cliente_email"));
			reserva.setCodigo(codigo);
			return reserva;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao procurar Reserva" + e);
		} finally {
			Conexao.closeConnection(conn1, ps);
		}
	}

	@Override
	public List listar(String email) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;

		try {
			List<Reserva> listReserva = new ArrayList<Reserva>();
			String sql = "SELECT * FROM reserva WHERE cliente_email = ?";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String tipo = rs.getString("tipo");
				Date dataReserva = rs.getDate("datareserva");
				String horaReserva = rs.getString("horareserva");
				int numParcelas = rs.getInt("numparcelas");
				int numPassageiros = rs.getInt("numpassageiros");
				String formaPagamento = rs.getString("formapagamento");
				Double Valor = rs.getDouble("valortotal");
				String codVooIda = rs.getString("cod_vooida");
				String codVooVolta = rs.getString("cod_voovolta");
				int codAerpIda = rs.getInt("num_aerptorigem");
				int codAerpVolta = rs.getInt("num_aerptdestino");
				String clienteEmail = rs.getString("cliente_email");

				listReserva.add(new Reserva(codigo, tipo, dataReserva,
						horaReserva, numParcelas, numPassageiros,
						formaPagamento, Valor, codVooIda, codVooVolta,
						codAerpIda, codAerpVolta, clienteEmail));
			}

			return listReserva;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao procurar Reservas" + e);
		} finally {
			Conexao.closeConnection(conn1, ps, rs);
		}
	}

}
