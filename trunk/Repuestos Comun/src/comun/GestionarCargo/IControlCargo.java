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
package comun.GestionarCargo;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.CargoDTO;

public interface IControlCargo extends Remote{
	
	public Long agregarCargo(CargoDTO cargoDTO)throws Exception;
	public void eliminarCargo(Long id)throws Exception;
	public void modificarCargo(Long id,CargoDTO modificado)throws Exception;
	
	public Vector<CargoDTO> obtenerCargos()throws Exception;
	public Vector<CargoDTO> obtenerCargos(String query)throws Exception;
	
	public boolean existeCargo(Long id) throws Exception;
	public boolean existeCargo(String nombre) throws Exception;

	public CargoDTO buscarCargo(Long id) throws Exception;
	public CargoDTO buscarCargo(String nombre) throws Exception;
	
}
