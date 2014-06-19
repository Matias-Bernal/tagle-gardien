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
package comun.GestionarPerito;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.PeritoDTO;

public interface IControlPerito extends Remote{
	
	public Long agregarPerito(PeritoDTO peritoDTO)throws Exception;
	public void eliminarPerito(Long id)throws Exception;
	public void modificarPerito(Long id,PeritoDTO modificado)throws Exception;
	
	public Vector<PeritoDTO> obtenerPeritos()throws Exception;
	public Vector<PeritoDTO> obtenerPeritos(String query)throws Exception;
	
	public boolean existePerito(Long id) throws Exception;
	public boolean existePerito(String nombre) throws Exception;

	public PeritoDTO buscarPerito(Long id) throws Exception;
	public PeritoDTO buscarPerito(String nombre) throws Exception;
}
