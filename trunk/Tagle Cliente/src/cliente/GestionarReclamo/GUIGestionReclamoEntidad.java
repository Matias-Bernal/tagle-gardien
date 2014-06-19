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
package cliente.GestionarReclamo;

import java.text.SimpleDateFormat;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;

import com.toedter.calendar.JDateChooser;

import common.DTOs.EntidadDTO;
import common.DTOs.ReclamoDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class GUIGestionReclamoEntidad extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private MediadorReclamo mediador;
	private JComboBox entidad;
	private JDateChooser fecha_reclamo;
	private JDateChooser fecha_turno;
	private JTextField nombre_reclamante;
	private JTextField dni_reclamante;
	private JTextField nombre_titular;
	private JTextField dominio;
	private JTextField vin;
	private JTextField numero_orden;
	private JComboBox estado_reclamo;
	private DefaultTableModel modelo;
	private JTable tablaReclamo;
	private Vector<Vector<String>> datosTabla;
	private Vector nombreColumnas;
	private Vector<String> entidades;
	private Vector<String> estados_reclamo;
	private DefaultComboBoxModel<String> cmbEstado_reclamo;
	private DefaultComboBoxModel<String> cmbEntidad;
	private Vector<Integer> anchos;
	private JButton btn_clear_FR;
	private JButton btn_clear_FT;

	public GUIGestionReclamoEntidad(MediadorReclamo mediadorReclamo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/reclamo.png")));
		this.setMediadorReclamo(mediadorReclamo);
		cargarDatos();
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("GESTIONAR RECLAMO ENTIDAD");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEntidad = new JLabel("Entidad");
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntidad.setBounds(10, 11, 130, 20);
		contentPane.add(lblEntidad);
		
		entidad = new JComboBox();
		cmbEntidad = new DefaultComboBoxModel<String>(entidades);
		entidad.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEntidad();
			}
		});
		entidad.setModel(cmbEntidad);
		entidad.setBounds(161, 11, 185, 20);
		contentPane.add(entidad);
		
		JLabel label = new JLabel("Fecha Reclamo");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(356, 44, 130, 20);
		contentPane.add(label);
		
		fecha_reclamo = new JDateChooser();
		fecha_reclamo.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFReclamo();
	        }
		});
		fecha_reclamo.setBounds(506, 44, 130, 20);
		contentPane.add(fecha_reclamo);
		
		JLabel lblFechaTurno = new JLabel("Fecha Turno");
		lblFechaTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaTurno.setBounds(356, 75, 130, 20);
		contentPane.add(lblFechaTurno);
		
		fecha_turno = new JDateChooser();
		fecha_turno.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFTurno();
	        }
		});
		fecha_turno.setBounds(506, 75, 130, 20);
		contentPane.add(fecha_turno);
		
		JLabel lblEstadoReclamo = new JLabel("Estado Reclamo");
		lblEstadoReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoReclamo.setBounds(356, 11, 130, 20);
		contentPane.add(lblEstadoReclamo);
		
		estado_reclamo = new JComboBox();
		cmbEstado_reclamo = new DefaultComboBoxModel<String>(estados_reclamo);
		estado_reclamo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEstado_Reclamo();
			}
		});
		estado_reclamo.setModel(cmbEstado_reclamo);
		estado_reclamo.setBounds(507, 11, 220, 20);
		contentPane.add(estado_reclamo);
		
		JLabel lblNombreReclamante = new JLabel("Nombre Reclamante");
		lblNombreReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreReclamante.setBounds(10, 42, 130, 20);
		contentPane.add(lblNombreReclamante);
		
		nombre_reclamante = new JTextField();
		nombre_reclamante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorReclamante();
			}
		});
		nombre_reclamante.setBounds(161, 42, 185, 20);
		contentPane.add(nombre_reclamante);
		nombre_reclamante.setColumns(10);
		
		JLabel lblDniReclamante = new JLabel("DNI Reclamante");
		lblDniReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblDniReclamante.setBounds(10, 75, 130, 20);
		contentPane.add(lblDniReclamante);
		
		dni_reclamante = new JTextField();
		dni_reclamante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorDni();
			}
		});
		dni_reclamante.setColumns(10);
		dni_reclamante.setBounds(161, 75, 130, 20);
		contentPane.add(dni_reclamante);
		
		JLabel lblNombreTitular = new JLabel("Nombre Titular");
		lblNombreTitular.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTitular.setBounds(10, 106, 130, 20);
		contentPane.add(lblNombreTitular);
		
		nombre_titular = new JTextField();
		nombre_titular.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorTitular();
			}
		});
		nombre_titular.setColumns(10);
		nombre_titular.setBounds(161, 106, 185, 20);
		contentPane.add(nombre_titular);
		
		JLabel lblDominio = new JLabel("Dominio");
		lblDominio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio.setBounds(10, 139, 130, 20);
		contentPane.add(lblDominio);
		
		dominio = new JTextField();
		dominio.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorDominio();
			}
		});
		dominio.setColumns(10);
		dominio.setBounds(161, 139, 130, 20);
		contentPane.add(dominio);
		
		JLabel lblVin = new JLabel("VIN");
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBounds(10, 170, 130, 20);
		contentPane.add(lblVin);
		
		vin = new JTextField();
		vin.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorVin();
			}
		});
		vin.setToolTipText("Ej 12345678901234567");
		vin.addKeyListener(new KeyListener() {
		private int limite = 17;
		public void keyTyped(KeyEvent e) {
			if (vin.getText().length()>=limite){
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
		}
		});
		vin.setColumns(10);
		vin.setBounds(161, 170, 130, 20);
		contentPane.add(vin);
		
		JLabel lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(356, 106, 130, 20);
		contentPane.add(lblNumeroOrden);
		
		numero_orden = new JTextField();
		numero_orden.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorOrden();
			}
		});
		numero_orden.setColumns(10);
		numero_orden.setBounds(507, 106, 130, 20);
		contentPane.add(numero_orden);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		
		tablaReclamo = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaReclamo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					verReclamante();
			    else{
			    	e.consume();
			    }   
			}
		});
		// Agregamos el ordenador para las tablas de los usuarios
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaReclamo.setRowSorter(ordenador);
		//
		tablaReclamo.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaReclamo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReclamo.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaReclamo);
		tablaReclamo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		scrollPaneTabla.setBounds(10, 202, 1254, 426);
		contentPane.add(scrollPaneTabla);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarDatos();
			}
		});
		btnActualizar.setBounds(884, 136, 315, 23);
		contentPane.add(btnActualizar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		btnEliminar.setBounds(884, 102, 315, 23);
		contentPane.add(btnEliminar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modificar();
			}
		});
		btnModificar.setBounds(884, 68, 315, 23);
		contentPane.add(btnModificar);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarNuevoReclamoEntidad();
			}
		});
		btnAgregar.setBounds(884, 33, 315, 23);
		contentPane.add(btnAgregar);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnVolver.setBounds(810, 639, 150, 23);
		contentPane.add(btnVolver);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablaReclamo.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnImprimir.setBounds(562, 639, 150, 23);
		contentPane.add(btnImprimir);
		
		JButton btnVer = new JButton("Ver Reclamante");
		btnVer.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verReclamante();
			}
		});
		btnVer.setBounds(320, 639, 150, 23);
		contentPane.add(btnVer);
		
		JButton btnExportarTabla = new JButton("");
		btnExportarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTabla.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla.setBounds(1232, 158, 32, 32);
		contentPane.add(btnExportarTabla);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_reclamo.getDate()!=null){
					fecha_reclamo.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(646, 43, 25, 20);
		contentPane.add(btn_clear_FR);
		
		btn_clear_FT = new JButton("");
		btn_clear_FT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_turno.getDate()!=null){
					fecha_turno.setDate(null);
				actualizarDatos();
				}
			}
		});
		btn_clear_FT.setIcon(new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FT.setBounds(646, 74, 25, 20);
		contentPane.add(btn_clear_FT);
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaReclamo, "Reclamos Entidad");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	protected void filtrarPorFTurno() {
		if(fecha_turno.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(fecha_turno.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(2).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				} 
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
				tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	protected void filtrarPorFReclamo() {
		if(fecha_reclamo.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(fecha_reclamo.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(1).toLowerCase());
				if (mat.find()) {
					datos.add(registrante);
				} 
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
				tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorOrden() {
		String filtro = numero_orden.getText().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(12).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorVin() {
		String filtro = vin.getText().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(9).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorDominio() {
		String filtro = dominio.getText().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(8).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorTitular() {
		String filtro = nombre_titular.getText().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(7).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorDni() {
		String filtro = dni_reclamante.getText().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(6).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorReclamante() {
		String filtro = nombre_reclamante.getText().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(5).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorEntidad() {
		String filtro = entidad.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(4).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorEstado_Reclamo() {
		String filtro = estado_reclamo.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>> datos = new Vector<Vector<String>>();
		for (int i=0; i< datosTabla.size();i++){
			Vector<String> reclamo = datosTabla.elementAt(i);
			
			Pattern pat = Pattern.compile(".*"+filtro+".*");
			Matcher mat = pat.matcher(reclamo.elementAt(3).toLowerCase());
			if (mat.find()) {
				datos.add(reclamo);
			} 
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void verReclamante() {
		int row = tablaReclamo.getSelectedRow();
		if (row >= 0) {
			String dni = tablaReclamo.getValueAt(row, 0).toString();
			mediador.verRegistrante(dni);
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void agregarNuevoReclamoEntidad() {
		mediador.altaReclamoEntidad();
	}

	//GESTION 
	protected void eliminar() {
		int row = tablaReclamo.getSelectedRow();
		if (row >= 0) {
			Long id = new Long (tablaReclamo.getValueAt(row, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Reclamo [ID:"+id+"]?, Esto eliminara todos sus componenetes.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				mediador.eliminarReclamoEntidad(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void modificar() {
		int row = tablaReclamo.getSelectedRow();
		if (row >= 0) {
			int aux = tablaReclamo.convertRowIndexToModel(row);
			Long id = new Long (tablaReclamo.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Reclamo [ID:"+id+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionReclamoEntidad.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarReclamoEntidad(id);;
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un regsitrante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}

	public MediadorReclamo getMediadorReclamo() {
		return mediador;
	}

	public void setMediadorReclamo(MediadorReclamo mediadorReclamo) {
		this.mediador = mediadorReclamo;
	}

	private void cargarDatos() {
		
		entidades = new Vector<String>();
		entidades.add("");
		Vector<EntidadDTO> entidadesDTO = mediador.obtenerEntidades();
		for (int i=0; i< entidadesDTO.size();i++){
			entidades.add(entidadesDTO.elementAt(i).getNombre_registrante());
		};
		
		anchos = new Vector<Integer>();
		
		int chico = 100;
		int mediano = 150;
		int grande = 230;

		nombreColumnas = new Vector<String> ();
		nombreColumnas.add("ID Reclamo");//0
		anchos.add(75);
		nombreColumnas.add("Fecha Reclamo");//1
		anchos.add(mediano);
		nombreColumnas.add("Fecha Turno");//2
		anchos.add(mediano);
		nombreColumnas.add("Estado Reclamo");//3
		anchos.add(grande);
		nombreColumnas.add("Entidad");//4
		anchos.add(grande);
		nombreColumnas.add("Nombre Reclamante");//5
		anchos.add(grande);
		nombreColumnas.add("DNI Reclamante");//6
		anchos.add(mediano);
		nombreColumnas.add("Nombre Titular");//7
		anchos.add(grande);
		nombreColumnas.add("Dominio");//8
		anchos.add(chico);
		nombreColumnas.add("VIN");//9
		anchos.add(mediano);
		nombreColumnas.add("Inmovilizado");//10
		anchos.add(chico);
		nombreColumnas.add("Peligroso");//11
		anchos.add(chico);
		nombreColumnas.add("Numero Orden");//12
		anchos.add(mediano);
		
		estados_reclamo = new Vector<String>();
		estados_reclamo.add("");
		estados_reclamo.add("ABIERTO");
		estados_reclamo.add("SIN PEDIDO");
		estados_reclamo.add("CON PEDIDO");
		estados_reclamo.add("SIN TURNO");
		estados_reclamo.add("CON TURNO");
		estados_reclamo.add("ABIERTO/SIN PEDIDO/SIN TURNO");
		estados_reclamo.add("ABIERTO/SIN PEDIDO/CON TURNO");
		estados_reclamo.add("ABIERTO/CON PEDIDO/SIN TURNO");
		estados_reclamo.add("ABIERTO/CON PEDIDO/CON TURNO");
		estados_reclamo.add("CERRADO");
				
		modelo = new DefaultTableModel();
		datosTabla = new Vector<Vector<String>>();
		Vector<ReclamoDTO> reclamos = mediador.obtenerReclamosEntidad();
		for (int i=0; i< reclamos.size();i++){
				Vector<String> row = new Vector<String> ();
				
				row.add(reclamos.elementAt(i).getId().toString());
				
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
								
				if(reclamos.elementAt(i).getFecha_reclamo()!=null){
					java.sql.Date fReclamo = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
					row.add(format2.format(fReclamo));
				}else{
					row.add("");
				}
				if(reclamos.elementAt(i).getFecha_turno()!=null){
					java.sql.Date fTurno = new java.sql.Date(reclamos.elementAt(i).getFecha_turno().getTime());
					row.add(format2.format(fTurno));
				}else{
					row.add("");
				}
				row.add(reclamos.elementAt(i).getEstado_reclamo());
				row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());
				if(reclamos.elementAt(i).getReclamante()!=null){
					row.add(reclamos.elementAt(i).getReclamante().getNombre_apellido());
					row.add(reclamos.elementAt(i).getReclamante().getDni());
				}else{
					row.add("");
					row.add("");
				}
				if(reclamos.elementAt(i).getVehiculo()!=null){
					row.add(reclamos.elementAt(i).getVehiculo().getNombre_titular());
					row.add(reclamos.elementAt(i).getVehiculo().getDominio());
					row.add(reclamos.elementAt(i).getVehiculo().getVin());
				}else{
					row.add("");
					row.add("");
					row.add("");
				}
				if(reclamos.elementAt(i).getInmovilizado()!=null){
					if(reclamos.elementAt(i).getInmovilizado()){
						row.add("SI");
					}else{
						row.add("NO");
					}
				}else{
					row.add("");
				}
				if(reclamos.elementAt(i).getPeligroso()!=null){
					if(reclamos.elementAt(i).getPeligroso()){
						row.add("SI");
					}else{
						row.add("NO");
					}	
				}else{
					row.add("");
				}
				if(reclamos.elementAt(i).getOrden()!=null){
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());
				}else{
					row.add("");
				}
				datosTabla.add(row);
		}	
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
	}

	public void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		Vector<ReclamoDTO> reclamos = mediador.obtenerReclamosEntidad();
		for (int i=0; i< reclamos.size();i++){
				Vector<String> row = new Vector<String> ();
				
				row.add(reclamos.elementAt(i).getId().toString());
				
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy");
								
				if(reclamos.elementAt(i).getFecha_reclamo()!=null){
					java.sql.Date fReclamo = new java.sql.Date(reclamos.elementAt(i).getFecha_reclamo().getTime());
					row.add(format2.format(fReclamo));
				}else{
					row.add("");
				}
				if(reclamos.elementAt(i).getFecha_turno()!=null){
					java.sql.Date fTurno = new java.sql.Date(reclamos.elementAt(i).getFecha_turno().getTime());
					row.add(format2.format(fTurno));
				}else{
					row.add("");
				}
				row.add(reclamos.elementAt(i).getEstado_reclamo());
				row.add(reclamos.elementAt(i).getRegistrante().getNombre_registrante());
				if(reclamos.elementAt(i).getReclamante()!=null){
					row.add(reclamos.elementAt(i).getReclamante().getNombre_apellido());
					row.add(reclamos.elementAt(i).getReclamante().getDni());
				}else{
					row.add("");
					row.add("");
				}
				if(reclamos.elementAt(i).getVehiculo()!=null){
					row.add(reclamos.elementAt(i).getVehiculo().getNombre_titular());
					row.add(reclamos.elementAt(i).getVehiculo().getDominio());
					row.add(reclamos.elementAt(i).getVehiculo().getVin());
				}else{
					row.add("");
					row.add("");
					row.add("");
				}
				if(reclamos.elementAt(i).getInmovilizado()!=null){
					if(reclamos.elementAt(i).getInmovilizado()){
						row.add("SI");
					}else{
						row.add("NO");
					}
				}else{
					row.add("");
				}
				if(reclamos.elementAt(i).getPeligroso()!=null){
					if(reclamos.elementAt(i).getPeligroso()){
						row.add("SI");
					}else{
						row.add("NO");
					}	
				}else{
					row.add("");
				}
				if(reclamos.elementAt(i).getOrden()!=null){
					row.add(reclamos.elementAt(i).getOrden().getNumero_orden());
				}else{
					row.add("");
				}
				datosTabla.add(row);
		}
		
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaReclamo.getColumnCount(); i++) {
			tablaReclamo.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaReclamo.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		entidad.setSelectedIndex(0);
		estado_reclamo.setSelectedIndex(0);;
		
		nombre_reclamante.setText("");
		dni_reclamante.setText("");
		nombre_titular.setText("");
		dominio.setText("");
		vin.setText("");
		fecha_reclamo.setDate(null);
		fecha_turno.setDate(null);
		numero_orden.setText("");
		}
}
