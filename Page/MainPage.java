package Page;
import Admin.AdminLogin;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class MainPage implements ActionListener {

	private JFrame f;
	private JPanel n,c;
	private JLabel ins,wc;
	private JButton b,a;
	public MainPage(){
		f=new JFrame("MAIN PAGE");
		f.pack();
		f.setSize(400, 200);
		init();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
	}
	public void init(){
		n=new JPanel();
		n.setSize(350,150);
		n.setBackground(Color.white);
		
	
		ins=new JLabel("CHOOSE LOGIN AS: ");
		n.add(ins);
		c= new JPanel();
		c.setLayout(new GridLayout(1,2));
		b = new JButton ("BORROWER");
		b.addActionListener(this);
		c.add(b);
		a = new JButton ("ADMIN");
		a.addActionListener(this);
		c.add(a);
		
		Font t=new Font("Serif",Font.BOLD,15);
		ins.setFont(t);
		Font tulisan = new Font("TimesRoman", Font.BOLD, 15);
		b.setFont(tulisan);
		a.setFont(tulisan);
		
		f.add(n,BorderLayout.NORTH);
		f.add(c,BorderLayout.CENTER);
				
}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("BORROWER")) {
			LoginPage l=new LoginPage();
		}
		else if(e.getActionCommand().equals("ADMIN")) {
			AdminLogin ad=new AdminLogin();
		}
		
}
}