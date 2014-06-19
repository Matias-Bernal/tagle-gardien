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
package servidor.GestionarSolicitud;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.CargoAssembler;
import servidor.Assemblers.PeritoAssembler;
import servidor.Assemblers.PiezaAssembler;
import servidor.Assemblers.ProveedorAssembler;
import servidor.Assemblers.SolicitanteAssembler;
import servidor.Assemblers.SolicitudAssembler;
import servidor.Assemblers.UsuarioRepuestoAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Solicitud;
import comun.DTOs.SolicitudDTO;
import comun.GestionarSolicitud.IControlSolicitud;

public class ControlSolicitud extends UnicastRemoteObject implements IControlSolicitud{

	private static final long serialVersionUID = 1L;

	public ControlSolicitud() throws RemoteException {
		super();
	}

	@Override
	public Long agregarSolicitud(SolicitudDTO solicitudDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
			Solicitud solicitud = solicitudAssemb.getSolicitudNuevo(solicitudDTO);
			accesoBD.hacerPersistente(solicitud);
			id = solicitud.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarSolicitud(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
			Solicitud solicitud = solicitudAssemb.getSolicitud(buscarSolicitud(id));
			accesoBD.eliminar(solicitud);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		
	}

	@Override
	public void modificarSolicitud(Long id, SolicitudDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
			Solicitud solicitud = solicitudAssemb.getSolicitud(buscarSolicitud(id));
			
			solicitud.setFecha_solicitud_solicitante(modificado.getFecha_solicitud_solicitante());
			solicitud.setFecha_solicitud_proveedor(modificado.getFecha_solicitud_proveedor());
			solicitud.setFecha_recepcion_proveedor(modificado.getFecha_recepcion_proveedor());
			solicitud.setStock_propio(modificado.isStock_propio());
			solicitud.setStock_fabrica(modificado.isStock_fabrica());
			solicitud.setBloqueada(modificado.isBloqueada());
			solicitud.setCantidad(modificado.getCantidad());
			solicitud.setOrden_trabajo(modificado.getOrden_trabajo());
			solicitud.setDominio(modificado.getDominio());
			solicitud.setNumero_pedido(modificado.getNumero_pedido());
			solicitud.setEstado(modificado.getEstado());
			solicitud.setNumero_siniestro(modificado.getNumero_siniestro());
			solicitud.setPnc(modificado.getPnc());
			solicitud.setPcl(modificado.getPcl());
			/***************************/
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			if (modificado.getCargo()!=null)
				solicitud.setCargo(cargoAssemb.getCargo(modificado.getCargo()));
			SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
			if (modificado.getSolicitante()!=null)
				solicitud.setSolicitante(solicitanteAssemb.getSolicitante(modificado.getSolicitante()));
			UsuarioRepuestoAssembler usuarioRepuestoAssemb = new UsuarioRepuestoAssembler(accesoBD);
			if (modificado.getUsuario_repuesto()!=null)
				solicitud.setUsuario_repuesto(usuarioRepuestoAssemb.getUsuarioRepuesto(modificado.getUsuario_repuesto()));		
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			if (modificado.getPieza()!=null)
				solicitud.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			if (modificado.getProveedor()!=null)
				solicitud.setProveedor(proveedorAssemb.getProveedor(modificado.getProveedor()));
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			if (modificado.getPerito()!=null)
				solicitud.setPerito(peritoAssemb.getPerito(modificado.getPerito()));
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		
	}

	@Override
	public Vector<SolicitudDTO> obtenerSolicitudes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SolicitudDTO> solicitudesDTO = new Vector<SolicitudDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Solicitud> solicitudes = new Vector<Solicitud> (accesoBD.buscarPorFiltro(Solicitud.class, ""));
			for (int i = 0; i < solicitudes.size(); i++) {
				SolicitudDTO solicitudDTO = new SolicitudDTO();

				solicitudDTO.setId(solicitudes.elementAt(i).getId());
				solicitudDTO.setFecha_solicitud_solicitante(solicitudes.elementAt(i).getFecha_solicitud_solicitante());
				solicitudDTO.setFecha_solicitud_proveedor(solicitudes.elementAt(i).getFecha_solicitud_proveedor());
				solicitudDTO.setFecha_recepcion_proveedor(solicitudes.elementAt(i).getFecha_recepcion_proveedor());
				solicitudDTO.setStock_propio(solicitudes.elementAt(i).isStock_propio());
				solicitudDTO.setStock_fabrica(solicitudes.elementAt(i).isStock_fabrica());
				solicitudDTO.setBloqueada(solicitudes.elementAt(i).isBloqueada());
				solicitudDTO.setCantidad(solicitudes.elementAt(i).getCantidad());
				solicitudDTO.setOrden_trabajo(solicitudes.elementAt(i).getOrden_trabajo());
				solicitudDTO.setDominio(solicitudes.elementAt(i).getDominio());
				solicitudDTO.setNumero_pedido(solicitudes.elementAt(i).getNumero_pedido());
				solicitudDTO.setEstado(solicitudes.elementAt(i).getEstado());
				solicitudDTO.setNumero_siniestro(solicitudes.elementAt(i).getNumero_siniestro());
				solicitudDTO.setPnc(solicitudes.elementAt(i).getPnc());
				solicitudDTO.setPcl(solicitudes.elementAt(i).getPcl());
				/***************************/
				
				CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
				if (solicitudes.elementAt(i).getCargo()!=null)
					solicitudDTO.setCargo(cargoAssemb.getCargoDTO(solicitudes.elementAt(i).getCargo()));
				SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
				if (solicitudes.elementAt(i).getSolicitante()!=null)
					solicitudDTO.setSolicitante(solicitanteAssemb.getSolicitanteDTO(solicitudes.elementAt(i).getSolicitante()));
				UsuarioRepuestoAssembler usuarioRepuestoAssemb = new UsuarioRepuestoAssembler(accesoBD);
				if (solicitudes.elementAt(i).getUsuario_repuesto()!=null)
					solicitudDTO.setUsuario_repuesto(usuarioRepuestoAssemb.getUsuarioRepuestoDTO(solicitudes.elementAt(i).getUsuario_repuesto()));		
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				if (solicitudes.elementAt(i).getPieza()!=null)
					solicitudDTO.setPieza(piezaAssemb.getPiezaDTO(solicitudes.elementAt(i).getPieza()));
				ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
				if (solicitudes.elementAt(i).getProveedor()!=null)
					solicitudDTO.setProveedor(proveedorAssemb.getProveedorDTO(solicitudes.elementAt(i).getProveedor()));
				PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
				if (solicitudes.elementAt(i).getPerito()!=null)
					solicitudDTO.setPerito(peritoAssemb.getPeritoDTO(solicitudes.elementAt(i).getPerito()));
				solicitudesDTO.add(solicitudDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return solicitudesDTO;
	}

	@Override
	public boolean existeSolicitud(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Solicitud) accesoBD.buscarPorId(Solicitud.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public SolicitudDTO buscarSolicitud(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SolicitudDTO solicitudDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
			solicitudDTO = solicitudAssemb.getSolicitudDTO((Solicitud) accesoBD.buscarPorId(Solicitud.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return solicitudDTO;
	}

	@Override
	public Vector<SolicitudDTO> obtenerSolicitudes(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SolicitudDTO> solicitudesDTO = new Vector<SolicitudDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Solicitud.class, query);
			SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				solicitudesDTO.add(solicitudAssemb.getSolicitudDTO((Solicitud)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return solicitudesDTO;
	}
}
