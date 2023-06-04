package Pasarelas;

public class PasarelaFactory {
    public PasarelaDePago getPasarela(String nombrePasarela){
        if (nombrePasarela == null) {
            return null;
        }
        if (nombrePasarela.equalsIgnoreCase("PAYPAL")) {
            return new PasarelaPaypal();
            
        } else if (nombrePasarela.equalsIgnoreCase("MERCADOPAGO")) {
            return new PasarelaMercadoPago();
            
        } else if (nombrePasarela.equalsIgnoreCase("PAYU")) {
            return new PasarelaPayu();
        }

        return null;
    }

    }
