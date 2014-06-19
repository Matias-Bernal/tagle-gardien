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
package comun.GestionarMostrador;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.MostradorDTO;

public interface IControlMostrador extends Remote{
	
	public Long agregarMostrador(MostradorDTO mostradorDTO)throws Exception;
	public void eliminarMostrador(Long id)throws Exception;
	public void modificarMostrador(Long id,MostradorDTO modificado)throws Exception;
	
	public Vector<MostradorDTO> obtenerMostradors()throws Exception;
	public Vector<MostradorDTO> obtenerMostradors(String query)throws Exception;
	
	public boolean existeMostrador(Long id) throws Exception;
	public boolean existeMostrador(String nombre) throws Exception;
	public boolean existeMostradorTel(String telefono) throws Exception;

	public MostradorDTO buscarMostrador(Long id) throws Exception;
	public MostradorDTO buscarMostrador(String nombre) throws Exception;
	public MostradorDTO buscarMostradorTel(String telefono) throws Exception;
}