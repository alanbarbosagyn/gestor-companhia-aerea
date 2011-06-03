package aeroporto;

import java.sql.Connection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import conexao.Conexao;
import conexao.RAereasDAOException;

public interface AeroportoDAO {
	
	public String gravar();
	public String atualizar();
	public String excluir();
	public List listarTodos();
	public Aeroporto procurarAeroporto();

}
