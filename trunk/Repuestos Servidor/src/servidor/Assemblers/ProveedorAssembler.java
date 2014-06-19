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
import servidor.Persistencia.Dominio.Proveedor;

import comun.DTOs.ProveedorDTO;

public class ProveedorAssembler {
	
	private AccesoBD accesoBD;

	public ProveedorAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public ProveedorDTO getProveedorDTO(Proveedor proveedor) {
		ProveedorDTO proveedorDTO = new ProveedorDTO();
		proveedorDTO.setId(proveedor.getId());
		proveedorDTO.setNombre(proveedor.getNombre());
		return proveedorDTO;
	}

	public Proveedor getProveedor(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = (Proveedor) accesoBD.buscarPorId(Proveedor.class, proveedorDTO.getId());
		proveedor.setId(proveedorDTO.getId());
		proveedor.setNombre(proveedorDTO.getNombre());
		return proveedor;
	}

	public Proveedor getProveedorNuevo(ProveedorDTO proveedorDTO) {
		Proveedor proveedor = new Proveedor();
		proveedor.setId(proveedorDTO.getId());
		proveedor.setNombre(proveedorDTO.getNombre());
		return proveedor;
	}

}
