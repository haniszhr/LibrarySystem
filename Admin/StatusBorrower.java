package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class StatusBorrower implements ActionListener{
	private JTable t;
	private JFrame f;
	private JPanel south,center;
	private JButton back,nocharge;
	private JLabel transid;
	private JTextField id;
	private DefaultTableModel t1;
	
	public StatusBorrower() {
		
		f=new JFrame("View Status");
		f.setSize(900,450);
	init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		BookData();
		Updatestatus();}
	public void init() {
		
		t1=new DefaultTableModel();
		t=new JTable(t1);
		t.setModel(t1);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.setPreferredScrollableViewportSize(new Dimension(800, 300));
        t.setFillsViewportHeight(true);
        f.add(new JScrollPane(t));
        t1.addColumn("Transaction ID");
        t1.addColumn("Title");
        t1.addColumn("Borrower's Name ");
        t1.addColumn("Status Payment");
        t1.addColumn("Status Return"); 
        t1.addColumn("Date Book was Returned"); 
        t1.addColumn("Date supposed to Return"); 
        t1.addColumn("Days Late Return"); 
      
        
       
	}
	
	

	public void BookData() {
	
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query = "select t.TransactionID, bk.Title, br.BorrowName, t.StatusPayment,t.StatusReturn,t.ReturnBookDate,t.Return_Date,t.Duration from BORROWER br inner join Borrow t on br.BorrowID = t.BorrowID inner join BOOK bk on t.BookID = bk.BookID WHERE t.StatusPayment='No fine'";
			
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(query);;
			
         while (rs.next()) {
	        	
	        	
	        	
				t1.addRow(new Object[]  {
	        			rs.getString("TransactionID"),
	        			rs.getString("Title"),
	        			rs.getString("BorrowName"),
	        			rs.getString("StatusPayment"),
	        			rs.getString("StatusReturn"),
	        			rs.getString("ReturnBookDate"),
	        			rs.getString("Return_Date"),
	        			rs.getString("Duration")
	        });
	        }
         
         f.dispose();
	        rs.close();
	      preparedStatement.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}
	public void Updatestatus() {
		center=new JPanel();
		center.setLayout(new GridLayout(1,1));
		transid=new JLabel("TransactionID:");
		center.add(transid);
		id=new JTextField(6);
		center.add(id);
		f.add(center,BorderLayout.CENTER);
		  south=new JPanel();
	        south.setLayout(new FlowLayout());
	        back=new JButton("Overdue");
	        back.addActionListener(this);
	        south.add(back);
	        nocharge=new JButton("No Charge");
	        nocharge.addActionListener(this);
	        south.add(nocharge);
	        f.add(south, BorderLayout.SOUTH);
	}
public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Overdue")) {
			String TransactionID=id.getText();
			PreparedStatement ps;
			ResultSet rs;
			
			String query = "select TransactionID from Borrow where  TransactionID= ? AND StatusPayment='No Fine'";
			try {
				ps = MyDatabase.doConnection().prepareStatement(query);
				ps.setString(1, TransactionID);
				
				rs = ps.executeQuery();
				if(rs.next())
				{
					PreparedStatement ps1;
					String query1="update Borrow set StatusPayment = 'Overdue' where TransactionID = ?";
					try {ps1=MyDatabase.doConnection().prepareStatement(query1);
	        		
	        		ps1.setString(1,TransactionID);
	        		ps1.executeUpdate();
    				
    				ps1.close();
    				JOptionPane.showMessageDialog(id, "Status successfully update to overdue");
    				f.dispose();
				}catch (Exception ex) {ex.printStackTrace();}
					
					}
				else {
					JOptionPane.showMessageDialog(null, "Wrong Transaction ID");
					
				}
				
			
			}catch (Exception ex) {ex.printStackTrace();}
			
		}
		
		if(e.getActionCommand().equals("No Charge")) {
			String TransactionID=id.getText();
			PreparedStatement ps;
			ResultSet rs;
			
			String query = "select TransactionID from Borrow where  TransactionID= ? AND StatusPayment='No Fine'";
			try {
				ps = MyDatabase.doConnection().prepareStatement(query);
				ps.setString(1, TransactionID);
				
				rs = ps.executeQuery();
				if(rs.next())
				{
					PreparedStatement ps1;
					String query1="update Borrow set StatusPayment = 'No Charge' where TransactionID = ?";
					try {ps1=MyDatabase.doConnection().prepareStatement(query1);
	        		
	        		ps1.setString(1,TransactionID);
	        		ps1.executeUpdate();
    				
    				ps1.close();
    				JOptionPane.showMessageDialog(id, "Status successfully update to no charge");
    				f.dispose();
				}catch (Exception ex) {ex.printStackTrace();}
					
					}
				else {
					JOptionPane.showMessageDialog(null, "Wrong Transaction ID");
					
				}
				
			
			}catch (Exception ex) {ex.printStackTrace();}
			
		}

}}