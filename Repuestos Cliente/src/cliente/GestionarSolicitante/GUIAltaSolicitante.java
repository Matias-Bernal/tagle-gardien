package cliente.GestionarSolicitante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import cliente.GestionarUsuario.MediadorUsuario;

public class GUIAltaSolicitante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombreSolicitante;
	private JTextField tFemail;
	private MediadorSolicitante medidador;
	private JComboBox<String> cBTSolicitante;
	private String[] tiposUsuarios;
	private int limite = 35;
	private JTextField tFTelefono;
	private JTextField tfTelefono;
	
	public GUIAltaSolicitante(final MediadorSolicitante medidador) {
		this.setMedidador(medidador);
		tiposUsuarios = new String[] {"Mostrador", "Mayorista", "Garantia", "Seguro" , "Taller Mecanico", "Taller Carroceria"};
		initialize();
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("AGREGAR SOLICITANTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreSolicitante = new JLabel("Nombre Solicitante");
		lblNombreSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSolicitante.setBounds(0, 39, 145, 20);
		contentPane.add(lblNombreSolicitante);
		
		tFNombreSolicitante = new JTextField();
		tFNombreSolicitante.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFNombreSolicitante.getText().length()>=limite){
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
			}
		});
		tFNombreSolicitante.setBounds(145, 39, 250, 20);
		contentPane.add(tFNombreSolicitante);
		tFNombreSolicitante.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setBounds(0, 70, 145, 20);
		contentPane.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(0, 100, 145, 20);
		contentPane.add(lblEmail);
		
		tFemail = new JTextField();
		tFemail.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFemail.getText().length()>=limite){
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
			}
		});
		tFemail.setBounds(145, 100, 250, 20);
		contentPane.add(tFemail);
		tFemail.setColumns(10);
		
		JButton btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crear();
			}
		});
		btnCrearUsuario.setBounds(226, 169, 110, 20);
		contentPane.add(btnCrearUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(51, 169, 110, 20);
		contentPane.add(btnCancelar);

		JLabel lblTipoSolicitante = new JLabel("Tipo Solicitante");
		lblTipoSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoSolicitante.setBounds(0, 11, 145, 20);
		contentPane.add(lblTipoSolicitante);
		contentPane.setVisible(true);
		
		cBTSolicitante = new JComboBox<String>();
		cBTSolicitante.setModel(new DefaultComboBoxModel<String>(tiposUsuarios));
		cBTSolicitante.setBounds(145, 11, 154, 20);
		contentPane.add(cBTSolicitante);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(145, 70, 250, 20);
		contentPane.add(tfTelefono);
		
	}
	
	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}

	public String[] getTiposUsuarios() {
		return tiposUsuarios;
	}

	public void setTiposUsuarios(String[] tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
	}
	
	public void crear(){
		if (tFNombreSolicitante.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
		}
	}

	public JTextField gettFTelefono() {
		return tFTelefono;
	}

	public void settFTelefono(JTextField tFTelefono) {
		this.tFTelefono = tFTelefono;
	}

	public MediadorSolicitante getMedidador() {
		return medidador;
	}

	public void setMedidador(MediadorSolicitante medidador) {
		this.medidador = medidador;
	}

}
