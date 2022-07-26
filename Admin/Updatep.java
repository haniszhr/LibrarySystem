package Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import Connection.MyDatabase;
public class Updatep implements ActionListener{
private JFrame frame;
private JPanel north,center,south,south1;
private JLabel name,email,password,userid,UId,phone;
private JButton search, nameb,emailb,passwordB,phoneB,back;
private JTextField id,nama,email1,password1,phone1,Id;


public Updatep() {
frame=new JFrame("Update User Detail");
frame.setSize(700,500);
init();
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setVisible(true);
}
public void init() {
	north=new JPanel();
	north.setLayout(new FlowLayout());
	userid=new JLabel("User Id:");
	north.add(userid);
	id=new JTextField(6);
	north.add(id);
	
	search=new JButton("Search");
	search.addActionListener(this);
	north.add(search);
	frame.add(north,BorderLayout.NORTH);
	
	center=new JPanel();
	center.setLayout(new GridLayout(5,2));
	UId=new JLabel("User ID:");
	center.add(UId);
	Id=new JTextField(6);
	center.add(Id);
	
	name=new JLabel("Name:");
	center.add(name);
	nama=new JTextField(255);
	center.add(nama);
	
	email=new JLabel("Email:");
	center.add(email);
	email1=new JTextField(300);
	center.add(email1);
	
	password=new JLabel("Password:");
	center.add(password);
	password1=new JTextField(250);
	center.add(password1);
	phone=new JLabel("Phone:");
	center.add(phone);
	phone1=new JTextField(250);
	center.add(phone1);
	
	south=new JPanel();
	south.setLayout(new FlowLayout());
	nameb=new JButton("Update Name");
	nameb.addActionListener(this);
	south.add(nameb);
	emailb=new JButton("Update Email");
	emailb.addActionListener(this);
	south.add(emailb);
    passwordB=new JButton("Update Password");
	passwordB.addActionListener(this);
	south.add(passwordB);
	phoneB=new JButton("Update Phone");
	phoneB.addActionListener(this);
	south.add(phoneB);
	back=new JButton("Back");
	back.addActionListener(this);
	south.add(back);
	frame.add(south,BorderLayout.SOUTH);
	
	
	Font ft=new Font("TimesRoman",Font.PLAIN,14);
	UId.setFont(ft);
	name.setFont(ft);
	password.setFont(ft);
	email.setFont(ft);
	phone.setFont(ft);
	userid.setFont(ft);
	
	search.setFont(ft);
	frame.add(center,BorderLayout.CENTER);
	id.setEditable(true);
	nama.setEditable(true);

	email1.setEditable(true);
	phone1.setEditable(true);
	password1.setEditable(true);
	Id.setEditable(false);
}
public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("Search")) {
		String BorrowID=id.getText();
		if(BorrowID.equals(""))//null
		{
		JOptionPane.showMessageDialog(null,"Please enter User ID you would like to update."); //Display dialog box with the message
	}
	else {
		PreparedStatement ps;
		ResultSet rs;
		
		String query = "SELECT * FROM Borrower WHERE BorrowID = ?";
		try {
			ps = MyDatabase.doConnection().prepareStatement(query);
			
			ps.setString(1, BorrowID);
		rs = ps.executeQuery();
		
		if(rs.next()){
			String d = rs.getString(1);
			String d1 = rs.getString(2);
			String d2 = rs.getString(3);
		     String d3 = rs.getString(4);
		     String d4 = rs.getString(5);
				
			
			Id.setText(d);
			nama.setText(d1);
			email1.setText(d2);
			password1.setText(d3);
			phone1.setText(d4);
			
			}
		else {JOptionPane.showMessageDialog(id,"Ops! No data found.");}
	}catch (Exception ex) {ex.printStackTrace();
	}
}}
	else if(e.getActionCommand().equals("Back")) {
		MenuAdmin m=new MenuAdmin();
		frame.dispose();
		UpdateDetail1.tutup();
		
	}
	else if(e.getActionCommand().equals("Update Name")) {
		String BorrowID=id.getText();
		String BorrowName=nama.getText();
		String query1 = "Update borrower set BorrowName=? where BorrowID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, BorrowName);
			ps1.setString(2, BorrowID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(id,"Data update successfully.");
			frame.dispose();
			UpdateDetail1.tutup();
			ViewDetail vb=new ViewDetail();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}		
		
	}
	else if(e.getActionCommand().equals("Update Email")) {
		String BorrowID=id.getText();
		String BorrowEmail=email1.getText();
		String query1 = "Update borrower set BorrowEmail=? where BorrowID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, BorrowEmail);
			ps1.setString(2, BorrowID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(id,"Data update successfully.");
			frame.dispose();
			UpdateDetail1.tutup();
			ViewDetail vb=new ViewDetail();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}		
		
	}	
	else if(e.getActionCommand().equals("Update Password")) {
		String BorrowID=id.getText();
		String BorrowPassword=password1.getText();
		String query1 = "Update borrower set BorrowPassword=? where BorrowID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, BorrowPassword);
			ps1.setString(2, BorrowID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(id,"Data update successfully.");
			frame.dispose();
			
			UpdateDetail1.tutup();
			ViewDetail vb=new ViewDetail();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}		
		
	}
	else if(e.getActionCommand().equals("Update Phone")) {
		String BorrowID=id.getText();
		String BorrowPhone=phone1.getText();
		String query1 = "Update borrower set BorrowPhone=? where BorrowID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, BorrowPhone);
			ps1.setString(2, BorrowID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(id,"Data update successfully.");
			frame.dispose();
			UpdateDetail1.tutup();
			ViewDetail vb=new ViewDetail();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}		
		
	}	
		
		
	}

}
