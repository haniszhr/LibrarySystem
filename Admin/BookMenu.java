package Admin;

import javax.swing.*;



import java.awt.event.*;
import java.awt.*;

public class BookMenu implements ActionListener{
		private JFrame frame;
		private JButton ViewB,Addb,UpdateB,sumr;
		private JPanel north,center;
		private JLabel menuB;
		
		BookMenu(){
			
			frame=new JFrame("BOOK MENU PAGE");
			frame.setSize(400,250);
			init();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		}
		
		public void init() {
			north =new JPanel();
			menuB=new JLabel("Click on the option below");
			north.add(menuB);
			Font tulisan = new Font("TimesRoman", Font.BOLD, 15);
			frame.add(north, BorderLayout.NORTH);
			menuB.setFont(tulisan);
			
			center=new JPanel();
			center.setLayout(new FlowLayout());
			

			
			
			
			ViewB=new JButton("View Book");
			ViewB.addActionListener(this);
			center.add(ViewB);
			Addb=new JButton("Add Book");
			Addb.addActionListener(this);
			center.add(Addb);
			UpdateB=new JButton("Update Book");
			UpdateB.addActionListener(this);
			center.add(UpdateB);
			sumr=new JButton("Report Summary");
			sumr.addActionListener(this);
			center.add(sumr);
		
		
		
			Font t = new Font("SansSerif", Font.PLAIN, 15);
			//BorrowB.setFont(t);
			sumr.setFont(t);
			UpdateB.setFont(t);
			ViewB.setFont(t);
			Addb.setFont(t);
			

			frame.add(center, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("View Book")) {
				ViewBook vb=new ViewBook();
			}
			else if(e.getActionCommand().equals("Add Book")) {
				
				AddBookAdmin sb = new AddBookAdmin();}
			
		else if(e.getActionCommand().equals("Update Book")) {
				
			ViewUpdate ud = new ViewUpdate();}
		else if(e.getActionCommand().equals("Report Summary")) {
			
	         Summary su = new Summary();}
			
		}}