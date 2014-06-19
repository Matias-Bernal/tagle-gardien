package cliente.GestionarSolicitud;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIAltaSolicitud extends JFrame {

	private static final long serialVersionUID = 1L;
	protected MediadorSolicitud mediador;
	private JComboBox cbTSolicitante;
	private JComboBox cbNSolicitante;
	private JTextField textField;
	
	public GUIAltaSolicitud(final MediadorSolicitud mediador) {
		this.mediador = mediador;
		getContentPane().setLayout(null);
		
		JPanel panelSolicitante = new JPanel();
		panelSolicitante.setBounds(10, 11, 927, 45);
		getContentPane().add(panelSolicitante);
		panelSolicitante.setLayout(null);
		
		JLabel lblSolicitante = new JLabel("Solicitante");
		lblSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitante.setBounds(10, 10, 115, 20);
		panelSolicitante.add(lblSolicitante);
		
		cbTSolicitante = new JComboBox();
		cbTSolicitante.setBounds(135, 10, 200, 20);
		panelSolicitante.add(cbTSolicitante);
		
		cbNSolicitante = new JComboBox();
		cbNSolicitante.setBounds(342, 10, 430, 20);
		panelSolicitante.add(cbNSolicitante);
		
		JButton btnNuevoSolicitante = new JButton("Nuevo Solicitante");
		btnNuevoSolicitante.setBounds(780, 10, 135, 20);
		panelSolicitante.add(btnNuevoSolicitante);
		
		JPanel panelProveedor = new JPanel();
		panelProveedor.setBounds(10, 70, 927, 45);
		getContentPane().add(panelProveedor);
		panelProveedor.setLayout(null);
		
		JLabel label = new JLabel("Proveedor");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 10, 115, 20);
		panelProveedor.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(135, 10, 200, 20);
		panelProveedor.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(342, 10, 430, 20);
		panelProveedor.add(comboBox_1);
		
		JButton button = new JButton("Nuevo Proveedor");
		button.setBounds(780, 10, 135, 20);
		panelProveedor.add(button);
		
		JPanel panelPieza = new JPanel();
		panelPieza.setBounds(10, 126, 927, 184);
		getContentPane().add(panelPieza);
		panelPieza.setLayout(null);
		
		JLabel lblNumeroPieza = new JLabel("Numero Pieza");
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPieza.setBounds(10, 11, 115, 20);
		panelPieza.add(lblNumeroPieza);
		
		textField = new JTextField();
		textField.setBounds(135, 10, 200, 20);
		panelPieza.add(textField);
		textField.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(10, 40, 115, 20);
		panelPieza.add(lblDescripcion);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(135, 40, 300, 97);
		panelPieza.add(editorPane);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(10, 150, 115, 20);
		panelPieza.add(lblCantidad);
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 506);
	}
}
