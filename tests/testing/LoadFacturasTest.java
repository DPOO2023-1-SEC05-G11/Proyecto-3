package testing;

import java.io.*;
import java.time.*;
import java.util.*;
import Modelo.*;
import Persistencia.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoadFacturasTest {
	
	@Test
    public static ArrayList<String> cargarFacturas() {
        ArrayList<String> facturas = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader("data/facturas.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                facturas.add(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return facturas;
    }
	
	@Test
    public static int getContadorFactura(ArrayList<String> facturas) {
        if (facturas.isEmpty()) {
            return 0;
        }

        String lastFactura = facturas.get(facturas.size() - 1);
        int idStartIndex = lastFactura.indexOf("facturaID=") + "facturaID=".length();
        int idEndIndex = lastFactura.indexOf(";", idStartIndex);

        if (idStartIndex >= 0 && idEndIndex >= 0) {
            String idString = lastFactura.substring(idStartIndex, idEndIndex);
            try {
                return Integer.parseInt(idString);
            } catch (NumberFormatException e) {
                System.err.println("Invalid facturaID format: " + idString);
            }
        }

        return 0;
    }

	@Test
    public static void salvarFacturas(ArrayList<String> facturas) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/facturas.txt"));
            for (String factura : facturas) {
                if (factura != null && !factura.isEmpty()) {
                    writer.write(factura + "\n");
                }
            }
            writer.close();
            System.out.println("Facturas saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

	@Test
    public static void main(String[] args) {
        // Prueba cargarFacturas()
        ArrayList<String> facturas = cargarFacturas();
        System.out.println("Facturas cargadas: " + facturas);

        // Prueba getContadorFactura()
        int contadorFactura = getContadorFactura(facturas);
        System.out.println("Contador de factura: " + contadorFactura);

        // Prueba salvarFacturas()
        facturas.add("Factura 1");
        facturas.add("Factura 2");
        facturas.add("Factura 3");
        salvarFacturas(facturas);
    }
}


