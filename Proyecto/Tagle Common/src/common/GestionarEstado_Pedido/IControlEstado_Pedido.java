package common.GestionarEstado_Pedido;

import java.rmi.Remote;
import java.util.Vector;

import common.DTOs.Estado_PedidoDTO;

public interface IControlEstado_Pedido extends Remote{

	public Long agregarEstado_Pedido(Estado_PedidoDTO estado_pedidoDTO)throws Exception;
	public void eliminarEstado_Pedido(Long id)throws Exception;
	public void modificarEstado_Pedido(Long id,Estado_PedidoDTO modificado)throws Exception;
	
	public Vector<Estado_PedidoDTO> obtenerEstado_Pedido()throws Exception;
	public Vector<Estado_PedidoDTO> obtenerEstado_Pedido(String estado_pedido)throws Exception;
	
	public boolean existeEstado_Pedido(Long id) throws Exception;
	
	public Estado_PedidoDTO buscarEstado_Pedido(Long id) throws Exception;

}