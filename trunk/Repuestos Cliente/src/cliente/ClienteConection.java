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
package cliente;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import comun.RootAndIp;
import comun.GestionarAlternativo.IControlAlternativo;
import comun.GestionarCargo.IControlCargo;
import comun.GestionarCarroceria.IControlCarroceria;
import comun.GestionarFabrica.IControlFabrica;
import comun.GestionarGarantia.IControlGarantia;
import comun.GestionarMayorista.IControlMayorista;
import comun.GestionarMecanico.IControlMecanico;
import comun.GestionarMostrador.IControlMostrador;
import comun.GestionarPerito.IControlPerito;
import comun.GestionarPeritoSeguro.IControlPeritoSeguro;
import comun.GestionarPieza.IControlPieza;
import comun.GestionarProveedor.IControlProveedor;
import comun.GestionarReclamo.IControlReclamo;
import comun.GestionarSeguro.IControlSeguro;
import comun.GestionarSolicitante.IControlSolicitante;
import comun.GestionarSolicitud.IControlSolicitud;
import comun.GestionarSucursal.IControlSucursal;
import comun.GestionarTaller.IControlTaller;
import comun.GestionarUsuarioRepuesto.IControlUsuarioRepuesto;

public class ClienteConection {
	
	//Se deben poner como atributos todos los controladores que se quieran cargar
	private IControlAlternativo controlAlternativo = null;
	private IControlCargo controlCargo = null;
	private IControlCarroceria controlCarroceria = null;
	private IControlFabrica controlFabrica = null;
	private IControlGarantia controlGarantia = null;
	private IControlMayorista controlMayorista = null;
	private IControlMecanico controlMecanico = null;
	private IControlMostrador controlMostrador = null;
	private IControlPerito controlPerito = null;
	private IControlPeritoSeguro controlPeritoSeguro;
	private IControlPieza controlPieza = null;
	private IControlProveedor controlProveedor = null;
	private IControlReclamo controlReclamo = null;
	private IControlSeguro controlSeguro = null;
	private IControlSolicitante controlSolicitante = null;
	private IControlSolicitud controlSolicitud = null;
	private IControlSucursal controlSucursal = null;
	private IControlTaller controlTaller = null;
	private IControlUsuarioRepuesto controlUsuarioRepuesto = null;

	public ClienteConection (){
	}

	public void iniciar() throws Exception {

		comun.RootAndIp.setConf("");
        String ip = RootAndIp.getIp_cliente();
        int port = RootAndIp.getPort();
		
        Registry Naming = LocateRegistry.getRegistry(ip,port);
		String nombreServer = "";
		
		nombreServer = "rmi://" + ip + "/IControlAlternativo";
		setControlAlternativo((IControlAlternativo)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlCargo";
		setControlCargo((IControlCargo)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlCarroceria";
		setControlCarroceria((IControlCarroceria)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlFabrica";
		setControlFabrica((IControlFabrica)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlGarantia";
		setControlGarantia((IControlGarantia)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMayorista";
		setControlMayorista((IControlMayorista)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMecanico";
		setControlMecanico((IControlMecanico)Naming.lookup(nombreServer));

		nombreServer = "rmi://" + ip + "/IControlMostrador";
		setControlMostrador((IControlMostrador)Naming.lookup(nombreServer));		
		
		nombreServer = "rmi://" + ip + "/IControlPerito";
		setControlPerito((IControlPerito)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPeritoSeguro";
		setControlPeritoSeguro((IControlPeritoSeguro)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlPieza";
		setControlPieza((IControlPieza)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlProveedor";
		setControlProveedor((IControlProveedor)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlReclamo";
		setControlReclamo((IControlReclamo)Naming.lookup(nombreServer));
			
		nombreServer = "rmi://" + ip + "/IControlSeguro";
		setControlSeguro((IControlSeguro)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlSolicitante";
		setControlSolicitante((IControlSolicitante)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlSolicitud";
		setControlSolicitud((IControlSolicitud)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlSucursal";
		setControlSucursal((IControlSucursal)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlTaller";
		setControlTaller((IControlTaller)Naming.lookup(nombreServer));
		
		nombreServer = "rmi://" + ip + "/IControlUsuarioRepuesto";
		setControlUsuarioRepuesto((IControlUsuarioRepuesto)Naming.lookup(nombreServer));
		
	}
	
	public IControlPieza getControlPieza() {
		return controlPieza;
	}

	public void setControlTest(IControlPieza controlPieza) {
		this.controlPieza = controlPieza;
	}

	public IControlProveedor getControlProveedor() {
		return controlProveedor;
	}

	public void setControlProveedor(IControlProveedor controlProveedor) {
		this.controlProveedor = controlProveedor;
	}

	public IControlReclamo getControlReclamo() {
		return controlReclamo;
	}

	public void setControlReclamo(IControlReclamo controlReclamo) {
		this.controlReclamo = controlReclamo;
	}

	public IControlSolicitante getControlSolicitante() {
		return controlSolicitante;
	}

	public void setControlSolicitante(IControlSolicitante controlSolicitante) {
		this.controlSolicitante = controlSolicitante;
	}

	public IControlSolicitud getControlSolicitud() {
		return controlSolicitud;
	}

	public void setControlSolicitud(IControlSolicitud controlSolicitud) {
		this.controlSolicitud = controlSolicitud;
	}

	public IControlUsuarioRepuesto getControlUsuarioRepuesto() {
		return controlUsuarioRepuesto;
	}

	public void setControlUsuarioRepuesto(
			IControlUsuarioRepuesto controlUsuarioRepuesto) {
		this.controlUsuarioRepuesto = controlUsuarioRepuesto;
	}

	public void setControlPieza(IControlPieza controlPieza) {
		this.controlPieza = controlPieza;
	}

	public IControlAlternativo getControlAlternativo() {
		return controlAlternativo;
	}

	public void setControlAlternativo(IControlAlternativo controlAlternativo) {
		this.controlAlternativo = controlAlternativo;
	}

	public IControlCarroceria getControlCarroceria() {
		return controlCarroceria;
	}

	public void setControlCarroceria(IControlCarroceria controlCarroceria) {
		this.controlCarroceria = controlCarroceria;
	}

	public IControlFabrica getControlFabrica() {
		return controlFabrica;
	}

	public void setControlFabrica(IControlFabrica controlFabrica) {
		this.controlFabrica = controlFabrica;
	}

	public IControlGarantia getControlGarantia() {
		return controlGarantia;
	}

	public void setControlGarantia(IControlGarantia controlGarantia) {
		this.controlGarantia = controlGarantia;
	}

	public IControlMayorista getControlMayorista() {
		return controlMayorista;
	}

	public void setControlMayorista(IControlMayorista controlMayorista) {
		this.controlMayorista = controlMayorista;
	}

	public IControlMecanico getControlMecanico() {
		return controlMecanico;
	}

	public void setControlMecanico(IControlMecanico controlMecanico) {
		this.controlMecanico = controlMecanico;
	}

	public IControlMostrador getControlMostrador() {
		return controlMostrador;
	}

	public void setControlMostrador(IControlMostrador controlMostrador) {
		this.controlMostrador = controlMostrador;
	}

	public IControlSeguro getControlSeguro() {
		return controlSeguro;
	}

	public void setControlSeguro(IControlSeguro controlSeguro) {
		this.controlSeguro = controlSeguro;
	}

	public IControlSucursal getControlSucursal() {
		return controlSucursal;
	}

	public void setControlSucursal(IControlSucursal controlSucursal) {
		this.controlSucursal = controlSucursal;
	}

	public IControlTaller getControlTaller() {
		return controlTaller;
	}

	public void setControlTaller(IControlTaller controlTaller) {
		this.controlTaller = controlTaller;
	}

	public IControlCargo getControlCargo() {
		return controlCargo;
	}

	public void setControlCargo(IControlCargo controlCargo) {
		this.controlCargo = controlCargo;
	}

	public IControlPerito getControlPerito() {
		return controlPerito;
	}

	public void setControlPerito(IControlPerito controlPerito) {
		this.controlPerito = controlPerito;
	}

	public IControlPeritoSeguro getControlPeritoSeguro() {
		return controlPeritoSeguro;
	}

	public void setControlPeritoSeguro(IControlPeritoSeguro controlPeritoSeguro) {
		this.controlPeritoSeguro = controlPeritoSeguro;
	}

}
