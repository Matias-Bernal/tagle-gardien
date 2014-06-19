package cliente.GestionarReclamo;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.DTOs.VehiculoDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarOrden.IControlOrden;
import common.GestionarReclamante.IControlReclamante;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarRegistrante.IControlRegistrante;
import common.GestionarVehiculo.IControlVehiculo;

public class MediadorReclamo {
	
	private GUIAltaReclamoEntidad guialtaReclamoEntidad;
	private GUIAltaReclamoAgente guialtaReclamoAgente;
	
	private GUIBuscarVehiculo guiBuscarVehiculo;
	private GUIBuscarReclamante guiBuscarReclamante;
	private GUIBuscarAgente guiBuscarAgente;
	private GUIBuscarEntidad guiBuscarEntidad;
	private GUIBuscarOrden guiBuscarOrden;
	
	private GUIGestionReclamoAgente guiGestionReclamoAgente;
	private GUIGestionReclamoEntidad guiGestionReclamoEntidad;
	
	private MediadorPrincipal mediadorPrincipal;

	public MediadorReclamo(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaReclamoAgente() {
		guialtaReclamoAgente = new GUIAltaReclamoAgente(this);
		guialtaReclamoAgente.setVisible(true);
	}
	
	public void altaReclamoEntidad() {
		guialtaReclamoEntidad = new GUIAltaReclamoEntidad(this);
		guialtaReclamoEntidad.setVisible(true);
	}

	public void gestionReclamoAgente() {
		guiGestionReclamoAgente = new GUIGestionReclamoAgente(this);
		guiGestionReclamoAgente.setVisible(true);
	}
	
	public void gestionReclamoEntidad() {
		guiGestionReclamoEntidad = new GUIGestionReclamoEntidad(this);
		guiGestionReclamoEntidad.setVisible(true);
	}
	
	public void setVehiculo(String id_vehiculo){
		if (guialtaReclamoAgente!=null){
			try {
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				guialtaReclamoAgente.setVehiculo(iControlVehiculo.buscarVehiculo(new Long(id_vehiculo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guialtaReclamoEntidad!=null){
			try {
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				guialtaReclamoEntidad.setVehiculo(iControlVehiculo.buscarVehiculo(new Long(id_vehiculo)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setReclamante(String id_reclamante) {
		if (guialtaReclamoAgente!=null){
			try {
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				guialtaReclamoAgente.setReclamante(iControlReclamante.buscarReclamante(new Long(id_reclamante)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guialtaReclamoEntidad!=null){
			try {
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				guialtaReclamoEntidad.setReclamante(iControlReclamante.buscarReclamante(new Long(id_reclamante)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	public void setAgente(String id_agente) {
		if (guialtaReclamoAgente!=null){
			try {
				IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
				guialtaReclamoAgente.setAgente(iControlAgente.buscarAgente(new Long(id_agente)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setEntidad(String id_entidad) {
		if (guialtaReclamoEntidad!=null){
			try {
				IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
				guialtaReclamoEntidad.setEntidad(iControlEntidad.buscarEntidad(new Long(id_entidad)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	public void setOrden(String id_orden){
		if (guialtaReclamoAgente!=null){
			try {
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				guialtaReclamoAgente.setOrden(iControlOrden.buscarOrden(new Long(id_orden)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (guialtaReclamoEntidad!=null){
			try {
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
				guialtaReclamoEntidad.setOrden(iControlOrden.buscarOrden(new Long(id_orden)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void buscarVehiculo() {
		guiBuscarVehiculo = new GUIBuscarVehiculo(this);
		guiBuscarVehiculo.setVisible(true);
	}
	public void buscarReclamante() {
		guiBuscarReclamante = new GUIBuscarReclamante(this);
		guiBuscarReclamante.setVisible(true);
	}
	public void buscarAgente() {
		guiBuscarAgente = new GUIBuscarAgente(this);
		guiBuscarAgente.setVisible(true);
	}
	public void buscarEntidad() {
		guiBuscarEntidad = new GUIBuscarEntidad(this);
		guiBuscarEntidad.setVisible(true);
		
	}
	public void buscarOrden() {
		guiBuscarOrden = new GUIBuscarOrden(this);
		guiBuscarOrden.setVisible(true);	
	}
	
	public Vector<VehiculoDTO> obtenerVehiculos() {
		IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
		Vector<VehiculoDTO> vehiculos = new Vector<VehiculoDTO> ();
		try {
			vehiculos = iControlVehiculo.obtenerVehiculos();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return vehiculos;
	}
	public Vector<ReclamanteDTO> obtenerReclamantes() {
		IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
		Vector<ReclamanteDTO> reclamantes = new Vector<ReclamanteDTO> ();
		try {
			reclamantes = iControlReclamante.obtenerReclamantes();
		} catch (Exception e) {
			System.out.println("Error al cargar los reclamantes en la clase MediadorReclamantes");
			e.printStackTrace();
		}
		return reclamantes;
	}
	public Vector<AgenteDTO> obtenerAgentes() {
		IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
		Vector<AgenteDTO> agentes = new Vector<AgenteDTO> ();
		try {
			agentes = iControlAgente.obtenerAgentes();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return agentes;
	}
	public Vector<EntidadDTO> obtenerEntidades() {
		IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
		Vector<EntidadDTO> entidades = new Vector<EntidadDTO> ();
		try {
			entidades = iControlEntidad.obtenerEntidad();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return entidades;
	}
	public Vector<OrdenDTO> obtenerOrdenes(){
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		Vector<OrdenDTO> ordenes = new Vector<OrdenDTO> ();
		try {
			ordenes = iControlOrden.obtenerOrdenes();
		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return ordenes;
	}
	
	public boolean nuevoReclamoAgente(java.sql.Date fecha_reclamo, Long id_agente, Long id_reclamante,	Long id_vehiculo, Long id_orden, String descripcion) {
		boolean res = false;
		try{
			IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
			IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
			IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
			IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
			IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();

			ReclamoDTO reclamo = new ReclamoDTO();
			reclamo.setFecha_reclamo(fecha_reclamo);
			reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
			
			RegistranteDTO registrante = iControlRegistrante.buscarRegistranteDTO(id_agente);
			reclamo.setRegistrante(registrante);
			
			ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id_reclamante);
			reclamo.setReclamante(reclamante);
			
			VehiculoDTO vehiculo = iControlVehiculo.buscarVehiculo(id_vehiculo);
			reclamo.setVehiculo(vehiculo);
			
			OrdenDTO orden = iControlOrden.buscarOrden(id_orden);
			orden.setEstado("ABIERTA/SIN RECURSO");
			reclamo.setOrden(orden);
			
			reclamo.setdescripcion(descripcion);
			
			reclamo.setUsuario(mediadorPrincipal.getUsuario());
			
			iControlReclamo.agregarReclamo(reclamo);
			res = true;

		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return res;
	}
	public boolean nuevoReclamoEntidad(java.sql.Date fecha_reclamo, Long id_entidad, Long id_reclamante, Long id_vehiculo, Long id_orden, boolean peligroso, boolean inmovilizado, String descripcion) {
		boolean res = false;
			try{
				IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
				IControlRegistrante iControlRegistrante = MediadorAccionesIniciarPrograma.getControlRegistrante();
				IControlReclamante iControlReclamante = MediadorAccionesIniciarPrograma.getControlReclamante();
				IControlVehiculo iControlVehiculo = MediadorAccionesIniciarPrograma.getControlVehiculo();
				IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();

				ReclamoDTO reclamo = new ReclamoDTO();
				reclamo.setFecha_reclamo(fecha_reclamo);
				reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO/SIN TURNO");
				
				RegistranteDTO registrante = iControlRegistrante.buscarRegistranteDTO(id_entidad);
				reclamo.setRegistrante(registrante);
				
				ReclamanteDTO reclamante = iControlReclamante.buscarReclamante(id_reclamante);
				reclamo.setReclamante(reclamante);
				
				VehiculoDTO vehiculo = iControlVehiculo.buscarVehiculo(id_vehiculo);
				reclamo.setVehiculo(vehiculo);
				
				reclamo.setInmovilizado(inmovilizado);
				reclamo.setPeligroso(peligroso);
				
				OrdenDTO orden = iControlOrden.buscarOrden(id_orden);
				orden.setEstado("ABIERTA/SIN RECURSO");
				reclamo.setOrden(orden);
							
				reclamo.setdescripcion(descripcion);
				
				reclamo.setUsuario(mediadorPrincipal.getUsuario());
				
				iControlReclamo.agregarReclamo(reclamo);
				res = true;

		} catch (Exception e) {
			System.out.println("Error al cargar los registrantess en la clase MediadorRegistrantes");
			e.printStackTrace();
		}
		return res;
	}

	public void actualizarDatosGestion() {
		if (guiGestionReclamoEntidad!=null)
			guiGestionReclamoEntidad.actualizarDatos();
		if (guiGestionReclamoAgente!=null)
			guiGestionReclamoAgente.actualizarDatos();
	}

}
