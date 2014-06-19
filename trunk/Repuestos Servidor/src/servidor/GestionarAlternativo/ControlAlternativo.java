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
package servidor.GestionarAlternativo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.AlternativoAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Alternativo;

import comun.DTOs.AlternativoDTO;
import comun.GestionarAlternativo.IControlAlternativo;

public class ControlAlternativo extends UnicastRemoteObject implements IControlAlternativo {

	private static final long serialVersionUID = 1L;

	public ControlAlternativo() throws RemoteException {
		super();
	}

	@Override
	public Long agregarAlternativo(AlternativoDTO alternativoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			AlternativoAssembler alternativoAssemb = new AlternativoAssembler(accesoBD);
			Alternativo alternativo = alternativoAssemb.getAlternativoNuevo(alternativoDTO);
			accesoBD.hacerPersistente(alternativo);
			id = alternativo.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarAlternativo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			AlternativoAssembler alternativoAssemb = new AlternativoAssembler(accesoBD);
			Alternativo alternativo = alternativoAssemb.getAlternativo(buscarAlternativo(id));
			accesoBD.eliminar(alternativo);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarAlternativo(Long id, AlternativoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			AlternativoAssembler alternativoAssemb = new AlternativoAssembler(accesoBD);
			Alternativo alternativo = alternativoAssemb.getAlternativo(buscarAlternativo(id));
			alternativo.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<AlternativoDTO> obtenerAlternativos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<AlternativoDTO> alternativosDTO = new Vector<AlternativoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Alternativo> alternativos = new Vector<Alternativo> (accesoBD.buscarPorFiltro(Alternativo.class, ""));
			for (int i = 0; i < alternativos.size(); i++) {
				AlternativoDTO alternativoDTO = new AlternativoDTO();
				alternativoDTO.setId(alternativos.elementAt(i).getId());
				alternativoDTO.setNombre(alternativos.elementAt(i).getNombre());
				alternativosDTO.add(alternativoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return alternativosDTO;
	}

	@Override
	public Vector<AlternativoDTO> obtenerAlternativos(String query)	throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<AlternativoDTO> alternativosDTO = new Vector<AlternativoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Alternativo.class, query);
			AlternativoAssembler alternativoAssemb = new AlternativoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				alternativosDTO.add(alternativoAssemb.getAlternativoDTO((Alternativo)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return alternativosDTO;
	}

	@Override
	public boolean existeAlternativo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Alternativo) accesoBD.buscarPorId(Alternativo.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeAlternativo(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Alternativo.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public AlternativoDTO buscarAlternativo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		AlternativoDTO alternativoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			AlternativoAssembler alternativoAssemb = new AlternativoAssembler(accesoBD);
			alternativoDTO = alternativoAssemb.getAlternativoDTO((Alternativo) accesoBD.buscarPorId(Alternativo.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return alternativoDTO;
	}

	@Override
	public AlternativoDTO buscarAlternativo(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		AlternativoDTO alternativoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Alternativo.class, filtro);
			AlternativoAssembler alternativoAssemb = new AlternativoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				alternativoDTO = alternativoAssemb.getAlternativoDTO((Alternativo)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return alternativoDTO;
	}

}
