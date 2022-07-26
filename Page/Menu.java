package Page;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

public class Menu implements ActionListener{
		private JFrame frame;
		private JButton ViewB,SearchB,UpdateB,BorrowB,reportb;
		private JPanel north,center;
		private JLabel menuB;
		
		Menu(){
			
			frame=new JFrame("BORROWER'S MENU PAGE");
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
			

			/*UpdateB=new JButton("Update Self Detail");
			UpdateB.addActionListener(this);
			center.add(UpdateB);*/
			
			ViewB=new JButton("View Book");
			ViewB.addActionListener(this);
			center.add(ViewB);
			
		
			SearchB=new JButton("Search Book");
			SearchB.addActionListener(this);
			center.add(SearchB);
		
		
		/*	BorrowB=new JButton("Borrow Book");
			BorrowB.addActionListener(this);
			center.add(BorrowB);*/
			
			Font t = new Font("SansSerif", Font.PLAIN, 15);
		//	BorrowB.setFont(t);
			SearchB.setFont(t);
			ViewB.setFont(t);
		//	UpdateB.setFont(t);
			
			frame.add(center, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("View Book")) {
				ViewBook vb=new ViewBook();
			}
			else if(e.getActionCommand().equals("Search Book")) {
				
				SearchBook sb = new SearchBook();}
			
			
		}}