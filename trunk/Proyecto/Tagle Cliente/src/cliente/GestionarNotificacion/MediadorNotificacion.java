package cliente.GestionarNotificacion;

import common.DTOs.NotificacionDTO;
import common.DTOs.UsuarioDTO;
import cliente.MediadorPrincipal;

public class MediadorNotificacion {

	private MediadorPrincipal mediador;
	private GUIGestionNotificacion guiGestionNotificacion;
	
	public MediadorNotificacion(MediadorPrincipal mediadorPrincipal) {
		this.mediador = mediadorPrincipal;
	}

	public void gestionNotificacion() {
		guiGestionNotificacion = new GUIGestionNotificacion(this, mediador.getUsuario());
		guiGestionNotificacion.setVisible(true);	
	}

	public void actualizarNotificaciones() {
		// TODO Auto-generated method stub
		
	}

	public void notificacionCompletada(NotificacionDTO notificacionDTO) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean esAdministrativo (UsuarioDTO usuario){
		return usuario.getTipo().equals("Administrativo");
	}

	public boolean setTiposNotificaciones(boolean turnos, boolean contencion, boolean reclamos, boolean sugerencias) {
		mediador.setTiposNotificaciones(turnos,contencion,reclamos,sugerencias);
		return true;
	}

}
