package Admin;


import javax.swing.*;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class AdminLogin implements ActionListener{
	private JFrame frame;
	private JPanel south,center,north;
	private JLabel labelBEmail, labelBPassword, instruc;
	private JTextField TFuser;
	private JPasswordField TFpswd;
	private JButton JBLogin;

	public AdminLogin(){
	frame=new JFrame("LOGIN PAGE");
	frame.pack();
	frame.setSize(400, 200);
	init();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
}

public void init() {
		
		north=new JPanel();
		north.setLayout(new GridLayout(2,2));
		instruc=new JLabel("Fill in below: ");
		north.add(instruc);
		center = new JPanel();
		center.setLayout(new GridLayout(2,2));
		
		labelBEmail = new JLabel(" Email : ");
		center.add(labelBEmail);
		TFuser = new JTextField(250);
		center.add(TFuser);
		
		labelBPassword= new JLabel(" Password : ");
		center.add(labelBPassword);
		TFpswd = new JPasswordField(60);
		center.add(TFpswd);
		
		south = new JPanel();
		JBLogin = new JButton ("SIGN IN");
		JBLogin.addActionListener(this);
		south.add(JBLogin);
		
		Font tulisan = new Font("TimesRoman", Font.PLAIN, 15);
		labelBPassword.setFont(tulisan);
		labelBEmail.setFont(tulisan);
		JBLogin.setFont(tulisan);
		
		frame.add(north, BorderLayout.NORTH);
		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		
	}


public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("SIGN IN")) {
			
			String AdminEmail = TFuser.getText();
			String AdminPassword= TFpswd.getText();
			if(AdminEmail.equals("")) //If username is null
			{
				JOptionPane.showMessageDialog(null,"Please enter email"); //Display dialog box with the message
			} 
			else if(AdminPassword.equals("")) //If password is null
			{
				JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
			}
			else
			{				
				PreparedStatement ps;
				ResultSet rs;
				
				String query = "SELECT * FROM Admin WHERE AdminEmail = ? AND AdminPassword = ?";
				
				try {
					ps = MyDatabase.doConnection().prepareStatement(query);
					
					ps.setString(1, AdminEmail);
					ps.setString(2, AdminPassword);
					
					rs = ps.executeQuery();
					
					if(rs.next())
					{								
						MenuAdmin menu = new MenuAdmin();
						frame.dispose();
					}
					else
					{
						 JOptionPane.showMessageDialog(null, "Incorrect Email Or Password", "Login Failed", 2);
					}
					ps.close();
					rs.close();
					
				}catch  (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}	

}
