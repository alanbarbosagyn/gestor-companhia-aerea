package passageiro;

import java.util.List;
import cliente.Cliente;
import apoio.ReservasDAOException;

public interface PassageiroDAO {
	
	public void gravar(Passageiro passageiro) throws ReservasDAOException;
	public void atualizar(Passageiro passageiro) throws ReservasDAOException;
	public void excluir(String codReserva, String assentoIda) throws ReservasDAOException;
	public Passageiro procurar(String codReserva, String assentoIda) throws ReservasDAOException;
	public List listar() throws ReservasDAOException;
	// Lista todos os passageiros da reserva pelo email do cliente
	public List listarPassDaReserva(String email) throws ReservasDAOException;

}
