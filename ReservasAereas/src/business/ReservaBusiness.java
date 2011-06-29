package business;

import java.util.ArrayList;
import java.util.List;

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
	private List<Passageiro> passageiros = new ArrayList();
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

	public List<Passageiro> getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(List<Passageiro> passageiros) {
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
	
	public String autenticaCliente() throws ReservasDAOException {
        ClienteDAO interfaceCliente = new ClienteDAOImpl();
        String retorno;
        Cliente cli = interfaceCliente.procurar(this.cliente.getEmail().trim());
        if (cli != null && cli.getSenha().equals(this.cliente.getSenha().trim())) {
            setCliente(cli);
            setLogado(true);
            retorno = "dashboard";
        } else {
            FacesContext contexto = FacesContext.getCurrentInstance();
            FacesMessage msg = new FacesMessage("Usu‡rio ou senha inv‡lido(s).");
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
		for (int i=0; i<passageiros.size(); i++) {
			
		}
		return "minhasReservas";
	}
	
	public DataModel getVoosDisp() throws ReservasDAOException {
		VooDAO vooDao = new VooDAOImpl();
        model = new ListDataModel(vooDao.listarVoosDisp(this.reserva.getCodAerpIda(), this.reserva.getCodAerpVolta()));
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
		
		for(Aeroporto a : aeroportos) {
			cidades.add(new SelectItem(a.getNumero(), a.getCidade()));
		}
		
		return cidades;
	}
	
	public String reload(){
		return "reserva";
	}
	
	public List<SelectItem> getDestinobyOrigem() throws ReservasDAOException {
		AeroportoDAO destino = new AeroportoDAOImpl();
		List<Aeroporto> aeroporto = destino.listarCidadesDestino(this.reserva.getCodAerpIda());
		List<SelectItem> cidadesDestino = new ArrayList<SelectItem>(aeroporto.size());
		for(Aeroporto a : aeroporto) {
			cidadesDestino.add(new SelectItem(a.getNumero(), a.getCidade()));
		}
		return cidadesDestino;
	}
	
	public String listarDestinos() throws ReservasDAOException {
		return "reserva";
	}
	
	public String fazerReserva() throws ReservasDAOException {
		for(int i = 0; i < this.reserva.getNumPassageiros(); i++){
			this.passageiros.add(new Passageiro());
		}
	
		return "reserva";
	}
	
	public DataModel getTodasReservas() throws ReservasDAOException {
		ReservaDAO reservaDao = new ReservaDAOImpl();
        model = new ListDataModel(reservaDao.listar(cliente.getEmail()));
        return model;
	}
	
	public void getReservaManipulacao() {
        setReserva((Reserva) model.getRowData());
    }
	
	public String editarReserva() throws ReservasDAOException {
        getReservaManipulacao();
        setReserva(reserva);
        return "editarReserva";
    }

    public String excluirReserva() throws ReservasDAOException {
        getReservaManipulacao();
        ReservaDAO reservaDao = new ReservaDAOImpl();
        reservaDao.excluir(this.reserva.getCodigo());
        return "minhasReservas";
    }

}
