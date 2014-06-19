package cliente.GestionOrden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import common.DTOs.OrdenDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RecursoDTO;

public class GUIGestionOrden extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorOrden mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnumero_orden;
	private JTextField tfReclamo;
	private JTextField tfRecurso;
	private JDateChooser dcFapertura;
	private JDateChooser dcFcierre;
	
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar;
	
	private JButton btnBuscarRecurso;
	private JButton btnBuscarReclamo;

	//Tabla
	private DefaultTableModel modelo;
	private JTable tablaOrdenes;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas; 
	//Combobox de Usuarios
	private JComboBox<String> cbEstadoOrden;
	private Vector<String> tiposEstados;
	
	private RecursoDTO recurso;
	private ReclamoDTO reclamo;
	
	public GUIGestionOrden(MediadorOrden mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR USUARIOS");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFnumero_orden = new JTextField();
		tFnumero_orden.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre();
			}
		});
		tFnumero_orden.setBounds(170, 15, 230, 20);
		tFnumero_orden.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnumero_orden.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					//buscar();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(tFnumero_orden);
		tFnumero_orden.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(488, 62, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnEliminar.setBounds(488, 96, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_numero_orden = new JLabel("Numero Orden");
		lbl_numero_orden.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_numero_orden.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numero_orden.setBounds(0, 15, 165, 20);
		contentPane.add(lbl_numero_orden);
		
		JLabel lbl_fapertura = new JLabel("Fecha Apertura");
		lbl_fapertura.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_fapertura.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_fapertura.setBounds(0, 40, 165, 20);
		contentPane.add(lbl_fapertura);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaOrdenes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaOrdenes.setRowSorter(ordenador);
		//
		tablaOrdenes.getTableHeader().setReorderingAllowed(false);
		tablaOrdenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaOrdenes.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaOrdenes);

		scrollPaneTabla.setBounds(10, 182, 774, 318);
		contentPane.add(scrollPaneTabla);

		JLabel lblEstado = new JLabel("Estado Orden");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblEstado.setBounds(0, 90, 165, 20);
		contentPane.add(lblEstado);
		
		cbEstadoOrden = new JComboBox<String>();
		cbEstadoOrden.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorTipo();
			}
		});
		cbEstadoOrden.setModel(new DefaultComboBoxModel<String>(tiposEstados));
		cbEstadoOrden.setBounds(170, 90, 160, 20);
		contentPane.add(cbEstadoOrden);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//mediador
			}
		});
		btnAgregar.setBounds(488, 27, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaOrdenes.print();
				} catch (PrinterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(237, 528, 150, 23);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(397, 528, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(488, 130, 220, 23);
		contentPane.add(btnActualizar);
		
		JLabel lblfcierrre = new JLabel("Fecha Cierre");
		lblfcierrre.setHorizontalAlignment(SwingConstants.CENTER);
		lblfcierrre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblfcierrre.setBounds(0, 65, 165, 20);
		contentPane.add(lblfcierrre);
		
		dcFapertura = new JDateChooser();
		dcFapertura.setBounds(170, 40, 160, 20);
		contentPane.add(dcFapertura);
		
		dcFcierre = new JDateChooser();
		dcFcierre.setBounds(170, 65, 160, 20);
		contentPane.add(dcFcierre);
		
		JLabel lblReclamo = new JLabel("Reclamo");
		lblReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblReclamo.setBounds(0, 115, 165, 20);
		contentPane.add(lblReclamo);
		
		tfReclamo = new JTextField();
		tfReclamo.setBounds(170, 115, 160, 20);
		contentPane.add(tfReclamo);
		tfReclamo.setColumns(10);
		
		JLabel lblRecurso = new JLabel("Recurso");
		lblRecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lblRecurso.setBounds(0, 140, 165, 20);
		contentPane.add(lblRecurso);
		
		tfRecurso = new JTextField();
		tfRecurso.setColumns(10);
		tfRecurso.setBounds(170, 140, 160, 20);
		contentPane.add(tfRecurso);
		
		btnBuscarReclamo = new JButton("Buscar");
		btnBuscarReclamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.buscarReclamos();
			}
		});
		btnBuscarReclamo.setBounds(336, 113, 64, 22);
		contentPane.add(btnBuscarReclamo);
		
		btnBuscarRecurso = new JButton("Buscar");
		btnBuscarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarRecursos();
			}
		});
		btnBuscarRecurso.setBounds(336, 138, 64, 22);
		contentPane.add(btnBuscarRecurso);
	}
	//Gestion
	public void modificar(){
		int row = tablaOrdenes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaOrdenes.convertRowIndexToModel(row);
			Long id = new Long (tablaOrdenes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Orden [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				mediador.modificarOrden(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Orden primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void eliminar(){
		int row = tablaOrdenes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaOrdenes.convertRowIndexToModel(row);
			Long id = new Long (tablaOrdenes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Orden [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarOrden(id)){
					JOptionPane.showMessageDialog(contentPane,"Orden eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Orden primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Filtros //
	public void filtrarPorNombre(){
		String filtro = tFnumero_orden.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	public void filtrarPorTipo(){
		String filtro = cbEstadoOrden.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>usuarios = datosTabla;
		for (int i=0; i< usuarios.size();i++){
			Vector<String> usuario = usuarios.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		tiposEstados = new Vector<String>();
		tiposEstados.add("");
		tiposEstados.add("SIN RECLAMO");
		tiposEstados.add("SIN PEDIDO");
		tiposEstados.add("ABIERTA/SIN RECURSO");
		tiposEstados.add("ABIERTA/CON RECURSO");
		tiposEstados.add("CERRADA");
		
		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Orden");
		nombreColumnas.add("Numero Orden");
		nombreColumnas.add("ID Reclmamo");
		nombreColumnas.add("Fecha Apertura");
		nombreColumnas.add("Fecha Cierre");
		nombreColumnas.add("Estado Orden");
		nombreColumnas.add("Numero Recurso");
		
		datosTabla = new Vector<Vector<String>>();
		Vector<OrdenDTO> ordenes = mediador.obtenerOrdenes();
		for (int i=0; i< ordenes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(ordenes.elementAt(i).getId().toString());
			row.add(ordenes.elementAt(i).getNumero_orden());
			ReclamoDTO reclamo = mediador.obtenerReclamo(ordenes.elementAt(i).getId());
			if (reclamo !=null){
				row.add(reclamo.getId().toString());
			}else{
				row.add("SIN RECLAMO");
			}
			row.add(ordenes.elementAt(i).getFecha_apertura().toString());
			if (ordenes.elementAt(i).getFecha_cierre()!=null){
				row.add(ordenes.elementAt(i).getFecha_cierre().toString());
			}else{
				row.add("SIN FECHA");
			}
			row.add(ordenes.elementAt(i).getEstado());
			if (ordenes.elementAt(i).getRecurso() !=null){
				row.add(ordenes.elementAt(i).getRecurso().getNumero_recurso());
			}else{
				row.add("SIN RECURSO");
			}
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tablaOrdenes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
	}
	public void actualizarPantalla(){ 
		modelo.fireTableStructureChanged();
	}
	public void actualizarDatos(){
		datosTabla = new Vector<Vector<String>>();
		Vector<OrdenDTO> ordenes = mediador.obtenerOrdenes();
		for (int i=0; i< ordenes.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(ordenes.elementAt(i).getId().toString());
			row.add(ordenes.elementAt(i).getNumero_orden());
			ReclamoDTO reclamo = mediador.obtenerReclamo(ordenes.elementAt(i).getId());
			if (reclamo !=null){
				row.add(reclamo.getId().toString());
			}else{
				row.add("SIN RECLAMO");
			}
			row.add(ordenes.elementAt(i).getFecha_apertura().toString());
			if (ordenes.elementAt(i).getFecha_cierre()!=null){
				row.add(ordenes.elementAt(i).getFecha_cierre().toString());
			}else{
				row.add("SIN FECHA");
			}
			row.add(ordenes.elementAt(i).getEstado());
			if (ordenes.elementAt(i).getRecurso() !=null){
				row.add(ordenes.elementAt(i).getRecurso().getNumero_recurso());
			}else{
				row.add("SIN RECURSO");
			}
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		tFnumero_orden.setText("");
		
		cbEstadoOrden.setSelectedIndex(0);
	}

	public void setRecurso(RecursoDTO recurso) {
		this.recurso = recurso;
		tfRecurso.setText(recurso.getNumero_recurso());
	}
	public void setReclamo(ReclamoDTO reclamo) {
		this.reclamo = reclamo;
		tfReclamo.setText(reclamo.getId().toString());
	}
}
