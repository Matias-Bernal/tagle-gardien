package cliente.GestionarPedido;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class GUIModificarPedidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorPedido medidador;
	private JDateChooser fecha_solicitud_pedido;
	private JDateChooser fecha_solicitud_fabrica;
	private JDateChooser fecha_recepcion_fabrica;
	private JTextField tfNumero_Pedido;
	private JLabel lblFechaSolicitudFabrica;
	private JLabel lblFechaRecepcionFabrica;
	private JLabel lblPnc;
	private JTextField tfPNC;
	private JTextField tfReclamo;
	private JButton button_1;
	private JButton button_2;
	
	public GUIModificarPedidoEntidad(final MediadorPedido medidador, String nombre_reclamante, String email, String telefono) {
		this.medidador = medidador;
		initialize();
		SetVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIModificarPedidoEntidad(final MediadorPedido medidador) {
		this.medidador = medidador;		
		initialize();
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("MODIFICAR PEDIDO ENTIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 320);
		getContentPane().setLayout(null);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setBounds(0, 10, 130, 20);
		getContentPane().add(lblNumeroPedido);
		
		tfNumero_Pedido = new JTextField();
		tfNumero_Pedido.setBounds(138, 10, 256, 20);
		getContentPane().add(tfNumero_Pedido);
		tfNumero_Pedido.setColumns(10);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud Pedido");
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitud.setBounds(0, 41, 130, 20);
		getContentPane().add(lblFechaSolicitud);
		
		fecha_solicitud_pedido = new JDateChooser();
		//fecha_solicitud_pedido.setDate(new Date());
		fecha_solicitud_pedido.setBounds(138, 41, 163, 20);
		getContentPane().add(fecha_solicitud_pedido);
		
		lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitudFabrica.setBounds(0, 72, 130, 20);
		getContentPane().add(lblFechaSolicitudFabrica);
		
		fecha_solicitud_fabrica = new JDateChooser();
		//fecha_solicitud_fabrica.setDate(new Date());
		fecha_solicitud_fabrica.setBounds(138, 72, 163, 20);
		getContentPane().add(fecha_solicitud_fabrica);
		
		lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaRecepcionFabrica.setBounds(0, 103, 130, 20);
		getContentPane().add(lblFechaRecepcionFabrica);
		
		fecha_recepcion_fabrica = new JDateChooser();
		//fecha_recepcion_fabrica.setDate(new Date());
		fecha_recepcion_fabrica.setBounds(138, 103, 163, 20);
		getContentPane().add(fecha_recepcion_fabrica);
		
		lblPnc = new JLabel("PNC");
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPnc.setBounds(0, 134, 130, 20);
		getContentPane().add(lblPnc);
		
		tfPNC = new JTextField();
		tfPNC.setBounds(138, 134, 256, 20);
		getContentPane().add(tfPNC);
		tfPNC.setColumns(10);
		
		JLabel lblReclamo = new JLabel("Reclamo");
		lblReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamo.setBounds(0, 165, 130, 20);
		getContentPane().add(lblReclamo);
		
		tfReclamo = new JTextField();
		tfReclamo.setEditable(false);
		tfReclamo.setBounds(138, 165, 163, 20);
		getContentPane().add(tfReclamo);
		tfReclamo.setColumns(10);
		
		Button button = new Button("Buscar");
		button.setBounds(307, 165, 87, 22);
		getContentPane().add(button);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezas.setBounds(0, 196, 130, 20);
		getContentPane().add(lblPiezas);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(138, 196, 163, 20);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setBounds(307, 196, 89, 20);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Quitar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(307, 217, 89, 20);
		getContentPane().add(btnNewButton_1);
		
		button_1 = new JButton("Cancelar");
		button_1.setBounds(80, 248, 110, 20);
		getContentPane().add(button_1);
		
		button_2 = new JButton("Crear");
		button_2.setBounds(218, 248, 110, 20);
		getContentPane().add(button_2);
		
	}
		
	public void SetVisible(boolean visible){
	}
}
