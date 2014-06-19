package cliente.GestionarReclamo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUIGestionReclamoAgente extends JFrame{


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MediadorReclamo mediadorReclamo;
	
	public GUIGestionReclamoAgente(MediadorReclamo mediadorReclamo) {
		this.setMediadorReclamo(mediadorReclamo);
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("GESTIONAR RECLAMO AGENTE");
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}

	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}

	public MediadorReclamo getMediadorReclamo() {
		return mediadorReclamo;
	}

	public void setMediadorReclamo(MediadorReclamo mediadorReclamo) {
		this.mediadorReclamo = mediadorReclamo;
	}

	public void actualizarDatos() {
		// TODO Auto-generated method stub
		
	}

}
