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
package comun.GestionarGarantia;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.GarantiaDTO;

public interface IControlGarantia extends Remote{
	
	public Long agregarGarantia(GarantiaDTO garantiaDTO)throws Exception;
	public void eliminarGarantia(Long id)throws Exception;
	public void modificarGarantia(Long id,GarantiaDTO modificado)throws Exception;
	
	public Vector<GarantiaDTO> obtenerGarantias()throws Exception;
	public Vector<GarantiaDTO> obtenerGarantias(String query)throws Exception;
	
	public boolean existeGarantia(Long id) throws Exception;
	public boolean existeGarantia(String nombre) throws Exception;

	public GarantiaDTO buscarGarantia(Long id) throws Exception;
	public GarantiaDTO buscarGarantia(String nombre) throws Exception;
	
}