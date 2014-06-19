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
import servidor.Persistencia.Dominio.Mecanico;

import comun.DTOs.MecanicoDTO;

public class MecanicoAssembler {
	
	private AccesoBD accesoBD;

	public MecanicoAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public MecanicoDTO getMecanicoDTO(Mecanico mecanico) {
		MecanicoDTO mecanicoDTO = new MecanicoDTO();
		mecanicoDTO.setId(mecanico.getId());
		mecanicoDTO.setNombre(mecanico.getNombre());
		return mecanicoDTO;
	}

	public Mecanico getMecanico(MecanicoDTO mecanicoDTO) {
		Mecanico mecanico = (Mecanico) accesoBD.buscarPorId(Mecanico.class, mecanicoDTO.getId());
		mecanico.setId(mecanicoDTO.getId());
		mecanico.setNombre(mecanicoDTO.getNombre());
		return mecanico;
	}

	public Mecanico getMecanicoNuevo(MecanicoDTO mecanicoDTO) {
		Mecanico mecanico = new Mecanico();
		mecanico.setId(mecanicoDTO.getId());
		mecanico.setNombre(mecanicoDTO.getNombre());
		return mecanico;
	}

}