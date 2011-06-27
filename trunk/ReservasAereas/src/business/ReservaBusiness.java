package business;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import reserva.Reserva;
import reserva.ReservaDAO;
import reserva.ReservaDAOImpl;
import voo.Voo;
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
	private Voo voo = new Voo();
	private Aeroporto aer = new Aeroporto();
	private Aeroporto aer2 = new Aeroporto();
	private List<SelectItem> aeroportos = new ArrayList<SelectItem>();
	private Escala escala = new Escala();
	private Reserva reserva = new Reserva();
	private boolean logado = false;
	private DataModel model;

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
            FacesMessage msg = new FacesMessage("Usu�rio ou senha inv�lido(s).");
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
        return "login";
    }

	public String cadastrarCliente() throws ReservasDAOException {
        ClienteDAO interfaceCliente = new ClienteDAOImpl();
        interfaceCliente.gravar(cliente);
        setLogado(true);
        return "dashboard";
    }
	
	public DataModel getTodosVoos() throws ReservasDAOException {
		VooDAO vooDao = new VooDAOImpl();
        model = new ListDataModel(vooDao.listar());
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
	
	public List<SelectItem> getDestinobyOrigem() throws ReservasDAOException {
		AeroportoDAO destino = new AeroportoDAOImpl();
		List<Aeroporto> aeroporto = destino.listarCidadesDestino(this.aer.getNumero());
		List<SelectItem> cidades = new ArrayList<SelectItem>(aeroporto.size());
		for(Aeroporto a : aeroporto) {
			cidades.add(new SelectItem(a.getNumero(), a.getCidade()));
		}
		
		return cidades;
	}
	
	public String listarDestinos() throws ReservasDAOException {
		return "voo";
	}
	
	public String fazerReserva() throws ReservasDAOException {
		
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