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
package servidor.GestionarTaller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.TallerAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Taller;

import comun.DTOs.TallerDTO;
import comun.GestionarTaller.IControlTaller;

public class ControlTaller extends UnicastRemoteObject implements IControlTaller{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlTaller() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarTaller(TallerDTO tallerDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			TallerAssembler tallerAssemb = new TallerAssembler(accesoBD);
			Taller taller = tallerAssemb.getTallerNuevo(tallerDTO);
			accesoBD.hacerPersistente(taller);
			id = taller.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarTaller(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			TallerAssembler tallerAssemb = new TallerAssembler(accesoBD);
			Taller taller = tallerAssemb.getTaller(buscarTaller(id));
			accesoBD.eliminar(taller);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarTaller(Long id, TallerDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			TallerAssembler tallerAssemb = new TallerAssembler(accesoBD);
			Taller taller = tallerAssemb.getTaller(buscarTaller(id));
			taller.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<TallerDTO> obtenerTallers() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<TallerDTO> talleresDTO = new Vector<TallerDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Taller> talleres = new Vector<Taller> (accesoBD.buscarPorFiltro(Taller.class, ""));
			for (int i = 0; i < talleres.size(); i++) {
				TallerDTO tallerDTO = new TallerDTO();
				tallerDTO.setId(talleres.elementAt(i).getId());
				tallerDTO.setNombre(talleres.elementAt(i).getNombre());
				talleresDTO.add(tallerDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return talleresDTO;
	}

	@Override
	public Vector<TallerDTO> obtenerTallers(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<TallerDTO> tallersDTO = new Vector<TallerDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Taller.class, query);
			TallerAssembler tallerAssemb = new TallerAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				tallersDTO.add(tallerAssemb.getTallerDTO((Taller)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return tallersDTO;
	}

	@Override
	public boolean existeTaller(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Taller) accesoBD.buscarPorId(Taller.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeTaller(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Taller.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public TallerDTO buscarTaller(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		TallerDTO tallerDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			TallerAssembler tallerAssemb = new TallerAssembler(accesoBD);
			tallerDTO = tallerAssemb.getTallerDTO((Taller) accesoBD.buscarPorId(Taller.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return tallerDTO;
	}

	@Override
	public TallerDTO buscarTaller(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		TallerDTO tallerDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Taller.class, filtro);
			TallerAssembler tallerAssemb = new TallerAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				tallerDTO = tallerAssemb.getTallerDTO((Taller)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return tallerDTO;
	}

}