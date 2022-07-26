package Admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;

public class BorrowBook implements ActionListener {
	private JTable t;
	private JFrame f;
	private JPanel north,center,south;
	private JLabel ins,Bookid,bor_id,b_date,r_date;
	protected JTextField idb,borid,bdate,rdate;
	private JButton borrow;
	private DefaultTableModel t1;
	public BorrowBook() {
		f=new JFrame("List Book Available");
		f.setSize(700,600);

		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		north=new JPanel();
		ins=new JLabel("List of book that available for borrowing");
		north.add(ins);
		
		f.add(north,BorderLayout.NORTH);
		init();
		BookData();
	    insertBook1();
	}
	
	public void init() {
		t1=new DefaultTableModel();
		t=new JTable(t1);
		t.setModel(t1);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.setPreferredScrollableViewportSize(new Dimension(600, 300));
        t.setFillsViewportHeight(true);
        f.add(new JScrollPane(t));
        t1.addColumn("Book ID");
        t1.addColumn("Title");
        t1.addColumn("Genre ");
        t1.addColumn("Author");
      
        }
	
	   
public void BookData() {
	
	try {
		
		Connection conn = MyDatabase.doConnection();
		String query = "SELECT BookID,Title,Genre,AuthorName FROM `book` where Availability='Yes' ";
		
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery(query);;
		
     while (rs.next()) {
        	
        	
        	
			t1.addRow(new Object[]  {
        			rs.getString("BookID"),
        			rs.getString("Title"),
        			rs.getString("Genre"),
        			rs.getString("AuthorName"),
        			
        });
        }
        rs.close();
      preparedStatement.close();
        conn.close();
}catch (Exception ex) {ex.printStackTrace();

}f.setVisible(true);
f.validate();
}
public void insertBook1() {
	center=new JPanel();
	center.setLayout(new GridLayout(4,2));
	Bookid=new JLabel("Book ID: ");
	center.add(Bookid);
	idb=new JTextField(6);
	center.add(idb);
	bor_id=new JLabel("Borrower ID:");
	center.add(bor_id);
	borid=new JTextField(6);
	center.add(borid);
	b_date=new JLabel("Borrow Date(YYYY-MM-DD):");
	center.add(b_date);
	bdate=new JTextField(25);
	center.add(bdate);
	r_date=new JLabel("Return Date(YYYY-MM-DD):");
	center.add(r_date);
	rdate= new JTextField(25);
	center.add(rdate);
	f.add(center,BorderLayout.CENTER);
	south=new JPanel();
	south.setLayout(new FlowLayout());
	borrow=new JButton("Borrow");
	borrow.addActionListener(this);
	south.add(borrow);
	f.add(south,BorderLayout.SOUTH);
	
}
public void actionPerformed(ActionEvent e) {
	String BookID=idb.getText();String BorrowID=borid.getText();
	String Borrow_Date=bdate.getText();
	String Return_Date=rdate.getText();
	if(e.getActionCommand().equals("Borrow")) {
		if(BookID.equals(""))//null
		{
		JOptionPane.showMessageDialog(null,"Please enter Book ID you would like to borrow."); //Display dialog box with the message
	}
	else {
		PreparedStatement ps3;
		ResultSet rs3;
		String query3="Select BorrowID from Borrower where BorrowID=?";
		try {
			ps3=MyDatabase.doConnection().prepareStatement(query3);
           ps3.setString(1, BorrowID);
			
			rs3 = ps3.executeQuery();
			
			if(rs3.next())
			{	
		
		PreparedStatement ps;
		ResultSet rs;
		
		String query = "SELECT BookID FROM Book WHERE Availability='yes' and BookID=?";
		try {
			ps = MyDatabase.doConnection().prepareStatement(query);
			
			ps.setString(1, BookID);
			
			rs = ps.executeQuery();
			
			if(rs.next())
			{	
				
				PreparedStatement ps1;
				String query1="insert into Borrow (BookID,BorrowID,Borrow_Date,Return_Date)values (?,?,?,?)";
                try {ps1=MyDatabase.doConnection().prepareStatement(query1);
        		ps1.setString(1,BookID);
        		ps1.setString(2,BorrowID);
        		ps1.setString(3,Borrow_Date);
        		ps1.setString(4,Return_Date);
        		int rowsInserted = ps1.executeUpdate();
        		if (rowsInserted > 0) {
        		    
        			JOptionPane.showMessageDialog(idb,"Successfully borrow book");
        			  PreparedStatement ps2;
        			String query2 = "Update book set Availability='No' where BookID=?";	
        			try {
        				 ps2 = MyDatabase.doConnection().prepareStatement(query2);
        				
        				ps2.setString(1, BookID);
        				
        				ps2.executeUpdate();
        				
        				ps2.close();
        				
        				f.dispose();
        				
        				}catch  (Exception ex) {ex.printStackTrace();}}
        		ps1.close();
        	
                	
                }catch  (Exception ex) {JOptionPane.showMessageDialog(null,"Insert all info");
    				ex.printStackTrace();}
                
			
			}
		
			else
			{
				 JOptionPane.showMessageDialog(null, "Book Id you enter not exist or currently not available to borrow");
			}
			ps.close();
			rs.close();
		}catch  (Exception ex) {ex.printStackTrace();}}
			else {
				 JOptionPane.showMessageDialog(borid, "Borrow Id you enter not exist ");
					
			}
	}catch (Exception ex) {ex.printStackTrace();}
	}
	}

}
}

