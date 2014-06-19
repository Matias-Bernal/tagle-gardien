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
package servidor.GestionarCargo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.CargoAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Cargo;

import comun.DTOs.CargoDTO;
import comun.GestionarCargo.IControlCargo;

public class ControlCargo extends UnicastRemoteObject implements IControlCargo{

	public ControlCargo() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Long agregarCargo(CargoDTO cargoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			Cargo cargo = cargoAssemb.getCargoNuevo(cargoDTO);
			accesoBD.hacerPersistente(cargo);
			id = cargo.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarCargo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			Cargo cargo  = cargoAssemb.getCargo(buscarCargo(id));
			accesoBD.eliminar(cargo);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarCargo(Long id, CargoDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			Cargo cargo = cargoAssemb.getCargo(buscarCargo(id));
			cargo.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<CargoDTO> obtenerCargos() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<CargoDTO> cargosDTO = new Vector<CargoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Cargo> cargos = new Vector<Cargo> (accesoBD.buscarPorFiltro(Cargo.class, ""));
			for (int i = 0; i < cargos.size(); i++) {
				CargoDTO cargoDTO = new CargoDTO();
				cargoDTO.setId(cargos.elementAt(i).getId());
				cargoDTO.setNombre(cargos.elementAt(i).getNombre());
				cargosDTO.add(cargoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cargosDTO;
	}

	@Override
	public Vector<CargoDTO> obtenerCargos(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<CargoDTO> cargosDTO = new Vector<CargoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Cargo.class, query);
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				cargosDTO.add(cargoAssemb.getCargoDTO((Cargo)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cargosDTO;
	}

	@Override
	public boolean existeCargo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Cargo) accesoBD.buscarPorId(Cargo.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeCargo(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Cargo.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public CargoDTO buscarCargo(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		CargoDTO cargoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			cargoDTO = cargoAssemb.getCargoDTO((Cargo) accesoBD.buscarPorId(Cargo.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cargoDTO;
	}

	@Override
	public CargoDTO buscarCargo(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		CargoDTO cargoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Cargo.class, filtro);
			CargoAssembler cargoAssemb = new CargoAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				cargoDTO = cargoAssemb.getCargoDTO((Cargo)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return cargoDTO;
	}

	
}
