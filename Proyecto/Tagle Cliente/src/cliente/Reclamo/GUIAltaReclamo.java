package cliente.Reclamo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.EtchedBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIAltaReclamo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField tfEmail_E;
	private JTextField tfNombreReclamante_E;
	private JTextField tfDni_E;
	private JTextField tfNombreTitular_E;
	private JTextField tfDominio_E;
	private JTextField tfVin_E;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIAltaReclamo window = new GUIAltaReclamo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIAltaReclamo() {
		setResizable(false);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setSize(new Dimension(111, 0));
		tabbedPane.setBounds(10, 11, 1015, 450);
		getContentPane().add(tabbedPane);
		
		JPanel entidad = new JPanel();
		tabbedPane.addTab("ENTIDAD", null, entidad, null);
		entidad.setLayout(null);
		
		JPanel selecEntidad = new JPanel();
		selecEntidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		selecEntidad.setBounds(10, 11, 490, 42);
		entidad.add(selecEntidad);
		selecEntidad.setLayout(null);
		
		JLabel lblEntidad = new JLabel("ENTIDAD");
		lblEntidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntidad.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEntidad.setBounds(10, 11, 140, 19);
		selecEntidad.add(lblEntidad);
		
		Choice choiceEntidad = new Choice();
		choiceEntidad.setBounds(160, 11, 200, 20);
		selecEntidad.add(choiceEntidad);
		
		JPanel selectReclamanteEntidad = new JPanel();
		selectReclamanteEntidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		selectReclamanteEntidad.setLayout(null);
		selectReclamanteEntidad.setBounds(10, 55, 490, 181);
		entidad.add(selectReclamanteEntidad);
		
		JLabel lbnombreReclamante_E = new JLabel("Nombre Del Reclamante");
		lbnombreReclamante_E.setHorizontalAlignment(SwingConstants.CENTER);
		lbnombreReclamante_E.setBounds(10, 11, 139, 20);
		selectReclamanteEntidad.add(lbnombreReclamante_E);
		
		JLabel lblEmail_E = new JLabel("Email");
		lblEmail_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_E.setBounds(10, 42, 139, 20);
		selectReclamanteEntidad.add(lblEmail_E);
		
		tfEmail_E = new JTextField();
		tfEmail_E.setColumns(10);
		tfEmail_E.setBounds(160, 42, 200, 20);
		selectReclamanteEntidad.add(tfEmail_E);
		
		tfNombreReclamante_E = new JTextField();
		tfNombreReclamante_E.setColumns(10);
		tfNombreReclamante_E.setBounds(160, 11, 200, 20);
		selectReclamanteEntidad.add(tfNombreReclamante_E);
		
		JLabel lblDni_E = new JLabel("DNI");
		lblDni_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblDni_E.setBounds(10, 72, 139, 20);
		selectReclamanteEntidad.add(lblDni_E);
		
		JLabel lblTelefonos_E = new JLabel("Telefonos");
		lblTelefonos_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefonos_E.setBounds(10, 103, 139, 20);
		selectReclamanteEntidad.add(lblTelefonos_E);
		
		JComboBox<String> cbTelefonos_E = new JComboBox<String>();
		cbTelefonos_E.setEditable(true);
		cbTelefonos_E.setBounds(160, 103, 120, 20);
		selectReclamanteEntidad.add(cbTelefonos_E);
		
		tfDni_E = new JTextField();
		tfDni_E.setColumns(10);
		tfDni_E.setBounds(160, 72, 120, 20);
		selectReclamanteEntidad.add(tfDni_E);
		
		JButton btnAgregarTelefono_E = new JButton("Agregar");
		btnAgregarTelefono_E.setBounds(290, 102, 90, 23);
		selectReclamanteEntidad.add(btnAgregarTelefono_E);
		
		JButton btnQuitarTelefono_E = new JButton("Quitar");
		btnQuitarTelefono_E.setBounds(290, 129, 90, 23);
		selectReclamanteEntidad.add(btnQuitarTelefono_E);
		
		JRadioButton rbCelular_E = new JRadioButton("Celular");
		rbCelular_E.setBounds(160, 129, 120, 23);
		selectReclamanteEntidad.add(rbCelular_E);
		
		JRadioButton rbFijo_E = new JRadioButton("Fijo");
		rbFijo_E.setBounds(160, 155, 120, 23);
		selectReclamanteEntidad.add(rbFijo_E);
		
		JButton btnBuscarReclamante_E = new JButton("Buscar");
		btnBuscarReclamante_E.setBounds(366, 10, 89, 23);
		selectReclamanteEntidad.add(btnBuscarReclamante_E);
		
		JPanel selectVehiculoEntidad = new JPanel();
		selectVehiculoEntidad.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		selectVehiculoEntidad.setBounds(10, 241, 490, 175);
		entidad.add(selectVehiculoEntidad);
		selectVehiculoEntidad.setLayout(null);
		
		JLabel lblNombreTitular_E = new JLabel("Nombre del Titular");
		lblNombreTitular_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreTitular_E.setBounds(10, 12, 130, 20);
		selectVehiculoEntidad.add(lblNombreTitular_E);
		
		tfNombreTitular_E = new JTextField();
		tfNombreTitular_E.setColumns(10);
		tfNombreTitular_E.setBounds(160, 12, 200, 20);
		selectVehiculoEntidad.add(tfNombreTitular_E);
		
		tfDominio_E = new JTextField();
		tfDominio_E.setColumns(10);
		tfDominio_E.setBounds(160, 42, 79, 20);
		selectVehiculoEntidad.add(tfDominio_E);
		
		JLabel lblDominio_E = new JLabel("Dominio");
		lblDominio_E.setHorizontalAlignment(SwingConstants.CENTER);
		lblDominio_E.setBounds(10, 42, 130, 20);
		selectVehiculoEntidad.add(lblDominio_E);
		
		JLabel lvlVin_E = new JLabel("VIN");
		lvlVin_E.setHorizontalAlignment(SwingConstants.CENTER);
		lvlVin_E.setBounds(10, 72, 130, 20);
		selectVehiculoEntidad.add(lvlVin_E);
		
		tfVin_E = new JTextField();
		tfVin_E.setColumns(10);
		tfVin_E.setBounds(160, 73, 155, 20);
		selectVehiculoEntidad.add(tfVin_E);
		
		JLabel label_3 = new JLabel("Marca");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(10, 103, 130, 20);
		selectVehiculoEntidad.add(label_3);
		
		JComboBox<String> cbMarca_E = new JComboBox<String>();
		cbMarca_E.setBounds(160, 103, 155, 20);
		selectVehiculoEntidad.add(cbMarca_E);
		
		JLabel label_4 = new JLabel("Modelo");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(10, 133, 130, 20);
		selectVehiculoEntidad.add(label_4);
		
		JComboBox<String> cbModeloE = new JComboBox<String>();
		cbModeloE.setBounds(160, 133, 155, 20);
		selectVehiculoEntidad.add(cbModeloE);
		
		JButton btnBuscarVehiculo_E = new JButton("Buscar");
		btnBuscarVehiculo_E.setBounds(366, 11, 89, 23);
		selectVehiculoEntidad.add(btnBuscarVehiculo_E);
		
		JCheckBox checkBox = new JCheckBox("PELIGROSO");
		checkBox.setBounds(358, 103, 126, 23);
		selectVehiculoEntidad.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("INMOVILIZADO");
		checkBox_1.setBounds(358, 133, 126, 23);
		selectVehiculoEntidad.add(checkBox_1);
		
		JPanel selectOrden_A = new JPanel();
		selectOrden_A.setBounds(510, 11, 490, 85);
		entidad.add(selectOrden_A);
		selectOrden_A.setLayout(null);
		selectOrden_A.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label_2 = new JLabel("Fecha Apertura");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(10, 42, 130, 20);
		selectOrden_A.add(label_2);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(160, 42, 155, 20);
		selectOrden_A.add(dateChooser_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 11, 200, 20);
		selectOrden_A.add(textField_1);
		
		JLabel label_10 = new JLabel("Numero Orden");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(10, 12, 130, 20);
		selectOrden_A.add(label_10);
		
		JButton button_2 = new JButton("Buscar");
		button_2.setBounds(366, 10, 89, 23);
		selectOrden_A.add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(510, 99, 490, 121);
		entidad.add(panel);
		panel.setLayout(null);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(160, 11, 160, 20);
		panel.add(dateChooser_2);
		
		JLabel label_11 = new JLabel("Fecha Reclamo");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(10, 11, 130, 20);
		panel.add(label_11);
		
		JLabel label_12 = new JLabel("Descripcion");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(10, 44, 130, 20);
		panel.add(label_12);
		
		JTextArea textArea = new JTextArea(4, 31);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setLineWrap(true);
		textArea.setBounds(160, 42, 256, 72);
		panel.add(textArea);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAgregar.setBounds(600, 303, 130, 23);
		entidad.add(btnAgregar);
		
		JButton btnCancelar = new JButton("Crear");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(780, 303, 130, 23);
		entidad.add(btnCancelar);
				
		JPanel agente = new JPanel();
		tabbedPane.addTab("AGENTE", null, agente, null);
		agente.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 510, 490, 121);
		agente.add(panel_1);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(148, 11, 160, 20);
		panel_1.add(dateChooser);
		
		JLabel label = new JLabel("Fecha Reclamo");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(10, 11, 130, 20);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Descripcion");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 44, 130, 20);
		panel_1.add(label_1);
		
		JTextArea textArea_1 = new JTextArea(4, 31);
		textArea_1.setLineWrap(true);
		textArea_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea_1.setBounds(148, 42, 256, 72);
		panel_1.add(textArea_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(10, 422, 490, 85);
		agente.add(panel_2);
		
		JLabel label_5 = new JLabel("Fecha Apertura");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(10, 42, 130, 20);
		panel_2.add(label_5);
		
		JDateChooser dateChooser_3 = new JDateChooser();
		dateChooser_3.setBounds(160, 42, 155, 20);
		panel_2.add(dateChooser_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(160, 11, 200, 20);
		panel_2.add(textField);
		
		JLabel label_6 = new JLabel("Numero Orden");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(10, 12, 130, 20);
		panel_2.add(label_6);
		
		JButton button = new JButton("Buscar");
		button.setBounds(366, 10, 89, 23);
		panel_2.add(button);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(0, 0, 1010, 422);
		agente.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(10, 11, 490, 42);
		panel_3.add(panel_4);
		
		JLabel lblAgente = new JLabel("AGENTE");
		lblAgente.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAgente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgente.setBounds(10, 11, 140, 19);
		panel_4.add(lblAgente);
		
		Choice choice = new Choice();
		choice.setBounds(160, 11, 200, 20);
		panel_4.add(choice);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(10, 55, 490, 181);
		panel_3.add(panel_5);
		
		JLabel label_8 = new JLabel("Nombre Del Reclamante");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(10, 11, 139, 20);
		panel_5.add(label_8);
		
		JLabel label_9 = new JLabel("Email");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(10, 42, 139, 20);
		panel_5.add(label_9);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(160, 42, 200, 20);
		panel_5.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(160, 11, 200, 20);
		panel_5.add(textField_3);
		
		JLabel label_13 = new JLabel("DNI");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(10, 72, 139, 20);
		panel_5.add(label_13);
		
		JLabel label_14 = new JLabel("Telefonos");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(10, 103, 139, 20);
		panel_5.add(label_14);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEditable(true);
		comboBox.setBounds(160, 103, 120, 20);
		panel_5.add(comboBox);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(160, 72, 120, 20);
		panel_5.add(textField_4);
		
		JButton button_1 = new JButton("Agregar");
		button_1.setBounds(290, 102, 90, 23);
		panel_5.add(button_1);
		
		JButton button_3 = new JButton("Quitar");
		button_3.setBounds(290, 129, 90, 23);
		panel_5.add(button_3);
		
		JRadioButton radioButton = new JRadioButton("Celular");
		radioButton.setBounds(160, 129, 120, 23);
		panel_5.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Fijo");
		radioButton_1.setBounds(160, 155, 120, 23);
		panel_5.add(radioButton_1);
		
		JButton button_4 = new JButton("Buscar");
		button_4.setBounds(366, 10, 89, 23);
		panel_5.add(button_4);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBounds(10, 241, 490, 175);
		panel_3.add(panel_6);
		
		JLabel label_15 = new JLabel("Nombre del Titular");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(10, 12, 130, 20);
		panel_6.add(label_15);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(160, 12, 200, 20);
		panel_6.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(160, 42, 79, 20);
		panel_6.add(textField_6);
		
		JLabel label_16 = new JLabel("Dominio");
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(10, 42, 130, 20);
		panel_6.add(label_16);
		
		JLabel label_17 = new JLabel("VIN");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(10, 72, 130, 20);
		panel_6.add(label_17);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(160, 73, 155, 20);
		panel_6.add(textField_7);
		
		JLabel label_18 = new JLabel("Marca");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(10, 103, 130, 20);
		panel_6.add(label_18);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(160, 103, 155, 20);
		panel_6.add(comboBox_1);
		
		JLabel label_19 = new JLabel("Modelo");
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setBounds(10, 133, 130, 20);
		panel_6.add(label_19);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(160, 133, 155, 20);
		panel_6.add(comboBox_2);
		
		JButton button_5 = new JButton("Buscar");
		button_5.setBounds(366, 11, 89, 23);
		panel_6.add(button_5);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBounds(510, 11, 490, 85);
		panel_3.add(panel_7);
		
		JLabel label_20 = new JLabel("Fecha Apertura");
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setBounds(10, 42, 130, 20);
		panel_7.add(label_20);
		
		JDateChooser dateChooser_4 = new JDateChooser();
		dateChooser_4.setBounds(160, 42, 155, 20);
		panel_7.add(dateChooser_4);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(160, 11, 200, 20);
		panel_7.add(textField_8);
		
		JLabel label_21 = new JLabel("Numero Orden");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setBounds(10, 12, 130, 20);
		panel_7.add(label_21);
		
		JButton button_6 = new JButton("Buscar");
		button_6.setBounds(366, 10, 89, 23);
		panel_7.add(button_6);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_8.setBounds(510, 99, 490, 121);
		panel_3.add(panel_8);
		
		JDateChooser dateChooser_5 = new JDateChooser();
		dateChooser_5.setBounds(160, 11, 160, 20);
		panel_8.add(dateChooser_5);
		
		JLabel label_22 = new JLabel("Fecha Reclamo");
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(10, 11, 130, 20);
		panel_8.add(label_22);
		
		JLabel label_23 = new JLabel("Descripcion");
		label_23.setHorizontalAlignment(SwingConstants.CENTER);
		label_23.setBounds(10, 44, 130, 20);
		panel_8.add(label_23);
		
		JTextArea textArea_2 = new JTextArea(4, 31);
		textArea_2.setLineWrap(true);
		textArea_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea_2.setBounds(160, 42, 256, 72);
		panel_8.add(textArea_2);
		
		JButton button_7 = new JButton("Agregar");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_7.setBounds(600, 303, 130, 23);
		panel_3.add(button_7);
		
		JButton button_8 = new JButton("Cancelar");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_8.setBounds(780, 303, 130, 23);
		panel_3.add(button_8);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1037, 500);
		setTitle("ALTA RECLAMO");
		frame = new JFrame();
	}
}
