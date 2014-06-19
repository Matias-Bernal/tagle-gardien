package cliente.GestionOrden;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import common.DTOs.OrdenDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RecursoDTO;

import javax.swing.JComboBox;

public class GUIModificarOrden extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MediadorOrden mediador;
	private JDateChooser fecha_apertura;
	private JDateChooser fecha_cierre;
	private JTextField tfNumero_Orden;
	private JButton btnCancelar;
	private JButton btnModificar;
	
	private JComboBox<String> cbEstadoOrden;
	private Vector<String> tiposEstados;

	private OrdenDTO orden;
	private RecursoDTO recurso;
	private JLabel lblFcierre;
	private JTextField tfRecurso;
	
	public GUIModificarOrden(final MediadorOrden medidador, OrdenDTO orden) {
		this.orden = orden;
		this.mediador = medidador;
		cargarDatos();
		initialize();
		tfNumero_Orden.setText(orden.getNumero_orden());
		fecha_apertura.setDate(orden.getFecha_apertura());
		if (orden.getFecha_cierre()!=null)
			fecha_cierre.setDate(orden.getFecha_cierre());
		cbEstadoOrden.setSelectedItem(orden.getEstado());
		if (orden.getRecurso()!=null)
			tfRecurso.setText(orden.getRecurso().getNumero_recurso());
		ReclamoDTO reclamo = mediador.obtenerReclamo(orden.getId());
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("MODIFICAR ORDEN");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 480, 250);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		JLabel lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(0, 10, 159, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumero_Orden = new JTextField();
		tfNumero_Orden.setBounds(169, 10, 281, 20);
		contentPane.add(tfNumero_Orden);
		tfNumero_Orden.setColumns(10);
		
		JLabel lblFechaApertura = new JLabel("Fecha Apertura");
		lblFechaApertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaApertura.setBounds(0, 41, 159, 20);
		contentPane.add(lblFechaApertura);
		
		fecha_apertura = new JDateChooser();
		fecha_apertura.setDate(new Date());
		fecha_apertura.setBounds(169, 41, 163, 20);
		contentPane.add(fecha_apertura);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(113, 179, 110, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarOrden();
			}
		});
		btnModificar.setBounds(262, 179, 110, 20);
		contentPane.add(btnModificar);
		
		lblFcierre = new JLabel("Fecha Cierre");
		lblFcierre.setHorizontalAlignment(SwingConstants.CENTER);
		lblFcierre.setBounds(0, 72, 159, 20);
		contentPane.add(lblFcierre);
		
		fecha_cierre = new JDateChooser();
		fecha_cierre.setBounds(169, 72, 163, 20);
		contentPane.add(fecha_cierre);
		
		JLabel lblEstado = new JLabel("Estado Orden");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(0, 102, 159, 20);
		contentPane.add(lblEstado);
		
		cbEstadoOrden = new JComboBox<String>();
		cbEstadoOrden.setModel(new DefaultComboBoxModel<String>(tiposEstados));
		cbEstadoOrden.setBounds(169, 103, 160, 20);
		contentPane.add(cbEstadoOrden);
		
		JLabel label_1 = new JLabel("Recurso");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 135, 159, 20);
		contentPane.add(label_1);
		
		tfRecurso = new JTextField();
		tfRecurso.setEditable(false);
		tfRecurso.setColumns(10);
		tfRecurso.setBounds(169, 135, 160, 20);
		contentPane.add(tfRecurso);
		
		JButton btnBuscarRecurso = new JButton("Buscar");
		btnBuscarRecurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarRecursos();
			}
		});
		btnBuscarRecurso.setBounds(335, 133, 115, 22);
		contentPane.add(btnBuscarRecurso);
		
	}
		
	protected void modificarOrden() {
		
		boolean num_orden = tfNumero_Orden.getText().isEmpty();
		boolean f_apertura = fecha_apertura.getDate()==null;
		boolean f_cierre = fecha_cierre.getDate()==null;
		boolean b_recurso = tfRecurso.getText().isEmpty();
		
		if (num_orden || f_apertura){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{ // ORDEN Y FECHA APERTURA EXISTEN
			if ((!f_cierre && b_recurso)){
					//CON FECHA CIERRE PERO SIN RECURSO
					JOptionPane.showMessageDialog(contentPane,"Falta el Recurso. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}else{
				if (f_cierre && !b_recurso){
						//SOLO ACTUALIZA EL RECURSO
						orden.setEstado("ABIERTA/CON RECURSO");
						orden.setRecurso(recurso);
						
					}else{
						SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
						String fecha = format2.format(fecha_cierre.getDate());
						java.sql.Date fechacierre = new java.sql.Date(fecha_cierre.getDate().getTime());
						orden.setFecha_cierre(fechacierre);
						
						orden.setEstado("CERRADA");
						orden.setRecurso(recurso);
						
					}
					if (mediador.modificarOrden(orden)){
						JOptionPane.showMessageDialog(contentPane,"Orden Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
						mediador.actualizarDatosGestion();
						dispose();
					}else{
						JOptionPane.showMessageDialog(contentPane,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
					}
			}
		}
	}

	public void SetVisible(boolean visible){
		contentPane.setVisible(visible);
	}
	
	public void cargarDatos(){
		
		tiposEstados = new Vector<String>();
		tiposEstados.add("SIN RECLAMO");
		tiposEstados.add("SIN PEDIDO");
		tiposEstados.add("ABIERTA/SIN RECURSO");
		tiposEstados.add("ABIERTA/CON RECURSO");
		tiposEstados.add("CERRADA");
	}

	public OrdenDTO getOrden() {
		return orden;
	}

	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
		tfNumero_Orden.setText(orden.getNumero_orden());
	}

	public void setRecurso(RecursoDTO recurso) {
		this.recurso = recurso;
		tfRecurso.setText(recurso.getNumero_recurso()+" [ID: "+recurso.getId()+"]");
	}
}
