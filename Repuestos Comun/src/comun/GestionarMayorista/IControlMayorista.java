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
package comun.GestionarMayorista;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.MayoristaDTO;

public interface IControlMayorista extends Remote{
	
	public Long agregarMayorista(MayoristaDTO mayoristaDTO)throws Exception;
	public void eliminarMayorista(Long id)throws Exception;
	public void modificarMayorista(Long id,MayoristaDTO modificado)throws Exception;
	
	public Vector<MayoristaDTO> obtenerMayoristas()throws Exception;
	public Vector<MayoristaDTO> obtenerMayoristas(String query)throws Exception;
	
	public boolean existeMayorista(Long id) throws Exception;
	public boolean existeMayorista(String nombre) throws Exception;

	public MayoristaDTO buscarMayorista(Long id) throws Exception;
	public MayoristaDTO buscarMayorista(String nombre) throws Exception;
	
}