import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class adminloginframe implements ActionListener, MouseListener {
	JFrame adminloginframe;
	JLabel loginlab, backpic, homelab;
	JTextField usertxt;
	JPasswordField passtxt;
	Font f, g;

	public void showadminloginframe() {
		adminloginframe = new JFrame("Admin Login Frame");
		adminloginframe.setLayout(null);

		f = new Font("Arial", Font.PLAIN + Font.BOLD, 20);
		g = new Font("Arial", Font.PLAIN + Font.BOLD, 50);

		backpic = new JLabel(new ImageIcon("myImages/adminlogin.jpg"));
		homelab = new JLabel(new ImageIcon("myImages/homelab2.png"));
		loginlab = new JLabel(new ImageIcon("myImages/loginlab.jpg"));
		usertxt = new JTextField();
		usertxt.setOpaque(false);
		usertxt.setForeground(Color.BLACK);
		passtxt = new JPasswordField();
		passtxt.setOpaque(false);
		passtxt.setForeground(Color.BLACK);

		homelab.setBounds(1220, 30, 96, 79);
		backpic.setBounds(0, 0, 1366, 768);
		usertxt.setBounds(478, 331, 405, 57);
		passtxt.setBounds(478, 444, 405, 57);
		loginlab.setBounds(476, 510, 106, 64);

		usertxt.setFont(f);
		passtxt.setFont(f);

		loginlab.addMouseListener(this);
		homelab.addMouseListener(this);

		adminloginframe.add(loginlab);
		adminloginframe.add(homelab);
		adminloginframe.add(usertxt);
		adminloginframe.add(passtxt);

		adminloginframe.add(backpic);

		adminloginframe.setUndecorated(true);
		adminloginframe.setVisible(true);
		adminloginframe.setSize(1366, 768);
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == homelab) {
			homepage hp = new homepage();
			hp.showFrontFrame();
			adminloginframe.dispose();
		}

		if (me.getSource() == loginlab) {
			// database connection
			boolean b = true;
			String username = usertxt.getText();
			String password = String.valueOf(passtxt.getPassword());
			System.out.println(username + " " + password);

			if (username.equals("") && password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Username & Password", "Dealership TRUST", 0);
				b = false;
			}

			else if (password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Password", "Dealership TRUST", 0);
				b = false;
				usertxt.setText("");
			}

			else if (username.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Username", "Dealership TRUST", 0);
				b = false;
				passtxt.setText("");
			}

			try {
				if (b) {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
					PreparedStatement pstmt = con
							.prepareStatement("select * from admintab where username=? and password=?");
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					ResultSet rst = pstmt.executeQuery();

					if (rst.next()) {
						adminloginframe.dispose();
						adminmodule am = new adminmodule();
						am.showadminlogin(username);
					} else {
						JOptionPane.showMessageDialog(null, "Authorized Personnel's Only", "Dealership TRUST", 0);
					}
					usertxt.setText("");
					passtxt.setText("");

				}
			}

			catch (ClassNotFoundException exp) {
				System.out.println("driver class dispute");
			} catch (SQLException em) {
				System.out.println("sql dispute");
			}
		}
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}
}
