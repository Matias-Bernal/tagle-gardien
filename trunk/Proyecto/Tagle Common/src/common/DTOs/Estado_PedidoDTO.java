package common.DTOs;

public class Estado_PedidoDTO extends ClaseGeneralDTO {

	protected static final long serialVersionUID = 1L;
	protected String estado_pedido;

	public Estado_PedidoDTO(){}
	public Estado_PedidoDTO(String estado_pedido) {
		super();
		this.estado_pedido = estado_pedido;
	}
	public String getEstado_pedido() {
		return estado_pedido;
	}
	public void setEstado_pedido(String estado_pedido) {
		this.estado_pedido = estado_pedido;
	}

}
