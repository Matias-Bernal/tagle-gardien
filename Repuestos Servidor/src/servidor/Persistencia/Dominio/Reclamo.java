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

import java.sql.Date;

public class Reclamo {
			
	protected static final long serialVersionUID = 1L;
	protected Long id;
	protected Date fecha_reclamo;
	protected String descripcion;
	protected Solicitud solicitud;
	protected UsuarioRepuesto usuario_repuesto;
	
	public Reclamo(){}
			
	public Reclamo(Long id, Date fecha_reclamo, String descripcion, Solicitud solicitud, UsuarioRepuesto usuario_repuesto) {
		this.id = id;
		this.fecha_reclamo = fecha_reclamo;
		this.descripcion = descripcion;
		this.solicitud = solicitud;
		this.usuario_repuesto = usuario_repuesto;
	}
	
	public Date getFecha_reclamo() {
		return fecha_reclamo;
	}
	public void setFecha_reclamo(Date fecha_reclamo) {
		this.fecha_reclamo = fecha_reclamo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Solicitud getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}
	public UsuarioRepuesto getUsuario_repuesto() {
		return usuario_repuesto;
	}
	public void setUsuario_repuesto(UsuarioRepuesto usuario_repuesto) {
		this.usuario_repuesto = usuario_repuesto;
	}
}
