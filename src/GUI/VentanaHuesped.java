package GUI;

import javax.swing.*;

import Modelo.Habitacion;
import Persistencia.Hotel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class VentanaHuesped extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private DefaultListModel<Habitacion> habitacionesListModel;
    private JList<Habitacion> habitacionesList;
    private LocalDate fechaInicial = null;
    private LocalDate fechaFinal = null;
    int duracion;

    public VentanaHuesped() {
        setTitle("Huesped");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create the text fields
        textField1 = new JTextField();
        textField2 = new JTextField();

        // Create the empty panel with scrollable pane
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        habitacionesListModel = new DefaultListModel<>();
        habitacionesList = new JList<>(habitacionesListModel);

        JScrollPane scrollPane = new JScrollPane(habitacionesList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the buttons
        JButton searchButton = new JButton("Search");
        getRootPane().setDefaultButton(searchButton);
        JButton infoButton = new JButton("Info");
        JButton reserveButton = new JButton("Reserve");
        JButton clearButton = new JButton("Clear");

        // Set layout for the frame
        setLayout(new BorderLayout());

        // Create a panel for the text fields
        JPanel topPanel = new JPanel(new GridLayout(1, 4));

        // Create the labels
        JLabel label1 = new JLabel("Fecha Inicial (YYYY-MM-DD):");
        JLabel label2 = new JLabel("Fecha Final (YYYY-MM-DD):");

        topPanel.add(label1);
        topPanel.add(textField1);
        topPanel.add(label2);
        topPanel.add(textField2);

        // Create a panel for the buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(searchButton);
        bottomPanel.add(infoButton);
        bottomPanel.add(reserveButton);
        bottomPanel.add(clearButton);

        // Add the panels and components to the frame
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    fechaInicial = LocalDate.parse(textField1.getText());
                    fechaFinal = LocalDate.parse(textField2.getText());
                }catch(Exception e1){
                    e1.getMessage();
                }

                if (fechaInicial == null || fechaFinal == null) {
                    JOptionPane.showMessageDialog(VentanaHuesped.this,
                            "Por favor entre fechas validas.",
                            "Fechas Invalidas",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                duracion = (int) ChronoUnit.DAYS.between(fechaInicial, fechaFinal) + 1;

                ArrayList<Habitacion> habitaciones = Hotel.getInstance().getHabitaciones();
                habitacionesListModel.clear();
                for (Habitacion hab : habitaciones) {
                    if (hab.libreEntre(fechaInicial, duracion))
                    {
                        habitacionesListModel.addElement(hab);
                    }
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                habitacionesListModel.clear();
            }
        });

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Habitacion selectedHabitacion = habitacionesList.getSelectedValue();
                if (selectedHabitacion != null) {
                    new VentanaShowRoom(selectedHabitacion).setVisible(true);; 
                }
            }
        });

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaCrearReserva crearReserva = new VentanaCrearReserva();
			    crearReserva.setVisible(true);
                crearReserva.addHabitacion(Integer.toString(habitacionesList.getSelectedValue().getId()));
                crearReserva.setFechaI(fechaInicial);
                crearReserva.setNumNoches(duracion);
            }
        });
        
    }
}
