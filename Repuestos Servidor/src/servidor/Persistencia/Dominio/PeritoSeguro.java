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
package servidor.Persistencia.Dominio;


public class PeritoSeguro {

	protected Long id;
	protected Perito perito;
	protected Seguro seguro;
	
	public PeritoSeguro() {}	
	
	public PeritoSeguro(Long id, Perito perito, Seguro seguro) {
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
	public Perito getPerito() {
		return perito;
	}
	public void setPerito(Perito perito) {
		this.perito = perito;
	}
	public Seguro getSeguro() {
		return seguro;
	}
	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

}
