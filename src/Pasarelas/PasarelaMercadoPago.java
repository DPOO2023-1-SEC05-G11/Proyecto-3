package Pasarelas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasarelaMercadoPago implements PasarelaDePago {
	@Override
    public boolean hacerPago(String nombreCliente, String correoCliente, Double valor, String tarjetaDeCredito, String cvv, String fecha){
        if (tarjetaDeCredito.length()!=16 || cvv.length()!=3){
            return false;
        }
        
        return validarStrings(correoCliente, cvv, tarjetaDeCredito, nombreCliente);
    }
    
    public boolean validarStrings(String correo, String cvv, String tarjeta, String nombre) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        if (cvv.matches("\\d+") && matcher.matches() && tarjeta.matches("\\d+") && nombre.matches("^[a-zA-Z]+$")) {
        	return true;
    	}
        return false;
    }
}
