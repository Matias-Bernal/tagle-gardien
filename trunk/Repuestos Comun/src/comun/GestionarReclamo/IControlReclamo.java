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
package comun.GestionarReclamo;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.ReclamoDTO;
import comun.DTOs.SolicitudDTO;
import comun.DTOs.UsuarioRepuestoDTO;

public interface IControlReclamo extends Remote{
	
	public Long agregarReclamo(ReclamoDTO reclamoDTO)throws Exception;
	public void eliminarReclamo(Long id)throws Exception;
	public void modificarReclamo(Long id,ReclamoDTO modificado)throws Exception;
	
	public Vector<ReclamoDTO> obtenerReclamos()throws Exception;
	public Vector<ReclamoDTO> obtenerReclamosSolicitud(SolicitudDTO solicitud)throws Exception;
	public Vector<ReclamoDTO> obtenerReclamosUsuario(UsuarioRepuestoDTO usuario_repuesto)throws Exception;
	
	public boolean existeReclamo(Long id) throws Exception;
	
	public ReclamoDTO buscarReclamo(Long id) throws Exception;

}
