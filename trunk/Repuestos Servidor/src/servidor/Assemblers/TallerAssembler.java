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
import servidor.Persistencia.Dominio.Taller;

import comun.DTOs.TallerDTO;

public class TallerAssembler {
	
	private AccesoBD accesoBD;

	public TallerAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public TallerDTO getTallerDTO(Taller taller) {
		TallerDTO tallerDTO = new TallerDTO();
		tallerDTO.setId(taller.getId());
		tallerDTO.setNombre(taller.getNombre());
		return tallerDTO;
	}

	public Taller getTaller(TallerDTO tallerDTO) {
		Taller taller = (Taller) accesoBD.buscarPorId(Taller.class, tallerDTO.getId());
		taller.setId(tallerDTO.getId());
		taller.setNombre(tallerDTO.getNombre());
		return taller;
	}

	public Taller getTallerNuevo(TallerDTO tallerDTO) {
		Taller taller = new Taller();
		taller.setId(tallerDTO.getId());
		taller.setNombre(tallerDTO.getNombre());
		return taller;
	}

}
