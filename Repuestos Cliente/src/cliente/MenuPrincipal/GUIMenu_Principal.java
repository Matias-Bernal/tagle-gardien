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
package cliente.MenuPrincipal;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import cliente.utils.JPanel_Whit_Image;
import cliente.utils.TransparentPanel;

import java.awt.Insets;

public class GUIMenu_Principal extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JPanel contentPane;
	private JPanel logo;
	private JPanel panel_buttons;
	private JPanel panel_logo;
	
	public GUIMenu_Principal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal= mediadorPrincipal;
		initialize();
	}

	@SuppressWarnings("deprecation")
	private void initialize() {
		setLocationRelativeTo(null);
		setResizable(false);

		String titulo = "USUARIO: "+mediadorPrincipal.getUsuario_repuesto().getNombre_usuario().toString() +" [ID: "+mediadorPrincipal.getUsuario_repuesto().getId().toString()+" ]";
		setTitle(titulo);
		setBounds(100, 100, 1100, 570);
		setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				int eleccion = JOptionPane.showConfirmDialog(null, "Desea salir?","Salir",0,0,new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Recursos/Iconos/exit_login.png")));
				if ( eleccion == 0) {
					System.exit(0);
				}	
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.reiniciar();
			}
		});
		mnInicio.add(mntmDesconectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.salir();
			}
		});
		mnInicio.add(mntmSalir);
		
		
		// MENU USUARIOS //
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAltaUsuario = new JMenuItem("Alta Usuario");
		mntmAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaUsuario();
			}
		});
		mnUsuarios.add(mntmAltaUsuario);
		
		JMenuItem mntmGestionUsuario = new JMenuItem("Gestionar Usuarios");
		mntmGestionUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarUsuario();
			}
		});
		mnUsuarios.add(mntmGestionUsuario);
		
		// MENU REGISTRANTES //
		JMenu mnSolicitante = new JMenu("Solicitantes");
		menuBar.add(mnSolicitante);
		
		JMenuItem mntmAltaSolicitante = new JMenuItem("Alta Solicitante");
		mntmAltaSolicitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				//mediadorPrincipal.altaRegistrante();
			}
		});
		mnSolicitante.add(mntmAltaSolicitante);
		
		JMenuItem mntmGestionSolicitante = new JMenuItem("Gestionar Registrantes");
		mntmGestionSolicitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				//mediadorPrincipal.gestionarRegistrante();
			}
		});
		mnSolicitante.add(mntmGestionSolicitante);
		
		JMenu mnProveedor = new JMenu("Proveedores");
		menuBar.add(mnProveedor);
		
		JMenuItem mntmAltaProveedor = new JMenuItem("Alta Proveedor");
		mntmAltaProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				//mediadorPrincipal.altaReclamante();
			}
		});
		mnProveedor.add(mntmAltaProveedor);
		
		JMenuItem mntmGestionProveedor = new JMenuItem("Gestionar Proveedores");
		mntmGestionProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				//mediadorPrincipal.gestionarReclamante();
			}
		});
		mnProveedor.add(mntmGestionProveedor);
		
		JMenu mnSolicitud = new JMenu("Solicitudes");
		menuBar.add(mnSolicitud);
		
		JMenuItem mntmAltaSolicitud = new JMenuItem("Alta Solicitud");
		mntmAltaSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaSolicitud();
			}
		});
		mnSolicitud.add(mntmAltaSolicitud);
		
		JMenuItem mntmGestionSolicutud = new JMenuItem("Gestionar Solicitudes");
		mntmGestionSolicutud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.gestionarSolicitud();
			}
		});
		mnSolicitud.add(mntmGestionSolicutud);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		menuBar.add(mnReclamos);
		
		JMenuItem mntmAltaReclamo = new JMenuItem("Alta Reclamo");
		mntmAltaReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				//mediadorPrincipal.altaReclamoAgente();
			}
		});
		mnReclamos.add(mntmAltaReclamo);
		
		JMenuItem mntmGestionReclamo = new JMenuItem("Gestionar Reclamos");
		mntmGestionReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				//mediadorPrincipal.gestionarReclamoAgente();
			}
		});
		mnReclamos.add(mntmGestionReclamo);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.ayuda();
			}
		});
		mnAyuda.add(mntmManual);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"IT10 Cooperativa - www.it10coop.com.ar","Acerca de..",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/it10.png")));
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel_logo = new TransparentPanel();
		contentPane.add(panel_logo, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_logo = new GridBagLayout();
		gbl_panel_logo.columnWidths = new int[] {200, 200, 200};
		gbl_panel_logo.rowHeights = new int[] {200};
		gbl_panel_logo.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_logo.rowWeights = new double[]{0.0};
		panel_logo.setLayout(gbl_panel_logo);
		
		logo = new JPanel_Whit_Image("/cliente/Recursos/Iconos/gardien_logo.png");
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.fill = GridBagConstraints.BOTH;
		gbc_logo.gridx = 2;
		gbc_logo.gridy = 0;
		panel_logo.add(logo, gbc_logo);
		
		panel_buttons = new TransparentPanel();
		contentPane.add(panel_buttons, BorderLayout.CENTER);
		GridBagLayout gbl_panel_buttons = new GridBagLayout();
		gbl_panel_buttons.columnWidths = new int[] {350, 0, 350, 0};
		gbl_panel_buttons.rowHeights = new int[] {100, 40, 50, 40, 100};
		gbl_panel_buttons.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_buttons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		panel_buttons.setLayout(gbl_panel_buttons);
		
		JButton btnNuevaSolicitud = new JButton("NUEVA SOLICITUD");
		btnNuevaSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediadorPrincipal.altaSolicitud();
			}
		});
		GridBagConstraints gbc_btnNuevaSolicitud = new GridBagConstraints();
		gbc_btnNuevaSolicitud.fill = GridBagConstraints.BOTH;
		gbc_btnNuevaSolicitud.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaSolicitud.gridx = 1;
		gbc_btnNuevaSolicitud.gridy = 1;
		panel_buttons.add(btnNuevaSolicitud, gbc_btnNuevaSolicitud);
		
		JButton btnBuscador = new JButton("BUSCADOR");
		btnBuscador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnBuscador = new GridBagConstraints();
		gbc_btnBuscador.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscador.fill = GridBagConstraints.BOTH;
		gbc_btnBuscador.gridx = 1;
		gbc_btnBuscador.gridy = 3;
		panel_buttons.add(btnBuscador, gbc_btnBuscador);

		contentPane.show();
		
	}

	public MediadorPrincipal getMediadorPrincipal() {
		return mediadorPrincipal;
	}

	public void setMediadorPrincipal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal = mediadorPrincipal;
	}

	public void reiniciar() {
		setVisible(false);
	}

}
