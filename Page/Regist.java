package Page;
import javax.swing.*;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;



public class Regist implements ActionListener {
private JFrame frame;
private JPanel north,center,south;
private JLabel ins,tajuk,genre,author,phone;
private JTextField nbook,gbook,abook,pone;
private JButton addB;
	public Regist() {
		frame=new JFrame("User Registration");
		//frame.setLayout(new FlowLayout());
		frame.setSize(450,200);
		init();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
public void init() {
	north=new JPanel();
	ins=new JLabel("Insert all the detail");
	Font f=new Font("SansSerif",Font.BOLD,14);
	ins.setFont(f);
	north.add(ins);
	frame.add(north,BorderLayout.NORTH);
	
	center=new JPanel();
	center.setLayout(new GridLayout(4,2));
	tajuk=new JLabel("Name: ");
	center.add(tajuk);
	nbook=new JTextField(255);
	center.add(nbook);
	author=new JLabel("Email: ");
	center.add(author);
	abook=new JTextField(250);
	center.add(abook);
	genre=new JLabel("Password: ");
	center.add(genre);
	gbook=new JTextField(300);
	center.add(gbook);
	phone=new JLabel("Phone: ");
	center.add(phone);
	pone=new JTextField(300);
	center.add(pone);
	south=new JPanel();
	south.setLayout(new FlowLayout());
	addB=new JButton("Add");
	addB.addActionListener(this);
	south.add(addB);
    tajuk.setFont(f);
    genre.setFont(f);
    author.setFont(f);
	frame.add(south, BorderLayout.SOUTH);
	frame.add(center, BorderLayout.CENTER);
	
}
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("Add")) {
		String BorrowName=nbook.getText();
		String BorrowEmail=abook.getText();
		String BorrowPassword=gbook.getText();
		String BorrowPhone=pone.getText();
		PreparedStatement ps;
		ResultSet rs;
		String query="select * from borrower where BorrowEmail = ?";
		try {
			
			ps = MyDatabase.doConnection().prepareStatement(query);
			
			ps.setString(1,BorrowEmail );
		rs = ps.executeQuery();
		
         if(rs.next()) {
        	 JOptionPane.showMessageDialog(nbook,"Email you enter already exist!");
         }
         else {
        	 PreparedStatement ps2;
     		//ResultSet rs;
     		String query2="Insert into borrower(BorrowName,BorrowEmail,BorrowPassword,BorrowPhone)Values(?,?,?,?)";
     	try {
     		ps2=MyDatabase.doConnection().prepareStatement(query2);
     		ps2.setString(1,BorrowName);
     		ps2.setString(2,BorrowEmail);
     		ps2.setString(3,BorrowPassword);
     		ps2.setString(4,BorrowPhone);
     		int rowsInserted = ps2.executeUpdate();
     		if (rowsInserted > 0) {
     		    
     			JOptionPane.showMessageDialog(null,"Successfully registered!");
     			frame.dispose();
     			
     		}
     		ps.close();
     	}catch  (Exception ex) {
     		ex.printStackTrace();
     	}
         }
        }catch  (Exception ex) {ex.printStackTrace();}}
	else
	{
		JOptionPane.showMessageDialog(null, "Enter data!.");
	}
}
}
