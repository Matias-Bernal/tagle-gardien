package cliente.GestionarDevoluviones;

import java.sql.Date;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.GestionarDevolucion_Pieza.IControlDevolucion_Pieza;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;

public class MediadorDevoluciones {
	
	private MediadorPrincipal mediadorPrincipal;
	
	private GUIAltaDevoluciones guiAltaDevolucion;
	private GUIGestionDevoluciones guiGestionDevoluciones;
	private GUIModificarDevoluciones guiModificarDevoluciones;
	
	private GUIBuscarPedido_Pieza guiBuscarPedidoPieza;

	public MediadorDevoluciones(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaDevolucion() {
		guiAltaDevolucion = new GUIAltaDevoluciones(this);
		guiAltaDevolucion.setVisible(true);
	}

	public void gestionDevolucion() {
		guiGestionDevoluciones = new GUIGestionDevoluciones(this);
		guiGestionDevoluciones.setVisible(true);	
	}

	public void altaDevolucion(String remito, String transporte, String retiro) {
		guiAltaDevolucion = new GUIAltaDevoluciones(this, remito, transporte, retiro);
		guiAltaDevolucion.setVisible(true);
	}
	
	public void buscarPedidoPieza(){
		guiBuscarPedidoPieza = new GUIBuscarPedido_Pieza(this);
		guiBuscarPedidoPieza.setVisible(true);
	}

	public void actualizarDatosGestion() {
		if(guiGestionDevoluciones!=null)
			guiGestionDevoluciones.actualizarDatos();
	}

	public boolean nuevaDevolucion(Pedido_PiezaDTO pedido_pieza, Date sqlDate, String numero_remito, String transporte, String numero_retiro) {
		boolean res = false;
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlDevolucion_Pieza iControlDevolucion_Pieza = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();
				
		try {
			
			Pedido_PiezaDTO pedido_piezaDTO = iControlPedido_Pieza.buscarPedido_Pieza(pedido_pieza.getId());
			
			Devolucion_PiezaDTO devolucion = new Devolucion_PiezaDTO(numero_remito, sqlDate, transporte, numero_retiro);
			devolucion.setId(iControlDevolucion_Pieza.agregarDevolucion_Pieza(devolucion));
			
			pedido_piezaDTO.setDevolucion_pieza(devolucion);
			pedido_piezaDTO.setEstado_pedido("CERRADO");
			
			iControlPedido_Pieza.modificarPedido_Pieza(pedido_piezaDTO.getId(),pedido_piezaDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedido_Piezas() {
		Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
		try {
			IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
			pedidos_piezas = iControlPedido_Pieza.obtenerPedido_Pieza();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedidos_piezas;
	}

	public void modificarDevolucion(Long id) {
		guiModificarDevoluciones = new GUIModificarDevoluciones(this);
		guiModificarDevoluciones.setVisible(true);
	}

	public void eliminarDevolucion(Long id) {
		try {
			IControlDevolucion_Pieza iControlDevolucion_Pieza = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();
			iControlDevolucion_Pieza.eliminarDevolucion_Pieza(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vector<Devolucion_PiezaDTO> obtenerDevoluciones() {
		Vector<Devolucion_PiezaDTO> devoluciones = new Vector<Devolucion_PiezaDTO>();
		try {
			IControlDevolucion_Pieza iControlDevolucion_Pieza = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();
			devoluciones = iControlDevolucion_Pieza.obtenerDevolucion_Pieza();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return devoluciones;
	}

	public Pedido_PiezaDTO obtenerPedido_Pieza(Long id) {
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		IControlDevolucion_Pieza iControlDevolucion_Pieza = MediadorAccionesIniciarPrograma.getControlDevolucion_Pieza();
		Pedido_PiezaDTO pedido_pieza = null;
		Devolucion_PiezaDTO devolucion_pieza;
		try {
			devolucion_pieza = iControlDevolucion_Pieza.buscarDevolucion_Pieza(id);
			pedido_pieza = iControlPedido_Pieza.buscarPedido_Pieza(devolucion_pieza);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pedido_pieza;
	}

	public void setPedidoPieza(String id_pedido_pieza) {
		
	}
}
