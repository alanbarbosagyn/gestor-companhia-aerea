package aeroporto;

import java.sql.Connection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import conexao.Conexao;
import conexao.RAereasDAOException;

public class AeroportoDAOJDBC implements AeroportoDAO{

	private final Connection conn;

    public AeroportoDAOJDBC() throws RAereasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new RAereasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }
	
	public String gravar() {
		FacesContext context = FacesContext.getCurrentInstance();
		return null;
	}
	
	public String atualizar() {
		return null;
	}
	
	public String excluir() {
		return null;
	}
	
	public List listarTodos() {
		return null;
	}
	
	public Aeroporto procurarAeroporto() {
		return null;
		
	}

}
