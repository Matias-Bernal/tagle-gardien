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
package cliente.GestionarReclamo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.toedter.calendar.JDateChooser;
import common.DTOs.AgenteDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.VehiculoDTO;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class GUIModificarReclamoAgente extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private MediadorReclamo mediador;
	private JPanel contentPane;

	
	private JDateChooser fecha_reclamo;
	private JTextField tfReclamante;
	private JTextField tfVehiculo;
	private JTextArea  tP_Descripcion;

	private JButton btnCancelar;
	private JButton btnModificar;
	private JLabel lblAgente;
	private JLabel lbl_Vehiculo;
	private int limite = 124;
	private JTextField tfEntidad;
	private JButton btn_entidad;
	private JButton btnBuscarOrden;
	private JLabel lblNumeroOrden;
	private JTextField tfNumeroOrden;
	
	private ReclamoDTO reclamo;
	private AgenteDTO agente;
	private VehiculoDTO vehiculo;
	private ReclamanteDTO reclamante;
	private OrdenDTO orden;
	private JButton btn_buscar_reclamante;
	private JButton btn_buscar_Vehivulo;
	private JButton btn_clear_FR;
	
	public GUIModificarReclamoAgente(final MediadorReclamo mediador, ReclamoDTO reclamo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/eddit_reclamo_agente.png")));
		this.mediador = mediador;
		this.reclamo = reclamo;
		initialize();
		SetVisible(true);
		fecha_reclamo.setDate(reclamo.getFecha_reclamo());

		if(reclamo.getRegistrante()!=null){
			agente = mediador.obtenerAgente(reclamo.getRegistrante().getId());
			tfEntidad.setText(agente.getNombre_registrante()+" [ID: "+agente.getId()+"]");
		}
		if(reclamo.getReclamante()!=null){
			reclamante = reclamo.getReclamante();
			tfReclamante.setText(reclamante.getNombre_apellido()+" [ID: "+reclamante.getId()+"]");
		}
		if(reclamo.getVehiculo()!=null){
			vehiculo = reclamo.getVehiculo();
			tfVehiculo.setText(vehiculo.getDominio()+" [ID: "+vehiculo.getId()+"]");
		}
		if(reclamo.getOrden()!=null){
			orden = reclamo.getOrden();
			tfNumeroOrden.setText(orden.getNumero_orden()+" [ID: "+orden.getId()+"]");
		}
		
		tP_Descripcion.setText(reclamo.getDescripcion());
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fecha_reclamo.getDate()!=null)
					fecha_reclamo.setDate(null);
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(308, 10, 25, 20);
		contentPane.add(btn_clear_FR);
	}
	
	private void initialize() {
		setTitle("MODIFICAR RECLAMO AGENTE");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 470, 330);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblFechaReclamo = new JLabel("Fecha Reclamo");
		lblFechaReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaReclamo.setBounds(0, 11, 130, 20);
		contentPane.add(lblFechaReclamo);
		
		fecha_reclamo = new JDateChooser();
		fecha_reclamo.setDate(new Date());
		fecha_reclamo.setBounds(138, 11, 160, 20);
		contentPane.add(fecha_reclamo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(0, 182, 130, 20);
		contentPane.add(lblDescripcion);
		
		tfReclamante = new JTextField();
		tfReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		tfReclamante.setEditable(false);
		tfReclamante.setBounds(138, 99, 190, 20);
		getContentPane().add(tfReclamante);
		tfReclamante.setColumns(10);
		
		btn_buscar_reclamante = new JButton("Buscar");
		btn_buscar_reclamante.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_reclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarReclamante();
			}
		});
		btn_buscar_reclamante.setBounds(334, 99, 110, 20);
		contentPane.add(btn_buscar_reclamante);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(66, 263, 110, 20);
		contentPane.add(btnCancelar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarReclamoAgente();
			}
		});
		btnModificar.setBounds(288, 263, 110, 20);
		contentPane.add(btnModificar);
		
		lblAgente = new JLabel("Agente");
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgente.setBounds(0, 73, 130, 20);
		contentPane.add(lblAgente);
		
		JLabel lblReclamante = new JLabel("Reclamante");
		lblReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamante.setBounds(0, 99, 130, 20);
		contentPane.add(lblReclamante);
		
		tP_Descripcion =  new JTextArea (4,31);
		tP_Descripcion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tP_Descripcion.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				if (tP_Descripcion.getText().length()>=limite){
					e.consume();					
				}
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					modificarReclamoAgente();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {		
			}
		});
		tP_Descripcion.setLineWrap(true);
		tP_Descripcion.setBounds(140, 182, 250, 70);
		contentPane.add(tP_Descripcion);
		
		lbl_Vehiculo = new JLabel("Vehiculo");
		lbl_Vehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Vehiculo.setBounds(0, 125, 130, 20);
		contentPane.add(lbl_Vehiculo);
		
		tfVehiculo = new JTextField();
		tfVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		tfVehiculo.setEditable(false);
		tfVehiculo.setColumns(10);
		tfVehiculo.setBounds(138, 125, 190, 20);
		contentPane.add(tfVehiculo);
		
		btn_buscar_Vehivulo = new JButton("Buscar");
		btn_buscar_Vehivulo.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_Vehivulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarVehiculo();
			}
		});
		btn_buscar_Vehivulo.setBounds(334, 125, 110, 20);
		contentPane.add(btn_buscar_Vehivulo);
		
		tfEntidad = new JTextField();
		tfEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		tfEntidad.setEditable(false);
		tfEntidad.setColumns(10);
		tfEntidad.setBounds(138, 73, 190, 20);
		contentPane.add(tfEntidad);
		
		btn_entidad = new JButton("Buscar");
		btn_entidad.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_entidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarAgente();
			}
		});
		btn_entidad.setBounds(334, 73, 110, 20);
		contentPane.add(btn_entidad);
		
		btnBuscarOrden = new JButton("Buscar");
		btnBuscarOrden.setIcon(new ImageIcon(GUIModificarReclamoAgente.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarOrden();
			}
		});
		btnBuscarOrden.setBounds(334, 151, 110, 20);
		contentPane.add(btnBuscarOrden);
		
		lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(0, 151, 130, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setColumns(10);
		tfNumeroOrden.setBounds(138, 151, 190, 20);
		contentPane.add(tfNumeroOrden);
		
	}
	
	public void setVehiculo(VehiculoDTO vehiculo){
		this.vehiculo = vehiculo;
		tfVehiculo.setText(vehiculo.getDominio()+" [ID: "+vehiculo.getId()+"]");
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
		tfReclamante.setText(reclamante.getNombre_apellido()+" [ID: "+reclamante.getId()+"]");
	}
	public void setAgente(AgenteDTO agente) {
		this.agente = agente;
		tfEntidad.setText(agente.getNombre_registrante()+" [ID: "+agente.getId()+"]");		
	
	}
	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
		tfNumeroOrden.setText(orden.getNumero_orden()+" [ID: "+orden.getId()+"]");
	}
	
	protected void modificarReclamoAgente() {
		if (agente == null || reclamante == null || vehiculo==null || orden==null || fecha_reclamo.getDate()==null){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			if(mediador.tienePedido(reclamo)){
	    		reclamo.setEstado_reclamo("ABIERTO/CON PEDIDO");
			}else{
	    		reclamo.setEstado_reclamo("ABIERTO/SIN PEDIDO");
			}
			
		    java.sql.Date f_reclamo = new java.sql.Date(fecha_reclamo.getDate().getTime());
			reclamo.setFecha_reclamo(f_reclamo);
			
		    reclamo.setRegistrante(agente);
		    reclamo.setReclamante(reclamante);
		    reclamo.setVehiculo(vehiculo);
		    reclamo.setOrden(orden);
		    reclamo.setDescripcion(tP_Descripcion.getText());
		    
			if (mediador.modificarReclamoAgente(reclamo)){
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