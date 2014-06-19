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
package comun.GestionarSolicitud;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.SolicitudDTO;

public interface IControlSolicitud extends Remote{

	public Long agregarSolicitud(SolicitudDTO solicitudDTO)throws Exception;
	public void eliminarSolicitud(Long id)throws Exception;
	public void modificarSolicitud(Long id,SolicitudDTO modificado)throws Exception;
	
	public Vector<SolicitudDTO> obtenerSolicitudes()throws Exception;
	public Vector<SolicitudDTO> obtenerSolicitudes(String query)throws Exception;
	
	public boolean existeSolicitud(Long id) throws Exception;	
	
	public SolicitudDTO buscarSolicitud(Long id) throws Exception;

}
