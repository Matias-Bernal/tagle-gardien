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
package comun.GestionarAlternativo;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.AlternativoDTO;

public interface IControlAlternativo extends Remote{
	
	public Long agregarAlternativo(AlternativoDTO alternativoDTO)throws Exception;
	public void eliminarAlternativo(Long id)throws Exception;
	public void modificarAlternativo(Long id,AlternativoDTO modificado)throws Exception;
	
	public Vector<AlternativoDTO> obtenerAlternativos()throws Exception;
	public Vector<AlternativoDTO> obtenerAlternativos(String query)throws Exception;
	
	public boolean existeAlternativo(Long id) throws Exception;
	public boolean existeAlternativo(String nombre) throws Exception;

	public AlternativoDTO buscarAlternativo(Long id) throws Exception;
	public AlternativoDTO buscarAlternativo(String nombre) throws Exception;
	
}
