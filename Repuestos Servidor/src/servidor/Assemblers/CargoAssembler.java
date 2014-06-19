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
import servidor.Persistencia.Dominio.Cargo;

import comun.DTOs.CargoDTO;

public class CargoAssembler {

	private AccesoBD accesoBD;

	public CargoAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public CargoDTO getCargoDTO(Cargo cargo) {
		CargoDTO CargoDTO = new CargoDTO();
		CargoDTO.setId(cargo.getId());
		CargoDTO.setNombre(cargo.getNombre());
		return CargoDTO;
	}

	public Cargo getCargo(CargoDTO cargoDTO) {
		Cargo Cargo = (Cargo) accesoBD.buscarPorId(Cargo.class, cargoDTO.getId());
		Cargo.setId(cargoDTO.getId());
		Cargo.setNombre(cargoDTO.getNombre());
		return Cargo;
	}

	public Cargo getCargoNuevo(CargoDTO cargoDTO) {
		Cargo Cargo = new Cargo();
		Cargo.setId(cargoDTO.getId());
		Cargo.setNombre(cargoDTO.getNombre());
		return Cargo;
	}
}
