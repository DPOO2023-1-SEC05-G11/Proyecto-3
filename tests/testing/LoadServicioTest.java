package testing;

import java.io.*;
import java.util.*;
import Modelo.*;
import Persistencia.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

public class LoadServicioTest {
    public static HashMap<String, Integer> updateServicio(File f) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] atributos = line.split(";");
                if (atributos.length >= 2) {
                    String servicio = atributos[0];
                    String valorStr = atributos[1];
                    int valor;
                    try {
                        valor = Integer.parseInt(valorStr);
                        map.put(servicio, valor);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid value format for servicio: " + servicio);
                    }
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        return map;
    }

    public static HashMap<String, Integer> cargarServicio(String file) {
        File f = new File("data/" + file + ".txt");
        return updateServicio(f);
    }

    public static void salvarServicio(HashMap<String, Integer> map, String file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + file + ".txt"));

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void removeServicio(String nombreServicio) {
        File f = new File("data/" + nombreServicio + ".txt");
        deleteFile(f);
    }

    public static void deleteFile(File f) {
        if (f.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.out.println("Failed to delete file.");
        }
    }

    public static String[] serviciosACargar() {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("data/servicios.txt")))) {
            String line = reader.readLine();
            return line.split(";");

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            return null;
        }
    }

    public static void salvarServiciosDoc(HashMap<String, Servicio> servicios) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/servicios.txt"));
            for (String servicio : servicios.keySet()) {
                writer.write(servicio + ";");
            }
            writer.close();
            System.out.println("Hotel services saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    @Test
    public static void main(String[] args) {
        // Prueba para updateServicio()
        File f = new File("data/servicios.txt");
        HashMap<String, Integer> servicioMap = updateServicio(f);
        System.out.println("Servicio Map: " + servicioMap);

        // Prueba para cargarServicio()
        String file = "servicios";
        HashMap<String, Integer> servicioMapCargado = cargarServicio(file);
        System.out.println("Servicio Map Cargado: " + servicioMapCargado);

        // Prueba para salvarServicio()
        HashMap<String, Integer> nuevoServicioMap = new HashMap<>();
        nuevoServicioMap.put("Servicio A", 10);
        nuevoServicioMap.put("Servicio B", 20);
        nuevoServicioMap.put("Servicio C", 30);
        String nuevoFile = "nuevoservicios";
        salvarServicio(nuevoServicioMap, nuevoFile);

        // Prueba para removeServicio()
        String servicioARemover = "Servicio A";
        removeServicio(servicioARemover);

        // Prueba para deleteFile()
        File fileToDelete = new File("data/nuevoservicios.txt");
        deleteFile(fileToDelete);

        // Prueba para serviciosACargar()
        String[] serviciosCargar = serviciosACargar();
        System.out.println("Servicios a Cargar: " + Arrays.toString(serviciosCargar));

        // Prueba para salvarServiciosDoc()
        HashMap<String, Servicio> servicios = new HashMap<>();
        servicios.put("Servicio X", new Servicio(nuevoServicioMap, "Servicio X"));
        servicios.put("Servicio Y", new Servicio(nuevoServicioMap, "Servicio Y"));
        servicios.put("Servicio Z", new Servicio(nuevoServicioMap, "Servicio Z"));
        salvarServiciosDoc(servicios);
    }
}


