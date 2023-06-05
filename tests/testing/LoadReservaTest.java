package testing;

import java.io.*;
import java.time.*;
import java.util.*;
import Modelo.*;
import Persistencia.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoadReservaTest {
    public static void main(String[] args) {

        ArrayList<Habitacion> allHabitaciones = new ArrayList<>();
        HashMap<String, Servicio> allServicios = new HashMap<>();

        ArrayList<ReservaEstadia> reservas = cargarReservas(allHabitaciones, allServicios);

        System.out.println("Reservas cargadas:");
        for (ReservaEstadia reserva : reservas) {
            System.out.println(reserva);
        }

        salvarReservas(reservas);
        System.out.println("Reservas guardadas exitosamente.");
    }

    @Test
    public static ArrayList<ReservaEstadia> cargarReservas(ArrayList<Habitacion> allHabitaciones, HashMap<String, Servicio> allServicios) {
        ArrayList<ReservaEstadia> reservas = new ArrayList<ReservaEstadia>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data/reservas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");		
                LocalDate fechaInicio = LocalDate.parse(atributos[0]);
                int duracion = Integer.parseInt(atributos[1]);
                
                String[] HPrincipal = atributos[2].split("-");
                Huesped huespedPrincipal = new Huesped(HPrincipal[0], HPrincipal[1], HPrincipal[2], HPrincipal[3]);
                
                String[] Habs = atributos[3].split(",");//Habitaciones
                ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
                for (String s : Habs) {
                    for (Habitacion h : allHabitaciones) {
                        if (h.getId() == Integer.parseInt(s)) {
                            habitaciones.add(h);
                            break;
                        }
                    }
                }
                                
                ReservaEstadia reserva = new ReservaEstadia(fechaInicio, duracion, huespedPrincipal, habitaciones);
                                
                Boolean isCheckedIn = Boolean.parseBoolean(atributos[4]);
                
                if (isCheckedIn) {
                    ArrayList<Huesped> huespedes = new ArrayList<Huesped>();
                    String[] Huesps = atributos[5].split(",");
                    for (String huesp : Huesps) {
                        String[] Huesp = huesp.split("-");
                        huespedes.add(new Huesped(Huesp[0], Huesp[1], Huesp[2], Huesp[3]));
                    }
                    reserva.checkIn(huespedes);
                    
                    if (!atributos[6].equals("null")) {
                        String[] Consums = atributos[6].split(",");
                        for (String cons : Consums) {
                            String[] Cons = cons.split("\\|");
                            Servicio servicio = allServicios.get(Cons[0]);
                            Boolean yaPagado = Boolean.parseBoolean(Cons[1]);
                            String[] HuespCons = Cons[2].split("-");
                            Huesped huespedConsumo = new Huesped(HuespCons[0], HuespCons[1], HuespCons[2], HuespCons[3]);
                            String huespedPrincipalConsumoDocumento = Cons[3];
                            String tipo = Cons[4];
                            int precioTotal = Integer.parseInt(Cons[5]);
                            
                            Consumo consumo = new Consumo(servicio, yaPagado, huespedConsumo, huespedPrincipalConsumoDocumento, tipo);
                            consumo.setPrecioTotal(precioTotal);
                            
                            reserva.addConsumo(consumo);
                        }
                    }
                }
                
                reservas.add(reserva);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return reservas;
    }
    
    @Test
    public static void salvarReservas(ArrayList<ReservaEstadia> reservas) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/reservas.txt"));
            
            for (ReservaEstadia reserva : reservas) {
                StringBuilder habitaciones = new StringBuilder();
                for (Habitacion habitacion : reserva.getHabitaciones()) {
                    habitaciones.append(habitacion.getId()).append(",");
                }
                habitaciones.deleteCharAt(habitaciones.length() - 1); 
                
                StringBuilder huespedes = new StringBuilder();
                if (reserva.isCheckedIn()) {
                    for (Huesped huesped : reserva.getHuespedes()) {
                        huespedes.append(huesped.guardarString()).append(",");
                    }
                    huespedes.deleteCharAt(huespedes.length() - 1); 
                } else {
                    huespedes.append("null");
                }
                
                StringBuilder consumos = new StringBuilder();
                if (reserva.getConsumos() == null || reserva.getConsumos().size() == 0) {
                    consumos.append("null");
                } else {
                    for (Consumo consumo : reserva.getConsumos()) {
                        consumos.append(consumo.guardarString()).append(",");
                    }
                    consumos.deleteCharAt(consumos.length() - 1);
                }
                
                writer.write(reserva.getFechaInicial() + ";"  + reserva.getDuracion() + ";"  + reserva.getHuespedPrincipal().guardarString() + ";"  +
                    habitaciones + ";" + reserva.isCheckedIn() + ";" + huespedes + ";" + consumos + "\n");
            }
            
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

