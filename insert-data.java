//ni this program use access

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//acess ->table name  is info

class sagar extends JFrame implements ActionListener
{
	Container con;
	JPanel title_panel,info_panel;
	JLabel no_lbl,name_lbl,title;	
	JButton save,cancal;
	JTextField no,name;
	
	sagar()
	{
		con = getContentPane();
		con.setLayout(new BorderLayout());
		setVisible(true);
		setSize(300,300);

		title_panel = new JPanel();
		title_panel.setLayout(new FlowLayout());
		title = new JLabel("Insert Data");
		title.setFont(new Font("arial",Font.BOLD,30));
		title_panel.add(title);
		
		info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(3,2));
		no_lbl = new JLabel("No :");
		name_lbl = new JLabel("Name :");
		no = new JTextField(10);
		name = new JTextField(10);
		save = new JButton("save");
		cancal = new JButton("cancal");

		info_panel.add(no_lbl);
		info_panel.add(no);
		info_panel.add(name_lbl);
		info_panel.add(name);
		info_panel.add(save);
		info_panel.add(cancal);
		
		con.add(title_panel,BorderLayout.NORTH);
		con.add(info_panel,BorderLayout.CENTER);
	
		save.addActionListener(this);
		cancal.addActionListener(this);		
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == save)
		{
			insertData();
		}
		if(ae.getSource() == cancal)
		{
			System.exit(0);
		}
		
	}
	public static void main(String args[])
	{
		sagar obj = new sagar();
		obj.setVisible(true);
	}
	//===================function==================

	void insertData()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
		
			PreparedStatement ps = con.prepareStatement("insert into info values("+Integer.parseInt(no.getText())+",'"+name.getText()+"')");
			ps.executeUpdate();

			JOptionPane.showMessageDialog(this,"data insert successfuly");			

			ps.close();
			con.close();
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);	
		}
	}
	
}
