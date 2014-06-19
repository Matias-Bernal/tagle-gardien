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
package comun.GestionarProveedor;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.ProveedorDTO;

public interface IControlProveedor extends Remote{
	
	public Long agregarProveedor(ProveedorDTO proveedorDTO)throws Exception;
	public void eliminarProveedor(Long id)throws Exception;
	public void modificarProveedor(Long id,ProveedorDTO modificado)throws Exception;
	
	public Vector<ProveedorDTO> obtenerProveedores()throws Exception;
	
	public boolean existeProveedor(Long id) throws Exception;
	public boolean existeProveedor(String nombre) throws Exception;
	
	public ProveedorDTO buscarProveedor(Long id) throws Exception;
	public ProveedorDTO buscarProveedor(String nombre) throws Exception;

}