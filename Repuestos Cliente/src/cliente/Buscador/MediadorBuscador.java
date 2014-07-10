package cliente.Buscador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import comun.DTOs.SolicitudDTO;
import comun.GestionarSolicitud.IControlSolicitud;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MenuPrincipal.MediadorPrincipal;

public class MediadorBuscador {
	
	protected GUIBuscador guiBuscador;
	protected MediadorPrincipal mediadorPrincipal;
	
	public MediadorBuscador(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void buscador(){
		guiBuscador = new GUIBuscador(this);
		guiBuscador.setVisible(true);
	}

	public Vector<SolicitudDTO> obtenerSolicitudes() {
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		Vector<SolicitudDTO> solicitudes = new Vector<SolicitudDTO> ();
		try {
			solicitudes = iControlSolicitud.obtenerSolicitudes("estado.equals(\"ABIERTA\")");
		} catch (Exception e) {
			System.out.println("Error al cargar las solicitudes");
			e.printStackTrace();
		}
		return solicitudes;
	}

	public SolicitudDTO buscarSolicitud(Long id) {
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		SolicitudDTO solicitud = null;
		try {
			solicitud = iControlSolicitud.buscarSolicitud(id);
		} catch (Exception e) {
			System.out.println("Error al cargar la solicitud");
			e.printStackTrace();
		}
		return solicitud;
	}

	@SuppressWarnings("deprecation")
	public boolean cambiarFechaEstimadaLlegada(Long id, java.sql.Date fELlegada) {
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		SolicitudDTO solicitud = null;
		try {
			solicitud = iControlSolicitud.buscarSolicitud(id);
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fELlegada.getTime());; //
					
			if(fELlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				fELlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(fELlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				fELlegada = new java.sql.Date(feLlegada_.getTime());
			}
			solicitud.setFecha_recepcion_estimada(fELlegada);
			iControlSolicitud.modificarSolicitud(id, solicitud);
			return true;
		} catch (Exception e) {
			System.out.println("Error al modificar fecha estimada llegada la solicitud");
			e.printStackTrace();
			return false;
		}	
	}

	public boolean cambiarFechaLlegada(Long id, java.sql.Date fLlegada) {
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		SolicitudDTO solicitud = null;
		try {
			solicitud = iControlSolicitud.buscarSolicitud(id);
			solicitud.setFecha_recepcion_proveedor(fLlegada);
			solicitud.setEstado("CERRADA");
			iControlSolicitud.modificarSolicitud(id, solicitud);
			return true;
		} catch (Exception e) {
			System.out.println("Error al modificar fecha llegada la solicitud");
			e.printStackTrace();
			return false;
		}	
	}

	public void reclamo(Long id) {
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		SolicitudDTO solicitud = null;
		try {
			solicitud = iControlSolicitud.buscarSolicitud(id);
			mediadorPrincipal.altaReclamo(solicitud);			
		} catch (Exception e) {
			System.out.println("Error al cargar reclamo de la solicitud");
			e.printStackTrace();
		}
	}
}