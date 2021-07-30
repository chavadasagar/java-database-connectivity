// use access for connection
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class sagar extends JFrame implements ActionListener
{
	Container con;
	JPanel title_panel,info_panel,button_panel;
	JLabel title,no_lbl,name_lbl,ct_lbl,phno_lbl;
	JTextField no,name,phno;
	JComboBox ct;
	JButton first,next,last,prev,save,cancal;
	int count=1;
	
	public sagar()
	{
		con = getContentPane();
		con.setLayout(new BorderLayout());
		setVisible(true);
		setSize(300,300);
		String cts[] = {"junagadh","rajkot","ahmdabad","baroda"};		

		title_panel = new JPanel();
		title_panel.setLayout(new FlowLayout());
		title = new JLabel("Save Data");
		title_panel.add(title);
		
		info_panel = new JPanel();
		info_panel.setLayout(new GridLayout(4,2));
		no_lbl = new JLabel("No :");
		name_lbl = new JLabel("Name :");
		ct_lbl = new JLabel("City :");
		phno_lbl = new JLabel("Phone No :");
		no = new JTextField(10);
		name = new JTextField(10);
		ct = new JComboBox(cts);
		phno = new JTextField(12);
		info_panel.add(no_lbl);
		info_panel.add(no);
		info_panel.add(name_lbl);
		info_panel.add(name);
		info_panel.add(ct_lbl);
		info_panel.add(ct);
		info_panel.add(phno_lbl);
		info_panel.add(phno);
		
		button_panel = new JPanel();
		button_panel.setLayout(new GridLayout(2,3));	
		first = new JButton("first");
		next =  new JButton("next");
		last = new JButton("last");
		prev = new JButton("previ");
		save = new JButton("save");
		cancal = new JButton("cancal");
		button_panel.add(first);
		button_panel.add(next);
		button_panel.add(last);
		button_panel.add(prev);
		button_panel.add(save);
		button_panel.add(cancal);	

		first.addActionListener(this);	
		next.addActionListener(this);
		last.addActionListener(this);
		prev.addActionListener(this);
		save.addActionListener(this);
		cancal.addActionListener(this);
		//ct.addAddListener(this);
	
		con.add(title_panel,BorderLayout.NORTH);
		con.add(info_panel,BorderLayout.CENTER);
		con.add(button_panel,BorderLayout.SOUTH);
	}	
	public void actionPerformed(ActionEvent ae)
	{
		try	
		{				
			if(ae.getSource() == first)
			{
				show_first_record();
			}
			if(ae.getSource() == next)
			{
				show_next_record();
			}
			if(ae.getSource() == last)
			{
				show_last_record();
			}
			if(ae.getSource() == prev)
			{
				show_prev_record();
			}
			if(ae.getSource() == save)
			{
				insertdata();				
			}
			if(ae.getSource() == cancal)
			{
				System.exit(0);
			}
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);
		}
	}

	public static void main(String args[])
	{
		sagar obj = new sagar();
		obj.setVisible(true);	
	}
	void insertdata()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
			PreparedStatement ps = con.prepareStatement("insert into info_ values("+Integer.parseInt(no.getText())+",'"+name.getText()+"','"+String.valueOf(ct.getSelectedIndex())+"','"+phno.getText()+"')");
			/*ps.setInt(1,Integer.parseInt(no.getText()));
			ps.setString(2,name.getText());
			ps.setString(3,String.valueOf(ct.getSelectedItem()));
			ps.setString(4,phno.getText());*/
			ps.executeUpdate();
			JOptionPane.showMessageDialog(this,"record insert");
			ps.close();
			con.close();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);
		}	
	}
	void show_first_record()
	{
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from info_");
			this.count = 2;
			rs.first();
				
			no.setText(String.valueOf(rs.getInt(1)));
			name.setText(rs.getString(2));
			ct.setSelectedIndex(Integer.parseInt(rs.getString(3)));
			phno.setText(rs.getString(4));
		
			rs.close();
			con.close();	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);
		}
	}
	void show_last_record()
	{
		countplus();
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from info_");
			
			rs.last();
				
			no.setText(String.valueOf(rs.getInt(1)));
			name.setText(rs.getString(2));
			ct.setSelectedIndex(Integer.parseInt(rs.getString(3)));
			phno.setText(rs.getString(4));
		
			rs.close();
			con.close();	
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,ex);
		}
	}
	void show_next_record()
	{
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from info_");
			
				
			
			if(rs.next())
			{
				rs.absolute(count);
				this.count++;
			}
			else
			{
				this.count = 1;	
			}			

			no.setText(String.valueOf(rs.getInt(1)));
			name.setText(rs.getString(2));
			ct.setSelectedIndex(Integer.parseInt(rs.getString(3)));
			phno.setText(rs.getString(4));
						
			rs.close();
			con.close();	
		}
		catch(Exception ex)
		{
			//JOptionPane.showMessageDialog(this,ex);
		}
	}
	void show_prev_record()
	{
		try	
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from info_");
			
			//rs.previous();
			
			if(count<0)
			{
				JOptionPane.showMessageDialog(this,"end");	
			}
			if(rs.next())
			{
				this.count--;
				rs.absolute(count);
			}			
	
			no.setText(String.valueOf(rs.getInt(1)));
			name.setText(rs.getString(2));
			ct.setSelectedIndex(Integer.parseInt(rs.getString(3)));
			phno.setText(rs.getString(4));
		
			rs.close();
			con.close();	
		}
		catch(Exception ex)
		{
			//JOptionPane.showMessageDialog(this,ex);
		}
	}
	void countplus()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:dsn");
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("select * from info_");
			count=1;
			while(rs.next())
			{
				this.count++;
			}
	
		}
		catch(Exception ex)
		{}
		
	}
}
