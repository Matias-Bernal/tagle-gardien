package cliente.GestionOrden;

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

public class GUIAltaOrden extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MediadorOrden mediador;
	private JDateChooser fecha_apertura;
	private JTextField tfNumero_Orden;
	private JButton btnCancelar;
	private JButton btnCrear;
		
	public GUIAltaOrden(final MediadorOrden medidador, String nombre_reclamante, String email, String telefono) {
		this.mediador = medidador;
		initialize();
		SetVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIAltaOrden(final MediadorOrden medidador) {
		this.mediador = medidador;		
		initialize();
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("AGREGAR ORDEN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 420, 190);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(0, 10, 130, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumero_Orden = new JTextField();
		tfNumero_Orden.setBounds(138, 10, 256, 20);
		contentPane.add(tfNumero_Orden);
		tfNumero_Orden.setColumns(10);
		
		JLabel lblFechaApertura = new JLabel("Fecha Apertura");
		lblFechaApertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaApertura.setBounds(0, 41, 130, 20);
		contentPane.add(lblFechaApertura);
		
		fecha_apertura = new JDateChooser();
		fecha_apertura.setDate(new Date());
		fecha_apertura.setBounds(138, 41, 163, 20);
		contentPane.add(fecha_apertura);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(96, 91, 110, 20);
		contentPane.add(btnCancelar);
		
		btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaOrden();
			}
		});
		btnCrear.setBounds(234, 91, 110, 20);
		contentPane.add(btnCrear);
		
	}
		
	protected void nuevaOrden() {
		if (tfNumero_Orden.getText().isEmpty()){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(fecha_apertura.getDate());
		    java.sql.Date fechaApertura = new java.sql.Date(fecha_apertura.getDate().getTime());
		    
			if (mediador.nuevaOrden(fechaApertura, tfNumero_Orden.getText())){
				JOptionPane.showMessageDialog(contentPane,"Orden Agregada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
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