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
package servidor.GestionarMecanico;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.MecanicoAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Mecanico;

import comun.DTOs.MecanicoDTO;
import comun.GestionarMecanico.IControlMecanico;

public class ControlMecanico extends UnicastRemoteObject implements IControlMecanico{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlMecanico() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarMecanico(MecanicoDTO mecanicoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			MecanicoAssembler mecanicoAssemb = new MecanicoAssembler(accesoBD);
			Mecanico mecanico = mecanicoAssemb.getMecanicoNuevo(mecanicoDTO);
			accesoBD.hacerPersistente(mecanico);
			id = mecanico.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarMecanico(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			MecanicoAssembler mecanicoAssemb = new MecanicoAssembler(accesoBD);
			Mecanico mecanico = mecanicoAssemb.getMecanico(buscarMecanico(id));
			accesoBD.eliminar(mecanico);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarMecanico(Long id, MecanicoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			MecanicoAssembler mecanicoAssemb = new MecanicoAssembler(accesoBD);
			Mecanico mecanico = mecanicoAssemb.getMecanico(buscarMecanico(id));
			mecanico.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<MecanicoDTO> obtenerMecanicos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MecanicoDTO> mecanicosDTO = new Vector<MecanicoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Mecanico> mecanicos = new Vector<Mecanico> (accesoBD.buscarPorFiltro(Mecanico.class, ""));
			for (int i = 0; i < mecanicos.size(); i++) {
				MecanicoDTO mecanicoDTO = new MecanicoDTO();
				mecanicoDTO.setId(mecanicos.elementAt(i).getId());
				mecanicoDTO.setNombre(mecanicos.elementAt(i).getNombre());
				mecanicosDTO.add(mecanicoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mecanicosDTO;
	}

	@Override
	public Vector<MecanicoDTO> obtenerMecanicos(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<MecanicoDTO> mecanicosDTO = new Vector<MecanicoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Mecanico.class, query);
			MecanicoAssembler mecanicoAssemb = new MecanicoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				mecanicosDTO.add(mecanicoAssemb.getMecanicoDTO((Mecanico)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mecanicosDTO;
	}

	@Override
	public boolean existeMecanico(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Mecanico) accesoBD.buscarPorId(Mecanico.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeMecanico(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Mecanico.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public MecanicoDTO buscarMecanico(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MecanicoDTO mecanicoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			MecanicoAssembler mecanicoAssemb = new MecanicoAssembler(accesoBD);
			mecanicoDTO = mecanicoAssemb.getMecanicoDTO((Mecanico) accesoBD.buscarPorId(Mecanico.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mecanicoDTO;
	}

	@Override
	public MecanicoDTO buscarMecanico(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		MecanicoDTO mecanicoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Mecanico.class, filtro);
			MecanicoAssembler mecanicoAssemb = new MecanicoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				mecanicoDTO = mecanicoAssemb.getMecanicoDTO((Mecanico)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return mecanicoDTO;
	}

}