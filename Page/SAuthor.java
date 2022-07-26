package Page;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class SAuthor implements ActionListener{
	private JTable t;
	private JFrame f;
	private JPanel north;
	private JLabel title;
	private JTextField title1;
	private DefaultTableModel t1;
	private JButton search;
	public SAuthor(){

		f=new JFrame("View Book");
		f.setSize(800,450);
		askid();
	init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);}
	public void askid() {
	
			north=new JPanel();
			north.setLayout(new FlowLayout());
			title=new JLabel("Author Name:");
			north.add(title);
			title1=new JTextField(30);
			north.add(title1);
			search=new JButton("Search");
			search.addActionListener(this);
			north.add(search);
			f.add(north,BorderLayout.SOUTH);
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
        t1.addColumn("Availability"); }
	public void actionPerformed(ActionEvent e)  {
		if(e.getActionCommand().equals("Search")) {
			String AuthorName =title1.getText();
			if(AuthorName.equals(""))//null
				{
				JOptionPane.showMessageDialog(null,"Please enter Author's Name."); //Display dialog box with the message
			}
			else {
		
		try {
			PreparedStatement ps;
			ResultSet rs;
		
			String query = "SELECT BookID,Title,Genre,AuthorName,Availability FROM Book WHERE AuthorName like ?";
			
			ps = MyDatabase.doConnection().prepareStatement(query);
			
			ps.setString(1,"%"+AuthorName+"%" );
		rs = ps.executeQuery();
		
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
	      ps.close();
	     
	}catch (Exception ex) {ex.printStackTrace();
	
	}}f.setVisible(true);
	f.validate();
	}
}}
