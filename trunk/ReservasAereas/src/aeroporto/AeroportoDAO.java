package aeroporto;

import java.util.List;
import apoio.ReservasDAOException;

public interface AeroportoDAO {
	
	public Aeroporto procurar(String numero) throws ReservasDAOException;
	public List listar() throws ReservasDAOException;
	public List listarCidadesDestino(int num) throws ReservasDAOException;

}
