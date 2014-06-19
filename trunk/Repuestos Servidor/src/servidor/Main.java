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
import comun.DTOs.UsuarioRepuestoDTO;

public class Main {  
  
	public static void main(String[] args) {
		try {
			ControlAlternativo controlAlternativo = new ControlAlternativo();
			ControlCargo controlCargo = new ControlCargo();
			ControlCarroceria controlCarroceria = new ControlCarroceria();
			ControlFabrica controlFabrica = new ControlFabrica();
			ControlGarantia controlGarantia = new ControlGarantia();
			ControlMayorista controlMayorista = new ControlMayorista();
			ControlMecanico controlMecanico = new ControlMecanico();
			ControlMostrador controlMostrador = new ControlMostrador();
			ControlPerito controlPerito = new ControlPerito();
			ControlPeritoSeguro controlPeritoSeguro = new ControlPeritoSeguro();
			ControlPieza controlPieza = new ControlPieza();
			ControlProveedor controlProveedor = new ControlProveedor();
			ControlReclamo controlReclamo = new ControlReclamo();
			ControlSeguro controlSeguro = new ControlSeguro();
			ControlSolicitud controlSolicitud = new ControlSolicitud();
			ControlSolicitante controlSolicitante = new ControlSolicitante();
			ControlSucursal controlSucursal = new ControlSucursal();
			ControlTaller controlTaller = new ControlTaller();
			ControlUsuarioRepuesto controlUsuarioRepuesto = new ControlUsuarioRepuesto();

			Servidor servidor = new Servidor();
			SingletonConexion c = new SingletonConexion();
			
			if (!controlUsuarioRepuesto.existeUsuario("Admin")){
				UsuarioRepuestoDTO admin = new UsuarioRepuestoDTO("Admin","it10" , "contacto@it10coop.com.ar", "Administrativo");
				controlUsuarioRepuesto.agregarUsuario(admin);
				System.out.println("Agregado el usuario Admin");
			}
			servidor.setControlAlternativo(controlAlternativo);
			servidor.setControlCargo(controlCargo);
			servidor.setControlCarroceria(controlCarroceria);
			servidor.setControlFabrica(controlFabrica);
			servidor.setControlGarantia(controlGarantia);
			servidor.setControlMayorista(controlMayorista);
			servidor.setControlMecanico(controlMecanico);
			servidor.setControlMostrador(controlMostrador);
			servidor.setControlPerito(controlPerito);
			servidor.setControlPeritoSeguro(controlPeritoSeguro);
			servidor.setControlPieza(controlPieza);
			servidor.setControlProveedor(controlProveedor);
			servidor.setControlReclamo(controlReclamo);
			servidor.setControlSeguro(controlSeguro);
			servidor.setControlSolicitud(controlSolicitud);
			servidor.setControlSolicitante(controlSolicitante);
			servidor.setControlSucursal(controlSucursal);
			servidor.setControlTaller(controlTaller);
			servidor.setControlUsuarioRepuesto(controlUsuarioRepuesto);
			
			servidor.iniciar();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
  
}
