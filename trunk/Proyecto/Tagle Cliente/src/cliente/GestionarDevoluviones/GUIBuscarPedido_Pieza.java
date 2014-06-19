package cliente.GestionarDevoluviones;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window.Type;
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

import cliente.GestionarReclamo.MediadorReclamo;
import common.DTOs.EntidadDTO;
import common.DTOs.Pedido_PiezaDTO;

public class GUIBuscarPedido_Pieza extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MediadorDevoluciones mediador;
	
	private Vector<Vector<Object>> datosTabla;
	private Vector<String> nombreColumnas;
	private JTable tablaPedido_Piezas;
	private DefaultTableModel modelo;
	
	
	public GUIBuscarPedido_Pieza(MediadorDevoluciones mediador) {
		setResizable(false);

		this.mediador = mediador;
		cargarDatos();
		initialize();
		SetVisible(true);
	}
	
	public void initialize() {
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaPedido_Piezas = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		TableRowSorter<TableModel> ordenador = new TableRowSorter<TableModel>(modelo);
		tablaPedido_Piezas.setRowSorter(ordenador);
		//
		tablaPedido_Piezas.getTableHeader().setReorderingAllowed(false);

		tablaPedido_Piezas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPedido_Piezas.setBounds(0, 0, 765, 320);
		tablaPedido_Piezas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getClickCount() == 2)
					buscarPedidoPieza();
			    else{
			    	e.consume();
			    }   
			}
		});
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaPedido_Piezas);
		scrollPaneTabla.setBounds(10, 182, 764, 318);
		contentPane.add(scrollPaneTabla);
		
	}

private void cargarDatos() {
		
	modelo = new DefaultTableModel();

	nombreColumnas = new Vector<String> ();
	nombreColumnas.add("ID Pedido_Pieza");
	nombreColumnas.add("Numero Pedido");
	nombreColumnas.add("Numero Pieza");
	nombreColumnas.add("Stock");
	nombreColumnas.add("Propio");
	nombreColumnas.add("Solicitud Fabrica");
	nombreColumnas.add("Repcion Fabrica");
	nombreColumnas.add("Pnc");
	nombreColumnas.add("Muleto");
	nombreColumnas.add("Estado Pedido");
	nombreColumnas.add("ID BDG");
	nombreColumnas.add("ID Mano Obra");
	nombreColumnas.add("Envio Agente");
	nombreColumnas.add("Recepcion Agente");
	nombreColumnas.add("Pieza Usada");

	datosTabla = new Vector<Vector<Object>>();
	Vector<Pedido_PiezaDTO> entidades = mediador.obtenerPedido_Piezas();
	for (int i=0; i< entidades.size();i++){
		Vector<Object> row = new Vector<Object> ();
		row.add(entidades.elementAt(i).getId().toString());
		if (entidades.elementAt(i).getPedido()!=null){
			row.add(entidades.elementAt(i).getPedido().getNumero_pedido());
		}else{
			row.add("SIN PEDIDO");
		}
		if (entidades.elementAt(i).getPieza()!=null){
			row.add(entidades.elementAt(i).getPieza().getNumero_pieza());
		}else{
			row.add("SIN PIEZA");
		}
		if (entidades.elementAt(i).getStock()){
			row.add("SI");
		}else{
			row.add("NO");
		}
			
		datosTabla.add(row);
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaPedido_Piezas = new JTable(modelo) {
		boolean[] columnEditables = new boolean[] {
			false, false, false, false
		};
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	modelo.setDataVector(datosTabla, nombreColumnas);
	tablaPedido_Piezas = new JTable(modelo) {
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

	public void buscarPedidoPieza() {
		int row = tablaPedido_Piezas.getSelectedRow();
		if (row >= 0) {		
			mediador.setPedidoPieza(tablaPedido_Piezas.getValueAt(row, 0).toString());
			dispose();
		}
	}
	
	private void SetVisible(boolean b) {
		setVisible(true);
	}


}
