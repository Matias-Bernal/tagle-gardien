package cliente.Buscador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;
import cliente.utils.JPanel_Whit_Image;
import cliente.utils.TransparentPanel;

import com.toedter.calendar.JDateChooser;

import comun.DTOs.SolicitudDTO;

import javax.swing.border.MatteBorder;

public class GUIBuscador extends JFrame {

	private static final long serialVersionUID = 1L;
	protected MediadorBuscador mediador;
	private JPanel contentPane;
	
	private JTable tablaBuscador;
	private DefaultTableModel modeloTabla;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	
	private JPanel panelSolicitud;
	private JDateChooser dCFSSolicitante;
	private JDateChooser dCFSProveedor;
	private JTextField tFNumPieza;
	private JTextField tFProveedor;
	private JTextField tFSolicitante;
	private JTextField tFCantidad;
	private JButton btnDesbloquear;
	private JDateChooser dCFELlegada;
	private JDateChooser dCFLlegada;
	private JButton btnGuardar;
	private JButton btnReclamo;
	private JCheckBox chckbxBloqueado;
	private JRadioButton rdbtnStockFabrica;
	private JRadioButton rdbtnStockPropio;
	private Vector<Integer> anchosColumnas;
	
	private SolicitudDTO SolicitudActual;
	private JTextArea tADescripcionSolicitud;
	private JButton btnActualizarTabla;
	private JButton btnImprimir;
	private JButton btnExportar;
	private JButton btnCancelar;

	public GUIBuscador(MediadorBuscador mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}


	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/find_reclamo.png")));
		setTitle("BUSCADOR");
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1100, 570);
		contentPane.setLayout(null);
		
		JPanel panelTablaBuscador = new TransparentPanel();
		panelTablaBuscador.setBackground(Color.WHITE);
		panelTablaBuscador.setLayout(new BorderLayout(0, 0));
		panelTablaBuscador.setBounds(10, 50, 612, 470);
		panelTablaBuscador.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelTablaBuscador);
		
		
		modeloTabla = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaBuscador = new JTable(modeloTabla) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {false,false,false,false,false,false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		
		tablaBuscador.setBackground(UIManager.getColor("window"));
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modeloTabla);
		tablaBuscador.setRowSorter(ordenador);
		tablaBuscador.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaBuscador.getColumnCount(); i++) {
			tablaBuscador.getColumnModel().getColumn(i).setPreferredWidth(anchosColumnas.elementAt(i));
			tablaBuscador.getColumnModel().getColumn(i).setMinWidth(anchosColumnas.elementAt(i));
		}		
		
		FormatoTablaDiasDesdeFELlegada fr = new FormatoTablaDiasDesdeFELlegada(2);
		tablaBuscador.setDefaultRenderer (Object.class, fr );	
		
		tablaBuscador.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verSolicitud();
			    else{
			    	e.consume();
			    }   
			}
		});
				
		tablaBuscador.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaBuscador.setBounds(0, 0, 530, 470);
		tablaBuscador.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaBuscador);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panelTablaBuscador.add(scrollPaneTabla);
		
		JLabel lblTablaDeSolicitudes = new JLabel("TABLA DE SOLICITUDES");
		lblTablaDeSolicitudes.setHorizontalAlignment(SwingConstants.CENTER);
		lblTablaDeSolicitudes.setBounds(10, 10, 612, 30);
		contentPane.add(lblTablaDeSolicitudes);
		
		panelSolicitud = new TransparentPanel();
		panelSolicitud.setForeground(Color.BLACK);
		panelSolicitud.setBounds(632, 50, 445, 470);
		contentPane.add(panelSolicitud);
		panelSolicitud.setLayout(null);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(10, 100, 110, 20);
		panelSolicitud.add(lblDescripcion);
		
		tADescripcionSolicitud = new JTextArea();
		tADescripcionSolicitud.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tADescripcionSolicitud.setDisabledTextColor(Color.BLACK);
		tADescripcionSolicitud.setEnabled(false);
		tADescripcionSolicitud.setEditable(false);
		tADescripcionSolicitud.setBounds(130, 100, 300, 100);
		panelSolicitud.add(tADescripcionSolicitud);
		
		JLabel lblFechaEstimadaLlegada = new JLabel("FECHA ESTIMADA LLEGADA");
		lblFechaEstimadaLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEstimadaLlegada.setBounds(10, 360, 200, 20);
		panelSolicitud.add(lblFechaEstimadaLlegada);
		
		JLabel lblFechaSolicitudProveedor = new JLabel("FECHA SOLICITUD PROVEEDOR");
		lblFechaSolicitudProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudProveedor.setBounds(10, 300, 200, 20);
		panelSolicitud.add(lblFechaSolicitudProveedor);
		
		JLabel lblFechaSolicitudSolicitante = new JLabel("FECHA SOLICITUD SOLICITANTE");
		lblFechaSolicitudSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudSolicitante.setBounds(10, 331, 200, 20);
		panelSolicitud.add(lblFechaSolicitudSolicitante);
		
		dCFSSolicitante = new JDateChooser();
		dCFSSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSSolicitante.getCalendarButton().setEnabled(false);
		dCFSSolicitante.setBounds(220, 330, 175, 20);
		panelSolicitud.add(dCFSSolicitante);
		
		dCFSProveedor = new JDateChooser();
		dCFSProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFSProveedor.getCalendarButton().setEnabled(false);
		dCFSProveedor.setBounds(220, 300, 175, 20);
		panelSolicitud.add(dCFSProveedor);
		
		dCFELlegada = new JDateChooser();
		dCFELlegada.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFELlegada.getCalendarButton().setEnabled(false);
		dCFELlegada.setBounds(220, 360, 175, 20);
		panelSolicitud.add(dCFELlegada);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/save.png")));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarSolicitud();
			}
		});
		btnGuardar.setBounds(310, 435, 125, 20);
		panelSolicitud.add(btnGuardar);
		
		btnReclamo = new JButton("RECLAMO");
		btnReclamo.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/exclamation-red.png")));
		btnReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reclamo();
			}
		});
		btnReclamo.setBounds(10, 435, 125, 20);
		panelSolicitud.add(btnReclamo);
		
		JLabel lblFechaLlegada = new JLabel("FECHA  LLEGADA");
		lblFechaLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaLlegada.setBounds(10, 390, 200, 20);
		panelSolicitud.add(lblFechaLlegada);
		
		dCFLlegada = new JDateChooser();
		dCFLlegada.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		dCFLlegada.setBounds(220, 390, 175, 20);
		panelSolicitud.add(dCFLlegada);
		
		JLabel lblNumeroPieza = new JLabel("NUMERO PIEZA");
		lblNumeroPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPieza.setBounds(10, 10, 110, 20);
		panelSolicitud.add(lblNumeroPieza);
		
		tFNumPieza = new JTextField();
		tFNumPieza.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFNumPieza.setDisabledTextColor(Color.BLACK);
		tFNumPieza.setEditable(false);
		tFNumPieza.setBounds(130, 10, 300, 20);
		panelSolicitud.add(tFNumPieza);
		tFNumPieza.setColumns(10);
		
		JLabel lblProveedor = new JLabel("PROVEEDOR");
		lblProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProveedor.setBounds(10, 40, 110, 20);
		panelSolicitud.add(lblProveedor);
		
		tFProveedor = new JTextField();
		tFProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFProveedor.setDisabledTextColor(Color.BLACK);
		tFProveedor.setEditable(false);
		tFProveedor.setColumns(10);
		tFProveedor.setBounds(130, 40, 300, 20);
		panelSolicitud.add(tFProveedor);
		
		JLabel lblSolicitante = new JLabel("SOLICITANTE");
		lblSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitante.setBounds(10, 70, 110, 20);
		panelSolicitud.add(lblSolicitante);
		
		tFSolicitante = new JTextField();
		tFSolicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFSolicitante.setDisabledTextColor(Color.BLACK);
		tFSolicitante.setEditable(false);
		tFSolicitante.setColumns(10);
		tFSolicitante.setBounds(130, 70, 300, 20);
		panelSolicitud.add(tFSolicitante);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(10, 210, 110, 20);
		panelSolicitud.add(lblCantidad);
		
		tFCantidad = new JTextField();
		tFCantidad.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFCantidad.setEnabled(false);
		tFCantidad.setForeground(Color.BLACK);
		tFCantidad.setDisabledTextColor(Color.BLACK);
		tFCantidad.setEditable(false);
		tFCantidad.setBounds(130, 210, 150, 20);
		panelSolicitud.add(tFCantidad);
		tFCantidad.setColumns(10);
		
		chckbxBloqueado = new JCheckBox("BLOQUEADO");
		chckbxBloqueado.setToolTipText("Pieza bloqueada en Fabrica");
		chckbxBloqueado.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxBloqueado.setContentAreaFilled(false);
		chckbxBloqueado.setBorder(null);
		chckbxBloqueado.setForeground(Color.BLACK);
		chckbxBloqueado.setEnabled(false);
		chckbxBloqueado.setBounds(220, 270, 150, 20);
		panelSolicitud.add(chckbxBloqueado);
		
		rdbtnStockFabrica = new JRadioButton("STOCK FABRICA");
		rdbtnStockFabrica.setToolTipText("Hay Stock en Fabrica");
		rdbtnStockFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnStockFabrica.setContentAreaFilled(false);
		rdbtnStockFabrica.setBorder(null);
		rdbtnStockFabrica.setForeground(Color.BLACK);
		rdbtnStockFabrica.setEnabled(false);
		rdbtnStockFabrica.setBounds(10, 270, 200, 20);
		panelSolicitud.add(rdbtnStockFabrica);
		
		rdbtnStockPropio = new JRadioButton("STOCK PROPIO");
		rdbtnStockPropio.setToolTipText("Hay Stock en Sucursal");
		rdbtnStockPropio.setEnabled(false);
		rdbtnStockPropio.setOpaque(false);
		rdbtnStockPropio.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnStockPropio.setContentAreaFilled(false);
		rdbtnStockPropio.setBorder(null);
		rdbtnStockPropio.setForeground(Color.BLACK);
		rdbtnStockPropio.setBounds(10, 240, 200, 20);
		panelSolicitud.add(rdbtnStockPropio);
		
		btnDesbloquear = new JButton("");
		btnDesbloquear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desbloquearCalendario();
			}
		});
		btnDesbloquear.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/unlock.png")));
		btnDesbloquear.setToolTipText("Desbloquear");
		btnDesbloquear.setBounds(405, 359, 30, 23);
		panelSolicitud.add(btnDesbloquear);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
		btnCancelar.setBounds(160, 435, 125, 20);
		panelSolicitud.add(btnCancelar);
		
		JLabel lblSolicitudSeleccionada = new JLabel("SOLICITUD SELECCIONADA");
		lblSolicitudSeleccionada.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitudSeleccionada.setBounds(632, 10, 448, 30);
		contentPane.add(lblSolicitudSeleccionada);
		
		btnActualizarTabla = new JButton("");
		btnActualizarTabla.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/1refresh.png")));
		btnActualizarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		btnActualizarTabla.setBounds(585, 25, 33, 20);
		contentPane.add(btnActualizarTabla);
		
		btnImprimir = new JButton("");
		btnImprimir.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaBuscador.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(545, 25, 33, 20);
		contentPane.add(btnImprimir);
		
		btnExportar = new JButton("");
		btnExportar.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/formulario.png")));
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTabla();
			}
		});
		btnExportar.setBounds(505, 25, 33, 20);
		contentPane.add(btnExportar);
		
		contentPane.setVisible(true);				
	}


	protected void exportarTabla() {
		try {
			ExportarExcel.exportarUnaTabla(tablaBuscador, "Solicitudes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}


	protected void reclamo() {
		if(SolicitudActual != null){
			mediador.reclamo(SolicitudActual.getId());
		}else{
			JOptionPane.showMessageDialog(getContentPane(),"Seleccione primero una Solicitud.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/error.png")));
		}
	}


	protected void guardarSolicitud() {
		if(SolicitudActual != null){
			if(dCFLlegada.getDate()!=null){
					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					String fecha_ = format2.format(dCFLlegada.getDate());
					java.sql.Date fLlegada = new java.sql.Date(dCFLlegada.getDate().getTime());
					if(mediador.cambiarFechaLlegada(SolicitudActual.getId(),fLlegada)){
						JOptionPane.showMessageDialog(getContentPane(),"Fecha Llegada Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
					}else{
						JOptionPane.showMessageDialog(getContentPane(),"Error al Modificar Fecha Llegada. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/error.png")));
					}
			}
			if(dCFELlegada.getDate()!=null){
				if(!dCFELlegada.getDate().equals(SolicitudActual.getFecha_recepcion_estimada())){
					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					String fecha = format2.format(dCFELlegada.getDate());
					java.sql.Date fELlegada = new java.sql.Date(dCFELlegada.getDate().getTime());
					if(mediador.cambiarFechaEstimadaLlegada(SolicitudActual.getId(),fELlegada)){
						JOptionPane.showMessageDialog(getContentPane(),"Fecha Estimada Llegada Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
					}else{
						JOptionPane.showMessageDialog(getContentPane(),"Error al Modificar Fecha Llegada. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/error.png")));				
					}
				}
			}else{
				JOptionPane.showMessageDialog(getContentPane(),"Falta la Fecha Llegada. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/error.png")));
			}
			actualizarTabla();
		}else{
			JOptionPane.showMessageDialog(getContentPane(),"Seleccione primero una Solicitud.","Error",JOptionPane.ERROR_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/error.png")));
		}
	}


	private void actualizarTabla() {
		datosTabla = new Vector<Vector<String>>();
		
		Vector<SolicitudDTO> solicitudes = mediador.obtenerSolicitudes();
		for (int i=0; i< solicitudes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(solicitudes.elementAt(i).getId().toString());
			
			if(solicitudes.elementAt(i).getFecha_recepcion_estimada()!=null){
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				java.sql.Date fre = new java.sql.Date(solicitudes.elementAt(i).getFecha_recepcion_estimada().getTime());
				row.add(format2.format(fre));//Fecha Recepcion Estimada
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				java.util.Date hoy = new Date(); //Fecha de hoy 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(fre.getYear(), fre.getMonth()-1, fre.getDay());
				long diferencia = ( fre.getTime() - hoy.getTime())/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
			}else{
				row.add("");
				row.add("");
			}
			
			
			if (solicitudes.elementAt(i).getPieza() !=null){
				row.add(solicitudes.elementAt(i).getPieza().getNumero_pieza());
			}else{
				row.add("");
			}
			if (solicitudes.elementAt(i).getProveedor() !=null){
				row.add(solicitudes.elementAt(i).getProveedor().getNombre());
			}else{
				row.add("");
			}
			if (solicitudes.elementAt(i).getSolicitante() !=null){
				row.add(solicitudes.elementAt(i).getSolicitante().getNombre());
			}else{
				row.add("");
			}
						
			datosTabla.add(row);
		};
		modeloTabla.setDataVector(datosTabla, nombreColumnas);
		modeloTabla.fireTableStructureChanged();
		
		for(int i = 0; i < tablaBuscador.getColumnCount(); i++) {
			tablaBuscador.getColumnModel().getColumn(i).setPreferredWidth(anchosColumnas.elementAt(i));
			tablaBuscador.getColumnModel().getColumn(i).setMinWidth(anchosColumnas.elementAt(i));
		}
		
		tFNumPieza.setText("");
		tFProveedor.setText("");
		tFSolicitante.setText("");
		tADescripcionSolicitud.setText("");
		tFCantidad.setText("");
		rdbtnStockPropio.setSelected(false);
		rdbtnStockFabrica.setSelected(false);
		chckbxBloqueado.setSelected(false);
		dCFELlegada.setDate(null);//Fecha Recepcion Estimada
		dCFSSolicitante.setDate(null);//Fecha Solicitud Solicitante
		dCFSProveedor.setDate(null);//Fecha Solicitud Proveedor
		dCFLlegada.setDate(null);//Fecha Recepcion Proveedor
		dCFELlegada.getCalendarButton().setEnabled(false);
		btnDesbloquear.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/unlock.png")));
		btnDesbloquear.setToolTipText("Desbloquear");
		
		SolicitudActual = null;
		
	}


	protected void desbloquearCalendario() {
		if(!dCFELlegada.getCalendarButton().isEnabled()){
			dCFELlegada.getCalendarButton().setEnabled(true);
			btnDesbloquear.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/lock.png")));
			btnDesbloquear.setToolTipText("Bloquear");
		}else{
			dCFELlegada.getCalendarButton().setEnabled(false);
			btnDesbloquear.setIcon(new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/unlock.png")));
			btnDesbloquear.setToolTipText("Desbloquear");
		}
	}


	protected void verSolicitud() {
		int row = tablaBuscador.getSelectedRow();
		if (row >= 0) {
			int aux = tablaBuscador.convertRowIndexToModel(row);
			Long id = new Long (tablaBuscador.getValueAt(aux, 0).toString());
				SolicitudActual = mediador.buscarSolicitud(id);
				if(SolicitudActual!=null)
					actualizarDatos();
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione una Solicitud primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIBuscador.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
		
	}


	private void actualizarDatos() {
		tFNumPieza.setText(SolicitudActual.getPieza().getNumero_pieza());
		tFProveedor.setText(SolicitudActual.getProveedor().getNombre());
		tFSolicitante.setText(SolicitudActual.getSolicitante().getNombre());
		tADescripcionSolicitud.setText(SolicitudActual.getPieza().getDescripcion());
		tFCantidad.setText(String.valueOf(SolicitudActual.getCantidad()));
		rdbtnStockPropio.setSelected(SolicitudActual.isStock_propio());
		rdbtnStockFabrica.setSelected(SolicitudActual.isStock_fabrica());
		chckbxBloqueado.setSelected(SolicitudActual.isBloqueada());
		
		if(SolicitudActual.getFecha_recepcion_estimada()!=null){
			dCFELlegada.setDate(SolicitudActual.getFecha_recepcion_estimada());//Fecha Recepcion Estimada
		}
		if(SolicitudActual.getFecha_solicitud_solicitante()!=null){
			dCFSSolicitante.setDate(SolicitudActual.getFecha_solicitud_solicitante());//Fecha Solicitud Solicitante
		}
		if(SolicitudActual.getFecha_solicitud_proveedor()!=null){
			dCFSProveedor.setDate(SolicitudActual.getFecha_solicitud_proveedor());//Fecha Solicitud Proveedor
		}	
		
	}


	private void cargarDatos() {
		
		modeloTabla = new DefaultTableModel();
		nombreColumnas = new Vector<String>();
		anchosColumnas = new Vector<Integer>();
		
		nombreColumnas.add("ID");
		anchosColumnas.add(35);
		nombreColumnas.add("FECHA ESTIMADA");
		anchosColumnas.add(125);
		nombreColumnas.add("DIAS");
		anchosColumnas.add(35);
		nombreColumnas.add("NUMERO PIEZA");
		anchosColumnas.add(110);
		nombreColumnas.add("PROVEEDOR");
		anchosColumnas.add(180);
		nombreColumnas.add("SOLICITANTE");
		anchosColumnas.add(200);
		
		datosTabla = new Vector<Vector<String>>();
		
		Vector<SolicitudDTO> solicitudes = mediador.obtenerSolicitudes();
		for (int i=0; i< solicitudes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(solicitudes.elementAt(i).getId().toString());
			
			if(solicitudes.elementAt(i).getFecha_recepcion_estimada()!=null){
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				java.sql.Date fre = new java.sql.Date(solicitudes.elementAt(i).getFecha_recepcion_estimada().getTime());
				row.add(format2.format(fre));//Fecha Recepcion Estimada
				final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000; //Milisegundos al día 
				java.util.Date hoy = new Date(); //Fecha de hoy 
				@SuppressWarnings("deprecation")
				Calendar calendar = new GregorianCalendar(fre.getYear(), fre.getMonth()-1, fre.getDay());
				long diferencia = ( fre.getTime() - hoy.getTime())/MILLSECS_PER_DAY;
				row.add(String.valueOf(diferencia));
			}else{
				row.add("");
				row.add("");
			}
			
			
			if (solicitudes.elementAt(i).getPieza() !=null){
				row.add(solicitudes.elementAt(i).getPieza().getNumero_pieza());
			}else{
				row.add("");
			}
			if (solicitudes.elementAt(i).getProveedor() !=null){
				row.add(solicitudes.elementAt(i).getProveedor().getNombre());
			}else{
				row.add("");
			}
			if (solicitudes.elementAt(i).getSolicitante() !=null){
				row.add(solicitudes.elementAt(i).getSolicitante().getNombre());
			}else{
				row.add("");
			}
						
			datosTabla.add(row);
		};
		modeloTabla.setDataVector(datosTabla, nombreColumnas);
		modeloTabla.fireTableStructureChanged();
		
	}
}
