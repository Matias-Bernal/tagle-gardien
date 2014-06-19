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
import servidor.Persistencia.Dominio.Alternativo;

import comun.DTOs.AlternativoDTO;

public class AlternativoAssembler {
	
	private AccesoBD accesoBD;

	public AlternativoAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public AlternativoDTO getAlternativoDTO(Alternativo alternativo) {
		AlternativoDTO alternativoDTO = new AlternativoDTO();
		alternativoDTO.setId(alternativo.getId());
		alternativoDTO.setNombre(alternativo.getNombre());
		return alternativoDTO;
	}

	public Alternativo getAlternativo(AlternativoDTO alternativoDTO) {
		Alternativo alternativo = (Alternativo) accesoBD.buscarPorId(Alternativo.class, alternativoDTO.getId());
		alternativo.setId(alternativoDTO.getId());
		alternativo.setNombre(alternativoDTO.getNombre());
		return alternativo;
	}

	public Alternativo getAlternativoNuevo(AlternativoDTO alternativoDTO) {
		Alternativo alternativo = new Alternativo();
		alternativo.setId(alternativoDTO.getId());
		alternativo.setNombre(alternativoDTO.getNombre());
		return alternativo;
	}

}