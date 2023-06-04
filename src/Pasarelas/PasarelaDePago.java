package Pasarelas;

public interface PasarelaDePago {
    boolean hacerPago(String nombreCliente, String correoCliente, Double valor, String tarjetaDeCredito, String cvv, String fecha);
}
