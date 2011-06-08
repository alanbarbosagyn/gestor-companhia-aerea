package reserva;

import java.sql.Connection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import conexao.Conexao;
import conexao.RAereasDAOException;

public class ReservaDAOJDBC implements ReservaDAO {
	
	private final Connection conn;

    public ReservaDAOJDBC() throws RAereasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new RAereasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }
	
	public String gravar() {
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
	
	public Reserva procurarVoo() {
		return null;
		
	}

}
