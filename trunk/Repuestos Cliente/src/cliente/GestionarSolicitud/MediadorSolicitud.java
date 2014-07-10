package cliente.GestionarSolicitud;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MenuPrincipal.MediadorPrincipal;
import comun.DTOs.AlternativoDTO;
import comun.DTOs.CargoDTO;
import comun.DTOs.CarroceriaDTO;
import comun.DTOs.FabricaDTO;
import comun.DTOs.GarantiaDTO;
import comun.DTOs.MayoristaDTO;
import comun.DTOs.MecanicoDTO;
import comun.DTOs.MostradorDTO;
import comun.DTOs.PeritoDTO;
import comun.DTOs.PeritoSeguroDTO;
import comun.DTOs.PiezaDTO;
import comun.DTOs.ProveedorDTO;
import comun.DTOs.SeguroDTO;
import comun.DTOs.SolicitanteDTO;
import comun.DTOs.SolicitudDTO;
import comun.DTOs.SucursalDTO;
import comun.GestionarAlternativo.IControlAlternativo;
import comun.GestionarCargo.IControlCargo;
import comun.GestionarCarroceria.IControlCarroceria;
import comun.GestionarFabrica.IControlFabrica;
import comun.GestionarGarantia.IControlGarantia;
import comun.GestionarMayorista.IControlMayorista;
import comun.GestionarMecanico.IControlMecanico;
import comun.GestionarMostrador.IControlMostrador;
import comun.GestionarPerito.IControlPerito;
import comun.GestionarPeritoSeguro.IControlPeritoSeguro;
import comun.GestionarPieza.IControlPieza;
import comun.GestionarProveedor.IControlProveedor;
import comun.GestionarSeguro.IControlSeguro;
import comun.GestionarSolicitante.IControlSolicitante;
import comun.GestionarSolicitud.IControlSolicitud;
import comun.GestionarSucursal.IControlSucursal;

public class MediadorSolicitud {
	
	protected GUIAltaSolicitud guiAltaSolicitud;
	protected GUIGestionSolicitud guiGestionSolicitud;
	protected GUIModificarSolicitud guiModificarSolicitud;
	protected MediadorPrincipal mediadorPrincipal;

	public MediadorSolicitud (MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void altaSolicitud(){
		guiAltaSolicitud = new GUIAltaSolicitud(this);
		guiAltaSolicitud.setVisible(true);
	}

	public void gestionarSolicitud() {
		guiGestionSolicitud = new GUIGestionSolicitud(this);
		guiGestionSolicitud.setVisible(true);
		
	}

	public void nuevoSolicitante() {
		mediadorPrincipal.altaSolicitante();
	}

	public void nuevoProveedor() {
		mediadorPrincipal.altaProveedor();		
	}

	public Vector<String> obtenerMostrador() {
		IControlMostrador iControlMostrador = MediadorAccionesIniciarPrograma.getControlMostrador();
		Vector<String> mostrador = new Vector<String> ();
		try {
			Iterator<MostradorDTO> iterator = iControlMostrador.obtenerMostradors().iterator();
            while (iterator.hasNext()){
            	MostradorDTO element = iterator.next();
            	mostrador.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar mostrador");
			e.printStackTrace();
		}
		return mostrador;
	}

	public Vector<String> obtenerMayorista() {
		IControlMayorista iControlMayorista = MediadorAccionesIniciarPrograma.getControlMayorista();
		Vector<String> mayorista = new Vector<String> ();
		try {
			Iterator<MayoristaDTO> iterator = iControlMayorista.obtenerMayoristas().iterator();
            while (iterator.hasNext()){
            	MayoristaDTO element = iterator.next();
            	mayorista.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar mayorista");
			e.printStackTrace();
		}
		return mayorista;
	}

	public Vector<String> obtenerGarantia() {
		IControlGarantia iControlGarantia = MediadorAccionesIniciarPrograma.getControlGarantia();
		Vector<String> garantia = new Vector<String> ();
		try {
			Iterator<GarantiaDTO> iterator = iControlGarantia.obtenerGarantias().iterator();
            while (iterator.hasNext()){
            	GarantiaDTO element = iterator.next();
            	garantia.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar garantia");
			e.printStackTrace();
		}
		return garantia;
	}

	public Vector<String> obtenerSeguro() {
		IControlSeguro iControlSeguro = MediadorAccionesIniciarPrograma.getControlSeguro();
		Vector<String> seguro = new Vector<String> ();
		try {
			Iterator<SeguroDTO> iterator = iControlSeguro.obtenerSeguros().iterator();
            while (iterator.hasNext()){
            	SeguroDTO element = iterator.next();
            	seguro.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar seguro");
			e.printStackTrace();
		}
		return seguro;
	}

	public Vector<String> obtenerMecanico() {
		IControlMecanico iControlMecanico = MediadorAccionesIniciarPrograma.getControlMecanico();
		Vector<String> mecanico = new Vector<String> ();
		try {
			Iterator<MecanicoDTO> iterator = iControlMecanico.obtenerMecanicos().iterator();
            while (iterator.hasNext()){
            	MecanicoDTO element = iterator.next();
            	mecanico.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar mecanico");
			e.printStackTrace();
		}
		return mecanico;
	}

	public Vector<String> obtenerCarroceria() {
		IControlCarroceria iControlCarroceria = MediadorAccionesIniciarPrograma.getControlCarroceria();
		Vector<String> carroceria = new Vector<String> ();
		try {
			Iterator<CarroceriaDTO> iterator = iControlCarroceria.obtenerCarrocerias().iterator();
            while (iterator.hasNext()){
            	CarroceriaDTO element = iterator.next();
            	carroceria.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar carroceria");
			e.printStackTrace();
		}
		return carroceria;
	}

	public Vector<String> obtenerFabrica() {
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		Vector<String> fabrica = new Vector<String> ();
		try {
			Iterator<FabricaDTO> iterator = iControlFabrica.obtenerFabricas().iterator();
            while (iterator.hasNext()){
            	FabricaDTO element = iterator.next();
            	fabrica.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar fabrica");
			e.printStackTrace();
		}
		return fabrica;
	}

	public Vector<String> obtenerSucursal() {
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		Vector<String> sucursal = new Vector<String> ();
		try {
			Iterator<SucursalDTO> iterator = iControlSucursal.obtenerSucursals().iterator();
            while (iterator.hasNext()){
            	SucursalDTO element = iterator.next();
            	sucursal.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar sucursal");
			e.printStackTrace();
		}
		return sucursal;
	}

	public Vector<String> obtenerAlternativo() {
		IControlAlternativo iControlAlternativo = MediadorAccionesIniciarPrograma.getControlAlternativo();
		Vector<String> alternativo = new Vector<String> ();
		try {
			Iterator<AlternativoDTO> iterator = iControlAlternativo.obtenerAlternativos().iterator();
            while (iterator.hasNext()){
            	AlternativoDTO element = iterator.next();
            	alternativo.add(element.getNombre()+" [ID: "+element.getId()+" ]");
            }
		} catch (Exception e) {
			System.out.println("Error al cargar alternativo");
			e.printStackTrace();
		}
		return alternativo;
	}

	public Vector<String> obtenerCargos() {
		IControlCargo iControlCargo = MediadorAccionesIniciarPrograma.getControlCargo();
		Vector<String> cargo = new Vector<String> ();
		try {
			Iterator<CargoDTO> iterator = iControlCargo.obtenerCargos().iterator();
            while (iterator.hasNext()){
            	CargoDTO element = iterator.next();
            	cargo.add(element.getNombre());
            }
		} catch (Exception e) {
			System.out.println("Error al cargar alternativo");
			e.printStackTrace();
		}
		return cargo;
	}

	@SuppressWarnings("deprecation")
	public boolean nuevaSolicitudMostrador(String id_proveedor, String id_solicitante, String numero_pieza, String descripcion, String cantidad, Date fsProveedor, Date fsSolicitante, Date feLlegada, boolean stock_fabrica, boolean stock_propio, boolean bloqueada) {
		IControlProveedor iContolProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		IControlSolicitante iContolSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		try {
			ProveedorDTO proveedor = iContolProveedor.buscarProveedor(new Long(id_proveedor));
			SolicitanteDTO solicitante = iContolSolicitante.buscarSolicitante(new Long(id_solicitante));
			PiezaDTO pieza = new PiezaDTO(null, numero_pieza, descripcion);
			pieza.setId(iControlPieza.agregarPieza(pieza));
			int cantidad_ = Integer.parseInt(cantidad);
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fsProveedor.getTime());; //
			
			//calendar.set(fsProveedor.getYear(),fsProveedor.getMonth(),fsProveedor.getDay() );
			if(iControlSucursal.existeSucursal(proveedor.getNombre())){
				calendar.add(Calendar.DATE, +2); //el +2 indica que se le suman 4 dias
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}else{
				if(iControlFabrica.existeFabrica(proveedor.getNombre())){
					calendar.add(Calendar.DATE, +4);
					java.util.Date feLlegada_ = calendar.getTime();
					String fecha = format2.format(feLlegada_);
					feLlegada = new java.sql.Date(feLlegada_.getTime());
				}
			}			
			
			if(feLlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(feLlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			SolicitudDTO solicitud = new SolicitudDTO(null, fsSolicitante, fsProveedor,null, feLlegada, stock_propio, stock_fabrica, bloqueada, cantidad_, "", "", "", "ABIERTA", null, "", "", "", solicitante, mediadorPrincipal.getUsuario_repuesto(), pieza, proveedor, null);
			
			return (iControlSolicitud.agregarSolicitud(solicitud)!=null);
			
		} catch (Exception e) {
			System.out.println("Error al agregar solicitud mostrador");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean nuevaSolicitudMayorista(String id_proveedor, String id_solicitante, String numero_pieza, String descripcion, String cantidad, Date fsProveedor, Date fsSolicitante, Date feLlegada, boolean stock_fabrica, boolean stock_propio, boolean bloqueada) {
		IControlProveedor iContolProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		IControlSolicitante iContolSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		try {
			ProveedorDTO proveedor = iContolProveedor.buscarProveedor(new Long(id_proveedor));
			SolicitanteDTO solicitante = iContolSolicitante.buscarSolicitante(new Long(id_solicitante));
			PiezaDTO pieza = new PiezaDTO(null, numero_pieza, descripcion);
			pieza.setId(iControlPieza.agregarPieza(pieza));
			int cantidad_ = Integer.parseInt(cantidad);
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fsProveedor.getTime());; //
			
			//calendar.set(fsProveedor.getYear(),fsProveedor.getMonth(),fsProveedor.getDay() );
			if(iControlSucursal.existeSucursal(proveedor.getNombre())){
				calendar.add(Calendar.DATE, +2); //el +2 indica que se le suman 4 dias
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}else{
				if(iControlFabrica.existeFabrica(proveedor.getNombre())){
					calendar.add(Calendar.DATE, +4);
					java.util.Date feLlegada_ = calendar.getTime();
					String fecha = format2.format(feLlegada_);
					feLlegada = new java.sql.Date(feLlegada_.getTime());
				}
			}	
			if(feLlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(feLlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			SolicitudDTO solicitud = new SolicitudDTO(null, fsSolicitante, fsProveedor, null, feLlegada, stock_propio, stock_fabrica, bloqueada, cantidad_, "", "", "", "ABIERTA", null, "", "", "", solicitante, mediadorPrincipal.getUsuario_repuesto(), pieza, proveedor, null);
			
			return (iControlSolicitud.agregarSolicitud(solicitud)!=null);
			
		} catch (Exception e) {
			System.out.println("Error al agregar solicitud mayorista");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean nuevaSolicitudGarantia(String id_proveedor, String id_solicitante, String numero_pieza, String descripcion, String cantidad, Date fsProveedor, Date fsSolicitante, Date feLlegada, boolean stock_fabrica, boolean stock_propio, boolean bloqueada, String numero_pedido, String dominio, String numero_orden, String nombre_cargo, String pnc, String pcl) {
		IControlProveedor iContolProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		IControlSolicitante iContolSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		IControlCargo iControlCargo = MediadorAccionesIniciarPrograma.getControlCargo();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		try {
			ProveedorDTO proveedor = iContolProveedor.buscarProveedor(new Long(id_proveedor));
			SolicitanteDTO solicitante = iContolSolicitante.buscarSolicitante(new Long(id_solicitante));
			PiezaDTO pieza = new PiezaDTO(null, numero_pieza, descripcion);
			pieza.setId(iControlPieza.agregarPieza(pieza));
			int cantidad_ = Integer.parseInt(cantidad);
			
			CargoDTO cargo = iControlCargo.buscarCargo(nombre_cargo);
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fsProveedor.getTime());; //
			
			//calendar.set(fsProveedor.getYear(),fsProveedor.getMonth(),fsProveedor.getDay() );
			if(iControlSucursal.existeSucursal(proveedor.getNombre())){
				calendar.add(Calendar.DATE, +2); //el +2 indica que se le suman 4 dias
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}else{
				if(iControlFabrica.existeFabrica(proveedor.getNombre())){
					calendar.add(Calendar.DATE, +4);
					java.util.Date feLlegada_ = calendar.getTime();
					String fecha = format2.format(feLlegada_);
					feLlegada = new java.sql.Date(feLlegada_.getTime());
				}
			}	
			if(feLlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(feLlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			SolicitudDTO solicitud = new SolicitudDTO(null, fsSolicitante, fsProveedor,null, feLlegada, stock_propio, stock_fabrica, bloqueada, cantidad_, numero_orden, dominio, numero_pedido, "ABIERTA", cargo, "", pnc, pcl, solicitante, mediadorPrincipal.getUsuario_repuesto(), pieza, proveedor, null);
			
			return (iControlSolicitud.agregarSolicitud(solicitud)!=null);
			
		} catch (Exception e) {
			System.out.println("Error al agregar solicitud garantia");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean nuevaSolicitudSeguro(String id_proveedor, String id_solicitante, String numero_pieza, String descripcion, String cantidad, Date fsProveedor, Date fsSolicitante, Date feLlegada, boolean stock_fabrica, boolean stock_propio, boolean bloqueada, String numero_siniestro, String nombre_perito, String telefono_perito, String email_perito) {
		IControlProveedor iContolProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		IControlSolicitante iContolSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		IControlPerito iContolPerito = MediadorAccionesIniciarPrograma.getControlPerito();
		IControlPeritoSeguro iContolPeritoSeguro = MediadorAccionesIniciarPrograma.getControlPeritoSeguro();
		IControlSeguro iControlSeguro = MediadorAccionesIniciarPrograma.getControlSeguro();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		try {
			ProveedorDTO proveedor = iContolProveedor.buscarProveedor(new Long(id_proveedor));
			SolicitanteDTO solicitante = iContolSolicitante.buscarSolicitante(new Long(id_solicitante));
			PiezaDTO pieza = new PiezaDTO(null, numero_pieza, descripcion);
			pieza.setId(iControlPieza.agregarPieza(pieza));
			int cantidad_ = Integer.parseInt(cantidad);
						
			PeritoDTO perito = null;
			if(iContolPerito.existePerito(nombre_perito)){
				perito = iContolPerito.buscarPerito(nombre_perito);
			}else{
				perito = new PeritoDTO(null, nombre_perito, telefono_perito, email_perito);
				perito.setId(iContolPerito.agregarPerito(perito));
				
				SeguroDTO seguro = iControlSeguro.buscarSeguro(id_solicitante);
				PeritoSeguroDTO perito_seguro = new PeritoSeguroDTO(null, perito, seguro);
				iContolPeritoSeguro.agregarPeritoSeguro(perito_seguro);
			}
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fsProveedor.getTime());; //
			
			//calendar.set(fsProveedor.getYear(),fsProveedor.getMonth(),fsProveedor.getDay() );
			if(iControlSucursal.existeSucursal(proveedor.getNombre())){
				calendar.add(Calendar.DATE, +2); //el +2 indica que se le suman 4 dias
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}else{
				if(iControlFabrica.existeFabrica(proveedor.getNombre())){
					calendar.add(Calendar.DATE, +4);
					java.util.Date feLlegada_ = calendar.getTime();
					String fecha = format2.format(feLlegada_);
					feLlegada = new java.sql.Date(feLlegada_.getTime());
				}
			}	
			if(feLlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(feLlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			SolicitudDTO solicitud = new SolicitudDTO(null, fsSolicitante, fsProveedor,null, feLlegada, stock_propio, stock_fabrica, bloqueada, cantidad_, "", "", "", "ABIERTA", null, numero_siniestro, "", "", solicitante, mediadorPrincipal.getUsuario_repuesto(), pieza, proveedor, perito);
			
			return (iControlSolicitud.agregarSolicitud(solicitud)!=null);
			
		} catch (Exception e) {
			System.out.println("Error al agregar solicitud seguro");
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public boolean nuevaSolicitudMecanico(String id_proveedor, String id_solicitante, String numero_pieza, String descripcion, String cantidad, Date fsProveedor, Date fsSolicitante, Date feLlegada, boolean stock_fabrica, boolean stock_propio, boolean bloqueada, String numero_pedido, String dominio, String numero_orden, String nombre_cargo, String pnc, String pcl) {
		IControlProveedor iContolProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		IControlSolicitante iContolSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		IControlCargo iControlCargo = MediadorAccionesIniciarPrograma.getControlCargo();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		try {
			ProveedorDTO proveedor = iContolProveedor.buscarProveedor(new Long(id_proveedor));
			SolicitanteDTO solicitante = iContolSolicitante.buscarSolicitante(new Long(id_solicitante));
			PiezaDTO pieza = new PiezaDTO(null, numero_pieza, descripcion);
			pieza.setId(iControlPieza.agregarPieza(pieza));
			int cantidad_ = Integer.parseInt(cantidad);
			
			CargoDTO cargo = iControlCargo.buscarCargo(nombre_cargo);
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fsProveedor.getTime());; //
			
			//calendar.set(fsProveedor.getYear(),fsProveedor.getMonth(),fsProveedor.getDay() );
			if(iControlSucursal.existeSucursal(proveedor.getNombre())){
				calendar.add(Calendar.DATE, +2); //el +2 indica que se le suman 4 dias
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}else{
				if(iControlFabrica.existeFabrica(proveedor.getNombre())){
					calendar.add(Calendar.DATE, +4);
					java.util.Date feLlegada_ = calendar.getTime();
					String fecha = format2.format(feLlegada_);
					feLlegada = new java.sql.Date(feLlegada_.getTime());
				}
			}	
			if(feLlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(feLlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			SolicitudDTO solicitud = new SolicitudDTO(null, fsSolicitante, fsProveedor,null, feLlegada, stock_propio, stock_fabrica, bloqueada, cantidad_, numero_orden, dominio, numero_pedido, "ABIERTA", cargo, "", pnc, pcl, solicitante, mediadorPrincipal.getUsuario_repuesto(), pieza, proveedor, null);
			
			return (iControlSolicitud.agregarSolicitud(solicitud)!=null);
			
		} catch (Exception e) {
			System.out.println("Error al agregar solicitud mecanico");
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	public boolean nuevaSolicitudCarroceria(String id_proveedor, String id_solicitante, String numero_pieza, String descripcion, String cantidad, Date fsProveedor, Date fsSolicitante, Date feLlegada, boolean stock_fabrica, boolean stock_propio, boolean bloqueada, String numero_pedido, String dominio, String numero_orden, String nombre_cargo, String pnc, String pcl) {
		IControlProveedor iContolProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		IControlSolicitante iContolSolicitante = MediadorAccionesIniciarPrograma.getControlSolicitante();
		IControlPieza iControlPieza = MediadorAccionesIniciarPrograma.getControlPieza();
		IControlSolicitud iControlSolicitud = MediadorAccionesIniciarPrograma.getControlSolicitud();
		IControlCargo iControlCargo = MediadorAccionesIniciarPrograma.getControlCargo();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		try {
			ProveedorDTO proveedor = iContolProveedor.buscarProveedor(new Long(id_proveedor));
			SolicitanteDTO solicitante = iContolSolicitante.buscarSolicitante(new Long(id_solicitante));
			PiezaDTO pieza = new PiezaDTO(null, numero_pieza, descripcion);
			pieza.setId(iControlPieza.agregarPieza(pieza));
			int cantidad_ = Integer.parseInt(cantidad);
			
			CargoDTO cargo = iControlCargo.buscarCargo(nombre_cargo);
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance(); //obtiene la fecha de hoy
			
			calendar.setTimeInMillis(fsProveedor.getTime());; //
			
			//calendar.set(fsProveedor.getYear(),fsProveedor.getMonth(),fsProveedor.getDay() );
			if(iControlSucursal.existeSucursal(proveedor.getNombre())){
				calendar.add(Calendar.DATE, +2); //el +2 indica que se le suman 4 dias
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}else{
				if(iControlFabrica.existeFabrica(proveedor.getNombre())){
					calendar.add(Calendar.DATE, +4);
					java.util.Date feLlegada_ = calendar.getTime();
					String fecha = format2.format(feLlegada_);
					feLlegada = new java.sql.Date(feLlegada_.getTime());
				}
			}	
			if(feLlegada.getDay() == 0){
				calendar.add(Calendar.DATE, +1);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			if(feLlegada.getDay() == 6){
				calendar.add(Calendar.DATE, +2);
				java.util.Date feLlegada_ = calendar.getTime();
				String fecha = format2.format(feLlegada_);
				feLlegada = new java.sql.Date(feLlegada_.getTime());
			}
			SolicitudDTO solicitud = new SolicitudDTO(null, fsSolicitante, fsProveedor,null, feLlegada, stock_propio, stock_fabrica, bloqueada, cantidad_, numero_orden, dominio, numero_pedido, "ABIERTA", cargo, "", pnc, pcl, solicitante, mediadorPrincipal.getUsuario_repuesto(), pieza, proveedor, null);
			
			return (iControlSolicitud.agregarSolicitud(solicitud)!=null);
			
		} catch (Exception e) {
			System.out.println("Error al agregar solicitud carroceria");
			e.printStackTrace();
			return false;
		}
	}

	
	public void actualizarDatosGestion() {
		// TODO Auto-generated method stub
		
	}
	
}