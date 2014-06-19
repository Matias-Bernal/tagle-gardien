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
package servidor.GestionarSolicitante;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.SolicitanteAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Solicitante;

import comun.DTOs.SolicitanteDTO;
import comun.GestionarSolicitante.IControlSolicitante;

public class ControlSolicitante extends UnicastRemoteObject implements IControlSolicitante {

	private static final long serialVersionUID = 1L;

	public ControlSolicitante() throws RemoteException {
		super();
	}

	@Override
	public Long agregarSolicitante(SolicitanteDTO solicitanteDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
			Solicitante solicitante = solicitanteAssemb.getSolicitanteNuevo(solicitanteDTO);
			accesoBD.hacerPersistente(solicitante);
			id = solicitante.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarSolicitante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
			Solicitante solicitante = solicitanteAssemb.getSolicitanteNuevo(buscarSolicitante(id));
			accesoBD.eliminar(solicitante);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}	
	}

	@Override
	public void modificarSolicitante(Long id, SolicitanteDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
			Solicitante solicitante = solicitanteAssemb.getSolicitanteNuevo(buscarSolicitante(id));
			
			solicitante.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		
	}

	@Override
	public Vector<SolicitanteDTO> obtenerSolicitantes() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<SolicitanteDTO> solicitantesDTO = new Vector<SolicitanteDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Solicitante> solicitantes = new Vector<Solicitante> (accesoBD.buscarPorFiltro(Solicitante.class, ""));
			for (int i = 0; i < solicitantes.size(); i++) {
				SolicitanteDTO solicitanteDTO = new SolicitanteDTO();
				solicitanteDTO.setId(solicitantes.elementAt(i).getId());
				solicitanteDTO.setNombre(solicitantes.elementAt(i).getNombre());
				solicitantesDTO.add(solicitanteDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return solicitantesDTO;
	}

	@Override
	public boolean existeSolicitante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Solicitante) accesoBD.buscarPorId(Solicitante.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeSolicitante(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Solicitante.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public SolicitanteDTO buscarSolicitante(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SolicitanteDTO solicitanteDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
			solicitanteDTO = solicitanteAssemb.getSolicitanteDTO((Solicitante) accesoBD.buscarPorId(Solicitante.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return solicitanteDTO;
	}

	@Override
	public SolicitanteDTO buscarSolicitante(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		SolicitanteDTO solicitanteDTO = null;		
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Solicitante.class, filtro);
			SolicitanteAssembler solicitanteAssemb = new SolicitanteAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				solicitanteDTO = solicitanteAssemb.getSolicitanteDTO((Solicitante)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return solicitanteDTO;
	}
}
