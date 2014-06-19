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
import servidor.Persistencia.Dominio.Mostrador;

import comun.DTOs.MostradorDTO;

public class MostradorAssembler {
	
	private AccesoBD accesoBD;

	public MostradorAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public MostradorDTO getMostradorDTO(Mostrador mostrador) {
		MostradorDTO mostradorDTO = new MostradorDTO();
		mostradorDTO.setId(mostrador.getId());
		mostradorDTO.setNombre(mostrador.getNombre());
		mostradorDTO.setTelefono(mostrador.getTelefono());
		mostradorDTO.setMail(mostrador.getMail());
		return mostradorDTO;
	}

	public Mostrador getMostrador(MostradorDTO mostradorDTO) {
		Mostrador mostrador = (Mostrador) accesoBD.buscarPorId(Mostrador.class, mostradorDTO.getId());
		mostrador.setId(mostradorDTO.getId());
		mostrador.setNombre(mostradorDTO.getNombre());
		mostrador.setTelefono(mostradorDTO.getTelefono());
		mostrador.setMail(mostradorDTO.getMail());
		return mostrador;
	}

	public Mostrador getMostradorNuevo(MostradorDTO mostradorDTO) {
		Mostrador mostrador = new Mostrador();
		mostrador.setId(mostradorDTO.getId());
		mostrador.setNombre(mostradorDTO.getNombre());
		mostrador.setTelefono(mostradorDTO.getTelefono());
		mostrador.setMail(mostradorDTO.getMail());
		return mostrador;
	}

}