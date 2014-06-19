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
package cliente.GestionarPedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import cliente.excellexport.ExportarExcel;

import com.toedter.calendar.JDateChooser;

import common.DTOs.AgenteDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class GUIGestionPedidoAgente extends JFrame{

	private static final long serialVersionUID = 1L;

	protected MediadorPedido mediador;
	
	private JPanel contentPane;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnActualizar;
	private JButton btnVer;
	private JTextField tfpedido;
	private JTextField tfpnc;
	private JTextField tfNumPieza;
	private JTextField tfOrden;
	private JTextField tfTitular;
	private JTextField tfDominio;
	private JTextField tfVIN;
	private JTextField tfReclamante;
	private JTextField tfMuleto;
	private JTextField tfNumeroBDG;
	private JTextField tfRemito;
	
	//Tabla
	private Vector<Vector<String>> datosTabla;
	private Vector<String> nombreColumnas;
	private DefaultTableModel modelo;
	private JTable tablaPedidos;
	private Vector<Integer> anchos;

	private JDateChooser dCFSP;
	private JDateChooser dCFSF;
	private JDateChooser dCFRF;
	private JDateChooser dCFBDG;
	private JDateChooser dcFEF;
	private JDateChooser dCFEA;
	private JDateChooser dCFRA;
	private JDateChooser dCFR;

	private JComboBox cBAgentes;
	private Vector<String> agentes;
	private DefaultComboBoxModel<String> cmbAgentes;

	private JComboBox cBEstadoPedido;
	private Vector<String> estados_pedidos;
	private DefaultComboBoxModel<String> cmbEstadoPedido;
	private JButton btnExportarTabla;
	private JButton btn_clear_FR;
	private JButton btn_clear_FBDG;
	private JButton btn_clear_FSF;
	private JButton btn_clear_FRF;
	private JButton btn_clear_FDF;
	private JButton btn_clear_FEA;
	private JButton btn_clear_FRA;

	public GUIGestionPedidoAgente(MediadorPedido mediadorRegistrante) {

		this.mediador = mediadorRegistrante;
		cargarDatos();
		initialize();
	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("GESTIONAR PEDIDO AGENTE");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/pedido.png")));
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.setBounds(1039, 70, 215, 23);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/delete.png")));
		btnEliminar.setBounds(1039, 104, 215, 23);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminar();
			}
		});
		contentPane.add(btnEliminar);
		
		modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tablaPedidos = new JTable(modelo) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false, false, false,
					false, false, false,
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tablaPedidos.addMouseListener(new MouseAdapter() {
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
		tablaPedidos.setRowSorter(ordenador);
		//
		tablaPedidos.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		tablaPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPedidos.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tablaPedidos);
		tablaPedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTabla.setBounds(20, 218, 1244, 398);
		scrollPaneTabla.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTabla.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/add.png")));
		btnAgregar.setBounds(1039, 35, 215, 23);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mediador.altaPedidoAgente();
			}
		});
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/printer.png")));
		btnImprimir.setBounds(562, 635, 150, 23);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					tablaPedidos.print();
				} catch (PrinterException ex) {
					JOptionPane.showMessageDialog(contentPane,"Error al imprimir.","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/back.png")));
		btnVolver.setBounds(858, 635, 150, 23);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/1refresh.png")));
		btnActualizar.setBounds(1039, 138, 215, 23);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actualizarDatos();
			}
		});
		contentPane.add(btnActualizar);
		
		btnVer = new JButton("Ver Reclamante");
		btnVer.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/find_reclamo.png")));
		btnVer.setBounds(263, 634, 150, 23);
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verReclamante();
			}
		});
		contentPane.add(btnVer);
		
		JPanel primer_panel = new JPanel();
		primer_panel.setBounds(10, 11, 345, 209);
		contentPane.add(primer_panel);
		primer_panel.setLayout(null);
		
		JLabel lblNumero_pedido = new JLabel("Numero Pedido");
		lblNumero_pedido.setBounds(0, 10, 135, 20);
		primer_panel.add(lblNumero_pedido);
		lblNumero_pedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfpedido = new JTextField();
		tfpedido.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNumero_Pedido();
			}
		});
		tfpedido.setBounds(135, 10, 150, 20);
		primer_panel.add(tfpedido);
		tfpedido.setColumns(10);
		
		JLabel lbl_FSP = new JLabel("Fecha Solicitud Pedido");
		lbl_FSP.setBounds(0, 60, 135, 20);
		primer_panel.add(lbl_FSP);
		lbl_FSP.setHorizontalAlignment(SwingConstants.CENTER);
		
		dCFSP = new JDateChooser();
		dCFSP.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFSP();
	        }
		});
		dCFSP.setBounds(135, 60, 128, 20);
		primer_panel.add(dCFSP);
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setBounds(0, 35, 135, 20);
		primer_panel.add(lblEstadoPedido);
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		cBEstadoPedido = new JComboBox();
		cmbEstadoPedido = new DefaultComboBoxModel<String>(estados_pedidos);
		cBEstadoPedido.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorEstado_Pedido();
			}
		});
		cBEstadoPedido.setModel(cmbEstadoPedido);
		cBEstadoPedido.setBounds(135, 35, 210, 20);
		primer_panel.add(cBEstadoPedido);
		
		JLabel label = new JLabel("Nombre Agente");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 85, 135, 20);
		primer_panel.add(label);
		
		cBAgentes = new JComboBox();
		cmbAgentes = new DefaultComboBoxModel<String>(agentes);
		cBAgentes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				filtrarPorAgentes();
			}
		});
		cBAgentes.setModel(cmbAgentes);
		cBAgentes.setBounds(135, 85, 210, 20);
		primer_panel.add(cBAgentes);
		
		JLabel label_1 = new JLabel("Numero Orden");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 110, 135, 20);
		primer_panel.add(label_1);
		
		tfOrden = new JTextField();
		tfOrden.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorOrden();
			}
		});
		tfOrden.setColumns(10);
		tfOrden.setBounds(135, 110, 150, 20);
		primer_panel.add(tfOrden);
		
		JLabel label_7 = new JLabel("Fecha Reclamo");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(0, 135, 135, 20);
		primer_panel.add(label_7);
		
		dCFR = new JDateChooser();
		dCFR.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFReclamo();
	        }
		});
		dCFR.setBounds(135, 135, 128, 20);
		primer_panel.add(dCFR);
		
		JLabel label_8 = new JLabel("Nombre Reclamante");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(0, 160, 135, 20);
		primer_panel.add(label_8);
		
		tfReclamante = new JTextField();
		tfReclamante.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorReclamante();;
			}
		});
		tfReclamante.setColumns(10);
		tfReclamante.setBounds(135, 160, 150, 20);
		primer_panel.add(tfReclamante);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFR.getDate()!=null){
					dCFR.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(273, 135, 25, 20);
		primer_panel.add(btn_clear_FR);
		
		JPanel segundo_panel = new JPanel();
		segundo_panel.setBounds(360, 11, 329, 209);
		contentPane.add(segundo_panel);
		segundo_panel.setLayout(null);
		
		JLabel lbl_PNC = new JLabel("PNC");
		lbl_PNC.setBounds(0, 110, 135, 20);
		segundo_panel.add(lbl_PNC);
		lbl_PNC.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIdMuleto = new JLabel("ID Muleto");
		lblIdMuleto.setBounds(0, 135, 135, 20);
		segundo_panel.add(lblIdMuleto);
		lblIdMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfMuleto = new JTextField();
		tfMuleto.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorMuleto();
			}
		});
		tfMuleto.setBounds(135, 135, 128, 20);
		segundo_panel.add(tfMuleto);
		tfMuleto.setColumns(10);
		
		tfpnc = new JTextField();
		tfpnc.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorPnc();
			}
		});
		tfpnc.setBounds(135, 110, 128, 20);
		segundo_panel.add(tfpnc);
		tfpnc.setColumns(10);
		
		JLabel label_2 = new JLabel("Numero Pieza");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 85, 135, 20);
		segundo_panel.add(label_2);
		
		tfNumPieza = new JTextField();
		tfNumPieza.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNum_Pieza();
			}
		});
//		tfNumPieza.addKeyListener(new KeyListener() {
//			private int limite;
//			public void keyTyped(KeyEvent e) {
//				if (tfNumPieza.getText().length()>=limite){
//					e.consume();					
//				}
//			}
//			@Override
//			public void keyPressed(KeyEvent arg0) {
//			}
//			@Override
//			public void keyReleased(KeyEvent arg0) {
//			}
//		});
		tfNumPieza.setColumns(10);
		tfNumPieza.setBounds(135, 85, 128, 20);
		segundo_panel.add(tfNumPieza);
		
		JLabel label_4 = new JLabel("Nombre Titular");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(0, 10, 135, 20);
		segundo_panel.add(label_4);
		
		tfTitular = new JTextField();
		tfTitular.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorTitular();
			}
		});
		tfTitular.setColumns(10);
		tfTitular.setBounds(135, 10, 128, 20);
		segundo_panel.add(tfTitular);
		
		JLabel label_5 = new JLabel("Dominio");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(0, 35, 135, 20);
		segundo_panel.add(label_5);
		
		tfDominio = new JTextField();
		tfDominio.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorDominio();
			}
		});
		tfDominio.addKeyListener(new KeyListener() {
		private int limite = 6;
		public void keyTyped(KeyEvent e) {
			if (tfDominio.getText().length()>=limite){
				e.consume();					
			}
//			else{
//				Pattern pat = Pattern.compile("^d{0,3}[A-Z]{0,3}$");
//				Matcher mat = pat.matcher(tfDominio.getText().toLowerCase());
//				if (!mat.find()) {
//					e.consume();
//				} 
//			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {

		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			
		}
		});
		tfDominio.setColumns(10);
		tfDominio.setBounds(135, 35, 128, 20);
		segundo_panel.add(tfDominio);
		
		JLabel label_6 = new JLabel("VIN");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(0, 60, 135, 20);
		segundo_panel.add(label_6);
		
		tfVIN = new JTextField();
		tfVIN.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorVin();
			}
		});
		tfVIN.addKeyListener(new KeyListener() {
		private int limite = 17;
		public void keyTyped(KeyEvent e) {
			if (tfVIN.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
		});
		tfVIN.setColumns(10);
		tfVIN.setBounds(135, 60, 128, 20);
		segundo_panel.add(tfVIN);
		
		JPanel tercer_panel = new JPanel();
		tercer_panel.setBounds(694, 11, 335, 209);
		contentPane.add(tercer_panel);
		tercer_panel.setLayout(null);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudFabrica.setBounds(0, 59, 164, 20);
		tercer_panel.add(lblFechaSolicitudFabrica);
		
		dCFSF = new JDateChooser();
		dCFSF.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFSF();
	        }
		});
		dCFSF.setBounds(163, 59, 128, 20);
		tercer_panel.add(dCFSF);
		
		dCFRF = new JDateChooser();
		dCFRF.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFRF();
	        }
		});
		dCFRF.setBounds(163, 84, 128, 20);
		tercer_panel.add(dCFRF);
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionFabrica.setBounds(0, 84, 164, 20);
		tercer_panel.add(lblFechaRecepcionFabrica);
		
		JLabel lblFechaEnvioAgente = new JLabel("Fecha Envio Agente");
		lblFechaEnvioAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaEnvioAgente.setBounds(0, 159, 164, 20);
		tercer_panel.add(lblFechaEnvioAgente);
		
		JLabel lblFechaRecepcionAgente = new JLabel("Fecha Recepcion Agente");
		lblFechaRecepcionAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionAgente.setBounds(0, 184, 164, 20);
		tercer_panel.add(lblFechaRecepcionAgente);
		
		dCFRA = new JDateChooser();
		dCFRA.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFRA();
	        }
		});
		dCFRA.setBounds(163, 184, 128, 20);
		tercer_panel.add(dCFRA);
		
		dCFEA = new JDateChooser();
		dCFEA.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFEA();
	        }
		});
		dCFEA.setBounds(163, 159, 128, 20);
		tercer_panel.add(dCFEA);
		
		tfNumeroBDG = new JTextField();
		tfNumeroBDG.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorNumBDG();
			}
		});
		tfNumeroBDG.setColumns(10);
		tfNumeroBDG.setBounds(163, 10, 128, 20);
		tercer_panel.add(tfNumeroBDG);
		
		JLabel lblIdBdg = new JLabel("Fecha BDG");
		lblIdBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdBdg.setBounds(0, 35, 164, 20);
		tercer_panel.add(lblIdBdg);
		
		JLabel lblFechaDevolucionFabrica = new JLabel("Fecha Devolucion Fabrica");
		lblFechaDevolucionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDevolucionFabrica.setBounds(0, 109, 164, 20);
		tercer_panel.add(lblFechaDevolucionFabrica);
		
		dcFEF = new JDateChooser();
		dcFEF.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFEF();
	        }
		});
		dcFEF.setBounds(163, 109, 128, 20);
		tercer_panel.add(dcFEF);
		
		JLabel lblNumeroRemito = new JLabel("Numero Remito");
		lblNumeroRemito.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroRemito.setBounds(0, 134, 164, 20);
		tercer_panel.add(lblNumeroRemito);
		
		tfRemito = new JTextField();
		tfRemito.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				filtrarPorRemito();
			}
		});
		tfRemito.setColumns(10);
		tfRemito.setBounds(163, 134, 128, 20);
		tercer_panel.add(tfRemito);
		
		JLabel lblNumeroBdg = new JLabel("Numero BDG");
		lblNumeroBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroBdg.setBounds(0, 10, 164, 20);
		tercer_panel.add(lblNumeroBdg);
		
		dCFBDG = new JDateChooser();
		dCFBDG.getDateEditor().addPropertyChangeListener(new PropertyChangeListener(){ 
	        public void propertyChange(PropertyChangeEvent e) {
	               filtrarPorFBDG();
	        }
		});
		dCFBDG.setBounds(163, 35, 128, 20);
		tercer_panel.add(dCFBDG);
		
		btn_clear_FBDG = new JButton("");
		btn_clear_FBDG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFBDG.getDate()!=null){
					dCFBDG.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FBDG.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FBDG.setBounds(301, 35, 25, 20);
		tercer_panel.add(btn_clear_FBDG);
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFSF.getDate()!=null){
					dCFSF.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSF.setSelectedIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSF.setBounds(301, 59, 25, 20);
		tercer_panel.add(btn_clear_FSF);
		
		btn_clear_FRF = new JButton("");
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFRF.getDate()!=null){
					dCFRF.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRF.setBounds(301, 84, 25, 20);
		tercer_panel.add(btn_clear_FRF);
		
		btn_clear_FDF = new JButton("");
		btn_clear_FDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFEF.getDate()!=null){
					dcFEF.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FDF.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FDF.setBounds(301, 109, 25, 20);
		tercer_panel.add(btn_clear_FDF);
		
		btn_clear_FEA = new JButton("");
		btn_clear_FEA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFEA.getDate()!=null){
					dCFEA.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FEA.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FEA.setBounds(301, 159, 25, 20);
		tercer_panel.add(btn_clear_FEA);
		
		btn_clear_FRA = new JButton("");
		btn_clear_FRA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dCFRA.getDate()!=null){
					dCFRA.setDate(null);
					actualizarDatos();
				}
			}
		});
		btn_clear_FRA.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRA.setBounds(301, 184, 25, 20);
		tercer_panel.add(btn_clear_FRA);
		
		btnExportarTabla = new JButton("");
		btnExportarTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarTablas();
			}
		});
		btnExportarTabla.setIcon(new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/formulario.png")));
		btnExportarTabla.setBounds(1222, 175, 32, 32);
		contentPane.add(btnExportarTabla);
	}
	protected void exportarTablas() {
		try {
			ExportarExcel.exportarUnaTabla(tablaPedidos, "Pedido Agentes");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(contentPane,"Ocurrio un error al querer exportar.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//FILTROS
	protected void filtrarPorEstado_Pedido() {
		String filtro = cBEstadoPedido.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(1)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(1).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorNumero_Pedido() {
		String filtro = tfpedido.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(2)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(2).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorFSP() {
		if(dCFSP.getDate()!=null){
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFSP.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(3)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(3).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}
	
	protected void filtrarPorAgentes() {
		String filtro = cBAgentes.getSelectedItem().toString().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(4)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(4).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorOrden() {
		String filtro = tfOrden.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(5)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(5).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorFReclamo() {
		if(dCFR.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFR.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(6)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(6).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorReclamante() {
		String filtro = tfReclamante.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(7)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(7).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorTitular() {
		String filtro = tfTitular.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(8)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(8).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorDominio() {
		String filtro = tfDominio.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(9)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(9).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorVin() {
		String filtro = tfVIN.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(10)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(10).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}
	
	protected void filtrarPorNum_Pieza() {
		String filtro = tfNumPieza.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(11)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(11).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorFSF() {
		if(dCFSF.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFSF.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(12)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(12).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorFRF() {
		if(dCFRF.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFRF.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(13)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(13).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorFEA() {
		if(dCFEA.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFEA.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(14)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(14).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorFRA() {
	if(dCFRA.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFRA.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(15)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(15).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorFEF() {
		if(dcFEF.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dcFEF.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(16)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(16).toLowerCase());
					if (mat.find())
						datos.add(registrante); 
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	protected void filtrarPorRemito() {
		String filtro = tfRemito.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(17)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(17).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorPnc() {
		String filtro = tfpnc.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(18)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(18).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorMuleto() {
		String filtro = tfMuleto.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(19)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(19).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorNumBDG() {
		String filtro = tfNumeroBDG.getText().toLowerCase();
		Vector<Vector<String>>datos = new Vector<Vector<String>>();
		Vector<Vector<String>> registrantes = datosTabla;
		for (int i=0; i< registrantes.size();i++){
			Vector<String> registrante = registrantes.elementAt(i);
			if(registrante.elementAt(20)!=null){
				Pattern pat = Pattern.compile(".*"+filtro+".*");
				Matcher mat = pat.matcher(registrante.elementAt(20).toLowerCase());
				if (mat.find())
					datos.add(registrante);
			}
		}
		modelo.setDataVector(datos, nombreColumnas);
		modelo.fireTableStructureChanged();
		
		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
	}

	protected void filtrarPorFBDG() {
		if(dCFBDG.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String filtro = format2.format(dCFBDG.getDate());
		    		    
			Vector<Vector<String>>datos = new Vector<Vector<String>>();
			Vector<Vector<String>> registrantes = datosTabla;
			for (int i=0; i< registrantes.size();i++){
				Vector<String> registrante = registrantes.elementAt(i);
				if(registrante.elementAt(21)!=null){
					Pattern pat = Pattern.compile(".*"+filtro+".*");
					Matcher mat = pat.matcher(registrante.elementAt(21).toLowerCase());
					if (mat.find())
						datos.add(registrante);
				}
			}
			modelo.setDataVector(datos, nombreColumnas);
			modelo.fireTableStructureChanged();
			
			for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
				tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
				tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
			}
		}
	}

	//GESTION
	protected void modificar() {
		int row = tablaPedidos.getSelectedRow();
		if (row >= 0) {
			int aux = tablaPedidos.convertRowIndexToModel(row);
			Long id_pedido = new Long (tablaPedidos.getValueAt(aux, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Modificar Pedido [ID:"+id_pedido+"]?", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/edit.png"))) == JOptionPane.YES_OPTION){ 
				mediador.modificarPedidoAgente(id_pedido);;
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un pedido primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void eliminar() {
		int row = tablaPedidos.getSelectedRow();
		if (row >= 0) {
			Long id = new Long (tablaPedidos.getValueAt(row, 0).toString());
			if (JOptionPane.showConfirmDialog(null, "¿Eliminar Pedido [ID:"+id+"]?, Esto eliminara todos sus componenetes.", "Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon(GUIGestionPedidoAgente.class.getResource("/cliente/Resources/Icons/delete.png"))) == JOptionPane.YES_OPTION){ 
				mediador.eliminarPedido(id);
				actualizarDatos();
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un pedido primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void cargarDatos() {
		modelo = new DefaultTableModel();

		nombreColumnas = new Vector<String> ();
		anchos = new Vector<Integer>();
		
		int chico = 100;
		int mediano = 150;
		int grande = 230;

		nombreColumnas.add("ID Pedido");//0
		anchos.add(75);
		nombreColumnas.add("Estado Pedido");//1
		anchos.add(grande);
		nombreColumnas.add("Numero Pedido");//2
		anchos.add(chico);
		nombreColumnas.add("Fecha Solicitud Pedido");//3
		anchos.add(mediano);
		nombreColumnas.add("Nombre Agente");//4
		anchos.add(grande);
		nombreColumnas.add("Numero Orden");//5
		anchos.add(chico);
		nombreColumnas.add("Fecha Reclamo");//6
		anchos.add(chico);
		nombreColumnas.add("Nombre Reclamante");//7
		anchos.add(grande);
		nombreColumnas.add("Nombre Titular");//8
		anchos.add(grande);
		nombreColumnas.add("Dominio");//9
		anchos.add(70);
		nombreColumnas.add("VIN");//10
		anchos.add(130);
		nombreColumnas.add("Numero Pieza");//11
		anchos.add(chico);
		nombreColumnas.add("Fecha Solicitud Fabrica");//12
		anchos.add(mediano);
		nombreColumnas.add("Fecha Recepcion Fabrica");//13
		anchos.add(mediano);
		nombreColumnas.add("Fecha Envio Agente");//14
		anchos.add(mediano);
		nombreColumnas.add("Fecha Recepcion Agente");//15
		anchos.add(mediano);
		nombreColumnas.add("Fecha Devolucion Fabrica");//16
		anchos.add(mediano);
		nombreColumnas.add("Numero Remito");//17
		anchos.add(chico);
		nombreColumnas.add("PNC");//18
		anchos.add(chico);
		nombreColumnas.add("Id Muleto");//19
		anchos.add(chico);
		nombreColumnas.add("Numero BDG");//20
		anchos.add(chico);
		nombreColumnas.add("Fecha BDG");//21
		anchos.add(chico);
		//
		
		agentes = new Vector<String>();
		agentes.add("");
		Vector<AgenteDTO> agentesDTO = mediador.obtenerAgentes();
		for (int i=0; i< agentesDTO.size();i++){
			agentes.add(agentesDTO.elementAt(i).getNombre_registrante());
		};
		
		estados_pedidos = new Vector<String>();
		estados_pedidos.add("");
		estados_pedidos.add("SIN SOLICITUD A FABRICA");
		estados_pedidos.add("EN ESPERA DE RECEPCION FABRICA");
		estados_pedidos.add("SIN ENVIAR A AGENTE");
		estados_pedidos.add("EN ESPERA DE RECEPCION AGENTE");
		estados_pedidos.add("SIN ENVIAR A FABRICA");
		estados_pedidos.add("ENVIADO A FABRICA");

		//tabla
		datosTabla = new Vector<Vector<String>>();
		Vector<PedidoDTO> pedidos = mediador.obtenerPedidosAgentes();
		
		for (int i=0; i< pedidos.size();i++){
				Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
				pedidos_piezas = mediador.buscarPedidoPieza(pedidos.elementAt(i).getId());
				for (int j=0; j< pedidos_piezas.size();j++){
					Vector<String> row = new Vector<String> ();

					row.add(pedidos.elementAt(i).getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(j).getEstado_pedido());//Estado Pedido
					row.add(pedidos_piezas.elementAt(j).getNumero_pedido());//Numero Pedido

					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 

					if(pedidos.elementAt(i).getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos.elementAt(i).getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");
					}			    
					
					row.add(pedidos.elementAt(i).getReclamo().getRegistrante().getNombre_registrante());//Nombre Agente
					if (pedidos.elementAt(i).getReclamo().getOrden()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getOrden().getNumero_orden());//Numero Orden
					}else{
						row.add("");
					}
					if (pedidos.elementAt(i).getReclamo().getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(pedidos.elementAt(i).getReclamo().getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(pedidos.elementAt(i).getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante
					
					if (pedidos.elementAt(i).getReclamo().getVehiculo()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getDominio());//Dominio
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getVin());//VIN
					}else{
						row.add("");
						row.add("");
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getPieza()!=null){
						row.add(pedidos_piezas.elementAt(j).getPieza().getNumero_pieza());//Numero Pieza
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fdf = new java.sql.Date(pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fdf));//Fecha Devolucion Fabrica
						row.add(pedidos_piezas.elementAt(j).getDevolucion_pieza().getNumero_remito());//Numero Remito
					}else{
						row.add("");
						row.add("");
					}
					
					if (pedidos_piezas.elementAt(j).getPnc()!=null){
						row.add(pedidos_piezas.elementAt(j).getPnc());// PCN
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getMuleto()!=null){
						row.add(pedidos_piezas.elementAt(j).getMuleto().getId().toString());//Id Muleto
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getBdg()!=null){
						row.add(pedidos_piezas.elementAt(j).getBdg().getNumero_bdg());//Numero BDG
						if(pedidos_piezas.elementAt(j).getBdg().getFecha_bdg()!=null){
							java.sql.Date fbdg = new java.sql.Date(pedidos_piezas.elementAt(j).getBdg().getFecha_bdg().getTime());
							row.add(format2.format(fbdg));//Fecha BDG
						}else{
							row.add("");
						}
					}else{
						row.add("");
						row.add("");
					}
					datosTabla.add(row);
				}
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();
	}
		
	public void actualizarDatos() {
		datosTabla = new Vector<Vector<String>>();
		Vector<PedidoDTO> pedidos = mediador.obtenerPedidosAgentes();
		for (int i=0; i< pedidos.size();i++){
				Vector<Pedido_PiezaDTO> pedidos_piezas = new Vector<Pedido_PiezaDTO>();
				pedidos_piezas = mediador.buscarPedidoPieza(pedidos.elementAt(i).getId());
				for (int j=0; j< pedidos_piezas.size();j++){
					Vector<String> row = new Vector<String> ();

					row.add(pedidos.elementAt(i).getId().toString());//ID Pedido
					row.add(pedidos_piezas.elementAt(j).getEstado_pedido());//Estado Pedido
					row.add(pedidos_piezas.elementAt(j).getNumero_pedido());//Numero Pedido

					SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
					if(pedidos.elementAt(i).getFecha_solicitud_pedido()!=null){
						java.sql.Date fsp = new java.sql.Date(pedidos.elementAt(i).getFecha_solicitud_pedido().getTime());
						row.add(format2.format(fsp));//Fecha Solicitud Pedido
					}else{
						row.add("");//Fecha Solicitud Pedido
					}
					row.add(pedidos.elementAt(i).getReclamo().getRegistrante().getNombre_registrante());//Nombre Agente
					if (pedidos.elementAt(i).getReclamo().getOrden()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getOrden().getNumero_orden());//Numero Orden
					}else{
						row.add("");
					}
					if (pedidos.elementAt(i).getReclamo().getFecha_reclamo()!=null){
						java.sql.Date fr = new java.sql.Date(pedidos.elementAt(i).getReclamo().getFecha_reclamo().getTime());
						row.add(format2.format(fr));//Fecha Reclamo
					}else{
						row.add("");
					}
					row.add(pedidos.elementAt(i).getReclamo().getReclamante().getNombre_apellido());//Nombre Reclamante
					
					if (pedidos.elementAt(i).getReclamo().getVehiculo()!=null){
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getNombre_titular());//Nombre Titular
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getDominio());//Dominio
						row.add(pedidos.elementAt(i).getReclamo().getVehiculo().getVin());//VIN
					}else{
						row.add("");
						row.add("");
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getPieza()!=null){
						row.add(pedidos_piezas.elementAt(j).getPieza().getNumero_pieza());//Numero Pieza
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica()!=null){
						java.sql.Date fsf = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_solicitud_fabrica().getTime());
						row.add(format2.format(fsf));//Fecha Solicitud Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica()!=null){
						java.sql.Date frf = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(frf));//Fecha Recepcion Fabrica
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_envio_agente()!=null){
						java.sql.Date fea = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_fabrica().getTime());
						row.add(format2.format(fea));//Fecha Envio Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getFecha_recepcion_agente()!=null){
						java.sql.Date fra = new java.sql.Date(pedidos_piezas.elementAt(j).getFecha_recepcion_agente().getTime());
						row.add(format2.format(fra));//Fecha Recepcion Agente
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getDevolucion_pieza()!=null && pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion()!=null){
						java.sql.Date fdf = new java.sql.Date(pedidos_piezas.elementAt(j).getDevolucion_pieza().getFecha_devolucion().getTime());
						row.add(format2.format(fdf));//Fecha Devolucion Fabrica
						row.add(pedidos_piezas.elementAt(j).getDevolucion_pieza().getNumero_remito());//Numero Remito
					}else{
						row.add("");
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getPnc()!=null){
						row.add(pedidos_piezas.elementAt(j).getPnc());// PCN
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getMuleto()!=null){
						row.add(pedidos_piezas.elementAt(j).getMuleto().getId().toString());//Id Muleto
					}else{
						row.add("");
					}
					if (pedidos_piezas.elementAt(j).getBdg()!=null){
						row.add(pedidos_piezas.elementAt(j).getBdg().getNumero_bdg());//Numero BDG
						if(pedidos_piezas.elementAt(j).getBdg().getFecha_bdg()!=null){
							java.sql.Date fbdg = new java.sql.Date(pedidos_piezas.elementAt(j).getBdg().getFecha_bdg().getTime());
							row.add(format2.format(fbdg));//Fecha BDG
						}else{
							row.add("");
						}
					}else{
						row.add("");
						row.add("");
					}
					datosTabla.add(row);
				}
		}
		modelo.setDataVector(datosTabla, nombreColumnas);
		modelo.fireTableStructureChanged();

		for(int i = 0; i < tablaPedidos.getColumnCount(); i++) {
			tablaPedidos.getColumnModel().getColumn(i).setPreferredWidth(anchos.elementAt(i));
			tablaPedidos.getColumnModel().getColumn(i).setMinWidth(anchos.elementAt(i));
		}
		
		tfpedido.setText("");
		cBEstadoPedido.setSelectedIndex(0);
		dCFSP.setDate(null);
		cBAgentes.setSelectedIndex(0);
		tfOrden.setText("");
		dCFR.setDate(null);
		tfReclamante.setText("");

		tfTitular.setText("");
		tfDominio.setText("");
		tfVIN.setText("");
		tfNumPieza.setText("");
		tfpnc.setText("");
		tfMuleto.setText("");

		tfNumeroBDG.setText("");
		dCFBDG.setDate(null);
		dCFSF.setDate(null);
		dCFRF.setDate(null);
		dcFEF.setDate(null);
		tfRemito.setText("");
		dCFEA.setDate(null);
		dCFRA.setDate(null);
	}
	
	protected void verReclamante() {
		int row = tablaPedidos.getSelectedRow();
		if (row >= 0) {
			String id_reclamo = tablaPedidos.getValueAt(row, 0).toString();
			mediador.verRegistrante(id_reclamo);
		}else{
			JOptionPane.showMessageDialog(contentPane,"Seleccione un reclamante primero.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
