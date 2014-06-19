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
import servidor.Persistencia.Dominio.Garantia;

import comun.DTOs.GarantiaDTO;

public class GarantiaAssembler {
	
	private AccesoBD accesoBD;

	public GarantiaAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public GarantiaDTO getGarantiaDTO(Garantia garantia) {
		GarantiaDTO garantiaDTO = new GarantiaDTO();
		garantiaDTO.setId(garantia.getId());
		garantiaDTO.setNombre(garantia.getNombre());
		return garantiaDTO;
	}

	public Garantia getGarantia(GarantiaDTO garantiaDTO) {
		Garantia garantia = (Garantia) accesoBD.buscarPorId(Garantia.class, garantiaDTO.getId());
		garantia.setId(garantiaDTO.getId());
		garantia.setNombre(garantiaDTO.getNombre());
		return garantia;
	}

	public Garantia getGarantiaNuevo(GarantiaDTO garantiaDTO) {
		Garantia garantia = new Garantia();
		garantia.setId(garantiaDTO.getId());
		garantia.setNombre(garantiaDTO.getNombre());
		return garantia;
	}

}
