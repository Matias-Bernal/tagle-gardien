package cliente.GestionarSolicitud;

import javax.swing.JFrame;

public class GUIGestionSolicitud extends JFrame {

	private static final long serialVersionUID = 1L;
	protected MediadorSolicitud mediador;
	
	public GUIGestionSolicitud(MediadorSolicitud mediador) {
		this.mediador = mediador;
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
	}
}
