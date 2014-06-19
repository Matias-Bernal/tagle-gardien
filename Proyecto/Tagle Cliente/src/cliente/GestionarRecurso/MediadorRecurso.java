package cliente.GestionarRecurso;

import java.sql.Date;
import java.util.Vector;

import common.DTOs.OrdenDTO;
import common.DTOs.RecursoDTO;
import common.GestionarRecurso.IControlRecurso;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

public class MediadorRecurso {

	private MediadorPrincipal mediadorPrincipal;
	
	private GUIAltaRecurso guiAltaRecurso;
	private GUIGestionRecurso guiGestionRecurso;
	private GUIModificarRecurso guiModificarRecurso;
	
	
	public MediadorRecurso(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaRecurso() {
		guiAltaRecurso = new GUIAltaRecurso(this);
		guiAltaRecurso.setVisible(true);
	}

	public void gestionRecurso() {
		guiGestionRecurso = new GUIGestionRecurso(this);
		guiGestionRecurso.setVisible(true);
	}

	public void altaRecurso(String numero_recurso, Date fechaRecurso) {
		guiAltaRecurso = new GUIAltaRecurso(this,fechaRecurso,numero_recurso);
		guiAltaRecurso.setVisible(true);
	}

	public void actualizarDatosGestion() {
		if (guiGestionRecurso!=null)
			guiGestionRecurso.actualizarDatos();
	}

	public boolean nuevoRecuroso(String numero_recurso, Date fechaRecurso) {
		boolean res = false;
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			RecursoDTO recurso = new RecursoDTO();
			recurso.setFecha_recurso(fechaRecurso);
			recurso.setNumero_recurso(numero_recurso);
			
			iControlRecurso.agregarRecurso(recurso);
			res = true;
		} catch (Exception e) {
			System.out.println("Error al agregar recurso");
			e.printStackTrace();
		}
		return res;
	}

	public Vector<RecursoDTO> obtenerRecursos() {
		Vector<RecursoDTO> recursos = new Vector<RecursoDTO>();
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			recursos = iControlRecurso.obtenerRecursos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recursos;
	}

	public void modificarRecurso(Long id) {
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			RecursoDTO recurso = iControlRecurso.buscarRecurso(id);
			guiModificarRecurso = new GUIModificarRecurso(this,recurso);
			guiModificarRecurso.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean eliminarRecurso(Long id) {
		boolean res = false;
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			iControlRecurso.eliminarRecurso(id);
			res= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean modificarRecuroso(RecursoDTO recurso) {
		boolean res = false;
		try {
			IControlRecurso iControlRecurso = MediadorAccionesIniciarPrograma.getControlRecurso();
			iControlRecurso.modificarRecurso(recurso.getId(),recurso);
			res= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
