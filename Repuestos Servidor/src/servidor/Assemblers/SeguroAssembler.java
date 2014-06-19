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
import servidor.Persistencia.Dominio.Seguro;

import comun.DTOs.SeguroDTO;

public class SeguroAssembler {
	
	private AccesoBD accesoBD;

	public SeguroAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public SeguroDTO getSeguroDTO(Seguro seguro) {
		SeguroDTO seguroDTO = new SeguroDTO();
		seguroDTO.setId(seguro.getId());
		seguroDTO.setNombre(seguro.getNombre());
		return seguroDTO;
	}

	public Seguro getSeguro(SeguroDTO seguroDTO) {
		Seguro seguro = (Seguro) accesoBD.buscarPorId(Seguro.class, seguroDTO.getId());
		seguro.setId(seguroDTO.getId());
		seguro.setNombre(seguroDTO.getNombre());
		return seguro;
	}

	public Seguro getSeguroNuevo(SeguroDTO seguroDTO) {
		Seguro seguro = new Seguro();
		seguro.setId(seguroDTO.getId());
		seguro.setNombre(seguroDTO.getNombre());
		return seguro;
	}

}