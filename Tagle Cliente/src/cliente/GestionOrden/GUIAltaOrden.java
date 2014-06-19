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
import java.awt.Toolkit;
import javax.swing.ImageIcon;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIAltaOrden.class.getResource("/cliente/Resources/Icons/add_orden.png")));
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
		lblNumeroOrden.setBounds(0, 10, 142, 20);
		contentPane.add(lblNumeroOrden);
		
		tfNumero_Orden = new JTextField();
		tfNumero_Orden.setBounds(148, 10, 256, 20);
		contentPane.add(tfNumero_Orden);
		tfNumero_Orden.setColumns(10);
		
		JLabel lblFechaApertura = new JLabel("Fecha Apertura");
		lblFechaApertura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaApertura.setBounds(0, 41, 142, 20);
		contentPane.add(lblFechaApertura);
		
		fecha_apertura = new JDateChooser();
		fecha_apertura.setDate(new Date());
		fecha_apertura.setBounds(148, 41, 163, 20);
		contentPane.add(fecha_apertura);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIAltaOrden.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(42, 91, 110, 20);
		contentPane.add(btnCancelar);
		
		btnCrear = new JButton("Crear");
		btnCrear.setIcon(new ImageIcon(GUIAltaOrden.class.getResource("/cliente/Resources/Icons/add.png")));
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevaOrden();
			}
		});
		btnCrear.setBounds(262, 91, 110, 20);
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