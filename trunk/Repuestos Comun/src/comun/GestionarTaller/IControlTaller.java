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
package comun.GestionarTaller;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.TallerDTO;

public interface IControlTaller extends Remote{
	
	public Long agregarTaller(TallerDTO tallerDTO)throws Exception;
	public void eliminarTaller(Long id)throws Exception;
	public void modificarTaller(Long id,TallerDTO modificado)throws Exception;
	
	public Vector<TallerDTO> obtenerTallers()throws Exception;
	public Vector<TallerDTO> obtenerTallers(String query)throws Exception;
	
	public boolean existeTaller(Long id) throws Exception;
	public boolean existeTaller(String nombre) throws Exception;

	public TallerDTO buscarTaller(Long id) throws Exception;
	public TallerDTO buscarTaller(String nombre) throws Exception;
	
}
