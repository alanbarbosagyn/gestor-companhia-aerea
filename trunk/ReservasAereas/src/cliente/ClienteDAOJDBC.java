package cliente;

import java.sql.Connection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import conexao.Conexao;
import conexao.RAereasDAOException;

public class ClienteDAOJDBC implements ClienteDAO {
	private Cliente c = new Cliente();
	private final Connection conn;

    public ClienteDAOJDBC() throws RAereasDAOException {
        try {
            this.conn = Conexao.getConnection();
        } catch (Exception e) {
            throw new RAereasDAOException("Erro: " + ":\n" + e.getMessage());
        }
    }
	
	public String gravar() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (!c.getSenha().equalsIgnoreCase(c.getConfirmaSenha())) {
			FacesMessage facesMessage = new FacesMessage("A senha n‹o foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}
		// salva o usuario
		return "sucesso";
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
	
	public Cliente procurarCliente() {
		return null;
		
	}

}
