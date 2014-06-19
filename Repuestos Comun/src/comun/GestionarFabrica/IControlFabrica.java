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
package comun.GestionarFabrica;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.FabricaDTO;

public interface IControlFabrica extends Remote{
	
	public Long agregarFabrica(FabricaDTO fabricaDTO)throws Exception;
	public void eliminarFabrica(Long id)throws Exception;
	public void modificarFabrica(Long id,FabricaDTO modificado)throws Exception;
	
	public Vector<FabricaDTO> obtenerFabricas()throws Exception;
	public Vector<FabricaDTO> obtenerFabricas(String query)throws Exception;
	
	public boolean existeFabrica(Long id) throws Exception;
	public boolean existeFabrica(String nombre) throws Exception;

	public FabricaDTO buscarFabrica(Long id) throws Exception;
	public FabricaDTO buscarFabrica(String nombre) throws Exception;
	
}
