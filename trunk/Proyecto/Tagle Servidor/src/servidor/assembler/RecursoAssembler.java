package servidor.assembler;

import servidor.persistencia.AccesoBD;
import servidor.persistencia.dominio.Recurso;

import common.DTOs.RecursoDTO;

public class RecursoAssembler {
	
	private AccesoBD accesoBD;

	public RecursoAssembler(AccesoBD accesoBD) {
		this.accesoBD = accesoBD;
	}

	public RecursoDTO getRecursoDTO(Recurso recurso) {
		RecursoDTO recursoDTO = new RecursoDTO();
		recursoDTO.setId(recurso.getId());
		recursoDTO.setFecha_recurso(recurso.getFecha_recurso());
		recursoDTO.setNumero_recurso(recurso.getNumero_recurso());
		Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
		if (recurso.getPedido_pieza()!=null)
			recursoDTO.setPedido_pieza(pedido_piezaAssemb.getPedido_PiezaDTO(recurso.getPedido_pieza()));
		return recursoDTO;
	}

	public Recurso getRecurso(RecursoDTO recursoDTO) {
		Recurso recurso = (Recurso) accesoBD.buscarPorId(Recurso.class, recursoDTO.getId());
		recurso.setId(recursoDTO.getId());
		recurso.setFecha_recurso(recursoDTO.getFecha_recurso());
		recurso.setNumero_recurso(recursoDTO.getNumero_recurso());
		Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
		if (recursoDTO.getPedido_pieza()!=null)
			recurso.setPedido_pieza(pedido_piezaAssemb.getPedido_Pieza(recursoDTO.getPedido_pieza()));
		return recurso;
	}
	
	public Recurso getRecursoNuevo(RecursoDTO recursoDTO) {
		Recurso recurso = new Recurso();
		recurso.setId(recursoDTO.getId());
		recurso.setFecha_recurso(recursoDTO.getFecha_recurso());
		recurso.setNumero_recurso(recursoDTO.getNumero_recurso());
		Pedido_PiezaAssembler pedido_piezaAssemb = new Pedido_PiezaAssembler(accesoBD);
		if (recursoDTO.getPedido_pieza()!=null)
			recurso.setPedido_pieza(pedido_piezaAssemb.getPedido_Pieza(recursoDTO.getPedido_pieza()));
		return recurso;
	}

}