package reserva;

import java.util.List;

import reserva.Reserva;
import apoio.ReservasDAOException;

public interface ReservaDAO {
	
	public void gravar(Reserva reserva) throws ReservasDAOException;
	public void atualizar(Reserva reserva) throws ReservasDAOException;
	public void excluir(String codigo) throws ReservasDAOException;
	public Reserva procurar(String codigo) throws ReservasDAOException;
	// Lista todas as reservas do cliente pelo email
	public List listar(String email) throws ReservasDAOException;

}
