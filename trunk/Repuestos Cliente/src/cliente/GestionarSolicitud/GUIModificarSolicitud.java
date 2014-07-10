package cliente.GestionarSolicitud;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class GUIModificarSolicitud extends JDialog {

	private static final long serialVersionUID = 1L;
	protected MediadorSolicitud mediador;
	
	public GUIModificarSolicitud(MediadorSolicitud mediador) {
		this.mediador = mediador;
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}

}