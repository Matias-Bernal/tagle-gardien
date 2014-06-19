package cliente.GestionarSolicitud;

import cliente.MenuPrincipal.MediadorPrincipal;

public class MediadorSolicitud {
	
	protected GUIAltaSolicitud guiAltaSolicitud;
	protected GUIGestionSolicitud guiGestionSolicitud;
	protected GUIModificarSolicitud guiModificarSolicitud;
	protected MediadorPrincipal mediadorPrincipal;

	public MediadorSolicitud (MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaSolicitud(){
		guiAltaSolicitud = new GUIAltaSolicitud(this);
		guiAltaSolicitud.setVisible(true);
	}

	public void gestionarSolicitud() {
		guiGestionSolicitud = new GUIGestionSolicitud(this);
		guiGestionSolicitud.setVisible(true);
		
	}
	
}
