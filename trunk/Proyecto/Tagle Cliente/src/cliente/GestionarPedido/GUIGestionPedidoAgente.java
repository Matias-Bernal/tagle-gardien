package cliente.GestionarPedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;

public class GUIGestionPedidoAgente extends JFrame{

	private static final long serialVersionUID = 1L;

	protected MediadorPedido mediador;
	
	private JPanel contentPane;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;
	private JButton btnVer;
	private JTextField tfpedido;
	private JTextField tfpnc;
	private JTextField tfReclamo;
	private JTextField tfNombre_Agente;
	
	//Tabla
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private DefaultTableModel modelo;
	private JTable tablaPedidos;
	private JTextField tfReclamante;
	private JTextField tfTitular;
	private JTextField tfDominio;
	private JTextField tfOrden;
	private JTextField tfPieza;
	private JTextField tfMuleto;
	private JTextField tfManoObra;
	private JTextField tfBDG;
	private JTextField tfRemito;
	private JTextField tfVin;
	



	public GUIGestionPedidoAgente(MediadorPedido mediadorRegistrante) {
		this.mediador = mediadorRegistrante;
		cargarDatos();
		initialize();

	}
	


	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("GESTIONAR PEDIDO AGENTE");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(974, 70, 280, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(974, 104, 280, 23);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnEliminar);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaPedidos = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false,false, false,false,false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPedidos.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaPedidos);
		scrollPaneTabla.setBounds(10, 226, 1244, 398);
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(974, 35, 280, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//mediador.altaRegistrante(tFnombre_registrante.getText(), (String) comboBox.getSelectedItem());
			}
		});
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(506, 634, 150, 23);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(666, 634, 150, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(974, 138, 280, 23);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnActualizar);
		
		btnVer = new JButton("Ver");
		btnVer.setBounds(346, 634, 150, 23);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnVer);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 304, 209);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNumero_pedido = new JLabel("Numero Pedido");
		lblNumero_pedido.setBounds(10, 10, 130, 20);
		panel.add(lblNumero_pedido);
		lblNumero_pedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfpedido = new JTextField();
		tfpedido.setBounds(150, 10, 128, 20);
		panel.add(tfpedido);
		tfpedido.setColumns(10);
		
		JLabel lbl_FSP = new JLabel("Fecha Solicitud Pedido");
		lbl_FSP.setBounds(10, 35, 130, 20);
		panel.add(lbl_FSP);
		lbl_FSP.setHorizontalAlignment(SwingConstants.CENTER);
		
		JDateChooser dc_fsp = new JDateChooser();
		dc_fsp.setBounds(150, 35, 128, 20);
		panel.add(dc_fsp);
		
		JLabel lblTitular = new JLabel("Nombre Titular");
		lblTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitular.setBounds(10, 110, 130, 20);
		panel.add(lblTitular);
		
		tfTitular = new JTextField();
		tfTitular.setColumns(10);
		tfTitular.setBounds(150, 110, 128, 20);
		panel.add(tfTitular);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio.setBounds(10, 135, 130, 20);
		panel.add(lblDominio);
		
		tfDominio = new JTextField();
		tfDominio.setColumns(10);
		tfDominio.setBounds(150, 135, 128, 20);
		panel.add(tfDominio);
		
		JLabel lblNumeroPieza = new JLabel("Numero Pieza");
		lblNumeroPieza.setBounds(10, 185, 130, 20);
		panel.add(lblNumeroPieza);
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPieza = new JTextField();
		tfPieza.setBounds(150, 185, 128, 20);
		panel.add(tfPieza);
		tfPieza.setColumns(10);
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setBounds(10, 60, 130, 20);
		panel.add(lblEstadoPedido);
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lbl_FTurno = new JLabel("Fecha Turno");
		lbl_FTurno.setBounds(10, 85, 130, 20);
		panel.add(lbl_FTurno);
		lbl_FTurno.setHorizontalAlignment(SwingConstants.CENTER);
		
		JDateChooser dc_fturno = new JDateChooser();
		dc_fturno.setBounds(150, 85, 128, 20);
		panel.add(dc_fturno);
		
		JComboBox cBEstadoPedido = new JComboBox();
		cBEstadoPedido.setBounds(150, 60, 128, 20);
		panel.add(cBEstadoPedido);
		
		JLabel lblVin = new JLabel("VIN");
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBounds(10, 160, 130, 20);
		panel.add(lblVin);
		
		tfVin = new JTextField();
		tfVin.setColumns(10);
		tfVin.setBounds(150, 160, 128, 20);
		panel.add(tfVin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(327, 11, 304, 209);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lbl_FReclamo = new JLabel("Fecha Reclamo");
		lbl_FReclamo.setBounds(10, 35, 130, 20);
		panel_1.add(lbl_FReclamo);
		lbl_FReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JDateChooser dc_fReclamo = new JDateChooser();
		dc_fReclamo.setBounds(150, 35, 128, 20);
		panel_1.add(dc_fReclamo);
		
		JLabel lbl_reclamo = new JLabel("ID Reclamo");
		lbl_reclamo.setBounds(10, 10, 130, 20);
		panel_1.add(lbl_reclamo);
		lbl_reclamo.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfReclamo = new JTextField();
		tfReclamo.setBounds(150, 10, 128, 20);
		panel_1.add(tfReclamo);
		tfReclamo.setColumns(10);
		
		JLabel lblEstadoRecl = new JLabel("Estado Reclamo");
		lblEstadoRecl.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoRecl.setBounds(10, 60, 130, 20);
		panel_1.add(lblEstadoRecl);
		
		JLabel lbl_PNC = new JLabel("PNC");
		lbl_PNC.setBounds(10, 135, 130, 20);
		panel_1.add(lbl_PNC);
		lbl_PNC.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIdMuleto = new JLabel("ID Muleto");
		lblIdMuleto.setBounds(10, 160, 130, 20);
		panel_1.add(lblIdMuleto);
		lblIdMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfMuleto = new JTextField();
		tfMuleto.setBounds(150, 160, 128, 20);
		panel_1.add(tfMuleto);
		tfMuleto.setColumns(10);
		
		tfpnc = new JTextField();
		tfpnc.setBounds(150, 135, 128, 20);
		panel_1.add(tfpnc);
		tfpnc.setColumns(10);
		
		tfManoObra = new JTextField();
		tfManoObra.setColumns(10);
		tfManoObra.setBounds(150, 185, 128, 20);
		panel_1.add(tfManoObra);
		
		JLabel lblIdManoObra = new JLabel("ID Mano Obra");
		lblIdManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdManoObra.setBounds(10, 185, 130, 20);
		panel_1.add(lblIdManoObra);
		
		JComboBox cBEstadoReclamo = new JComboBox();
		cBEstadoReclamo.setBounds(150, 60, 128, 20);
		panel_1.add(cBEstadoReclamo);
		
		tfReclamante = new JTextField();
		tfReclamante.setBounds(150, 110, 128, 20);
		panel_1.add(tfReclamante);
		tfReclamante.setColumns(10);
		
		tfNombre_Agente = new JTextField();
		tfNombre_Agente.setBounds(150, 85, 128, 20);
		panel_1.add(tfNombre_Agente);
		tfNombre_Agente.setColumns(10);
		
		JLabel lbl_nom_registrante = new JLabel("Nombre Agente");
		lbl_nom_registrante.setBounds(10, 85, 130, 20);
		panel_1.add(lbl_nom_registrante);
		lbl_nom_registrante.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblReclamante = new JLabel("Nombre Reclamante");
		lblReclamante.setBounds(10, 110, 130, 20);
		panel_1.add(lblReclamante);
		lblReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(641, 11, 304, 209);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudFabrica.setBounds(10, 59, 130, 20);
		panel_2.add(lblFechaSolicitudFabrica);
		
		JDateChooser dCFSF = new JDateChooser();
		dCFSF.setBounds(150, 59, 128, 20);
		panel_2.add(dCFSF);
		
		JDateChooser dCFRF = new JDateChooser();
		dCFRF.setBounds(150, 84, 128, 20);
		panel_2.add(dCFRF);
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionFabrica.setBounds(10, 84, 130, 20);
		panel_2.add(lblFechaRecepcionFabrica);
		
		JLabel lblFechaEnvioAgente = new JLabel("Fecha Envio Agente");
		lblFechaEnvioAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEnvioAgente.setBounds(10, 159, 130, 20);
		panel_2.add(lblFechaEnvioAgente);
		
		JLabel lblFechaRecepcionAgente = new JLabel("Fecha Recepcion Agente");
		lblFechaRecepcionAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionAgente.setBounds(10, 184, 130, 20);
		panel_2.add(lblFechaRecepcionAgente);
		
		JDateChooser dCFRA = new JDateChooser();
		dCFRA.setBounds(150, 184, 128, 20);
		panel_2.add(dCFRA);
		
		JDateChooser dCFEA = new JDateChooser();
		dCFEA.setBounds(150, 159, 128, 20);
		panel_2.add(dCFEA);
		
		tfBDG = new JTextField();
		tfBDG.setColumns(10);
		tfBDG.setBounds(150, 35, 128, 20);
		panel_2.add(tfBDG);
		
		JLabel lblIdBdg = new JLabel("ID BDG");
		lblIdBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdBdg.setBounds(10, 35, 130, 20);
		panel_2.add(lblIdBdg);
		
		JLabel lblFechaDevolucionFabrica = new JLabel("Fecha Devolucion Fabrica");
		lblFechaDevolucionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDevolucionFabrica.setBounds(10, 109, 130, 20);
		panel_2.add(lblFechaDevolucionFabrica);
		
		JDateChooser dcFEF = new JDateChooser();
		dcFEF.setBounds(150, 109, 128, 20);
		panel_2.add(dcFEF);
		
		JLabel lblNumeroRemito = new JLabel("Numero Remito");
		lblNumeroRemito.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroRemito.setBounds(10, 134, 130, 20);
		panel_2.add(lblNumeroRemito);
		
		tfRemito = new JTextField();
		tfRemito.setColumns(10);
		tfRemito.setBounds(150, 134, 128, 20);
		panel_2.add(tfRemito);
		
		JLabel lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setBounds(10, 10, 130, 20);
		panel_2.add(lblNumeroOrden);
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfOrden = new JTextField();
		tfOrden.setBounds(150, 10, 128, 20);
		panel_2.add(tfOrden);
		tfOrden.setColumns(10);
		


	}
	
	
	private void cargarDatos() {
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		//
		nombreColumnas.add("ID Pedido");
		nombreColumnas.add("Numero Pedido");
		nombreColumnas.add("Fecha Solicitud Pedido");
		nombreColumnas.add("Estado Pedido");
		nombreColumnas.add("Fecha Turno");
		nombreColumnas.add("Nombre Titular");
		nombreColumnas.add("Dominio");
		nombreColumnas.add("VIN");
		nombreColumnas.add("Numero Pieza");
		//
		nombreColumnas.add("ID Reclamo");
		nombreColumnas.add("Fecha Reclamo");
		nombreColumnas.add("Estado Reclamo");
		nombreColumnas.add("Nombre Agente");
		nombreColumnas.add("Nombre Reclamante");
		nombreColumnas.add("PNC");
		nombreColumnas.add("ID Muleto");
		nombreColumnas.add("ID Mano Obra");
		//
		nombreColumnas.add("Numero Orden");
		nombreColumnas.add("ID BDG");
		nombreColumnas.add("Fecha Solicitud Fabrica");
		nombreColumnas.add("Fecha Recepcion Fabrica");
		nombreColumnas.add("Fecha Devolucion Fabrica");
		nombreColumnas.add("Numero Remito");
		nombreColumnas.add("Fecha Envio Agente");
		nombreColumnas.add("Fecha Recepcion Agente");
		//Pieza


		//Oden
		
		datosTabla = new Vector<Vector<String>>();
		Vector<PedidoDTO> pedidos = mediador.obtenerPedidos();
		for (int i=0; i< pedidos.size();i++){
			Vector<String> row = new Vector<String> ();
			if (pedidos.elementAt(i).getReclamo()!=null && pedidos.elementAt(i).getReclamo().getRegistrante()!=null && mediador.esAgente(pedidos.elementAt(i).getReclamo().getRegistrante())){
				Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
				pedidos_piezas = mediador.buscarPedidoPieza(pedidos.elementAt(i).getId());
				for (int j=0; j< pedidos_piezas.size();j++){
					
					row.add(pedidos.elementAt(i).getId().toString());//ID Pedido
					row.add(pedidos.elementAt(i).getNumero_pedido());//Numero Pedido
					row.add(pedidos.elementAt(i).getFecha_solicitud_pedido().toString());//Fecha Solicitud Pedido
					row.add(pedidos_piezas.elementAt(j).getEstado_pedido());//Estado Pedido
					if (pedidos.elementAt(i).getReclamo().getFecha_turno()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getFecha_turno().toString());
					}else{
						row.add("");
					}
					if (pedidos.elementAt(i).getReclamo().getVehiculo()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getNombre_titular());
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getDominio());
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getVin());
					}else{
						row.add("");
						row.add("");
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getPieza()!=null){
						row.add(pedidos_piezas.elementAt(j).getPieza().getNumero_pieza());
					}else{
						row.add("");
					}
					row.add(pedidos.elementAt(i).getReclamo().getId().toString());
					row.add(pedidos.elementAt(i).getReclamo().getFecha_reclamo().toString());
					row.add(pedidos.elementAt(i).getReclamo().getEstado_reclamo());
					row.add(pedidos.elementAt(i).getReclamo().getRegistrante().getNombre_registrante());
					row.add(pedidos.elementAt(i).getReclamo().getReclamante().getNombre_apellido());
					
					row.add(pedidos_piezas.elementAt(j).getPnc());
					if (pedidos_piezas.elementAt(j).getMuleto()!=null){
						row.add(pedidos_piezas.elementAt(j).getMuleto().getId().toString());
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getMano_obra()!=null){
						row.add(pedidos_piezas.elementAt(j).getMano_obra().getId().toString());
					}else{
						row.add("");
					}
					if (pedidos.elementAt(i).getReclamo().getOrden()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getOrden().getId().toString());
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getBdg()!=null){
						row.add(pedidos_piezas.elementAt(j).getBdg().getId().toString());
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica()!=null){
						row.add(pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica().toString());
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica()!=null){
						row.add(pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica().toString());
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion()!=null){
						row.add(pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion().toString());
						row.add(pedidos_piezas.elementAt(j).getDevolucion_pieza().getNumero_remito());
					}else{
						row.add("");
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_envio_agente()!=null){
						row.add(pedidos_piezas.elementAt(j).getFecha_envio_agente().toString());
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_recepcion_agente()!=null){
						row.add(pedidos_piezas.elementAt(j).getFecha_recepcion_agente().toString());
					}else{
						row.add("");
					}
					datosTabla.add(row);
				}
			}
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		tablaPedidos = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false,
				false, false, false, false, false,
				false, false, false, false, false,
				false, false, false, false, false,
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		
	}
}
