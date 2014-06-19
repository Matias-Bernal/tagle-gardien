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
package comun.GestionarUsuarioRepuesto;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.UsuarioRepuestoDTO;

public interface IControlUsuarioRepuesto extends Remote{

	public Long agregarUsuario(UsuarioRepuestoDTO usuario)throws Exception;
	public void eliminarUsuario(Long id)throws Exception;
	public void modificarUsuario(Long id,UsuarioRepuestoDTO modificado)throws Exception;
	
	public Vector<UsuarioRepuestoDTO> obtenerUsuarios()throws Exception;
	public Vector<UsuarioRepuestoDTO> obtenerUsuarios(String tipo)throws Exception;

	public boolean login(String nombre_usuario, String password)throws Exception;
	
	public boolean existeUsuario(Long id) throws Exception;
	public boolean existeUsuario(String nombre_usuario) throws Exception;
	
	public UsuarioRepuestoDTO buscarUsuario(Long id) throws Exception;
	public UsuarioRepuestoDTO buscarUsuario(String nombre_usuario) throws Exception;
	
}