package cliente;

import javax.swing.JOptionPane;

import common.DTOs.NotificacionDTO;
import common.DTOs.UsuarioDTO;
import common.GestionarUsuario.IControlUsuario;
import cliente.GestionOrden.MediadorOrden;
import cliente.GestionarDevoluviones.MediadorDevoluciones;
import cliente.GestionarNotificacion.MediadorNotificacion;
import cliente.GestionarPedido.MediadorPedido;
import cliente.GestionarReclamante.MediadorReclamante;
import cliente.GestionarReclamo.MediadorReclamo;
import cliente.GestionarRecurso.MediadorRecurso;
import cliente.GestionarRegistrante.MediadorRegistrante;
import cliente.GestionarUsuario.MediadorUsuario;
import cliente.GestionarVehiculo.MediadorVehiculo;
import cliente.Reclamo.MediadoReclamos_Fabrica;
import cliente.Reporte.MediadorReporte;
import cliente.Repuesto.MediadorRepuesto;
import cliente.login.GUILogin;


public class MediadorPrincipal{

	protected GUIMenu_Principal gui_menu_Principal;
	protected GUILogin gui_login; 
	protected MediadorUsuario mediadorUsuario;
	protected MediadorRegistrante mediadorRegistrante; 
	protected MediadorReclamante mediadorReclamante;
	protected MediadorVehiculo mediadorVehiculos;
	protected MediadorPedido mediadorPedidos;
	protected MediadorReclamo mediadorReclamos;
	protected MediadorRecurso mediadorRecursos;
	protected MediadorDevoluciones mediadorDevoluciones;
	protected MediadorNotificacion mediadorNotificaciones;
	protected MediadorOrden mediadorOrden;

	
	protected MediadorRepuesto mediadorRepuestos;
	protected MediadoReclamos_Fabrica mediadorReclamo_Fabrica;
	protected MediadorReporte mediadorReporte;
	
	protected UsuarioDTO usuario;
	
	public MediadorPrincipal() throws Exception{
		gui_login = new GUILogin(this);
		gui_login.setVisible(true);
	}

	// Geters y Seters // 
	
	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	
	public boolean acceso(String usuario, String contrasenia) throws Exception {
		boolean result = false;
		try{
			IControlUsuario iControlUsuario = MediadorAccionesIniciarPrograma.getControlUsuarios();
			if (iControlUsuario.login(usuario, contrasenia)){
				setUsuario(iControlUsuario.buscarUsuario(usuario));
				gui_menu_Principal = new GUIMenu_Principal(this);
				gui_menu_Principal.setVisible(true);
				result = true;
			}
		}catch (Exception e){
			JOptionPane.showMessageDialog(gui_login,"Error de conexion.","Error",JOptionPane.ERROR_MESSAGE);
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
	
	// Usuarios //
	
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
	
	// Registrantes //
	public void altaRegistrante(){
		mediadorRegistrante = new MediadorRegistrante(this);
		mediadorRegistrante.altaRegistrante();
	}

	public void gestionarRegistrante(){
		mediadorRegistrante = new MediadorRegistrante(this);
		mediadorRegistrante.gestioRegistrante();
	}
	public void altaRegistrante(String nombre_registrante, String tipo){
		mediadorRegistrante = new MediadorRegistrante(this);
		mediadorRegistrante.altaRegistrante(nombre_registrante, tipo);
	}
	
	// Reclamantes //
	public void altaReclamante(){
		mediadorReclamante = new MediadorReclamante(this);
		mediadorReclamante.altaReclamante();
	}

	public void gestionarReclamante(){
		mediadorReclamante = new MediadorReclamante(this);
		mediadorReclamante.gestionReclamante();
	}
	public void altaReclamante(String nombre_reclamante, String email, String dni, String telefono){
		mediadorReclamante = new MediadorReclamante(this);
		mediadorReclamante.altaReclamante(nombre_reclamante,email, dni, telefono);
	}
	
	// Vehiculo //
	public void altaVehiculo(){
		mediadorVehiculos = new MediadorVehiculo(this);
		mediadorVehiculos.altaVehiculo();
	}

	public void gestionarVehiculo(){
		mediadorVehiculos = new MediadorVehiculo(this);
		mediadorVehiculos.gestionVehiculo();
	}
	public void altaVehiculo(String nombre_titular, String dominio, String vin, String marca, String modelo){
		mediadorVehiculos = new MediadorVehiculo(this);
		mediadorVehiculos.altaVehiculo(nombre_titular,dominio,vin,marca,modelo);
	}
	
	// Pedido //
	public void gestionarPedidoEntidad() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.gestionarPedidoEntidad();
	}

	public void altaPedidoEntidad() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.altaPedidoEntidad();
	}

	public void gestionarPedidoAgente() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.gestionarPedidoAgente();
	}

	public void altaPedidoAgente() {
		mediadorPedidos = new MediadorPedido(this);
		mediadorPedidos.altaPedidoAgente();
	}
	
	// Reclamo //
	public void altaReclamoAgente(){
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.altaReclamoAgente();
	}
	public void altaReclamoEntidad() {
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.altaReclamoEntidad();		
	}

	public void gestionarReclamoAgente(){
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.gestionReclamoAgente();
	}
	
	public void gestionarReclamoEntidad(){
		mediadorReclamos = new MediadorReclamo(this);
		mediadorReclamos.gestionReclamoEntidad();
	}
	
	// Recurso //
	public void altaRecurso(){
		mediadorRecursos = new MediadorRecurso(this);
		mediadorRecursos.altaRecurso();
	}

	public void gestionarRecurso(){
		mediadorRecursos = new MediadorRecurso(this);
		mediadorRecursos.gestionRecurso();
	}
	public void altaRecurso(String nombre_registrante, String tipo){
		mediadorRecursos = new MediadorRecurso(this);
		mediadorRecursos.altaRecurso();
	}
	
	// Devoluciones //
	public void altaDevolucion(){
		mediadorDevoluciones = new MediadorDevoluciones(this);
		mediadorDevoluciones.altaDevolucion();
	}

	public void gestionarDevolucion(){
		mediadorDevoluciones = new MediadorDevoluciones(this);
		mediadorDevoluciones.gestionDevolucion();
	}

	// Notificacion //
	public void gestionarNotificaciones(){
		mediadorNotificaciones = new MediadorNotificacion(this);
		mediadorNotificaciones.gestionNotificacion();
	}
	public void actualizarNotificaciones(){
		mediadorNotificaciones = new MediadorNotificacion(this);
		mediadorNotificaciones.actualizarNotificaciones();
	}
	public void notificacionCompletadad(NotificacionDTO notificacionDTO){
		mediadorNotificaciones = new MediadorNotificacion(this);
		mediadorNotificaciones.notificacionCompletada(notificacionDTO);
	}
	public void posponetNotificacion(NotificacionDTO notificacionDTO){
	}
	
	// Ayuda //
	public void ayuda(){
		
	}



	public void altaOrden() {
		mediadorOrden = new MediadorOrden(this);
		mediadorOrden.altaOrden();
	}

	public void gestionarOrden() {
		mediadorOrden = new MediadorOrden(this);
		mediadorOrden.gestionarOrden();		
	}

	public void reclamos() {

		
	}

	public void repuestos() {
		// TODO Auto-generated method stub
		
	}

	public void reportes() {
		mediadorReporte = new MediadorReporte(this);
		mediadorReporte.mostrarReportes();
		
	}

	public void setTiposNotificaciones(boolean turnos, boolean contencion,
			boolean reclamos, boolean sugerencias) {
		// TODO Auto-generated method stub
		
	}


	
	// RECLAMOS //
	
	// REPUESTOS //
	
	// REPORTES //

}
