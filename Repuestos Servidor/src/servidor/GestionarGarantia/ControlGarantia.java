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
package servidor.GestionarGarantia;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.GarantiaAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Garantia;

import comun.DTOs.GarantiaDTO;
import comun.GestionarGarantia.IControlGarantia;

public class ControlGarantia extends UnicastRemoteObject implements IControlGarantia{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlGarantia() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarGarantia(GarantiaDTO garantiaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			GarantiaAssembler garantiaAssemb = new GarantiaAssembler(accesoBD);
			Garantia garantia = garantiaAssemb.getGarantiaNuevo(garantiaDTO);
			accesoBD.hacerPersistente(garantia);
			id = garantia.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarGarantia(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			GarantiaAssembler garantiaAssemb = new GarantiaAssembler(accesoBD);
			Garantia garantia = garantiaAssemb.getGarantia(buscarGarantia(id));
			accesoBD.eliminar(garantia);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarGarantia(Long id, GarantiaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			GarantiaAssembler garantiaAssemb = new GarantiaAssembler(accesoBD);
			Garantia garantia = garantiaAssemb.getGarantia(buscarGarantia(id));
			garantia.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<GarantiaDTO> obtenerGarantias() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<GarantiaDTO> garantiasDTO = new Vector<GarantiaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Garantia> garantias = new Vector<Garantia> (accesoBD.buscarPorFiltro(Garantia.class, ""));
			for (int i = 0; i < garantias.size(); i++) {
				GarantiaDTO garantiaDTO = new GarantiaDTO();
				garantiaDTO.setId(garantias.elementAt(i).getId());
				garantiaDTO.setNombre(garantias.elementAt(i).getNombre());
				garantiasDTO.add(garantiaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return garantiasDTO;
	}

	@Override
	public Vector<GarantiaDTO> obtenerGarantias(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<GarantiaDTO> garantiasDTO = new Vector<GarantiaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Garantia.class, query);
			GarantiaAssembler garantiaAssemb = new GarantiaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				garantiasDTO.add(garantiaAssemb.getGarantiaDTO((Garantia)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return garantiasDTO;
	}

	@Override
	public boolean existeGarantia(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Garantia) accesoBD.buscarPorId(Garantia.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeGarantia(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Garantia.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public GarantiaDTO buscarGarantia(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		GarantiaDTO garantiaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			GarantiaAssembler garantiaAssemb = new GarantiaAssembler(accesoBD);
			garantiaDTO = garantiaAssemb.getGarantiaDTO((Garantia) accesoBD.buscarPorId(Garantia.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return garantiaDTO;
	}

	@Override
	public GarantiaDTO buscarGarantia(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		GarantiaDTO garantiaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Garantia.class, filtro);
			GarantiaAssembler garantiaAssemb = new GarantiaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				garantiaDTO = garantiaAssemb.getGarantiaDTO((Garantia)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return garantiaDTO;
	}

}
