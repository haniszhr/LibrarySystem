package Admin;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Connection.MyDatabase;
import javax.swing.*;
public class rbook implements ActionListener {
private JFrame frame;
private JPanel center,south;
private JLabel tid,bid,date;
private JTextField idtrans,idbook,redate;
private JButton returnb;
	public rbook() {
		frame=new JFrame("Return Book");
		frame.setSize(700,450);
		 init();
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void init() {
		center=new JPanel();
		center.setLayout(new GridLayout(3,1));
		tid=new JLabel("TransactionID:");
	    center.add(tid);
	    idtrans=new JTextField(6);
	    center.add(idtrans);
	    bid=new JLabel("Book ID:");
	    center.add(bid);
	    idbook=new JTextField(6);
	    center.add(idbook);
	    date=new JLabel("Return Date(YYYY-MM-DD):");
	    center.add(date);
	    redate=new JTextField(25);
	    center.add(redate);
	    frame.add(center,BorderLayout.CENTER);
		
	    south=new JPanel();
	    south.setLayout(new FlowLayout());
	   
	    returnb=new JButton("Return Book");
	    returnb.addActionListener(this);
	    south.add(returnb);
	    frame.add(south,BorderLayout.SOUTH);
		
		
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Return Book")) {
			String BookID=idbook.getText();
			String TransactionID=idtrans.getText();
			String ReturnBookDate=redate.getText();
			PreparedStatement ps;
			ResultSet rs;
			
			String query = "select b.TransactionID,k.BookID from Borrow b inner join Book k on b.BookID=k.BookID where  b.TransactionID= ?  and b.BookID = ? AND StatusReturn='Not Return'";
			try {
				ps = MyDatabase.doConnection().prepareStatement(query);
				ps.setString(1, TransactionID);
				ps.setString(2, BookID);
				rs = ps.executeQuery();
				if(rs.next())
				{
					PreparedStatement ps1;
					String query1="UPDATE `borrow` SET ReturnBookDate = ?, StatusReturn ='Return' WHERE TransactionID = ?";
					try {ps1=MyDatabase.doConnection().prepareStatement(query1);
	        		ps1.setString(1,ReturnBookDate);
	        		ps1.setString(2,TransactionID);
	        		ps1.executeUpdate();
    				
    				ps1.close();
					
    				PreparedStatement ps2;
    				String query2="update Book set Availability = 'Yes' where BookID = ?";
    				try {ps2=MyDatabase.doConnection().prepareStatement(query2);
	        		ps2.setString(1,BookID);
	        		ps2.executeUpdate();
	        		ps2.close();
	        		
	        		PreparedStatement ps3;
	        		String query3="UPDATE `borrow` SET `Duration` =(SELECT DateDiff(ReturnBookDate,Return_Date))WHERE `borrow`.`TransactionID` = ?";
	        		try {ps3=MyDatabase.doConnection().prepareStatement(query3);
	        		ps3.setString(1,TransactionID);
	        		ps3.executeUpdate();
	        		ps3.close();
	        
	        		JOptionPane.showMessageDialog(null,"Successfully return book.");
	        		frame.dispose();
	        		Listborrow.tutup();
	        		}catch (Exception ex) {ex.printStackTrace();}
	        		
    				}catch (Exception ex) {ex.printStackTrace();}
					
					
					}catch  (Exception ex) {
						ex.printStackTrace();}
				}
				else
				{
					 JOptionPane.showMessageDialog(null, "You have enter mismatch ID between TransactionID and BookID!Press try again");
				}
				
			}catch  (Exception ex) {
				ex.printStackTrace();}
			
		
		}
		
	}
}
