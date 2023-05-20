package GUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Habitacion;

import java.awt.BorderLayout;
import java.awt.Font;

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
