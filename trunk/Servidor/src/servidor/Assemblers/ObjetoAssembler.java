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
import servidor.Persistencia.Dominio.Objeto;

import comun.DTOs.ObjetoDTO;

public class ObjetoAssembler {
	
	private AccesoBD accesoBD;

	public ObjetoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ObjetoDTO getObjetoDTO(Objeto objeto) {
		ObjetoDTO objetoDTO = new ObjetoDTO();
		//mapear los atributos de las dos clases
		return objetoDTO;
	}

	public Objeto getObjeto(ObjetoDTO objetoDTO) {
		Objeto objeto =	(Objeto) accesoBD.buscarPorId(Objeto.class, objetoDTO.getId());
		return objeto;
	}
	
	public Objeto getObjetoNuevo(ObjetoDTO objetoDTO) {
		Objeto objeto =	new Objeto();
		objeto.setId(objetoDTO.getId());
		return objeto;
	}

}