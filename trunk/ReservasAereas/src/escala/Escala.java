package escala;

import java.util.Date;

public class Escala {
	private String codVoo;
	private Date horaSaida;
	private Date horaChegada;
	private double valor;
	private int numAerpOrigem;
	private int numAerpDestino;
	
	public Escala() {
		
	}
	public Escala(String codVoo, Date horaSaida, Date horaChegada,
			double valor, int numAerpOrigem, int numAerpDestino) {
		super();
		this.codVoo = codVoo;
		this.horaSaida = horaSaida;
		this.horaChegada = horaChegada;
		this.valor = valor;
		this.numAerpOrigem = numAerpOrigem;
		this.numAerpDestino = numAerpDestino;
	}
	public String getCodVoo() {
		return codVoo;
	}
	public void setCodVoo(String codVoo) {
		this.codVoo = codVoo;
	}
	public Date getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(Date horaSaida) {
		this.horaSaida = horaSaida;
	}
	public Date getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(Date horaChegada) {
		this.horaChegada = horaChegada;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getNumAerpOrigem() {
		return numAerpOrigem;
	}
	public void setNumAerpOrigem(int numAerpOrigem) {
		this.numAerpOrigem = numAerpOrigem;
	}
	public int getNumAerpDestino() {
		return numAerpDestino;
	}
	public void setNumAerpDestino(int numAerpDestino) {
		this.numAerpDestino = numAerpDestino;
	}

}
