package servidor.CargaDatos;

import java.rmi.RemoteException;
import java.util.Vector;

import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamoDTO;
import servidor.SingletonConexion;
import servidor.GestionarPedido_Pieza.ControlPedido_Pieza;
import servidor.GestionarReclamo.ControlReclamo;

public class TestClass {

	public static void main(String[] args) {
		try {
			SingletonConexion c = new SingletonConexion();
			ControlPedido_Pieza controlPedidoPieza = new ControlPedido_Pieza();
//			Vector<ReclamoDTO> reclamos = controlReclamo.obtenerReclamosAgentes();
//			System.out.println("cantidad de reclamos agentes: "+reclamos.size());
			Vector<Pedido_PiezaDTO> pedidos_piezas = controlPedidoPieza.obtenerPedido_PiezaSugerencia();
			System.out.println("cantidad de pedidos de entidades sin turno: "+pedidos_piezas.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
