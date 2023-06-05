package GUI;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Modelo.Habitacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

public class VentanaShowRoom extends JFrame {

    private JPanel panelPrincipal;

    public VentanaShowRoom(Habitacion hab) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        setContentPane(panelPrincipal);

        JPanel panel = new JPanel();
        panelPrincipal.add(panel, BorderLayout.CENTER);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel lblHab = new JLabel("Características de la Habitación");
        lblHab.setAlignmentX(CENTER_ALIGNMENT);							
		lblHab.setFont(new Font(lblHab.getFont().getFontName(), Font.BOLD, 16));
        panel.add(lblHab);

        JLabel lblId = new JLabel("ID: " + hab.getId());
        lblId.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblId);

        JLabel lblTarifa = new JLabel("Tarifa: " + hab.getTarifa());
        lblTarifa.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblTarifa);

        JLabel lblEspacioNinos = new JLabel("Espacio ninos: " + hab.getEspacioNinos());
        lblEspacioNinos.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblEspacioNinos);

        JLabel lblEspacioAdultos = new JLabel("Espacio adultos: " + hab.getEspacioAdultos());
        lblEspacioAdultos.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblEspacioAdultos);

        ArrayList<String> tiene = new ArrayList<>();
        ArrayList<String> no_tiene = new ArrayList<>();

        try{
            if (hab.tieneAire()){tiene.add("Aire Condicionado");}else{no_tiene.add("Aire Condicionado");}
            if (hab.tieneBalcon()){tiene.add("Balcon");}else{no_tiene.add("Balcon");}
            if (hab.tieneCafetera()){tiene.add("Cafetera");}else{no_tiene.add("Cafetera");}
            if (hab.tieneCalefaccion()){tiene.add("Calefaccion");}else{no_tiene.add("Calefaccion");}
            if (hab.tieneCocina()){tiene.add("Cocina");}else{no_tiene.add("Cocina");}
            if (hab.tieneDesayuno()){tiene.add("Desayuno");}else{no_tiene.add("Desayuno");}
            if (hab.tienePlancha()){tiene.add("Plancha");}else{no_tiene.add("Plancha");}
            if (hab.tieneRopaCama()){tiene.add("Ropa Cama");}else{no_tiene.add("Ropa Cama");}
            if (hab.tieneSecador()){tiene.add("Secador");}else{no_tiene.add("Secador");}
            if (hab.tieneTapetesHipo()){tiene.add("Tapetes Hipo");}else{no_tiene.add("Tapetes Hipo");}
            if (hab.tieneTv()){tiene.add("TV");}else{no_tiene.add("TV");}
            if (hab.tieneUsbA()){tiene.add("USB A");}else{no_tiene.add("USB A");}
            if (hab.tieneUsbB()){tiene.add("USB B");}else{no_tiene.add("USB B");}
            if (hab.tieneVista()){tiene.add("Vista");}else{no_tiene.add("Vista");}
        }catch(Exception e){
            System.out.println("Probablemente hay un problema de una de las habitaciones: " + e.getMessage());
        }


        StringBuilder tieneBuilder = new StringBuilder();
        for (int i = 0; i < tiene.size(); i++) {
            tieneBuilder.append(tiene.get(i));
            if (i < tiene.size() - 1) {
                tieneBuilder.append(", ");
            }
        }

        StringBuilder noTieneBuilder = new StringBuilder();
        for (int i = 0; i < no_tiene.size(); i++) {
            noTieneBuilder.append(no_tiene.get(i));
            if (i < no_tiene.size() - 1) {
                noTieneBuilder.append(", ");
            }
        }

        JLabel lblTiene = new JLabel("Tiene: " + tieneBuilder);
        lblTiene.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblTiene);

        JLabel lblNoTiene = new JLabel("No Tiene: " + noTieneBuilder);
        lblNoTiene.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblNoTiene);

        JLabel lblTamanoM2 = new JLabel("Tamaño en m^2: " + hab.getTamanhoM2());
        lblTamanoM2.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblTamanoM2);

        JLabel lblVoltajeAC = new JLabel("Voltaje AC: " + hab.getVoltajeAC());
        lblVoltajeAC.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblVoltajeAC);

        JLabel lblCaracteristicasHotel = new JLabel("Características del Hotel");
        lblCaracteristicasHotel.setAlignmentX(CENTER_ALIGNMENT);
		lblCaracteristicasHotel.setFont(new Font(lblCaracteristicasHotel.getFont().getFontName(), Font.BOLD, 16));
        panel.add(lblCaracteristicasHotel);

        JLabel lblParqueaderoGratuito = new JLabel("Parqueadero gratuito");
        lblParqueaderoGratuito.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblParqueaderoGratuito);

        JLabel lblPiscina = new JLabel("Piscina");
        lblPiscina.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblPiscina);

        JLabel lblZonasHumeda = new JLabel("Zonas húmedas");
        lblZonasHumeda.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblZonasHumeda);

        JLabel lblBBQ = new JLabel("BBQ");
        lblBBQ.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblBBQ);

        JLabel lblWifiGratis = new JLabel("Wifi gratis");
        lblWifiGratis.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblWifiGratis);

        JLabel lblRecepcion24h = new JLabel("Recepción 24 horas");
        lblRecepcion24h.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblRecepcion24h);

        JLabel lblAdmiteMascotas = new JLabel("Admite mascotas");
        lblAdmiteMascotas.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblAdmiteMascotas);
	
    }
}
