package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class ViewDetail{
	private JTable t;
	private JFrame f;
	private DefaultTableModel t1;
	
	public ViewDetail() {
		
		f=new JFrame("View User");
		f.setSize(900,450);
	init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		UserData();}
	public void init() {
		t1=new DefaultTableModel();
		t=new JTable(t1);
		t.setModel(t1);
		t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        t.setPreferredScrollableViewportSize(new Dimension(600, 300));
        t.setFillsViewportHeight(true);
        f.add(new JScrollPane(t));
        t1.addColumn("ID");
        t1.addColumn("Name");
        t1.addColumn("Email ");
        t1.addColumn("Password");
        t1.addColumn("Phone"); 
        
       
	}
	
	

	public void UserData() {
	
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query = "SELECT * FROM `borrower`";
			
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(query);;
			
         while (rs.next()) {
	        	
	        	
	        	
				t1.addRow(new Object[]  {
	        			rs.getString("BorrowID"),
	        			rs.getString("BorrowName"),
	        			rs.getString("BorrowEmail"),
	        			rs.getString("BorrowPassword"),
	        			rs.getString("BorrowPhone")
	        });
	        }
	        rs.close();
	      preparedStatement.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}




		
	}
