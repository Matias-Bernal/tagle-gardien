/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente.MenuPrincipal;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.Buscador.MediadorBuscador;
import cliente.GestionarProveedor.MediadorProveedor;
import cliente.GestionarReclamo.MediadorReclamo;
import cliente.GestionarSolicitante.MediadorSolicitante;
import cliente.GestionarSolicitud.MediadorSolicitud;
import cliente.GestionarUsuario.MediadorUsuario;
import cliente.Login.GUILogin;
import comun.RootAndIp;
import comun.DTOs.SolicitudDTO;
import comun.DTOs.UsuarioRepuestoDTO;
import comun.GestionarUsuarioRepuesto.IControlUsuarioRepuesto;

public class MediadorPrincipal{
	
	protected GUIMenu_Principal gui_menu_Principal;
	protected GUILogin gui_login; 
	protected UsuarioRepuestoDTO usuario_repuesto;
	protected MediadorUsuario mediadorUsuario;
	protected MediadorSolicitante mediadorSolicitante;
	protected MediadorProveedor mediadorProveedor;
	protected MediadorSolicitud mediadorSolicitud;
	protected MediadorReclamo mediadorReclamo;
	protected MediadorBuscador mediadorBuscador;
	
	public MediadorPrincipal() throws Exception{
		gui_login = new GUILogin(this);
		gui_login.setVisible(true);
	}
	
	public boolean acceso(String usuario, String contrasenia) throws Exception {
		boolean result = false;
		try{
			IControlUsuarioRepuesto iControlUsuarioRepuesto = MediadorAccionesIniciarPrograma.getControlUsuariosRepuesto();
			if (iControlUsuarioRepuesto.login(usuario, contrasenia)){
				UsuarioRepuestoDTO usuarioRepuestoDTO = iControlUsuarioRepuesto.buscarUsuario(usuario);
				setUsuario_repuesto(usuarioRepuestoDTO);
				gui_menu_Principal = new GUIMenu_Principal(this);
				gui_menu_Principal.setVisible(true);
				result = true;
			}else{
				JOptionPane.showMessageDialog(gui_login,"Usuario o contraseña invalidos.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(gui_login,"Error de conexion.","Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(0);
		}
		return result;
	}
	
	public void reiniciar(){
		gui_menu_Principal.dispose();
		gui_login = new GUILogin(this);
		gui_login.setVisible(true);
	}

	public void salir() {
		gui_menu_Principal.dispose();
		gui_login.dispose();
		System.exit(0);
	}

	public UsuarioRepuestoDTO getUsuario_repuesto() {
		return usuario_repuesto;
	}

	public void setUsuario_repuesto(UsuarioRepuestoDTO usuario_repuesto) {
		this.usuario_repuesto = usuario_repuesto;
	}
	// Usuario //
	public void altaUsuario(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.altaUsuario();
	}
	public void gestionarUsuario(){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.gestioUsuario();
	}
	public void altaUsuario(String nombre_usuario, String email, String tipo){
		mediadorUsuario = new MediadorUsuario(this);
		mediadorUsuario.altaUsuario(nombre_usuario, email, tipo);
	}
	// Solicitante //
	public void altaSolicitante(){
		mediadorSolicitante = new MediadorSolicitante(this);
		mediadorSolicitante.altaSolicitante();
	}
	public void gestionarSolicitante(){
		mediadorSolicitante = new MediadorSolicitante(this);
		mediadorSolicitante.gestionSolicitante();
	}
	// Proveedor //
	
	public void altaProveedor(){
		mediadorProveedor = new MediadorProveedor(this);
		mediadorProveedor.altaProveedor();
	}
	public void gestionarProveedor(){
		mediadorProveedor = new MediadorProveedor(this);
		mediadorProveedor.gestionProveedor();
	}
	
	// Solicitud //
	public void altaSolicitud(){
		mediadorSolicitud = new MediadorSolicitud(this);
		mediadorSolicitud.altaSolicitud();
	}
	public void gestionarSolicitud(){
		mediadorSolicitud = new MediadorSolicitud(this);
		mediadorSolicitud.gestionarSolicitud();
	}
	
	// Reclamo //
	public void altaReclamo(){
		mediadorReclamo = new MediadorReclamo(this);
		mediadorReclamo.altaReclamo();
	}
	
	public void altaReclamo(SolicitudDTO solicitud){
		mediadorReclamo = new MediadorReclamo(this);
		mediadorReclamo.altaReclamo(solicitud);
	}
	public void gestionarReclamo(){
		mediadorReclamo = new MediadorReclamo(this);
		mediadorReclamo.gestionarReclamo();
	}
	
	// Buscador //
	
	public void inicarBuscador(){
		mediadorBuscador = new MediadorBuscador(this);
		mediadorBuscador.buscador();
	}
	// Ayuda //
	public void ayuda(){
		File manual = new File(RootAndIp.getPath_manual()+"ManualRepuestos.pdf");
		if(manual.exists()){
			try {
				Desktop.getDesktop().open(manual);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,"Error al cargar el manual.","Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null,"No hay manual actualizar.","Error",JOptionPane.INFORMATION_MESSAGE);

		}
	}

}
