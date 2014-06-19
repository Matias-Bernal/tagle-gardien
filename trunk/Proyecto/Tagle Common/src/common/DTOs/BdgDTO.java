package common.DTOs;

import java.sql.Date;

public class BdgDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected Date fecha_bdg;
	protected PiezaDTO pieza;
	protected PedidoDTO pedido;
	
	public BdgDTO() {}
	public BdgDTO(Date fecha_bdg, PiezaDTO pieza, PedidoDTO pedido) {
		super();
		this.fecha_bdg = fecha_bdg;
		this.pieza = pieza;
		this.pedido = pedido;
	}
	public Date getFecha_bdg() {
		return fecha_bdg;
	}
	public void setFecha_bdg(Date fecha_bdg) {
		this.fecha_bdg = fecha_bdg;
	}
	public PiezaDTO getPieza() {
		return pieza;
	}
	public void setPieza(PiezaDTO pieza) {
		this.pieza = pieza;
	}
	public PedidoDTO getPedido() {
		return pedido;
	}
	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

}
