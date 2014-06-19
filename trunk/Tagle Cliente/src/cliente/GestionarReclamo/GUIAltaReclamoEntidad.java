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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import common.DTOs.EntidadDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.VehiculoDTO;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class GUIAltaReclamoEntidad extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private MediadorReclamo mediador;
	private JPanel contentPane;

	
	private JDateChooser dp_fecha_reclamo;
	private JTextField tfReclamo;
	private JTextField tfVehiculo;
	private JTextArea  tP_Descripcion;

	private JButton btnCancelar;
	private JButton btnCrear;
	private JLabel lblEntidad;
	private JLabel lbl_Vehiculo;
	private int limite = 124;
	private JTextField tfEntidad;
	private JButton btn_entidad;
	private JButton btnBuscarOrden;
	private JLabel lblNumeroOrden;
	private JTextField tfNumeroOrden;
	private JCheckBox chckbxInmovilizado;
	private JCheckBox chckbxPeligroso;
	
	private EntidadDTO entidad;
	private VehiculoDTO vehiculo;
	private ReclamanteDTO reclamante;
	private OrdenDTO orden;
	private JButton btn_buscar_reclamante;
	private JButton btn_buscar_Vehivulo;
	private JButton btn_clear_FR;
	
	public GUIAltaReclamoEntidad(final MediadorReclamo mediador, String reclamo, String entidad, String descripcion) {
		this.mediador = mediador;
		initialize();
		SetVisible(true);
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIAltaReclamoEntidad(final MediadorReclamo mediador) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/add_reclamo_entidad.png")));
		this.mediador = mediador;		
		initialize();
		SetVisible(true);
	}
	
	private void initialize() {
		setTitle("AGREGAR RECLAMO ENTIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 470, 325);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblFechaReclamo = new JLabel("Fecha Reclamo");
		lblFechaReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaReclamo.setBounds(0, 11, 130, 20);
		contentPane.add(lblFechaReclamo);
		
		dp_fecha_reclamo = new JDateChooser();
		dp_fecha_reclamo.setDate(new Date());
		dp_fecha_reclamo.setBounds(138, 11, 160, 20);
		contentPane.add(dp_fecha_reclamo);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcion.setBounds(0, 185, 130, 20);
		contentPane.add(lblDescripcion);
		
		tfReclamo = new JTextField();
		tfReclamo.setHorizontalAlignment(SwingConstants.CENTER);
		tfReclamo.setEditable(false);
		tfReclamo.setBounds(138, 68, 190, 20);
		getContentPane().add(tfReclamo);
		tfReclamo.setColumns(10);
		
		btn_buscar_reclamante = new JButton("Buscar");
		btn_buscar_reclamante.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_reclamante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarReclamante();
			}
		});
		btn_buscar_reclamante.setBounds(334, 68, 110, 20);
		contentPane.add(btn_buscar_reclamante);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(68, 265, 110, 20);
		contentPane.add(btnCancelar);
		
		btnCrear = new JButton("Crear");
		btnCrear.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/add.png")));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoReclamoEntidad();
			}
		});
		btnCrear.setBounds(288, 265, 110, 20);
		contentPane.add(btnCrear);
		
		lblEntidad = new JLabel("Entidad");
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntidad.setBounds(0, 42, 130, 20);
		contentPane.add(lblEntidad);
		
		JLabel lblReclamante = new JLabel("Reclamante");
		lblReclamante.setHorizontalAlignment(SwingConstants.CENTER);
		lblReclamante.setBounds(0, 68, 130, 20);
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
					nuevoReclamoEntidad();
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {		
			}
		});
		tP_Descripcion.setLineWrap(true);
		tP_Descripcion.setBounds(140, 185, 250, 70);
		contentPane.add(tP_Descripcion);
		
		lbl_Vehiculo = new JLabel("Vehiculo");
		lbl_Vehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Vehiculo.setBounds(0, 94, 130, 20);
		contentPane.add(lbl_Vehiculo);
		
		tfVehiculo = new JTextField();
		tfVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
		tfVehiculo.setEditable(false);
		tfVehiculo.setColumns(10);
		tfVehiculo.setBounds(138, 94, 190, 20);
		contentPane.add(tfVehiculo);
		
		btn_buscar_Vehivulo = new JButton("Buscar");
		btn_buscar_Vehivulo.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_buscar_Vehivulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarVehiculo();
			}
		});
		btn_buscar_Vehivulo.setBounds(334, 94, 110, 20);
		contentPane.add(btn_buscar_Vehivulo);
		
		chckbxPeligroso = new JCheckBox("Peligroso");
		chckbxPeligroso.setBounds(138, 121, 102, 23);
		contentPane.add(chckbxPeligroso);
		
		chckbxInmovilizado = new JCheckBox("Inmovilizado");
		chckbxInmovilizado.setBounds(242, 121, 102, 23);
		contentPane.add(chckbxInmovilizado);
		
		tfEntidad = new JTextField();
		tfEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		tfEntidad.setEditable(false);
		tfEntidad.setColumns(10);
		tfEntidad.setBounds(138, 42, 190, 20);
		contentPane.add(tfEntidad);
		
		btn_entidad = new JButton("Buscar");
		btn_entidad.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btn_entidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarEntidad();
			}
		});
		btn_entidad.setBounds(334, 42, 110, 20);
		contentPane.add(btn_entidad);
		
		btnBuscarOrden = new JButton("Buscar");
		btnBuscarOrden.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/1find.png")));
		btnBuscarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediador.buscarOrden();
			}
		});
		btnBuscarOrden.setBounds(334, 150, 110, 20);
		contentPane.add(btnBuscarOrden);
		
		lblNumeroOrden = new JLabel("Numero Orden");
		lblNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroOrden.setBounds(0, 150, 130, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setHorizontalAlignment(SwingConstants.CENTER);
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setColumns(10);
		tfNumeroOrden.setBounds(138, 150, 190, 20);
		contentPane.add(tfNumeroOrden);
		
		btn_clear_FR = new JButton("");
		btn_clear_FR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dp_fecha_reclamo.getDate()!=null)
					dp_fecha_reclamo.setDate(null);
			}
		});
		btn_clear_FR.setIcon(new ImageIcon(GUIAltaReclamoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FR.setBounds(308, 10, 25, 20);
		contentPane.add(btn_clear_FR);
		
	}
	
	public void setVehiculo(VehiculoDTO vehiculo){
		this.vehiculo = vehiculo;
		tfVehiculo.setText(vehiculo.getDominio()+" [ID: "+vehiculo.getId()+"]");
	}
	public void setReclamante(ReclamanteDTO reclamante) {
		this.reclamante = reclamante;
		tfReclamo.setText(reclamante.getNombre_apellido()+" [ID: "+reclamante.getId()+"]");
	}
	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
		tfEntidad.setText(entidad.getNombre_registrante()+" [ID: "+entidad.getId()+"]");		
	
	}
	public void setOrden(OrdenDTO orden) {
		this.orden = orden;
		tfNumeroOrden.setText(orden.getNumero_orden()+" [ID: "+orden.getId()+"]");
	}
	
	protected void nuevoReclamoEntidad() {
		if (entidad == null || reclamante == null || vehiculo==null || orden==null || dp_fecha_reclamo.getDate()==null){
			JOptionPane.showMessageDialog(contentPane,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{
			
			SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
			String fecha = format2.format(dp_fecha_reclamo.getDate());
		    java.sql.Date sqlDate = new java.sql.Date(dp_fecha_reclamo.getDate().getTime());
		    
			if (mediador.nuevoReclamoEntidad(sqlDate, entidad.getId(), reclamante.getId(), vehiculo.getId(), orden.getId(),chckbxPeligroso.isSelected(),chckbxInmovilizado.isSelected(),tP_Descripcion.getText())){
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