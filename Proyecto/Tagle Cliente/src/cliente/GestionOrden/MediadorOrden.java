package cliente.GestionOrden;

import java.sql.Date;
import java.util.Vector;

import common.DTOs.OrdenDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RecursoDTO;
import common.GestionarOrden.IControlOrden;
import common.GestionarReclamo.IControlReclamo;
import common.GestionarRecurso.IControlRecurso;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

public class MediadorOrden {
	
	private MediadorPrincipal mediadorPrincipal;
	
	private GUIAltaOrden guiAltaOrden;
	private GUIGestionOrden guigestionOrden;
	private GUIModificarOrden guiModificarOrden;
	
	private GUIBuscarReclamo guiBuscarReclamo;
	private GUIBuscarRecurso guiBuscarRecurso;

	public MediadorOrden(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaOrden(){
		guiAltaOrden = new GUIAltaOrden(this);
		guiAltaOrden.setVisible(true);
	}
	public boolean nuevaOrden(Date fecha_apertura, String numero_orden) {
		boolean res= false;
		try {
			IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
			OrdenDTO orden = new OrdenDTO();
			orden.setNumero_orden(numero_orden);
			orden.setEstado("SIN RECLAMO");
			orden.setFecha_apertura(fecha_apertura);
			iControlOrden.agregarOrden(orden);
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public void gestionarOrden() {
		guigestionOrden = new GUIGestionOrden(this);
		guigestionOrden.setVisible(true);
	}
	public void modificarOrden(Long id) {
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		try {
			OrdenDTO orden = iControlOrden.buscarOrden(id);
			guiModificarOrden = new GUIModificarOrden(this, orden);
			guiModificarOrden.setVisible(true);
			
		} catch (Exception e) {
			System.out.println("Error al modifcar orden");
			e.printStackTrace();
		}
		
	}
	public boolean eliminarOrden(Long id) {
		boolean res = false;
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		try {
			iControlOrden.eliminarOrden(id);
			res = true;
		} catch (Exception e) {
			System.out.println("Error al eliminar orden");
			e.printStackTrace();
		}
		return res;
	}

	public Vector<OrdenDTO> obtenerOrdenes() {
		Vector<OrdenDTO> ordenes = new Vector<OrdenDTO>();
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		try {
			ordenes = iControlOrden.obtenerOrdenes();
		} catch (Exception e) {
			System.out.println("Error al obtener ordenes");
			e.printStackTrace();
		}
		return ordenes;
	}
	public Vector<RecursoDTO> obtenerRecursos() {
		Vector<RecursoDTO> recursos = new Vector<RecursoDTO>();
		IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
		try {
			recursos = iControlRecurso.obtenerRecursos();
		} catch (Exception e) {
			System.out.println("Error al obtener recursos");
			e.printStackTrace();
		}
		return recursos;
	}
	public Vector<ReclamoDTO> obtenerReclamos() {
		Vector<ReclamoDTO> reclamos = new Vector<ReclamoDTO>();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			reclamos = iControlReclamo.obtenerReclamos();
		} catch (Exception e) {
			System.out.println("Error al obtener recursos");
			e.printStackTrace();
		}
		return reclamos;
	}

	public void buscarReclamos() {					
		guiBuscarReclamo = new GUIBuscarReclamo(this);
		guiBuscarReclamo.setVisible(true);
	}
	public void buscarRecursos() {
		guiBuscarRecurso = new GUIBuscarRecurso(this);
		guiBuscarRecurso.setVisible(true);
	}

	public ReclamoDTO obtenerReclamo(Long id) {
		ReclamoDTO reclamoDTO = null;
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			reclamoDTO = iControlReclamo.buscarReclamo(id);
		} catch (Exception e) {
			System.out.println("Error al buscar reclamo");
			e.printStackTrace();
		}	
		return reclamoDTO;
	}
	public RecursoDTO obtenerRecurso(String num_recurso) {
		RecursoDTO recursoDTO = null;
		IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
		try {
			recursoDTO = iControlRecurso.buscarRecurso(num_recurso);
		} catch (Exception e) {
			System.out.println("Error al buscar recurso");
			e.printStackTrace();
		}	
		return recursoDTO;
	}
	
	public void actualizarDatosGestion() {
		if (guigestionOrden!=null)
			guigestionOrden.actualizarDatos();
	}

	public boolean modificarOrden(OrdenDTO orden) {
		IControlOrden iControlOrden = MediadorAccionesIniciarPrograma.getControlOrden();
		boolean res = false;
		try {
			OrdenDTO ordenDTO = iControlOrden.buscarOrden(orden.getId());
			
			ordenDTO.setFecha_apertura(orden.getFecha_apertura());
			if (orden.getFecha_cierre()!=null)
				ordenDTO.setFecha_cierre(orden.getFecha_cierre());
			if (orden.getRecurso()!=null)
				ordenDTO.setRecurso(orden.getRecurso());
			ordenDTO.setEstado(orden.getEstado());
			ordenDTO.setNumero_orden(orden.getNumero_orden());
			
			iControlOrden.modificarOrden(ordenDTO.getId(), ordenDTO);
			res = true;
			
		} catch (Exception e) {
			System.out.println("Error al modifcar orden");
			e.printStackTrace();
		}
		return res;
	}

	public void setRecurso(String id_recurso) {
		IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
		try {
			RecursoDTO recurso = iControlRecurso.buscarRecurso(new Long(id_recurso));
			if(guiModificarOrden!=null){
				guiModificarOrden.setRecurso(recurso);
			}else{
				if (guigestionOrden!=null){
					guigestionOrden.setRecurso(recurso);
				}
			}
		} catch (Exception e) {
			System.out.println("Error al buscar recurso");
			e.printStackTrace();
		}
	}
	public void setReclamo(String id_reclamo) {
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			ReclamoDTO reclamo = iControlReclamo.buscarReclamo(new Long(id_reclamo));
			if (guigestionOrden!=null){
				guigestionOrden.setReclamo(reclamo);
			}
		} catch (Exception e) {
			System.out.println("Error al buscar recurso");
			e.printStackTrace();
		}
	}


}