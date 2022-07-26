package Admin;
import javax.swing.*;


import java.awt.event.*;
import java.awt.*;

public class BorrowReturn implements ActionListener{
		private JFrame frame;
		private JButton BorrowB,Returnb,Status,pay;
		private JPanel north,center;
		private JLabel menuB;
		
		BorrowReturn(){
			
			frame=new JFrame("BORROW/RETURN BOOK MENU PAGE");
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
			
		
			BorrowB=new JButton("Borrow Book");
			BorrowB.addActionListener(this);
			center.add(BorrowB);
			Returnb=new JButton("Return Book");
			Returnb.addActionListener(this);
			center.add(Returnb);
			Status=new JButton("Status Payment");
			Status.addActionListener(this);
			center.add(Status);
			pay=new JButton("Pay Fine");
			pay.addActionListener(this);
			center.add(pay);
			
			Font t = new Font("SansSerif", Font.PLAIN, 15);
			//BorrowB.setFont(t);
			//SearchB.setFont(t);
			
		   pay.setFont(t);
			BorrowB.setFont(t);
			Returnb.setFont(t);
			
			Status.setFont(t);

			frame.add(center, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e) {
			
		
			 if(e.getActionCommand().equals("Borrow Book")) {
				
			BorrowBook bb = new BorrowBook();}
			else if(e.getActionCommand().equals("Return Book")) {
				
				Listborrow bb = new Listborrow();}
          else if(e.getActionCommand().equals("Status Payment")) {
				
				StatusBorrower ab = new StatusBorrower();}
          else if(e.getActionCommand().equals("Pay Fine")) {
				
				FineCalculation ab = new FineCalculation();}

			
		}}