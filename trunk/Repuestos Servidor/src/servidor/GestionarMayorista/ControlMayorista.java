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
package servidor.GestionarMayorista;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.MayoristaAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Mayorista;

import comun.DTOs.MayoristaDTO;
import comun.GestionarMayorista.IControlMayorista;

public class ControlMayorista extends UnicastRemoteObject implements IControlMayorista{

	private static final long serialVersionUID = 1L;

	public ControlMayorista() throws RemoteException {
		super();
	}

	@Override
	public Long agregarMayorista(MayoristaDTO mayoristaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			MayoristaAssembler mayoristaAssemb = new MayoristaAssembler(accesoBD);
			Mayorista mayorista = mayoristaAssemb.getMayoristaNuevo(mayoristaDTO);
			accesoBD.hacerPersistente(mayorista);
			id = mayorista.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarMayorista(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MayoristaAssembler mayoristaAssemb = new MayoristaAssembler(accesoBD);
			Mayorista mayorista = mayoristaAssemb.getMayorista(buscarMayorista(id));
			accesoBD.eliminar(mayorista);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarMayorista(Long id, MayoristaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			MayoristaAssembler mayoristaAssemb = new MayoristaAssembler(accesoBD);
			Mayorista mayorista = mayoristaAssemb.getMayorista(buscarMayorista(id));
			mayorista.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<MayoristaDTO> obtenerMayoristas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MayoristaDTO> mayoristasDTO = new Vector<MayoristaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Mayorista> mayoristas = new Vector<Mayorista> (accesoBD.buscarPorFiltro(Mayorista.class, ""));
			for (int i = 0; i < mayoristas.size(); i++) {
				MayoristaDTO mayoristaDTO = new MayoristaDTO();
				mayoristaDTO.setId(mayoristas.elementAt(i).getId());
				mayoristaDTO.setNombre(mayoristas.elementAt(i).getNombre());
				mayoristasDTO.add(mayoristaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mayoristasDTO;
	}

	@Override
	public Vector<MayoristaDTO> obtenerMayoristas(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MayoristaDTO> mayoristasDTO = new Vector<MayoristaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Mayorista.class, query);
			MayoristaAssembler mayoristaAssemb = new MayoristaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				mayoristasDTO.add(mayoristaAssemb.getMayoristaDTO((Mayorista)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mayoristasDTO;
	}

	@Override
	public boolean existeMayorista(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Mayorista) accesoBD.buscarPorId(Mayorista.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeMayorista(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Mayorista.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MayoristaDTO buscarMayorista(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MayoristaDTO mayoristaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			MayoristaAssembler mayoristaAssemb = new MayoristaAssembler(accesoBD);
			mayoristaDTO = mayoristaAssemb.getMayoristaDTO((Mayorista) accesoBD.buscarPorId(Mayorista.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mayoristaDTO;
	}

	@Override
	public MayoristaDTO buscarMayorista(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MayoristaDTO mayoristaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Mayorista.class, filtro);
			MayoristaAssembler mayoristaAssemb = new MayoristaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				mayoristaDTO = mayoristaAssemb.getMayoristaDTO((Mayorista)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mayoristaDTO;
	}

}