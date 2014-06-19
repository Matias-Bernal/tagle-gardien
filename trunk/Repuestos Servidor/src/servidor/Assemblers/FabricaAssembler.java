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
import servidor.Persistencia.Dominio.Fabrica;

import comun.DTOs.FabricaDTO;

public class FabricaAssembler {
	
	private AccesoBD accesoBD;

	public FabricaAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public FabricaDTO getFabricaDTO(Fabrica fabrica) {
		FabricaDTO fabricaDTO = new FabricaDTO();
		fabricaDTO.setId(fabrica.getId());
		fabricaDTO.setNombre(fabrica.getNombre());
		return fabricaDTO;
	}

	public Fabrica getFabrica(FabricaDTO fabricaDTO) {
		Fabrica fabrica = (Fabrica) accesoBD.buscarPorId(Fabrica.class, fabricaDTO.getId());
		fabrica.setId(fabricaDTO.getId());
		fabrica.setNombre(fabricaDTO.getNombre());
		return fabrica;
	}

	public Fabrica getFabricaNuevo(FabricaDTO fabricaDTO) {
		Fabrica fabrica = new Fabrica();
		fabrica.setId(fabricaDTO.getId());
		fabrica.setNombre(fabricaDTO.getNombre());
		return fabrica;
	}

}