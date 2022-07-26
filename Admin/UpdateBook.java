package Admin;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import Connection.MyDatabase;
public class UpdateBook implements ActionListener{
private JFrame frame;
private JPanel north,center,south,south1;
private JLabel inser,title,genre,author,bookid,bId;
private JButton search, titleB,genreB,authorB,back;
private JTextField id,nama,genre1,author1,Id;


public UpdateBook() {
frame=new JFrame("Update Book");
frame.setSize(700,500);
init();
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setVisible(true);
}
public void init() {
	north=new JPanel();
	north.setLayout(new FlowLayout());
	bookid=new JLabel(" Book Id:");
	north.add(bookid);
	id=new JTextField(6);
	north.add(id);
	
	search=new JButton("Search");
	search.addActionListener(this);
	north.add(search);
	frame.add(north,BorderLayout.NORTH);
	
	center=new JPanel();
	center.setLayout(new GridLayout(4,1));
	bId=new JLabel(" Book ID:");
	center.add(bId);
	Id=new JTextField(6);
	center.add(Id);
	
	title=new JLabel(" Title:");
	center.add(title);
	nama=new JTextField(255);
	center.add(nama);
	
	genre=new JLabel(" Genre :");
	center.add(genre);
	genre1=new JTextField(300);
	center.add(genre1);
	
	author=new JLabel(" Author:");
	center.add(author);
	author1=new JTextField(250);
	center.add(author1);
	
	south=new JPanel();
	south.setLayout(new FlowLayout());
	titleB=new JButton("Update Title");
	titleB.addActionListener(this);
	south.add(titleB);
	genreB=new JButton("Update Genre");
	genreB.addActionListener(this);
	south.add(genreB);
    authorB=new JButton("Update Author");
	authorB.addActionListener(this);
	south.add(authorB);
    
	back=new JButton("Back");
	back.addActionListener(this);
	south.add(back);
	frame.add(south,BorderLayout.SOUTH);
	
	
	Font ft=new Font("TimesRoman",Font.PLAIN,14);
	bId.setFont(ft);
	title.setFont(ft);
	genre.setFont(ft);
	author.setFont(ft);
	
	search.setFont(ft);
	frame.add(center,BorderLayout.CENTER);
	
	nama.setEditable(false);

	genre1.setEditable(true);
	nama.setEditable(true);
	author1.setEditable(true);
	Id.setEditable(false);
}
public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("Search")) {
		String BookID=id.getText();
		if(BookID.equals(""))//null
		{
		JOptionPane.showMessageDialog(null,"Please enter Book's ID you would like to update."); //Display dialog box with the message
	}
	else {
		PreparedStatement ps;
		ResultSet rs;
		
		String query = "SELECT BookID,Title,Genre,AuthorName FROM Book WHERE BookID = ?";
		try {
			ps = MyDatabase.doConnection().prepareStatement(query);
			
			ps.setString(1, BookID);
		rs = ps.executeQuery();
		
		if(rs.next()){
			String d = rs.getString(1);
			String d1 = rs.getString(2);
			String d2 = rs.getString(3);
		     String d3 = rs.getString(4);
			
			
			Id.setText(d);
			nama.setText(d1);
			genre1.setText(d2);
			author1.setText(d3);
			
			}
		else {JOptionPane.showMessageDialog(null,"Ops! No data found.");}
	}catch (Exception ex) {ex.printStackTrace();
	}
}}
	else if(e.getActionCommand().equals("Back")) {
	ViewUpdate.tutup();
		frame.dispose();
	}
	else if(e.getActionCommand().equals("Update Title")) {
		String BookID=id.getText();
		String Title=nama.getText();
		
		
		String query1 = "Update book set Title=? where BookID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, Title);
			ps1.setString(2, BookID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(nama,"Data update successfully.");
			ViewUpdate.tutup();
			frame.dispose();
			ViewUpdate vb=new ViewUpdate();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}		
	
	}
	else if(e.getActionCommand().equals("Update Genre")) {
		String BookID=id.getText();
		String Genre=genre1.getText();
		
		String query1 = "Update book set Genre=? where BookID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, Genre);
			ps1.setString(2, BookID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(Id,"Data update successfully.");
			ViewUpdate.tutup();
			frame.dispose();
			ViewUpdate vb=new ViewUpdate();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}	
		
	}	
	else if(e.getActionCommand().equals("Update Author")) {
		String BookID=id.getText();
		String AuthorName=author1.getText();
	
		String query1 = "Update book set AuthorName=? where BookID=?";	
		try {
			PreparedStatement ps1 = MyDatabase.doConnection().prepareStatement(query1);
			
			ps1.setString(1, AuthorName);
			ps1.setString(2, BookID);
			ps1.executeUpdate();
			
			ps1.close();
			JOptionPane.showMessageDialog(Id,"Data update successfully.");
			ViewUpdate.tutup();
			frame.dispose();
			ViewUpdate vb=new ViewUpdate();
			}
			
		catch  (Exception ex) {
			ex.printStackTrace();
		}		
		
	}
		
		
		
	}

}
