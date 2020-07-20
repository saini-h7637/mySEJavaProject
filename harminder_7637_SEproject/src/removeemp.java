import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.toedter.calendar.JDateChooser;

class removeemp implements MouseListener {
	JFrame removeempframe;
	JTextField nametxt, contxt, mailtxt, usernametxt, prooftxt, datetxt, dobtxt, gendertxt;
	JTextArea addtxt;
	JLabel backpic, view, remove, cancel;
	JScrollPane jsp;
	JPasswordField passtxt;
	JComboBox<String> empproof;
	String proof[] = { "Select Proof", "Aadhar Card", "Driving Licence", "Pan Card" };
	Font f;

	public void showremoveempframe() {
		removeempframe = new JFrame();
		removeempframe.setLayout(null);

		nametxt = new JTextField("");
		contxt = new JTextField("");
		mailtxt = new JTextField("");
		usernametxt = new JTextField("");
		gendertxt = new JTextField("");
		datetxt = new JTextField("");
		addtxt = new JTextArea("");
		jsp = new JScrollPane(addtxt);
		passtxt = new JPasswordField("");

		backpic = new JLabel(new ImageIcon("myImages/backpicforaddemp.jpg"));
		view = new JLabel(new ImageIcon("myImages/viewa.png"));
		cancel = new JLabel(new ImageIcon("myImages/cancela.png"));
		remove = new JLabel(new ImageIcon("myImages/removea.png"));

		prooftxt = new JTextField("Proof No.");
		prooftxt.setOpaque(true);
		empproof = new JComboBox<String>(proof);
		empproof.setOpaque(true);

		dobtxt = new JTextField("");
		f = new Font("Arial", Font.PLAIN + Font.BOLD, 20);

		nametxt.setBounds(320, 20, 275, 40);
		gendertxt.setBounds(320, 70, 275, 40);
		dobtxt.setBounds(320, 120, 275, 40);
		contxt.setBounds(320, 220, 275, 40);
		mailtxt.setBounds(320, 270, 275, 40);
		empproof.setBounds(320, 320, 160, 40);
		prooftxt.setBounds(485, 320, 110, 40);
		datetxt.setBounds(320, 370, 275, 40);
		usernametxt.setBounds(320, 420, 275, 40);
		passtxt.setBounds(320, 470, 275, 40);
		jsp.setBounds(320, 170, 275, 40);
		backpic.setBounds(0, 0, 800, 600);

		view.setBounds(532, 520, 60, 60);
		remove.setBounds(612, 520, 60, 60);
		cancel.setBounds(692, 520, 60, 60);

		nametxt.setFont(f);
		contxt.setFont(f);
		mailtxt.setFont(f);
		usernametxt.setFont(f);
		addtxt.setFont(f);
		passtxt.setFont(f);
		datetxt.setFont(f);
		dobtxt.setFont(f);
		gendertxt.setFont(f);
		prooftxt.setFont(f);
		empproof.setFont(f);

		removeempframe.add(nametxt);
		removeempframe.add(contxt);
		removeempframe.add(mailtxt);
		removeempframe.add(usernametxt);
		removeempframe.add(passtxt);
		removeempframe.add(jsp);
		removeempframe.add(datetxt);
		removeempframe.add(dobtxt);
		removeempframe.add(gendertxt);
		removeempframe.add(prooftxt);
		removeempframe.add(empproof);
		removeempframe.add(view);
		removeempframe.add(remove);
		removeempframe.add(cancel);
		removeempframe.add(backpic);

		cancel.addMouseListener(this);
		view.addMouseListener(this);
		remove.addMouseListener(this);

		removeempframe.setUndecorated(true);
		removeempframe.setSize(800, 600);
		removeempframe.setLocationRelativeTo(null);
		removeempframe.setVisible(true);
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == view) {
			boolean b = true;
			String email = mailtxt.getText();
			if (email.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Email-ID", "SYSTEM", 0);
				b = false;
			}
			if (b == true)
				;
			{
				try {
					// 1.Driver Registeration
					Class.forName("com.mysql.jdbc.Driver");
					// 2.Connection Establishment
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
					// 3.Prepare SQL Query
					PreparedStatement pstmt = con.prepareStatement("select * from emptab where email=?");
					pstmt.setString(1, email);
					ResultSet rst = pstmt.executeQuery();
					while (rst.next()) {
						nametxt.setText(rst.getString("name"));
						gendertxt.setText(rst.getString("gender"));
						dobtxt.setText(rst.getString("dob"));
						addtxt.setText(rst.getString("address"));
						contxt.setText(rst.getString("contact"));
						empproof.setSelectedItem(rst.getString("proof"));
						prooftxt.setText(rst.getString("proofno"));
						datetxt.setText(rst.getString("date"));
						usernametxt.setText(rst.getString("username"));
						passtxt.setText(rst.getString("password"));
					}

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "E-Mail Not Found", "SYSTEM", 0);
					e.printStackTrace();
				}
			}
		}
		if (me.getSource() == remove) {
			String email = mailtxt.getText();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
				// 3.Prepare SQL Query
				PreparedStatement pstmt = con.prepareStatement("delete from emptab where email=?");
				pstmt.setString(1, email);
				// 4. Execute Update
				int y = pstmt.executeUpdate();
				if (y > 0) {
					JOptionPane.showMessageDialog(null, "Employee Removed From Records", "SYSTEM", 1);
					nametxt.setText("");
					passtxt.setText("");
					addtxt.setText("");
					contxt.setText("");
					mailtxt.setText("");
					prooftxt.setText("");
					usernametxt.setText("");
					dobtxt.setText("");
					datetxt.setText("");
					empproof.setSelectedIndex(0);
					gendertxt.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "RECORD NOT FOUND", "SYSTEM", 0);
				}
			} catch (ClassNotFoundException exp) {
				System.out.println("Driver Class dispute while deleting The Employee record " + exp);
			} catch (MySQLIntegrityConstraintViolationException exp) {
				System.out.println("SQL dispute while deleting The employee record " + exp);
				JOptionPane.showMessageDialog(null, "Email Already Exist", "Admin", 0);
			} catch (SQLException exp) {
				System.out.println("SQL dispute while deleting The employee record " + exp);
			}
		}
		if (me.getSource() == cancel) {
			removeempframe.dispose();
		}

	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancelb.png"));
		}

		if (me.getSource() == remove) {
			remove.setIcon(new ImageIcon("myImages/removeb.png"));
		}

		if (me.getSource() == view) {
			view.setIcon(new ImageIcon("myImages/viewb.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancela.png"));
		}

		if (me.getSource() == remove) {
			remove.setIcon(new ImageIcon("myImages/removea.png"));
		}

		if (me.getSource() == view) {
			view.setIcon(new ImageIcon("myImages/viewa.png"));
		}
	}
}
