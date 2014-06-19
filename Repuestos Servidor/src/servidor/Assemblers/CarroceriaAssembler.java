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
package servidor.Assemblers;

import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.Carroceria;

import comun.DTOs.CarroceriaDTO;

public class CarroceriaAssembler {
	
	private AccesoBD accesoBD;

	public CarroceriaAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public CarroceriaDTO getCarroceriaDTO(Carroceria carroceria) {
		CarroceriaDTO carroceriaDTO = new CarroceriaDTO();
		carroceriaDTO.setId(carroceria.getId());
		carroceriaDTO.setNombre(carroceria.getNombre());
		return carroceriaDTO;
	}

	public Carroceria getCarroceria(CarroceriaDTO carroceriaDTO) {
		Carroceria carroceria = (Carroceria) accesoBD.buscarPorId(Carroceria.class, carroceriaDTO.getId());
		carroceria.setId(carroceriaDTO.getId());
		carroceria.setNombre(carroceriaDTO.getNombre());
		return carroceria;
	}

	public Carroceria getCarroceriaNuevo(CarroceriaDTO carroceriaDTO) {
		Carroceria carroceria = new Carroceria();
		carroceria.setId(carroceriaDTO.getId());
		carroceria.setNombre(carroceriaDTO.getNombre());
		return carroceria;
	}

}