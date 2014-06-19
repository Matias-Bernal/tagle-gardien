
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
package cliente;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import common.DTOs.Notificacion_ReclamoDTO;

public class GUIMenu_Principal extends JFrame{

	private static final long serialVersionUID = 1L;
	private MediadorPrincipal mediadorPrincipal;
	private JFrame frmPrincipal;
	private JTable tablaNotificaciones;
	private Vector<Integer> anchos;
	private DefaultTableModel modelo;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JButton btnReportesGestion;
	private JButton btnReclamos_Piezas;
	private JButton btnReclamos;
	private JButton btnReportesControl;
	private JMenu mnReportes;
	
	public GUIMenu_Principal(MediadorPrincipal mediadorPrincipal) {
		this.mediadorPrincipal= mediadorPrincipal;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		nombreColumnas = new Vector<String>();
		anchos = new Vector<Integer>();
		nombreColumnas.add("TIPO");
		anchos.add(150);
		nombreColumnas.add("DETALLES");
		anchos.add(425);
		datosTabla = new Vector<Vector<String>>();
	}

	private void initialize() {
		
		setLocationRelativeTo(null);
		
		frmPrincipal = new JFrame();
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/tagle.ico")));
		frmPrincipal.getContentPane().setBackground(UIManager.getColor("window"));
		frmPrincipal.setResizable(false);
		frmPrincipal.setLocationRelativeTo(null);

		String titulo = "USUARIO: "+mediadorPrincipal.getUsuario().getNombre_usuario().toString() +" [ID: "+mediadorPrincipal.getUsuario().getId().toString()+" ]";
		frmPrincipal.setTitle(titulo);
		frmPrincipal.setBounds(100, 100, 1100, 570);		
		frmPrincipal.setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);

		frmPrincipal.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				int eleccion = JOptionPane.showConfirmDialog(null, "Desea salir?","Salir",0,0,new ImageIcon(GUILogin.class.getResource("/cliente/Resources/Icons/exit_1.png")));
				if ( eleccion == 0) {
					mediadorPrincipal.matarThreads();
					System.exit(0);
				}	
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnInicio = new JMenu("Inicio");
		mnInicio.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/inicio.png")));
		menuBar.add(mnInicio);
		
		JMenuItem mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/disconect.png")));
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.reiniciar();
			}
		});
		mnInicio.add(mntmDesconectar);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/exit.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				mediadorPrincipal.salir();
			}
		});
		mnInicio.add(mntmSalir);
		
		
		// MENU USUARIOS //
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/usuarios.png")));
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAltaUsuario = new JMenuItem("Alta Usuario");
		mntmAltaUsuario.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_users.png")));

		mntmAltaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.altaUsuario();
			}
		});
		mnUsuarios.add(mntmAltaUsuario);
		
		JMenuItem mntmGestionUsuario = new JMenuItem("Gestionar Usuarios");
		mntmGestionUsuario.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_users.png")));
		mntmGestionUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarUsuario();
			}
		});
		mnUsuarios.add(mntmGestionUsuario);
		
		// MENU REGISTRANTES //
		JMenu mnRegistrantes = new JMenu("Registrantes");
		mnRegistrantes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/registrantes.png")));
		menuBar.add(mnRegistrantes);
		
		JMenuItem mntmAltaRegistrante = new JMenuItem("Alta Registrante");
		mntmAltaRegistrante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_registrante.png")));
		mntmAltaRegistrante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaRegistrante();
			}
		});
		mnRegistrantes.add(mntmAltaRegistrante);
		
		JMenuItem mntmGestionRegistrante = new JMenuItem("Gestionar Registrantes");
		mntmGestionRegistrante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_registrante.png")));
		mntmGestionRegistrante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarRegistrante();
			}
		});
		mnRegistrantes.add(mntmGestionRegistrante);
		
		JMenu mnReclamantes = new JMenu("Reclamantes");
		mnReclamantes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamante.png")));
		menuBar.add(mnReclamantes);
		
		JMenuItem mntmAltaReclamante = new JMenuItem("Alta Reclamante");
		mntmAltaReclamante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_reclamante.png")));
		mntmAltaReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaReclamante();
			}
		});
		mnReclamantes.add(mntmAltaReclamante);
		
		JMenuItem mntmGestionReclamante = new JMenuItem("Gestionar Reclamantes");
		mntmGestionReclamante.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_reclamante.png")));
		mntmGestionReclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarReclamante();
			}
		});
		mnReclamantes.add(mntmGestionReclamante);
		
		JMenu mnVehiculos = new JMenu("Vehiculos");
		mnVehiculos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/vehiculo.png")));
		menuBar.add(mnVehiculos);
		
		JMenuItem mntmAltaVehiculo = new JMenuItem("Alta Vehiculo");
		mntmAltaVehiculo.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_vehiculo.png")));
		mntmAltaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaVehiculo();
			}
		});
		mnVehiculos.add(mntmAltaVehiculo);
		
		JMenuItem mntmGestionVehiculo = new JMenuItem("Gestionar Vehiculos");
		mntmGestionVehiculo.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_vehiculo.png")));
		mntmGestionVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarVehiculo();
			}
		});
		mnVehiculos.add(mntmGestionVehiculo);
		
		JMenu mnOrden = new JMenu("Ordenes");
		mnOrden.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/ordenen.png")));
		menuBar.add(mnOrden);
		
		JMenuItem mntmAltaOrden = new JMenuItem("Alta Orden");
		mntmAltaOrden.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_orden.png")));
		mntmAltaOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaOrden();
			}
		});
		mnOrden.add(mntmAltaOrden);
		
		JMenuItem mntmGestionOrden = new JMenuItem("Gestion Orden");
		mntmGestionOrden.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_orden.png")));
		mntmGestionOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarOrden();
			}
		});
		mnOrden.add(mntmGestionOrden);
		
		JMenu mnReclamos = new JMenu("Reclamos");
		mnReclamos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamo.png")));
		menuBar.add(mnReclamos);
		
		JMenuItem mntmAltaReclamoAgente = new JMenuItem("Alta Reclamo Agente");
		mntmAltaReclamoAgente.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_reclamo_agente.png")));
		mntmAltaReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaReclamoAgente();
			}
		});
		mnReclamos.add(mntmAltaReclamoAgente);
		
		JMenuItem mntmGestionReclamoAgente = new JMenuItem("Gestionar Reclamos Agente");
		mntmGestionReclamoAgente.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/eddit_reclamo_agente.png")));
		mntmGestionReclamoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarReclamoAgente();
			}
		});
		
		JMenuItem mntmAltaReclamoEntidad = new JMenuItem("Alta Reclamo Entidad");
		mntmAltaReclamoEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_reclamo_entidad.png")));
		mntmAltaReclamoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaReclamoEntidad();
			}
		});
		mnReclamos.add(mntmAltaReclamoEntidad);
		mnReclamos.add(mntmGestionReclamoAgente);
		
		JMenuItem mntmGestionarReclamosEntidad = new JMenuItem("Gestionar Reclamos Entidad");
		mntmGestionarReclamosEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/eddit_reclamo_entidad.png")));
		mntmGestionarReclamosEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarReclamoEntidad();
			}
		});
		mnReclamos.add(mntmGestionarReclamosEntidad);
		
		JMenu mnPedido = new JMenu("Pedidos");
		mnPedido.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/pedido.png")));
		menuBar.add(mnPedido);
		
		JMenuItem mntmAltaPedidoAgente = new JMenuItem("Alta Pedido Agente");
		mntmAltaPedidoAgente.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_pedido_agente.png")));
		mntmAltaPedidoAgente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaPedidoAgente();
			}
		});
		mnPedido.add(mntmAltaPedidoAgente);
		
		JMenuItem mntmGestionPedidoAgentes = new JMenuItem("Gestionar Pedidos Agentes");
		mntmGestionPedidoAgentes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_pedido_agente.png")));
		mntmGestionPedidoAgentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarPedidoAgente();
			}
		});
		
		JMenuItem mntmNuevoPedidoEntidad = new JMenuItem("Alta Pedido Entidad");
		mntmNuevoPedidoEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/add_pedido_entidad.png")));
		mntmNuevoPedidoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.altaPedidoEntidad();
			}
		});
		mnPedido.add(mntmNuevoPedidoEntidad);
		mnPedido.add(mntmGestionPedidoAgentes);
		
		JMenuItem mntmGestionPedidoEntidad = new JMenuItem("Gestion Pedidos Entidades");
		mntmGestionPedidoEntidad.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		mntmGestionPedidoEntidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarPedidoEntidad();
			}
		});
		mnPedido.add(mntmGestionPedidoEntidad);
		
		mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/tablas.png")));
		menuBar.add(mnReportes);
		
		JMenu mnReportesGestion = new JMenu("Reportes Gestion");
		mnReportesGestion.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/gestion1.png")));
		mnReportesGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportesGestion();
			}
		});
		mnReportes.add(mnReportesGestion);
		
		JMenuItem mntmPiezasLlegadas = new JMenuItem("Piezas Llegadas");
		mntmPiezasLlegadas.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_down.png")));
		mntmPiezasLlegadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportePiezasLlegadas();
			}
		});
		mnReportesGestion.add(mntmPiezasLlegadas);
		
		JMenuItem mntmPiezasDevueltas = new JMenuItem("Piezas Devueltas");
		mntmPiezasDevueltas.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_up.png")));
		mntmPiezasDevueltas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportePiezasDevueltas();
			}
		});
		mnReportesGestion.add(mntmPiezasDevueltas);
		
		JMenuItem mntmPiezasSinLlegar = new JMenuItem("Piezas Sin Llegar");
		mntmPiezasSinLlegar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/wait_image.png")));
		mntmPiezasSinLlegar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportePiezasSinLlegar();
			}
		});
		mnReportesGestion.add(mntmPiezasSinLlegar);
		
		JMenuItem mntmPiezasLlegadasSin = new JMenuItem("Piezas Llegadas Sin Turno");
		mntmPiezasLlegadasSin.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/sin_turno.png")));
		mntmPiezasLlegadasSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportePiezasLlegadasSinTurno();
			}
		});
		mnReportesGestion.add(mntmPiezasLlegadasSin);
		
		JMenuItem mntmOrdenSinSolicitud = new JMenuItem("Orden Sin Solicitud De Pedido");
		mntmOrdenSinSolicitud.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/orden_sin_sdp.png")));
		mntmOrdenSinSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteOrdenSinSDP();
			}
		});
		mnReportesGestion.add(mntmOrdenSinSolicitud);
		
		JMenuItem mntmSolicitudDePedido = new JMenuItem("Solicitud De Pedido Sin Numero De Pediddo");
		mntmSolicitudDePedido.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/sdp_sin_numero.png")));
		mntmSolicitudDePedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteSDPSinNP();
			}
		});
		mnReportesGestion.add(mntmSolicitudDePedido);
		
		JMenu mnReportesControl = new JMenu("Reportes Control");
		mnReportesControl.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/control.png")));
		mnReportesControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportesControl();
			}
		});
		mnReportes.add(mnReportesControl);
		
		JMenuItem mntmDiasDesdePedido = new JMenuItem("Dias Desde Pedido Fabrica");
		mntmDiasDesdePedido.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_up.png")));
		mntmDiasDesdePedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasDesdePedidoFabrica();
			}
		});
		mnReportesControl.add(mntmDiasDesdePedido);
		
		JMenuItem mntmDiasDesdeRecepcion = new JMenuItem("Dias Desde Recepcion Pedido Fabrica");
		mntmDiasDesdeRecepcion.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/arrow_down.png")));
		mntmDiasDesdeRecepcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasDesdeRecepcionFabrica();
			}
		});
		mnReportesControl.add(mntmDiasDesdeRecepcion);
		
		JMenuItem mntmDiasDesdeRecepcion_1 = new JMenuItem("Dias Desde Recepcion de Fabrica y Fecha Turno");
		mntmDiasDesdeRecepcion_1.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/entidad.png")));
		mntmDiasDesdeRecepcion_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasDesdeRecepcionFabricaYTurno();
			}
		});
		mnReportesControl.add(mntmDiasDesdeRecepcion_1);
		
		JMenuItem mntmDiasDesdeFecha = new JMenuItem("Dias Desde Fecha Cierre Orden y Fecha Turno");
		mntmDiasDesdeFecha.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/sin_turno.png")));
		mntmDiasDesdeFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasDesdeCierreOrdenYTurno();
			}
		});
		mnReportesControl.add(mntmDiasDesdeFecha);
		
		JMenuItem mntmDiasDesdeFecha_1 = new JMenuItem("Dias Desde Fecha Recurso y Fecha Cierre Orden");
		mntmDiasDesdeFecha_1.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/orden_sin_sdp.png")));
		mntmDiasDesdeFecha_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasFechaRecursoYCierreOrden();
			}
		});
		mnReportesControl.add(mntmDiasDesdeFecha_1);
		
		JMenuItem mntmDiasDesdeFecha_2 = new JMenuItem("Dias Desde Fecha Reclamo y Fecha Devolucion");
		mntmDiasDesdeFecha_2.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamo.png")));
		mntmDiasDesdeFecha_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasFechaReclamoYFechaDevolucion();
			}
		});
		mnReportesControl.add(mntmDiasDesdeFecha_2);
		
		JMenuItem mntmReclamosTurnos = new JMenuItem("Reclamos - Turnos");
		mntmReclamosTurnos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/wait_image.png")));
		mntmReclamosTurnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasFechaReclamo_Turnos();
			}
		});
		mnReportesControl.add(mntmReclamosTurnos);
		
		JMenuItem mntmPiezasLlegadas_1 = new JMenuItem("Piezas Llegadas - Piezas Devueltas");
		mntmPiezasLlegadas_1.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/llegadas_dev.png")));
		mntmPiezasLlegadas_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteDiasPiezasLlegadas_PiezasDevueltas();
			}
		});
		mnReportesControl.add(mntmPiezasLlegadas_1);
		
		JMenuItem mntmManoDeObra = new JMenuItem("Mano De Obra");
		mntmManoDeObra.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/mano_obra.png")));
		mntmManoDeObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteManoDeObra();
			}
		});
		mnReportesControl.add(mntmManoDeObra);
		
		JMenuItem mntmRecursoCierre = new JMenuItem("Recurso - Cierre Orden");
		mntmRecursoCierre.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/ordenen.png")));
		mntmRecursoCierre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reporteRecurso_CierreOrden();
			}
		});
		mnReportesControl.add(mntmRecursoCierre);
		
		JMenu mnNotificaciones = new JMenu("Notificaciones");
		mnNotificaciones.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/notificaciones.png")));
		menuBar.add(mnNotificaciones);
		
		JMenuItem mntmModificarNotificacio = new JMenuItem("Modificar Notificaciones");
		mntmModificarNotificacio.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/edit_notificaciones.png")));
		mntmModificarNotificacio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.gestionarNotificaciones();
			}
		});
		mnNotificaciones.add(mntmModificarNotificacio);
		
		JMenuItem mntmActualizar = new JMenuItem("Actualizar");
		mntmActualizar.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/refresh.png")));
		mntmActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.actualizarNotificaciones();
			}
		});
		mnNotificaciones.add(mntmActualizar);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		mnAyuda.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/help.png")));
		menuBar.add(mnAyuda);
		
		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/manual.png")));
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediadorPrincipal.ayuda();
			}
		});
		mnAyuda.add(mntmManual);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de..");
		mntmAcercaDe.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/hm-about.png")));
		mntmAcercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmPrincipal,"IT10 Cooperativa - www.it10coop.com.ar","Acerca de..",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/it10.png")));
			}
		});
		mnAyuda.add(mntmAcercaDe);
		
		btnReclamos = new JButton("RECLAMO RAPIDO");
		btnReclamos.setHorizontalAlignment(SwingConstants.LEADING);
		btnReclamos.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReclamos.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamo_rapido.png")));
		btnReclamos.setBounds(100, 92, 275, 50);
		btnReclamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reclamos();
			}
		});
		frmPrincipal.getContentPane().setLayout(null);
		frmPrincipal.getContentPane().add(btnReclamos);
		
		btnReclamos_Piezas = new JButton("RECLAMOS DE PIEZAS");
		btnReclamos_Piezas.setHorizontalAlignment(SwingConstants.LEADING);
		btnReclamos_Piezas.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/reclamospie.png")));
		btnReclamos_Piezas.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReclamos_Piezas.setBounds(100, 192, 275, 50);
		btnReclamos_Piezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.repuestos();	
			}
		});
		frmPrincipal.getContentPane().add(btnReclamos_Piezas);
		
		btnReportesGestion = new JButton("REPORTES GESTION");
		btnReportesGestion.setHorizontalAlignment(SwingConstants.LEADING);
		btnReportesGestion.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReportesGestion.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/gestion1.png")));
		btnReportesGestion.setBounds(100, 292, 275, 50);
		btnReportesGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportesGestion();
			}
		});
		frmPrincipal.getContentPane().add(btnReportesGestion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(420, 40, 600, 450);
		frmPrincipal.getContentPane().add(panel);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaNotificaciones = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaNotificaciones.setBackground(SystemColor.window);
		// Agregamos el ordenador para las tablas de los usuarios
		// TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		// tablaNotificaciones.setRowSorter(ordenador);
		tablaNotificaciones.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaNotificaciones.getColumnCount(); i++) {
			tablaNotificaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaNotificaciones.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaNotificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaNotificaciones.setBounds(0, 0, 750, 320);
		tablaNotificaciones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPaneTabla = new JScrollPane(tablaNotificaciones);
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablaNotificaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verNotificacion();
			    else{
			    	e.consume();
			    }   
			}
		});
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(scrollPaneTabla);
		
		JLabel lblNotificaciones = new JLabel("NOTIFICACIONES");
		lblNotificaciones.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblNotificaciones.setBounds(655, 0, 126, 21);
		lblNotificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotificaciones.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNotificaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
		frmPrincipal.getContentPane().add(lblNotificaciones);
		
		btnReportesControl = new JButton("REPORTES CONTROL");
		btnReportesControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//setVisible(false);
				mediadorPrincipal.reportesControl();
			}
		});
		btnReportesControl.setIcon(new ImageIcon(GUIMenu_Principal.class.getResource("/cliente/Resources/Icons/control.png")));
		btnReportesControl.setHorizontalAlignment(SwingConstants.LEADING);
		btnReportesControl.setFont(new Font("Arial Black", Font.BOLD, 14));
		btnReportesControl.setBounds(100, 392, 275, 50);
		frmPrincipal.getContentPane().add(btnReportesControl);
	}

	protected void verNotificacion() {
		int row = tablaNotificaciones.getSelectedRow();
		if (row >= 0) {
			mediadorPrincipal.verNotificacion(row);
		}else{
			JOptionPane.showMessageDialog(tablaNotificaciones,"Seleccione una notificacion primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
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

	public void actualizarTabla(Vector<Notificacion_ReclamoDTO> notificaciones) {
		datosTabla = new Vector<>();
		for(int i =0; i<notificaciones.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(notificaciones.elementAt(i).getNotificacion().getTipo_notificacion());
			row.add(notificaciones.elementAt(i).getNotificacion().getTexto_notificacion());
			
			datosTabla.add(row);
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		for(int i = 0; i < tablaNotificaciones.getColumnCount(); i++) {
			tablaNotificaciones.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaNotificaciones.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
}
