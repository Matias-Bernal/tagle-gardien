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

import cliente.utils.JPanel_Whit_Image;

import java.awt.Toolkit;

import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;

public class GUIAltaProveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombreProveedor;
	private MediadorProveedor mediador;
	private JComboBox<String> cBTProveedor;
	private Vector<String> tiposProveedores;
	private int limite = 35;
	
	public GUIAltaProveedor(final MediadorProveedor mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {
		tiposProveedores = new Vector<String>();
		tiposProveedores.add("Fabrica");
		tiposProveedores.add("Sucursal");
		tiposProveedores.add("Alternativo");
	}

	private void initialize() {
		setTitle("AGREGAR PROVEEDOR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/add_registrante.png")));
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNombreSolicitante = new JLabel("Nombre Proveedor");
		lblNombreSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSolicitante.setBounds(0, 39, 145, 20);
		contentPane.add(lblNombreSolicitante);
		
		tFNombreProveedor = new JTextField();
		tFNombreProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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
		btnCrearUsuario.setIcon(new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/save.png")));
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crear();
			}
		});
		btnCrearUsuario.setBounds(226, 169, 110, 20);
		contentPane.add(btnCrearUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
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
		cBTProveedor.setModel(new DefaultComboBoxModel(tiposProveedores));
		cBTProveedor.setBounds(145, 11, 154, 20);
		contentPane.add(cBTProveedor);
		
		contentPane.setVisible(true);
		
	}
	
	public void crear(){
		if (tFNombreProveedor.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}else{
			if(cBTProveedor.getSelectedItem().equals("Fabrica")){
				if (mediador.agregarFabrica(tFNombreProveedor.getText())){
					JOptionPane.showMessageDialog(contentPane,"Proveedor Fabrica Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
					mediador.actualizarDatosGestion();
					dispose();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al agregar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
				}
			}else{
				if(cBTProveedor.getSelectedItem().equals("Sucursal")){
					if (mediador.agregarSucursal(tFNombreProveedor.getText())){
						JOptionPane.showMessageDialog(contentPane,"Proveedor Sucursal Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al agregar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}else{
					if(cBTProveedor.getSelectedItem().equals("Alternativo")){
						if (mediador.agregarAlternativo(tFNombreProveedor.getText())){
							JOptionPane.showMessageDialog(contentPane,"Proveedor Alternativo Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
							mediador.actualizarDatosGestion();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al agregar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
						}
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al agregar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}
			}
		}
	}

}