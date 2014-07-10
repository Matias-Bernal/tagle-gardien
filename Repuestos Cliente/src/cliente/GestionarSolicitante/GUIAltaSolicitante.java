package cliente.GestionarSolicitante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import cliente.utils.TransparentPanel;

import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Vector;

public class GUIAltaSolicitante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombreSolicitante;
	private MediadorSolicitante mediador;
	private JComboBox<String> cBTSolicitante;
	private Vector<String> tiposSolicitantes;
	private int limite = 35;
	private JTextField tFTelefonoMostrador;
	private JTextField tFEmailMostrador;
	private JPanel panelDinamico;
	
	public GUIAltaSolicitante(final MediadorSolicitante mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	private void cargarDatos() {
		tiposSolicitantes = new Vector<String>();
		tiposSolicitantes.add("Mostrador");
		tiposSolicitantes.add("Mayorista");
		tiposSolicitantes.add("Garantia");
		tiposSolicitantes.add("Seguro");
		tiposSolicitantes.add("Taller Mecanico");
		tiposSolicitantes.add("Taller Carroceria");
	}

	private void initialize() {
		setTitle("AGREGAR SOLICITANTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 240);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/add_reclamante.png")));
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel lblNombreSolicitante = new JLabel("Nombre Solicitante");
		lblNombreSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSolicitante.setBounds(0, 39, 145, 20);
		contentPane.add(lblNombreSolicitante);
		
		tFNombreSolicitante = new JTextField();
		tFNombreSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
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
		
		JButton btnCrearUsuario = new JButton("Crear");
		btnCrearUsuario.setIcon(new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/save.png")));
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				crear();
			}
		});
		btnCrearUsuario.setBounds(226, 169, 110, 20);
		contentPane.add(btnCrearUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
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
		cBTSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cBTSolicitante.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarCampos();
			}
		});
		cBTSolicitante.setModel(new DefaultComboBoxModel<String>(tiposSolicitantes));
		cBTSolicitante.setBounds(145, 11, 154, 20);
		contentPane.add(cBTSolicitante);
		
		panelDinamico = new TransparentPanel();
		panelDinamico.setBounds(0, 70, 404, 88);
		contentPane.add(panelDinamico);
		panelDinamico.setLayout(null);
		
		JLabel label = new JLabel("Telefono");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 145, 20);
		panelDinamico.add(label);
		
		tFTelefonoMostrador = new JTextField();
		tFTelefonoMostrador.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFTelefonoMostrador.setColumns(10);
		tFTelefonoMostrador.setBounds(145, 0, 250, 20);
		panelDinamico.add(tFTelefonoMostrador);
		
		JLabel label_1 = new JLabel("Email");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 30, 145, 20);
		panelDinamico.add(label_1);
		
		tFEmailMostrador = new JTextField();
		tFEmailMostrador.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFEmailMostrador.setColumns(10);
		tFEmailMostrador.setBounds(145, 30, 250, 20);
		panelDinamico.add(tFEmailMostrador);
		
		contentPane.setVisible(true);
	}
	
	protected void actualizarCampos() {
		if(cBTSolicitante.getSelectedItem().equals("Mostrador")){
			panelDinamico.setVisible(true);
			panelDinamico.updateUI();
		}else{
			panelDinamico.setVisible(false);
			panelDinamico.updateUI();
		}
	}
	
	public void crear(){
		
		if (tFNombreSolicitante.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}else{
			if(cBTSolicitante.getSelectedItem().equals("Mostrador")){
				if (tFTelefonoMostrador.getText().isEmpty()){
					JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
				}else{
					if (mediador.agregarMostrador(tFNombreSolicitante.getText(),tFTelefonoMostrador.getText(),tFEmailMostrador.getText())){
						JOptionPane.showMessageDialog(contentPane,"Solicitante Mostrador Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}
			}else{
				if(cBTSolicitante.getSelectedItem().equals("Mayorista")){
					if (mediador.agregarMayorista(tFNombreSolicitante.getText())){
						JOptionPane.showMessageDialog(contentPane,"Solicitante Mayorista Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}else{
					if(cBTSolicitante.getSelectedItem().equals("Garantia")){
						if (mediador.agregarGarantia(tFNombreSolicitante.getText())){
							JOptionPane.showMessageDialog(contentPane,"Solicitante Garantia Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
							mediador.actualizarDatosGestion();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
						}
					}else{
						if(cBTSolicitante.getSelectedItem().equals("Seguro")){
							if (mediador.agregarSeguro(tFNombreSolicitante.getText())){
								JOptionPane.showMessageDialog(contentPane,"Solicitante Seguro Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
								mediador.actualizarDatosGestion();
								dispose();
							}else{
								JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
							}
						}else{
							if(cBTSolicitante.getSelectedItem().equals("Taller Mecanico")){
								if (mediador.agregarTallerMecanico(tFNombreSolicitante.getText())){
									JOptionPane.showMessageDialog(contentPane,"Solicitante Taller Mecanico Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
									mediador.actualizarDatosGestion();
									dispose();
								}else{
									JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
								}
							}else{
								if(cBTSolicitante.getSelectedItem().equals("Taller Carroceria")){
									if (mediador.agregarTallerCarroceria(tFNombreSolicitante.getText())){
										JOptionPane.showMessageDialog(contentPane,"Solicitante Taller Carroceria Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
										mediador.actualizarDatosGestion();
										dispose();
									}else{
										JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
									}
								}else{
									JOptionPane.showMessageDialog(contentPane,"Error al agregar solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIAltaSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
								}
							}
						}
					}
				}
			}
		}
	}

}