package cliente.GestionarRecurso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import common.DTOs.RecursoDTO;

public class GUIModificarRecurso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MediadorRecurso mediador;
	private JDateChooser fecha_recurso;
	private JTextField tfNumero_Recurso;
//	private JTextField tfPedido_Pieza;
//  private PedidoPiezaDTO pedidoPieza;
	private JButton btnCancelar;
	private JButton btnModificar;
	private RecursoDTO recurso;
	
	public GUIModificarRecurso(final MediadorRecurso mediador, RecursoDTO recurso) {
		this.mediador = mediador;
		this.recurso = recurso;
		initialize();
		tfNumero_Recurso.setText(this.recurso.getNumero_recurso());
		if (recurso.getFecha_recurso()!=null){
			java.util.Date fecha = new java.util.Date(recurso.getFecha_recurso().getTime());
			this.fecha_recurso.setDate(fecha);
		}	
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("MODIFICAR RECURSO");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 410, 185);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JLabel lblNumeroPedido = new JLabel("Numero Recurso");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setBounds(0, 10, 130, 20);
		contentPane.add(lblNumeroPedido);
		
		tfNumero_Recurso = new JTextField();
		tfNumero_Recurso.setBounds(138, 10, 256, 20);
		contentPane.add(tfNumero_Recurso);
		tfNumero_Recurso.setColumns(10);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Recurso");
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitud.setBounds(0, 41, 130, 20);
		contentPane.add(lblFechaSolicitud);
		
		fecha_recurso = new JDateChooser();
		fecha_recurso.setDate(new java.util.Date());
		fecha_recurso.setBounds(138, 41, 163, 20);
		contentPane.add(fecha_recurso);
		
//		JLabel lblReclamo = new JLabel("Reclamo");
//		lblReclamo.setVisible(false);
//		lblReclamo.setHorizontalAlignment(SwingConstants.CENTER);
//		lblReclamo.setBounds(0, 67, 130, 20);
//		contentPane.add(lblReclamo);
//		
//		tfPedido_Pieza = new JTextField();
//		tfPedido_Pieza.setVisible(false);
//		tfPedido_Pieza.setEditable(false);
//		tfPedido_Pieza.setBounds(138, 67, 163, 20);
//		contentPane.add(tfPedido_Pieza);
//		tfPedido_Pieza.setColumns(10);
//		
//		Button btnBuscarPedidoPieza = new Button("Buscar");
//		btnBuscarPedidoPieza.setVisible(false);
//		btnBuscarPedidoPieza.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				buscarPedidoPieza();
//			}
//		});
//		btnBuscarPedidoPieza.setBounds(307, 67, 87, 22);
//		getContentPane().add(btnBuscarPedidoPieza);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(85, 114, 110, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(223, 114, 110, 20);
		contentPane.add(btnModificar);
		
	}
		
	protected void buscarPedidoPieza() {
		// TODO Auto-generated method stub
		
	}

	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}
	
	public void modificar(){
		if (tfNumero_Recurso.getText().isEmpty() ||  fecha_recurso.getDate()==null){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(fecha_recurso.getDate());
		    java.sql.Date fechaRecurso = new java.sql.Date(fecha_recurso.getDate().getTime());
		    
		    recurso.setFecha_recurso(fechaRecurso);
		    recurso.setNumero_recurso(tfNumero_Recurso.getText());
		    
			if (mediador.modificarRecuroso(recurso)){
				JOptionPane.showMessageDialog(contentPane,"Recurso Modificado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				mediador.actualizarDatosGestion();
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
