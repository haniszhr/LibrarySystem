package Admin;

import javax.swing.*;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;



public class AddBookAdmin implements ActionListener {
private JFrame frame;
private JPanel north,center,south;
private JLabel ins,tajuk,genre,author;
private JTextField nbook,gbook,abook;
private JButton addB;
	public AddBookAdmin() {
		frame=new JFrame("Add Book Page");
		//frame.setLayout(new FlowLayout());
		frame.setSize(400,200);
		init();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
public void init() {
	north=new JPanel();
	ins=new JLabel("Insert all the detail");
	Font f=new Font("TimesRoman",Font.BOLD,14);
	ins.setFont(f);
	north.add(ins);
	frame.add(north,BorderLayout.NORTH);
	
	center=new JPanel();
	center.setLayout(new GridLayout(3,2));
	tajuk=new JLabel(" Title: ");
	center.add(tajuk);
	nbook=new JTextField(255);
	center.add(nbook);
	author=new JLabel(" Author: ");
	center.add(author);
	abook=new JTextField(250);
	center.add(abook);
	genre=new JLabel(" Genre: ");
	center.add(genre);
	gbook=new JTextField(300);
	center.add(gbook);
	south=new JPanel();
	south.setLayout(new FlowLayout());
	addB=new JButton("Add");
	addB.addActionListener(this);
	south.add(addB);
    tajuk.setFont(f);
    genre.setFont(f);
    author.setFont(f);
	frame.add(south, BorderLayout.SOUTH);
	frame.add(center, BorderLayout.CENTER);
	
}
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("Add")) {
		
		
		String Title=nbook.getText();
		String AuthorName=abook.getText();
		String Genre=gbook.getText();
		if(Title.equals("")||AuthorName.equals("")||AuthorName.equals("")) //If username is null
		{
			JOptionPane.showMessageDialog(null,"Please enter all the data"); //Display dialog box with the message
		} else {
		PreparedStatement ps;
		//ResultSet rs;
		String query="Insert into book(Title,AuthorName,Genre,AdminID)Values(?,?,?,?)";
	try {
		ps=MyDatabase.doConnection().prepareStatement(query);
		ps.setString(1,Title);
		ps.setString(2,AuthorName);
		ps.setString(3,Genre);
		ps.setInt(4,1);
		int rowsInserted = ps.executeUpdate();
		if (rowsInserted > 0) {
		    
			JOptionPane.showMessageDialog(null,"Successfully insert new book");
			frame.dispose();
			ViewBook n=new ViewBook();
		}
		ps.close();
	}catch  (Exception ex) {
		ex.printStackTrace();
	}
	}}
	else
	{
		JOptionPane.showMessageDialog(null, "Enter data!.");
	}
}
}
