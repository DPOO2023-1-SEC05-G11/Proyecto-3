package testing;

import java.time.LocalDate;
import java.util.ArrayList;
import Modelo.*;
import Persistencia.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CrearReservaTest {
    private static ArrayList<Habitacion> habitaciones = new ArrayList<>();
    private static ArrayList<ReservaEstadia> reservas = new ArrayList<>();

    public static void main(String[] args) {

    	ArrayList<Habitacion> habitaciones = new ArrayList<>();
    	ArrayList<Cama> camas = new ArrayList<>();
    	camas.add(new Cama(2));
    	camas.add(new Cama(2));
    	camas.add(new Cama(0));

    	habitaciones.add(new Habitacion(1, "Individual", true, true, false, camas, 100.0, false, 20.0, true, false, true, false, true, true, true, false, 220, true, true, false));
    	habitaciones.add(new Habitacion(2, "Doble", true, true, true, camas, 150.0, true, 25.0, true, true, true, true, true, true, true, false, 220, true, true, true));
    	habitaciones.add(new Habitacion(3, "Suite", true, true, true, camas, 200.0, false, 30.0, true, true, true, true, true, true, true, false, 220, true, true, true));

        Huesped huespedPrincipal = new Huesped("Juan", "Perez", "12345678", "juan@example.com");

        // Prueba ejecutarCrearReserva() con habitaciones válidas y disponibles
        try {
            LocalDate fechaInicio = LocalDate.now();
            int duracion = 5;
            int[] roomIDs = {1, 3};
            ReservaEstadia reserva = ejecutarCrearReserva(huespedPrincipal, fechaInicio.toString(), duracion, roomIDs);
            System.out.println("Reserva creada exitosamente: " + reserva);
        } catch (Exception e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
        }

        // Prueba ejecutarCrearReserva() con habitaciones inválidas
        try {
            LocalDate fechaInicio = LocalDate.now();
            int duracion = 3;
            int[] roomIDs = {2, 4};
            ReservaEstadia reserva = ejecutarCrearReserva(huespedPrincipal, fechaInicio.toString(), duracion, roomIDs);
            System.out.println("Reserva creada exitosamente: " + reserva);
        } catch (Exception e) {
            System.out.println("Error al crear la reserva: " + e.getMessage());
        }
    }

    public static ReservaEstadia ejecutarCrearReserva(Huesped huespedPrincipal, String fechaI, int duracion, int[] roomIDs) throws Exception {
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();

        LocalDate fechaInicio = LocalDate.parse(fechaI);

        for (int id : roomIDs) {
            Habitacion habitacion = buscarHabitacion(id);

            if (habitacion != null) {
                if (habitacion.libreEntre(fechaInicio, duracion)) {
                    habitaciones.add(habitacion);
                } else {
                    throw new Exception("La habitacion seleccionada no está libre en las fechas seleccionadas.");
                }
            } else {
                throw new Exception("La habitación seleccionada no existe. Por favor, intente nuevamente.");
            }
        }

        ReservaEstadia res = new ReservaEstadia(fechaInicio, duracion, huespedPrincipal, habitaciones);
        reservas.add(res);
        LoaderSaver.salvarReservas(reservas);
        return res;
    }

    public static Habitacion buscarHabitacion(int id) {
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getId() == id) {
                return habitacion;
            }
        }
        return null;
    }
}

