package cliente.Reporte;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class GUIReporte extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private MediadorReporte mediador;

	private JPanel contentPane;
	
	private JTable table;

	public GUIReporte() {
		initialize();
	}

	public GUIReporte(MediadorReporte mediadorReporte) {
		mediador = mediadorReporte;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		setTitle("REPORTES");
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	
		JTabbedPane reportes = new JTabbedPane(JTabbedPane.TOP);
		reportes.setBounds(10, 33, 1244, 638);
		contentPane.add(reportes);
		
		JPanel pnAgentes = new JPanel();
		reportes.addTab("AGENTES", null, pnAgentes, null);
		pnAgentes.setLayout(null);
		
		JTabbedPane reportesAgentes = new JTabbedPane(JTabbedPane.TOP);
		reportesAgentes.setBounds(10, 11, 1219, 588);
		pnAgentes.add(reportesAgentes);
		
		JPanel pn_reporte_general = new JPanel();
		reportesAgentes.addTab("REPORTE GENERAL", null, pn_reporte_general, null);
		pn_reporte_general.setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 1, 1);
		pn_reporte_general.add(table);
		
		JPanel pn_pieza_sin_turno = new JPanel();
		reportesAgentes.addTab("PIEZAS RECIBIDAS DE LA FABRICA CUYOS CLIENTES AUN NO TIENEN TURNO", null, pn_pieza_sin_turno, null);
		
		JPanel pn_piezas_no_recibidas = new JPanel();
		reportesAgentes.addTab("PIEZAS PEDIDAS A FABRICA. ESPERANDO LLEGADA A TAGLE", null, pn_piezas_no_recibidas, null);
		
		JPanel pnEntidad = new JPanel();
		reportes.addTab("ENTIDADES", null, pnEntidad, null);
		pnEntidad.setLayout(null);
		
		JTabbedPane reportesEntidad = new JTabbedPane(JTabbedPane.TOP);
		reportesEntidad.setBounds(10, 11, 1219, 588);
		pnEntidad.add(reportesEntidad);
		
		JPanel pnPiezasEviadasyNoDevueltas = new JPanel();
		reportesEntidad.addTab("PIEZAS ENVIADAS AL AGENTE Y AUN NO DEVUELTAS", null, pnPiezasEviadasyNoDevueltas, null);
		
		JPanel pnPeizasComprometidas = new JPanel();
		reportesEntidad.addTab("PIEZAS COMPROMETIDAS A USAR POR EL AGENTE", null, pnPeizasComprometidas, null);
		
		JPanel pnPiezasRecibidas = new JPanel();
		reportesEntidad.addTab("PIEZAS RECIBIDAS DESDE FABRICA Y AUN NO ENVIADAS AL AGENTE", null, pnPiezasRecibidas, null);
		
		JPanel pnPiezasNoPedidas = new JPanel();
		reportesEntidad.addTab("PIEZAS SOLICITADAS POR EL AGENTE Y AUN SIN PEDIR A FABRICA", null, pnPiezasNoPedidas, null);
		
		JPanel pn_piezas_nuevas_recibidas_no_enviadas = new JPanel();
		reportesEntidad.addTab("PIEZAS NUEVAS RECIBIDAS DEL AGENTE Y AUN NO ENVIADAS A PLANTA", null, pn_piezas_nuevas_recibidas_no_enviadas, null);
		
		JPanel pn_piezas_remplazadas_recibidas_no_enviadas = new JPanel();
		reportesEntidad.addTab("PIEZAS REEMPLAZADAS RECIBIDAS DEL AGENTE Y AUN NO ENVIADAS A PLANTA", null, pn_piezas_remplazadas_recibidas_no_enviadas, null);
		
		JPanel pn_piezas_pedidas_no_llegadas = new JPanel();
		reportesEntidad.addTab("PIEZAS PEDIDAS A FABRICA Y ESPERANDO LLEGADA A TAGLE", null, pn_piezas_pedidas_no_llegadas, null);
		
		JPanel pnPropio = new JPanel();
		reportes.addTab("PROPIOS", null, pnPropio, null);
		pnPropio.setLayout(null);
		
	}
}
