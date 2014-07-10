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

public class PeritoDTO implements Serializable{

	protected static final long serialVersionUID = 1L;
	protected Long id;
	protected String nombre;
	protected String telefono;
	protected String mail;
	
	public PeritoDTO(){
		super();
	}
	
	public PeritoDTO(Long id, String nombre, String telefono, String mail) {
		super();
		this.id=id;
		this.nombre=nombre;
		this.telefono = telefono;
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}