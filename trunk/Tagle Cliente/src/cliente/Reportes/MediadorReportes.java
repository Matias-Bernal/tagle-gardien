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
package cliente.Reportes;

import java.util.Vector;

import cliente.MediadorAccionesIniciarPrograma;
import cliente.MediadorPrincipal;

import common.DTOs.Orden_ReclamoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RegistranteDTO;
import common.GestionarAgente.IControlAgente;
import common.GestionarEntidad.IControlEntidad;
import common.GestionarOrden_Reclamo.IControlOrden_Reclamo;
import common.GestionarPedido_Pieza.IControlPedido_Pieza;
import common.GestionarReclamo.IControlReclamo;

public class MediadorReportes {
	
	private MediadorPrincipal mediadorPrincipal;

	private GUIReportesControl guiReporteControl;
	private GUIReportesGestion guiReporteGestion;
	private GUIReportePiezasLlegadas guiReportePiezasLlegadas;
	private GUIReportePiezasDevueltas guiReportePiezasDevueltas;
	private GUIReportePiezasSinLlegar guiReportePiezasSinLlegar;
	private GUIReportePiezasLlegadasSinTurno guiReportePiezasLlegadasSinTurno;
	private GUIReporteSDPSinNP guiReporteSDPSinNP;
	private GUIReporteDiasDesdePedidoFabrica guiReporteDiasDesdePedidoFabrica; 
	private GUIReporteDiasDesdeRecepcionFabrica guiReporteDiasDesdeRecepcionFabrica;
	private GUIReporteDiasDesdeRecepcionFabricaYTurno guiReporteDiasDesdeRecepcionFabricaYTurno;
	private GUIReporteDiasDesdeCierreOrdenYTurno guiReporteDiasDesdeCierreOrdenYTurno;
	private GUIReporteDiasFechaRecursoYCierreOrden guiReporteDiasFechaRecursoYCierreOrden;
	private GUIReporteDiasFechaReclamoYFechaDevolucion guiReporteDiasFechaReclamoYFechaDevolucion;
	private GUIReporteDiasFechaReclamo_Turnos guiReporteDiasFechaReclamo_Turnos;
	private GUIReporteDiasPiezasLlegadas_PiezasDevueltas guiReporteDiasPiezasLlegadas_PiezasDevueltas;
	private GUIReporteManoDeObra guiReporteManoDeObra;
	private GUIReporteRecurso_CierreOrden guiReporteRecurso_CierreOrden;
	private GUIReporteOrdenSinSDP guiReporteOrdenSinSDP;
	
	
	public MediadorReportes(MediadorPrincipal mediadorPrincipal) {
		this.setMediadorPrincipal(mediadorPrincipal);
	}
	
	public void mostrarReportesControl(){
		guiReporteControl = new GUIReportesControl(this);
		guiReporteControl.setVisible(true);
	}

	public void mostrarReportesGestion(){
		guiReporteGestion = new GUIReportesGestion(this);
		guiReporteGestion.setVisible(true);
	}

	public void mostrarReportePiezasLlegadas() {
		guiReportePiezasLlegadas = new GUIReportePiezasLlegadas(this);
		guiReportePiezasLlegadas.setVisible(true);		
	}

	public void mostrarReportePiezasDevueltas() {
		guiReportePiezasDevueltas = new GUIReportePiezasDevueltas(this);
		guiReportePiezasDevueltas.setVisible(true);			
	}

	public void mostrarReportePiezasSinLlegar() {
		guiReportePiezasSinLlegar = new GUIReportePiezasSinLlegar(this);
		guiReportePiezasSinLlegar.setVisible(true);		
	}

	public void mostrarPiezasLlegadasSinTurno() {
		guiReportePiezasLlegadasSinTurno = new GUIReportePiezasLlegadasSinTurno(this);
		guiReportePiezasLlegadasSinTurno.setVisible(true);	
	}

	public void mostrarOrdenSinSDP() {
		guiReporteOrdenSinSDP = new GUIReporteOrdenSinSDP(this);
		guiReporteOrdenSinSDP.setVisible(true);	
	}
	
	public void mostrarSDPSinNP() {
		guiReporteSDPSinNP = new GUIReporteSDPSinNP(this);
		guiReporteSDPSinNP.setVisible(true);	
	}

	public void mostrarDiasDesdePedidoFabrica() {
		guiReporteDiasDesdePedidoFabrica = new GUIReporteDiasDesdePedidoFabrica(this);
		guiReporteDiasDesdePedidoFabrica.setVisible(true);	
	}

	public void mostrarDiasDesdeRecepcionFabrica() {
		guiReporteDiasDesdeRecepcionFabrica = new GUIReporteDiasDesdeRecepcionFabrica(this);
		guiReporteDiasDesdeRecepcionFabrica.setVisible(true);		
	}

	public void mostrarDiasDesdeRecepcionFabricaYTurno() {
		guiReporteDiasDesdeRecepcionFabricaYTurno = new GUIReporteDiasDesdeRecepcionFabricaYTurno(this);
		guiReporteDiasDesdeRecepcionFabricaYTurno.setVisible(true);	
	}

	public void mostrarDiasDesdeCierreOrdenYTurno() {
		guiReporteDiasDesdeCierreOrdenYTurno = new GUIReporteDiasDesdeCierreOrdenYTurno(this);
		guiReporteDiasDesdeCierreOrdenYTurno.setVisible(true);	
	}

	public void mostrarDiasFechaRecursoYCierreOrden() {
		guiReporteDiasFechaRecursoYCierreOrden = new GUIReporteDiasFechaRecursoYCierreOrden(this);
		guiReporteDiasFechaRecursoYCierreOrden.setVisible(true);	
	}

	public void mostrarDiasFechaReclamoYFechaDevolucion() {
		guiReporteDiasFechaReclamoYFechaDevolucion = new GUIReporteDiasFechaReclamoYFechaDevolucion(this);
		guiReporteDiasFechaReclamoYFechaDevolucion.setVisible(true);	
	}

	public void mostrarDiasFechaReclamo_Turnos() {
		guiReporteDiasFechaReclamo_Turnos = new GUIReporteDiasFechaReclamo_Turnos(this);
		guiReporteDiasFechaReclamo_Turnos.setVisible(true);	
	}

	public void mostrarDiasPiezasLlegadas_PiezasDevueltas() {
		guiReporteDiasPiezasLlegadas_PiezasDevueltas = new GUIReporteDiasPiezasLlegadas_PiezasDevueltas(this);
		guiReporteDiasPiezasLlegadas_PiezasDevueltas.setVisible(true);	
	}

	public void mostrarManoDeObra() {
		guiReporteManoDeObra = new GUIReporteManoDeObra(this);
		guiReporteManoDeObra.setVisible(true);	
	}

	public void mostrarRecurso_CierreOrden() {
		guiReporteRecurso_CierreOrden = new GUIReporteRecurso_CierreOrden(this);
		guiReporteRecurso_CierreOrden.setVisible(true);	
	}

	
	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public Vector<Pedido_PiezaDTO> obtenerPedido_Piezas() {
		Vector<Pedido_PiezaDTO> result = new Vector<Pedido_PiezaDTO>();
		IControlPedido_Pieza iControlPedido_Pieza = MediadorAccionesIniciarPrograma.getControlPedido_Pieza();
		try {
			result = iControlPedido_Pieza.obtenerPedido_Pieza();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean esEntidad(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlEntidad iControlEntidad = MediadorAccionesIniciarPrograma.getControlEntidad();
			res = iControlEntidad.existeEntidad(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean esAgente(RegistranteDTO registrante) {
		boolean res = false;
		try {
			IControlAgente iControlAgente = MediadorAccionesIniciarPrograma.getControlAgente();
			res = iControlAgente.existeAgente(registrante.getNombre_registrante());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public Vector<ReclamoDTO> obtenerReclamos() {
		Vector<ReclamoDTO> result = new Vector<ReclamoDTO>();
		IControlReclamo iControlReclamo = MediadorAccionesIniciarPrograma.getControlReclamo();
		try {
			result = iControlReclamo.obtenerReclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Vector<Orden_ReclamoDTO> obtenerOrdenes() {
		Vector<Orden_ReclamoDTO> result = new Vector<Orden_ReclamoDTO>();
		IControlOrden_Reclamo iControlOrden_Reclamo = MediadorAccionesIniciarPrograma.getControlOrden_Reclamo();
		try {
			result = iControlOrden_Reclamo.obtenerOrdenes_Reclamos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
