package Page;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.sql.*;

public class SearchBook implements ActionListener {
	
	private JFrame f;
	private JPanel n,c;
	private JButton bname,bauthor,bgenre;
	private JLabel ins;
	
  public SearchBook() {
	  f=new JFrame("Search Page");
	  f.setSize(400,200);
	
	  init();
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
  }

public void init() {
	
	n=new JPanel();
	ins=new JLabel("Search Through:");
	n.add(ins);
	f.add(n,BorderLayout.NORTH);
	bname=new JButton("Title");
	bauthor=new JButton("Author");
	bgenre=new JButton("Genre");
	Font t=new Font("TimesRoman",Font.BOLD,16);
	ins.setFont(t);
	
	c=new JPanel();
	
	c.add(bname);
	c.add(bauthor);c.add(bgenre);
	bauthor.addActionListener(this);bname.addActionListener(this);
	bgenre.addActionListener(this);
	f.add(c,BorderLayout.CENTER);
	Font ft=new Font("TimesRoman",Font.PLAIN,15);
	bname.setFont(ft);
	bauthor.setFont(ft);
	bgenre.setFont(ft);
	
}
public void actionPerformed(ActionEvent e) {
	if(e.getActionCommand().equals("Title")) {
		Searchbooktable b=new Searchbooktable();
	}
	else if(e.getActionCommand().equals("Author")) {
		SAuthor a=new SAuthor();}
else if(e.getActionCommand().equals("Genre")) {
		SGenre g= new SGenre();
	}
}
}