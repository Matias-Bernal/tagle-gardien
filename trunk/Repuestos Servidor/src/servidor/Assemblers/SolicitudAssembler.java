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
package servidor.Assemblers;

import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Solicitud;

import comun.DTOs.SolicitudDTO;

public class SolicitudAssembler {

	private AccesoBD accesoBD;

	public SolicitudAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public SolicitudDTO getSolicitudDTO(Solicitud solicitud) {
		SolicitudDTO solicitudDTO = new SolicitudDTO();
		
		solicitudDTO.setId(solicitud.getId());
		solicitudDTO.setFecha_solicitud_solicitante(solicitud.getFecha_solicitud_solicitante());
		solicitudDTO.setFecha_solicitud_proveedor(solicitud.getFecha_solicitud_proveedor());
		solicitudDTO.setFecha_recepcion_proveedor(solicitud.getFecha_recepcion_proveedor());
		solicitudDTO.setStock_propio(solicitud.isStock_propio());
		solicitudDTO.setStock_fabrica(solicitud.isStock_fabrica());
		solicitudDTO.setBloqueada(solicitud.isBloqueada());
		solicitudDTO.setCantidad(solicitud.getCantidad());
		solicitudDTO.setOrden_trabajo(solicitud.getOrden_trabajo());
		solicitudDTO.setDominio(solicitud.getDominio());
		solicitudDTO.setNumero_pedido(solicitud.getNumero_pedido());
		solicitudDTO.setEstado(solicitud.getEstado());
		solicitudDTO.setNumero_siniestro(solicitud.getNumero_siniestro());
		solicitudDTO.setPnc(solicitud.getPnc());
		solicitudDTO.setPcl(solicitud.getPcl());
		/***************************/
		CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
		if (solicitud.getCargo()!=null)
			solicitudDTO.setCargo(cargoAssemb.getCargoDTO(solicitud.getCargo()));	
		SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
		if (solicitud.getSolicitante()!=null)
			solicitudDTO.setSolicitante(solicitanteAssemb.getSolicitanteDTO(solicitud.getSolicitante()));
		UsuarioRepuestoAssembler usuarioRepuestoAssemb = new UsuarioRepuestoAssembler(accesoBD);
		if (solicitud.getUsuario_repuesto()!=null)
			solicitudDTO.setUsuario_repuesto(usuarioRepuestoAssemb.getUsuarioRepuestoDTO(solicitud.getUsuario_repuesto()));		
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if (solicitud.getPieza()!=null)
			solicitudDTO.setPieza(piezaAssemb.getPiezaDTO(solicitud.getPieza()));
		ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
		if (solicitud.getProveedor()!=null)
			solicitudDTO.setProveedor(proveedorAssemb.getProveedorDTO(solicitud.getProveedor()));
		PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
		if (solicitud.getPerito()!=null)
			solicitudDTO.setPerito(peritoAssemb.getPeritoDTO(solicitud.getPerito()));
		return solicitudDTO;
	}

	public Solicitud getSolicitud(SolicitudDTO solicitudDTO) {
		Solicitud solicitud = (Solicitud) accesoBD.buscarPorId(Solicitud.class, solicitudDTO.getId());
		
		solicitud.setId(solicitudDTO.getId());
		solicitud.setFecha_solicitud_solicitante(solicitudDTO.getFecha_solicitud_solicitante());
		solicitud.setFecha_solicitud_proveedor(solicitudDTO.getFecha_solicitud_proveedor());
		solicitud.setFecha_recepcion_proveedor(solicitudDTO.getFecha_recepcion_proveedor());
		solicitud.setStock_propio(solicitudDTO.isStock_propio());
		solicitud.setStock_fabrica(solicitudDTO.isStock_fabrica());
		solicitud.setBloqueada(solicitudDTO.isBloqueada());
		solicitud.setCantidad(solicitudDTO.getCantidad());
		solicitud.setOrden_trabajo(solicitudDTO.getOrden_trabajo());
		solicitud.setDominio(solicitudDTO.getDominio());
		solicitud.setNumero_pedido(solicitudDTO.getNumero_pedido());
		solicitud.setEstado(solicitudDTO.getEstado());
		solicitud.setNumero_siniestro(solicitudDTO.getNumero_siniestro());
		solicitud.setPnc(solicitudDTO.getPnc());
		solicitud.setPcl(solicitudDTO.getPcl());	
		/***************************/
		CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
		if (solicitudDTO.getCargo()!=null)
			solicitud.setCargo(cargoAssemb.getCargo(solicitudDTO.getCargo()));
		SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
		if (solicitudDTO.getSolicitante()!=null)
			solicitud.setSolicitante(solicitanteAssemb.getSolicitante(solicitudDTO.getSolicitante()));
		UsuarioRepuestoAssembler usuarioRepuestoAssemb = new UsuarioRepuestoAssembler(accesoBD);
		if (solicitudDTO.getUsuario_repuesto()!=null)
			solicitud.setUsuario_repuesto(usuarioRepuestoAssemb.getUsuarioRepuesto(solicitudDTO.getUsuario_repuesto()));		
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if (solicitudDTO.getPieza()!=null)
			solicitud.setPieza(piezaAssemb.getPieza(solicitudDTO.getPieza()));
		ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
		if (solicitudDTO.getProveedor()!=null)
			solicitud.setProveedor(proveedorAssemb.getProveedor(solicitudDTO.getProveedor()));
		PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
		if (solicitudDTO.getPerito()!=null)
			solicitud.setPerito(peritoAssemb.getPerito(solicitudDTO.getPerito()));
		return solicitud;
	}

	public Solicitud getSolicitudNuevo(SolicitudDTO solicitudDTO) {
		Solicitud solicitud = new Solicitud();
		
		solicitud.setId(solicitudDTO.getId());
		solicitud.setFecha_solicitud_solicitante(solicitudDTO.getFecha_solicitud_solicitante());
		solicitud.setFecha_solicitud_proveedor(solicitudDTO.getFecha_solicitud_proveedor());
		solicitud.setFecha_recepcion_proveedor(solicitudDTO.getFecha_recepcion_proveedor());
		solicitud.setStock_propio(solicitudDTO.isStock_propio());
		solicitud.setStock_fabrica(solicitudDTO.isStock_fabrica());
		solicitud.setBloqueada(solicitudDTO.isBloqueada());
		solicitud.setCantidad(solicitudDTO.getCantidad());
		solicitud.setOrden_trabajo(solicitudDTO.getOrden_trabajo());
		solicitud.setDominio(solicitudDTO.getDominio());
		solicitud.setNumero_pedido(solicitudDTO.getNumero_pedido());
		solicitud.setEstado(solicitudDTO.getEstado());
		solicitud.setNumero_siniestro(solicitudDTO.getNumero_siniestro());
		solicitud.setPnc(solicitudDTO.getPnc());
		solicitud.setPcl(solicitudDTO.getPcl());	
		/***************************/
		CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
		if (solicitudDTO.getCargo()!=null)
			solicitud.setCargo(cargoAssemb.getCargo(solicitudDTO.getCargo()));
		SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
		if (solicitudDTO.getSolicitante()!=null)
			solicitud.setSolicitante(solicitanteAssemb.getSolicitante(solicitudDTO.getSolicitante()));
		UsuarioRepuestoAssembler usuarioRepuestoAssemb = new UsuarioRepuestoAssembler(accesoBD);
		if (solicitudDTO.getUsuario_repuesto()!=null)
			solicitud.setUsuario_repuesto(usuarioRepuestoAssemb.getUsuarioRepuesto(solicitudDTO.getUsuario_repuesto()));		
		PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
		if (solicitudDTO.getPieza()!=null)
			solicitud.setPieza(piezaAssemb.getPieza(solicitudDTO.getPieza()));
		ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
		if (solicitudDTO.getProveedor()!=null)
			solicitud.setProveedor(proveedorAssemb.getProveedor(solicitudDTO.getProveedor()));
		PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
		if (solicitudDTO.getPerito()!=null)
			solicitud.setPerito(peritoAssemb.getPerito(solicitudDTO.getPerito()));
		return solicitud;
	}
}
