package GUI;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class VentanaAdmin extends JFrame {
	private JTabbedPane pestañas, panel3;
	private panelRooms panel1;
	private panelServices panel2;
	private panelOcupacion panelOccup;
	
	public VentanaAdmin() {
		setLocationRelativeTo(null);
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		pestañas = new JTabbedPane();
		panel1 = new panelRooms();
		panel2= new panelServices();
		panel3 = new JTabbedPane();
		panelOccup = new panelOcupacion();
		panel3.add("Occupacion", panelOccup);
		pestañas.add("Services", panel2);
		pestañas.add("Rooms", panel1);
		pestañas.add("Stats", panel3);
		getContentPane().add(pestañas);


		
	}

}
