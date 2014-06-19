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
package servidor.GestionarFabrica;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.FabricaAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Fabrica;

import comun.DTOs.FabricaDTO;
import comun.GestionarFabrica.IControlFabrica;

public class ControlFabrica extends UnicastRemoteObject implements IControlFabrica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlFabrica() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarFabrica(FabricaDTO fabricaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			FabricaAssembler fabricaAssemb = new FabricaAssembler(accesoBD);
			Fabrica fabrica = fabricaAssemb.getFabricaNuevo(fabricaDTO);
			accesoBD.hacerPersistente(fabrica);
			id = fabrica.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarFabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			FabricaAssembler fabricaAssemb = new FabricaAssembler(accesoBD);
			Fabrica fabrica = fabricaAssemb.getFabrica(buscarFabrica(id));
			accesoBD.eliminar(fabrica);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarFabrica(Long id, FabricaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			FabricaAssembler fabricaAssemb = new FabricaAssembler(accesoBD);
			Fabrica fabrica = fabricaAssemb.getFabrica(buscarFabrica(id));
			fabrica.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<FabricaDTO> obtenerFabricas() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<FabricaDTO> fabricasDTO = new Vector<FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Fabrica> fabricas = new Vector<Fabrica> (accesoBD.buscarPorFiltro(Fabrica.class, ""));
			for (int i = 0; i < fabricas.size(); i++) {
				FabricaDTO fabricaDTO = new FabricaDTO();
				fabricaDTO.setId(fabricas.elementAt(i).getId());
				fabricaDTO.setNombre(fabricas.elementAt(i).getNombre());
				fabricasDTO.add(fabricaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return fabricasDTO;
	}

	@Override
	public Vector<FabricaDTO> obtenerFabricas(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<FabricaDTO> fabricasDTO = new Vector<FabricaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Fabrica.class, query);
			FabricaAssembler fabricaAssemb = new FabricaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				fabricasDTO.add(fabricaAssemb.getFabricaDTO((Fabrica)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return fabricasDTO;
	}

	@Override
	public boolean existeFabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Fabrica) accesoBD.buscarPorId(Fabrica.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeFabrica(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Fabrica.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public FabricaDTO buscarFabrica(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		FabricaDTO fabricaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			FabricaAssembler fabricaAssemb = new FabricaAssembler(accesoBD);
			fabricaDTO = fabricaAssemb.getFabricaDTO((Fabrica) accesoBD.buscarPorId(Fabrica.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return fabricaDTO;
	}

	@Override
	public FabricaDTO buscarFabrica(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		FabricaDTO fabricaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Fabrica.class, filtro);
			FabricaAssembler fabricaAssemb = new FabricaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				fabricaDTO = fabricaAssemb.getFabricaDTO((Fabrica)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return fabricaDTO;
	}

}
