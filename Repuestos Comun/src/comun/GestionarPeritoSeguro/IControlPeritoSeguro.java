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
package comun.GestionarPeritoSeguro;

import java.rmi.Remote;
import java.util.Vector;

import comun.DTOs.PeritoDTO;
import comun.DTOs.PeritoSeguroDTO;
import comun.DTOs.SeguroDTO;

public interface IControlPeritoSeguro extends Remote{
	
	public Long agregarPeritoSeguro(PeritoSeguroDTO peritoSeguroDTO)throws Exception;
	public void eliminarPeritoSeguro(Long id)throws Exception;
	public void modificarPeritoSeguro(Long id,PeritoSeguroDTO modificado)throws Exception;
	
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros()throws Exception;
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros(PeritoDTO perito)throws Exception;
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros(SeguroDTO seguro)throws Exception;
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros(String query)throws Exception;
	
	public boolean existePeritoSeguro(Long id) throws Exception;
	public boolean existePeritoSeguro(PeritoDTO perito,SeguroDTO seguro) throws Exception;

	public PeritoSeguroDTO buscarPeritoSeguro(Long id) throws Exception;
	public PeritoSeguroDTO buscarPeritoSeguro(PeritoDTO perito,SeguroDTO seguro) throws Exception;
}