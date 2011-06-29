package voo;

import java.util.Date;

public class VooCompleto {
	private String codigo;
	private String companhia;
	private Date data;
	private String origem;
	private String destino;
	private String horaSaida;
	private String horaChegada;
	private Double valor;
	
	public VooCompleto() {
		
	}
	public VooCompleto(String codigo, String companhia, Date data,
			String origem, String destino, String horaSaida,
			String horaChegada, Double valor) {
		super();
		this.codigo = codigo;
		this.companhia = companhia;
		this.data = data;
		this.origem = origem;
		this.destino = destino;
		this.horaSaida = horaSaida;
		this.horaChegada = horaChegada;
		this.valor = valor;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCompanhia() {
		return companhia;
	}
	public void setCompanhia(String companhia) {
		this.companhia = companhia;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(String horaSaida) {
		this.horaSaida = horaSaida;
	}
	public String getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}

}
