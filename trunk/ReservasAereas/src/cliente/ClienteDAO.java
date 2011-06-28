package cliente;

import java.util.List;

import apoio.ReservasDAOException;

public interface ClienteDAO {
	
	public void gravar(Cliente cliente) throws ReservasDAOException;
	public void atualizar(Cliente cliente) throws ReservasDAOException;
	public void excluir(String email) throws ReservasDAOException;
	public Cliente procurar(String email) throws ReservasDAOException;
	public List listar() throws ReservasDAOException;

}
