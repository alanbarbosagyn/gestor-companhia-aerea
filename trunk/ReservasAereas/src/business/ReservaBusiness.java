package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import passageiro.Passageiro;
import passageiro.PassageiroDAO;
import passageiro.PassageiroDAOImpl;

import reserva.Reserva;
import reserva.ReservaDAO;
import reserva.ReservaDAOImpl;
import voo.Voo;
import voo.VooCompleto;
import voo.VooDAO;
import voo.VooDAOImpl;

import cliente.Cliente;
import cliente.ClienteDAO;
import cliente.ClienteDAOImpl;
import escala.Escala;
import aeroporto.Aeroporto;
import aeroporto.AeroportoDAO;
import aeroporto.AeroportoDAOImpl;
import apoio.ReservasDAOException;

public class ReservaBusiness {
	private Cliente cliente = new Cliente();
	private ArrayList<Passageiro> passageiros = null;
	private Voo voo = new Voo();
	private VooCompleto vooCompleto = new VooCompleto();
	private Aeroporto aer = new Aeroporto();
	private Aeroporto aer2 = new Aeroporto();
	private List<SelectItem> aeroportos = new ArrayList<SelectItem>();
	private Escala escala = new Escala();
	private Reserva reserva = new Reserva();
	private boolean logado = false;
	private DataModel model;

	public VooCompleto getVooCompleto() {
		return vooCompleto;
	}

	public void setVooCompleto(VooCompleto vooCompleto) {
		this.vooCompleto = vooCompleto;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<SelectItem> getAeroportos() {
		return aeroportos;
	}

	public void setAeroportos(List<SelectItem> aeroportos) {
		this.aeroportos = aeroportos;
	}

	public Escala getEscala() {
		return escala;
	}

	public void setEscala(Escala escala) {
		this.escala = escala;
	}

	public Aeroporto getAer() {
		return aer;
	}

	public void setAer(Aeroporto aer) {
		this.aer = aer;
	}

	public ArrayList<Passageiro> getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(ArrayList<Passageiro> passageiros) {
		this.passageiros = passageiros;
	}

	public Aeroporto getAer2() {
		return aer2;
	}

	public void setAer2(Aeroporto aer2) {
		this.aer2 = aer2;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public DataModel getModel() {
		return model;
	}

	public void setModel(DataModel model) {
		this.model = model;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public void getReservaManipulacao() {
		setReserva((Reserva) model.getRowData());
	}

	public void getVooCompletoManipulacao() {
		setVooCompleto((VooCompleto) model.getRowData());
	}

	public String autenticaCliente() throws ReservasDAOException {
		ClienteDAO interfaceCliente = new ClienteDAOImpl();
		String retorno;
		Cliente cli = interfaceCliente.procurar(this.cliente.getEmail().trim());
		if (cli != null
				&& cli.getSenha().equals(this.cliente.getSenha().trim())) {
			setCliente(cli);
			setLogado(true);
			retorno = "dashboard";
		} else {
			FacesContext contexto = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage("Usuario ou senha invalido(s).");
			contexto.addMessage("login", msg);
			setLogado(false);
			retorno = "login";
		}
		return retorno;

	}

	public String logout() {
		setLogado(false);
		cliente.setEmail("");
		cliente.setSenha("");
		return "index";
	}

	public String cadastrarCliente() throws ReservasDAOException {
		ClienteDAO interfaceCliente = new ClienteDAOImpl();
		interfaceCliente.gravar(cliente);
		setLogado(true);
		return "dashboard";
	}

	public String cadastrarReserva() throws ReservasDAOException {
		ReservaDAO interfaceReserva = new ReservaDAOImpl();
		interfaceReserva.gravar(reserva);
		PassageiroDAO interfacePassageiro = new PassageiroDAOImpl();
		interfacePassageiro.gravarLista(passageiros);
		return "minhasReservas";
	}

	public DataModel getVoosDisp() throws ReservasDAOException {
		VooDAO vooDao = new VooDAOImpl();
		model = new ListDataModel(vooDao.listarVoosDisp(
				this.reserva.getCodAerpIda(), this.reserva.getCodAerpVolta()));
		return model;
	}

	public DataModel getTodosVoos() throws ReservasDAOException {
		VooDAO vooDao = new VooDAOImpl();
		model = new ListDataModel(vooDao.listarTodosVoos());
		return model;
	}

	public List<SelectItem> getCidades() throws ReservasDAOException {
		AeroportoDAO cidadesDao = new AeroportoDAOImpl();
		List<Aeroporto> aeroportos = cidadesDao.listar();
		List<SelectItem> cidades = new ArrayList<SelectItem>(aeroportos.size());

		for (Aeroporto a : aeroportos) {
			cidades.add(new SelectItem(a.getNumero(), a.getCidade()));
		}

		return cidades;
	}

	public String reload() {
		return "reserva";
	}

	public List<SelectItem> getDestinobyOrigem() throws ReservasDAOException {
		AeroportoDAO destino = new AeroportoDAOImpl();
		List<Aeroporto> aeroporto = destino.listarCidadesDestino(this.reserva
				.getCodAerpIda());
		List<SelectItem> cidadesDestino = new ArrayList<SelectItem>(
				aeroporto.size());
		for (Aeroporto a : aeroporto) {
			cidadesDestino.add(new SelectItem(a.getNumero(), a.getCidade()));
		}
		return cidadesDestino;
	}

	public String listarDestinos() throws ReservasDAOException {
		return "reserva";
	}

	public String fazerReserva() throws ReservasDAOException {
		this.setReserva(new Reserva());
		return "reserva";
	}

	public void parseVooCompletoToReserva() throws ReservasDAOException {
		reserva.obtemCodigoGerado();
		this.reserva.setValor(this.vooCompleto.getValor()
				* this.reserva.getNumPassageiros());
		this.reserva.setCodVooIda(this.vooCompleto.getCodigo());
		this.reserva.setCompanhia(this.vooCompleto.getCompanhia());
		this.reserva.setDataVoo(this.vooCompleto.getData());
		this.reserva.setOrigem(this.vooCompleto.getOrigem());
		this.reserva.setDestino(this.vooCompleto.getDestino());
		this.reserva.setHoraSaida(this.vooCompleto.getHoraSaida());
		this.reserva.setHoraChegada(this.vooCompleto.getHoraChegada());
		this.reserva.setClienteEmail(this.cliente.getEmail());
	}

	public String reservar() throws ReservasDAOException {
		getVooCompletoManipulacao();
		setVooCompleto(vooCompleto);
		parseVooCompletoToReserva();
		this.passageiros = new ArrayList<Passageiro>(
				this.reserva.getNumPassageiros());
		for (int i = 0; i < this.reserva.getNumPassageiros(); i++) {
			Passageiro p = new Passageiro();
			p.setCodReserva(this.reserva.getCodigo());
			this.passageiros.add(p);
		}
		return "cadastroReserva";
	}

	public String detalharVoo() throws ReservasDAOException {
		getVooCompletoManipulacao();
		setVooCompleto(vooCompleto);
		return "vooDetalhes";
	}

	public DataModel getDetalhesVoo() throws ReservasDAOException {
		VooDAO vooDao = new VooDAOImpl();
		model = new ListDataModel(vooDao.listarEscalas(this.vooCompleto
				.getCodigo()));
		return model;
	}

	public DataModel getTodasReservas() throws ReservasDAOException {
		ReservaDAO reservaDao = new ReservaDAOImpl();
		model = new ListDataModel(reservaDao.listar(cliente.getEmail()));
		return model;
	}

	public String editarReserva() throws ReservasDAOException {
		getReservaManipulacao();
		ReservaDAO interfaceReservaDao = new ReservaDAOImpl();
		Reserva aux = new Reserva();
		aux = interfaceReservaDao.procurar(reserva.getCodigo());
		// aux.
		// setReserva(reserva);
		// this.reserva.setCompanhia(aux.getCompanhia())
		PassageiroDAO interfaceDao = new PassageiroDAOImpl();
		this.passageiros = (ArrayList<Passageiro>) interfaceDao
				.listarPassDaReserva(this.cliente.getEmail(),
						this.reserva.getCodigo());
		return "editaReserva";
	}

	public String atualizarReserva() throws ReservasDAOException {
		PassageiroDAO interfaceDao = new PassageiroDAOImpl();
		interfaceDao.atualizarLista(this.passageiros, this.reserva.getCodigo());
		return "minhasReservas";
	}

	public String excluirReserva() throws ReservasDAOException {
		getReservaManipulacao();
		ReservaDAO reservaDao = new ReservaDAOImpl();
		reservaDao.excluir(this.reserva.getCodigo());
		return "minhasReservas";
	}

}
