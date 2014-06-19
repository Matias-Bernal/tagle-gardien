package cliente.GestionarDevoluviones;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.Pedido_PiezaDTO;

public class GUIAltaDevoluciones extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private MediadorDevoluciones mediador;
	private JPanel contentPane;

	
	private JDateChooser dp_fecha_devolucion;
	private JTextField tfnum_remito;
	private JTextField tftransporte;
	private JTextField tfnum_retiro;
	
	private JButton button_1;
	private JButton button_2;
	private JLabel lblPedidoPieza;
	private JLabel lbl_Transporte;
		
	private int limite = 124;
	private JTextField tfPedido_Pieza;
	private JButton btn_pedidoPieza;
	
	private Pedido_PiezaDTO pedido_pieza;
	
	public GUIAltaDevoluciones(final MediadorDevoluciones mediador, String remito, String transporte, String retiro) {
		this.mediador = mediador;
		initialize();
		SetVisible(true);
		tfnum_remito.setText(remito);
		tftransporte.setText(transporte);
		tfnum_retiro.setText(retiro);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIAltaDevoluciones(final MediadorDevoluciones mediador) {
		setResizable(false);
		this.mediador = mediador;		
		initialize();
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("AGREGAR DEVOLUCION PIEZA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 425, 235);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblFechaDevolucion = new JLabel("Fecha Devolucion");
		lblFechaDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaDevolucion.setBounds(0, 11, 130, 20);
		contentPane.add(lblFechaDevolucion);
		
		dp_fecha_devolucion = new JDateChooser();
		dp_fecha_devolucion.setDate(new Date());
		dp_fecha_devolucion.setBounds(138, 11, 160, 20);
		contentPane.add(dp_fecha_devolucion);
		
		tfnum_remito = new JTextField();
		tfnum_remito.setHorizontalAlignment(SwingConstants.CENTER);
		tfnum_remito.setBounds(138, 68, 190, 20);
		getContentPane().add(tfnum_remito);
		tfnum_remito.setColumns(10);
		
		button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(80, 165, 110, 20);
		contentPane.add(button_1);
		
		button_2 = new JButton("Crear");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaDevolucionPieza();
			}
		});
		button_2.setBounds(218, 165, 110, 20);
		contentPane.add(button_2);
		
		lblPedidoPieza = new JLabel("Pedido-Pieza");
		lblPedidoPieza.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedidoPieza.setBounds(0, 42, 130, 20);
		contentPane.add(lblPedidoPieza);
		
		JLabel lblnumero_remito = new JLabel("Numero Remito");
		lblnumero_remito.setHorizontalAlignment(SwingConstants.CENTER);
		lblnumero_remito.setBounds(0, 68, 130, 20);
		contentPane.add(lblnumero_remito);
		
		lbl_Transporte = new JLabel("Transporte");
		lbl_Transporte.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Transporte.setBounds(0, 94, 130, 20);
		contentPane.add(lbl_Transporte);
		
		tftransporte = new JTextField();
		tftransporte.setHorizontalAlignment(SwingConstants.CENTER);
		tftransporte.setColumns(10);
		tftransporte.setBounds(138, 94, 190, 20);
		contentPane.add(tftransporte);
			
		tfPedido_Pieza = new JTextField();
		tfPedido_Pieza.setHorizontalAlignment(SwingConstants.CENTER);
		tfPedido_Pieza.setEditable(false);
		tfPedido_Pieza.setColumns(10);
		tfPedido_Pieza.setBounds(138, 42, 190, 20);
		contentPane.add(tfPedido_Pieza);
		
		btn_pedidoPieza = new JButton("Buscar");
		btn_pedidoPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarPedidoPieza();
			}
		});
		btn_pedidoPieza.setBounds(334, 42, 75, 20);
		contentPane.add(btn_pedidoPieza);
		
		JLabel lblNum_retiro = new JLabel("Numero Retiro");
		lblNum_retiro.setHorizontalAlignment(SwingConstants.CENTER);
		lblNum_retiro.setBounds(0, 120, 130, 20);
		contentPane.add(lblNum_retiro);
		
		tfnum_retiro = new JTextField();
		tfnum_retiro.setHorizontalAlignment(SwingConstants.CENTER);
		tfnum_retiro.setColumns(10);
		tfnum_retiro.setBounds(138, 121, 190, 20);
		contentPane.add(tfnum_retiro);
		
	}
	
	protected void nuevaDevolucionPieza() {
		if (pedido_pieza == null || tfnum_remito.getText().isEmpty() || tfnum_retiro.getText().isEmpty() || tftransporte.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dp_fecha_devolucion.getDate());
		    java.sql.Date sqlDate = new java.sql.Date(dp_fecha_devolucion.getDate().getTime());
		    
			if (mediador.nuevaDevolucion(pedido_pieza,sqlDate,tfnum_remito.getText(),tftransporte.getText(),tfnum_retiro.getText())){
				JOptionPane.showMessageDialog(contentPane,"Reclamo Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}
}