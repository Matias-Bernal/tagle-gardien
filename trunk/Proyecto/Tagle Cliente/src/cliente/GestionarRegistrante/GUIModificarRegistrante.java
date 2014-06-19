package cliente.GestionarRegistrante;

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

import common.DTOs.AgenteDTO;
import common.DTOs.EntidadDTO;
import common.DTOs.RegistranteDTO;

public class GUIModificarRegistrante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_usuario;
	private MediadorRegistrante mediador;
	private JComboBox<String> comboBox;
	private Vector<String> tiposRegistrantes;
	private int limite = 35;

	private RegistranteDTO registrante;
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIModificarRegistrante(final MediadorRegistrante mediadorRegistrante, AgenteDTO agente) {
		this.mediador = mediadorRegistrante;
		registrante = agente;
		setTitle("MODIFICAR AGENTE");
		initialize();
		tFnombre_usuario.setText(registrante.getNombre_registrante());
		comboBox.setSelectedItem("Agente");
		
		SetVisible(true);
	}

	public GUIModificarRegistrante(final MediadorRegistrante mediadorRegistrante,	EntidadDTO entidad) {
		this.mediador = mediadorRegistrante;
		registrante = entidad;
		setTitle("MODIFICAR ENTIDAD");
		initialize();
		tFnombre_usuario.setText(registrante.getNombre_registrante());
		comboBox.setSelectedItem("Entidad");
		
		SetVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
					modificar();
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
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(225, 140, 110, 20);
		contentPane.add(btnModificar);
		
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
		
		comboBox = new JComboBox<String>();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel<String>(tiposRegistrantes));
		comboBox.setBounds(135, 41, 253, 20);
		contentPane.add(comboBox);
	
	}


	private void modificar() {
		if (tFnombre_usuario.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			registrante.setNombre_registrante(tFnombre_usuario.getText());
				if (mediador.esAgente(registrante.getId().toString())){
					if (mediador.modificarAgente((AgenteDTO)registrante)){
						JOptionPane.showMessageDialog(contentPane,"Agente Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar agente","Error",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					if (mediador.modificarEntidad((EntidadDTO) registrante)){
						JOptionPane.showMessageDialog(contentPane,"Entidad Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar entidad","Error",JOptionPane.ERROR_MESSAGE);
					}
				}

		}
		
	}
	
	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}

}
