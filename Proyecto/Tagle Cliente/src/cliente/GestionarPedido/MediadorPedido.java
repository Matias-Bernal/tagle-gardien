package cliente.GestionarPedido;

import java.util.Vector;

import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.RegistranteDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarPedido.IControlPedido;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

public class MediadorPedido {

	private GUIAltaPedidoAgente guiAltaPedidoAgente;
	private GUIAltaPedidoEntidad guiAltaPedidoEntidad;
	
	private GUIGestionPedidoAgente guiGestionPedidoAgente;
	private GUIGestionPedidoEntidad guiGestionPedidoEntidad;
	
	private GUIModificarPedidoAgente guiModificarPedidoAgente;
	private GUIModificarPedidoEntidad guiModificarPedidoEntidad;
	
	private MediadorPrincipal mediadorPrincipal;
	
	public MediadorPedido(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaPedidoEntidad(String nombre_registrante, String tipo) {
		guiAltaPedidoEntidad = new GUIAltaPedidoEntidad(this); // faltan datos
		guiAltaPedidoEntidad.setVisible(true);		
	}
	public void altaPedidoAgente(String nombre_registrante, String tipo) {
		guiAltaPedidoAgente = new GUIAltaPedidoAgente(this);
		guiAltaPedidoAgente.setVisible(true);	
	}

	public void altaPedidoEntidad() {
		guiAltaPedidoEntidad = new GUIAltaPedidoEntidad(this);
		guiAltaPedidoEntidad.setVisible(true);		
	}
	public void altaPedidoAgente() {
		guiAltaPedidoAgente = new GUIAltaPedidoAgente(this);
		guiAltaPedidoAgente.setVisible(true);		
	}

	public void gestionarPedidoEntidad() {
		guiGestionPedidoEntidad = new GUIGestionPedidoEntidad(this);
		guiGestionPedidoEntidad.setVisible(true);	
	}
	public void gestionarPedidoAgente() {
		guiGestionPedidoAgente = new GUIGestionPedidoAgente(this);
		guiGestionPedidoAgente.setVisible(true);
	}

	
	public Vector<PedidoDTO> obtenerPedidos() {
		Vector<PedidoDTO> pedido = new Vector<PedidoDTO>();
		IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
		try {
			pedido = iControlPedido.obtenerPedidos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido;
	}

	public boolean esAgente(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			res = iControlAgente.existeAgente(registrante.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Vector<Pedido_PiezaDTO> buscarPedidoPieza(Long id_pedido) {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
			IControlPedido iControlPedido = MediadorAccionesIniciarPrograma.getControlPedido();
			PedidoDTO pedido;
			pedido = iControlPedido.buscarPedido(id_pedido);
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_Pieza(pedido);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_piezas;
	}

	
}
