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

public final class MediadorAccionesIniciarPrograma{
	
	public static IControlAlternativo getControlAlternativo(){
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlAlternativo. Constructor");
		}
		return clienteConexion.getControlAlternativo();
	}
	
	public static IControlCargo getControlCargo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlCargo Constructor");
		}
		return clienteConexion.getControlCargo();
	}
	
	public static IControlCarroceria getControlCarroceria() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlCarroceria. Constructor");
		}
		return clienteConexion.getControlCarroceria();
	}
	
	public static IControlFabrica getControlFabrica() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlFabrica. Constructor");
		}
		return clienteConexion.getControlFabrica();
	}
	
	public static IControlGarantia getControlGarantia() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlGarantia. Constructor");
		}
		return clienteConexion.getControlGarantia();
	}
	
	public static IControlMayorista getControlMayorista() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMayorista. Constructor");
		}
		return clienteConexion.getControlMayorista();
	}
	
	public static IControlMecanico getControlMecanico() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMecanico. Constructor");
		}
		return clienteConexion.getControlMecanico();
	}

	public static IControlMostrador getControlMostrador() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlMostrador. Constructor");
		}
		return clienteConexion.getControlMostrador();
	}
	
	public static IControlPerito getControlPerito() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPerito Constructor");
		}
		return clienteConexion.getControlPerito();
	}
	
	public static IControlPeritoSeguro getControlPeritoSeguro() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPeritoSeguro Constructor");
		}
		return clienteConexion.getControlPeritoSeguro();
	}
	
	public static IControlPieza getControlPieza() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlPieza. Constructor");
		}
		return clienteConexion.getControlPieza();
	}
	
	public static IControlProveedor getControlProveedor(){
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlProveedor. Constructor");
		}
		return clienteConexion.getControlProveedor();
	}

	public static IControlReclamo getControlReclamo() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlReclamo. Constructor");
		}
		return clienteConexion.getControlReclamo();
	}
	
	public static IControlSeguro getControlSeguro() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlSeguro. Constructor");
		}
		return clienteConexion.getControlSeguro();
	}
	
	public static IControlSolicitante getControlSolicitante() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlSolicitante. Constructor");
		}
		return clienteConexion.getControlSolicitante();
	}
	
	public static IControlSolicitud getControlSolicitud() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlSolicitud. Constructor");
		}
		return clienteConexion.getControlSolicitud();
	}
	
	public static IControlSucursal getControlSucursal() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlSucursal. Constructor");
		}
		return clienteConexion.getControlSucursal();
	}
	
	public static IControlTaller getControlTaller() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			System.out.println("Error en getControlTaller. Constructor");
		}
		return clienteConexion.getControlTaller();
	}
	
	public static IControlUsuarioRepuesto getControlUsuariosRepuesto() {
		ClienteConection clienteConexion = new ClienteConection();
		try{
			clienteConexion.iniciar();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("Error en getControlUsuariosRepuesto. Constructor");
		}
		return clienteConexion.getControlUsuarioRepuesto();
	}

}