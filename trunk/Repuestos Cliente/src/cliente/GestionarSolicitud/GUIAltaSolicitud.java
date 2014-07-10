package cliente.GestionarSolicitud;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cliente.utils.JPanel_Whit_Image;
import cliente.utils.TransparentPanel;

import com.toedter.calendar.JDateChooser;

public class GUIAltaSolicitud extends JFrame {

	private static final long serialVersionUID = 1L;
	protected MediadorSolicitud mediador;
	private JComboBox cbTSolicitante;
	private JComboBox cbNSolicitante;
	private JTextField tFNumPieza;
	private JEditorPane ePDescripcion;
	private JComboBox cBTProveedor;
	private JComboBox cBNProveedor;
	private JTextField tFNumPedido;
	private JTextField tFDominio;
	private JTextField tFNumOrden;
	private JComboBox cBCargo;
	private JRadioButton rBStockPropio;
	private JRadioButton rBStockFabrica;
	private JCheckBox chckbxBloqueado;
	private JPanel panelFechas;
	private JPanel panelTaller;
	private JPanel panelSeguro;
	private JDateChooser dCFSProveedor;
	private JDateChooser dCFSSolicitante;
	private JTextField tfPnc;
	private JTextField tfPCL;
	private JTextField tFNumeroSiniestro;
	private JTextField tFNombrePerito;
	private JTextField tFTelefonoPerito;
	private JTextField tFEmailPerito;
	private Vector<String> tiposSolicitantes;
	private Vector<String> tiposProveedor;
	private Vector<String> tiposCargos;
	private Vector<String> nombresSolicitantes;
	private Vector<String> nombresProveedor;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JSpinner jScantidad;
	private JButton btnActualizarSolicitante;
	private JButton btnActualizarProveedor;
	private JDateChooser dCFeLlegada;
	private JLabel lblFechaEstimadaDe;
	private JPanel contentPane;
	
	
	public GUIAltaSolicitud(final MediadorSolicitud mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}

	private void cargarDatos() {
		tiposSolicitantes = new Vector<String>();
		tiposSolicitantes.add("");
		tiposSolicitantes.add("Mostrador");
		tiposSolicitantes.add("Mayorista");
		tiposSolicitantes.add("Garantia");
		tiposSolicitantes.add("Seguro");
		tiposSolicitantes.add("Taller Mecanico");
		tiposSolicitantes.add("Taller Carroceria");
				
		tiposProveedor = new Vector<String>();
		tiposProveedor.add("");
		tiposProveedor.add("Fabrica");
		tiposProveedor.add("Sucursal");
		tiposProveedor.add("Alternativo");
		
		tiposCargos = mediador.obtenerCargos();
		
		nombresSolicitantes = new Vector<String>();
		nombresProveedor = new Vector<String>();
	}

	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/add_pedido_agente.png")));
		setTitle("NUEVA SOLICITUD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 963, 506);
		setResizable(false);
		
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);
		
		contentPane.setLayout(null);

		JPanel panelSolicitante = new TransparentPanel();
		panelSolicitante.setBounds(10, 11, 927, 45);
		contentPane.add(panelSolicitante);
		panelSolicitante.setLayout(null);
		
		JLabel lblSolicitante = new JLabel("Solicitante");
		lblSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitante.setBounds(10, 10, 115, 20);
		panelSolicitante.add(lblSolicitante);
		
		cbTSolicitante = new JComboBox();
		cbTSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbTSolicitante.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarSolicitante();
			}
		});
		cbTSolicitante.setModel(new DefaultComboBoxModel<String>(tiposSolicitantes));
		cbTSolicitante.setBounds(135, 10, 200, 20);
		panelSolicitante.add(cbTSolicitante);
		
		cbNSolicitante = new JComboBox();
		cbNSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cbNSolicitante.setModel(new DefaultComboBoxModel<String>(nombresSolicitantes));
		cbNSolicitante.setBounds(340, 10, 370, 20);
		panelSolicitante.add(cbNSolicitante);
		
		JButton btnNuevoSolicitante = new JButton("Nuevo Solicitante");
		btnNuevoSolicitante.setIcon(new ImageIcon(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/add.png")));
		btnNuevoSolicitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.nuevoSolicitante();
			}
		});
		btnNuevoSolicitante.setBounds(750, 10, 175, 20);
		panelSolicitante.add(btnNuevoSolicitante);
		
		btnActualizarSolicitante = new JButton("");
		btnActualizarSolicitante.setIcon(new ImageIcon(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/1refresh.png")));
		btnActualizarSolicitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarSolicitante();
			}
		});
		btnActualizarSolicitante.setBounds(715, 10, 30, 20);
		panelSolicitante.add(btnActualizarSolicitante);
		
		JPanel panelProveedor = new TransparentPanel();
		panelProveedor.setBounds(10, 70, 927, 45);
		contentPane.add(panelProveedor);
		panelProveedor.setLayout(null);
		
		JLabel label = new JLabel("Proveedor");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 10, 115, 20);
		panelProveedor.add(label);
		
		cBTProveedor = new JComboBox();
		cBTProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cBTProveedor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarProveedor();
			}
		});
		cBTProveedor.setModel(new DefaultComboBoxModel<String>(tiposProveedor));
		cBTProveedor.setBounds(135, 10, 200, 20);
		panelProveedor.add(cBTProveedor);
		
		cBNProveedor = new JComboBox();
		cBNProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cBNProveedor.setModel(new DefaultComboBoxModel<String>(nombresProveedor));
		cBNProveedor.setBounds(340, 10, 370, 20);
		panelProveedor.add(cBNProveedor);
		
		JButton btnNuevoProveedor = new JButton("Nuevo Proveedor");
		btnNuevoProveedor.setIcon(new ImageIcon(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/add.png")));
		btnNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.nuevoProveedor();
			}
		});
		btnNuevoProveedor.setBounds(750, 10, 175, 20);
		panelProveedor.add(btnNuevoProveedor);
		
		btnActualizarProveedor = new JButton("");
		btnActualizarProveedor.setBorderPainted(false);
		btnActualizarProveedor.setIcon(new ImageIcon(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/1refresh.png")));
		btnActualizarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarProveedor();
			}
		});
		btnActualizarProveedor.setBounds(715, 10, 30, 20);
		panelProveedor.add(btnActualizarProveedor);
		
		JPanel panelPieza = new TransparentPanel();
		panelPieza.setBounds(10, 126, 464, 184);
		contentPane.add(panelPieza);
		panelPieza.setLayout(null);
		
		JLabel lblNumeroPieza = new JLabel("Numero Pieza");
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPieza.setBounds(10, 10, 115, 20);
		panelPieza.add(lblNumeroPieza);
		
		tFNumPieza = new JTextField();
		tFNumPieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumPieza.setBounds(135, 10, 200, 20);
		panelPieza.add(tFNumPieza);
		tFNumPieza.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(10, 40, 115, 20);
		panelPieza.add(lblDescripcion);
		
		ePDescripcion = new JEditorPane();
		ePDescripcion.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		ePDescripcion.setBounds(135, 40, 300, 100);
		panelPieza.add(ePDescripcion);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(10, 150, 115, 20);
		panelPieza.add(lblCantidad);
		
		jScantidad = new JSpinner();
		jScantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		jScantidad.setModel(new SpinnerNumberModel(1, 1, 16, 1));
		jScantidad.setBounds(135, 150, 115, 20);
		panelPieza.add(jScantidad);
		
		/// Panel Taller ///
		panelTaller = new TransparentPanel();
		panelTaller.setBounds(10, 321, 650, 136);
		panelTaller.setLayout(null);
		contentPane.add(panelTaller);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setBounds(10, 10, 115, 20);
		panelTaller.add(lblNumeroPedido);
		
		tFNumPedido = new JTextField();
		tFNumPedido.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumPedido.setColumns(10);
		tFNumPedido.setBounds(135, 10, 200, 20);
		panelTaller.add(tFNumPedido);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio.setBounds(10, 40, 115, 20);
		panelTaller.add(lblDominio);
		
		tFDominio = new JTextField();
		tFDominio.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFDominio.setColumns(10);
		tFDominio.setBounds(135, 40, 100, 20);
		panelTaller.add(tFDominio);
		
		JLabel lblOdenDeTrabajo = new JLabel("Oden de Trabajo");
		lblOdenDeTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdenDeTrabajo.setBounds(10, 70, 115, 20);
		panelTaller.add(lblOdenDeTrabajo);
		
		tFNumOrden = new JTextField();
		tFNumOrden.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumOrden.setColumns(10);
		tFNumOrden.setBounds(135, 70, 200, 20);
		panelTaller.add(tFNumOrden);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(10, 100, 115, 20);
		panelTaller.add(lblCargo);
		
		cBCargo = new JComboBox();
		cBCargo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cBCargo.setModel(new DefaultComboBoxModel<String>(tiposCargos));
		cBCargo.setBounds(135, 100, 200, 20);
		panelTaller.add(cBCargo);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPnc.setBounds(341, 10, 115, 20);
		panelTaller.add(lblPnc);
		
		tfPnc = new JTextField();
		tfPnc.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfPnc.setColumns(10);
		tfPnc.setBounds(466, 10, 100, 20);
		panelTaller.add(tfPnc);
		
		JLabel lblPcl = new JLabel("PCL");
		lblPcl.setHorizontalAlignment(SwingConstants.CENTER);
		lblPcl.setBounds(341, 40, 115, 20);
		panelTaller.add(lblPcl);
		
		tfPCL = new JTextField();
		tfPCL.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tfPCL.setColumns(10);
		tfPCL.setBounds(466, 40, 100, 20);
		panelTaller.add(tfPCL);
		
		panelTaller.setVisible(false);
		
		panelSeguro = new TransparentPanel();
		panelSeguro.setBounds(10, 321, 650, 136);
		panelSeguro.setLayout(null);
		contentPane.add(panelSeguro);
		
		JLabel lblNumeroSiniestro = new JLabel("Numero Siniestro");
		lblNumeroSiniestro.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroSiniestro.setBounds(10, 10, 115, 20);
		panelSeguro.add(lblNumeroSiniestro);
		
		tFNumeroSiniestro = new JTextField();
		tFNumeroSiniestro.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumeroSiniestro.setColumns(10);
		tFNumeroSiniestro.setBounds(135, 10, 200, 20);
		panelSeguro.add(tFNumeroSiniestro);
		
		JLabel lblNombrePerito = new JLabel("Nombre Perito");
		lblNombrePerito.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePerito.setBounds(10, 40, 115, 20);
		panelSeguro.add(lblNombrePerito);
		
		tFNombrePerito = new JTextField();
		tFNombrePerito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNombrePerito.setColumns(10);
		tFNombrePerito.setBounds(135, 40, 200, 20);
		panelSeguro.add(tFNombrePerito);
		
		JLabel lblTelefonoPerito = new JLabel("Telefono");
		lblTelefonoPerito.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonoPerito.setBounds(10, 70, 115, 20);
		panelSeguro.add(lblTelefonoPerito);
		
		tFTelefonoPerito = new JTextField();
		tFTelefonoPerito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFTelefonoPerito.setColumns(10);
		tFTelefonoPerito.setBounds(135, 70, 115, 20);
		panelSeguro.add(tFTelefonoPerito);
		
		JLabel lblEmailPerito = new JLabel("Email");
		lblEmailPerito.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailPerito.setBounds(10, 100, 115, 20);
		panelSeguro.add(lblEmailPerito);
		
		tFEmailPerito = new JTextField();
		tFEmailPerito.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFEmailPerito.setColumns(10);
		tFEmailPerito.setBounds(135, 100, 200, 20);
		panelSeguro.add(tFEmailPerito);
		
		panelSeguro.setVisible(false);

		/////
		
		panelFechas = new TransparentPanel();
		panelFechas.setBounds(484, 126, 453, 184);
		contentPane.add(panelFechas);
		panelFechas.setLayout(null);
		
		JLabel lblFechaSolicitudSolicitante = new JLabel("Fecha Solicitud Solicitante");
		lblFechaSolicitudSolicitante.setBounds(10, 10, 180, 20);
		lblFechaSolicitudSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		panelFechas.add(lblFechaSolicitudSolicitante);
		
		dCFSSolicitante = new JDateChooser();
		dCFSSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSSolicitante.setDate(new Date());
		dCFSSolicitante.setBounds(200, 10, 163, 20);
		panelFechas.add(dCFSSolicitante);
		
		JLabel lblFechaSolicitudProvedoor = new JLabel("Fecha Solicitud Provedoor");
		lblFechaSolicitudProvedoor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudProvedoor.setBounds(10, 40, 180, 20);
		panelFechas.add(lblFechaSolicitudProvedoor);
		
		dCFSProveedor = new JDateChooser();
		dCFSProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSProveedor.setDate(new Date());
		dCFSProveedor.setBounds(200, 40, 163, 20);
		panelFechas.add(dCFSProveedor);
		
		rBStockFabrica = new JRadioButton("Stock Fabrica");
		rBStockFabrica.setBorder(null);
		rBStockFabrica.setContentAreaFilled(false);
		rBStockFabrica.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (rBStockFabrica.isSelected()){
					rBStockPropio.setSelected(false);
					chckbxBloqueado.setEnabled(true);
				}
			}
		});
		rBStockFabrica.setBounds(10, 130, 180, 20);
		panelFechas.add(rBStockFabrica);
		
		rBStockPropio = new JRadioButton("Stock Propio");
		rBStockPropio.setContentAreaFilled(false);
		rBStockPropio.setBorder(null);
		rBStockPropio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rBStockPropio.isSelected()){
					rBStockFabrica.setSelected(false);
					chckbxBloqueado.setSelected(false);
					chckbxBloqueado.setEnabled(false);
				}
			}
		});
		rBStockPropio.setBounds(10, 100, 180, 20);
		panelFechas.add(rBStockPropio);
		
		chckbxBloqueado = new JCheckBox("Bloqueado");
		chckbxBloqueado.setContentAreaFilled(false);
		chckbxBloqueado.setBorder(null);
		chckbxBloqueado.setBounds(200, 130, 180, 20);
		panelFechas.add(chckbxBloqueado);
		chckbxBloqueado.setEnabled(false);
		
		lblFechaEstimadaDe = new JLabel("Fecha Estimada Llegada");
		lblFechaEstimadaDe.setEnabled(false);
		lblFechaEstimadaDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEstimadaDe.setBounds(10, 70, 180, 20);
		panelFechas.add(lblFechaEstimadaDe);
		
		dCFeLlegada = new JDateChooser();
		dCFeLlegada.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFeLlegada.setEnabled(false);
		dCFeLlegada.setBounds(200, 70, 163, 20);
		panelFechas.add(dCFeLlegada);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setIcon(new ImageIcon(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/save.png")));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarSolicitud();
			}
		});
		btnGuardar.setBounds(670, 420, 125, 20);
		contentPane.add(btnGuardar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setIcon(new ImageIcon(GUIAltaSolicitud.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(815, 420, 125, 20);
		contentPane.add(btnCancelar);
		contentPane.setVisible(true);

	}
	
	protected void actualizarProveedor() {
		nombresProveedor = new Vector<String>();
		if(cBTProveedor.getSelectedItem().equals("Fabrica")){
			nombresProveedor = mediador.obtenerFabrica();
			lblFechaEstimadaDe.setEnabled(false);
			dCFeLlegada.setEnabled(false);			
		}else{
			if(cBTProveedor.getSelectedItem().equals("Sucursal")){
				nombresProveedor = mediador.obtenerSucursal();
				lblFechaEstimadaDe.setEnabled(false);
				dCFeLlegada.setEnabled(false);
			}else{
				if(cBTProveedor.getSelectedItem().equals("Alternativo")){
					nombresProveedor = mediador.obtenerAlternativo();
					lblFechaEstimadaDe.setEnabled(true);
					dCFeLlegada.setEnabled(true);
				}
			}
		}
		cBNProveedor.setModel(new DefaultComboBoxModel<String>(nombresProveedor));
		cBNProveedor.updateUI();		
	}

	protected void actualizarSolicitante() {
		nombresSolicitantes = new Vector<String>();
		if(cbTSolicitante.getSelectedItem().equals("Mostrador")){
			nombresSolicitantes = mediador.obtenerMostrador();
			//panelMostrador.setVisible(true);
			panelSeguro.setVisible(false);
			panelTaller.setVisible(false);
		}else{
			if(cbTSolicitante.getSelectedItem().equals("Mayorista")){
				nombresSolicitantes = mediador.obtenerMayorista();
				//panelMostrador.setVisible(true);
				panelSeguro.setVisible(false);
				panelTaller.setVisible(false);
			}else{
				if(cbTSolicitante.getSelectedItem().equals("Garantia")){
					nombresSolicitantes = mediador.obtenerGarantia();
					//panelMostrador.setVisible(false);
					panelSeguro.setVisible(false);
					panelTaller.setVisible(true);
				}else{
					if(cbTSolicitante.getSelectedItem().equals("Seguro")){
						nombresSolicitantes = mediador.obtenerSeguro();
						//panelMostrador.setVisible(false);
						panelSeguro.setVisible(true);
						panelTaller.setVisible(false);					}
					else{
						if(cbTSolicitante.getSelectedItem().equals("Taller Mecanico")){
							nombresSolicitantes = mediador.obtenerMecanico();
							//panelMostrador.setVisible(false);
							panelSeguro.setVisible(false);
							panelTaller.setVisible(true);						
						}else{
							if(cbTSolicitante.getSelectedItem().equals("Taller Carroceria")){
								nombresSolicitantes = mediador.obtenerCarroceria();
								//panelMostrador.setVisible(false);
								panelSeguro.setVisible(false);
								panelTaller.setVisible(true);
							}
						}
					}
				}
			}
		}
		cbNSolicitante.setModel(new DefaultComboBoxModel<String>(nombresSolicitantes));
		cbNSolicitante.updateUI();
	}

	protected void guardarSolicitud() {
		if(cbTSolicitante.getSelectedItem().toString().isEmpty() || cBTProveedor.getSelectedItem().toString().isEmpty() || tFNumPieza.getText().isEmpty() ||
				cBNProveedor.getSelectedItem().toString().isEmpty() || cbNSolicitante.getSelectedItem().toString().isEmpty() || 
				(cBTProveedor.getSelectedItem().toString().equals("Alternativo") && dCFeLlegada.getDate()==null)){
			JOptionPane.showMessageDialog(contentPane,"Faltan Algunos Datos Oblogatorios.","Error",JOptionPane.ERROR_MESSAGE);
		}else{
			String id_proveedor = cBNProveedor.getSelectedItem().toString().subSequence(cBNProveedor.getSelectedItem().toString().indexOf(" [ID: ")+6, cBNProveedor.getSelectedItem().toString().indexOf(" ]")).toString();
			String id_solicitante = cbNSolicitante.getSelectedItem().toString().subSequence(cbNSolicitante.getSelectedItem().toString().indexOf(" [ID: ")+6, cbNSolicitante.getSelectedItem().toString().indexOf(" ]")).toString();
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dCFSProveedor.getDate());
		    java.sql.Date fsProveedor = new java.sql.Date(dCFSProveedor.getDate().getTime());
			
		    String fecha2 = format2.format(dCFSSolicitante.getDate());
		    java.sql.Date fsSolicitante = new java.sql.Date(dCFSSolicitante.getDate().getTime());
		    
		    java.sql.Date feLlegada = null;
		    if(dCFeLlegada.getDate()!=null){
		    	String fecha3 = format2.format(dCFeLlegada.getDate());
		    	feLlegada = new java.sql.Date(dCFeLlegada.getDate().getTime());
		    }
			if(cbTSolicitante.getSelectedItem().equals("Mostrador")){
				if(mediador.nuevaSolicitudMostrador(id_proveedor,id_solicitante,tFNumPieza.getText(),ePDescripcion.getText(),jScantidad.getValue().toString(),
						fsProveedor,fsSolicitante,feLlegada,rBStockFabrica.isSelected(),rBStockPropio.isSelected(),chckbxBloqueado.isSelected())){
					JOptionPane.showMessageDialog(contentPane,"Solicitud Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
					mediador.actualizarDatosGestion();
					dispose();
				}else{
					JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}else{
				if(cbTSolicitante.getSelectedItem().equals("Mayorista")){
					if(mediador.nuevaSolicitudMayorista(id_proveedor,id_solicitante,tFNumPieza.getText(),ePDescripcion.getText(),jScantidad.getValue().toString(),
							fsProveedor,fsSolicitante,feLlegada,rBStockFabrica.isSelected(),rBStockPropio.isSelected(),chckbxBloqueado.isSelected())){
						JOptionPane.showMessageDialog(contentPane,"Solicitud Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
					}
				}else{
					if(cbTSolicitante.getSelectedItem().equals("Garantia")){
						if(tFNumPedido.getText().isEmpty() || tFNumOrden.getText().isEmpty()){
							JOptionPane.showMessageDialog(contentPane,"Faltan Algunos Datos Oblogatorios.","Error",JOptionPane.ERROR_MESSAGE);
						}else{
							if(mediador.nuevaSolicitudGarantia(id_proveedor,id_solicitante,tFNumPieza.getText(),ePDescripcion.getText(),jScantidad.getValue().toString(),
									fsProveedor,fsSolicitante,feLlegada,rBStockFabrica.isSelected(),rBStockPropio.isSelected(),chckbxBloqueado.isSelected(),tFNumPedido.getText(),tFDominio.getText(),tFNumOrden.getText(),cBCargo.getSelectedItem().toString(),tfPnc.getText(),tfPCL.getText())){
								JOptionPane.showMessageDialog(contentPane,"Solicitud Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
								mediador.actualizarDatosGestion();
								dispose();
							}else{
								JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
							}
						}
					}else{
						if(cbTSolicitante.getSelectedItem().equals("Seguro")){
							if(tFNumeroSiniestro.getText().isEmpty() || tFNombrePerito.getText().isEmpty()){
								JOptionPane.showMessageDialog(contentPane,"Faltan Algunos Datos Oblogatorios.","Error",JOptionPane.ERROR_MESSAGE);
							}else{
								if(mediador.nuevaSolicitudSeguro(id_proveedor,id_solicitante,tFNumPieza.getText(),ePDescripcion.getText(),jScantidad.getValue().toString(),
										fsProveedor,fsSolicitante,feLlegada,rBStockFabrica.isSelected(),rBStockPropio.isSelected(),chckbxBloqueado.isSelected(),tFNumeroSiniestro.getText(),
										tFNombrePerito.getText(),tFTelefonoPerito.getText(),tFEmailPerito.getText())){
									JOptionPane.showMessageDialog(contentPane,"Solicitud Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
									mediador.actualizarDatosGestion();
									dispose();
								}else{
									JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
								}
							}
						}else{
							if(cbTSolicitante.getSelectedItem().equals("Taller Mecanico")){
								if(tFNumPedido.getText().isEmpty() || tFNumOrden.getText().isEmpty()){
									JOptionPane.showMessageDialog(contentPane,"Faltan Algunos Datos Oblogatorios.","Error",JOptionPane.ERROR_MESSAGE);
								}else{
									if(mediador.nuevaSolicitudMecanico(id_proveedor,id_solicitante,tFNumPieza.getText(),ePDescripcion.getText(),jScantidad.getValue().toString(),
											fsProveedor,fsSolicitante,feLlegada,rBStockFabrica.isSelected(),rBStockPropio.isSelected(),chckbxBloqueado.isSelected(),tFNumPedido.getText(),tFDominio.getText(),tFNumOrden.getText(),cBCargo.getSelectedItem().toString(),tfPnc.getText(),tfPCL.getText())){
										JOptionPane.showMessageDialog(contentPane,"Solicitud Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
										mediador.actualizarDatosGestion();
										dispose();
									}else{
										JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
									}
								}
							}else{
								if(cbTSolicitante.getSelectedItem().equals("Taller Carroceria")){
									if(tFNumPedido.getText().isEmpty() || tFNumOrden.getText().isEmpty()){
										JOptionPane.showMessageDialog(contentPane,"Faltan Algunos Datos Oblogatorios.","Error",JOptionPane.ERROR_MESSAGE);
									}else{
										if(mediador.nuevaSolicitudCarroceria(id_proveedor,id_solicitante,tFNumPieza.getText(),ePDescripcion.getText(),jScantidad.getValue().toString(),
												fsProveedor,fsSolicitante,feLlegada,rBStockFabrica.isSelected(),rBStockPropio.isSelected(),chckbxBloqueado.isSelected(),tFNumPedido.getText(),tFDominio.getText(),tFNumOrden.getText(),cBCargo.getSelectedItem().toString(),tfPnc.getText(),tfPCL.getText())){
											JOptionPane.showMessageDialog(contentPane,"Solicitud Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
											mediador.actualizarDatosGestion();
											dispose();
										}else{
											JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
										}
									}
								}
							}
						}
					}
				}
			}
		}	
	}
}
