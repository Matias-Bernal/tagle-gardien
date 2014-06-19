package cliente.GestionarPedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class GUIGestionPedidoEntidad extends JFrame {
	private static final long serialVersionUID = 1L;

	protected MediadorPedido mediador;
	
	private JPanel contentPane;
	private JButton btnModificar;
	private JButton btnEliminar;
	private Object[][] datosTabla;
	private String[] nombreColumnas;
	private JTable tableRegistrantes;
	
	private JButton btnAgregar;
	private JButton btnImprimir;
	private JButton btnVolver;
	private JButton btnListarRegistrantes;
	private String[] tiposRegistrantes;
	private JLabel label;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnVer;

	public GUIGestionPedidoEntidad(MediadorPedido mediadorRegistrante) {
		this.mediador = mediadorRegistrante;
		tiposRegistrantes = new String[] {"Entidad", "Agente"};
		initialize();

	}
	
	public void initialize(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("GESTIONAR PEDIDO ENTIDAD");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(939, 70, 315, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(939, 104, 315, 23);
		contentPane.add(btnEliminar);
		
		datosTabla =  new Object[][]{
				{new Long(0L), "#1271239","#121231", "#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231", "#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231", "#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231", "#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231", "#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231","#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231","#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231","#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231","#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231","#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239","#121231","#234234", "12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				{new Long(0L), "#1271239", "#121231","#234234","12/12/1986", "18/07/1983", "06/02/2006", "En Fabrica", "123,45" },
				
		};
		nombreColumnas = new String []{
			"ID Pedido", "Numero Pedido", "Pieza","ID Reclamo", "Fecha Solicitud Pedido","Fecha Solicitud Fabrica", "Fecha Recepcion Fabrica", "Estado Pedido", "PNC"
		};
		
		DefaultTableModel modelo = new DefaultTableModel(datosTabla, nombreColumnas);
		tableRegistrantes = new JTable(modelo) {
			boolean[] columnEditables = new boolean[] {
				false, false, false,false, false,false,false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableRegistrantes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRegistrantes.setBounds(0, 0, 765, 320);
		
		JScrollPane scrollPaneTabla = new JScrollPane(tableRegistrantes);
		scrollPaneTabla.setBounds(10, 222, 1244, 402);
		contentPane.add(scrollPaneTabla);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//mediador.altaRegistrante(tFnombre_registrante.getText(), (String) comboBox.getSelectedItem());
			}
		});
		btnAgregar.setBounds(939, 35, 315, 23);
		contentPane.add(btnAgregar);
		
		btnImprimir = new JButton("Imprimir");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImprimir.setBounds(506, 634, 150, 23);
		contentPane.add(btnImprimir);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBounds(666, 634, 150, 23);
		contentPane.add(btnVolver);
		
		btnListarRegistrantes = new JButton("Listar Pedidos");
		btnListarRegistrantes.setBounds(939, 138, 315, 23);
		contentPane.add(btnListarRegistrantes);
		
		label = new JLabel("Numero Pedido");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 10, 130, 20);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(150, 10, 256, 20);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Fecha Solicitud Pedido");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 35, 130, 20);
		contentPane.add(label_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(150, 35, 128, 20);
		contentPane.add(dateChooser);
		
		JLabel label_2 = new JLabel("Fecha Solicitud Fabrica");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 60, 130, 20);
		contentPane.add(label_2);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(150, 60, 128, 20);
		contentPane.add(dateChooser_1);
		
		JLabel label_3 = new JLabel("Fecha Recepcion Fabrica");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 85, 130, 20);
		contentPane.add(label_3);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(150, 85, 128, 20);
		contentPane.add(dateChooser_2);
		
		JLabel label_4 = new JLabel("PNC");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 110, 130, 20);
		contentPane.add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 110, 256, 20);
		contentPane.add(textField_1);
		
		JLabel label_5 = new JLabel("Reclamo");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 135, 130, 20);
		contentPane.add(label_5);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(150, 135, 128, 20);
		contentPane.add(textField_2);
		
		btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVer.setBounds(346, 634, 150, 23);
		contentPane.add(btnVer);
		
		JButton btnBuscarReclamo = new JButton("Buscar");
		btnBuscarReclamo.setBounds(290, 135, 116, 22);
		contentPane.add(btnBuscarReclamo);
		
		JLabel lblRegistrante = new JLabel("Registrante");
		lblRegistrante.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrante.setBounds(10, 166, 130, 20);
		contentPane.add(lblRegistrante);
		


	}
}