package cliente.GestionOrden;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import common.DTOs.ReclamoDTO;

public class GUIBuscarReclamo extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorOrden mediador;
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaReclamos;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarReclamo(MediadorOrden mediadorOrden) {
		this.mediador = mediadorOrden;
		cargarDatos();
		initialize();
		SetVisible(true);
	}
	
	public void initialize() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 612, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaReclamos = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaReclamos.setRowSorter(ordenador);
		//
		tablaReclamos.getTableHeader().setReorderingAllowed(false);

		tablaReclamos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReclamos.setBounds(0, 0, 765, 320);
		tablaReclamos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					buscarReclamo();
			    else{
			    	e.consume();
			    }   
			}
		});
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaReclamos);
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Recurso");
	nombreColumnas.add("Numero Recurso");
	nombreColumnas.add("Numero Pedido");
	nombreColumnas.add("Numero Pieza");

	datosTabla = new Vector<Vector<String>>();
	Vector<ReclamoDTO> reclamos = mediador.obtenerReclamos();
	for (int i=0; i< reclamos.size();i++){
		Vector<String> row = new Vector<String> ();
		
		row.add(reclamos.elementAt(i).getId().toString());

		datosTabla.add(row);
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaReclamos = new JTable(modelo) {
		boolean[] columnEditables = new boolean[] {
			false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaReclamos = new JTable(modelo) {
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

	public void buscarReclamo() {
		int row = tablaReclamos.getSelectedRow();
		if (row >= 0) {		
			mediador.setReclamo(tablaReclamos.getValueAt(row, 0).toString());
			dispose();
		}
	}
	
	private void SetVisible(boolean b) {
		setVisible(true);
	}
}