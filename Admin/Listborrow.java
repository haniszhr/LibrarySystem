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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;
public class Listborrow implements ActionListener{
	private JTable t;
	private static JFrame f;
	private DefaultTableModel t1;
	private JPanel south;
	
	private JButton returnb;
	public Listborrow() {
		f=new JFrame("List Book that still not being return");
		f.setSize(700,450);
		init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		BorrowingData();
		
	}
	public void init() {
		
		t1=new DefaultTableModel();
		t=new JTable(t1);
		t.setModel(t1);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.setPreferredScrollableViewportSize(new Dimension(600, 300));
        t.setFillsViewportHeight(true);
        f.add(new JScrollPane(t));
        t1.addColumn("Transaction ID");
        t1.addColumn("Title");
        t1.addColumn("Book ID");
        t1.addColumn("Borrower's Name");
        t1.addColumn("Status Payment");
        t1.addColumn("Status Return"); 
        t1.addColumn("Return Date(Book supposed to return)"); 
        t1.addColumn("Date Book was return"); 
        south=new JPanel();
        south.setLayout(new FlowLayout());
        returnb=new JButton("Return Book");    
        returnb.addActionListener(this);
        south.add(returnb);
        f.add(south,BorderLayout.SOUTH);
	}
	public void BorrowingData() {
		
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query = "select t.TransactionID, bk.Title,bk.BookID, br.BorrowName, t.StatusPayment,t.StatusReturn,t.Return_Date,t.ReturnBookDate from BORROWER br inner join Borrow t on br.BorrowID = t.BorrowID inner join BOOK bk on t.BookID = bk.BookID where StatusReturn='Not Return'";
					
			
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(query);;
			
         while (rs.next()) {
	        	
	        	
	        	
				t1.addRow(new Object[]  {
	        			rs.getString("TransactionID"),
	        			rs.getString("Title"),
	        			rs.getString("BookID"),
	        			rs.getString("BorrowName"),
	        			rs.getString("StatusPayment"),
	        			rs.getString("StatusReturn"),
	        			rs.getString("Return_Date"),
	        			rs.getString("ReturnBookDate"),
	        });
	        }
	        rs.close();
	      preparedStatement.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Return Book")) {
			
			rbook r=new rbook();
		}}
	public static void tutup() {
		f.dispose();
		
	}
	



}