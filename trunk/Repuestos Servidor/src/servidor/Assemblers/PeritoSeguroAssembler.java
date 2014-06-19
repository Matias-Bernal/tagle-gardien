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
import servidor.Persistencia.Dominio.PeritoSeguro;
import comun.DTOs.PeritoSeguroDTO;

public class PeritoSeguroAssembler {

	private AccesoBD accesoBD;

	public PeritoSeguroAssembler (AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public PeritoSeguroDTO getPeritoSeguroDTO(PeritoSeguro peritoSeguro) {
		PeritoSeguroDTO peritoSeguroDTO = new PeritoSeguroDTO();
		peritoSeguroDTO.setId(peritoSeguro.getId());
		PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
		if(peritoSeguro.getPerito()!=null)
			peritoSeguroDTO.setPerito(peritoAssemb.getPeritoDTO(peritoSeguro.getPerito()));
		SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
		if(peritoSeguro.getSeguro()!=null)
			peritoSeguroDTO.setSeguro(seguroAssemb.getSeguroDTO(peritoSeguro.getSeguro()));
		return peritoSeguroDTO;
	}

	public PeritoSeguro getPeritoSeguro(PeritoSeguroDTO peritoSeguroDTO) {
		PeritoSeguro peritoSeguro = (PeritoSeguro) accesoBD.buscarPorId(PeritoSeguro.class, peritoSeguroDTO.getId());
		peritoSeguro.setId(peritoSeguroDTO.getId());
		PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
		if(peritoSeguroDTO.getPerito()!=null)
			peritoSeguro.setPerito(peritoAssemb.getPerito(peritoSeguroDTO.getPerito()));
		SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
		if(peritoSeguroDTO.getSeguro()!=null)
			peritoSeguro.setSeguro(seguroAssemb.getSeguro(peritoSeguroDTO.getSeguro()));
		return peritoSeguro;
	}

	public PeritoSeguro getPeritoSeguroNuevo(PeritoSeguroDTO peritoSeguroDTO) {
		PeritoSeguro peritoSeguro = new PeritoSeguro();
		peritoSeguro.setId(peritoSeguroDTO.getId());
		PeritoAssembler peritoAssemb = new PeritoAssembler(accesoBD);
		if(peritoSeguroDTO.getPerito()!=null)
			peritoSeguro.setPerito(peritoAssemb.getPerito(peritoSeguroDTO.getPerito()));
		SeguroAssembler seguroAssemb = new SeguroAssembler(accesoBD);
		if(peritoSeguroDTO.getSeguro()!=null)
			peritoSeguro.setSeguro(seguroAssemb.getSeguro(peritoSeguroDTO.getSeguro()));
		return peritoSeguro;
	}
}
