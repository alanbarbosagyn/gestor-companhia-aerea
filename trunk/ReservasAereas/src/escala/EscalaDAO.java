package escala;

import java.util.List;

import apoio.ReservasDAOException;

public interface EscalaDAO {
	
	public Escala procurar(String codVoo, int numAerpOrigem, int numAerpDestino) throws ReservasDAOException;
	public List listar() throws ReservasDAOException;

}
