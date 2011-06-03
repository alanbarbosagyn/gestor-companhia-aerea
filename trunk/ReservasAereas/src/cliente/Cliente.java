package cliente;

import java.sql.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

public class Cliente {
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String confirmaSenha;
	private String numTelefone;
	private String numCartaoCredito;
	private Date dataCriacaoConta;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmaSenha() {
		return confirmaSenha;
	}
	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	public String getNumTelefone() {
		return numTelefone;
	}
	public void setNumTelefone(String numTelefone) {
		this.numTelefone = numTelefone;
	}
	public String getNumCartaoCredito() {
		return numCartaoCredito;
	}
	public void setNumCartaoCredito(String numCartaoCredito) {
		this.numCartaoCredito = numCartaoCredito;
	}
	public Date getDataCriacaoConta() {
		return dataCriacaoConta;
	}
	public void setDataCriacaoConta(Date dataCriacaoConta) {
		this.dataCriacaoConta = dataCriacaoConta;
	}
	
	

}
