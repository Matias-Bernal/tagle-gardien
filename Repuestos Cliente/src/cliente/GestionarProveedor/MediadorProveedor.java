package cliente.GestionarProveedor;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MenuPrincipal.MediadorPrincipal;
import comun.DTOs.AlternativoDTO;
import comun.DTOs.FabricaDTO;
import comun.DTOs.ProveedorDTO;
import comun.DTOs.SucursalDTO;
import comun.GestionarAlternativo.IControlAlternativo;
import comun.GestionarFabrica.IControlFabrica;
import comun.GestionarProveedor.IControlProveedor;
import comun.GestionarSucursal.IControlSucursal;

public class MediadorProveedor {

	protected GUIAltaProveedor guiAltaProveedor;
	protected GUIModificarProveedor guiModificarProveedor;
	protected GUIGestionProveedor guiGestionProveedor;
	protected MediadorPrincipal mediadorPrincipal;
	

	public MediadorProveedor(MediadorPrincipal mediadorPrincipal){
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void altaProveedor(){
		guiAltaProveedor = new GUIAltaProveedor(this);
		guiAltaProveedor.setVisible(true);
	}
	
	public void gestionProveedor(){
		guiGestionProveedor = new GUIGestionProveedor(this);
		guiGestionProveedor.setVisible(true);
	}

	public boolean agregarFabrica(String nombre) {
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		FabricaDTO fabricaDTO = new FabricaDTO(null, nombre);
		try {
			return (iControlFabrica.agregarFabrica(fabricaDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Proveedor");
			e.printStackTrace();
			return false;
		}
	}

	public boolean agregarSucursal(String nombre) {
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		SucursalDTO sucursalDTO = new SucursalDTO(null, nombre);
		try {
			return (iControlSucursal.agregarSucursal(sucursalDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Proveedor");
			e.printStackTrace();
			return false;
		}
	}

	public boolean agregarAlternativo(String nombre) {
		IControlAlternativo iControlAlternativo = MediadorAccionesIniciarPrograma.getControlAlternativo();
		AlternativoDTO alternativoDTO = new AlternativoDTO(null, nombre);
		try {
			return (iControlAlternativo.agregarAlternativo(alternativoDTO)!=null);
		} catch (Exception e) {
			System.out.println("Error al agregar Proveedor");
			e.printStackTrace();
			return false;
		}
	}

	public void modificarProveedor(Long id) {
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlAlternativo iControlAlternativo = MediadorAccionesIniciarPrograma.getControlAlternativo();
		try {
			ProveedorDTO proveedor = null;
			if(iControlSucursal.existeSucursal(id))
				proveedor = iControlSucursal.buscarSucursal(id);
			if(iControlFabrica.existeFabrica(id))
				proveedor = iControlFabrica.buscarFabrica(id);
			if(iControlAlternativo.existeAlternativo(id))
				proveedor = iControlAlternativo.buscarAlternativo(id);
			guiModificarProveedor = new GUIModificarProveedor(this, proveedor);
			guiModificarProveedor.setVisible(true);
		} catch (Exception e) {
			System.out.println("Error al modificar Proveedor");
			e.printStackTrace();
		}
	}

	public boolean eliminarProveedor(Long id) {
		IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		try {
			iControlProveedor.eliminarProveedor(id);
			return true;
		} catch (Exception e) {
			System.out.println("Error al elimnar Proveedor");
			e.printStackTrace();
			return false;
		}	
	}

	public Vector<ProveedorDTO> obtenerProveedores() {
		IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		Vector<ProveedorDTO> proveedores = new Vector<ProveedorDTO>();
		try {
			proveedores = iControlProveedor.obtenerProveedores();
			return proveedores;
		} catch (Exception e) {
			System.out.println("Error al obtener Proveedores");
			e.printStackTrace();
			return proveedores;
		}
	}

	public ProveedorDTO tipoProveedor(Long id) {
		IControlFabrica iControlFabrica = MediadorAccionesIniciarPrograma.getControlFabrica();
		IControlSucursal iControlSucursal = MediadorAccionesIniciarPrograma.getControlSucursal();
		IControlAlternativo iControlAlternativo = MediadorAccionesIniciarPrograma.getControlAlternativo();
		try {
			
			ProveedorDTO proveedor = null;
			if(iControlSucursal.existeSucursal(id)){
				proveedor = iControlSucursal.buscarSucursal(id);
				return proveedor;
			}
			if(iControlFabrica.existeFabrica(id)){
				proveedor = iControlFabrica.buscarFabrica(id);
				return proveedor;
			}
			if(iControlAlternativo.existeAlternativo(id)){
				proveedor = iControlAlternativo.buscarAlternativo(id);
				return proveedor;
			}
			return proveedor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean modificarFabrica(ProveedorDTO fabrica, String nombre) {
		IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		try {
			fabrica.setNombre(nombre);
			iControlProveedor.modificarProveedor(fabrica.getId(), fabrica);
			return true;
		} catch (Exception e) {
			System.out.println("Error al modificar fabrica");
			e.printStackTrace();
			return false;
		}	
	}

	public boolean modificarSucursal(ProveedorDTO sucursal, String nombre) {
		IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		try {
			sucursal.setNombre(nombre);
			iControlProveedor.modificarProveedor(sucursal.getId(), sucursal);
			return true;
		} catch (Exception e) {
			System.out.println("Error al modificar sucursal");
			e.printStackTrace();
			return false;
		}	
	}

	public boolean modificarAlternativo(ProveedorDTO alternativo, String nombre) {
		IControlProveedor iControlProveedor = MediadorAccionesIniciarPrograma.getControlProveedor();
		try {
			alternativo.setNombre(nombre);
			iControlProveedor.modificarProveedor(alternativo.getId(), alternativo);
			return true;
		} catch (Exception e) {
			System.out.println("Error al modificar alternativo");
			e.printStackTrace();
			return false;
		}	
	}

	public void actualizarDatosGestion() {
		if (guiGestionProveedor!= null)
			guiGestionProveedor.actualizarDatos();
	}
}
