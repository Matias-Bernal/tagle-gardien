package servidor.persistencia.dominio;

public class Estado_Pedido {

	protected String estado_pedido;
	protected Long id;

	public Estado_Pedido() {
	}

	public Estado_Pedido(String estado_pedido) {
		super();
		this.estado_pedido = estado_pedido;
	}

	public String getEstado_pedido() {
		return estado_pedido;
	}

	public void setEstado_pedido(String estado_pedido) {
		this.estado_pedido = estado_pedido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
