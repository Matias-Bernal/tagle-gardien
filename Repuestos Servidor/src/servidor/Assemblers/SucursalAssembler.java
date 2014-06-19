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
import servidor.Persistencia.Dominio.Sucursal;

import comun.DTOs.SucursalDTO;

public class SucursalAssembler {
	
	private AccesoBD accesoBD;

	public SucursalAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public SucursalDTO getSucursalDTO(Sucursal sucursal) {
		SucursalDTO sucursalDTO = new SucursalDTO();
		sucursalDTO.setId(sucursal.getId());
		sucursalDTO.setNombre(sucursal.getNombre());
		return sucursalDTO;
	}

	public Sucursal getSucursal(SucursalDTO sucursalDTO) {
		Sucursal sucursal = (Sucursal) accesoBD.buscarPorId(Sucursal.class, sucursalDTO.getId());
		sucursal.setId(sucursalDTO.getId());
		sucursal.setNombre(sucursalDTO.getNombre());
		return sucursal;
	}

	public Sucursal getSucursalNuevo(SucursalDTO sucursalDTO) {
		Sucursal sucursal = new Sucursal();
		sucursal.setId(sucursalDTO.getId());
		sucursal.setNombre(sucursalDTO.getNombre());
		return sucursal;
	}

}