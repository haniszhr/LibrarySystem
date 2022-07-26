package Admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import Connection.MyDatabase;
public class calculate implements ActionListener{
private JFrame frame;
private JPanel north,center,south;
private JLabel transid,duration,payment;
private JTextField id,day,total;
private JButton search,pay;

	public calculate() {
		frame=new JFrame("Calculation Fine");
	
		frame.setSize(500,350);
	
	init();
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void init() {
		north=new JPanel();
		north.setLayout(new FlowLayout());
		transid=new JLabel("Transaction ID:");
		north.add(transid);
		id=new JTextField(6);
		north.add(id);
		search=new JButton("Search");
		search.addActionListener(this);
		north.add(search);
		frame.add(north,BorderLayout.NORTH);
		
		center=new JPanel();
		center.setLayout(new GridLayout(2,1));
		duration=new JLabel("Total days book return late:");
		center.add(duration);
		day=new JTextField(25);
		center.add(day);
		payment=new JLabel("Total Fine:RM");
		center.add(payment);
		total=new JTextField(25);
		center.add(total);
		frame.add(center,BorderLayout.CENTER);
		south=new JPanel();
		south.setLayout(new FlowLayout());
		pay=new JButton("Paid");
		pay.addActionListener(this);
				south.add(pay);
				frame.add(south,BorderLayout.SOUTH);
	    
	}
public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Search")) {
		String TransactionID=id.getText();
		//String Duration=day.getText();
		if(TransactionID.equals(""))//null
		{
		JOptionPane.showMessageDialog(null,"Please enter TransactionID."); //Display dialog box with the message
	}
	else {
		PreparedStatement ps;
		ResultSet rs;
		
		String query = "select TransactionID,Duration,(duration*0.5) as Fine from Borrow where TransactionID = ? AND StatusReturn='Return' AND StatusPayment='Overdue'";
		try {	
		ps = MyDatabase.doConnection().prepareStatement(query);
			ps.setString(1, TransactionID);
		//	ps.setString(2, Duration);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{
				String d = rs.getString(1);
				String d1 = rs.getString(2);
				String d2=rs.getString(3);
				
				id.setText(d);
				day.setText(d1);
				total.setText(d2);
			}
			else {
				JOptionPane.showMessageDialog(null,"This id you enter either has paid,not overdue,not exist or the status hasn't being update.");
			}
			}catch (Exception ex) {ex.printStackTrace();}
		
	}
		}
		else if(e.getActionCommand().equals("Paid")) {
		String TransactionID=id.getText();
			PreparedStatement ps1;
			String query1="update Borrow set StatusPayment = 'Paid' where TransactionID = ?";
			try {ps1=MyDatabase.doConnection().prepareStatement(query1);
    		
    		ps1.setString(1,TransactionID);
    		ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(id, "Borrower successfully paid");
			frame.dispose();
			FineCalculation.tutup();
		}catch (Exception ex) {ex.printStackTrace();}
			
		
		}
}}