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
package servidor.GestionarPerito;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.PeritoAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Perito;

import comun.DTOs.PeritoDTO;
import comun.GestionarPerito.IControlPerito;

public class ControlPerito extends UnicastRemoteObject implements IControlPerito{

	private static final long serialVersionUID = 1L;

	public ControlPerito() throws RemoteException {
		super();
	}

	@Override
	public Long agregarPerito(PeritoDTO peritoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			Perito perito = peritoAssemb.getPeritoNuevo(peritoDTO);
			accesoBD.hacerPersistente(perito);
			id = perito.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPerito(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			Perito perito = peritoAssemb.getPerito(buscarPerito(id));
			accesoBD.eliminar(perito);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPerito(Long id, PeritoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			Perito perito = peritoAssemb.getPerito(buscarPerito(id));
			perito.setNombre(modificado.getNombre());
			perito.setMail(modificado.getMail());
			perito.setTelefono(modificado.getTelefono());
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<PeritoDTO> obtenerPeritos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PeritoDTO> peritosDTO = new Vector<PeritoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Perito> peritos = new Vector<Perito> (accesoBD.buscarPorFiltro(Perito.class, ""));
			for (int i = 0; i < peritos.size(); i++) {
				PeritoDTO peritoDTO = new PeritoDTO();
				peritoDTO.setId(peritos.elementAt(i).getId());
				peritoDTO.setNombre(peritos.elementAt(i).getNombre());
				peritoDTO.setMail(peritos.elementAt(i).getMail());
				peritoDTO.setTelefono(peritos.elementAt(i).getTelefono());
				peritosDTO.add(peritoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritosDTO;
	}

	@Override
	public Vector<PeritoDTO> obtenerPeritos(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PeritoDTO> peritosDTO = new Vector<PeritoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Perito.class, query);
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				peritosDTO.add(peritoAssemb.getPeritoDTO((Perito)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritosDTO;
	}

	@Override
	public boolean existePerito(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Perito) accesoBD.buscarPorId(Perito.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePerito(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Perito.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public PeritoDTO buscarPerito(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PeritoDTO peritoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			peritoDTO = peritoAssemb.getPeritoDTO((Perito) accesoBD.buscarPorId(Perito.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoDTO;
	}

	@Override
	public PeritoDTO buscarPerito(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PeritoDTO peritoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Perito.class, filtro);
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				peritoDTO = peritoAssemb.getPeritoDTO((Perito)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoDTO;
	}

}
