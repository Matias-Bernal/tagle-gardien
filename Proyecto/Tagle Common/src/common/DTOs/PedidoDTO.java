package common.DTOs;

import java.sql.Date;

public class PedidoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String numero_pedido;
	protected Date fecha_solicitud_pedido;
	protected ReclamoDTO reclamo;

	public PedidoDTO() {}
	public PedidoDTO(String numero_pedido, Date fecha_solicitud_pedido,ReclamoDTO reclamo) {
		super();
		this.numero_pedido = numero_pedido;
		this.fecha_solicitud_pedido = fecha_solicitud_pedido;
		this.reclamo = reclamo;
	}
	public String getNumero_pedido() {
		return numero_pedido;
	}
	public void setNumero_pedido(String numero_pedido) {
		this.numero_pedido = numero_pedido;
	}
	public Date getFecha_solicitud_pedido() {
		return fecha_solicitud_pedido;
	}
	public void setFecha_solicitud_pedido(Date fecha_solicitud_pedido) {
		this.fecha_solicitud_pedido = fecha_solicitud_pedido;
	}
	public ReclamoDTO getReclamo() {
		return reclamo;
	}
	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
	}

}
