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
package comun.GestionarSucursal;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.SucursalDTO;

public interface IControlSucursal extends Remote{
	
	public Long agregarSucursal(SucursalDTO sucursalDTO)throws Exception;
	public void eliminarSucursal(Long id)throws Exception;
	public void modificarSucursal(Long id,SucursalDTO modificado)throws Exception;
	
	public Vector<SucursalDTO> obtenerSucursals()throws Exception;
	public Vector<SucursalDTO> obtenerSucursals(String query)throws Exception;
	
	public boolean existeSucursal(Long id) throws Exception;
	public boolean existeSucursal(String nombre) throws Exception;

	public SucursalDTO buscarSucursal(Long id) throws Exception;
	public SucursalDTO buscarSucursal(String nombre) throws Exception;
	
}