package cliente.GestionarReclamo;

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

import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;

public class GUIBuscarOrden extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorReclamo mediador;
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaordenes;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarOrden(MediadorReclamo mediadorReclamo) {

		this.mediador = mediadorReclamo;
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
		tablaordenes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaordenes.setRowSorter(ordenador);
		//
		tablaordenes.getTableHeader().setReorderingAllowed(false);

		tablaordenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaordenes.setBounds(0, 0, 765, 320);
		tablaordenes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					buscarOrden();
			    else{
			    	e.consume();
			    }   
			}
		});
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaordenes);
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Orden");
	nombreColumnas.add("Numero Orden");
	nombreColumnas.add("Fecha Apertura");
	nombreColumnas.add("Fecha Cierre");
	nombreColumnas.add("Estado Orden");

	datosTabla = new Vector<Vector<String>>();
	Vector<OrdenDTO> ordenes = mediador.obtenerOrdenes();
	for (int i=0; i< ordenes.size();i++){
		Vector<String> row = new Vector<String> ();
		if (ordenes.elementAt(i).getEstado().equals("SIN RECLAMO")){
			row.add(ordenes.elementAt(i).getId().toString());
			row.add(ordenes.elementAt(i).getNumero_orden());
			row.add(ordenes.elementAt(i).getFecha_apertura().toString());
			if (ordenes.elementAt(i).getFecha_cierre()!= null){
				row.add(ordenes.elementAt(i).getFecha_cierre().toString());
			}else{
				row.add("");
			}
			row.add(ordenes.elementAt(i).getEstado());
			datosTabla.add(row);
		}		
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaordenes = new JTable(modelo) {
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

	public void buscarOrden() {
		int row = tablaordenes.getSelectedRow();
		if (row >= 0) {		
			mediador.setOrden(tablaordenes.getValueAt(row, 0).toString());
			dispose();
		}
	}
	
	private void SetVisible(boolean b) {
		setVisible(true);
	}


}
