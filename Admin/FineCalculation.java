package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Connection.MyDatabase;

import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class FineCalculation implements ActionListener{
	private JTable table;
	private static JFrame f;
	private JPanel north,south,center;
	private JButton back;
	private JLabel transid,ins;
	private JTextField id;
	private DefaultTableModel table1;
	
	public FineCalculation() {
		
		f=new JFrame("Fine Calculation Page");
		f.setSize(900,450);
	init();
		f.setLayout(new FlowLayout());
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);
		BorrowerData();
		Result ();}
	public void init() {
		north=new JPanel();
		north.setLayout(new FlowLayout());
		
		ins=new JLabel("List of Borrowing Info of Book That have been Returned and Overdue");
		Font t= new Font("TimesRoman", Font.BOLD, 15);
		ins.setFont(t);
		north.add(ins);
		f.add(north,BorderLayout.NORTH);
		table1=new DefaultTableModel();
		table=new JTable(table1);
		table.setModel(table1);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setPreferredScrollableViewportSize(new Dimension(800, 300));
        table.setFillsViewportHeight(true);
        f.add(new JScrollPane(table));
        table1.addColumn("Transaction ID");
        table1.addColumn("Title");
        table1.addColumn("Borrower's Name ");
        table1.addColumn("Status Payment");
        table1.addColumn("Date Book Return"); 
        
      
		
	}
	public void BorrowerData() {
		
		try {
			
			Connection conn = MyDatabase.doConnection();
			String query = "select t.TransactionID, bk.Title, br.BorrowName, t.StatusPayment,t.ReturnBookDate from BORROWER br inner join Borrow t on br.BorrowID = t.BorrowID inner join BOOK bk on t.BookID = bk.BookID where t.StatusReturn='Return' AND t.StatusPayment='Overdue'";
			PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery(query);;
			
         if (rs.next()) {
	        	
	        	
	        	
				table1.addRow(new Object[]  {
	        			rs.getString("TransactionID"),
	        			rs.getString("Title"),
	        			rs.getString("BorrowName"),
	        			rs.getString("StatusPayment"),
	        	        rs.getString("ReturnBookDate")
	        });
	        }
         else {JOptionPane.showMessageDialog(null, " List is empty because all borrower either have paid or return book on due");
         f.dispose();}
         
        
	        rs.close();
	      preparedStatement.close();
	        conn.close();
	}catch (Exception ex) {ex.printStackTrace();
	
	}f.setVisible(true);
	f.validate();
	}  

public void Result() {
	
	  south=new JPanel();
        south.setLayout(new FlowLayout());
        back=new JButton("Pay Fine");
        back.addActionListener(this);
        south.add(back);
        f.add(south, BorderLayout.SOUTH);
}

public void actionPerformed(ActionEvent e) {
	
	if(e.getActionCommand().equals("Pay Fine")) {
		calculate m=new calculate();


}
	}
public static void tutup() {
	f.dispose();
	
}
}