package cliente;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class GUIMenu_Principal extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JFrame frmPrincipal;
	private JTable tablaNotificaciones;
	
	public GUIMenu_Principal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal= mediadorPrincipal;
		initialize();
	}

	@SuppressWarnings("deprecation")
	private void initialize() {
		
		setLocationRelativeTo(null);
		
		frmPrincipal = new JFrame();
		frmPrincipal.setResizable(false);

		String titulo = "USUARIO: "+mediadorPrincipal.getUsuario().getNombre_usuario().toString() +" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		frmPrincipal.setTitle(titulo);
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIMenu_Principal.class.getResource("/cliente/imagenes/tagle.ico")));
		frmPrincipal.setBounds(100, 100, 910, 570);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		menuBar.add(mnInicio);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/imagenes/disconnect.ico")));
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.reiniciar();
			}
		});
		mnInicio.add(mntmDesconectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/imagenes/exit.ico")));
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
				//setVisible(false);
				mediadorPrincipal.altaUsuario();
			}
		});
		mnUsuarios.add(mntmAltaUsuario);
		
		JMenuItem mntmGestionUsuario = new JMenuItem("Gestionar Usuarios");
		mntmGestionUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarUsuario();
			}
		});
		mnUsuarios.add(mntmGestionUsuario);
		
		// MENU REGISTRANTES //
		JMenu mnRegistrantes = new JMenu("Registrantes");
		menuBar.add(mnRegistrantes);
		
		JMenuItem mntmAltaRegistrante = new JMenuItem("Alta Registrante");
		mntmAltaRegistrante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaRegistrante();
			}
		});
		mnRegistrantes.add(mntmAltaRegistrante);
		
		JMenuItem mntmGestionRegistrante = new JMenuItem("Gestionar Registrantes");
		mntmGestionRegistrante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarRegistrante();
			}
		});
		mnRegistrantes.add(mntmGestionRegistrante);
		
		JMenu mnReclamantes = new JMenu("Reclamantes");
		menuBar.add(mnReclamantes);
		
		JMenuItem mntmAltaReclamante = new JMenuItem("Alta Reclamante");
		mntmAltaReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaReclamante();
			}
		});
		mnReclamantes.add(mntmAltaReclamante);
		
		JMenuItem mntmGestionReclamante = new JMenuItem("Gestionar Reclamantes");
		mntmGestionReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarReclamante();
			}
		});
		mnReclamantes.add(mntmGestionReclamante);
		
		JMenu mnVehiculos = new JMenu("Vehiculos");
		menuBar.add(mnVehiculos);
		
		JMenuItem mntmAltaVehiculo = new JMenuItem("Alta Vehiculo");
		mntmAltaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaVehiculo();
			}
		});
		mnVehiculos.add(mntmAltaVehiculo);
		
		JMenuItem mntmGestionVehiculo = new JMenuItem("Gestionar Vehiculos");
		mntmGestionVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarVehiculo();
			}
		});
		mnVehiculos.add(mntmGestionVehiculo);
		
		JMenu mnOrden = new JMenu("Ordenes");
		menuBar.add(mnOrden);
		
		JMenuItem mntmAltaOrden = new JMenuItem("Alta Orden");
		mntmAltaOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaOrden();
			}
		});
		mnOrden.add(mntmAltaOrden);
		
		JMenuItem mntmGestionOrden = new JMenuItem("Gestion Orden");
		mntmGestionOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarOrden();
			}
		});
		mnOrden.add(mntmGestionOrden);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		menuBar.add(mnReclamos);
		
		JMenuItem mntmAltaReclamoAgente = new JMenuItem("Alta Reclamo Agente");
		mntmAltaReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaReclamoAgente();
			}
		});
		mnReclamos.add(mntmAltaReclamoAgente);
		
		JMenuItem mntmGestionReclamoAgente = new JMenuItem("Gestionar Reclamos Agente");
		mntmGestionReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarReclamoAgente();
			}
		});
		
		JMenuItem mntmAltaReclamoEntidad = new JMenuItem("Alta Reclamo Entidad");
		mntmAltaReclamoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaReclamoEntidad();
			}
		});
		mnReclamos.add(mntmAltaReclamoEntidad);
		mnReclamos.add(mntmGestionReclamoAgente);
		
		JMenuItem mntmGestionarReclamosEntidad = new JMenuItem("Gestionar Reclamos Entidad");
		mntmGestionarReclamosEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarReclamoEntidad();
			}
		});
		mnReclamos.add(mntmGestionarReclamosEntidad);
		
		JMenu mnPedido = new JMenu("Pedidos");
		menuBar.add(mnPedido);
		
		JMenuItem mntmAltaPedidoAgente = new JMenuItem("Alta Pedido Agente");
		mntmAltaPedidoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaPedidoAgente();
			}
		});
		mnPedido.add(mntmAltaPedidoAgente);
		
		JMenuItem mntmGestionPedidoAgentes = new JMenuItem("Gestionar Pedidos Agentes");
		mntmGestionPedidoAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarPedidoAgente();
			}
		});
		
		JMenuItem mntmNuevoPedidoEntidad = new JMenuItem("Alta Pedido Entidad");
		mntmNuevoPedidoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaPedidoEntidad();
			}
		});
		mnPedido.add(mntmNuevoPedidoEntidad);
		mnPedido.add(mntmGestionPedidoAgentes);
		
		JMenuItem mntmGestionPedidoEntidad = new JMenuItem("Gestion Pedidos Entidades");
		mntmGestionPedidoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarPedidoEntidad();
			}
		});
		mnPedido.add(mntmGestionPedidoEntidad);
		
		JMenu mnRecursos = new JMenu("Recursos");
		menuBar.add(mnRecursos);
		
		JMenuItem mntmAltaRecurso = new JMenuItem("Alta Recursos");
		mntmAltaRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaRecurso();
			}
		});
		mnRecursos.add(mntmAltaRecurso);
		
		JMenuItem mntmGestionRecurso = new JMenuItem("Gestionar Recursos");
		mntmGestionRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarRecurso();
			}
		});
		mnRecursos.add(mntmGestionRecurso);
		
		JMenu mnDevoluciones = new JMenu("Devoluciones");
		menuBar.add(mnDevoluciones);
		
		JMenuItem mntmAltaDevolucion = new JMenuItem("Alta Devolucion");
		mntmAltaDevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaDevolucion();
			}
		});
		mnDevoluciones.add(mntmAltaDevolucion);
		
		JMenuItem mntmGestiondevolucion = new JMenuItem("Gestionar Devoluciones");
		mntmGestiondevolucion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarDevolucion();
			}
		});
		mnDevoluciones.add(mntmGestiondevolucion);
		
		JMenu mnNotificaciones = new JMenu("Notificaciones");
		menuBar.add(mnNotificaciones);
		
		JMenuItem mntmModificarNotificacio = new JMenuItem("Modificar Notificaciones");
		mntmModificarNotificacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarNotificaciones();
			}
		});
		mnNotificaciones.add(mntmModificarNotificacio);
		
		JMenuItem mntmActualizar = new JMenuItem("Actualizar");
		mntmActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.actualizarNotificaciones();
			}
		});
		mnNotificaciones.add(mntmActualizar);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.ayuda();
			}
		});
		mnAyuda.add(mntmAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				JOptionPane.showMessageDialog(frmPrincipal,"IT 10 Cooperativa","Acerca de..",JOptionPane.INFORMATION_MESSAGE);
				System.out.println("Ayuda presionado");	
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		JButton btnReclamos = new JButton("RECLAMOS");
		btnReclamos.setBounds(104, 189, 250, 23);
		btnReclamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reclamos();
			}
		});
		frmPrincipal.getContentPane().setLayout(null);
		frmPrincipal.getContentPane().add(btnReclamos);
		
		JButton btnRepuestos = new JButton("REPUESTOS");
		btnRepuestos.setBounds(104, 249, 250, 23);
		btnRepuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.repuestos();	
			}
		});
		frmPrincipal.getContentPane().add(btnRepuestos);
		
		JButton btnReportes = new JButton("REPORTES");
		btnReportes.setBounds(104, 310, 250, 23);
		btnReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportes();
			}
		});
		frmPrincipal.getContentPane().add(btnReportes);
		
		JPanel panel = new JPanel();
		panel.setBounds(535, 45, 330, 375);
		frmPrincipal.getContentPane().add(panel);
		panel.setLayout(null);
		
		tablaNotificaciones = new JTable();
		tablaNotificaciones.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Notificacion", "Boton"
			}
		));
		tablaNotificaciones.setBounds(10, 10, 310, 355);
		panel.add(tablaNotificaciones);
		
		JLabel lblNotificaciones = new JLabel("NOTIFICACIONES");
		lblNotificaciones.setBounds(638, 11, 126, 21);
		lblNotificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNotificaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		frmPrincipal.getContentPane().add(lblNotificaciones);
		
		JButton btnCompletado = new JButton("Completada");
		btnCompletado.setBounds(534, 455, 138, 23);
		btnCompletado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completada();
			}
		});
		frmPrincipal.getContentPane().add(btnCompletado);
		
		JButton btnPosponer = new JButton("Posponer");
		btnPosponer.setBounds(727, 455, 138, 23);
		btnPosponer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posponer();
			}
		});
		frmPrincipal.getContentPane().add(btnPosponer);
	}

	protected void posponer() {
		// TODO Auto-generated method stub
		
	}

	protected void completada() {
		// TODO Auto-generated method stub
		
	}

	public void setVisible(boolean b) {
		frmPrincipal.setVisible(b);
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
	public void show() {
		// TODO Auto-generated method stub
	}
}
