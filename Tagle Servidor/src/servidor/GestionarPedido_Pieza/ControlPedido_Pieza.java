package servidor.GestionarPedido_Pieza;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.jdo.Extent;
import javax.jdo.Query;

import servidor.assembler.BdgAssembler;
import servidor.assembler.Devolucion_PiezaAssembler;
import servidor.assembler.Mano_ObraAssembler;
import servidor.assembler.MuletoAssembler;
import servidor.assembler.PedidoAssembler;
import servidor.assembler.Pedido_PiezaAssembler;
import servidor.assembler.PiezaAssembler;
import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Agente;
import servidor.persistencia.dominio.Entidad;
import servidor.persistencia.dominio.Pedido_Pieza;
import servidor.persistencia.dominio.Reclamo;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;

public class ControlPedido_Pieza extends UnicastRemoteObject implements
		IControlPedido_Pieza {

	private static final long serialVersionUID = 1L;

	public ControlPedido_Pieza() throws RemoteException {
		super();
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
			
			pedido_Pieza.setNumero_pedido(modificado.getNumero_pedido());
			if(modificado.getFecha_solicitud_fabrica()!=null)
				pedido_Pieza.setFecha_solicitud_fabrica(modificado.getFecha_solicitud_fabrica());
			if(modificado.getFecha_recepcion_fabrica()!=null)
				pedido_Pieza.setFecha_recepcion_fabrica(modificado.getFecha_recepcion_fabrica());
			
			pedido_Pieza.setPnc(modificado.getPnc());
			MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
			if(modificado.getMuleto()!=null)
				pedido_Pieza.setMuleto(muletoAssemb.getMuleto(modificado.getMuleto()));

			Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
			if(modificado.getDevolucion_pieza()!=null)
				pedido_Pieza.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_Pieza(modificado.getDevolucion_pieza()));
			
			pedido_Pieza.setEstado_pedido(modificado.getEstado_pedido());
			
			BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
			if(modificado.getBdg()!=null)
				pedido_Pieza.setBdg(bdgAssemb.getBdg(modificado.getBdg()));
			
			Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
			if(modificado.getMano_obra()!=null)
				pedido_Pieza.setMano_obra(mano_ObraAssemb.getMano_Obra(modificado.getMano_obra()));
			
			if(modificado.getStock()!=null)
				pedido_Pieza.setStock(modificado.getStock());
			if(modificado.getPropio()!=null)
				pedido_Pieza.setPropio(modificado.getPropio());
			
			if(modificado.getFecha_envio_agente()!=null)
				pedido_Pieza.setFecha_envio_agente(modificado.getFecha_envio_agente());
			if(modificado.getFecha_recepcion_agente()!=null)
				pedido_Pieza.setFecha_recepcion_agente(modificado.getFecha_recepcion_agente());
			
			if(modificado.getPieza_usada()!=null)
				pedido_Pieza.setPieza_usada(modificado.getPieza_usada());

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
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

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

	@Override
	public boolean existePedido_Pieza(String numero_pedido) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "numero_pedido.equals(\""+numero_pedido+"\")";
			movCol = accesoBD.buscarPorFiltro(Pedido_Pieza.class, filtro);

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public Pedido_PiezaDTO buscarPedido_Pieza(String numero_pedido){
		AccesoBD accesoBD = new AccesoBD();
		Pedido_PiezaDTO pedido_PiezaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "numero_pedido.equals(\""+numero_pedido+"\")";
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
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSinTurno() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidades = (Collection) q1.execute();
			
			String filtro = "fecha_recepcion_fabrica != null && devolucion_pieza == null && pedido.reclamo.fecha_turno == null && entidades.contains(pedido.reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection entidades");
			Collection c2 = (Collection) query.execute(entidades);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaContencion()
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidades = (Collection) q1.execute();
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -10); //el -3 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());

			String filtro = "fecha_recepcion_fabrica == null && pedido.reclamo.fecha_turno == null && entidades.contains(pedido.reclamo.registrante) && hoy>=pedido.reclamo.fecha_reclamo";
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection ; import java.sql.Date");
            query.declareParameters("Collection entidades, Date hoy");
			Collection c2 = (Collection) query.execute(entidades,hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoAgente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -7); //el -7 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());			
			
			String filtro = "fecha_envio_agente != null && fecha_recepcion_agente == null && agentes.contains(pedido.reclamo.registrante) && hoy>=fecha_envio_agente";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection ; import java.sql.Date");
            query.declareParameters("Collection agentes, Date hoy");
			Collection c2 = (Collection) query.execute(agentes,hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaReclamoFabrica() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
					
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -10); //el -10 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());			
								
			String filtro = "fecha_solicitud_fabrica != null && fecha_recepcion_fabrica == null && hoy>=fecha_solicitud_fabrica";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            query.declareImports("import java.sql.Date");
            query.declareParameters("Date hoy");
			Collection c2 = (Collection) query.execute(hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaSugerencia() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
					
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 
			calendar.add(Calendar.DATE, -15); //el -15 indica que se le restaran 3 dias
			
			java.util.Date fechaHoy = calendar.getTime();
			String fecha = format2.format(fechaHoy);
			
		    java.sql.Date hoy = new java.sql.Date(fechaHoy.getTime());			
								
			String filtro = "fecha_solicitud_fabrica != null && fecha_recepcion_fabrica == null && hoy>=fecha_solicitud_fabrica";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            query.declareImports("import java.sql.Date");
            query.declareParameters("Date hoy");
			Collection c2 = (Collection) query.execute(hoy);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaAgente() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Agente.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection agentes = (Collection) q1.execute();	
			
			String filtro = "agentes.contains(pedido.reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection agentes");
			Collection c2 = (Collection) query.execute(agentes);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

	@Override
	public Vector<Pedido_PiezaDTO> obtenerPedido_PiezaEntidad() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<Pedido_PiezaDTO> pedidos_PiezaDTO = new Vector<Pedido_PiezaDTO>();
		try {
			accesoBD.iniciarTransaccion();
				
			Extent e1 = accesoBD.getPersistencia().getExtent(Entidad.class, true);
			Query q1 = accesoBD.getPersistencia().newQuery(e1, "");
			Collection entidad = (Collection) q1.execute();	
			
			String filtro = "entidad.contains(pedido.reclamo.registrante)";       
						
			Extent clnCliente = accesoBD.getPersistencia().getExtent(Pedido_Pieza.class, false);
            Query query = accesoBD.getPersistencia().newQuery(clnCliente,filtro);
            query.setGrouping("pedido.id");
            
            query.declareImports("import java.util.Collection");
            query.declareParameters("Collection entidad");
			Collection c2 = (Collection) query.execute(entidad);

			Vector<Pedido_Pieza> pedidos_Pieza = new Vector<Pedido_Pieza> (c2);	
	
			for (int i = 0; i < pedidos_Pieza.size(); i++) {
				
				Pedido_PiezaDTO pedido_PiezaDTO = new Pedido_PiezaDTO();

				pedido_PiezaDTO.setId(pedidos_Pieza.elementAt(i).getId());
				
				PedidoAssembler pedidoAssemb = new PedidoAssembler(accesoBD);
				pedido_PiezaDTO.setPedido(pedidoAssemb.getPedidoDTO(pedidos_Pieza.elementAt(i).getPedido()));
				
				PiezaAssembler piezaAssemb = new PiezaAssembler(accesoBD);
				pedido_PiezaDTO.setPieza(piezaAssemb.getPiezaDTO(pedidos_Pieza.elementAt(i).getPieza()));
				
				pedido_PiezaDTO.setNumero_pedido(pedidos_Pieza.elementAt(i).getNumero_pedido());
				if(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica()!=null)
					pedido_PiezaDTO.setFecha_solicitud_fabrica(pedidos_Pieza.elementAt(i).getFecha_solicitud_fabrica());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica()!=null)
					pedido_PiezaDTO.setFecha_recepcion_fabrica(pedidos_Pieza.elementAt(i).getFecha_recepcion_fabrica());
				
				pedido_PiezaDTO.setPnc(pedidos_Pieza.elementAt(i).getPnc());
				MuletoAssembler muletoAssemb = new  MuletoAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMuleto()!=null)
					pedido_PiezaDTO.setMuleto(muletoAssemb.getMuletoDTO(pedidos_Pieza.elementAt(i).getMuleto()));

				Devolucion_PiezaAssembler devolucion_PiezaAssemb = new Devolucion_PiezaAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getDevolucion_pieza()!=null)
					pedido_PiezaDTO.setDevolucion_pieza(devolucion_PiezaAssemb.getDevolucion_PiezaDTO(pedidos_Pieza.elementAt(i).getDevolucion_pieza()));
				
				pedido_PiezaDTO.setEstado_pedido(pedidos_Pieza.elementAt(i).getEstado_pedido());
				
				BdgAssembler bdgAssemb = new BdgAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getBdg()!=null)
					pedido_PiezaDTO.setBdg(bdgAssemb.getBdgDTO(pedidos_Pieza.elementAt(i).getBdg()));
				
				Mano_ObraAssembler mano_ObraAssemb = new Mano_ObraAssembler(accesoBD);
				if(pedidos_Pieza.elementAt(i).getMano_obra()!=null)
					pedido_PiezaDTO.setMano_obra(mano_ObraAssemb.getMano_ObraDTO(pedidos_Pieza.elementAt(i).getMano_obra()));
				
				if(pedidos_Pieza.elementAt(i).getStock()!=null)
					pedido_PiezaDTO.setStock(pedidos_Pieza.elementAt(i).getStock());
				if(pedidos_Pieza.elementAt(i).getPropio()!=null)
					pedido_PiezaDTO.setPropio(pedidos_Pieza.elementAt(i).getPropio());
				
				if(pedidos_Pieza.elementAt(i).getFecha_envio_agente()!=null)
					pedido_PiezaDTO.setFecha_envio_agente(pedidos_Pieza.elementAt(i).getFecha_envio_agente());
				if(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente()!=null)
					pedido_PiezaDTO.setFecha_recepcion_agente(pedidos_Pieza.elementAt(i).getFecha_recepcion_agente());
				
				if(pedidos_Pieza.elementAt(i).getPieza_usada()!=null)
					pedido_PiezaDTO.setPieza_usada(pedidos_Pieza.elementAt(i).getPieza_usada());

				pedidos_PiezaDTO.add(pedido_PiezaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return pedidos_PiezaDTO;
	}

}
