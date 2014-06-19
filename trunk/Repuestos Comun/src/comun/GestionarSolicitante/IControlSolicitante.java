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
package comun.GestionarSolicitante;

import java.rmi.Remote;
import java.util.Vector;
import comun.DTOs.SolicitanteDTO;

public interface IControlSolicitante extends Remote{

	public Long agregarSolicitante(SolicitanteDTO solicitanteDTO)throws Exception;
	public void eliminarSolicitante(Long id)throws Exception;
	public void modificarSolicitante(Long id,SolicitanteDTO modificado)throws Exception;
	
	public Vector<SolicitanteDTO> obtenerSolicitantes()throws Exception;
	
	public boolean existeSolicitante(Long id) throws Exception;
	public boolean existeSolicitante(String nombre) throws Exception;
	
	public SolicitanteDTO buscarSolicitante(Long id) throws Exception;
	public SolicitanteDTO buscarSolicitante(String nombre) throws Exception;

}