package common.GestionarPedido_Pieza;

import java.rmi.Remote;
import java.sql.Date;
import java.util.Vector;

import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;

public interface IControlPedido_Pieza extends Remote{

	public Long agregarPedido_Pieza(Pedido_PiezaDTO pedido_PiezaDTO)throws Exception;
	public void eliminarPedido_Pieza(Long id)throws Exception;
	public void modificarPedido_Pieza(Long id,Pedido_PiezaDTO modificado)throws Exception;
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza()throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaAgente()throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad()throws Exception;
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(Date fecha_estado)throws Exception;
	
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSinTurno() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaContencion() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoAgente() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoFabrica() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSugerencia() throws Exception;
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(PedidoDTO pedidoDTO)throws Exception;

	public boolean existePedido_Pieza(Long id) throws Exception;
	public boolean existePedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO) throws Exception;
	public boolean existePedido_Pieza(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	public boolean existePedido_Pieza(String numero_pedido)throws Exception;

	public Pedido_PiezaDTO buscarPedido_Pieza(Long id) throws Exception;
	public Pedido_PiezaDTO buscarPedido_Pieza(PedidoDTO pedidoDTO, PiezaDTO piezaDTO) throws Exception;
	public Pedido_PiezaDTO buscarPedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO)throws Exception;
	public Pedido_PiezaDTO buscarPedido_Pieza(String numero_pedido)throws Exception;

}
