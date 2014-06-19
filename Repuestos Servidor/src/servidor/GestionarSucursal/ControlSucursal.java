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
package servidor.GestionarSucursal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.SucursalAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Sucursal;

import comun.DTOs.SucursalDTO;
import comun.GestionarSucursal.IControlSucursal;

public class ControlSucursal extends UnicastRemoteObject implements IControlSucursal{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlSucursal() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarSucursal(SucursalDTO sucursalDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			SucursalAssembler sucursalAssemb = new SucursalAssembler(accesoBD);
			Sucursal sucursal = sucursalAssemb.getSucursalNuevo(sucursalDTO);
			accesoBD.hacerPersistente(sucursal);
			id = sucursal.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarSucursal(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			SucursalAssembler sucursalAssemb = new SucursalAssembler(accesoBD);
			Sucursal sucursal = sucursalAssemb.getSucursal(buscarSucursal(id));
			accesoBD.eliminar(sucursal);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarSucursal(Long id, SucursalDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			SucursalAssembler sucursalAssemb = new SucursalAssembler(accesoBD);
			Sucursal sucursal = sucursalAssemb.getSucursal(buscarSucursal(id));
			sucursal.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<SucursalDTO> obtenerSucursals() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SucursalDTO> sucursalesDTO = new Vector<SucursalDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Sucursal> sucursales = new Vector<Sucursal> (accesoBD.buscarPorFiltro(Sucursal.class, ""));
			for (int i = 0; i < sucursales.size(); i++) {
				SucursalDTO sucursalDTO = new SucursalDTO();
				sucursalDTO.setId(sucursales.elementAt(i).getId());
				sucursalDTO.setNombre(sucursales.elementAt(i).getNombre());
				sucursalesDTO.add(sucursalDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return sucursalesDTO;
	}

	@Override
	public Vector<SucursalDTO> obtenerSucursals(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SucursalDTO> sucursalsDTO = new Vector<SucursalDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Sucursal.class, query);
			SucursalAssembler sucursalAssemb = new SucursalAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				sucursalsDTO.add(sucursalAssemb.getSucursalDTO((Sucursal)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return sucursalsDTO;
	}

	@Override
	public boolean existeSucursal(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Sucursal) accesoBD.buscarPorId(Sucursal.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeSucursal(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Sucursal.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public SucursalDTO buscarSucursal(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SucursalDTO SucursalDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			SucursalAssembler sucursalAssemb = new SucursalAssembler(accesoBD);
			SucursalDTO = sucursalAssemb.getSucursalDTO((Sucursal) accesoBD.buscarPorId(Sucursal.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return SucursalDTO;
	}

	@Override
	public SucursalDTO buscarSucursal(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SucursalDTO sucursalDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Sucursal.class, filtro);
			SucursalAssembler sucursalAssemb = new SucursalAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				sucursalDTO = sucursalAssemb.getSucursalDTO((Sucursal)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return sucursalDTO;
	}

}