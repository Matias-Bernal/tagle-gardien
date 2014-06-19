package servidor.GestionarPedido_Pieza;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.BdgAssembler;
import servidor.assembler.Devolucion_PiezaAssembler;
import servidor.assembler.Estado_PedidoAssembler;
import servidor.assembler.Mano_ObraAssembler;
import servidor.assembler.MuletoAssembler;
import servidor.assembler.PedidoAssembler;
import servidor.assembler.Pedido_PiezaAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido_Pieza;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.Estado_PedidoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;

public class ControlPedido_Pieza extends UnicastRemoteObject implements
		IControlPedido_Pieza {

	private static final long serialVersionUID = 1L;

	public ControlPedido_Pieza() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarPedido_Pieza(Pedido_PiezaDTO pedido_PiezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			Pedido_Pieza pedido_Pieza = pedido_piezaAssemb.getPedido_PiezaNuevo(pedido_PiezaDTO);
			accesoBD.hacerPersistente(pedido_Pieza);
			id = pedido_Pieza.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPedido_Pieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			Pedido_Pieza pedido_Pieza = pedido_piezaAssemb.getPedido_Pieza(buscarPedido_Pieza(id));
			accesoBD.eliminar(pedido_Pieza);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPedido_Pieza(Long id, Pedido_PiezaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			Pedido_Pieza pedido_Pieza = pedido_piezaAssemb.getPedido_Pieza(buscarPedido_Pieza(id));
			
			PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
			pedido_Pieza.setPedido(pedidoAssemb.getPedido(modificado.getPedido()));
			
			PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
			pedido_Pieza.setPieza(piezaAssemb.getPieza(modificado.getPieza()));
			
			pedido_Pieza.setStock(modificado.getStock());
			pedido_Pieza.setPropio(modificado.getPropio());
			
			MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
			pedido_Pieza.setMuleto(muletoAssemb.getMuleto(modificado.getMuleto()));
			
			Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
			pedido_Pieza.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_Pieza(modificado.getDevolucion_pieza()));
			
			//Estado_PedidoAssembler estado_PedidoAssemb = new Estado_PedidoAssembler(accesoBD);
			pedido_Pieza.setEstado_Pedido(modificado.getEstado_pedido());
			
			//pedido_Pieza.setFecha_estado(modificado.getFecha_estado());
			
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			pedido_Pieza.setBdg(bdgAssemb.getBdg(modificado.getBdg()));
			
			Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
			pedido_Pieza.setMano_obra(mano_ObraAssemb.getMano_Obra(modificado.getMano_obra()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (accesoBD.buscarPorFiltro(Pedido_Pieza.class, ""));
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));
				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				//Estado_PedidoAssembler estado_PedidoAssemb = new Estado_PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_Pedido());
				
				//pedido_PiezaDTO.setFecha_estado(pedidos_Pieza.elementAt(i).getFecha_estado());
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));

				pedidos_PiezaDTO.add(pedido_PiezaDTO);

			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(Date fecha_estado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol =  accesoBD.obtenerObjetosFecha(Pedido_Pieza.class, fecha_estado.getYear(),fecha_estado.getMonth(), fecha_estado.getDay());
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_PiezaDTO.add(pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

//	@Override
//	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(Estado_PedidoDTO estado_pedidoDTO) throws Exception {
//		AccesoBD accesoBD = new AccesoBD();
//		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
//		try {
//			accesoBD.iniciarTransaccion();
//			String filtro = "estado_pedido.equals(\""+estado_pedidoDTO+"\")";
//			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
//			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
//			for (int i = 0; i < movCol.size(); i++) {
//				pedidos_PiezaDTO.add(pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray())[i]));
//			}
//			accesoBD.concretarTransaccion();
//		} catch (Exception e) {
//			accesoBD.rollbackTransaccion();
//		}
//		return pedidos_PiezaDTO;
//	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_Pieza(PedidoDTO pedidoDTO)	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "pedido.id == "+pedidoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidos_PiezaDTO.add(pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public boolean existePedido_Pieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pedido_Pieza) accesoBD.buscarPorId(Pedido_Pieza.class,id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePedido_Pieza(PedidoDTO pedidoDTO, PiezaDTO piezaDTO)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pedido.id == "+pedidoDTO.getId()+ " && pieza.id == "+piezaDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();

			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza) accesoBD.buscarPorId(Pedido_Pieza.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(PedidoDTO pedidoDTO,PiezaDTO piezaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "pedido.id == "+pedidoDTO.getId()+ " && pieza.id == "+piezaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}

	@Override
	public boolean existePedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO)throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "devolucion_pieza.id == "+devolucion_piezaDTO.getId();
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(Devolucion_PiezaDTO devolucion_piezaDTO) throws Exception{
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "devolucion_pieza.id == "+devolucion_piezaDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);
			Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				pedido_PiezaDTO = pedido_piezaAssemb.getPedido_PiezaDTO((Pedido_Pieza)(movCol.toArray()[0]));
			}
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedido_PiezaDTO;
	}

}
