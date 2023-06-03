package GUI;

import javax.swing.*;

import Modelo.Habitacion;
import Modelo.Huesped;
import Modelo.ReservaEstadia;
import Persistencia.Hotel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

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
                crearReserva.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (crearReserva.getReserva() == null)
                        {
                            JOptionPane.showMessageDialog(null, "Hubo un error creando la reserva, verifique sus datos \n y elige habitaciones disponibles.", "Error!", JOptionPane.WARNING_MESSAGE);
                        }else{
                            ReservaEstadia reserva = crearReserva.getReserva();

                            ArrayList<Huesped> huespedes = new ArrayList<Huesped>();

                            JFrame frame = new JFrame("Add Huespedes");
                            frame.setLocationRelativeTo(null);
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(400, 300);
                    
                            JPanel panel = new JPanel();
                            panel.setLayout(new BorderLayout());
                    
                            DefaultListModel<String> listModel = new DefaultListModel<>();
                            JList<String> list = new JList<>(listModel);
                    
                            JScrollPane scrollPane = new JScrollPane(list);
                            panel.add(scrollPane, BorderLayout.CENTER);
                    
                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
                    
                            JLabel label1 = new JLabel("Nombre:");
                            JTextField textField1 = new JTextField(10);
                    
                            JLabel label2 = new JLabel("Documento:");
                            JTextField textField2 = new JTextField(10);
                    
                            JLabel label3 = new JLabel("Correo:");
                            JTextField textField3 = new JTextField(10);
                    
                            JLabel label4 = new JLabel("Telefono:");
                            JTextField textField4 = new JTextField(10);
                    
                            JButton addButton = new JButton("Add");
                            addButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    Huesped huesped = Hotel.getInstance().crearHuesped(textField1.getText(), textField2.getText(), textField3.getText(), textField4.getText());
                                    huespedes.add(huesped);

                                    listModel.addElement(huesped.toString());

                                    textField1.setText("");
                                    textField2.setText("");
                                    textField3.setText("");
                                    textField4.setText("");
                                }
                            });
                            frame.getRootPane().setDefaultButton(addButton);
                    
                            JButton removeButton = new JButton("Remove");
                            removeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int selectedIndex = list.getSelectedIndex();
                                    if (selectedIndex != -1) {
                                        String selectedHuesped = listModel.elementAt(selectedIndex);
                                        Iterator<Huesped> iterator = huespedes.iterator();
                                        while (iterator.hasNext()) {
                                            Huesped huesped = iterator.next();
                                            if (huesped.toString().equals(selectedHuesped)) {
                                                iterator.remove();
                                            }
                                        }
                                        listModel.remove(selectedIndex);
                                    }
                                }
                            });
                            
                    
                            buttonPanel.add(label1);
                            buttonPanel.add(textField1);
                            buttonPanel.add(label2);
                            buttonPanel.add(textField2);
                            buttonPanel.add(label3);
                            buttonPanel.add(textField3);
                            buttonPanel.add(label4);
                            buttonPanel.add(textField4);
                            buttonPanel.add(addButton);
                            buttonPanel.add(removeButton);
                    
                            panel.add(buttonPanel, BorderLayout.EAST);

                    
                            JButton confirmButton = new JButton("Confirm");
                            confirmButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (huespedes.size()==0){
                                        JOptionPane.showMessageDialog(null, "Entre las informaciones de al menos un huesped.", "No Huespedes", JOptionPane.ERROR_MESSAGE);
                                    }else{
                                        for (Huesped h : huespedes) {
                                        reserva.addHuesped(h);
                                    }
                                    frame.dispose();
                            
                                    int choice = JOptionPane.showOptionDialog(null, "Do you want to pay now or at check-out?", "Payment Option",
                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                            new String[]{"Now", "Later"}, null);
                            
                                    if (choice == JOptionPane.NO_OPTION) {
                                        JOptionPane.showMessageDialog(null, "Reservation confirmed. Payment will be done at check-out.", "Payment Confirmation", JOptionPane.INFORMATION_MESSAGE);
                                    } else if (choice == JOptionPane.YES_OPTION) {
                                        //Falta procesar el pago y mostrar "Payment accepted. Reservation Confirmed."
                                        JFrame paymentWindow = new JFrame();
                                        paymentWindow.setVisible(true);
                                    }
                                    }
                                    
                                }
                            });
                                    


                                    buttonPanel.add(confirmButton);

                                    frame.setContentPane(panel);
                                    frame.setVisible(true);

                                    frame.pack();

                                    String message = "Por favor entre las informaciones de todos los huespedes del grupo";
                                    String title = "Huespedes";
                                    JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                                    }
                    }
                });
                crearReserva.setVisible(true);
                crearReserva.addHabitacion(Integer.toString(habitacionesList.getSelectedValue().getId()));
                crearReserva.setFechaI(fechaInicial);
                crearReserva.setNumNoches(duracion);
                
                
            }
        });
    }
}
