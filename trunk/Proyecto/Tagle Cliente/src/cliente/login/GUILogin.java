package cliente.login;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cliente.MediadorPrincipal;

import java.awt.event.KeyAdapter;

public class GUILogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JFrame LoginFrame;
	private JTextField tf_nombre_usuario;
	private JPanel jPanel_sur;
	private JPasswordField pf_contrasenia;
	private int limite  = 35;

	public GUILogin(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
		initialize();
	}
	
	private void initialize() {
		
		LoginFrame = new JFrame();
		LoginFrame.setTitle("LOGIN");
		LoginFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Matyas\\Pictures\\tagle.ico"));
		LoginFrame.setResizable(false);
		LoginFrame.setMinimumSize(new Dimension(375, 225));
		LoginFrame.setLocationRelativeTo(null);
		LoginFrame.getContentPane().setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setBounds(109, 10, 150, 20);
		LoginFrame.getContentPane().add(lblNombreDeUsuario);
		
		tf_nombre_usuario = new JTextField(limite);
		tf_nombre_usuario.setBounds(59, 35, 250, 20);
		tf_nombre_usuario.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tf_nombre_usuario.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		tf_nombre_usuario.setHorizontalAlignment(SwingConstants.CENTER);
		LoginFrame.getContentPane().add(tf_nombre_usuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(109, 65, 150, 20);
		LoginFrame.getContentPane().add(lblContrasea);
		
		pf_contrasenia = new JPasswordField(limite);
		pf_contrasenia.setBounds(59, 89, 250, 20);
		pf_contrasenia.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (pf_contrasenia.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					login();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		pf_contrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		LoginFrame.getContentPane().add(pf_contrasenia);
		
		jPanel_sur = new JPanel();
		jPanel_sur.setBounds(57, 112, 254, 88);
		jPanel_sur.setBorder(null);
		LoginFrame.getContentPane().add(jPanel_sur);
		GridBagLayout gbl_jPanel_sur = new GridBagLayout();
		gbl_jPanel_sur.columnWidths = new int[] {90, 35, 90, 0};
		gbl_jPanel_sur.rowHeights = new int[]{30, 0, 23, 0};
		gbl_jPanel_sur.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_jPanel_sur.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		jPanel_sur.setLayout(gbl_jPanel_sur);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 0;
		gbc_btnAceptar.gridy = 1;
		jPanel_sur.add(btnAceptar, gbc_btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame.dispose();
			}
		});
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 0);
		gbc_btnSalir.gridx = 2;
		gbc_btnSalir.gridy = 1;
		jPanel_sur.add(btnSalir, gbc_btnSalir);
	}
	public void setVisible(boolean b) {
		LoginFrame.setVisible(b);		
	}

	public void login (){
		String usuario = tf_nombre_usuario.getText();
		String contrasenia = pf_contrasenia.getText();
		if (usuario.isEmpty() || usuario=="" || contrasenia.isEmpty() || contrasenia == ""){
			JOptionPane.showMessageDialog(LoginFrame,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			try {
				if (mediadorPrincipal.acceso(usuario,contrasenia)) {
					setVisible(false);
				}else{
					JOptionPane.showMessageDialog(LoginFrame,"Usuario/Contraseña no validos.","Error",JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(LoginFrame,"Error al iniciar sesion.","Error",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	public void reiniciar() {
		tf_nombre_usuario.setText("");
		pf_contrasenia.setText("");
		setVisible(true);
	}
}
