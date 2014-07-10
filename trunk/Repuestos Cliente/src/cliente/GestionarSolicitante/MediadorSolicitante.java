package cliente.GestionarSolicitante;

import java.util.Vector;

import comun.DTOs.CarroceriaDTO;
import comun.DTOs.GarantiaDTO;
import comun.DTOs.MayoristaDTO;
import comun.DTOs.MecanicoDTO;
import comun.DTOs.MostradorDTO;
import comun.DTOs.SeguroDTO;
import comun.DTOs.SolicitanteDTO;
import comun.GestionarCarroceria.IControlCarroceria;
import comun.GestionarGarantia.IControlGarantia;
import comun.GestionarMayorista.IControlMayorista;
import comun.GestionarMecanico.IControlMecanico;
import comun.GestionarMostrador.IControlMostrador;
import comun.GestionarSeguro.IControlSeguro;
import comun.GestionarSolicitante.IControlSolicitante;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MenuPrincipal.MediadorPrincipal;

public class MediadorSolicitante {

	protected GUIAltaSolicitante guiAltaSolicitante;
	protected GUIModificarSolicitante guiModificarSolicitante;
	protected GUIGestionSolicitante guiGestionSolicitante;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorSolicitante(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaSolicitante(){
		guiAltaSolicitante = new GUIAltaSolicitante(this);
		guiAltaSolicitante.setVisible(true);
	}
	
	public void gestionSolicitante(){
		guiGestionSolicitante = new GUIGestionSolicitante(this);
		guiGestionSolicitante.setVisible(true);
	}

	public boolean agregarMostrador(String nombre, String telefono, String email) {
		IControlMostrador iControlMostrador = MediadorAccionesIniciarPrograma.getControlMostrador();
		MostradorDTO mostradorDTO = new MostradorDTO(null, nombre, telefono, email);
		try {
			return (iControlMostrador.agregarMostrador(mostradorDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Solicitante");
			e.printStackTrace();
			return false;
		}
	}

	public boolean agregarMayorista(String nombre) {
		IControlMayorista iControlMayorista = MediadorAccionesIniciarPrograma.getControlMayorista();
		MayoristaDTO mayoristaDTO = new MayoristaDTO(null, nombre);
		try {
			return (iControlMayorista.agregarMayorista(mayoristaDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Solicitante");
			e.printStackTrace();
			return false;
		}
	}

	public boolean agregarGarantia(String nombre) {
		IControlGarantia iControlGarantia = MediadorAccionesIniciarPrograma.getControlGarantia();
		GarantiaDTO garantiaDTO = new GarantiaDTO(null, nombre);
		try {
			return (iControlGarantia.agregarGarantia(garantiaDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Solicitante");
			e.printStackTrace();
			return false;
		}
	}

	public boolean agregarSeguro(String nombre) {
		IControlSeguro iControlSeguro = MediadorAccionesIniciarPrograma.getControlSeguro();
		SeguroDTO seguroDTO = new SeguroDTO(null, nombre);
		try {
			return (iControlSeguro.agregarSeguro(seguroDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Solicitante");
			e.printStackTrace();
			return false;
		}
	}

	public boolean agregarTallerMecanico(String nombre) {
		IControlMecanico iControlMecanico = MediadorAccionesIniciarPrograma.getControlMecanico();
		MecanicoDTO mecanicoDTO = new MecanicoDTO(null, nombre);
		try {
			return (iControlMecanico.agregarMecanico(mecanicoDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Solicitante");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean agregarTallerCarroceria(String nombre) {
		IControlCarroceria iControlCarroceria = MediadorAccionesIniciarPrograma.getControlCarroceria();
		CarroceriaDTO carroceriaDTO = new CarroceriaDTO(null, nombre);
		try {
			return (iControlCarroceria.agregarCarroceria(carroceriaDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Solicitante");
			e.printStackTrace();
			return false;
		}
	}

	public void modificarSolicitante(Long id) {
		IControlMostrador iControlMostrador = MediadorAccionesIniciarPrograma.getControlMostrador();
		IControlMayorista iControlMayorista = MediadorAccionesIniciarPrograma.getControlMayorista();
		IControlGarantia iControlGarantia = MediadorAccionesIniciarPrograma.getControlGarantia();
		IControlSeguro iControlSeguro = MediadorAccionesIniciarPrograma.getControlSeguro();
		IControlMecanico iControlMecanico = MediadorAccionesIniciarPrograma.getControlMecanico();
		IControlCarroceria iControlCarroceria = MediadorAccionesIniciarPrograma.getControlCarroceria();
		try {
			SolicitanteDTO solictante = null;
			
			if(iControlMostrador.existeMostrador(id))
				solictante = iControlMostrador.buscarMostrador(id);
			if(iControlMayorista.existeMayorista(id))
				solictante = iControlMayorista.buscarMayorista(id);
			if(iControlGarantia.existeGarantia(id))
				solictante = iControlGarantia.buscarGarantia(id);
			if(iControlSeguro.existeSeguro(id))
				solictante = iControlSeguro.buscarSeguro(id);
			if(iControlMecanico.existeMecanico(id))
				solictante = iControlMecanico.buscarMecanico(id);
			if(iControlCarroceria.existeCarroceria(id))
				solictante = iControlCarroceria.buscarCarroceria(id);
			
			guiModificarSolicitante = new GUIModificarSolicitante(this, solictante); 
			guiModificarSolicitante.setVisible(true);
		} catch (Exception e) {
			System.out.println("Error al modificar solicitante en la clase MediadorUsuario");
			e.printStackTrace();
		}
		
	}

	public boolean eliminarSolicitante(Long id) {
		IControlSolicitante iControlSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		try {
			iControlSolicitante.eliminarSolicitante(id);
			return true;
		} catch (Exception e) {
			System.out.println("Error al eliminar Solicitante");
			e.printStackTrace();
			return false;
		}
	}

	public Vector<SolicitanteDTO> obtenerSolicitates() {
		IControlSolicitante iControlSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		Vector<SolicitanteDTO> solicitantes = new Vector<SolicitanteDTO>();
		try {
			solicitantes = iControlSolicitante.obtenerSolicitantes();
			return solicitantes;
		} catch (Exception e) {
			System.out.println("Error al eliminar Solicitante");
			e.printStackTrace();
			return solicitantes;
		}
	}

	public SolicitanteDTO tipoSolicitante(Long id) {
		IControlMostrador iControlMostrador = MediadorAccionesIniciarPrograma.getControlMostrador();
		IControlMayorista iControlMayorista = MediadorAccionesIniciarPrograma.getControlMayorista();
		IControlGarantia iControlGarantia = MediadorAccionesIniciarPrograma.getControlGarantia();
		IControlSeguro iControlSeguro = MediadorAccionesIniciarPrograma.getControlSeguro();
		IControlMecanico iControlMecanico = MediadorAccionesIniciarPrograma.getControlMecanico();
		IControlCarroceria iControlCarroceria = MediadorAccionesIniciarPrograma.getControlCarroceria();
		
		try {
			
			SolicitanteDTO solictante = null;
			if(iControlMostrador.existeMostrador(id)){
				solictante = iControlMostrador.buscarMostrador(id);
				return solictante;
			}
			if(iControlMayorista.existeMayorista(id)){
				solictante = iControlMayorista.buscarMayorista(id);
				return solictante;
			}
			if(iControlGarantia.existeGarantia(id)){
				solictante = iControlGarantia.buscarGarantia(id);
				return solictante;
			}
			if(iControlSeguro.existeSeguro(id)){
				solictante = iControlSeguro.buscarSeguro(id);
				return solictante;
			}
			if(iControlMecanico.existeMecanico(id)){
				solictante = iControlMecanico.buscarMecanico(id);
				return solictante;
			}
			if(iControlCarroceria.existeCarroceria(id)){
				solictante = iControlCarroceria.buscarCarroceria(id);
				return solictante;
			}
			return solictante;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean modificarMostrador(Long id, String nombre, String telefono,	String email) {
		IControlMostrador iControlMostrador = MediadorAccionesIniciarPrograma.getControlMostrador();
		try {
			MostradorDTO mostrador = iControlMostrador.buscarMostrador(id);
			mostrador.setNombre(nombre);
			mostrador.setTelefono(telefono);
			mostrador.setMail(email);
			iControlMostrador.modificarMostrador(id, mostrador);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean modificarMayorista(Long id, String nombre) {
		IControlMayorista iControlMayorista = MediadorAccionesIniciarPrograma.getControlMayorista();
		try {
			MayoristaDTO mayorista = iControlMayorista.buscarMayorista(id);
			mayorista.setNombre(nombre);
			iControlMayorista.modificarMayorista(id, mayorista);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean modificarGarantia(Long id, String nombre) {
		IControlGarantia iControlGarantia = MediadorAccionesIniciarPrograma.getControlGarantia();
		try {
			GarantiaDTO garantia = iControlGarantia.buscarGarantia(id);
			garantia.setNombre(nombre);
			iControlGarantia.modificarGarantia(id, garantia);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean modificarSeguro(Long id, String nombre) {
		IControlSeguro iControlSeguro = MediadorAccionesIniciarPrograma.getControlSeguro();
		try {
			SeguroDTO seguro = iControlSeguro.buscarSeguro(id);
			seguro.setNombre(nombre);
			iControlSeguro.modificarSeguro(id, seguro);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean modificarTallerMecanico(Long id, String nombre) {
		IControlMecanico iControlMecanico = MediadorAccionesIniciarPrograma.getControlMecanico();
		try {
			MecanicoDTO mecanico = iControlMecanico.buscarMecanico(id);
			mecanico.setNombre(nombre);
			iControlMecanico.modificarMecanico(id, mecanico);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean modificarTallerCarroceria(Long id, String nombre) {
		IControlCarroceria iControlCarroceria = MediadorAccionesIniciarPrograma.getControlCarroceria();
		try {
			CarroceriaDTO carroceria = iControlCarroceria.buscarCarroceria(id);
			carroceria.setNombre(nombre);
			iControlCarroceria.modificarCarroceria(id, carroceria);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void actualizarDatosGestion() {
		if (guiGestionSolicitante!= null)
			guiGestionSolicitante.actualizarDatos();
		
	}
}
