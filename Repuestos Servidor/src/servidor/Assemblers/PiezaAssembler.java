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
import servidor.Persistencia.Dominio.Pieza;

import comun.DTOs.PiezaDTO;

public class PiezaAssembler {
	
	private AccesoBD accesoBD;

	public PiezaAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public PiezaDTO getPiezaDTO(Pieza pieza) {
		PiezaDTO piezaDTO = new PiezaDTO();
		piezaDTO.setId(pieza.getId());
		piezaDTO.setNumero_pieza(pieza.getNumero_pieza());
		piezaDTO.setDescripcion(pieza.getDescripcion());
		return piezaDTO;
	}

	public Pieza getPieza(PiezaDTO piezaDTO) {
		Pieza pieza = (Pieza) accesoBD.buscarPorId(Pieza.class, piezaDTO.getId());
		pieza.setId(piezaDTO.getId());
		pieza.setNumero_pieza(piezaDTO.getNumero_pieza());
		pieza.setDescripcion(piezaDTO.getDescripcion());
		return pieza;
	}

	public Pieza getPiezaNuevo(PiezaDTO piezaDTO) {
		Pieza pieza = new Pieza();
		pieza.setId(piezaDTO.getId());
		pieza.setNumero_pieza(piezaDTO.getNumero_pieza());
		pieza.setDescripcion(piezaDTO.getDescripcion());
		return pieza;
	}

}