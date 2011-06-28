package voo;

public class Voo {
	private String codigo;
	private int numAssentos;
	private int numCPAerea;
	
	public Voo() {
		
	}
	
	public Voo(String codigo, int numAssentos, int numCPAerea) {
		super();
		this.codigo = codigo;
		this.numAssentos = numAssentos;
		this.numCPAerea = numCPAerea;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getNumAssentos() {
		return numAssentos;
	}
	public void setNumAssentos(int numAssentos) {
		this.numAssentos = numAssentos;
	}
	public int getNumCPAerea() {
		return numCPAerea;
	}
	public void setNumCPAerea(int numCPAere) {
		this.numCPAerea = numCPAerea;
	}

}
