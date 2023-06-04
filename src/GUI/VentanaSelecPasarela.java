package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.ReservaEstadia;
import Pasarelas.PasarelaDePago;
import Pasarelas.PasarelaFactory;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class VentanaSelecPasarela extends JFrame implements ActionListener {
	

	JButton btnMercado, btnPayu, btnPaypal;
	private JPanel PanelPrincipal;
	private JPanel panelInfo;
	private JLabel lblFecha;
	private JLabel lblNumero;
	private JLabel lblTitular;
	private JLabel lblCvv;
	private JTextField textTarjeta;
	private JTextField textCVV;
	private JTextField textNombre;
	private JTextField textFecha;
	private JLabel lblCorreo;
	private JTextField textCorreo;
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSelecPasarela frame = new VentanaSelecPasarela(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}/**/
	public VentanaSelecPasarela(Double valor) {
		setTitle("Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 368);
		PanelPrincipal = new JPanel();
		PanelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(PanelPrincipal);
		PanelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Escoja un servicio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		PanelPrincipal.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panelBotones = new JPanel();
		PanelPrincipal.add(panelBotones, BorderLayout.SOUTH);
		
		btnMercado = new JButton("MercadoPago");
		panelBotones.add(btnMercado);
		btnMercado.addActionListener(this);
		
		btnPayu = new JButton("Payu");
		panelBotones.add(btnPayu);
		btnPayu.addActionListener(this);
		
		btnPaypal = new JButton("PayPal");
		panelBotones.add(btnPaypal);
		btnPaypal.addActionListener(this);
		
		panelInfo = new JPanel();
		PanelPrincipal.add(panelInfo, BorderLayout.CENTER);
		panelInfo.setLayout(new GridLayout(5, 2, 0, 0));
		
		lblNumero = new JLabel("Numero de tarjeta: ");
		panelInfo.add(lblNumero);
		
		textTarjeta = new JTextField();
		panelInfo.add(textTarjeta);
		textTarjeta.setColumns(10);
		
		lblTitular = new JLabel("Nombre del titular:");
		panelInfo.add(lblTitular);
		
		textNombre = new JTextField();
		panelInfo.add(textNombre);
		textNombre.setColumns(10);
		
		lblCvv = new JLabel("CVV:");
		panelInfo.add(lblCvv);
		
		textCVV = new JTextField();
		panelInfo.add(textCVV);
		textCVV.setColumns(10);
		
		lblFecha = new JLabel("Expiracion (AAAA/MM): ");
		panelInfo.add(lblFecha);
		
		textFecha = new JTextField();
		panelInfo.add(textFecha);
		textFecha.setColumns(10);
		
		lblCorreo = new JLabel("Correo electronico:");
		panelInfo.add(lblCorreo);
		
		textCorreo = new JTextField();
		panelInfo.add(textCorreo);
		textCorreo.setColumns(10);
		
		
		/**
		btnMercado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaMercado mercadoPago = new VentanaMercado();
				mercadoPago.setVisible(true);
			}
		});	
		btnPaypal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPaypal payPal = new VentanaPaypal();
				payPal.setVisible(true);
			}
		});	
		btnPayu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPayu payU = new VentanaPayu();
				payU.setVisible(true);
			}
		});	**/
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean resu = true;
		String numero = textTarjeta.getText();
		String nombre = textNombre.getText();
		String cvv = textCVV.getText();
		String fecha = textFecha.getText();
		String correo = textCorreo.getText();
		PasarelaFactory factory = new PasarelaFactory();
		if (e.getSource()==btnMercado) {
			PasarelaDePago pasarela = factory.getPasarela("MERCADOPAGO");
			resu = pasarela.hacerPago(nombre, correo, null, numero, cvv, fecha);
		} else if (e.getSource()==btnPayu) {
			PasarelaDePago pasarela = factory.getPasarela("PAYU");
			resu = pasarela.hacerPago(nombre, correo, null, numero, cvv, fecha);
		} else if (e.getSource()==btnPaypal) {
			PasarelaDePago pasarela = factory.getPasarela("PAYPAL");
			resu = pasarela.hacerPago(nombre, correo, null, numero, cvv, fecha);
		}
		if (resu) {
			JOptionPane.showMessageDialog(null, "Pago exitoso, Check Out completado");
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Error en el pago");
		}
	}
}
