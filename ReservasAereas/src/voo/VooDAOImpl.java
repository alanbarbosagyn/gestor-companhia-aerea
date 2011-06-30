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
	public Voo procurar(String codigo) throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;
		Voo voo = new Voo();
		if (codigo == null) {
			throw new ReservasDAOException("Nenhum voo foi informado!");
		}
		try {
			String sql = "SELECT * FROM voo " + "WHERE codigo=?";
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
	public List listar() throws ReservasDAOException {
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

	@Override
	public List listarTodosVoos() throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;

		try {
			List<VooCompleto> listVoo = new ArrayList<VooCompleto>();
			String sql = "select v.codigo, comp.nome, d.data, a1.cidade as Origem, " +
					"e1.horaSaida, a2.cidade as Destino, e2.horaChegada, e2.preco from voo v, " +
					"companhiaaerea comp, diassemana d, aeroporto a1, aeroporto a2, escala e1, " +
					"escala e2 where v.codigo = e1.cod_voo and v.codigo=e2.cod_voo and " +
					"v.num_cpAerea = comp.numero and v.codigo=d.cod_voo and " +
					"e1.num_aerptOrigem = a1.numero and e2.num_aerptDestino = a2.numero and a1.cidade != a2.cidade;";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {

				String codigo = rs.getString("codigo");
				String companhia = rs.getString("nome");
				Date data = rs.getDate("data");
				String origem = rs.getString("origem");
				String destino = rs.getString("destino");
				String horaSaida = rs.getString("horasaida");
				String horaChegada = rs.getString("horachegada");
				Double valor = rs.getDouble("preco");

				listVoo.add(new VooCompleto(codigo, companhia, data, origem,
						destino, horaSaida, horaChegada, valor));
			}

			return listVoo;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao listar V™os" + e);
		} finally {
			Conexao.closeConnection(conn1, ps, rs);
		}
	}

	@Override
	public List listarVoosDisp(int codOrigem, int codDestino)
			throws ReservasDAOException {
		PreparedStatement ps = null;
		Connection conn1 = null;
		ResultSet rs = null;

		try {
			List<VooCompleto> listVoo = new ArrayList<VooCompleto>();
			String sql = "select  v.codigo, comp.nome, d.data, a1.cidade as Origem, " +
					"e.horaSaida, a2.cidade as Destino, e.horaChegada, e.preco from voo v, " +
					"companhiaaerea comp, diassemana d, aeroporto a1, aeroporto a2, " +
					"escala e where (e.num_aerptOrigem = ? and e.num_aerptDestino = ? ) and " +
					"e.cod_voo = v.codigo and v.num_cpaerea = comp.numero and " +
					"v.codigo = d.cod_voo and e.num_aerptOrigem = a1.numero and e.num_aerptDestino = a2.numero;";
			conn1 = this.conn;
			ps = conn1.prepareStatement(sql);
			ps.setInt(1, codOrigem);
			ps.setInt(2, codDestino);
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String companhia = rs.getString("nome");
				Date data = rs.getDate("data");
				String origem = rs.getString("origem");
				String destino = rs.getString("destino");
				String horaSaida = rs.getString("horaSaida");
				String horaChegada = rs.getString("horaChegada");
				Double valor = rs.getDouble("preco");

				listVoo.add(new VooCompleto(codigo, companhia, data, origem,
						destino, horaSaida, horaChegada, valor));
			}

			return listVoo;
		} catch (SQLException e) {
			throw new ReservasDAOException("Erro ao listar V™os!" + e);
		} finally {
			Conexao.closeConnection(conn1, ps, rs);
		}
	}

}
