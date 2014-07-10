package cliente.GestionarSolicitante;

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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;
import cliente.utils.JPanel_Whit_Image;
import comun.DTOs.GarantiaDTO;
import comun.DTOs.MayoristaDTO;
import comun.DTOs.MecanicoDTO;
import comun.DTOs.MostradorDTO;
import comun.DTOs.SeguroDTO;
import comun.DTOs.SolicitanteDTO;
import java.awt.Toolkit;

import javax.swing.border.MatteBorder;

import java.awt.Color;

public class GUIGestionSolicitante extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorSolicitante mediador;
	
	private JPanel contentPane;
	
	private int limite = 35;
	private JTextField tFnombre_solicitante;
	private JTextField tFemail;
	private JButton btnModificar; 
	private JButton btnEliminar; 
	private JButton btnAgregar; 
	private JButton btnImprimir; 
	private JButton btnVolver; 
	private JButton btnActualizar; 
	private JLabel labelTipoSolicitante;
	
	//Tabla
	private DefaultTableModel modelo;
	private JTable tablaSolicitantes;
	private Vector<Vector<String>> datosTabla;
	private Vector<Integer> anchos;

	private Vector<String> nombreColumnas; 
	//Combobox de Usuarios
	private JComboBox<String> cBTsolicitantes;
	private Vector<String> tiposSolicitantes;	
	private JButton btnExportExcell;

	public GUIGestionSolicitante(MediadorSolicitante mediador) {
		this.mediador = mediador;
		cargarDatos();
		initialize();
	}
	
	@SuppressWarnings("serial")
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("GESTIONAR SOLICITANTES");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/usuarios.png")));
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);	
		setLocationRelativeTo(null);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		labelTipoSolicitante = new JLabel("Tipo Solicitante");
		labelTipoSolicitante.setHorizontalAlignment(SwingConstants.CENTER);
		labelTipoSolicitante.setBounds(10, 109, 172, 24);
		contentPane.add(labelTipoSolicitante);
		
		
		tFnombre_solicitante = new JTextField();
		tFnombre_solicitante.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFnombre_solicitante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNombre();
			}
		});
		tFnombre_solicitante.setBounds(192, 37, 230, 20);
		tFnombre_solicitante.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFnombre_solicitante.getText().length()>=limite){
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
		contentPane.add(tFnombre_solicitante);
		tFnombre_solicitante.setColumns(10);
		
		tFemail = new JTextField();
		tFemail.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFemail.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorEmail();
			}
		});
		tFemail.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tFemail.getText().length()>=limite){
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
		tFemail.setBounds(192, 72, 230, 20);
		contentPane.add(tFemail);
		tFemail.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(493, 69, 220, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		
		btnEliminar.setBounds(493, 103, 220, 23);
		contentPane.add(btnEliminar);
		
		JLabel lbl_nombre_solicitante = new JLabel("Nombre de Solicitante");
		lbl_nombre_solicitante.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_nombre_solicitante.setBounds(10, 35, 172, 24);
		contentPane.add(lbl_nombre_solicitante);
		
		JLabel lbl_email = new JLabel("Email");
		lbl_email.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_email.setBounds(10, 72, 172, 24);
		contentPane.add(lbl_email);
		
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaSolicitantes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaSolicitantes.setRowSorter(ordenador);
		//
		tablaSolicitantes.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaSolicitantes.getColumnCount(); i++) {
			tablaSolicitantes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaSolicitantes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaSolicitantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaSolicitantes.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaSolicitantes);
		scrollPaneTabla.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tablaSolicitantes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		scrollPaneTabla.setBounds(10, 180, 775, 320);
		contentPane.add(scrollPaneTabla);

		cBTsolicitantes = new JComboBox<String>();
		cBTsolicitantes.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		cBTsolicitantes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorTipo();
			}
		});
		cBTsolicitantes.setModel(new DefaultComboBoxModel<String>(tiposSolicitantes));
		cBTsolicitantes.setBounds(192, 111, 172, 20);
		contentPane.add(cBTsolicitantes);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaSolicitante();
			}
		});
		btnAgregar.setBounds(493, 34, 220, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tablaSolicitantes.print();
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		});
		btnImprimir.setBounds(237, 528, 150, 23);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(397, 528, 150, 23);
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(493, 137, 220, 23);
		contentPane.add(btnActualizar);
		
		btnExportExcell = new JButton("");
		btnExportExcell.setIcon(new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/formulario.png")));
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
			ExportarExcel.exportarUnaTabla(tablaSolicitantes, "Solicitantes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}

	//Gestion
	public void modificar(){
		int row = tablaSolicitantes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaSolicitantes.convertRowIndexToModel(row);
			Long id = new Long (tablaSolicitantes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Solicitante [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarSolicitante(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Solicitante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}
	public void eliminar(){
		int row = tablaSolicitantes.getSelectedRow();
		if (row >= 0) {
			int aux = tablaSolicitantes.convertRowIndexToModel(row);
			Long id = new Long (tablaSolicitantes.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Solicitante [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionSolicitante.class.getResource("/cliente/Recursos/Iconos/delete.png"))) == JOptionPane.YES_OPTION){ 
				if (mediador.eliminarSolicitante(id)){
					JOptionPane.showMessageDialog(contentPane,"Solicitante eliminado.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
					actualizarDatos();
				}
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un Solicitante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(GUIModificarSolicitante.class.getResource("/cliente/Recursos/Iconos/informacion.png")));
		}
	}
	
	// Filtros //
	public void filtrarPorNombre(){
		String filtro = tFnombre_solicitante.getText().toLowerCase();
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
		
		for(int i = 0; i < tablaSolicitantes.getColumnCount(); i++) {
			tablaSolicitantes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaSolicitantes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	public void filtrarPorEmail(){
		String filtro = tFemail.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>solicitantes = datosTabla;
		for (int i=0; i< solicitantes.size();i++){
			Vector<String> usuario = solicitantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(usuario.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(usuario);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaSolicitantes.getColumnCount(); i++) {
			tablaSolicitantes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaSolicitantes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	public void filtrarPorTipo(){
		String filtro = cBTsolicitantes.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>>solicitantes = datosTabla;
		for (int i=0; i< solicitantes.size();i++){
			Vector<String> solicitante = solicitantes.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(solicitante.elementAt(4).toLowerCase());
			if (mat.find()) {
				datos.add(solicitante);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaSolicitantes.getColumnCount(); i++) {
			tablaSolicitantes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaSolicitantes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	public void cargarDatos(){
		
		modelo = new DefaultTableModel();

		tiposSolicitantes = new Vector<String>();
		tiposSolicitantes.add("");
		tiposSolicitantes.add("Mostrador");
		tiposSolicitantes.add("Mayorista");
		tiposSolicitantes.add("Garantia");
		tiposSolicitantes.add("Seguro");
		tiposSolicitantes.add("Taller Mecanico");
		tiposSolicitantes.add("Taller Carroceria");

		nombreColumnas = new Vector<String> ();
		anchos = new Vector<Integer>();
		
		int chico = 100;
		int mediano = 150;
		int grande = 227;
		
		nombreColumnas.add("ID Solicitante");
		anchos.add(75);
		nombreColumnas.add("Nombre Solicitante");
		anchos.add(grande);
		nombreColumnas.add("Telefono");
		anchos.add(grande);
		nombreColumnas.add("Email");
		anchos.add(grande);
		nombreColumnas.add("Tipo");
		anchos.add(grande);
		
		datosTabla = new Vector<Vector<String>>();
		Vector<SolicitanteDTO> solicitantes = mediador.obtenerSolicitates();
		for (int i=0; i< solicitantes.size();i++){
			Vector<String> row = new Vector<String> ();
			SolicitanteDTO solicitante = mediador.tipoSolicitante(solicitantes.elementAt(i).getId());
			
			if(solicitante!=null){
				row.add(solicitante.getId().toString());
				row.add(solicitante.getNombre());
				if(solicitante instanceof MostradorDTO){
					row.add(((MostradorDTO) solicitante).getTelefono());
					row.add(((MostradorDTO) solicitante).getMail());
					row.add("Mostrador");
				}else{
					if(solicitante instanceof MayoristaDTO){
						row.add("");
						row.add("");
						row.add("Mayorista");
					}else{
						if(solicitante instanceof GarantiaDTO){
							row.add("");
							row.add("");
							row.add("Garantia");
						}else{
							if(solicitante instanceof SeguroDTO){
								row.add("");
								row.add("");
								row.add("Seguro");
							}else{
								if(solicitante instanceof MecanicoDTO){
									row.add("");
									row.add("");
									row.add("Taller Mecanico");
								}else{
									row.add("");
									row.add("");
									row.add("Taller Carroceria");
								}
							}
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
		Vector<SolicitanteDTO> solicitantes = mediador.obtenerSolicitates();
		for (int i=0; i< solicitantes.size();i++){
			Vector<String> row = new Vector<String> ();
			SolicitanteDTO solicitante = mediador.tipoSolicitante(solicitantes.elementAt(i).getId());
			
			if(solicitante!=null){
				row.add(solicitante.getId().toString());
				row.add(solicitante.getNombre());
				if(solicitante instanceof MostradorDTO){
					row.add(((MostradorDTO) solicitante).getTelefono());
					row.add(((MostradorDTO) solicitante).getMail());
					row.add("Mostrador");
				}else{
					if(solicitante instanceof MayoristaDTO){
						row.add("");
						row.add("");
						row.add("Mayorista");
					}else{
						if(solicitante instanceof GarantiaDTO){
							row.add("");
							row.add("");
							row.add("Garantia");
						}else{
							if(solicitante instanceof SeguroDTO){
								row.add("");
								row.add("");
								row.add("Seguro");
							}else{
								if(solicitante instanceof MecanicoDTO){
									row.add("");
									row.add("");
									row.add("Taller Mecanico");
								}else{
									row.add("");
									row.add("");
									row.add("Taller Carroceria");
								}
							}
						}
					}
				}
				datosTabla.add(row);
			}
		};
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaSolicitantes.getColumnCount(); i++) {
			tablaSolicitantes.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaSolicitantes.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		tFnombre_solicitante.setText("");
		tFemail.setText("");
		cBTsolicitantes.setSelectedIndex(0);
	}
	
}
