package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Modelo.Cama;
import Persistencia.Hotel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class VentanaAddRoom extends JFrame implements ActionListener {

	private JPanel panelPrincipal;
	private JTextField textId, textTarifa, textTamanhoM2, textVoltajeAC;
	private JPanel panelOp;
	protected JButton btnAddCama, btnRemoverCama, btnCancel, btnDone;
	protected JRadioButton btnDoble, btnEstandar, btnSuite;
	protected JCheckBox btnVista, btnCocina, btnBalcon, checkBoxBalcon, checkBoxVista, checkBoxCocina, checkBoxAire, checkBoxCalefaccion,
	checkBoxTV, checkBoxCafetera, checkBoxRopaCama, checkBoxTapetesHipo, checkBoxPlancha, checkBoxSecador, checkBoxUsbA, checkBoxUsbB, checkBoxDesayuno;
	protected JList<String> list;
	protected DefaultListModel<String> listaa;
	
	
	public VentanaAddRoom() {
		setTitle("Añadir Habitacion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);

		

		

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperior = new JPanel();
		panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new GridLayout(0, 4, 0, 0));
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.LEFT);
		panelSuperior.add(lblId);
		
		textId = new JTextField();
		panelSuperior.add(textId);
		textId.setColumns(10);
		
		JLabel lblTarifa = new JLabel("Tarifa:");
		lblTarifa.setHorizontalAlignment(SwingConstants.LEFT);
		panelSuperior.add(lblTarifa);
		
		textTarifa = new JTextField();
		panelSuperior.add(textTarifa);
		textTarifa.setColumns(10);
		
		panelOp = new JPanel();
		panelPrincipal.add(panelOp, BorderLayout.CENTER);
		panelOp.setLayout(new GridLayout(0,1, 0, 0));
		
		JPanel panelTipos = new JPanel();
		panelOp.add(panelTipos);
		
		JLabel lblTipo = new JLabel("Tipo:");
		panelTipos.add(lblTipo);

		// TEXT FIELDS??##############

		JPanel panelTamanhoM2 = new JPanel();
		panelOp.add(panelTamanhoM2);

		JLabel lblTamanhoM2 = new JLabel("Tamanho (m^2):");
		panelTamanhoM2.add(lblTamanhoM2);

		textTamanhoM2 = new JTextField();
		panelTamanhoM2.add(textTamanhoM2);
		textTamanhoM2.setColumns(10);

		JPanel panelVoltajeAC = new JPanel();
		panelOp.add(panelVoltajeAC);

		JLabel lblVoltajeAC = new JLabel("Voltaje AC:");
		panelVoltajeAC.add(lblVoltajeAC);

		textVoltajeAC = new JTextField();
		panelVoltajeAC.add(textVoltajeAC);
		textVoltajeAC.setColumns(10);

		
		// GRUPO DE BOTONES #############
		
		ButtonGroup roomTypeButtonGroup = new ButtonGroup();
		btnDoble = new JRadioButton("Doble", false);
		panelTipos.add(btnDoble);
		roomTypeButtonGroup.add(btnDoble); 
		btnEstandar = new JRadioButton("Estandar", false);
		panelTipos.add(btnEstandar);
		roomTypeButtonGroup.add(btnEstandar);
		btnSuite = new JRadioButton("Suite", false);
		panelTipos.add(btnSuite);
		roomTypeButtonGroup.add(btnSuite);
		
		JPanel panelCama = new JPanel();
		panelOp.add(panelCama);
		JLabel lblCaracteristicas = new JLabel("Caracteristicas:");
		panelCama.add(lblCaracteristicas);
		
		
		// CHECK BOXES #####################
		
		

		checkBoxBalcon = new JCheckBox("Balcón");
		panelCama.add(checkBoxBalcon);

		checkBoxVista = new JCheckBox("Vista");
		panelCama.add(checkBoxVista);

		checkBoxCocina = new JCheckBox("Cocina");
		panelCama.add(checkBoxCocina);

		checkBoxAire = new JCheckBox("Aire");
		panelCama.add(checkBoxAire);

		checkBoxCalefaccion = new JCheckBox("Calefacción");
		panelCama.add(checkBoxCalefaccion);

		checkBoxTV = new JCheckBox("TV");
		panelCama.add(checkBoxTV);

		checkBoxCafetera = new JCheckBox("Cafetera");
		panelCama.add(checkBoxCafetera);

		checkBoxRopaCama = new JCheckBox("Ropa de cama");
		panelCama.add(checkBoxRopaCama);

		checkBoxTapetesHipo = new JCheckBox("Tapetes hipoalergénicos");
		panelCama.add(checkBoxTapetesHipo);

		checkBoxPlancha = new JCheckBox("Plancha");
		panelCama.add(checkBoxPlancha);

		checkBoxSecador = new JCheckBox("Secador");
		panelCama.add(checkBoxSecador);

		checkBoxUsbA = new JCheckBox("USB A");
		panelCama.add(checkBoxUsbA);

		checkBoxUsbB = new JCheckBox("USB B");
		panelCama.add(checkBoxUsbB);

		checkBoxDesayuno = new JCheckBox("Desayuno");
		panelCama.add(checkBoxDesayuno);

		
		JPanel panelCarac = new JPanel();
		panelOp.add(panelCarac);
		
		panelCarac.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblcama = new JLabel("Cama:");
		panelCarac.add(lblcama);
		
		// J LIST ##########
	
		list = new JList<String>();
		listaa = new DefaultListModel<String>();

		JScrollPane scrollPaneLista = new JScrollPane(list);
		scrollPaneLista.setPreferredSize(new Dimension(200, 50));
		
		panelCarac.add(scrollPaneLista);
		
		btnAddCama = new JButton("Añadir cama");
		btnAddCama.addActionListener(this);
		panelCarac.add(btnAddCama);

		btnRemoverCama = new JButton("Remover cama");
		btnRemoverCama.addActionListener(this);
		panelCarac.add(btnRemoverCama);
		
		JPanel panelInferior = new JPanel();
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		
		btnCancel = new JButton("Cancelar");
		panelInferior.add(btnCancel);
		btnCancel.addActionListener(this);
		
		btnDone = new JButton("Aceptar");
		panelInferior.add(btnDone);
		btnDone.addActionListener(this);
		list.setModel(listaa);

		pack();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tipo = "";
		ArrayList<Integer> listaNums = new ArrayList<>();
		if (e.getSource()==btnAddCama) {
			dialogCama dialogo = new dialogCama(VentanaAddRoom.this);
			dialogo.setVisible(true);
		}else if (e.getSource() == btnRemoverCama) {
			int selectedIndex = list.getSelectedIndex();
			if (selectedIndex != -1) {
				listaa.remove(selectedIndex);
			} else {
				JOptionPane.showMessageDialog(null, "Please select an item to remove.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (e.getSource()==btnDone) {
		
			try {
				int id;
				id = Integer.parseInt(textId.getText());
				
				if (btnEstandar.isSelected()) {
					tipo = "estandar";
				} else if (btnSuite.isSelected()){
					tipo = "suite";
				} else if (btnDoble.isSelected()) {
					tipo = "doble";
				}
				Boolean balcon = checkBoxBalcon.isSelected();
				Boolean vista = checkBoxVista.isSelected();
				Boolean cocina = checkBoxCocina.isSelected();
				Boolean aire = checkBoxAire.isSelected();
				Boolean calefaccion = checkBoxCalefaccion.isSelected();
				Boolean tv = checkBoxTV.isSelected();
				Boolean cafetera = checkBoxCafetera.isSelected();
				Boolean ropaCama = checkBoxRopaCama.isSelected();
				Boolean tapetesHipo = checkBoxTapetesHipo.isSelected();
				Boolean plancha = checkBoxPlancha.isSelected();
				Boolean secador = checkBoxSecador.isSelected();
				Boolean usbA = checkBoxUsbA.isSelected();
				Boolean usbB = checkBoxUsbB.isSelected();
				Boolean desayuno = checkBoxDesayuno.isSelected();

				Double tarifa = Double.parseDouble(textTarifa.getText());

				Double tamanhoM2 = Double.parseDouble(textTamanhoM2.getText());
				int voltajeAC = Integer.parseInt(textVoltajeAC.getText());

				
				for (int i =0; i<listaa.getSize();i++) {
					if (listaa.getElementAt(i).equals("")||listaa.getElementAt(i)==null) {
						listaNums.add(0);
					} else if (listaa.getElementAt(i).equals("King")) {
						listaNums.add(1);
					} else if (listaa.getElementAt(i).equals("Queen")) {
						listaNums.add(2);
					} else if (listaa.getElementAt(i).equals("Doble")) {
						listaNums.add(3);
					} else if (listaa.getElementAt(i).equals("Sencilla")) {
						listaNums.add(4);
					} else if (listaa.getElementAt(i).equals("Niños")) {
						listaNums.add(5);
					}
				}
				ArrayList<Cama> listaCamas = new ArrayList<>();
				for (int num: listaNums ) {
					Cama camaa = new Cama(num);
					listaCamas.add(camaa);
				}
				
				if (Hotel.getInstance().buscarHabs1(id) != null)
				{
					JOptionPane.showMessageDialog(null, "Ya existe la habitacion.");	
				}else{
					Hotel.getInstance().anadirHabs1(id, tipo, balcon, vista, cocina, listaCamas, tarifa, false, tamanhoM2, aire,
					calefaccion, tv, cafetera, ropaCama, tapetesHipo, plancha, secador, voltajeAC, usbA, usbB, desayuno);
					dispose();
					JOptionPane.showMessageDialog(null, "Se añadio correctamente");	
				}

				
				
				
			} catch (NumberFormatException e1) {
				System.err.println("Exception occurred: " + e1);
				JOptionPane.showMessageDialog(null, "Invalid ID or Tarifa. Please enter a valid numeric value.");
			} catch (Exception e1) {
				System.err.println("Exception occurred: " + e1);
				JOptionPane.showMessageDialog(null, "An error occurred. Please check the input.");
			}
			
		}else if(e.getSource()==btnCancel) {
			dispose();
		}
	}
	public void addLista(String element) {
		listaa.addElement(element);
	}	
	public void setId(String id) {
		textId.setText(id);
	}
	public void setTarifa(String tarifa) {
		textTarifa.setText(tarifa);
	}
	
	

}