package aeroporto;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import apoio.ReservasDAOException;

public class Aeroporto {
	private int numero;
	private String nome;
	private String pais;
	private String cidade;
	private String destino;
	
	public Aeroporto() {
		
	}
	public Aeroporto(String cidade, int numero) {
		this.cidade = cidade;
		this.numero = numero;
	}
	public Aeroporto(int numero, String nome, String pais, String cidade) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.pais = pais;
		this.cidade = cidade;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}

}
