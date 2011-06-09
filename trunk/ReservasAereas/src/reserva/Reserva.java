package reserva;

import java.sql.Date;

public class Reserva {
	private int codigo;
	private char tipo;
	private Date dataReserva;
	private int numParcelas;
	private String formaPagamento;
	private float valorTotal;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public Date getDataReserva() {
		return dataReserva;
	}
	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}
	public int getNumParcelas() {
		return numParcelas;
	}
	public void setNumParcelas(int numParcelas) {
		this.numParcelas = numParcelas;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

}
