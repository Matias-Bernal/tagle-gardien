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
package servidor;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import servidor.GestionarAlternativo.ControlAlternativo;
import servidor.GestionarCargo.ControlCargo;
import servidor.GestionarCarroceria.ControlCarroceria;
import servidor.GestionarFabrica.ControlFabrica;
import servidor.GestionarGarantia.ControlGarantia;
import servidor.GestionarMayorista.ControlMayorista;
import servidor.GestionarMecanico.ControlMecanico;
import servidor.GestionarMostrador.ControlMostrador;
import servidor.GestionarPerito.ControlPerito;
import servidor.GestionarPeritoSeguro.ControlPeritoSeguro;
import servidor.GestionarPieza.ControlPieza;
import servidor.GestionarProveedor.ControlProveedor;
import servidor.GestionarReclamo.ControlReclamo;
import servidor.GestionarSeguro.ControlSeguro;
import servidor.GestionarSolicitante.ControlSolicitante;
import servidor.GestionarSolicitud.ControlSolicitud;
import servidor.GestionarSucursal.ControlSucursal;
import servidor.GestionarTaller.ControlTaller;
import servidor.GestionarUsuarioRepuesto.ControlUsuarioRepuesto;
import comun.RootAndIp;
import comun.Utils;

public class Servidor {

	private String name = "";
	private String ip = "";
	
	private ControlAlternativo controlAlternativo = null;
	private ControlCargo controlCargo = null;
	private ControlCarroceria controlCarroceria = null;
	private ControlFabrica controlFabrica = null;
	private ControlGarantia controlGarantia = null;
	private ControlMayorista controlMayorista = null;
	private ControlMecanico controlMecanico = null;
	private ControlMostrador controlMostrador = null;
	private ControlPerito controlPerito = null;
	private ControlPeritoSeguro controlPeritoSeguro = null;
	private ControlPieza controlPieza = null;
	private ControlProveedor controlProveedor = null;
	private ControlReclamo controlReclamo = null;
	private ControlSeguro controlSeguro = null;
	private ControlSolicitante controlSolicitante = null;
	private ControlSolicitud controlSolicitud = null;
	private ControlSucursal controlSucursal = null;
	private ControlTaller controlTaller = null;
	private ControlUsuarioRepuesto controlUsuarioRepuesto = null;
	
	public Servidor() {
	}
	
	public void iniciar() throws Exception {
		comun.RootAndIp.setConf("");
		int port = RootAndIp.getPort();
		
		Registry Naming = LocateRegistry.createRegistry(port);
		Utils.setCodeBase(Servidor.class);
		
		System.out.println("Iniciando servidor !!!");

		comun.RootAndIp.setConf("");
        ip = RootAndIp.getIp_servidor();
        System.out.println("ip localhost: "+InetAddress.getLocalHost().toString());
		System.out.println("Ip: " + this.ip);
		
		this.name = "rmi://" + this.ip + "/IControlAlternativo";
		Naming.rebind(this.name, this.controlAlternativo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlCargo";
		Naming.rebind(this.name, this.controlCargo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlCarroceria";
		Naming.rebind(this.name, this.controlCarroceria);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlFabrica";
		Naming.rebind(this.name, this.controlFabrica);
		System.out.println("Nombre: " + this.name);	
		
		this.name = "rmi://" + this.ip + "/IControlGarantia";
		Naming.rebind(this.name, this.controlGarantia);
		System.out.println("Nombre: " + this.name);	
		
		this.name = "rmi://" + this.ip + "/IControlMayorista";
		Naming.rebind(this.name, this.controlMayorista);
		System.out.println("Nombre: " + this.name);	
		
		this.name = "rmi://" + this.ip + "/IControlMecanico";
		Naming.rebind(this.name, this.controlMecanico);
		System.out.println("Nombre: " + this.name);			

		this.name = "rmi://" + this.ip + "/IControlMostrador";
		Naming.rebind(this.name, this.controlMostrador);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPerito";
		Naming.rebind(this.name, this.controlPerito);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPeritoSeguro";
		Naming.rebind(this.name, this.controlPeritoSeguro);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlPieza";
		Naming.rebind(this.name, this.controlPieza);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlProveedor";
		Naming.rebind(this.name, this.controlProveedor);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlReclamo";
		Naming.rebind(this.name, this.controlReclamo);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlSeguro";
		Naming.rebind(this.name, this.controlSeguro);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlSolicitante";
		Naming.rebind(this.name, this.controlSolicitante);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlSolicitud";
		Naming.rebind(this.name, this.controlSolicitud);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlSucursal";
		Naming.rebind(this.name, this.controlSucursal);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlTaller";
		Naming.rebind(this.name, this.controlTaller);
		System.out.println("Nombre: " + this.name);
		
		this.name = "rmi://" + this.ip + "/IControlUsuarioRepuesto";
		Naming.rebind(this.name, this.controlUsuarioRepuesto);
		System.out.println("Nombre: " + this.name);
		
		// Cargar el resto de los controles
		System.out.println("Listo para conexiones");
		int exit = 0;
		synchronized (this) {
			if(exit!=1)
				this.wait();
		}
		//GUIServidor serv = new GUIServidor();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public ControlPieza getControlPieza() {
		return controlPieza;
	}

	public void setControlPieza(ControlPieza controlPieza) {
		this.controlPieza = controlPieza;
	}

	public ControlProveedor getControlProveedor() {
		return controlProveedor;
	}

	public void setControlProveedor(ControlProveedor controlProveedor) {
		this.controlProveedor = controlProveedor;
	}

	public ControlReclamo getControlReclamo() {
		return controlReclamo;
	}

	public void setControlReclamo(ControlReclamo controlReclamo) {
		this.controlReclamo = controlReclamo;
	}

	public ControlSolicitante getControlSolicitante() {
		return controlSolicitante;
	}

	public void setControlSolicitante(ControlSolicitante controlSolicitante) {
		this.controlSolicitante = controlSolicitante;
	}

	public ControlSolicitud getControlSolicitud() {
		return controlSolicitud;
	}

	public void setControlSolicitud(ControlSolicitud controlSolicitud) {
		this.controlSolicitud = controlSolicitud;
	}

	public ControlUsuarioRepuesto getControlUsuarioRepuesto() {
		return controlUsuarioRepuesto;
	}

	public void setControlUsuarioRepuesto(
			ControlUsuarioRepuesto controlUsuarioRepuesto) {
		this.controlUsuarioRepuesto = controlUsuarioRepuesto;
	}

	public ControlAlternativo getControlAlternativo() {
		return controlAlternativo;
	}

	public void setControlAlternativo(ControlAlternativo controlAlternativo) {
		this.controlAlternativo = controlAlternativo;
	}

	public ControlCarroceria getControlCarroceria() {
		return controlCarroceria;
	}

	public void setControlCarroceria(ControlCarroceria controlCarroceria) {
		this.controlCarroceria = controlCarroceria;
	}

	public ControlFabrica getControlFabrica() {
		return controlFabrica;
	}

	public void setControlFabrica(ControlFabrica controlFabrica) {
		this.controlFabrica = controlFabrica;
	}

	public ControlGarantia getControlGarantia() {
		return controlGarantia;
	}

	public void setControlGarantia(ControlGarantia controlGarantia) {
		this.controlGarantia = controlGarantia;
	}

	public ControlMayorista getControlMayorista() {
		return controlMayorista;
	}

	public void setControlMayorista(ControlMayorista controlMayorista) {
		this.controlMayorista = controlMayorista;
	}

	public ControlMecanico getControlMecanico() {
		return controlMecanico;
	}

	public void setControlMecanico(ControlMecanico controlMecanico) {
		this.controlMecanico = controlMecanico;
	}

	public ControlMostrador getControlMostrador() {
		return controlMostrador;
	}

	public void setControlMostrador(ControlMostrador controlMostrador) {
		this.controlMostrador = controlMostrador;
	}

	public ControlSeguro getControlSeguro() {
		return controlSeguro;
	}

	public void setControlSeguro(ControlSeguro controlSeguro) {
		this.controlSeguro = controlSeguro;
	}

	public ControlSucursal getControlSucursal() {
		return controlSucursal;
	}

	public void setControlSucursal(ControlSucursal controlSucursal) {
		this.controlSucursal = controlSucursal;
	}

	public ControlTaller getControlTaller() {
		return controlTaller;
	}

	public void setControlTaller(ControlTaller controlTaller) {
		this.controlTaller = controlTaller;
	}

	public ControlCargo getControlCargo() {
		return controlCargo;
	}

	public void setControlCargo(ControlCargo controlCargo) {
		this.controlCargo = controlCargo;
	}

	public ControlPerito getControlPerito() {
		return controlPerito;
	}

	public void setControlPerito(ControlPerito controlPerito) {
		this.controlPerito = controlPerito;
	}

	public ControlPeritoSeguro getControlPeritoSeguro() {
		return controlPeritoSeguro;
	}

	public void setControlPeritoSeguro(ControlPeritoSeguro controlPeritoSeguro) {
		this.controlPeritoSeguro = controlPeritoSeguro;
	}


}
