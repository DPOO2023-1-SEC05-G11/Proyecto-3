package testing;

import java.io.*;
import java.util.*;
import Modelo.*;
import Persistencia.*;
import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class LoadHabitacionesTest {
    public static void main(String[] args) {
        testUpdateHabitaciones();
        testCargarHabitaciones();
    }

    public static void testUpdateHabitaciones() {
        File f = createTestFile("1;suite;true;true;false;1;0;0;100.0;false;20.0;true;false;true;false;false;true;true;null;220;true;false;true");
        ArrayList<Habitacion> habitaciones = LoaderSaver.updateHabitaciones(f);

        assert habitaciones.size() == 1;

        Habitacion habitacion = habitaciones.get(0);

        assert habitacion.getId() == 1;
        assert habitacion.getTipo().equals("suite");
        assert habitacion.tieneBalcon();
        assert habitacion.tieneVista();
        assert !habitacion.tieneCocina();
        assert habitacion.getCamas().size() == 1;
        assert habitacion.getTarifa() == 100.0;
        assert !habitacion.isOcupado();
        assert habitacion.getTamanhoM2() == 20.0;
        assert habitacion.tieneAire();
        assert !habitacion.tieneCalefaccion();
        assert habitacion.tieneTv();
        assert !habitacion.tieneCafetera();
        assert habitacion.tieneRopaCama();
        assert !habitacion.tieneTapetesHipo();
        assert !habitacion.tienePlancha();
        assert !habitacion.tieneSecador();
        assert habitacion.getVoltajeAC() == 220;
        assert habitacion.tieneUsbA();
        assert !habitacion.tieneUsbB();
        assert habitacion.tieneDesayuno();

        deleteTestFile(f);
    }

    public static void testCargarHabitaciones() {
        File f = createTestFile("1;suite;true;true;false;1;0;0;100.0;false;20.0;true;false;true;false;false;true;true;null;220;true;false;true\n" +
                "2;doble;true;true;true;2;2;0;150.0;true;25.0;true;true;true;true;true;true;true;null;220;true;true;true");
        ArrayList<Habitacion> habitaciones = LoaderSaver.cargarHabitaciones();

        assert habitaciones.size() == 2;

        Habitacion habitacion1 = habitaciones.get(0);
        Habitacion habitacion2 = habitaciones.get(1);

        assert habitacion1.getId() == 1;
        assert habitacion1.getTipo().equals("suite");
        assert habitacion1.tieneBalcon();
        assert habitacion1.tieneVista();
        assert !habitacion1.tieneCocina();
        assert habitacion1.getCamas().size() == 1;
        assert habitacion1.getTarifa() == 100.0;
        assert !habitacion1.isOcupado();
        assert habitacion1.getTamanhoM2() == 20.0;
        assert habitacion1.tieneAire();
        assert !habitacion1.tieneCalefaccion();
        assert habitacion1.tieneTv();
        assert !habitacion1.tieneCafetera();
        assert habitacion1.tieneRopaCama();
        assert !habitacion1.tieneTapetesHipo();
        assert !habitacion1.tienePlancha();
        assert !habitacion1.tieneSecador();
        assert habitacion1.getVoltajeAC() == 220;
        assert habitacion1.tieneUsbA();
        assert !habitacion1.tieneUsbB();
        assert habitacion1.tieneDesayuno();

        assert habitacion2.getId() == 2;
        assert habitacion2.getTipo().equals("doble");
        assert habitacion2.tieneBalcon();
        assert habitacion2.tieneVista();
        assert habitacion2.tieneCocina();
        assert habitacion2.getCamas().size() == 3;
        assert habitacion2.getTarifa() == 150.0;
        assert habitacion2.isOcupado();
        assert habitacion2.getTamanhoM2() == 25.0;
        assert habitacion2.tieneAire();
        assert habitacion2.tieneCalefaccion();
        assert habitacion2.tieneTv();
        assert habitacion2.tieneCafetera();
        assert habitacion2.tieneRopaCama();
        assert habitacion2.tieneTapetesHipo();
        assert habitacion2.tienePlancha();
        assert habitacion2.tieneSecador();
        assert habitacion2.getVoltajeAC() == 220;
        assert habitacion2.tieneUsbA();
        assert habitacion2.tieneUsbB();
        assert habitacion2.tieneDesayuno();

        deleteTestFile(f);
    }


    public static File createTestFile(String content) {
        try {
            File file = File.createTempFile("test", ".txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteTestFile(File file) {
        file.delete();
    }

    public static File createTempFile() {
        try {
            return File.createTempFile("temp", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteTempFile(File file) {
        file.delete();
    }
}

