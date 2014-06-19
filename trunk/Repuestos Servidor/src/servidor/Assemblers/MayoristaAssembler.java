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
import servidor.Persistencia.Dominio.Mayorista;

import comun.DTOs.MayoristaDTO;

public class MayoristaAssembler {
	
	private AccesoBD accesoBD;

	public MayoristaAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public MayoristaDTO getMayoristaDTO(Mayorista mayorista) {
		MayoristaDTO mayoristaDTO = new MayoristaDTO();
		mayoristaDTO.setId(mayorista.getId());
		mayoristaDTO.setNombre(mayorista.getNombre());
		return mayoristaDTO;
	}

	public Mayorista getMayorista(MayoristaDTO mayoristaDTO) {
		Mayorista mayorista = (Mayorista) accesoBD.buscarPorId(Mayorista.class, mayoristaDTO.getId());
		mayorista.setId(mayoristaDTO.getId());
		mayorista.setNombre(mayoristaDTO.getNombre());
		return mayorista;
	}

	public Mayorista getMayoristaNuevo(MayoristaDTO mayoristaDTO) {
		Mayorista mayorista = new Mayorista();
		mayorista.setId(mayoristaDTO.getId());
		mayorista.setNombre(mayoristaDTO.getNombre());
		return mayorista;
	}

}