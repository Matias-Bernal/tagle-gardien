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
import servidor.Persistencia.Dominio.UsuarioRepuesto;

import comun.DTOs.UsuarioRepuestoDTO;

public class UsuarioRepuestoAssembler {

	private AccesoBD accesoBD;

	public UsuarioRepuestoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public UsuarioRepuestoDTO getUsuarioRepuestoDTO(UsuarioRepuesto usuarioRepuesto) {
		UsuarioRepuestoDTO usuarioRepuestoDTO = new UsuarioRepuestoDTO();
		usuarioRepuestoDTO.setId(usuarioRepuesto.getId());
		usuarioRepuestoDTO.setNombre_usuario(usuarioRepuesto.getNombre_usuario());
		usuarioRepuestoDTO.setClave(usuarioRepuesto.getClave());
		usuarioRepuestoDTO.setEmail(usuarioRepuesto.getEmail());
		usuarioRepuestoDTO.setTipo(usuarioRepuesto.getTipo());
		return usuarioRepuestoDTO;
	}

	public  UsuarioRepuesto getUsuarioRepuesto(UsuarioRepuestoDTO usuarioRepuestoDTO) {
		UsuarioRepuesto usuarioRepuesto = (UsuarioRepuesto) accesoBD.buscarPorId(UsuarioRepuesto.class, usuarioRepuestoDTO.getId());
		usuarioRepuesto.setId(usuarioRepuestoDTO.getId());
		usuarioRepuesto.setNombre_usuario(usuarioRepuestoDTO.getNombre_usuario());
		usuarioRepuesto.setClave(usuarioRepuestoDTO.getClave());
		usuarioRepuesto.setEmail(usuarioRepuestoDTO.getEmail());
		usuarioRepuesto.setTipo(usuarioRepuestoDTO.getTipo());

		return usuarioRepuesto;
	}

	public UsuarioRepuesto getUsuarioRepuestoNuevo(UsuarioRepuestoDTO usuarioRepuestoDTO) {
		UsuarioRepuesto usuarioRepuesto = new UsuarioRepuesto();
		usuarioRepuesto.setId(usuarioRepuestoDTO.getId());
		usuarioRepuesto.setNombre_usuario(usuarioRepuestoDTO.getNombre_usuario());
		usuarioRepuesto.setClave(usuarioRepuestoDTO.getClave());
		usuarioRepuesto.setEmail(usuarioRepuestoDTO.getEmail());
		usuarioRepuesto.setTipo(usuarioRepuestoDTO.getTipo());

		return usuarioRepuesto;
	}

}