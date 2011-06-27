package cliente;

import java.util.Date;

public class Cliente {
	private String nome;
	private String email;
	private String senha;
	private String telefone;
	private int nCartao;
	private Date dataCriacaoConta;
	
	public Cliente() {
		
	}
	
	public Cliente(String nome, String email, String senha, String telefone,
			int nCartao, Date dataCriacaoConta) {
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.nCartao = nCartao;
		this.dataCriacaoConta = dataCriacaoConta;
		
	}
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public int getnCartao() {
		return nCartao;
	}
	public void setnCartao(int nCartao) {
		this.nCartao = nCartao;
	}
	public Date getDataCriacaoConta() {
		return dataCriacaoConta;
	}
	public void setDataCriacaoConta(Date dataCriacaoConta) {
		this.dataCriacaoConta = dataCriacaoConta;
	}
	

}
