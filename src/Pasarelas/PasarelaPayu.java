package Pasarelas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasarelaPayu implements PasarelaDePago {
	@Override
    public boolean hacerPago(String nombreCliente, String correoCliente, Double valor, String tarjetaDeCredito, String cvv, String fecha){
        boolean a = validarStrings(correoCliente, cvv, tarjetaDeCredito, nombreCliente);
        if (tarjetaDeCredito.length()!=16 || cvv.length()!=3){
            return false;
        } else if (a==false) {
            return false;
        }
        String linea = nombreCliente+";"+correoCliente+";"+Double.toString(valor)+";"+tarjetaDeCredito+";"+cvv+";"+fecha;
        generarRegistro(linea);
        return true;
    }
    
    public boolean validarStrings(String correo, String cvv, String tarjeta, String nombre) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        if (cvv.matches("\\d+") && matcher.matches() && tarjeta.matches("\\d+") && nombre.matches("^[a-zA-Z ]+$")) {
        	return true;
    	}
        return false;
    }
    public static void generarRegistro(String line) {
        try {
            FileWriter writer = new FileWriter("./data/payu.txt", true);  // true indica que se agregará al final del archivo
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(line);
            bufferedWriter.newLine();  // Esto añade una nueva línea al final
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
