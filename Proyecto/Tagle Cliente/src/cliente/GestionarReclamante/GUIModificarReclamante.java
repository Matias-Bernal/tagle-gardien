package cliente.GestionarReclamante;

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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import common.DTOs.ReclamanteDTO;

public class GUIModificarReclamante extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFnombre_reclamante;
	private MediadorReclamante mediador;
	private JTextField tFEmail;
	private JComboBox<String> comboBox_telefonos; 
	private DefaultComboBoxModel<String> comboBOX_Modelo;
	private Vector<String> telefonos;
	private JRadioButton rdbtnFijo;
	private JRadioButton rdbtnCelular;
	private JTextField tfDni;
	private int limite = 50;
	private int limiteDNI = 10;
	private ReclamanteDTO reclamante;

	public GUIModificarReclamante(MediadorReclamante mediadorReclamante, ReclamanteDTO reclamante, Vector<String> telefonos) {
		this.mediador = mediadorReclamante;
		this.reclamante = reclamante;
		this.telefonos = new Vector<String>();
		initialize();
		
		tFnombre_reclamante.setText(reclamante.getNombre_apellido());
		tFEmail.setText(reclamante.getEmail());
		tfDni.setText(reclamante.getDni());
		this.telefonos = telefonos;
		comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
		comboBox_telefonos.setModel(comboBOX_Modelo);
		SetVisible(true);
	}


	private void initialize() {
		setTitle("AGREGAR RECLAMANTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreReclamante = new JLabel("Nombre Del Reclamante");
		lblNombreReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreReclamante.setBounds(5, 10, 170, 20);
		contentPane.add(lblNombreReclamante);
		
		tFnombre_reclamante = new JTextField();
		tFnombre_reclamante.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_reclamante.getText().length()>=limite){
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
		tFnombre_reclamante.setBounds(174, 10, 250, 20);
		contentPane.add(tFnombre_reclamante);
		tFnombre_reclamante.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(5, 41, 160, 20);
		contentPane.add(lblEmail);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(249, 185, 110, 20);
		contentPane.add(btnModificar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(74, 185, 110, 20);
		contentPane.add(btnCancelar);

		JLabel lblTelefonos = new JLabel("Telefonos");
		lblTelefonos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonos.setBounds(5, 102, 170, 20);
		contentPane.add(lblTelefonos);
		contentPane.setVisible(true);
		
		comboBox_telefonos = new JComboBox<String>();
		
		comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
		comboBox_telefonos.setModel(comboBOX_Modelo);
		comboBox_telefonos.setEditable(true);
		comboBox_telefonos.setBounds(174, 102, 150, 20);
		contentPane.add(comboBox_telefonos);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nuevo_telefono = (String)comboBox_telefonos.getSelectedItem();
				if (!telefonos.contains(nuevo_telefono) && nuevo_telefono != null && nuevo_telefono!=""){
					if (rdbtnFijo.isSelected()){
						nuevo_telefono += " (Fijo)";
						telefonos.add(nuevo_telefono);
						DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
						comboBox_telefonos.setModel(comboBOX_Modelo);
						comboBox_telefonos.setSelectedIndex(-1);
						rdbtnFijo.setSelected(false);
					}else{
						if (rdbtnCelular.isSelected()){
							nuevo_telefono +=" (Celular)";
							telefonos.add(nuevo_telefono);
							DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
							comboBox_telefonos.setModel(comboBOX_Modelo);
							comboBox_telefonos.setSelectedIndex(-1);
							rdbtnCelular.setSelected(false);
						}else{
							JOptionPane.showMessageDialog(contentPane,"Seleccione el tipo de telefono.","Advertencia",JOptionPane.INFORMATION_MESSAGE);

						}
					}					
				}
			}
		});
		btnAgregar.setBounds(334, 99, 90, 23);
		contentPane.add(btnAgregar);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (telefonos.contains((String)comboBox_telefonos.getSelectedItem())){
						telefonos.remove((String)comboBox_telefonos.getSelectedItem());
						DefaultComboBoxModel<String> comboBOX_Modelo = new DefaultComboBoxModel<String>(telefonos);
						comboBox_telefonos.setModel(comboBOX_Modelo);
						comboBox_telefonos.setSelectedIndex(-1);
						rdbtnFijo.setSelected(false);
						rdbtnCelular.setSelected(false);					
				}
			}
		});
		btnQuitar.setBounds(334, 133, 90, 23);
		contentPane.add(btnQuitar);
		
		tFEmail = new JTextField();
		tFEmail.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFEmail.getText().length()>=limite){
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
		tFEmail.setBounds(174, 40, 250, 20);
		contentPane.add(tFEmail);
		tFEmail.setColumns(10);
		
		rdbtnCelular = new JRadioButton("Celular");
		rdbtnCelular.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnCelular.isSelected()){
					rdbtnFijo.setSelected(false);
				}
			}
		});

		
		rdbtnCelular.setBounds(174, 129, 150, 23);
		contentPane.add(rdbtnCelular);
		
		rdbtnFijo = new JRadioButton("Fijo");
		rdbtnFijo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rdbtnFijo.isSelected()){
					rdbtnCelular.setSelected(false);
				}
			}
		});

		rdbtnFijo.setBounds(174, 155, 150, 23);
		contentPane.add(rdbtnFijo);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni.setBounds(5, 71, 170, 20);
		contentPane.add(lblDni);
		
		tfDni = new JTextField();
		tfDni.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tfDni.getText().length()>=limiteDNI){
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
		tfDni.setColumns(10);
		tfDni.setBounds(174, 71, 150, 20);
		contentPane.add(tfDni);
		
	}
	
	private void modificar() {

		
		if (tFnombre_reclamante.getText().isEmpty() || tfDni.getText().isEmpty() || tFEmail.getText().isEmpty() || telefonos.isEmpty() ){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			reclamante.setNombre_apellido(tFnombre_reclamante.getText());
			reclamante.setDni(tfDni.getText());
			reclamante.setEmail(tFEmail.getText());
			if (mediador.modificar(reclamante,telefonos)){
				JOptionPane.showMessageDialog(contentPane,"Reclamante Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private void SetVisible(boolean visible) {
		contentPane.setVisible(visible);
		
	}

}
