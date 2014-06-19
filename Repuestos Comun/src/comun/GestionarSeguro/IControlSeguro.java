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
package comun.GestionarSeguro;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.SeguroDTO;

public interface IControlSeguro extends Remote{
	
	public Long agregarSeguro(SeguroDTO seguroDTO)throws Exception;
	public void eliminarSeguro(Long id)throws Exception;
	public void modificarSeguro(Long id,SeguroDTO modificado)throws Exception;
	
	public Vector<SeguroDTO> obtenerSeguros()throws Exception;
	public Vector<SeguroDTO> obtenerSeguros(String query)throws Exception;
	
	public boolean existeSeguro(Long id) throws Exception;
	public boolean existeSeguro(String nombre) throws Exception;

	public SeguroDTO buscarSeguro(Long id) throws Exception;
	public SeguroDTO buscarSeguro(String nombre) throws Exception;
	
}