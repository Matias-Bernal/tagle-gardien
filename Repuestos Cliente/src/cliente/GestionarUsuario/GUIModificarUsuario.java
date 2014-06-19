/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package cliente.GestionarUsuario;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import comun.DTOs.UsuarioRepuestoDTO;

public class GUIModificarUsuario extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_usuario;
	private JPasswordField passwordField;
	private JPasswordField newpassword;
	private JTextField tFemail;
	private MediadorUsuario medidador;
	private JComboBox<String> comboBox;
	private String[] tiposUsuarios = new String[] {"Administrativo", "Encargado Repuesto"};
	private int limite = 35;
	private UsuarioRepuestoDTO usuario;

	public GUIModificarUsuario(final MediadorUsuario medidador, UsuarioRepuestoDTO usuario) {
		this.medidador = medidador;
		this.setUsuario(usuario);
		initialize();
		tFnombre_usuario.setText(usuario.getNombre_usuario());
		tFemail.setText(usuario.getEmail());
		comboBox.setSelectedItem(usuario.getTipo());
		
		SetVisible(true);
	}

	
	private void initialize() {
		setTitle("MODIFICAR USUARIO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre De Usuario");
		lblNombreDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreDeUsuario.setBounds(0, 10, 141, 20);
		contentPane.add(lblNombreDeUsuario);
		
		tFnombre_usuario = new JTextField();
		tFnombre_usuario.setEditable(false);
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
			}
		});
		tFnombre_usuario.setBounds(144, 10, 250, 20);
		contentPane.add(tFnombre_usuario);
		tFnombre_usuario.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase�a Anterior");
		lblContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrasea.setBounds(0, 40, 141, 20);
		contentPane.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (passwordField.getText().length()>=limite){
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
			}
		});
		passwordField.setBounds(144, 40, 250, 20);
		contentPane.add(passwordField);
		
		newpassword = new JPasswordField();
		newpassword.addKeyListener(new KeyListener() {
			@SuppressWarnings("deprecation")
			public void keyTyped(KeyEvent e) {
				if (newpassword.getText().length()>=limite){
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
			}
		});
		newpassword.setBounds(144, 70, 250, 20);
		contentPane.add(newpassword);
		
		JLabel lblConfirmar = new JLabel("Nueva Contrase\u00F1a");
		lblConfirmar.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmar.setBounds(0, 70, 141, 20);
		contentPane.add(lblConfirmar);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(0, 100, 141, 20);
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
					modificar();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {			
			}
		});
		tFemail.setBounds(144, 100, 250, 20);
		contentPane.add(tFemail);
		tFemail.setColumns(10);
		
		JButton btnModificarUsuario = new JButton("Modificar");
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificarUsuario.setBounds(226, 169, 110, 20);
		contentPane.add(btnModificarUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(51, 169, 110, 20);
		contentPane.add(btnCancelar);

		JLabel lblTipoUsuario = new JLabel("Tipo Usuario");
		lblTipoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoUsuario.setBounds(10, 131, 131, 20);
		contentPane.add(lblTipoUsuario);
		contentPane.setVisible(true);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(tiposUsuarios));
		comboBox.setBounds(144, 131, 154, 20);
		contentPane.add(comboBox);
		
	}
		
	@SuppressWarnings("deprecation")
	private boolean chequearUsuario() {
		return (medidador.login(tFnombre_usuario.getText(),passwordField.getText()));
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
	
	@SuppressWarnings("deprecation")
	public void modificar(){
		if (tFnombre_usuario.getText().isEmpty() || passwordField.getText().isEmpty() || tFemail.getText().isEmpty() || newpassword.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			if (chequearUsuario()){
				UsuarioRepuestoDTO usuariomodif = new UsuarioRepuestoDTO(tFnombre_usuario.getText(), newpassword.getText(), tFemail.getText(),comboBox.getSelectedItem().toString());
				if (medidador.modificar(usuariomodif)){
					JOptionPane.showMessageDialog(contentPane,"Usuario Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
					medidador.actualizarDatosGestion();
					dispose();
				}
			}else{
				JOptionPane.showMessageDialog(contentPane,"Las contrase�a anterior no coincide.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	public UsuarioRepuestoDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioRepuestoDTO usuario) {
		this.usuario = usuario;
	}
}