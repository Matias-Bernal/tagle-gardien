/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package servidor.GestionarProveedor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.ProveedorAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Proveedor;

import comun.DTOs.ProveedorDTO;
import comun.GestionarProveedor.IControlProveedor;

public class ControlProveedor extends UnicastRemoteObject implements IControlProveedor {

	private static final long serialVersionUID = 1L;

	public ControlProveedor() throws RemoteException {
		super();
	}

	@Override
	public Long agregarProveedor(ProveedorDTO proveedorDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			Proveedor proveedor = proveedorAssemb.getProveedorNuevo(proveedorDTO);
			accesoBD.hacerPersistente(proveedor);
			id = proveedor.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarProveedor(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			Proveedor proveedor = proveedorAssemb.getProveedorNuevo(buscarProveedor(id));
			accesoBD.eliminar(proveedor);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}	
	}

	@Override
	public void modificarProveedor(Long id, ProveedorDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			Proveedor proveedor = proveedorAssemb.getProveedorNuevo(buscarProveedor(id));
			
			proveedor.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		
	}

	@Override
	public Vector<ProveedorDTO> obtenerProveedores() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<ProveedorDTO> proveedoresDTO = new Vector<ProveedorDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Proveedor> proveedores = new Vector<Proveedor> (accesoBD.buscarPorFiltro(Proveedor.class, ""));
			for (int i = 0; i < proveedores.size(); i++) {
				ProveedorDTO proveedorDTO = new ProveedorDTO();
				proveedorDTO.setId(proveedores.elementAt(i).getId());
				proveedorDTO.setNombre(proveedores.elementAt(i).getNombre());
				proveedoresDTO.add(proveedorDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return proveedoresDTO;
	}

	@Override
	public boolean existeProveedor(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Proveedor) accesoBD.buscarPorId(Proveedor.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeProveedor(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Proveedor.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public ProveedorDTO buscarProveedor(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ProveedorDTO proveedorDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			proveedorDTO = proveedorAssemb.getProveedorDTO((Proveedor) accesoBD.buscarPorId(Proveedor.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return proveedorDTO;
	}

	@Override
	public ProveedorDTO buscarProveedor(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		ProveedorDTO proveedorDTO = null;		
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Proveedor.class, filtro);
			ProveedorAssembler proveedorAssemb = new ProveedorAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				proveedorDTO = proveedorAssemb.getProveedorDTO((Proveedor)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return proveedorDTO;
	}

}
