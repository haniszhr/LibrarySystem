package Admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class ViewUpdate implements ActionListener{
	private JTable t;
	private static JFrame f;
	private JPanel south;
	private JButton update,back;
	private DefaultTableModel t1;
	
	public ViewUpdate() {
		
		f=new JFrame("View Book");
		f.setSize(700,450);
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
        south=new JPanel();
        south.setLayout(new FlowLayout());
        update=new JButton("Update");
        update.addActionListener(this);
        south.add(update);
        back=new JButton("Back");
        back.addActionListener(this);
        south.add(back);
        f.add(south, BorderLayout.SOUTH);
        
       
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

public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Update")) {
			UpdateBook u=new UpdateBook();
			
			}
		else if(e.getActionCommand().equals("Back")) {
		
			
			f.dispose();
		}
	
		}
public static void tutup() {
	// TODO Auto-generated method stub
	f.dispose();
	
}


		
	}
