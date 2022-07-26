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
public class delete implements ActionListener{
private JFrame frame;
private JPanel north,center;
private JLabel userid;
private JButton search;
private JTextField id;


public delete() {
frame=new JFrame("Delete User");
frame.setSize(300,200);
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
	
	search=new JButton("Delete");
	search.addActionListener(this);
	north.add(search);
	frame.add(north,BorderLayout.NORTH);
	
	
	
	
	Font ft=new Font("TimesRoman",Font.PLAIN,14);

	userid.setFont(ft);
	
	search.setFont(ft);

}
public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("Delete")) {
		String BorrowID=id.getText();
		if(BorrowID.equals(""))//null
		{
		JOptionPane.showMessageDialog(null,"Please enter User ID you would like to delete."); 
	}
	else {
		try {
		PreparedStatement ps;
		ResultSet rs;
	
		String query = "SELECT *FROM Borrower WHERE BorrowID=?";
		
		ps = MyDatabase.doConnection().prepareStatement(query);
		
		ps.setString(1,BorrowID );
	rs = ps.executeQuery();
	
		if(rs.next()) {

	String query1 = "Delete from borrower where BorrowID=?";	
	try {
		PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);

		ps1.setString(1, BorrowID);
		ps1.executeUpdate();
		JOptionPane.showMessageDialog(id,"User delete successfully.");
		ps1.close();

		frame.dispose();
		
	    DeleteUser.tutup();
	    ViewDetail ud = new ViewDetail();}
	catch (Exception ex) {
		JOptionPane.showMessageDialog(id,"Failed to delete! ID you enter is an active user.");
     ex.printStackTrace();}
		}
		else {
			JOptionPane.showMessageDialog(id,"ID not exist.");
		}
	}catch (Exception ex) {
	
		
		ex.printStackTrace();
	}
}}
	
		
	}

}
