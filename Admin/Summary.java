package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class Summary{
	private JTable table1,table2,table3;
	private JFrame f;
	private DefaultTableModel t1,t2,t3;
	private JLabel ins1,ins2,ins3;
	private JPanel north1,north2,north3;
	
	
	public Summary() {
		
		f=new JFrame("REPORT PAGE");
		f.setSize(700,1000);
	init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		ShowBook();
		init2();
		ShowBook2();
		init3();
		ShowBook3();
	
		}


	public void init() {
		north2=new JPanel();
		north2.setLayout(new FlowLayout());
		ins2=new JLabel("1.Overall Book Report:");
		north2.add(ins2);
		f.add(north2,BorderLayout.NORTH);
		t2=new DefaultTableModel();
		table2=new JTable(t2);
		table2.setModel(t2);
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table2.setPreferredScrollableViewportSize(new Dimension(600, 50));
        table2.setFillsViewportHeight(true);
        f.add(new JScrollPane(table2));
        t2.addColumn("Total Book");
        t2.addColumn("Total Genre");
        t2.addColumn("Total Author");}
	public void ShowBook() {
		
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query = "Select count(BookID),count(DISTINCT Genre),Count(DISTINCT AuthorName) from Book";
			
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(query);;
			
         while (rs.next()) {
	        	
	        	
	        	
				t2.addRow(new Object[]  {
	        			rs.getString("count(BookID)"),
	        			rs.getString("count(DISTINCT Genre)"),
	        			rs.getString("count(DISTINCT AuthorName)"),
	        			
	        });
	        }
	        rs.close();
	      preparedStatement.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}

	
	//Overall report 2
	public void init2() {
		north1=new JPanel();
		north1.setLayout(new FlowLayout());
		ins1=new JLabel("2.Overall Author Report:");
		north1.add(ins1);
		f.add(north1,BorderLayout.CENTER);
		t1=new DefaultTableModel();
		table1=new JTable(t1);
		table1.setModel(t1);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setPreferredScrollableViewportSize(new Dimension(600, 200));
        table1.setFillsViewportHeight(true);
        f.add(new JScrollPane(table1));
        t1.addColumn("Author");
        t1.addColumn("Total Book Written");
        t1.addColumn("Total Genre Written ");}
	public void ShowBook2() {
		
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query1 = "SELECT AuthorName,count(BookID),count(genre) from Book group by AuthorName";
			
			PreparedStatement preparedStatement1 = (PreparedStatement) conn.prepareStatement(query1);
			ResultSet rs1 = preparedStatement1.executeQuery(query1);
			
         while (rs1.next()) {
	        	
	        	
	        	
				t1.addRow(new Object[]  {
	        			rs1.getString("AuthorName"),
	        			rs1.getString("count(BookID)"),
	        			rs1.getString("count(Genre)"),
	        			
	        });
	        }
	        rs1.close();
	      preparedStatement1.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}

	//Overall report 3
	public void init3() {
		north3=new JPanel();
		north3.setLayout(new FlowLayout());
		ins3=new JLabel("3.Overall Genre Report:");
		north3.add(ins3);
		f.add(north3,BorderLayout.SOUTH);
		t3=new DefaultTableModel();
		table3=new JTable(t3);
		table3.setModel(t3);
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table3.setPreferredScrollableViewportSize(new Dimension(600, 200));
        table3.setFillsViewportHeight(true);
        f.add(new JScrollPane(table3));
        t3.addColumn("Genre");
        t3.addColumn("Total Book ");
        t3.addColumn("Num of Author ");}
	public void ShowBook3() {
		
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query3 = "SELECT DISTINCT Genre,count(Genre),count(AuthorName) from Book group by Genre";
			
			PreparedStatement preparedStatement3 = (PreparedStatement) conn.prepareStatement(query3);
			ResultSet rs3 = preparedStatement3.executeQuery(query3);
			
         while (rs3.next()) {
	        	
	        	
	        	
				t3.addRow(new Object[]  {
	        			rs3.getString("Genre"),
	        			rs3.getString("count(Genre)"),
	        			rs3.getString("count(AuthorName)"),
	        			
	        });
	        }
	        rs3.close();
	      preparedStatement3.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}

		
	}
