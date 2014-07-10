package cliente.GestionarProveedor;

import java.awt.Color;
import java.awt.Toolkit;
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
import javax.swing.ImageIcon;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;
import cliente.utils.JPanel_Whit_Image;

import comun.DTOs.AlternativoDTO;
import comun.DTOs.FabricaDTO;
import comun.DTOs.ProveedorDTO;
import comun.DTOs.SucursalDTO;

public class GUIGestionProveedor extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorProveedor mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnombre_proveedor;
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar; 
	
	//Tabla
	private DefaultTableModel modelo;
	private JTable tablaProveedores;
	private Vector<Vector<String>> datosTabla;
	private Vector<Integer> anchos;

	private Vector<String> nombreColumnas; 
	//Combobox de Usuarios
	private JComboBox<String> cBTProveedor;
	private Vector<String> tiposProveedor;	
	private JButton btnExportExcell;

	public GUIGestionProveedor(MediadorProveedor mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR PROVEEDORES");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/registrantes.png")));
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JLabel labelTipoProveedor = new JLabel("Tipo Proveedor");
		labelTipoProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		labelTipoProveedor.setBounds(10, 70, 172, 24);
		contentPane.add(labelTipoProveedor);
		
		
		tFnombre_proveedor = new JTextField();
		tFnombre_proveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_proveedor.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre();
			}
		});
		tFnombre_proveedor.setBounds(192, 37, 230, 20);
		tFnombre_proveedor.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_proveedor.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {				
			}
		});
		contentPane.add(tFnombre_proveedor);
		tFnombre_proveedor.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(493, 69, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnEliminar.setBounds(493, 103, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_proveedor = new JLabel("Nombre de Proveedor");
		lbl_nombre_proveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_proveedor.setBounds(10, 35, 172, 24);
		contentPane.add(lbl_nombre_proveedor);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaProveedores = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaProveedores.setRowSorter(ordenador);
		//
		tablaProveedores.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaProveedores.getColumnCount(); i++) {
			tablaProveedores.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaProveedores.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaProveedores.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaProveedores);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablaProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTabla.setBounds(10, 180, 775, 320);
		contentPane.add(scrollPaneTabla);

		cBTProveedor = new JComboBox<String>();
		cBTProveedor.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cBTProveedor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorTipo();
			}
		});
		cBTProveedor.setModel(new DefaultComboBoxModel<String>(tiposProveedor));
		cBTProveedor.setBounds(192, 70, 172, 20);
		contentPane.add(cBTProveedor);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaProveedor();
			}
		});
		btnAgregar.setBounds(493, 34, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaProveedores.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(237, 528, 150, 23);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(397, 528, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(493, 137, 220, 23);
		contentPane.add(btnActualizar);
		
		btnExportExcell = new JButton("");
		btnExportExcell.setIcon(new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/formulario.png")));
		btnExportExcell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportExcell.setBounds(753, 137, 32, 32);
		contentPane.add(btnExportExcell);
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaProveedores, "Proveedores");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}

	//Gestion
	public void modificar(){
		int row = tablaProveedores.getSelectedRow();
		if (row >= 0) {
			int aux = tablaProveedores.convertRowIndexToModel(row);
			Long id = new Long (tablaProveedores.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Proveedor [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarProveedor(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Proveedor primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}
	public void eliminar(){
		int row = tablaProveedores.getSelectedRow();
		if (row >= 0) {
			int aux = tablaProveedores.convertRowIndexToModel(row);
			Long id = new Long (tablaProveedores.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Proveedor [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/delete.png"))) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarProveedor(id)){
					JOptionPane.showMessageDialog(contentPane,"Proveedor eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Proveedor primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIGestionProveedor.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}
	
	// Filtros //
	public void filtrarPorNombre(){
		String filtro = tFnombre_proveedor.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>solicitantes = datosTabla;
		for (int i=0; i< solicitantes.size();i++){
			Vector<String> usuario = solicitantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(1).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaProveedores.getColumnCount(); i++) {
			tablaProveedores.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaProveedores.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	public void filtrarPorTipo(){
		String filtro = cBTProveedor.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>solicitantes = datosTabla;
		for (int i=0; i< solicitantes.size();i++){
			Vector<String> solicitante = solicitantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(solicitante.elementAt(2).toLowerCase());
			if (mat.find()) {
				datos.add(solicitante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaProveedores.getColumnCount(); i++) {
			tablaProveedores.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaProveedores.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		tiposProveedor = new Vector<String>();
		tiposProveedor.add("");
		tiposProveedor.add("Fabrica");
		tiposProveedor.add("Sucursal");
		tiposProveedor.add("Alternativo");

		nombreColumnas = new Vector<String> ();
		anchos = new Vector<Integer>();
		
		int chico = 100;
		int mediano = 250;
		int grande = 425;
		
		nombreColumnas.add("ID Solicitante");
		anchos.add(75);
		nombreColumnas.add("Nombre Solicitante");
		anchos.add(grande);
		nombreColumnas.add("Tipo");
		anchos.add(mediano);
		
		datosTabla = new Vector<Vector<String>>();
		Vector<ProveedorDTO> proveedores = mediador.obtenerProveedores();
		for (int i=0; i< proveedores.size();i++){
			Vector<String> row = new Vector<String> ();
			ProveedorDTO proveedor = mediador.tipoProveedor(proveedores.elementAt(i).getId());
			
			if(proveedor!=null){
				row.add(proveedor.getId().toString());
				row.add(proveedor.getNombre());
				if(proveedor instanceof FabricaDTO){
					row.add("Fabrica");
				}else{
					if(proveedor instanceof SucursalDTO){
						row.add("Sucursal");
					}else{
						if(proveedor instanceof AlternativoDTO){
							row.add("Alternativo");
						}
					}
				}
				datosTabla.add(row);
			}
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
	}

	public void actualizarDatos(){
		datosTabla = new Vector<Vector<String>>();
		Vector<ProveedorDTO> proveedores = mediador.obtenerProveedores();
		for (int i=0; i< proveedores.size();i++){
			Vector<String> row = new Vector<String> ();
			ProveedorDTO proveedor = mediador.tipoProveedor(proveedores.elementAt(i).getId());
			
			if(proveedor!=null){
				row.add(proveedor.getId().toString());
				row.add(proveedor.getNombre());
				if(proveedor instanceof FabricaDTO){
					row.add("Fabrica");
				}else{
					if(proveedor instanceof SucursalDTO){
						row.add("Sucursal");
					}else{
						if(proveedor instanceof AlternativoDTO){
							row.add("Alternativo");
						}
					}
				}
				datosTabla.add(row);
			}
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaProveedores.getColumnCount(); i++) {
			tablaProveedores.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaProveedores.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		tFnombre_proveedor.setText("");
		cBTProveedor.setSelectedIndex(0);
	}
	
}