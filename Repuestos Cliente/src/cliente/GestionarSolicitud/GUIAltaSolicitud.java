package cliente.GestionarSolicitud;

import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

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
	private JPanel panelDinamico;
	private JTextField tFNumPedido;
	private JTextField tFDominio;
	private JTextField tFNumOrden;
	private JComboBox cBCargo;
	private JCheckBox chckbxStockPropio;
	private JCheckBox chckbxStockFabrica;
	private JCheckBox chckbxBloqueado;
	private JPanel panelFechas;
	private JDateChooser dCFSProveedor;
	private JDateChooser dCFSSolicitante;
	private JTextField tfPnc;
	private JTextField tfPCL;
	private JTextField tFNombreCliente;
	private JTextField tFTelefonoCliente;
	private JTextField tFEmailCliente;
	private JTextField tFNumeroSiniestro;
	private JTextField tFNombrePerito;
	private JTextField tFTelefonoPerito;
	private JTextField tFEmailPerito;
	
	public GUIAltaSolicitud(final MediadorSolicitud mediador) {
		this.mediador = mediador;
		getContentPane().setLayout(null);
		
		JPanel panelSolicitante = new JPanel();
		panelSolicitante.setBounds(10, 11, 927, 45);
		getContentPane().add(panelSolicitante);
		panelSolicitante.setLayout(null);
		
		JLabel lblSolicitante = new JLabel("Solicitante");
		lblSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitante.setBounds(10, 10, 115, 20);
		panelSolicitante.add(lblSolicitante);
		
		cbTSolicitante = new JComboBox();
		cbTSolicitante.setBounds(135, 10, 200, 20);
		panelSolicitante.add(cbTSolicitante);
		
		cbNSolicitante = new JComboBox();
		cbNSolicitante.setBounds(342, 10, 430, 20);
		panelSolicitante.add(cbNSolicitante);
		
		JButton btnNuevoSolicitante = new JButton("Nuevo Solicitante");
		btnNuevoSolicitante.setBounds(780, 10, 135, 20);
		panelSolicitante.add(btnNuevoSolicitante);
		
		JPanel panelProveedor = new JPanel();
		panelProveedor.setBounds(10, 70, 927, 45);
		getContentPane().add(panelProveedor);
		panelProveedor.setLayout(null);
		
		JLabel label = new JLabel("Proveedor");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 10, 115, 20);
		panelProveedor.add(label);
		
		cBTProveedor = new JComboBox();
		cBTProveedor.setBounds(135, 10, 200, 20);
		panelProveedor.add(cBTProveedor);
		
		cBNProveedor = new JComboBox();
		cBNProveedor.setBounds(342, 10, 430, 20);
		panelProveedor.add(cBNProveedor);
		
		JButton btnNuevoProveedor = new JButton("Nuevo Proveedor");
		btnNuevoProveedor.setBounds(780, 10, 135, 20);
		panelProveedor.add(btnNuevoProveedor);
		
		JPanel panelPieza = new JPanel();
		panelPieza.setBounds(10, 126, 464, 184);
		getContentPane().add(panelPieza);
		panelPieza.setLayout(null);
		
		JLabel lblNumeroPieza = new JLabel("Numero Pieza");
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPieza.setBounds(10, 10, 115, 20);
		panelPieza.add(lblNumeroPieza);
		
		tFNumPieza = new JTextField();
		tFNumPieza.setBounds(135, 10, 200, 20);
		panelPieza.add(tFNumPieza);
		tFNumPieza.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(10, 40, 115, 20);
		panelPieza.add(lblDescripcion);
		
		ePDescripcion = new JEditorPane();
		ePDescripcion.setBounds(135, 40, 300, 97);
		panelPieza.add(ePDescripcion);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(10, 150, 115, 20);
		panelPieza.add(lblCantidad);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 16, 1));
		spinner.setBounds(135, 150, 115, 20);
		panelPieza.add(spinner);
		
		panelDinamico = new JPanel();
		panelDinamico.setBounds(10, 321, 650, 136);
		getContentPane().add(panelDinamico);
		panelDinamico.setLayout(null);
		
		panelFechas = new JPanel();
		panelFechas.setBounds(484, 126, 453, 184);
		getContentPane().add(panelFechas);
		panelFechas.setLayout(null);
		
		JLabel lblFechaSolicitudSolicitante = new JLabel("Fecha Solicitud Solicitante");
		lblFechaSolicitudSolicitante.setBounds(10, 10, 150, 20);
		lblFechaSolicitudSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		panelFechas.add(lblFechaSolicitudSolicitante);
		
		dCFSSolicitante = new JDateChooser();
		dCFSSolicitante.setDate(new Date());
		dCFSSolicitante.setBounds(170, 10, 163, 20);
		panelFechas.add(dCFSSolicitante);
		
		JLabel lblFechaSolicitudProvedoor = new JLabel("Fecha Solicitud Provedoor");
		lblFechaSolicitudProvedoor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudProvedoor.setBounds(10, 40, 150, 20);
		panelFechas.add(lblFechaSolicitudProvedoor);
		
		dCFSProveedor = new JDateChooser();
		dCFSProveedor.setDate(new Date());
		dCFSProveedor.setBounds(170, 40, 163, 20);
		panelFechas.add(dCFSProveedor);
		
		chckbxStockFabrica = new JCheckBox("Stock Fabrica");
		chckbxStockFabrica.setBounds(10, 100, 150, 20);
		panelFechas.add(chckbxStockFabrica);
		
		chckbxStockPropio = new JCheckBox("Stock Propio");
		chckbxStockPropio.setBounds(10, 70, 150, 20);
		panelFechas.add(chckbxStockPropio);
		
		chckbxBloqueado = new JCheckBox("Bloqueado");
		chckbxBloqueado.setBounds(170, 100, 150, 20);
		panelFechas.add(chckbxBloqueado);
		chckbxBloqueado.setEnabled(false);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(690, 375, 115, 20);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(820, 375, 115, 20);
		getContentPane().add(btnCancelar);
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 506);
		setPanelSeguro();
		
	}
	
	private void setPanelTaller(){
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setBounds(10, 10, 115, 20);
		panelDinamico.add(lblNumeroPedido);
		
		tFNumPedido = new JTextField();
		tFNumPedido.setColumns(10);
		tFNumPedido.setBounds(135, 10, 200, 20);
		panelDinamico.add(tFNumPedido);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio.setBounds(10, 40, 115, 20);
		panelDinamico.add(lblDominio);
		
		tFDominio = new JTextField();
		tFDominio.setColumns(10);
		tFDominio.setBounds(135, 40, 100, 20);
		panelDinamico.add(tFDominio);
		
		JLabel lblOdenDeTrabajo = new JLabel("Oden de Trabajo");
		lblOdenDeTrabajo.setHorizontalAlignment(SwingConstants.CENTER);
		lblOdenDeTrabajo.setBounds(10, 70, 115, 20);
		panelDinamico.add(lblOdenDeTrabajo);
		
		tFNumOrden = new JTextField();
		tFNumOrden.setColumns(10);
		tFNumOrden.setBounds(135, 70, 200, 20);
		panelDinamico.add(tFNumOrden);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCargo.setBounds(10, 100, 115, 20);
		panelDinamico.add(lblCargo);
		
		cBCargo = new JComboBox();
		cBCargo.setBounds(135, 100, 200, 20);
		panelDinamico.add(cBCargo);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPnc.setBounds(341, 10, 115, 20);
		panelDinamico.add(lblPnc);
		
		tfPnc = new JTextField();
		tfPnc.setColumns(10);
		tfPnc.setBounds(466, 10, 100, 20);
		panelDinamico.add(tfPnc);
		
		JLabel lblPcl = new JLabel("PCL");
		lblPcl.setHorizontalAlignment(SwingConstants.CENTER);
		lblPcl.setBounds(341, 40, 115, 20);
		panelDinamico.add(lblPcl);
		
		tfPCL = new JTextField();
		tfPCL.setColumns(10);
		tfPCL.setBounds(466, 40, 100, 20);
		panelDinamico.add(tfPCL);
	}

	private void setPanelMostrador(){
		JLabel lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreCliente.setBounds(10, 10, 115, 20);
		panelDinamico.add(lblNombreCliente);
		
		tFNombreCliente = new JTextField();
		tFNombreCliente.setColumns(10);
		tFNombreCliente.setBounds(135, 10, 200, 20);
		panelDinamico.add(tFNombreCliente);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setBounds(10, 35, 115, 20);
		panelDinamico.add(lblTelefono);
		
		tFTelefonoCliente = new JTextField();
		tFTelefonoCliente.setColumns(10);
		tFTelefonoCliente.setBounds(135, 35, 115, 20);
		panelDinamico.add(tFTelefonoCliente);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(10, 60, 115, 20);
		panelDinamico.add(lblEmail);
		
		tFEmailCliente = new JTextField();
		tFEmailCliente.setColumns(10);
		tFEmailCliente.setBounds(135, 60, 200, 20);
		panelDinamico.add(tFEmailCliente);
	}

	private void setPanelSeguro(){
		JLabel lblNumeroSiniestro = new JLabel("Numero Siniestro");
		lblNumeroSiniestro.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroSiniestro.setBounds(10, 10, 115, 20);
		panelDinamico.add(lblNumeroSiniestro);
		
		tFNumeroSiniestro = new JTextField();
		tFNumeroSiniestro.setColumns(10);
		tFNumeroSiniestro.setBounds(135, 10, 200, 20);
		panelDinamico.add(tFNumeroSiniestro);
		
		JLabel lblNombrePerito = new JLabel("Nombre Perito");
		lblNombrePerito.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombrePerito.setBounds(10, 40, 115, 20);
		panelDinamico.add(lblNombrePerito);
		
		tFNombrePerito = new JTextField();
		tFNombrePerito.setColumns(10);
		tFNombrePerito.setBounds(135, 40, 200, 20);
		panelDinamico.add(tFNombrePerito);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setBounds(10, 70, 115, 20);
		panelDinamico.add(lblTelefono);
		
		tFTelefonoPerito = new JTextField();
		tFTelefonoPerito.setColumns(10);
		tFTelefonoPerito.setBounds(135, 70, 115, 20);
		panelDinamico.add(tFTelefonoPerito);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(10, 100, 115, 20);
		panelDinamico.add(lblEmail);
		
		tFEmailPerito = new JTextField();
		tFEmailPerito.setColumns(10);
		tFEmailPerito.setBounds(135, 100, 200, 20);
		panelDinamico.add(tFEmailPerito);
	}
}