package passageiro;

import java.util.ArrayList;
import java.util.List;

import reserva.Reserva;
import cliente.Cliente;
import apoio.ReservasDAOException;

public interface PassageiroDAO {
	
	public void gravar(Passageiro passageiro) throws ReservasDAOException;
	public void atualizar(Passageiro passageiro) throws ReservasDAOException;
	public void excluir(String codReserva, String assentoIda) throws ReservasDAOException;
	public Passageiro procurar(String codReserva, String assentoIda) throws ReservasDAOException;
	public List listar() throws ReservasDAOException;
	// Lista todos os passageiros da reserva pelo email do cliente
	public List listarPassDaReserva(String email, String codReserva) throws ReservasDAOException;
	public void gravarLista(ArrayList<Passageiro> passageiros) throws ReservasDAOException;
	public void atualizarLista(ArrayList<Passageiro> passageiros) throws ReservasDAOException;

}
