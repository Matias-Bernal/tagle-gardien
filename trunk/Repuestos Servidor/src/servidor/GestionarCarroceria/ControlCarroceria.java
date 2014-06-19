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
package servidor.GestionarCarroceria;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.CarroceriaAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Carroceria;

import comun.DTOs.CarroceriaDTO;
import comun.GestionarCarroceria.IControlCarroceria;

public class ControlCarroceria extends UnicastRemoteObject implements IControlCarroceria{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ControlCarroceria() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long agregarCarroceria(CarroceriaDTO carroceriaDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			CarroceriaAssembler carroceriaAssemb = new CarroceriaAssembler(accesoBD);
			Carroceria carroceria = carroceriaAssemb.getCarroceriaNuevo(carroceriaDTO);
			accesoBD.hacerPersistente(carroceria);
			id = carroceria.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarCarroceria(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			CarroceriaAssembler carroceriaAssemb = new CarroceriaAssembler(accesoBD);
			Carroceria carroceria = carroceriaAssemb.getCarroceria(buscarCarroceria(id));
			accesoBD.eliminar(carroceria);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarCarroceria(Long id, CarroceriaDTO modificado) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			
			CarroceriaAssembler carroceriaAssemb = new CarroceriaAssembler(accesoBD);
			Carroceria carroceria = carroceriaAssemb.getCarroceria(buscarCarroceria(id));
			carroceria.setNombre(modificado.getNombre());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<CarroceriaDTO> obtenerCarrocerias() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<CarroceriaDTO> carroceriasDTO = new Vector<CarroceriaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<Carroceria> carrocerias = new Vector<Carroceria> (accesoBD.buscarPorFiltro(Carroceria.class, ""));
			for (int i = 0; i < carrocerias.size(); i++) {
				CarroceriaDTO carroceriaDTO = new CarroceriaDTO();
				carroceriaDTO.setId(carrocerias.elementAt(i).getId());
				carroceriaDTO.setNombre(carrocerias.elementAt(i).getNombre());
				carroceriasDTO.add(carroceriaDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return carroceriasDTO;
	}

	@Override
	public Vector<CarroceriaDTO> obtenerCarrocerias(String query) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<CarroceriaDTO> carroceriasDTO = new Vector<CarroceriaDTO>();
		try {
			accesoBD.iniciarTransaccion();
			Collection movCol = accesoBD.buscarPorFiltro(Carroceria.class, query);
			CarroceriaAssembler carroceriaAssemb = new CarroceriaAssembler(accesoBD);
			for (int i = 0; i < movCol.size(); i++) {
				carroceriasDTO.add(carroceriaAssemb.getCarroceriaDTO((Carroceria)(movCol.toArray())[i]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return carroceriasDTO;
	}

	@Override
	public boolean existeCarroceria(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		boolean existe = false;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((Carroceria) accesoBD.buscarPorId(Carroceria.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeCarroceria(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection movCol = null;
		try {
			accesoBD.iniciarTransaccion();
			
			String filtro = "nombre.equals(\""+nombre+"\")";
			movCol = accesoBD.buscarPorFiltro(Carroceria.class, filtro);
			
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (movCol != null && movCol.size()>=1);
	}

	@Override
	public CarroceriaDTO buscarCarroceria(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		CarroceriaDTO carroceriaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			CarroceriaAssembler carroceriaAssemb = new CarroceriaAssembler(accesoBD);
			carroceriaDTO = carroceriaAssemb.getCarroceriaDTO((Carroceria) accesoBD.buscarPorId(Carroceria.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return carroceriaDTO;
	}

	@Override
	public CarroceriaDTO buscarCarroceria(String nombre) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		CarroceriaDTO carroceriaDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre.equals(\""+nombre+"\")";
			Collection movCol = accesoBD.buscarPorFiltro(Carroceria.class, filtro);
			CarroceriaAssembler carroceriaAssemb = new CarroceriaAssembler(accesoBD);
			if (movCol.size()>=1 ) {
				carroceriaDTO = carroceriaAssemb.getCarroceriaDTO((Carroceria)(movCol.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return carroceriaDTO;
	}

}
