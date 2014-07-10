package cliente.GestionarReclamo;

import java.util.Vector;

import comun.DTOs.ReclamoDTO;
import comun.DTOs.SolicitudDTO;
import comun.GestionarReclamo.IControlReclamo;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MenuPrincipal.MediadorPrincipal;

public class MediadorReclamo {

	protected GUINuevoReclamo guiAltaReclamo;
	protected GUIModificarReclamo guiModificarReclamo;
	protected GUIGestionReclamo guiGestionReclamo;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorReclamo(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaReclamo(SolicitudDTO solicitud){
		guiAltaReclamo = new GUINuevoReclamo(this,solicitud);
		guiAltaReclamo.setVisible(true);
	}
	
	public void altaReclamo() {
		// TODO Auto-generated method stub
		
	}
	
	public void gestionarReclamo(){
		guiGestionReclamo = new GUIGestionReclamo(this);
		guiGestionReclamo.setVisible(true);
	}

	public Vector<SolicitudDTO> obtenerReclamos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerCargos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerFabrica() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerSucursal() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerAlternativo() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerMostrador() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerMayorista() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerSeguro() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerGarantia() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerMecanico() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector<String> obtenerCarroceria() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean nuevoReclamo(SolicitudDTO solicitud, java.sql.Date fReclamo,	String descripcion) {
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		ReclamoDTO reclamo = new ReclamoDTO(null, fReclamo, descripcion, solicitud, mediadorPrincipal.getUsuario_repuesto());
		try{
			return (iControlReclamo.agregarReclamo(reclamo)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar reclamo");
			e.printStackTrace();
			return false;
		}
	}



}
