package cliente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import apoio.Conexao;
import apoio.ReservasDAOException;

public class ClienteDAOImpl implements ClienteDAO {
	
	private final Connection conn;

	public ClienteDAOImpl() throws ReservasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new ReservasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }

	@Override
	public void gravar(Cliente cliente) throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        java.util.Date dataJava = new java.util.Date();
        java.sql.Date dataCad = new java.sql.Date(dataJava.getTime());
        cliente.setDataCriacaoConta(dataCad);
        if (cliente == null)
            throw new ReservasDAOException("Nenhum cliente foi informado!");
        try {
            String sql = "INSERT INTO cliente (email, nome, senha, datacriacaoconta," +
            		" telefone, numcartaocredito) " +
                    "values (?, ?, ?, ?, ?, ? )";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, cliente.getEmail());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getSenha());
            ps.setDate(4, (Date) cliente.getDataCriacaoConta());
            ps.setString(5, cliente.getTelefone());
            ps.setInt(6, cliente.getnCartao());

            ps.executeUpdate();
        } catch (SQLException e){
            throw new ReservasDAOException("Erro ao inserir cliente " + e);
        } finally {
            Conexao.closeConnection(conn1, ps);
        }
		
	}

	@Override
	public void atualizar(Cliente cliente) throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        if (cliente == null) {
            throw new ReservasDAOException("Nenhum cliente foi informado!");
        }
        try {
            String sql = "UPDATE cliente SET nome=?, senha=?, telefone=? numcartaocredito=? "
                    + "WHERE email=?";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getSenha());
            ps.setString(3, cliente.getTelefone());
            ps.setInt(4, cliente.getnCartao());
            ps.setString(5, cliente.getEmail());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao atualizar Cliente" + e);
        } finally {
            Conexao.closeConnection(conn1, ps);
        }
		
	}

	@Override
	public void excluir(String email) throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        if (email == null || email.equalsIgnoreCase("")) {
            throw new ReservasDAOException("Nenhum cliente foi informado!");
        }
        try {
            String sql = "DELETE FROM cliente WHERE email = ?";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao excluir cliente " + e);
        } finally {
            Conexao.closeConnection(conn1, ps);
        }
		
	}

	@Override
	public Cliente procurar(String email) throws ReservasDAOException {
		PreparedStatement ps = null;
        Connection conn1 = null;
        ResultSet rs = null;
        Cliente cliente = new Cliente();
        if (email == null) {
            throw new ReservasDAOException("Nenhum cliente foi informado!");
        }
        try {
            String sql = "SELECT * FROM cliente "
                    + "WHERE email=?";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (!rs.next()) {
                throw new ReservasDAOException("Erro ao procurar cliente!" + email);
            }
            cliente.setNome(rs.getString("nome"));
            cliente.setEmail(rs.getString("email"));
            cliente.setSenha(rs.getString("senha"));
            cliente.setDataCriacaoConta(rs.getDate("datacriacaoconta"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setnCartao(rs.getInt("numcartaocredito"));
            return cliente;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Usuario" + e);
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
            List<Cliente> listCliente = new ArrayList<Cliente>();
            String sql = "SELECT * FROM cliente order by asc datacriacaoconta";
            conn1 = this.conn;
            ps = conn1.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                String telefone = rs.getString("telefone");
                int nCartao = rs.getInt("nCartao");
                Date dataCriacaoConta = rs.getDate("datacriacaoconta");

                listCliente.add(new Cliente(nome, email, senha, telefone, nCartao, dataCriacaoConta));
            }

            return listCliente;
        } catch (SQLException e) {
            throw new ReservasDAOException("Erro ao procurar Usuarios" + e);
        } finally {
            Conexao.closeConnection(conn1, ps, rs);
        }
		
	}
	
}
