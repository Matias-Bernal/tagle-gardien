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
package cliente.GestionarPedido;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import com.toedter.calendar.JDateChooser;

import common.DTOs.BdgDTO;
import common.DTOs.Devolucion_PiezaDTO;
import common.DTOs.Mano_ObraDTO;
import common.DTOs.MuletoDTO;
import common.DTOs.PedidoDTO;
import common.DTOs.Pedido_PiezaDTO;
import common.DTOs.PiezaDTO;
import common.DTOs.ProveedorDTO;
import common.DTOs.ReclamoDTO;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class GUIModificarPedidoEntidad extends JFrame {

	private static final long serialVersionUID = 1L;
	private MediadorPedido mediador;
	private JDateChooser dcFSF;
	private JDateChooser dcFRF;
	private JDateChooser dcFBDG;	
	private JDateChooser dcFDF;
	
	private JTextField tfNumero_Pedido;
	private JTextField tfPNC;
	private JTextField tfNumeroOrden;
	private JTextField tfNum_Pieza;
	private JTextField tfVIN_Muleto;
	private JTextField tfHs_Mano_Obra;
	private JTextField tfVal_Mano_Obra;
	private JTextField tfCod_Mano_Obra;
	private JTextField tfNumero_BDG;
	private JTextField tfEstado_Pedido;
	private JTextField tfNumero_Remito;
	private JTextField tfTransporte;
	private JTextField tfNumero_Retiro;
	private JTextField tfFSP;

	private JTextArea taDesc_Muleto;
	private JTextArea taDesc_Pedido;

	private JCheckBox cbxPropio;
	private JCheckBox cbxStrock;
	
	private JComboBox cbPiezas;
	private DefaultComboBoxModel<String> cmbPiezas;
	private Vector<String> numeros_piezas;

	private JComboBox cbProveedor;
	private DefaultComboBoxModel<String> cmbProveedor;
	private Vector<String> proveedores;


	private Vector<PiezaDTO> piezasDTO;
	private Vector<Pedido_PiezaDTO> pedidos_piezas;

	private PedidoDTO pedido;
	private ReclamoDTO reclamo;
	private Devolucion_PiezaDTO devolucion;
	private Mano_ObraDTO mano_obra;
	private BdgDTO bdg;
	private MuletoDTO muleto;
	private JButton btn_clear_FSF;
	private JButton btn_clear_FRF;
	private JButton btn_clear_FDF;
	private JButton btn_clear_FBDG;
		
	public GUIModificarPedidoEntidad(final MediadorPedido mediador, PedidoDTO pedido) {
		this.setMediador(mediador);
		this.setPedido(pedido);
		cargarDatos();
		initialize();
		completarCampos();
	}
	
	private void cargarDatos() {
		
		proveedores = mediador.obtenerProveedores();
		pedidos_piezas = mediador.obtenerPedidos_Piezas(pedido.getId());
		
		numeros_piezas = new Vector<String>();
		piezasDTO = new Vector<PiezaDTO>();
		for (int i=0; i< pedidos_piezas.size(); i++){
			if(pedidos_piezas.elementAt(i).getPieza()!=null)
				numeros_piezas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
				piezasDTO.add(pedidos_piezas.elementAt(i).getPieza());
		}
	}

	private void completarCampos() {
		if (pedidos_piezas.size()>0)
			tfNumero_Pedido.setText(pedidos_piezas.elementAt(0).getNumero_pedido());
		if(pedido.getFecha_solicitud_pedido()!=null)
			tfFSP.setText(pedido.getFecha_solicitud_pedido().toString());
		reclamo = pedido.getReclamo();
		if(reclamo.getOrden()!=null)
			tfNumeroOrden.setText(reclamo.getOrden().getNumero_orden());
		//pedido_pieza
		if(cmbPiezas.getSize()>0)
			cbPiezas.setSelectedIndex(0);
		actualizarPiezas();
	}
	
	private void initialize() {
		setTitle("MODIFICAR PEDIDO ENTIDAD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 865, 700);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit_pedido_entidad.png")));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblFechaSolicitud = new JLabel("Fecha Solicitud Pedido");
		lblFechaSolicitud.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitud.setHorizontalAlignment(SwingConstants.CENTER);
		lblFechaSolicitud.setBounds(0, 40, 150, 20);
		getContentPane().add(lblFechaSolicitud);
		
		JLabel lblOrden = new JLabel("Numero Orden");
		lblOrden.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblOrden.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrden.setBounds(0, 70, 150, 20);
		getContentPane().add(lblOrden);
		
		tfNumeroOrden = new JTextField();
		tfNumeroOrden.setEditable(false);
		tfNumeroOrden.setBounds(150, 70, 160, 20);
		getContentPane().add(tfNumeroOrden);
		tfNumeroOrden.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(259, 630, 110, 20);
		getContentPane().add(btnCancelar);
		
		JButton btnModificar = new JButton("Guardar");
		btnModificar.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/save.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
			}
		});
		btnModificar.setBounds(479, 630, 110, 20);
		getContentPane().add(btnModificar);
		
		JLabel lblNumeroPedido = new JLabel("Numero Pedido");
		lblNumeroPedido.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNumeroPedido.setBounds(0, 10, 150, 20);
		getContentPane().add(lblNumeroPedido);
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfNumero_Pedido = new JTextField();

		tfNumero_Pedido.setBounds(150, 10, 160, 20);
		getContentPane().add(tfNumero_Pedido);
		tfNumero_Pedido.setColumns(10);
		
		JPanel panel_piezas = new JPanel();
		panel_piezas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_piezas.setBounds(10, 105, 424, 515);
		getContentPane().add(panel_piezas);
		panel_piezas.setLayout(null);
		
		JLabel lblPiezas = new JLabel("Piezas");
		lblPiezas.setBounds(0, 40, 140, 20);
		panel_piezas.add(lblPiezas);
		lblPiezas.setHorizontalAlignment(SwingConstants.CENTER);
		
		cbPiezas = new JComboBox();
		cbPiezas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				actualizarPiezas();
			}
		});
		cmbPiezas = new DefaultComboBoxModel<String>(numeros_piezas);
		cbPiezas.setModel(cmbPiezas);
		cbPiezas.setBounds(140, 40, 160, 20);
		panel_piezas.add(cbPiezas);
		
		cbxPropio = new JCheckBox("Propio");
		cbxPropio.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxPropio.setBounds(138, 210, 130, 23);
		panel_piezas.add(cbxPropio);
		cbxPropio.setHorizontalAlignment(SwingConstants.LEFT);
		
		cbxStrock = new JCheckBox("Stock");
		cbxStrock.setFont(new Font("Tahoma", Font.ITALIC, 11));
		cbxStrock.setBounds(270, 210, 126, 23);
		panel_piezas.add(cbxStrock);
		cbxStrock.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel lblPnc = new JLabel("PNC");
		lblPnc.setBounds(0, 330, 140, 20);
		panel_piezas.add(lblPnc);
		lblPnc.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfPNC = new JTextField();

		tfPNC.setBounds(140, 330, 160, 20);
		panel_piezas.add(tfPNC);
		tfPNC.setColumns(10);
		
		dcFSF = new JDateChooser();
		dcFSF.setBounds(140, 240, 160, 20);
		panel_piezas.add(dcFSF);
		
		JLabel lblFechaSolicitudFabrica = new JLabel("Fecha Solicitud Fabrica");
		lblFechaSolicitudFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaSolicitudFabrica.setBounds(0, 240, 140, 20);
		panel_piezas.add(lblFechaSolicitudFabrica);
		lblFechaSolicitudFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblFechaRecepcionFabrica = new JLabel("Fecha Recepcion Fabrica");
		lblFechaRecepcionFabrica.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaRecepcionFabrica.setBounds(0, 270, 140, 20);
		panel_piezas.add(lblFechaRecepcionFabrica);
		lblFechaRecepcionFabrica.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFRF = new JDateChooser();
		dcFRF.setBounds(140, 270, 160, 20);
		panel_piezas.add(dcFRF);
		
		tfNum_Pieza = new JTextField();
		tfNum_Pieza.setColumns(10);
		tfNum_Pieza.setBounds(140, 70, 160, 20);
		panel_piezas.add(tfNum_Pieza);
		
		cbProveedor = new JComboBox();
		cmbProveedor = new DefaultComboBoxModel<String>(proveedores);
		cbProveedor.setModel(cmbProveedor);
		cbProveedor.setBounds(140, 100, 160, 20);
		panel_piezas.add(cbProveedor);
		
		taDesc_Pedido = new JTextArea(4, 31);
		taDesc_Pedido.setLineWrap(true);
		taDesc_Pedido.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		taDesc_Pedido.setBounds(140, 130, 260, 70);
		panel_piezas.add(taDesc_Pedido);
		
		JLabel label = new JLabel("Descripcion");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 130, 140, 20);
		panel_piezas.add(label);
		
		JLabel label_1 = new JLabel("Proveedor");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(0, 100, 140, 20);
		panel_piezas.add(label_1);
		
		JLabel label_2 = new JLabel("Numero Pieza");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(0, 70, 140, 20);
		panel_piezas.add(label_2);
		
		JLabel lblEstadoPedido = new JLabel("Estado Pedido");
		lblEstadoPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstadoPedido.setBounds(0, 300, 140, 20);
		panel_piezas.add(lblEstadoPedido);
		
		JButton btnModificarPieza = new JButton("Modificar");
		btnModificarPieza.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/edit.png")));
		btnModificarPieza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarPieza();
			}
		});
		btnModificarPieza.setBounds(305, 40, 110, 20);
		panel_piezas.add(btnModificarPieza);
		
		tfEstado_Pedido = new JTextField();
		tfEstado_Pedido.setEditable(false);
		tfEstado_Pedido.setColumns(10);
		tfEstado_Pedido.setBounds(140, 300, 256, 20);
		panel_piezas.add(tfEstado_Pedido);
		
		JLabel lblPiezas_1 = new JLabel("PIEZAS");
		lblPiezas_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPiezas_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPiezas_1.setBounds(142, 10, 130, 20);
		panel_piezas.add(lblPiezas_1);
		
		btn_clear_FSF = new JButton("");
		btn_clear_FSF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFSF.getDate()!=null)
					dcFSF.setDate(null);
			}
		});
		btn_clear_FSF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FSF.setBounds(310, 240, 25, 20);
		panel_piezas.add(btn_clear_FSF);
		
		btn_clear_FRF = new JButton("");
		btn_clear_FRF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFRF.getDate()!=null)
					dcFRF.setDate(null);
			}
		});
		btn_clear_FRF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FRF.setBounds(310, 270, 25, 20);
		panel_piezas.add(btn_clear_FRF);
		
		JPanel panel_claves_foraneas = new JPanel();
		panel_claves_foraneas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_claves_foraneas.setBounds(438, 105, 402, 515);
		getContentPane().add(panel_claves_foraneas);
		panel_claves_foraneas.setLayout(null);
		
		taDesc_Muleto = new JTextArea(4, 31);
		taDesc_Muleto.setLineWrap(true);
		taDesc_Muleto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		taDesc_Muleto.setBounds(140, 220, 260, 70);
		panel_claves_foraneas.add(taDesc_Muleto);
		
		JLabel lblDescripcionMuleto = new JLabel("Descripcion Muleto");
		lblDescripcionMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionMuleto.setBounds(0, 220, 140, 20);
		panel_claves_foraneas.add(lblDescripcionMuleto);
		
		JLabel lblVin = new JLabel("VIN Muleto");
		lblVin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVin.setBounds(0, 190, 140, 20);
		panel_claves_foraneas.add(lblVin);
		
		tfVIN_Muleto = new JTextField();
		tfVIN_Muleto.setToolTipText("Ej 12345678901234567");
		tfVIN_Muleto.addKeyListener(new KeyListener() {
		private int limite = 17;
		public void keyTyped(KeyEvent e) {
			if (tfVIN_Muleto.getText().length()>=limite){
				e.consume();					
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
				//buscar();
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {			
		}
		});
		tfVIN_Muleto.setColumns(10);
		tfVIN_Muleto.setBounds(140, 190, 160, 20);
		panel_claves_foraneas.add(tfVIN_Muleto);
		
		JLabel lblMuleto = new JLabel("MULETO");
		lblMuleto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMuleto.setHorizontalAlignment(SwingConstants.CENTER);
		lblMuleto.setBounds(142, 160, 130, 20);
		panel_claves_foraneas.add(lblMuleto);
		
		JLabel lblCantidadHs = new JLabel("Horas de Mano Obra");
		lblCantidadHs.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidadHs.setBounds(0, 330, 140, 20);
		panel_claves_foraneas.add(lblCantidadHs);
		
		tfHs_Mano_Obra = new JTextField();
		tfHs_Mano_Obra.setColumns(10);
		tfHs_Mano_Obra.setBounds(140, 330, 160, 20);
		panel_claves_foraneas.add(tfHs_Mano_Obra);
		
		JLabel lblValor = new JLabel(" Valor de Mano Obra");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(0, 360, 140, 20);
		panel_claves_foraneas.add(lblValor);
		
		tfVal_Mano_Obra = new JTextField();
		tfVal_Mano_Obra.setColumns(10);
		tfVal_Mano_Obra.setBounds(140, 360, 160, 20);
		panel_claves_foraneas.add(tfVal_Mano_Obra);
		
		JLabel lblCodigoManoObra = new JLabel("Codigo de Mano Obra");
		lblCodigoManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		lblCodigoManoObra.setBounds(0, 390, 140, 20);
		panel_claves_foraneas.add(lblCodigoManoObra);
		
		tfCod_Mano_Obra = new JTextField();
		tfCod_Mano_Obra.setColumns(10);
		tfCod_Mano_Obra.setBounds(140, 390, 160, 20);
		panel_claves_foraneas.add(tfCod_Mano_Obra);
		
		JLabel lblManoObra = new JLabel("MANO OBRA");
		lblManoObra.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblManoObra.setHorizontalAlignment(SwingConstants.CENTER);
		lblManoObra.setBounds(142, 300, 130, 20);
		panel_claves_foraneas.add(lblManoObra);
		
		JLabel lblBdg = new JLabel("BDG");
		lblBdg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblBdg.setBounds(142, 420, 130, 20);
		panel_claves_foraneas.add(lblBdg);
		
		JLabel lblFechaBdg = new JLabel("Fecha Carga BDG");
		lblFechaBdg.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblFechaBdg.setBounds(0, 450, 140, 20);
		panel_claves_foraneas.add(lblFechaBdg);
		lblFechaBdg.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFBDG = new JDateChooser();
		dcFBDG.setBounds(140, 450, 160, 20);
		panel_claves_foraneas.add(dcFBDG);
		
		JLabel lblNumeroDeBdg = new JLabel("Numero de BDG");
		lblNumeroDeBdg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroDeBdg.setBounds(0, 480, 140, 20);
		panel_claves_foraneas.add(lblNumeroDeBdg);
		
		tfNumero_BDG = new JTextField();
		tfNumero_BDG.setColumns(10);
		tfNumero_BDG.setBounds(140, 480, 160, 20);
		panel_claves_foraneas.add(tfNumero_BDG);
		
		JLabel lblDevolucion = new JLabel("DEVOLUCION");
		lblDevolucion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDevolucion.setBounds(142, 10, 130, 20);
		panel_claves_foraneas.add(lblDevolucion);
		lblDevolucion.setHorizontalAlignment(SwingConstants.CENTER);
		
		dcFDF = new JDateChooser();
		dcFDF.setBounds(140, 40, 160, 20);
		panel_claves_foraneas.add(dcFDF);
		
		JLabel label_3 = new JLabel("Fecha Devolucion");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(0, 40, 140, 20);
		panel_claves_foraneas.add(label_3);
		
		JLabel label_4 = new JLabel("Numero Remito");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(0, 70, 140, 20);
		panel_claves_foraneas.add(label_4);
		
		tfNumero_Remito = new JTextField();
		tfNumero_Remito.setColumns(10);
		tfNumero_Remito.setBounds(140, 70, 160, 20);
		panel_claves_foraneas.add(tfNumero_Remito);
		
		JLabel label_5 = new JLabel("Transporte");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(0, 100, 140, 20);
		panel_claves_foraneas.add(label_5);
		
		tfTransporte = new JTextField();
		tfTransporte.setColumns(10);
		tfTransporte.setBounds(140, 100, 160, 20);
		panel_claves_foraneas.add(tfTransporte);
		
		JLabel label_6 = new JLabel("Numero Retiro");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(0, 130, 140, 20);
		panel_claves_foraneas.add(label_6);
		
		tfNumero_Retiro = new JTextField();
		tfNumero_Retiro.setColumns(10);
		tfNumero_Retiro.setBounds(140, 130, 160, 20);
		panel_claves_foraneas.add(tfNumero_Retiro);
		
		btn_clear_FDF = new JButton("");
		btn_clear_FDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFDF.getDate()!=null)
					dcFDF.setDate(null);
			}
		});
		btn_clear_FDF.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FDF.setBounds(310, 40, 25, 20);
		panel_claves_foraneas.add(btn_clear_FDF);
		
		btn_clear_FBDG = new JButton("");
		btn_clear_FBDG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dcFBDG.getDate()!=null)
					dcFBDG.setDate(null);
			}
		});
		btn_clear_FBDG.setIcon(new ImageIcon(GUIModificarPedidoEntidad.class.getResource("/cliente/Resources/Icons/clear.png")));
		btn_clear_FBDG.setBounds(310, 450, 25, 20);
		panel_claves_foraneas.add(btn_clear_FBDG);
		
		tfFSP = new JTextField();
		tfFSP.setEditable(false);
		tfFSP.setColumns(10);
		tfFSP.setBounds(150, 40, 160, 20);
		getContentPane().add(tfFSP);
		
	}

	protected void modificarPieza() {
		if (tfNum_Pieza.getText().isEmpty() || cbProveedor.getSelectedItem()==null){
			JOptionPane.showMessageDialog(this,"Algunos campos estan vacios.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
		}else{	
				//pieza
				ProveedorDTO proveedor = mediador.obtenerProveedor(cbProveedor.getSelectedItem().toString());
				
				for(int i=0;i< pedidos_piezas.size();i++){
					if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

						pedidos_piezas.elementAt(i).getPieza().setNumero_pieza(tfNum_Pieza.getText());
						pedidos_piezas.elementAt(i).getPieza().setDescripcion(taDesc_Pedido.getText());
						pedidos_piezas.elementAt(i).getPieza().setProveedor(proveedor);

						if (!numeros_piezas.contains(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza())){
							numeros_piezas.remove(cbPiezas.getSelectedItem().toString());
							numeros_piezas.add(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
						}
						cmbPiezas = new DefaultComboBoxModel<String>(numeros_piezas);
						cbPiezas.setModel(cmbPiezas);
						
						pedidos_piezas.elementAt(i).setPropio(cbxPropio.isSelected());
						pedidos_piezas.elementAt(i).setStock(cbxStrock.isSelected());
						
						//fecha solicitud fabrica
						if (dcFSF.getDate()!=null){
							SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
							String fecha = format2.format(dcFSF.getDate());
							java.sql.Date fsf = new java.sql.Date(dcFSF.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_solicitud_fabrica(fsf);
						}else{
							pedidos_piezas.elementAt(i).setFecha_solicitud_fabrica(null);
						}
						//fecha recepcion fabrica
						if(dcFRF.getDate()!=null){
							SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
							String fecha = format2.format(dcFRF.getDate());
							java.sql.Date frf = new java.sql.Date(dcFRF.getDate().getTime());
							pedidos_piezas.elementAt(i).setFecha_recepcion_fabrica(frf);
						}else{
							pedidos_piezas.elementAt(i).setFecha_recepcion_fabrica(null);
						}
						
						pedidos_piezas.elementAt(i).setPnc(tfPNC.getText());
	
						//devolucion
						if(dcFDF.getDate()!=null || !tfNumero_Remito.getText().isEmpty() || !tfTransporte.getText().isEmpty() || !tfNumero_Retiro.getText().isEmpty()){
							if(pedidos_piezas.elementAt(i).getDevolucion_pieza()==null){
								Devolucion_PiezaDTO devolucion = new Devolucion_PiezaDTO();
								SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
								String fecha = format2.format(dcFDF.getDate());
								java.sql.Date fdf = new java.sql.Date(dcFDF.getDate().getTime());
								devolucion.setFecha_devolucion(fdf);
								devolucion.setNumero_remito(tfNumero_Remito.getText());
								devolucion.setTransporte(tfTransporte.getText());
								devolucion.setNumero_retiro(tfNumero_Retiro.getText());
								pedidos_piezas.elementAt(i).setDevolucion_pieza(devolucion);
							}else{
								SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
								String fecha = format2.format(dcFDF.getDate());
								java.sql.Date fdf = new java.sql.Date(dcFDF.getDate().getTime());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setFecha_devolucion(fdf);
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setNumero_remito(tfNumero_Remito.getText());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setTransporte(tfTransporte.getText());
								pedidos_piezas.elementAt(i).getDevolucion_pieza().setNumero_retiro(tfNumero_Retiro.getText());
		
							}
						}
						//muleto
						if(!tfVIN_Muleto.getText().isEmpty() || !taDesc_Muleto.getText().isEmpty()){
							if(pedidos_piezas.elementAt(i).getMuleto()==null){
								MuletoDTO muleto = new MuletoDTO();
								muleto.setVin(tfVIN_Muleto.getText());
								muleto.setDescripcion(taDesc_Muleto.getText());
								muleto.setPedido(pedido);
								muleto.setPieza(pedidos_piezas.elementAt(i).getPieza());
								pedidos_piezas.elementAt(i).setMuleto(muleto);
							}else{
								pedidos_piezas.elementAt(i).getMuleto().setVin(tfVIN_Muleto.getText());
								pedidos_piezas.elementAt(i).getMuleto().setDescripcion(taDesc_Muleto.getText());
								pedidos_piezas.elementAt(i).getMuleto().setPedido(pedido);
								pedidos_piezas.elementAt(i).getMuleto().setPieza(pedidos_piezas.elementAt(i).getPieza());
							}
						}
						//mano obra
						if(!tfVal_Mano_Obra.getText().isEmpty() && !tfCod_Mano_Obra.getText().isEmpty() && !tfHs_Mano_Obra.getText().isEmpty()){
							if(pedidos_piezas.elementAt(i).getMano_obra()==null){
								Mano_ObraDTO mano_obra = new Mano_ObraDTO();
								try{
									mano_obra.setCantidad_horas(Float.parseFloat(tfHs_Mano_Obra.getText()));
									mano_obra.setValor_mano_obra(Float.parseFloat(tfVal_Mano_Obra.getText()));
									mano_obra.setCodigo_mano_obra(tfCod_Mano_Obra.getText());
									pedidos_piezas.elementAt(i).setMano_obra(mano_obra);
								}catch(Exception e){
									System.out.println("Error de parseo de floats");
								}
							}else{
								try{
									pedidos_piezas.elementAt(i).getMano_obra().setCantidad_horas(Float.parseFloat(tfHs_Mano_Obra.getText()));
									pedidos_piezas.elementAt(i).getMano_obra().setValor_mano_obra(Float.parseFloat(tfVal_Mano_Obra.getText()));
									pedidos_piezas.elementAt(i).getMano_obra().setCodigo_mano_obra(tfCod_Mano_Obra.getText());
								}catch(Exception e){
									System.out.println("Error de parseo de floats");
								}
							}
						}
						//bdg
						if(!tfNumero_BDG.getText().isEmpty() && dcFBDG.getDate()!=null){
							if(pedidos_piezas.elementAt(i).getBdg()==null){
								BdgDTO bdg = new BdgDTO();
								SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
								String fecha = format2.format(dcFBDG.getDate());
								java.sql.Date fbdg = new java.sql.Date(dcFBDG.getDate().getTime());
								bdg.setFecha_bdg(fbdg);
								bdg.setNumero_bdg(tfNumero_BDG.getText());
								bdg.setPedido(pedido);
								bdg.setPieza(pedidos_piezas.elementAt(i).getPieza());
								pedidos_piezas.elementAt(i).setBdg(bdg);
							}else{
								SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy"); 
								String fecha = format2.format(dcFBDG.getDate());
								java.sql.Date fbdg = new java.sql.Date(dcFBDG.getDate().getTime());
								pedidos_piezas.elementAt(i).getBdg().setFecha_bdg(fbdg);
								pedidos_piezas.elementAt(i).getBdg().setNumero_bdg(tfNumero_BDG.getText());
								pedidos_piezas.elementAt(i).getBdg().setPedido(pedido);
								pedidos_piezas.elementAt(i).getBdg().setPieza(pedidos_piezas.elementAt(i).getPieza());
							}
						}
						//pedidos_piezas.add(pedido_pieza);
						JOptionPane.showMessageDialog(this,"Pieza Modificada.","Advertencia",JOptionPane.INFORMATION_MESSAGE);
						actualizarPiezas();
						
						break;
				}
			}
		}
		
	}

	protected void modificar() {
		if(!tfNumero_Pedido.getText().isEmpty()){
			for (int i= 0;i<pedidos_piezas.size();i++){
				pedidos_piezas.elementAt(i).setNumero_pedido(tfNumero_Pedido.getText());
			}
		}
		if (mediador.modificarPedidoEntidad(pedido,pedidos_piezas)){
			JOptionPane.showMessageDialog(null,"Pedido Modificada.","Notificacion",JOptionPane.INFORMATION_MESSAGE);
			mediador.actualizarDatosGestion();
			dispose();
		}else{
			JOptionPane.showMessageDialog(null,"Error al modificar. Inente nuevamente.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	protected void actualizarPiezas() {
		if (cbPiezas.getSelectedIndex()!=-1){
			if(!cbPiezas.getSelectedItem().toString().isEmpty() && cbPiezas.getSelectedItem()!=null ){

				//piezas
				tfNum_Pieza.setText("");
				cbProveedor.setSelectedIndex(0);
				taDesc_Pedido.setText("");
				cbxPropio.setSelected(false);
				cbxStrock.setSelected(false);
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				tfPNC.setText("");
				//devolucion
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				//mano obra
				tfHs_Mano_Obra.setText("");
				tfVal_Mano_Obra.setText("");
				tfCod_Mano_Obra.setText("");
				//bdg
				dcFBDG.setDate(null);
				tfNumero_BDG.setText("");
				
												
				for(int i=0;i< pedidos_piezas.size();i++){
					if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(cbPiezas.getSelectedItem().toString())){

						tfNum_Pieza.setText(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza());
						cbProveedor.setSelectedItem(pedidos_piezas.elementAt(i).getPieza().getProveedor().getNombre());
						taDesc_Pedido.setText(pedidos_piezas.elementAt(i).getPieza().getDescripcion());
						if(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica()!=null)
							dcFSF.setDate(pedidos_piezas.elementAt(i).getFecha_solicitud_fabrica());
						if(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica()!=null)
							dcFRF.setDate(pedidos_piezas.elementAt(i).getFecha_recepcion_fabrica());
						tfEstado_Pedido.setText(pedidos_piezas.elementAt(i).getEstado_pedido());
						tfPNC.setText(pedidos_piezas.elementAt(i).getPnc());
						//
						if(pedidos_piezas.elementAt(i).getPropio()!=null){
							cbxPropio.setSelected(pedidos_piezas.elementAt(i).getPropio());
						}
						if(pedidos_piezas.elementAt(i).getStock()!=null){
							cbxStrock.setSelected(pedidos_piezas.elementAt(i).getStock());
						}
						//devolucion
						if(pedidos_piezas.elementAt(i).getDevolucion_pieza()!=null){
							setDevolucion(pedidos_piezas.elementAt(i).getDevolucion_pieza());
							if(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion()!=null)
								dcFDF.setDate(pedidos_piezas.elementAt(i).getDevolucion_pieza().getFecha_devolucion());
							tfNumero_Remito.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getNumero_remito());
							tfTransporte.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getTransporte());
							tfNumero_Retiro.setText(pedidos_piezas.elementAt(i).getDevolucion_pieza().getNumero_retiro());
						}
						//muleto
						if(pedidos_piezas.elementAt(i).getMuleto()!=null){
							setMuleto(pedidos_piezas.elementAt(i).getMuleto());
							tfVIN_Muleto.setText(pedidos_piezas.elementAt(i).getMuleto().getVin());
							taDesc_Muleto.setText(pedidos_piezas.elementAt(i).getMuleto().getDescripcion());
						}
						//mano obra
						if(pedidos_piezas.elementAt(i).getMano_obra()!=null){
							setMano_obra(pedidos_piezas.elementAt(i).getMano_obra());
							try{
								tfHs_Mano_Obra.setText(String.valueOf(pedidos_piezas.elementAt(i).getMano_obra().getCantidad_horas()));
								tfVal_Mano_Obra.setText(String.valueOf(pedidos_piezas.elementAt(i).getMano_obra().getValor_mano_obra()));
								tfCod_Mano_Obra.setText(pedidos_piezas.elementAt(i).getMano_obra().getCodigo_mano_obra());
								
							}catch (Exception e){
								tfHs_Mano_Obra.setText("");
								tfVal_Mano_Obra.setText("");
								tfCod_Mano_Obra.setText("");
							}
						}
						//bdg
						if(pedidos_piezas.elementAt(i).getBdg()!=null){
							setBdg(pedidos_piezas.elementAt(i).getBdg());
							if(pedidos_piezas.elementAt(i).getBdg().getFecha_bdg()!=null)
								dcFBDG.setDate(pedidos_piezas.elementAt(i).getBdg().getFecha_bdg());
							tfNumero_BDG.setText(pedidos_piezas.elementAt(i).getBdg().getNumero_bdg());
						}
						break;
					}
				}
			}else{
				//piezas
				tfNum_Pieza.setText("");
				cbProveedor.setSelectedIndex(0);
				taDesc_Pedido.setText("");
				cbxPropio.setSelected(false);
				cbxStrock.setSelected(false);
				dcFSF.setDate(null);
				dcFRF.setDate(null);
				tfEstado_Pedido.setText("");
				tfPNC.setText("");
				//devolucion
				dcFDF.setDate(null);
				tfNumero_Remito.setText("");
				tfTransporte.setText("");
				tfNumero_Retiro.setText("");
				//muleto
				tfVIN_Muleto.setText("");
				taDesc_Muleto.setText("");
				//mano obra
				tfHs_Mano_Obra.setText("");
				tfVal_Mano_Obra.setText("");
				tfCod_Mano_Obra.setText("");
				//bdg
				dcFBDG.setDate(null);
				tfNumero_BDG.setText("");
			}
		}
		
	}
	@SuppressWarnings("unused")
	private Pedido_PiezaDTO buscarPedido_Pieza(String numero_pieza) {
		Pedido_PiezaDTO pedido = null;
		for(int i=0;i< pedidos_piezas.size();i++){
			if(pedidos_piezas.elementAt(i).getPieza().getNumero_pieza().equals(numero_pieza))
				pedido = pedidos_piezas.elementAt(i);
		}
		return pedido;
	}
	

	//GETTERS AND SETTERS//
	public MediadorPedido getMediador() {
		return mediador;
	}

	public void setMediador(MediadorPedido mediador) {
		this.mediador = mediador;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}

	public Devolucion_PiezaDTO getDevolucion() {
		return devolucion;
	}

	public void setDevolucion(Devolucion_PiezaDTO devolucion) {
		this.devolucion = devolucion;
	}

	public Mano_ObraDTO getMano_obra() {
		return mano_obra;
	}

	public void setMano_obra(Mano_ObraDTO mano_obra) {
		this.mano_obra = mano_obra;
	}

	public BdgDTO getBdg() {
		return bdg;
	}

	public void setBdg(BdgDTO bdg) {
		this.bdg = bdg;
	}

	public MuletoDTO getMuleto() {
		return muleto;
	}

	public void setMuleto(MuletoDTO muleto) {
		this.muleto = muleto;
	}
}