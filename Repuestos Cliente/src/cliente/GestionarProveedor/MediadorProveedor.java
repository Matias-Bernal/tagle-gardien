package cliente.GestionarProveedor;

import cliente.MenuPrincipal.MediadorPrincipal;

public class MediadorProveedor {

	protected GUIAltaProveedor guiAltaProveedor;
	protected GUIModificarProveedor guiModificarProveedor;
	protected GUIGestionProveedor guiGestionProveedor;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorProveedor(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaProveedor(){
		guiAltaProveedor = new GUIAltaProveedor(this);
		guiAltaProveedor.setVisible(true);
	}
//	public void altaUsuario(String nombre_usuario, String email, String Tipo){
//		guiAltaUsuario = new GUIAltaUsuario(this,nombre_usuario,email, Tipo);
//		guiAltaUsuario.setVisible(true);
//	}
	
	public void gestionProveedor(){
		guiGestionProveedor = new GUIGestionProveedor(this);
		guiGestionProveedor.setVisible(true);
	}
}