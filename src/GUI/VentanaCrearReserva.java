package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Persistencia.Hotel;
import Modelo.*;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;

public class VentanaCrearReserva extends JFrame {

	private JPanel panelPrincipal;
	private JTextField textFieldInicio;
	private Boolean acceptButtonClicked;
	private JTextField textFieldNoches;
	private JTextField textFieldNombre;
	private JTextField textFieldDocumento;
	private JTextField textFieldCorreo;
	private JTextField textFieldTelefono;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPane;
    private ReservaEstadia reserva = null;

	public VentanaCrearReserva() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 439);

		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel bottomPanel = new JPanel();
		panelPrincipal.add(bottomPanel, BorderLayout.SOUTH);
		
		JButton btnCancelar = new JButton("Cancelar");
		bottomPanel.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		bottomPanel.add(btnAceptar);
		
		JPanel panel_1 = new JPanel();
		panelPrincipal.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblFechai = new JLabel("Fecha de incio (YYYY-MM-DD):");
		panel_1.add(lblFechai);

        textFieldInicio = new JTextField();
		panel_1.add(textFieldInicio);
        textFieldInicio.setColumns(10);

        JLabel lblNoches = new JLabel("Numero de noches:");
		panel_1.add(lblNoches);

        textFieldNoches = new JTextField();
		panel_1.add(textFieldNoches);
        textFieldNoches.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre huesped principal:");
		panel_1.add(lblNombre);

        textFieldNombre = new JTextField();
		panel_1.add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblDoc = new JLabel("Documento:");
		panel_1.add(lblDoc);

        textFieldDocumento = new JTextField();
		panel_1.add(textFieldDocumento);
        textFieldDocumento.setColumns(10);

		
		JLabel lblCorreo = new JLabel("Correo:");
		panel_1.add(lblCorreo);

        textFieldCorreo = new JTextField();
		panel_1.add(textFieldCorreo);
        textFieldCorreo.setColumns(10);

        JLabel lblTelefono = new JLabel("Telefono:");
		panel_1.add(lblTelefono);

        textFieldTelefono = new JTextField();
		panel_1.add(textFieldTelefono);
        textFieldTelefono.setColumns(10);
		
        JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));


        listModel = new DefaultListModel<>();
        JList<String> habsSeleccionadasList = new JList<>(listModel);
        habsSeleccionadasList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        scrollPane = new JScrollPane(habsSeleccionadasList);
        scrollPane.setPreferredSize(new Dimension(250, 500));

        habsSeleccionadasList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    try {
                        int selectedRoomID = Integer.parseInt(habsSeleccionadasList.getSelectedValue());
                        new VentanaShowRoom(Hotel.getInstance().buscarHabs1(selectedRoomID)).setVisible(true);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid room ID");
                    }
                }
            }
        });
        

        panel_2.add(scrollPane);
		



		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnAdd = new JButton("Añadir");
		panel_3.add(btnAdd);
		
		JButton btnRemove = new JButton("Eliminar");
		panel_3.add(btnRemove);

		btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newItem = JOptionPane.showInputDialog(null, "Entre el numero de la habitacion que quiere:");
                
                if (Hotel.getInstance().buscarHabs1(Integer.parseInt(newItem)) != null)
                {
                    addHabitacion(newItem);
                }else{
                    JOptionPane.showMessageDialog(null, "La habitacion no existe!", "ID error!", JOptionPane.WARNING_MESSAGE);
                }

                
            }
        });

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = habsSeleccionadasList.getSelectedIndex();
                if (selectedIndex == -1) {
                    JOptionPane.showMessageDialog(null, "No item selected. Please select an item to remove.");
                } else {
                    String selectedItem = habsSeleccionadasList.getSelectedValue();
                    
                    int option = JOptionPane.showConfirmDialog(null, "Estás seguro de que quieres eliminar la habitacion "+selectedItem+" ?",
                            "Confirm Remove", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        listModel.remove(selectedIndex);
                    }
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Confirm Cancel",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    acceptButtonClicked = false;
                    dispose();
                }
            }
        });
        

        
        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fechaInicio = textFieldInicio.getText();
                    int numNoches = Integer.parseInt(textFieldNoches.getText());
                    String nombreHuesped = textFieldNombre.getText();
                    String documento = textFieldDocumento.getText();
                    String correo = textFieldCorreo.getText();
                    String telefono = textFieldTelefono.getText();

                    if (fechaInicio.isEmpty() || numNoches <= 0 || nombreHuesped.isEmpty() || documento.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese todas las informaciones necesarias.", "Error!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Huesped huesped = Hotel.getInstance().crearHuesped(nombreHuesped, documento, correo, telefono);

                    if (listModel.getSize() == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Seleccione al menos una habitacion.", "Ningun Habitacion Seleccionada!", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    int[] selectedHabs = new int[listModel.getSize()];
                    for (int i = 0; i < listModel.getSize(); i++) {
                        selectedHabs[i]=Integer.parseInt(listModel.getElementAt(i));
                    }

                    reserva = CrearReserva(huesped, fechaInicio, numNoches, selectedHabs);
                    
                        if(reserva!=null){
                            acceptButtonClicked = true;
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null, "Una de las habitaciones seleccionadas está ocupada en la fechas deseadas.", "Error!", JOptionPane.WARNING_MESSAGE);
                        }
                        
                } catch (NullPointerException ex) {
                    System.out.println("An error occurred while executing crearReserva: " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Ingrese todas las informaciones necesarias.", "Error!", JOptionPane.WARNING_MESSAGE);
                }
                
                
            }
        });
        
        

		//pack();

	}

    public ReservaEstadia CrearReserva(Huesped huesped, String fechaInicio, int numNoches, int[] selectedHabs)
    {
        try {
            return Hotel.getInstance().ejecutarCrearReserva(huesped, fechaInicio, numNoches, selectedHabs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            
        }
        return null;
    }

    public ReservaEstadia getReserva()
    {
        return this.reserva;
    }
    
    
    public void addHabitacion(String numeroCama)
    {
        if (numeroCama != null && !numeroCama.isEmpty()) {
            listModel.addElement(numeroCama);
        
            JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        }
    }

    public void setFechaI(LocalDate fecha)
    {
        textFieldInicio.setText(fecha.toString());
    }

    public void setNumNoches(int noches)
    {
        textFieldNoches.setText(Integer.toString(noches));
    }

    public boolean isAcceptButtonClicked(){
        return this.acceptButtonClicked;
    }

}
