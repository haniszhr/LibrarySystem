package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class ViewBook{
	protected JTable t;
	protected JFrame f;
	protected DefaultTableModel t1;
	
	public ViewBook() {
		
		f=new JFrame("View Book");
		f.setSize(800,450);
	init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		BookData();}
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
        t1.addColumn("Availability"); 
        
       
	}
	
	

	public void BookData() {
	
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query = "SELECT BookID,Title,Genre,AuthorName,Availability FROM `book`";
			
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(query);;
			
         while (rs.next()) {
	        	
	        	
	        	
				t1.addRow(new Object[]  {
	        			rs.getString("BookID"),
	        			rs.getString("Title"),
	        			rs.getString("Genre"),
	        			rs.getString("AuthorName"),
	        			rs.getString("Availability")
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
