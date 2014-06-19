
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
package cliente.GestionarNotificacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class GUINotificacion extends JFrame {

	private MediadorEjecutarNotificacion mediador;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea tA_textoNotificacion;
	private JButton btnPosponer;
	private JButton btnCompletada;

	public GUINotificacion(MediadorEjecutarNotificacion mediador,String titulo, String text) {
		this.setMediador(mediador);
		initialize();
		setVisible(false);
		tA_textoNotificacion.setText(text);
		setTitle(titulo);
	}
	private void initialize(){
		setResizable(false);
		setType(Type.POPUP);
		setBounds(100, 100, 375, 270);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUINotificacion.class.getResource("/cliente/Resources/Icons/notificaciones.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				posponer();
			}
		});
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnCompletada = new JButton("COMPLETADA");
		btnCompletada.setIcon(new ImageIcon(GUINotificacion.class.getResource("/cliente/Resources/Icons/Check.png")));
		btnCompletada.setBounds(10, 210, 145, 25);
		btnCompletada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				completada();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnCompletada);
		
		btnPosponer = new JButton("POSPONER");
		btnPosponer.setIcon(new ImageIcon(GUINotificacion.class.getResource("/cliente/Resources/Icons/posponer.png")));
		btnPosponer.setBounds(214, 210, 145, 25);
		btnPosponer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posponer();
			}
		});
		contentPane.add(btnPosponer);
		
		tA_textoNotificacion = new JTextArea();
		tA_textoNotificacion.setEditable(false);
		tA_textoNotificacion.setLineWrap(true);
		tA_textoNotificacion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tA_textoNotificacion.setBounds(10, 11, 350, 186);
		contentPane.add(tA_textoNotificacion);
	}
	
	private void posponer(){
		setVisible(false);
		mediador.posponer();
	}
	
	private void completada(){
		setVisible(false);
		mediador.completada();
	}
	
	public MediadorEjecutarNotificacion getMediador() {
		return mediador;
	}
	public void setMediador(MediadorEjecutarNotificacion mediador) {
		this.mediador = mediador;
	}
}
