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
package servidor.GestionarMostrador;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.MostradorAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Mostrador;
import comun.DTOs.MostradorDTO;
import comun.GestionarMostrador.IControlMostrador;

public class ControlMostrador extends UnicastRemoteObject implements IControlMostrador{

	private static final long serialVersionUID = 1L;

	public ControlMostrador() throws RemoteException {
		super();
	}

	@Override
	public Long agregarMostrador(MostradorDTO mostradorDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			Mostrador mostrador = mostradorAssemb.getMostradorNuevo(mostradorDTO);
			accesoBD.hacerPersistente(mostrador);
			id = mostrador.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarMostrador(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			Mostrador mostrador = mostradorAssemb.getMostrador(buscarMostrador(id));
			accesoBD.eliminar(mostrador);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarMostrador(Long id, MostradorDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			Mostrador mostrador = mostradorAssemb.getMostrador(buscarMostrador(id));
			mostrador.setNombre(modificado.getNombre());
			mostrador.setMail(modificado.getMail());
			mostrador.setTelefono(modificado.getTelefono());
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<MostradorDTO> obtenerMostradors() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MostradorDTO> mostradorsDTO = new Vector<MostradorDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Mostrador> mostradors = new Vector<Mostrador> (accesoBD.buscarPorFiltro(Mostrador.class, ""));
			for (int i = 0; i < mostradors.size(); i++) {
				MostradorDTO mostradorDTO = new MostradorDTO();
				mostradorDTO.setId(mostradors.elementAt(i).getId());
				mostradorDTO.setNombre(mostradors.elementAt(i).getNombre());
				mostradorDTO.setMail(mostradors.elementAt(i).getMail());
				mostradorDTO.setTelefono(mostradors.elementAt(i).getTelefono());
				mostradorsDTO.add(mostradorDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mostradorsDTO;
	}

	@Override
	public Vector<MostradorDTO> obtenerMostradors(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MostradorDTO> mostradorsDTO = new Vector<MostradorDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Mostrador.class, query);
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				mostradorsDTO.add(mostradorAssemb.getMostradorDTO((Mostrador)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mostradorsDTO;
	}

	@Override
	public boolean existeMostrador(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Mostrador) accesoBD.buscarPorId(Mostrador.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeMostrador(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Mostrador.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MostradorDTO buscarMostrador(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MostradorDTO mostradorDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			mostradorDTO = mostradorAssemb.getMostradorDTO((Mostrador) accesoBD.buscarPorId(Mostrador.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mostradorDTO;
	}

	@Override
	public MostradorDTO buscarMostrador(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MostradorDTO mostradorDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Mostrador.class, filtro);
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				mostradorDTO = mostradorAssemb.getMostradorDTO((Mostrador)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mostradorDTO;
	}

	@Override
	public boolean existeMostradorTel(String telefono) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "telefono.equals(\""+telefono+"\")";
			movCol = accesoBD.buscarPorFiltro(Mostrador.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MostradorDTO buscarMostradorTel(String telefono) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MostradorDTO mostradorDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "telefono.equals(\""+telefono+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Mostrador.class, filtro);
			MostradorAssembler mostradorAssemb = new MostradorAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				mostradorDTO = mostradorAssemb.getMostradorDTO((Mostrador)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mostradorDTO;
	}

}
