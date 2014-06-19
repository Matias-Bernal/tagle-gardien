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
import servidor.Persistencia.Dominio.Solicitante;

import comun.DTOs.SolicitanteDTO;

public class SolicitanteAssembler {

	private AccesoBD accesoBD;

	public SolicitanteAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public SolicitanteDTO getSolicitanteDTO(Solicitante solicitante) {
		SolicitanteDTO solicitanteDTO = new SolicitanteDTO();
		solicitanteDTO.setId(solicitante.getId());
		solicitanteDTO.setNombre(solicitante.getNombre());
		return solicitanteDTO;
	}

	public Solicitante getSolicitante(SolicitanteDTO solicitanteDTO) {
		Solicitante solicitante = (Solicitante) accesoBD.buscarPorId(Solicitante.class, solicitanteDTO.getId());
		solicitante.setId(solicitanteDTO.getId());
		solicitante.setNombre(solicitanteDTO.getNombre());
		return solicitante;
	}

	public Solicitante getSolicitanteNuevo(SolicitanteDTO solicitanteDTO) {
		Solicitante solicitante = new Solicitante();
		solicitante.setId(solicitanteDTO.getId());
		solicitante.setNombre(solicitanteDTO.getNombre());
		return solicitante;
	}
}
