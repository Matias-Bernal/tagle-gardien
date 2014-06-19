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
package servidor.GestionarSeguro;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.SeguroAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Seguro;

import comun.DTOs.SeguroDTO;
import comun.GestionarSeguro.IControlSeguro;

public class ControlSeguro extends UnicastRemoteObject implements IControlSeguro{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlSeguro() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarSeguro(SeguroDTO seguroDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			Seguro seguro = seguroAssemb.getSeguroNuevo(seguroDTO);
			accesoBD.hacerPersistente(seguro);
			id = seguro.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarSeguro(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			Seguro seguro = seguroAssemb.getSeguro(buscarSeguro(id));
			accesoBD.eliminar(seguro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarSeguro(Long id, SeguroDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			Seguro seguro = seguroAssemb.getSeguro(buscarSeguro(id));
			seguro.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<SeguroDTO> obtenerSeguros() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SeguroDTO> segurosDTO = new Vector<SeguroDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Seguro> seguros = new Vector<Seguro> (accesoBD.buscarPorFiltro(Seguro.class, ""));
			for (int i = 0; i < seguros.size(); i++) {
				SeguroDTO seguroDTO = new SeguroDTO();
				seguroDTO.setId(seguros.elementAt(i).getId());
				seguroDTO.setNombre(seguros.elementAt(i).getNombre());
				segurosDTO.add(seguroDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return segurosDTO;
	}

	@Override
	public Vector<SeguroDTO> obtenerSeguros(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SeguroDTO> segurosDTO = new Vector<SeguroDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Seguro.class, query);
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				segurosDTO.add(seguroAssemb.getSeguroDTO((Seguro)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return segurosDTO;
	}

	@Override
	public boolean existeSeguro(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Seguro) accesoBD.buscarPorId(Seguro.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeSeguro(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Seguro.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public SeguroDTO buscarSeguro(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SeguroDTO seguroDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			seguroDTO = seguroAssemb.getSeguroDTO((Seguro) accesoBD.buscarPorId(Seguro.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return seguroDTO;
	}

	@Override
	public SeguroDTO buscarSeguro(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SeguroDTO seguroDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Seguro.class, filtro);
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				seguroDTO = seguroAssemb.getSeguroDTO((Seguro)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return seguroDTO;
	}

}