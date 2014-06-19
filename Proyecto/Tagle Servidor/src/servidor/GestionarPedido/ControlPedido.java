package servidor.GestionarPedido;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.util.Collection;
import java.util.Vector;

import servidor.assembler.PedidoAssembler;
import servidor.assembler.ReclamoAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Pedido;

import common.DTOs.PedidoDTO;
import common.DTOs.ReclamoDTO;
import common.GestionarPedido.IControlPedido;

public class ControlPedido extends UnicastRemoteObject implements IControlPedido {

	private static final long serialVersionUID = 1L;

	public ControlPedido() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarPedido(PedidoDTO pedidoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			Pedido pedido = pedidoAssem.getPedidoNuevo(pedidoDTO);
			accesoBD.hacerPersistente(pedido);
			id = pedido.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPedido(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			Pedido pedido = pedidoAssem.getPedidoNuevo(buscarPedido(id));
			accesoBD.eliminar(pedido);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPedido(Long id, PedidoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			Pedido pedido = pedidoAssem.getPedidoNuevo(buscarPedido(id));
			
			pedido.setFecha_solicitud_pedido(modificado.getFecha_solicitud_pedido());
			pedido.setNumero_pedido(modificado.getNumero_pedido());
			ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
			pedido.setReclamo(reclamoAssemb.getReclamo(modificado.getReclamo()));

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Pedido> pedidos = new Vector<Pedido> (accesoBD.buscarPorFiltro(Pedido.class, ""));
			for (int i = 0; i < pedidos.size(); i++) {
				PedidoDTO pedidoDTO = new PedidoDTO();
				
				pedidoDTO.setId(pedidos.elementAt(i).getId());
				pedidoDTO.setFecha_solicitud_pedido(pedidos.elementAt(i).getFecha_solicitud_pedido());
				pedidoDTO.setNumero_pedido(pedidos.elementAt(i).getNumero_pedido());
				ReclamoAssembler reclamoAssemb = new ReclamoAssembler(accesoBD);
				pedidoDTO.setReclamo(reclamoAssemb.getReclamoDTO(pedidos.elementAt(i).getReclamo()));
				
				pedidosDTO.add(pedidoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidos_Fecha_Solicitud_Pedido(Date fecha_solicitud_pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol =  accesoBD.obtenerObjetosFecha(Pedido.class, fecha_solicitud_pedido.getYear(),fecha_solicitud_pedido.getMonth(), fecha_solicitud_pedido.getDay());
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidosDTO.add(pedidoAssem.getPedidoDTO((Pedido)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public Vector<PedidoDTO> obtenerPedidos(ReclamoDTO reclamoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PedidoDTO> pedidosDTO = new Vector<PedidoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "reclamo.id == "+reclamoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(Pedido.class, filtro);
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				pedidosDTO.add(pedidoAssem.getPedidoDTO((Pedido)(movCol.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidosDTO;
	}

	@Override
	public boolean existePedido(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Pedido) accesoBD.buscarPorId(Pedido.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePedido(String numero_pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection pedidos = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pedido.equals(\""+numero_pedido+"\")";
			pedidos = accesoBD.buscarPorFiltro(Pedido.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (pedidos!=null && pedidos.size()>=1);
	}

	@Override
	public PedidoDTO buscarPedido(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PedidoDTO pedidoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			pedidoDTO = pedidoAssem.getPedidoDTO((Pedido) accesoBD.buscarPorId(Pedido.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidoDTO;
	}

	@Override
	public PedidoDTO buscarPedido(String numero_pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PedidoDTO pedidoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pedido.equals(\""+numero_pedido+"\")";
			Collection pedidos = accesoBD.buscarPorFiltro(Pedido.class, filtro);
			PedidoAssembler pedidoAssem = new PedidoAssembler(accesoBD);
			if (pedidos.size()>=1 ) {
				pedidoDTO = pedidoAssem.getPedidoDTO((Pedido)(pedidos.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidoDTO;
	}

}