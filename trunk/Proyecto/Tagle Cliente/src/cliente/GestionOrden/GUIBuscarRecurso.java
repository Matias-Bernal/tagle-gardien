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

import common.DTOs.RecursoDTO;

public class GUIBuscarRecurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorOrden mediador;
	
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaRecursos;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarRecurso(MediadorOrden mediadorOrden) {

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
		tablaRecursos = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaRecursos.setRowSorter(ordenador);
		//
		tablaRecursos.getTableHeader().setReorderingAllowed(false);

		tablaRecursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaRecursos.setBounds(0, 0, 765, 320);
		tablaRecursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					buscarRecurso();
			    else{
			    	e.consume();
			    }   
			}
		});
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaRecursos);
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Recurso");
	nombreColumnas.add("Numero Recurso");
//	nombreColumnas.add("Numero Pedido");
//	nombreColumnas.add("Numero Pieza");

	datosTabla = new Vector<Vector<String>>();
	Vector<RecursoDTO> entidades = mediador.obtenerRecursos();
	for (int i=0; i< entidades.size();i++){
		Vector<String> row = new Vector<String> ();
		
		row.add(entidades.elementAt(i).getId().toString());
		row.add(entidades.elementAt(i).getNumero_recurso());
		
//		if (entidades.elementAt(i).getPedido_pieza()!=null){
//			row.add(entidades.elementAt(i).getPedido_pieza().getPedido().getNumero_pedido());
//			row.add(entidades.elementAt(i).getPedido_pieza().getPieza().getNumero_pieza());
//		}else{
//			row.add("SIN PEDIDO");
//			row.add("SIN PIEZA");
//		}
		datosTabla.add(row);
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaRecursos = new JTable(modelo) {
		boolean[] columnEditables = new boolean[] {
			false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaRecursos = new JTable(modelo) {
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

	public void buscarRecurso() {
		int row = tablaRecursos.getSelectedRow();
		if (row >= 0) {		
			mediador.setRecurso(tablaRecursos.getValueAt(row, 0).toString());
			dispose();
		}
	}
	
	private void SetVisible(boolean b) {
		setVisible(true);
	}
}