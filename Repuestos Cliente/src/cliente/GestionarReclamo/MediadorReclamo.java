package cliente.GestionarReclamo;

import cliente.MenuPrincipal.MediadorPrincipal;

public class MediadorReclamo {

	protected GUIAltaReclamo guiAltaReclamo;
	protected GUIModificarReclamo guiModificarReclamo;
	protected GUIGestionReclamo guiGestionReclamo;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorReclamo(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaReclamo(){
		guiAltaReclamo = new GUIAltaReclamo(this);
		guiAltaReclamo.setVisible(true);
	}
//	public void altaUsuario(String nombre_usuario, String email, String Tipo){
//		guiAltaUsuario = new GUIAltaUsuario(this,nombre_usuario,email, Tipo);
//		guiAltaUsuario.setVisible(true);
//	}
	
	public void gestionarReclamo(){
		guiGestionReclamo = new GUIGestionReclamo(this);
		guiGestionReclamo.setVisible(true);
	}
}
