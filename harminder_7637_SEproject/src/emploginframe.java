import java.awt.Font;
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

public class emploginframe implements MouseListener {
	JFrame loginframe;
	JLabel loginlab, backpic, homelab;
	JTextField usertxt;
	JPasswordField passtxt;
	Font g;

	public void showloginframe() {
		loginframe = new JFrame("login Module");
		loginframe.setLayout(null);
		usertxt = new JTextField();
		usertxt.setOpaque(false);
		passtxt = new JPasswordField();
		passtxt.setOpaque(false);
		loginlab = new JLabel(new ImageIcon("myImages/logina.png"));
		homelab = new JLabel(new ImageIcon("myImages/homelab2.png"));
		backpic = new JLabel(new ImageIcon("myImages/ELbackpic.jpg"));

		g = new Font("Arial", Font.PLAIN, 20);
		usertxt.setBounds(495, 360, 230, 45);
		passtxt.setBounds(495, 465, 230, 45);
		loginlab.setBounds(230, 530, 200, 50);
		homelab.setBounds(1220, 30, 96, 79);
		backpic.setBounds(0, 0, 1366, 768);

		usertxt.setFont(g);
		passtxt.setFont(g);

		homelab.addMouseListener(this);
		loginlab.addMouseListener(this);

		loginframe.add(usertxt);
		loginframe.add(passtxt);
		loginframe.add(loginlab);
		loginframe.add(homelab);
		loginframe.add(backpic);

		loginframe.setUndecorated(true);
		loginframe.setVisible(true);
		loginframe.setSize(1366, 768);
		loginframe.setLocationRelativeTo(null);
	}

	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == loginlab)
			loginlab.setIcon(new ImageIcon("myImages/loginb.png"));
	}

	public void mouseExited(MouseEvent me) {
		if (me.getSource() == loginlab)
			loginlab.setIcon(new ImageIcon("myImages/logina.png"));
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == homelab) {
			homepage hp = new homepage();
			hp.showFrontFrame();
			loginframe.dispose();
		}

		if (me.getSource() == loginlab) {
			// database connection
			boolean b = true;
			String username = usertxt.getText();
			String password = String.valueOf(passtxt.getPassword());
			System.out.println(username + " " + password);

			if (username.equals("") && password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Username & Password", "ADMIN", 0);
				b = false;
			}

			else if (password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Password", "ADMIN", 0);
				b = false;
				usertxt.setText("");
			}

			else if (username.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Username", "ADMIN", 0);
				b = false;
				passtxt.setText("");
			}

			try {
				if (b) {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", ""); //
					PreparedStatement pstmt = con
							.prepareStatement("select * from emptab where username=? and password=?");
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					ResultSet rst = pstmt.executeQuery();

					if (rst.next()) {
						empmodule em = new empmodule();
						em.showempmodule(username);
						loginframe.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "RECORD NOT FOUND", "ADMIN", 0);
					}
					usertxt.setText("");
					passtxt.setText("");

				}
			} catch (ClassNotFoundException exp) {
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

}
