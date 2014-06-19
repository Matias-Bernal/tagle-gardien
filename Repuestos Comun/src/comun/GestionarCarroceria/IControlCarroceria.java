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
package comun.GestionarCarroceria;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.CarroceriaDTO;

public interface IControlCarroceria extends Remote{
	
	public Long agregarCarroceria(CarroceriaDTO carroceriaDTO)throws Exception;
	public void eliminarCarroceria(Long id)throws Exception;
	public void modificarCarroceria(Long id,CarroceriaDTO modificado)throws Exception;
	
	public Vector<CarroceriaDTO> obtenerCarrocerias()throws Exception;
	public Vector<CarroceriaDTO> obtenerCarrocerias(String query)throws Exception;
	
	public boolean existeCarroceria(Long id) throws Exception;
	public boolean existeCarroceria(String nombre) throws Exception;

	public CarroceriaDTO buscarCarroceria(Long id) throws Exception;
	public CarroceriaDTO buscarCarroceria(String nombre) throws Exception;
	
}