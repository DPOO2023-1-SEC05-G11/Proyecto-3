package Pasarelas;

public class PasarelaPayu implements PasarelaDePago {
    @Override
    public boolean hacerPago(String nombreCliente, String correoCliente, Double valor, String tarjetaDeCredito, String cvv){
        if (tarjetaDeCredito.length()!=16 || cvv.length()!=3){
            return false;
        }
        return true;
    }
}
