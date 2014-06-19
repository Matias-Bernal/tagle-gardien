package common.DTOs;

import java.sql.Date;

public class RecursoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected Date fecha_recurso;
	protected String numero_recurso;
	protected Pedido_PiezaDTO pedido_pieza;

	public RecursoDTO(){}
	public RecursoDTO(Date fecha_recurso, String numero_recurso, Pedido_PiezaDTO pedido_pieza) {
		super();
		this.fecha_recurso = fecha_recurso;
		this.numero_recurso = numero_recurso;
		this.pedido_pieza = pedido_pieza;
	}
	public Date getFecha_recurso() {
		return fecha_recurso;
	}
	public void setFecha_recurso(Date fecha_recurso) {
		this.fecha_recurso = fecha_recurso;
	}
	public String getNumero_recurso() {
		return numero_recurso;
	}
	public void setNumero_recurso(String numero_recurso) {
		this.numero_recurso = numero_recurso;
	}
	public Pedido_PiezaDTO getPedido_pieza() {
		return pedido_pieza;
	}
	public void setPedido_pieza(Pedido_PiezaDTO pedido_pieza) {
		this.pedido_pieza = pedido_pieza;
	}

}