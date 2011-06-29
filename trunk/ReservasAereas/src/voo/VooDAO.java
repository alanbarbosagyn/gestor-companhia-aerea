package voo;

import java.util.List;
import apoio.ReservasDAOException;

public interface VooDAO {
	
	public Voo procurar(String codigo) throws ReservasDAOException;
	public List listar() throws ReservasDAOException;
	public List listarVoosDisp(int codOrigem, int codDestino ) throws ReservasDAOException;
	public List listarTodosVoos() throws ReservasDAOException;

}
