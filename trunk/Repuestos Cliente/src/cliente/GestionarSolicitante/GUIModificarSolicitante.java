package cliente.GestionarSolicitante;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.utils.JPanel_Whit_Image;
import cliente.utils.TransparentPanel;

import comun.DTOs.CarroceriaDTO;
import comun.DTOs.GarantiaDTO;
import comun.DTOs.MayoristaDTO;
import comun.DTOs.MecanicoDTO;
import comun.DTOs.MostradorDTO;
import comun.DTOs.SeguroDTO;
import comun.DTOs.SolicitanteDTO;

public class GUIModificarSolicitante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombreSolicitante;
	private MediadorSolicitante mediador;
	private int limite = 35;
	private JTextField tFTelefonoMostrador;
	private JTextField tFEmailMostrador;
	private JPanel panelDinamico;
	private JTextField tFTipoSolicitante;
	private SolicitanteDTO solicitante;
	private JTextField tfIdSolicitante;
	
	public GUIModificarSolicitante(final MediadorSolicitante mediador, SolicitanteDTO solicitante) {
		this.mediador = mediador;
		this.solicitante = solicitante;
		initialize();
		cargarDatos();
	}
	
	private void cargarDatos() {
		tFNombreSolicitante.setText(solicitante.getNombre());
		tfIdSolicitante.setText(solicitante.getId().toString());
		if(solicitante instanceof MostradorDTO){
			tFTipoSolicitante.setText("Mostrador");
			tFEmailMostrador.setText(((MostradorDTO) solicitante).getMail());
			tFTelefonoMostrador.setText(((MostradorDTO) solicitante).getTelefono());
			panelDinamico.setVisible(true);
		}else{
			panelDinamico.setVisible(false);
			if(solicitante instanceof MayoristaDTO)
				tFTipoSolicitante.setText("Mayorista");
			if(solicitante instanceof GarantiaDTO)
				tFTipoSolicitante.setText("Garantia");
			if(solicitante instanceof SeguroDTO)
				tFTipoSolicitante.setText("Seguro");
			if(solicitante instanceof MecanicoDTO)
				tFTipoSolicitante.setText("Taller Mecanico");
			if(solicitante instanceof CarroceriaDTO)
				tFTipoSolicitante.setText("Taller Carroceria");
		}
	}

	private void initialize() {
		setTitle("MODIFICAR SOLICITANTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/edit_reclamante.png")));
		setResizable(false);
		setBounds(100, 100, 410, 240);
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		contentPane.setLayout(null);
		
		JLabel lblNombreSolicitante = new JLabel("Nombre Solicitante");
		lblNombreSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreSolicitante.setBounds(0, 70, 145, 20);
		contentPane.add(lblNombreSolicitante);
		
		tFNombreSolicitante = new JTextField();
		tFNombreSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNombreSolicitante.setBounds(145, 70, 250, 20);
		contentPane.add(tFNombreSolicitante);
		tFNombreSolicitante.setColumns(10);
		
		JButton btnModificarUsuario = new JButton("Modificar");
		btnModificarUsuario.setIcon(new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/edit.png")));
		btnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificarUsuario.setBounds(226, 169, 110, 20);
		contentPane.add(btnModificarUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(51, 169, 110, 20);
		contentPane.add(btnCancelar);

		JLabel lblTipoSolicitante = new JLabel("Tipo Solicitante");
		lblTipoSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoSolicitante.setBounds(0, 40, 145, 20);
		contentPane.add(lblTipoSolicitante);
		contentPane.setVisible(true);
		
		panelDinamico = new TransparentPanel();
		panelDinamico.setBounds(0, 110, 404, 60);
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
		
		tFTipoSolicitante = new JTextField();
		tFTipoSolicitante.setDisabledTextColor(Color.BLACK);
		tFTipoSolicitante.setEditable(false);
		tFTipoSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFTipoSolicitante.setBounds(145, 40, 191, 20);
		contentPane.add(tFTipoSolicitante);
		tFTipoSolicitante.setColumns(10);
		
		JLabel lbl_id = new JLabel("ID Solicitante");
		lbl_id.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_id.setBounds(0, 10, 145, 20);
		contentPane.add(lbl_id);
		
		tfIdSolicitante = new JTextField();
		tfIdSolicitante.setEditable(false);
		tfIdSolicitante.setDisabledTextColor(Color.BLACK);
		tfIdSolicitante.setColumns(10);
		tfIdSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfIdSolicitante.setBounds(145, 10, 100, 20);
		contentPane.add(tfIdSolicitante);
		
		contentPane.setVisible(true);
	}
	
	public void modificar(){
		if (tFNombreSolicitante.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}else{
			if(tFTipoSolicitante.getText().equals("Mostrador")){
				if (tFTelefonoMostrador.getText().isEmpty()){
					JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
				}else{
					if (mediador.modificarMostrador(solicitante.getId(),tFNombreSolicitante.getText(),tFTelefonoMostrador.getText(),tFEmailMostrador.getText())){
						JOptionPane.showMessageDialog(contentPane,"Solicitante Mostrador Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
						mediador.actualizarDatosGestion();
						dispose();
						}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}
			}else{
				if(tFTipoSolicitante.getText().equals("Mayorista")){
					if (mediador.modificarMayorista(solicitante.getId(),tFNombreSolicitante.getText())){
						JOptionPane.showMessageDialog(contentPane,"Solicitante Mayorista Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
				}else{
					if(tFTipoSolicitante.getText().equals("Garantia")){
						if (mediador.modificarGarantia(solicitante.getId(),tFNombreSolicitante.getText())){
							JOptionPane.showMessageDialog(contentPane,"Solicitante Garantia Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
							mediador.actualizarDatosGestion();
							dispose();
						}else{
							JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
						}
					}else{
						if(tFTipoSolicitante.getText().equals("Seguro")){
							if (mediador.modificarSeguro(solicitante.getId(),tFNombreSolicitante.getText())){
								JOptionPane.showMessageDialog(contentPane,"Solicitante Seguro Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
								mediador.actualizarDatosGestion();
								dispose();
							}else{
								JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
							}
						}else{
							if(tFTipoSolicitante.getText().equals("Taller Mecanico")){
								if (mediador.modificarTallerMecanico(solicitante.getId(),tFNombreSolicitante.getText())){
									JOptionPane.showMessageDialog(contentPane,"Solicitante Taller Mecanico Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
									mediador.actualizarDatosGestion();
									dispose();
								}else{
									JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
								}
							}else{
								if(tFTipoSolicitante.getText().equals("Taller Carroceria")){
									if (mediador.modificarTallerCarroceria(solicitante.getId(),tFNombreSolicitante.getText())){
										JOptionPane.showMessageDialog(contentPane,"Solicitante Taller Carroceria Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
										mediador.actualizarDatosGestion();
										dispose();
									}else{
										JOptionPane.showMessageDialog(contentPane,"Error al modificar Solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
									}
								}else{
									JOptionPane.showMessageDialog(contentPane,"Error al modificar Solicitante. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/error.png")));
								}
							}
						}
					}
				}
			}
		}
	}

	public MediadorSolicitante getMedidador() {
		return mediador;
	}

	public void setMedidador(MediadorSolicitante medidador) {
		this.mediador = medidador;
	}

	public SolicitanteDTO getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(SolicitanteDTO solicitante) {
		this.solicitante = solicitante;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}
}