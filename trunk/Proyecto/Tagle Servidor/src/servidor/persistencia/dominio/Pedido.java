package servidor.persistencia.dominio;

import java.sql.Date;

public class Pedido {

	protected Long id;
	protected String numero_pedido;
	protected Date fecha_solicitud_pedido;
	protected Reclamo reclamo;

	public Pedido() {
	}

	public Pedido(String numero_pedido, Date fecha_solicitud_pedido, Reclamo reclamo) {
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

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}