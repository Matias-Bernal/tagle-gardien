package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Estado_Pedido;

import common.DTOs.Estado_PedidoDTO;

public class Estado_PedidoAssembler {
	
	private AccesoBD accesoBD;

	public Estado_PedidoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public Estado_PedidoDTO getEstado_PedidoDTO(Estado_Pedido estado_pedido) {
		Estado_PedidoDTO estado_pedidoDTO = new Estado_PedidoDTO();
		estado_pedidoDTO.setId(estado_pedido.getId());
		estado_pedidoDTO.setEstado_pedido(estado_pedido.getEstado_pedido());
		return estado_pedidoDTO;
	}

	public Estado_Pedido getEstado_Pedido(Estado_PedidoDTO estado_pedidoDTO) {
		Estado_Pedido estado_pedido = (Estado_Pedido) accesoBD.buscarPorId(Estado_Pedido.class, estado_pedidoDTO.getId());
		estado_pedido.setId(estado_pedidoDTO.getId());
		estado_pedido.setEstado_pedido(estado_pedidoDTO.getEstado_pedido());
		return estado_pedido;
	}

	public Estado_Pedido getEstado_PedidoNuevo(Estado_PedidoDTO estado_pedidoDTO) {
		Estado_Pedido estado_pedido = new Estado_Pedido();
		estado_pedido.setId(estado_pedidoDTO.getId());
		estado_pedido.setEstado_pedido(estado_pedidoDTO.getEstado_pedido());
		return estado_pedido;
	}

}