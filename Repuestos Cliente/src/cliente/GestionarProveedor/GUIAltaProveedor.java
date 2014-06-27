package cliente.GestionarProveedor;

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

public class GUIAltaProveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombreProveedor;
	private MediadorProveedor medidador;
	private JComboBox<String> cBTProveedor;
	private String[] tiposProveedores;
	private int limite = 35;
	
	public GUIAltaProveedor(final MediadorProveedor medidador) {
		this.setMedidador(medidador);
		tiposProveedores = new String[] {"Mostrador", "Mayorista", "Garantia", "Seguro" , "Taller Mecanico", "Taller Carroceria"};
		initialize();
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("AGREGAR PROVEEDOR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreSolicitante = new JLabel("Nombre Proveedor");
		lblNombreSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSolicitante.setBounds(0, 39, 145, 20);
		contentPane.add(lblNombreSolicitante);
		
		tFNombreProveedor = new JTextField();
		tFNombreProveedor.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFNombreProveedor.getText().length()>=limite){
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
		tFNombreProveedor.setBounds(145, 39, 250, 20);
		contentPane.add(tFNombreProveedor);
		tFNombreProveedor.setColumns(10);
		
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

		JLabel lblTipoProveedor = new JLabel("Tipo Proveedor");
		lblTipoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoProveedor.setBounds(0, 11, 145, 20);
		contentPane.add(lblTipoProveedor);
		contentPane.setVisible(true);
		
		cBTProveedor = new JComboBox<String>();
		cBTProveedor.setModel(new DefaultComboBoxModel(new String[] {"Fabrica", "Sucursal", "Alternativo"}));
		cBTProveedor.setBounds(145, 11, 154, 20);
		contentPane.add(cBTProveedor);
		
	}
	
	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}

	public String[] getTiposUsuarios() {
		return tiposProveedores;
	}

	public void setTiposUsuarios(String[] tiposUsuarios) {
		this.tiposProveedores = tiposUsuarios;
	}
	
	public void crear(){
		if (tFNombreProveedor.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
		}
	}

	public MediadorProveedor getMedidador() {
		return medidador;
	}

	public void setMedidador(MediadorProveedor medidador) {
		this.medidador = medidador;
	}

}