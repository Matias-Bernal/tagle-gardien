package cliente.GestionarProveedor;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import cliente.utils.JPanel_Whit_Image;
import comun.DTOs.FabricaDTO;
import comun.DTOs.ProveedorDTO;
import comun.DTOs.SucursalDTO;

public class GUIModificarProveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombreProveedor;
	private MediadorProveedor mediador;
	private int limite = 35;
	private ProveedorDTO proveedor;
	private JTextField tFTipoProveedor;
	private JTextField tFIdProveedor;
	
	public GUIModificarProveedor(final MediadorProveedor mediador, ProveedorDTO proveedor) {
		this.mediador = mediador;
		this.proveedor = proveedor;
		initialize();
		cargarDatos();
	}
	
	private void cargarDatos() {
		tFIdProveedor.setText(proveedor.getId().toString());
		if(proveedor instanceof SucursalDTO){
			tFTipoProveedor.setText("Sucursal");			
		}else{
			if(proveedor instanceof FabricaDTO){
				tFTipoProveedor.setText("Fabrica");			
			}else{
				tFTipoProveedor.setText("Alternativo");
			}
		}
		tFNombreProveedor.setText(proveedor.getNombre());
	}

	private void initialize() {
		setTitle("MODIFICAR PROVEEDOR");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/edit_registrante.png")));
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNombreSolicitante = new JLabel("Nombre Proveedor");
		lblNombreSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSolicitante.setBounds(0, 70, 145, 20);
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

			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		tFNombreProveedor.setBounds(145, 70, 250, 20);
		contentPane.add(tFNombreProveedor);
		tFNombreProveedor.setColumns(10);
		
		JButton btnCrearUsuario = new JButton("Modificar");
		btnCrearUsuario.setIcon(new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/edit.png")));
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifcar();
			}
		});
		btnCrearUsuario.setBounds(226, 169, 110, 20);
		contentPane.add(btnCrearUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(51, 169, 110, 20);
		contentPane.add(btnCancelar);

		JLabel lblTipoProveedor = new JLabel("Tipo Proveedor");
		lblTipoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoProveedor.setBounds(0, 40, 145, 20);
		contentPane.add(lblTipoProveedor);
		
		tFTipoProveedor = new JTextField();
		tFTipoProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFTipoProveedor.setDisabledTextColor(Color.BLACK);
		tFTipoProveedor.setEditable(false);
		tFTipoProveedor.setBounds(145, 40, 150, 20);
		contentPane.add(tFTipoProveedor);
		tFTipoProveedor.setColumns(10);
		
		JLabel lblIdproveedor = new JLabel("ID Proveedor");
		lblIdproveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdproveedor.setBounds(0, 10, 145, 20);
		contentPane.add(lblIdproveedor);
		
		tFIdProveedor = new JTextField();
		tFIdProveedor.setEditable(false);
		tFIdProveedor.setDisabledTextColor(Color.BLACK);
		tFIdProveedor.setColumns(10);
		tFIdProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFIdProveedor.setBounds(145, 10, 100, 20);
		contentPane.add(tFIdProveedor);
		contentPane.setVisible(true);
		
		contentPane.setVisible(true);
		
	}
	
	public void modifcar(){
		if (tFNombreProveedor.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}else{
			if(tFTipoProveedor.getText().equals("Fabrica")){
				if (mediador.modificarFabrica(proveedor,tFNombreProveedor.getText())){
					JOptionPane.showMessageDialog(contentPane,"Proveedor Fabrica Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
					dispose();
					mediador.actualizarDatosGestion();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al modificar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
				}
			}else{
				if(tFTipoProveedor.getText().equals("Sucursal")){
					if (mediador.modificarSucursal(proveedor,tFNombreProveedor.getText())){
						JOptionPane.showMessageDialog(contentPane,"Proveedor Sucursal Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
						dispose();
						mediador.actualizarDatosGestion();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}else{
					if(tFTipoProveedor.getText().equals("Alternativo")){
						if (mediador.modificarAlternativo(proveedor,tFNombreProveedor.getText())){
							JOptionPane.showMessageDialog(contentPane,"Proveedor Alternativo Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
							dispose();
							mediador.actualizarDatosGestion();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al modificar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
						}
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar proveedor. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarProveedor.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}
			}
		}
	}
}