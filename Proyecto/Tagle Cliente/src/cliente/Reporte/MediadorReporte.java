package cliente.Reporte;

import cliente.MediadorPrincipal;

public class MediadorReporte {
	
	private GUIReporte guiReporte;
	
	private MediadorPrincipal mediadorPrincipal;
	
	
	public MediadorReporte(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}
	
	public void mostrarReportes(){
		guiReporte = new GUIReporte(this);
		guiReporte.setVisible(true);
	}
}
