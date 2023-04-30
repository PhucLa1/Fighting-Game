package Auth;

import javax.swing.*;
import javax.swing.border.Border;

import main.Game;

import java.awt.*;
import java.awt.event.*;
import java.security.Signature;

public class LoginFrame extends JFrame implements ActionListener {

	
    JLabel userLabel = new JLabel("Username");
    JTextField userTextField = new JTextField();
    
    JLabel passLabel = new JLabel("Password");
    JPasswordField passTextField = new JPasswordField();
    
    JButton loginButton = new JButton("Login");
    JButton signUp = new JButton("Sign Up");
    Connect connect;
    public int x =3;
    
    public LoginFrame(Connect connect) {
    	this.connect=connect;
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        userLabel.setBounds(480, 250, 100, 30);
        userTextField.setBounds(580, 250, 150, 30);
        passLabel.setBounds(480, 300, 500, 30);
        passTextField.setBounds(580, 300, 150, 30);       
        loginButton.setBounds(480, 350, 100, 30);
        signUp.setBounds(630, 350, 100, 30);
        add(userLabel);
        add(userTextField);
        add(passLabel);
        add(passTextField);
        add(loginButton);
        add(signUp);
		add(new BackGround());
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
        loginButton.addActionListener(this);
        signUp.addActionListener(this);  
        setVisible(true);
    }
    

    public void actionPerformed(ActionEvent e) {
    	System.out.println(x);
        if (e.getSource() == loginButton) {
            String username = userTextField.getText();
            String password = String.valueOf(passTextField.getPassword());
            
            if (Connect.returnPlayer(username, password)) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                
                //Khoi tao vao tro choi
                setVisible(false);
                Game game= new Game();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } else if (e.getSource() == signUp) {
        	dispose(); //dong khung hien tai va giai phong
        	new signUp(connect);
        }

    }
}

