package Admin;

import javax.swing.*;



import java.awt.event.*;
import java.awt.*;

public class MenuAdmin implements ActionListener{
		private JFrame frame;
		private JButton ViewB,ViewUser,BorrowB;
		private JPanel north,center;
		private JLabel menuB;
		
		MenuAdmin(){
			
			frame=new JFrame("ADMIN'S MENU PAGE");
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
			

			
			
			ViewUser=new JButton("User");
			ViewUser.addActionListener(this);
			center.add(ViewUser);
			

			ViewB=new JButton("Book");
			ViewB.addActionListener(this);
			center.add(ViewB);
			
		
		
			BorrowB=new JButton("Borrow/Return Book");
			BorrowB.addActionListener(this);
			center.add(BorrowB);
			
	
			
			
			Font t = new Font("SansSerif", Font.PLAIN, 15);
			//BorrowB.setFont(t);
			
			
			ViewB.setFont(t);
			
			ViewUser.setFont(t);
			
			BorrowB.setFont(t);
			

			frame.add(center, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Book")) {
				BookMenu vb=new BookMenu();
			}
			
		else if(e.getActionCommand().equals("User")) {
			
			UserMenu ud = new UserMenu();}

			
			else if(e.getActionCommand().equals("Borrow/Return Book")) {
				
			BorrowReturn bb = new BorrowReturn();}
			
		
				
			
		}}