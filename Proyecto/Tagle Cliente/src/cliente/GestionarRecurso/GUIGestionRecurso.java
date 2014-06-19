package cliente.GestionarRecurso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
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
import common.DTOs.RecursoDTO;

public class GUIGestionRecurso extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorRecurso mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnumero_orden;
	private JDateChooser dcFapertura;
	
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar;

	//Tabla
	private DefaultTableModel modelo;
	private JTable tablaRecursos;
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas; 
	
	public GUIGestionRecurso(MediadorRecurso mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR RECURSOS");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tFnumero_orden = new JTextField();
		tFnumero_orden.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNumeroOrden();
			}
		});
		tFnumero_orden.setBounds(180, 11, 230, 20);
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
		
		JLabel lbl_numero_recurso = new JLabel("Numero Recurso");
		lbl_numero_recurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_numero_recurso.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numero_recurso.setBounds(10, 11, 165, 20);
		contentPane.add(lbl_numero_recurso);
		
		JLabel lbl_frecurso = new JLabel("Fecha Recurso");
		lbl_frecurso.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		lbl_frecurso.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_frecurso.setBounds(10, 36, 165, 20);
		contentPane.add(lbl_frecurso);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaRecursos = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaRecursos.setRowSorter(ordenador);
		//
		tablaRecursos.getTableHeader().setReorderingAllowed(false);
		tablaRecursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRecursos.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaRecursos);

		scrollPaneTabla.setBounds(10, 182, 774, 318);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(dcFapertura.getDate()!=null){
					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					String fecha = format2.format(dcFapertura.getDate());
					java.sql.Date fechaRecurso = new java.sql.Date(dcFapertura.getDate().getTime());
					mediador.altaRecurso(tFnumero_orden.getText(),fechaRecurso);
				}else{
					mediador.altaRecurso(tFnumero_orden.getText(),null);
				}
			}
		});
		btnAgregar.setBounds(488, 27, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaRecursos.print();
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
		
		dcFapertura = new JDateChooser();
		dcFapertura.setBounds(180, 36, 160, 20);
		contentPane.add(dcFapertura);
	}
	//Gestion
	public void modificar(){
		int row = tablaRecursos.getSelectedRow();
		if (row >= 0) {
			int aux = tablaRecursos.convertRowIndexToModel(row);
			Long id = new Long (tablaRecursos.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Recurso [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				mediador.modificarRecurso(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Recurso primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void eliminar(){
		int row = tablaRecursos.getSelectedRow();
		if (row >= 0) {
			int aux = tablaRecursos.convertRowIndexToModel(row);
			Long id = new Long (tablaRecursos.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Recurso [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarRecurso(id)){
					JOptionPane.showMessageDialog(contentPane,"Recurso eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Recurso primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// Filtros //
	public void filtrarPorNumeroOrden(){
		String filtro = tFnumero_orden.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>recursos = datosTabla;
		for (int i=0; i< recursos.size();i++){
			Vector<String> recurso = recursos.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(recurso.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(recurso);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();
		
		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Orden");
		nombreColumnas.add("Numero Recurso");
		nombreColumnas.add("Fecha Orden");
		
		//nombreColumnas.add("Numero Pedido");
		//nombreColumnas.add("Numero Pieza");
		
		datosTabla = new Vector<Vector<String>>();
		Vector<RecursoDTO> recursos = mediador.obtenerRecursos();
		for (int i=0; i< recursos.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(recursos.elementAt(i).getId().toString());
			row.add(recursos.elementAt(i).getNumero_recurso());
			row.add(recursos.elementAt(i).getFecha_recurso().toString());
			//row.add(recursos.elementAt(i).getPedido_pieza().getPedido().getNumero_pedido());
			//row.add(recursos.elementAt(i).getPedido_pieza().getPieza().getNumero_pieza());
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		tablaRecursos = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
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
		Vector<RecursoDTO> recursos = mediador.obtenerRecursos();
		for (int i=0; i< recursos.size();i++){
			Vector<String> row = new Vector<String> ();
			
			row.add(recursos.elementAt(i).getId().toString());
			row.add(recursos.elementAt(i).getNumero_recurso());
			row.add(recursos.elementAt(i).getFecha_recurso().toString());
			//row.add(recursos.elementAt(i).getPedido_pieza().getPedido().getNumero_pedido());
			//row.add(recursos.elementAt(i).getPedido_pieza().getPieza().getNumero_pieza());
			datosTabla.add(row);
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		tFnumero_orden.setText("");
		dcFapertura.setDate(null);
	}

}
