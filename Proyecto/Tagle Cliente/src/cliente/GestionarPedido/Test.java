package cliente.GestionarPedido;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Test {

	private JFrame frame;
	private JTextField textField;
	private JDateChooser calendario;
	private JButton btnFecha;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setTitle("AGREGAR PEDIDO");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 622, 348);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 24, 163, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		calendario = new JDateChooser();
		calendario.setDate(new Date());
		
//		calandario.getDayChooser().getDayPanel().addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				String año = Integer.toString(calandario.getCalendar().get(java.util.Calendar.YEAR));
//				String mes = Integer.toString(calandario.getCalendar().get(java.util.Calendar.MONTH));
//				String dia = Integer.toString(calandario.getCalendar().get(java.util.Calendar.DAY_OF_MONTH));
//				textField.setText(dia+"/"+mes+"/"+año);
//			}
//		});
//		calandario.setTodayButtonVisible(true);
//		calandario.getDayChooser().getDayPanel().addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				String año = Integer.toString(calandario.getCalendar().get(java.util.Calendar.YEAR));
//				String mes = Integer.toString(calandario.getCalendar().get(java.util.Calendar.MONTH));
//				String dia = Integer.toString(calandario.getCalendar().get(java.util.Calendar.DAY_OF_MONTH));
//				textField.setText(dia+"/"+mes+"/"+año);
//			}
//		});
		calendario.setBounds(10, 58, 163, 20);
		frame.getContentPane().add(calendario);
		
		btnFecha = new JButton("fecha");
		btnFecha.setBounds(183, 24, 89, 23);
		btnFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SimpleDateFormat format2=new SimpleDateFormat("dd/MM/yyyy"); 
				
				
				String fecha = format2.format(calendario.getDate());
				
			    java.sql.Date sqlDate = new java.sql.Date(calendario.getDate().getTime());
			    System.out.println("sqlDate:" + sqlDate);
			    
				textField.setText(fecha);

			}
		});
		frame.getContentPane().add(btnFecha);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.setBounds(225, 125, 222, 174);
		frame.getContentPane().add(table);
	}
}
