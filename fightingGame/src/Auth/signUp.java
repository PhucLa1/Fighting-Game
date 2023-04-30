package Auth;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import inputs.KeyboardInputs;

public class signUp extends JFrame implements ActionListener {

	 	JLabel userLabel = new JLabel("Username");
	    JTextField userTextField = new JTextField();
	    
	    JLabel passLabel = new JLabel("Password");
	    JPasswordField passTextField = new JPasswordField();
	    
	    JLabel repassLabel = new JLabel("Repassword");
	    JPasswordField repassTextField = new JPasswordField();
	    
	    JButton signUpButton = new JButton("Sign up");
	    JButton login = new JButton("Login");
	    
	    Connect connect;

	    
	    public signUp(Connect connect) {
			// TODO Auto-generated constructor stub
	    	this.connect=connect;
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        userLabel.setBounds(480, 200, 100, 30);
	        userTextField.setBounds(580, 200, 150, 30);	        
	        passLabel.setBounds(480, 250, 500, 30);
	        passTextField.setBounds(580, 250, 150, 30);	        
	        repassLabel.setBounds(480, 300, 500, 30);
	        repassTextField.setBounds(580, 300, 150, 30);	        
	        signUpButton.setBounds(480, 350, 100, 30);
	        login.setBounds(630, 350, 100, 30); 
	        add(userLabel);
	        add(userTextField);
	        add(passLabel);
	        add(passTextField);
	        add(repassLabel);
	        add(repassTextField);
	        add(signUpButton);
	        add(login);
			add(new BackGround());
			setResizable(false);
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
			setLayout(null);
	        signUpButton.addActionListener(this);
	        login.addActionListener(this);
	        setVisible(true);
	    }
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==signUpButton ) {
            String username = userTextField.getText();
            String password = String.valueOf(passTextField.getPassword());
            String repassString = String.valueOf(repassTextField.getPassword());
            if(checkSignUp(username, password, repassString)=="") {
            	if(connect.SignUp(username, password)) {
            		JOptionPane.showMessageDialog(this, "Sign Up Succesfull");
                	dispose(); //dong khung hien tai va giai phong
                	LoginFrame lgFrame= new LoginFrame(connect);
                	lgFrame.userTextField.setText(username);
                	lgFrame.passTextField.setText(password);
            	}else {
            		JOptionPane.showMessageDialog(this, "This username has already exist");
            	}
            }else {
            	String mesString = checkSignUp(username, password, repassString);
            	JOptionPane.showMessageDialog(this, mesString);
            }
		}else {
        	dispose(); //dong khung hien tai va giai phong
        	new LoginFrame(connect);
		}
		
	}
	
	public String checkSignUp(String user,String pass,String repass) {
		String resString = "";
		if(user.length()==0) {
			resString += "\nUser field cannot be blank";
		}
		if(pass.length()==0) {
			resString += "\nPass field cannot be blank";
		}
		if(!pass.equals(repass)) {
			resString += "\nPass and repass are not the same";
		}
		if(user.length()<6) {
			resString += "\nUser length must be at least 6 character";
		}
		if(pass.length()<8) {
			resString += "\nPass length must be at least 8 character";
		}
		return resString;
	}



}
