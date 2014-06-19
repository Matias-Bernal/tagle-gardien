package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Main;
import java.awt.Dimension;

public class GuiLauncher extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelButtons;
	private JButton btnGardienRepuestos;
	private JButton btnGardienGarantias;
	private JButton btnAyuda;
	private JPanel panel_logo;
	private JPanel logo;
	private JButton btnInformacion;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public GuiLauncher() {
		setResizable(false);
		setTitle("GARDIEN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiLauncher.class.getResource("/gui/Resources/Icons/gardien_logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel_Whit_Image("/gui/Resources/Images/fondo.jpg");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelButtons = new TransparentPanel();
		panelButtons.setMinimumSize(new Dimension(0, 0));
		panelButtons.setAlignmentY(0.0f);
		panelButtons.setAlignmentX(0.0f);
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[] {0, 30, 100, 300, 100, 30, 0};
		gbl_panelButtons.rowHeights = new int[] {30, 30, 30, 30, 30};
		gbl_panelButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panelButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		panelButtons.setLayout(gbl_panelButtons);
		
		btnGardienGarantias = new JButton("Gardien Garantias");
		btnGardienGarantias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lazarGardienGarantias();
			}
		});
		btnGardienGarantias.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnGardienGarantias = new GridBagConstraints();
		gbc_btnGardienGarantias.insets = new Insets(0, 0, 5, 5);
		gbc_btnGardienGarantias.fill = GridBagConstraints.BOTH;
		gbc_btnGardienGarantias.gridx = 3;
		gbc_btnGardienGarantias.gridy = 1;
		panelButtons.add(btnGardienGarantias, gbc_btnGardienGarantias);
		
		btnInformacion = new JButton("");
		btnInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lanzarInformacion();
			}
		});
		
		btnGardienRepuestos = new JButton("Gardien Repuestos");
		btnGardienRepuestos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lazarGardienRepuestos();
			}
		});
		btnGardienRepuestos.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_btnGardienRepuestos = new GridBagConstraints();
		gbc_btnGardienRepuestos.fill = GridBagConstraints.BOTH;
		gbc_btnGardienRepuestos.insets = new Insets(0, 0, 5, 5);
		gbc_btnGardienRepuestos.gridx = 3;
		gbc_btnGardienRepuestos.gridy = 3;
		panelButtons.add(btnGardienRepuestos, gbc_btnGardienRepuestos);
		btnInformacion.setIcon(new ImageIcon(GuiLauncher.class.getResource("/gui/Resources/Icons/hm-about.png")));
		btnInformacion.setContentAreaFilled(false);
		btnInformacion.setBorderPainted(false);
		btnInformacion.setBorder(null);
		btnInformacion.setAlignmentX(0.5f);
		GridBagConstraints gbc_btnInformacion = new GridBagConstraints();
		gbc_btnInformacion.fill = GridBagConstraints.BOTH;
		gbc_btnInformacion.insets = new Insets(0, 0, 0, 5);
		gbc_btnInformacion.gridx = 1;
		gbc_btnInformacion.gridy = 6;
		panelButtons.add(btnInformacion, gbc_btnInformacion);
		
		btnAyuda = new JButton("");
		btnAyuda.setContentAreaFilled(false);
		btnAyuda.setBorderPainted(false);
		btnAyuda.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAyuda.setBorder(null);
		btnAyuda.setIcon(new ImageIcon(GuiLauncher.class.getResource("/gui/Resources/Icons/help.png")));
		btnAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lanzarAyuda();
			}
		});
		GridBagConstraints gbc_btnAyuda = new GridBagConstraints();
		gbc_btnAyuda.insets = new Insets(0, 0, 0, 5);
		gbc_btnAyuda.fill = GridBagConstraints.BOTH;
		gbc_btnAyuda.gridx = 5;
		gbc_btnAyuda.gridy = 6;
		panelButtons.add(btnAyuda, gbc_btnAyuda);
		
		panel_logo = new TransparentPanel();
		contentPane.add(panel_logo, BorderLayout.NORTH);
		GridBagLayout gbl_panel_logo = new GridBagLayout();
		gbl_panel_logo.columnWidths = new int[] {200, 200, 200};
		gbl_panel_logo.rowHeights = new int[] {200};
		gbl_panel_logo.columnWeights = new double[]{0.0, 1.0, 0.0};
		gbl_panel_logo.rowWeights = new double[]{0.0};
		panel_logo.setLayout(gbl_panel_logo);
		
		logo = new JPanel_Whit_Image("/gui/Resources/Icons/gardien_logo.png");
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.insets = new Insets(0, 0, 0, 5);
		gbc_logo.fill = GridBagConstraints.BOTH;
		gbc_logo.gridx = 1;
		gbc_logo.gridy = 0;
		panel_logo.add(logo, gbc_logo);

		contentPane.show();
	}

	protected void lazarGardienGarantias() {
		try {
			new Main().start();
			//this.toBack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void lazarGardienRepuestos() {
		try {
			new Main().start();
			//this.toBack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void lanzarAyuda() {
		try {
			new Main().start();
			//this.toBack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void lanzarInformacion() {
		try {
			new Main().start();
			//this.toBack();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
