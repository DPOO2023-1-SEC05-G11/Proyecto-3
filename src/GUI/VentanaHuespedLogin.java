package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Persistencia.Hotel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;



public class VentanaHuespedLogin extends JFrame implements ActionListener {
	
	JPanel panelPrincipal;
	JTextField textFieldUsuario, textFieldContraseña;
	JButton acceptarButton, newUserButton;
	
	public VentanaHuespedLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 302);
		setLocationRelativeTo(null);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(null);
		
		acceptarButton = new JButton("Aceptar");
		acceptarButton.setBounds(71, 158, 120, 21);
		panelPrincipal.add(acceptarButton);
		acceptarButton.addActionListener(this);
		getRootPane().setDefaultButton(acceptarButton);

        newUserButton = new JButton("Nuevo Usuario");
		newUserButton.setBounds(206, 158, 120, 21);
		panelPrincipal.add(newUserButton);
		newUserButton.addActionListener(this);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(138, 83, 189, 19);
		panelPrincipal.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		textFieldContraseña = new JPasswordField();
		textFieldContraseña.setColumns(10);
		textFieldContraseña.setBounds(138, 112, 189, 19);
		panelPrincipal.add(textFieldContraseña);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(54, 86, 45, 13);
		panelPrincipal.add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(54, 115, 74, 13);
		panelPrincipal.add(lblPassword);

        Hotel.getInstance().cargarDatos();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==acceptarButton) {
			String usuario = textFieldUsuario.getText();
			String contra = textFieldContraseña.getText();
			Boolean access = Hotel.getInstance().accesoHuesped(usuario, contra);
			if (access) {
                VentanaHuesped ventana = new VentanaHuesped();
                ventana.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecto");
				textFieldUsuario.setText("");
				textFieldContraseña.setText("");
			} 
			
		} else if (e.getSource()==newUserButton){
            JTextField usernameField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            
            Object[] message = {
                "Username:", usernameField,
                "Password:", passwordField
            };
            
            while (true) {
                int option = JOptionPane.showConfirmDialog(this, message, "New User Registration", JOptionPane.OK_CANCEL_OPTION);
                
                if (option == JOptionPane.OK_OPTION) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    
                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Username and password fields cannot be empty. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                    if (!Hotel.getInstance().usernameAvailable(username))
                    {
                        JOptionPane.showMessageDialog(this, "Username already taken. Please enter another and try again.", "Username Taken", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                   
                    Hotel.getInstance().newHuespedUsuario(username, password);
                    
                    // Process the new user registration
                    // ... (your code to handle the new user registration)
                    
                    JOptionPane.showMessageDialog(this, "New user registered: " + username);
                }
                
                break;
            }
        }
	}

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaHuespedLogin ventana = new VentanaHuespedLogin();
                    ventana.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
