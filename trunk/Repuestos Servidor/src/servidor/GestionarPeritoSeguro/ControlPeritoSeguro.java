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
package servidor.GestionarPeritoSeguro;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.PeritoAssembler;
import servidor.Assemblers.PeritoSeguroAssembler;
import servidor.Assemblers.SeguroAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.PeritoSeguro;

import comun.DTOs.PeritoDTO;
import comun.DTOs.PeritoSeguroDTO;
import comun.DTOs.SeguroDTO;
import comun.GestionarPeritoSeguro.IControlPeritoSeguro;

public class ControlPeritoSeguro extends UnicastRemoteObject implements IControlPeritoSeguro{

	private static final long serialVersionUID = 1L;

	public ControlPeritoSeguro() throws RemoteException {
		super();
	}

	@Override
	public Long agregarPeritoSeguro(PeritoSeguroDTO peritoSeguroDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			PeritoSeguro peritoSeguro = peritoSeguroAssemb.getPeritoSeguroNuevo(peritoSeguroDTO);
			accesoBD.hacerPersistente(peritoSeguro);
			id = peritoSeguro.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarPeritoSeguro(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			PeritoSeguro peritoSeguro = peritoSeguroAssemb.getPeritoSeguro(buscarPeritoSeguro(id));
			accesoBD.eliminar(peritoSeguro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarPeritoSeguro(Long id, PeritoSeguroDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			PeritoSeguro peritoSeguro = peritoSeguroAssemb.getPeritoSeguro(buscarPeritoSeguro(id));
			
			PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
			if (modificado.getPerito()!=null)
				peritoSeguro.setPerito(peritoAssemb.getPerito(modificado.getPerito()));
			SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
			if (modificado.getSeguro()!=null)
				peritoSeguro.setSeguro(seguroAssemb.getSeguro(modificado.getSeguro()));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PeritoSeguroDTO> peritoSegurosDTO = new Vector<PeritoSeguroDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<PeritoSeguro> peritoSeguros = new Vector<PeritoSeguro> (accesoBD.buscarPorFiltro(PeritoSeguro.class, ""));
			for (int i = 0; i < peritoSeguros.size(); i++) {
				PeritoSeguroDTO peritoSeguroDTO = new PeritoSeguroDTO();
				peritoSeguroDTO.setId(peritoSeguros.elementAt(i).getId());
				PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
				if (peritoSeguros.elementAt(i).getPerito()!=null)
					peritoSeguroDTO.setPerito(peritoAssemb.getPeritoDTO(peritoSeguros.elementAt(i).getPerito()));
				SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
				if (peritoSeguros.elementAt(i).getSeguro()!=null)
					peritoSeguroDTO.setSeguro(seguroAssemb.getSeguroDTO(peritoSeguros.elementAt(i).getSeguro()));
				peritoSegurosDTO.add(peritoSeguroDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoSegurosDTO;
	}

	@Override
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros(PeritoDTO peritoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PeritoSeguroDTO> peritoSegurosDTO = new Vector<PeritoSeguroDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String query = "perito.id == "+peritoDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(PeritoSeguro.class, query);
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				peritoSegurosDTO.add(peritoSeguroAssemb.getPeritoSeguroDTO((PeritoSeguro)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoSegurosDTO;
	}

	@Override
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros(SeguroDTO seguroDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PeritoSeguroDTO> peritoSegurosDTO = new Vector<PeritoSeguroDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String query = "seguro.id == "+seguroDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(PeritoSeguro.class, query);
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				peritoSegurosDTO.add(peritoSeguroAssemb.getPeritoSeguroDTO((PeritoSeguro)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoSegurosDTO;
	}

	@Override
	public Vector<PeritoSeguroDTO> obtenerPeritoSeguros(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<PeritoSeguroDTO> peritoSegurosDTO = new Vector<PeritoSeguroDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(PeritoSeguro.class, query);
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				peritoSegurosDTO.add(peritoSeguroAssemb.getPeritoSeguroDTO((PeritoSeguro)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoSegurosDTO;
	}

	@Override
	public boolean existePeritoSeguro(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((PeritoSeguro) accesoBD.buscarPorId(PeritoSeguro.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existePeritoSeguro(PeritoDTO peritoDTO, SeguroDTO seguroDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "perito.id == "+peritoDTO.getId()+ " && seguro.id == "+seguroDTO.getId();
			movCol = accesoBD.buscarPorFiltro(PeritoSeguro.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public PeritoSeguroDTO buscarPeritoSeguro(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PeritoSeguroDTO peritoSeguroDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			peritoSeguroDTO = peritoSeguroAssemb.getPeritoSeguroDTO((PeritoSeguro) accesoBD.buscarPorId(PeritoSeguro.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoSeguroDTO;
	}

	@Override
	public PeritoSeguroDTO buscarPeritoSeguro(PeritoDTO peritoDTO, SeguroDTO seguroDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		PeritoSeguroDTO peritoSeguroDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "perito.id == "+peritoDTO.getId()+ " && seguro.id == "+seguroDTO.getId();
			Collection movCol = accesoBD.buscarPorFiltro(PeritoSeguro.class, filtro);
			PeritoSeguroAssembler peritoSeguroAssemb = new PeritoSeguroAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				peritoSeguroDTO = peritoSeguroAssemb.getPeritoSeguroDTO((PeritoSeguro)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return peritoSeguroDTO;
	}

}