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
import servidor.Persistencia.Dominio.Perito;

import comun.DTOs.PeritoDTO;

public class PeritoAssembler {

	private AccesoBD accesoBD;

	public PeritoAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public PeritoDTO getPeritoDTO(Perito perito) {
		PeritoDTO PeritoDTO = new PeritoDTO();
		PeritoDTO.setId(perito.getId());
		PeritoDTO.setNombre(perito.getNombre());
		PeritoDTO.setTelefono(perito.getTelefono());
		PeritoDTO.setMail(perito.getMail());
		return PeritoDTO;
	}

	public Perito getPerito(PeritoDTO peritoDTO) {
		Perito Perito = (Perito) accesoBD.buscarPorId(Perito.class, peritoDTO.getId());
		Perito.setId(peritoDTO.getId());
		Perito.setNombre(peritoDTO.getNombre());
		Perito.setTelefono(peritoDTO.getTelefono());
		Perito.setMail(peritoDTO.getMail());
		return Perito;
	}

	public Perito getPeritoNuevo(PeritoDTO peritoDTO) {
		Perito Perito = new Perito();
		Perito.setId(peritoDTO.getId());
		Perito.setNombre(peritoDTO.getNombre());
		Perito.setTelefono(peritoDTO.getTelefono());
		Perito.setMail(peritoDTO.getMail());
		return Perito;
	}
}
