package Admin;

import javax.swing.*;



import java.awt.event.*;
import java.awt.*;

public class UserMenu implements ActionListener{
		private JFrame frame;
		private JButton ViewUser,UpdateUser,delete;
		private JPanel north,center;
		private JLabel menuB;
		
		UserMenu(){
			
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
			

			
			
			ViewUser=new JButton("View User");
			ViewUser.addActionListener(this);
			center.add(ViewUser);
			UpdateUser=new JButton("Update User");
			UpdateUser.addActionListener(this);
			center.add(UpdateUser);

		delete=new JButton("Delete User");
			delete.addActionListener(this);
			center.add(delete);
		
			
			Font t = new Font("SansSerif", Font.PLAIN, 15);
			//BorrowB.setFont(t);
			//SearchB.setFont(t);
			
			
			ViewUser.setFont(t);
			UpdateUser.setFont(t);
			delete.setFont(t);
		

			frame.add(center, BorderLayout.CENTER);
		}
		public void actionPerformed(ActionEvent e) {
			
			
		if(e.getActionCommand().equals("View User")) {
			
			ViewDetail ud = new ViewDetail();}
		else if(e.getActionCommand().equals("Update User")) {
			
			UpdateDetail1 ud = new UpdateDetail1();}
else if(e.getActionCommand().equals("Delete User")) {
			
			DeleteUser ud = new DeleteUser();}
			
			

			
		}}