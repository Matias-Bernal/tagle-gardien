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
package comun.DTOs;

import java.io.Serializable;

public class PeritoSeguroDTO implements Serializable{
	
	protected static final long serialVersionUID = 1L;
	protected Long id;
	protected PeritoDTO perito;
	protected SeguroDTO seguro;
	
	public PeritoSeguroDTO() {
		super();
	}	
	
	public PeritoSeguroDTO(Long id, PeritoDTO perito, SeguroDTO seguro) {
		super();
		this.id = id;
		this.perito = perito;
		this.seguro = seguro;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PeritoDTO getPerito() {
		return perito;
	}
	public void setPerito(PeritoDTO perito) {
		this.perito = perito;
	}
	public SeguroDTO getSeguro() {
		return seguro;
	}
	public void setSeguro(SeguroDTO seguro) {
		this.seguro = seguro;
	}

}
