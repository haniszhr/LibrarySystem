package Page;

import javax.swing.*;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class LoginPage implements ActionListener{
	private JFrame frame;
	private JPanel south,center,north;
	private JLabel labelBEmail, labelBPassword, instruc;
	private JTextField TFuser,id;
	private JPasswordField TFpswd;
	private JButton JBLogin,signup;
   public String BorrowEmail;
   public String BorrowID;
	public LoginPage(){
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
		id=new JTextField(6);
		south = new JPanel();
		JBLogin = new JButton ("SIGN IN");
		JBLogin.addActionListener(this);
		south.add(JBLogin);
		signup = new JButton ("REGISTRATION");
		signup.addActionListener(this);
		south.add(signup);
		Font tulisan = new Font("TimesRoman", Font.PLAIN, 15);
		labelBPassword.setFont(tulisan);
		labelBEmail.setFont(tulisan);
		JBLogin.setFont(tulisan);
		signup.setFont(tulisan);
		frame.add(north, BorderLayout.NORTH);
		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		
	}


public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("SIGN IN")) {
			BorrowID=id.getText();
			 BorrowEmail = TFuser.getText();
			String BorrowPassword= TFpswd.getText();
			if(BorrowEmail.equals("")) //If username is null
			{
				JOptionPane.showMessageDialog(null,"Please enter email"); //Display dialog box with the message
			} 
			else if(BorrowPassword.equals("")) //If password is null
			{
				JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
			}
			else
			{				
				PreparedStatement ps;
				ResultSet rs;
				
				String query = "SELECT * FROM Borrower WHERE BorrowEmail = ? AND BorrowPassword = ?";
				
				try {
					ps = MyDatabase.doConnection().prepareStatement(query);
					
					ps.setString(1, BorrowEmail);
					ps.setString(2, BorrowPassword);
					
					rs = ps.executeQuery();
					
					if(rs.next())
					{	frame.dispose();							
						Menu menu = new Menu();
					}
					else
					{
						 JOptionPane.showMessageDialog(null, "Incorrect Email Or Password or Not Registered User");
					}
					ps.close();
					rs.close();
				}catch  (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		else if(e.getActionCommand().equals("REGISTRATION")) {
			Regist n= new Regist();
			
		}
		
	}	

}
