package reserva;

import java.util.Date;
import java.util.List;

import passageiro.Passageiro;

public class Reserva {
	private String codigo;
	private String tipo;
	private Date dataReserva;
	private Date horaReserva;
	private int numParcelas;
	private int numPassageiros;
	private String formaPagamento;
	private double Valor;
	private String codVooIda;
	private String codVooVolta;
	private int codAerpIda;
	private int codAerpVolta;
	private String clienteEmail;
	
	public Reserva() {
		
	}
	
	public Reserva(String codigo, String tipo, Date dataReserva,
			Date horaReserva, int numParcelas, int numPassageiros,
			String formaPagamento, double valor, String codVooIda,
			String codVooVolta, int codAerpIda, int codAerpVolta,
			String clienteEmail) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
		this.dataReserva = dataReserva;
		this.horaReserva = horaReserva;
		this.numParcelas = numParcelas;
		this.numPassageiros = numPassageiros;
		this.formaPagamento = formaPagamento;
		Valor = valor;
		this.codVooIda = codVooIda;
		this.codVooVolta = codVooVolta;
		this.codAerpIda = codAerpIda;
		this.codAerpVolta = codAerpVolta;
		this.clienteEmail = clienteEmail;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	public Date getHoraReserva() {
		return horaReserva;
	}
	public void setHoraReserva(Date horaReserva) {
		this.horaReserva = horaReserva;
	}
	public int getNumParcelas() {
		return numParcelas;
	}
	public void setNumParcelas(int numParcelas) {
		this.numParcelas = numParcelas;
	}
	public int getNumPassageiros() {
		return numPassageiros;
	}
	public void setNumPassageiros(int numPassageiros) {
		this.numPassageiros = numPassageiros;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public double getValor() {
		return Valor;
	}
	public void setValor(double valor) {
		Valor = valor;
	}
	public String getCodVooIda() {
		return codVooIda;
	}
	public void setCodVooIda(String codVooIda) {
		this.codVooIda = codVooIda;
	}
	public String getCodVooVolta() {
		return codVooVolta;
	}
	public void setCodVooVolta(String codVooVolta) {
		this.codVooVolta = codVooVolta;
	}
	public int getCodAerpIda() {
		return codAerpIda;
	}
	public void setCodAerpIda(int codAerpIda) {
		this.codAerpIda = codAerpIda;
	}
	public int getCodAerpVolta() {
		return codAerpVolta;
	}
	public void setCodAerpVolta(int codAerpVolta) {
		this.codAerpVolta = codAerpVolta;
	}
	public String getClienteEmail() {
		return clienteEmail;
	}
	public void setClienteEmail(String clienteEmail) {
		this.clienteEmail = clienteEmail;
	}

}
