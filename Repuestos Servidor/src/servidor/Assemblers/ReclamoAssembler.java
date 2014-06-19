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
import servidor.Persistencia.Dominio.Reclamo;

import comun.DTOs.ReclamoDTO;

public class ReclamoAssembler {
	
	private AccesoBD accesoBD;

	public ReclamoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ReclamoDTO getReclamoDTO(Reclamo reclamo) {
		ReclamoDTO reclamoDTO = new ReclamoDTO();
		reclamoDTO.setId(reclamo.getId());
		reclamoDTO.setFecha_reclamo(reclamo.getFecha_reclamo());
		reclamoDTO.setDescripcion(reclamo.getDescripcion());
		UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
		if(reclamo.getUsuario_repuesto()!=null)
			reclamoDTO.setUsuario_repuesto(usuarioAssemb.getUsuarioRepuestoDTO(reclamo.getUsuario_repuesto()));
		SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
		if(reclamo.getUsuario_repuesto()!=null)
			reclamoDTO.setSolicitud(solicitudAssemb.getSolicitudDTO(reclamo.getSolicitud()));
		return reclamoDTO;
	}

	public Reclamo getReclamo(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = (Reclamo) accesoBD.buscarPorId(Reclamo.class, reclamoDTO.getId());
		reclamo.setId(reclamoDTO.getId());
		reclamo.setFecha_reclamo(reclamoDTO.getFecha_reclamo());
		reclamo.setDescripcion(reclamoDTO.getDescripcion());
		UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
		if(reclamoDTO.getUsuario_repuesto()!=null)
			reclamo.setUsuario_repuesto(usuarioAssemb.getUsuarioRepuesto(reclamoDTO.getUsuario_repuesto()));
		SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
		if(reclamoDTO.getUsuario_repuesto()!=null)
			reclamo.setSolicitud(solicitudAssemb.getSolicitud(reclamoDTO.getSolicitud()));
		return reclamo;
	}
	
	public Reclamo getReclamoNuevo(ReclamoDTO reclamoDTO) {
		Reclamo reclamo = new Reclamo();
		reclamo.setId(reclamoDTO.getId());
		reclamo.setFecha_reclamo(reclamoDTO.getFecha_reclamo());
		reclamo.setDescripcion(reclamoDTO.getDescripcion());
		UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
		if(reclamoDTO.getUsuario_repuesto()!=null)
			reclamo.setUsuario_repuesto(usuarioAssemb.getUsuarioRepuesto(reclamoDTO.getUsuario_repuesto()));
		SolicitudAssembler solicitudAssemb = new SolicitudAssembler(accesoBD);
		if(reclamoDTO.getUsuario_repuesto()!=null)
			reclamo.setSolicitud(solicitudAssemb.getSolicitud(reclamoDTO.getSolicitud()));
		return reclamo;
	}

}