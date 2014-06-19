package cliente.GestionarDevoluviones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.Pedido_PiezaDTO;

public class GUIGestionDevoluciones extends JFrame {

	private static final long serialVersionUID = 1L;

	protected MediadorDevoluciones mediador;
	
	private JPanel contentPane;
	
	private JTextField tFRemito;
	private JTextField tFRetiro;
	private JTextField tFTransporte;
	private JTextField tfPedido;
	private JTextField tfPieza;
	private JDateChooser fecha_devolucion;
	
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;
	
	//Tabla
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaDevoluciones;
	private DefaultTableModel modelo;
	
	public GUIGestionDevoluciones(MediadorDevoluciones mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();

	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR DEVOLUCIONES");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfPedido = new JTextField();
		tfPedido.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorPedido();
			}
		});
		tfPedido.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub				
			}
		});
		tfPedido.setBounds(185, 76, 230, 20);
		contentPane.add(tfPedido);
		tfPedido.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(564, 69, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btnEliminar.setBounds(564, 103, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lblPedido = new JLabel("Numero Pedido");
		lblPedido.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setBounds(10, 76, 170, 20);
		contentPane.add(lblPedido);
		
		JLabel lblemail = new JLabel("Numero Remito");
		lblemail.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblemail.setHorizontalAlignment(SwingConstants.CENTER);
		lblemail.setBounds(10, 101, 170, 20);
		contentPane.add(lblemail);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaDevoluciones = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaDevoluciones.setRowSorter(ordenador);
		//
		tablaDevoluciones.getTableHeader().setReorderingAllowed(false);
		tablaDevoluciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaDevoluciones.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaDevoluciones);
		scrollPaneTabla.setBounds(5, 182, 779, 318);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaDevolucion(tfPedido.getText(),tFRemito.getText(), tFTransporte.getText());
			}
		});
		btnAgregar.setBounds(564, 34, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablaDevoluciones.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImprimir.setBounds(194, 527, 150, 23);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(414, 527, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(564, 137, 220, 23);
		contentPane.add(btnActualizar);
		
		JLabel lblRetiro = new JLabel("Numero Retiro");
		lblRetiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRetiro.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblRetiro.setBounds(10, 151, 170, 20);
		contentPane.add(lblRetiro);
		
		tFRemito = new JTextField();
		tFRemito.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorRemito();
			}
		});
		tFRemito.setColumns(10);
		tFRemito.setBounds(185, 101, 230, 20);
		contentPane.add(tFRemito);
		
		tFRetiro = new JTextField();
		tFRetiro.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorRetiro();
			}
		});
		tFRetiro.setColumns(10);
		tFRetiro.setBounds(185, 151, 230, 20);
		contentPane.add(tFRetiro);
		
		JLabel lblTrasnporte = new JLabel("Transporte");
		lblTrasnporte.setHorizontalAlignment(SwingConstants.CENTER);
		lblTrasnporte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblTrasnporte.setBounds(10, 126, 170, 20);
		contentPane.add(lblTrasnporte);
		
		tFTransporte = new JTextField();
		tFTransporte.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorTrasnporte();
			}
		});
		tFTransporte.setColumns(10);
		tFTransporte.setBounds(185, 126, 230, 20);
		contentPane.add(tFTransporte);
		
		JLabel lblPieza = new JLabel("Numero Pieza");
		lblPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblPieza.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblPieza.setBounds(10, 51, 170, 20);
		contentPane.add(lblPieza);
		
		tfPieza = new JTextField();
		tfPieza.setColumns(10);
		tfPieza.setBounds(185, 51, 230, 20);
		contentPane.add(tfPieza);
		
		fecha_devolucion = new JDateChooser();
		fecha_devolucion.setBounds(185, 26, 140, 20);
		contentPane.add(fecha_devolucion);
		
		JLabel lblFechaDevolucion = new JLabel("Fecha Devolucion");
		lblFechaDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDevolucion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblFechaDevolucion.setBounds(10, 26, 170, 20);
		contentPane.add(lblFechaDevolucion);
		
	}
	
	protected void filtrarPorRetiro() {
		// TODO Auto-generated method stub
		
	}

	// Gestion
	protected void modificar() {
		int row = tablaDevoluciones.getSelectedRow();
		if (row >= 0) {
			int aux = tablaDevoluciones.convertRowIndexToModel(row);
			Long id = new Long (tablaDevoluciones.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Devolucion [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				mediador.modificarDevolucion(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione una devolucion primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	protected void eliminar() {
		int row = tablaDevoluciones.getSelectedRow();
		if (row >= 0) {
			int aux = tablaDevoluciones.convertRowIndexToModel(row);
			Long id = new Long (tablaDevoluciones.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Devolucion [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				mediador.eliminarDevolucion(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione una devolucion primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Filtros
	protected void filtrarPorPedido() {
		String filtro = tfPedido.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> reclamantes = datosTabla;
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> reclamante = reclamantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamante.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(reclamante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	protected void filtrarPorTrasnporte() {
		String filtro = tFTransporte.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> reclamantes = datosTabla;
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> reclamante = reclamantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamante.elementAt(2).toLowerCase());
			if (mat.find()) {
				datos.add(reclamante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	protected void filtrarPorRemito() {
		String filtro = tFRemito.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> reclamantes = datosTabla;
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> reclamante = reclamantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamante.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(reclamante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}

	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Devolucion");
		nombreColumnas.add("Numero Pedido");
		nombreColumnas.add("Numero Pieza");
		nombreColumnas.add("Fecha Devolucion");
		nombreColumnas.add("Numero Remito");
		nombreColumnas.add("Transporte");
		nombreColumnas.add("Numero Retiro");

		datosTabla = new Vector<Vector<String>>();
		Vector<Devolucion_PiezaDTO> reclamantes = mediador.obtenerDevoluciones();
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			Pedido_PiezaDTO pedido_pieza = mediador.obtenerPedido_Pieza(reclamantes.elementAt(i).getId());
			row.add(reclamantes.elementAt(i).getId().toString());
			if (pedido_pieza!=null){
				if (pedido_pieza.getPedido()!=null){
					row.add(pedido_pieza.getPedido().getNumero_pedido());
				}else{
					row.add("SIN PEDIDO");
				}
				if (pedido_pieza.getPieza()!=null){
					row.add(pedido_pieza.getPieza().getNumero_pieza());
				}else{
					row.add("SIN PIEZA");
				}
			}else{
				row.add("SIN PEDIDO");
				row.add("SIN PIEZA");
			}
			row.add(reclamantes.elementAt(i).getFecha_devolucion().toString());
			row.add(reclamantes.elementAt(i).getNumero_remito());
			row.add(reclamantes.elementAt(i).getTransporte());
			row.add(reclamantes.elementAt(i).getNumero_retiro());

			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tablaDevoluciones = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	}
	public void actualizarPantalla(){ 
		modelo.fireTableStructureChanged();
	}
	
	public void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		Vector<Devolucion_PiezaDTO> reclamantes = mediador.obtenerDevoluciones();
		for (int i=0; i< reclamantes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			Pedido_PiezaDTO pedido_pieza = mediador.obtenerPedido_Pieza(reclamantes.elementAt(i).getId());
			row.add(reclamantes.elementAt(i).getId().toString());
			if (pedido_pieza!=null){
				if (pedido_pieza.getPedido()!=null){
					row.add(pedido_pieza.getPedido().getNumero_pedido());
				}else{
					row.add("SIN PEDIDO");
				}
				if (pedido_pieza.getPieza()!=null){
					row.add(pedido_pieza.getPieza().getNumero_pieza());
				}else{
					row.add("SIN PIEZA");
				}
			}else{
				row.add("SIN PEDIDO");
				row.add("SIN PIEZA");
			}
			row.add(reclamantes.elementAt(i).getFecha_devolucion().toString());
			row.add(reclamantes.elementAt(i).getNumero_remito());
			row.add(reclamantes.elementAt(i).getTransporte());
			row.add(reclamantes.elementAt(i).getNumero_retiro());
			
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		tfPedido.setText("");
		tFTransporte.setText("");
		tFRetiro.setText("");
		tFRemito.setText("");
		tfPieza.setText("");
		fecha_devolucion.setDate(null);
	}
}
