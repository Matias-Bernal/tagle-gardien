package cliente.GestionarRegistrante;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GUIAltaRegistrante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_usuario;
	private MediadorRegistrante mediador;
	private JComboBox<String> comboBox;
	private Vector<String> tiposRegistrantes;
	private int limite = 35;

	public GUIAltaRegistrante(final MediadorRegistrante mediador, String nombre_usuario, String tipo) {
		this.mediador = mediador;
		initialize();
		tFnombre_usuario.setText(nombre_usuario);
		comboBox.setSelectedItem(tipo);
		SetVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIAltaRegistrante(final MediadorRegistrante mediador) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaRegistrante.class.getResource("/cliente/imagenes/del_user.ico")));
		this.mediador = mediador;
		initialize();
	}
	
	private void initialize() {
		setTitle("AGREGAR REGISTRANTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 210);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDelRegistrante = new JLabel("Nombre Del Registrante");
		lblNombreDelRegistrante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDelRegistrante.setBounds(0, 10, 130, 20);
		contentPane.add(lblNombreDelRegistrante);
		
		tFnombre_usuario = new JTextField();
		tFnombre_usuario.setBounds(135, 10, 250, 20);
		tFnombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					crear();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(tFnombre_usuario);
		tFnombre_usuario.setColumns(10);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setBounds(0, 40, 130, 20);
		contentPane.add(lblTipo);
		
		JButton btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crear();
			}
		});
		btnCrearUsuario.setBounds(225, 140, 110, 20);
		contentPane.add(btnCrearUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(50, 140, 110, 20);
		contentPane.add(btnCancelar);
		
		
		tiposRegistrantes = new Vector<String>();
		tiposRegistrantes.add("");
		tiposRegistrantes.add("Agente");
		tiposRegistrantes.add("Entidad");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(tiposRegistrantes));
		comboBox.setBounds(135, 41, 253, 20);
		contentPane.add(comboBox);

		contentPane.setVisible(true);
		
	}
	
	private void crear() {
		if (!tFnombre_usuario.getText().isEmpty() && comboBox.getSelectedItem().toString()!= "" ){
			if (mediador.nuevoRegistrante(tFnombre_usuario.getText(), comboBox.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(contentPane,"Registrante Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Nombre de Registrante vacio.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}
}
