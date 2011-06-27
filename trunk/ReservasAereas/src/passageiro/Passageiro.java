package passageiro;

public class Passageiro {
	private String nome;
	private String codReserva;
	private String assentoIda;
	private String assentoVolta;
	
	public Passageiro() {
		
	}
	
	public Passageiro(String nome, String codReserva, String assentoIda,
			String assentoVolta) {
		this.nome = nome;
		this.codReserva = codReserva;
		this.assentoIda = assentoIda;
		this.assentoVolta = assentoVolta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodReserva() {
		return codReserva;
	}
	public void setCodReserva(String codReserva) {
		this.codReserva = codReserva;
	}
	public String getAssentoIda() {
		return assentoIda;
	}
	public void setAssentoIda(String assentoIda) {
		this.assentoIda = assentoIda;
	}
	public String getAssentoVolta() {
		return assentoVolta;
	}
	public void setAssentoVolta(String assentoVolta) {
		this.assentoVolta = assentoVolta;
	}

}
