package cliente.GestionarReclamo;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import cliente.utils.JPanel_Whit_Image;

import com.toedter.calendar.JDateChooser;
import comun.DTOs.SolicitudDTO;

public class GUINuevoReclamo extends JFrame {

	private static final long serialVersionUID = 1L;
	protected MediadorReclamo mediador;
	private JPanel contentPane;
	private JDateChooser dCFReclamo;
	private JButton btnGuardar;
	
	private SolicitudDTO solicitud;
	private JTextArea tADescripcionSolicitud;
	private JButton btnCancelar;
	private JTextField tFIdSolicitud;

	public GUINuevoReclamo(MediadorReclamo mediador, SolicitudDTO solicitud) {
		this.mediador = mediador;
		this.solicitud = solicitud;
		initialize();
		cargarDatos();
	}


	private void initialize() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUINuevoReclamo.class.getResource("/cliente/Recursos/Iconos/new_reclamo.png")));
		setTitle("NUEVO RECLAMO");
		contentPane = new JPanel_Whit_Image("/cliente/Recursos/Imagenes/fondo.jpg");
		setContentPane(contentPane);
		setResizable(false);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 251);
		contentPane.setLayout(null);
		
		JLabel lblFechaLlegada = new JLabel("FECHA  RECLAMO");
		lblFechaLlegada.setBounds(10, 40, 150, 20);
		contentPane.add(lblFechaLlegada);
		lblFechaLlegada.setHorizontalAlignment(SwingConstants.CENTER);
		
		dCFReclamo = new JDateChooser();
		dCFReclamo.setDate(new Date());
		dCFReclamo.setBounds(170, 40, 175, 20);
		contentPane.add(dCFReclamo);
		dCFReclamo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		tADescripcionSolicitud = new JTextArea();
		tADescripcionSolicitud.setBounds(170, 70, 250, 100);
		contentPane.add(tADescripcionSolicitud);
		tADescripcionSolicitud.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setBounds(10, 70, 150, 20);
		contentPane.add(lblDescripcion);
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(250, 180, 125, 20);
		contentPane.add(btnGuardar);
		btnGuardar.setIcon(new ImageIcon(GUINuevoReclamo.class.getResource("/cliente/Recursos/Iconos/save.png")));
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(50, 180, 125, 20);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setIcon(new ImageIcon(GUINuevoReclamo.class.getResource("/cliente/Recursos/Iconos/cancel.png")));
		
		JLabel lblIdSolicitud = new JLabel("ID SOLICITUD");
		lblIdSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdSolicitud.setBounds(10, 10, 150, 20);
		contentPane.add(lblIdSolicitud);
		
		tFIdSolicitud = new JTextField();
		tFIdSolicitud.setEditable(false);
		tFIdSolicitud.setDisabledTextColor(Color.BLACK);
		tFIdSolicitud.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tFIdSolicitud.setBounds(170, 10, 150, 20);
		contentPane.add(tFIdSolicitud);
		tFIdSolicitud.setColumns(10);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarReclamo();
			}
		});
		
		contentPane.setVisible(true);				
	}

	protected void guardarReclamo() {
		if(!tADescripcionSolicitud.getText().isEmpty() || dCFReclamo.getDate()!=null){
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dCFReclamo.getDate());
		    java.sql.Date fReclamo = new java.sql.Date(dCFReclamo.getDate().getTime());
		    
		    if(mediador.nuevoReclamo(solicitud,fReclamo,tADescripcionSolicitud.getText())){
		    	JOptionPane.showMessageDialog(contentPane,"Reclamo Agregado.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}else{
				JOptionPane.showMessageDialog(contentPane,"Error al agregar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(contentPane,"Faltan Datos. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void cargarDatos() {
		tFIdSolicitud.setText(String.valueOf(solicitud.getId()));
	}

}