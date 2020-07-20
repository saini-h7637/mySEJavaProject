import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

class editemp implements MouseListener {
	JFrame editempframe;
	JPasswordField passtxt, confrmpasstxt;
	JTextArea addtxt;
	JLabel cancel, update, backpic;
	JTextField contxt, mailtxt;
	JScrollPane jsp;
	Font f;
	boolean b1 = true;

	public void showeditempframe() {
		editempframe = new JFrame();
		editempframe.setLayout(null);

		passtxt = new JPasswordField("");
		passtxt.setOpaque(true);
		confrmpasstxt = new JPasswordField("");
		confrmpasstxt.setOpaque(true);
		addtxt = new JTextArea("");
		addtxt.setOpaque(true);
		contxt = new JTextField("");
		contxt.setOpaque(true);
		mailtxt = new JTextField("");
		mailtxt.setOpaque(true);
		jsp = new JScrollPane(addtxt);
		jsp.setOpaque(false);
		backpic = new JLabel(new ImageIcon("myImages/backpicforeditemp.jpg"));
		cancel = new JLabel(new ImageIcon("myImages/cancela.png"));
		update = new JLabel(new ImageIcon("myImages/updatea.png"));

		f = new Font("Arial", Font.PLAIN + Font.BOLD, 25);

		passtxt.setBounds(420, 95, 275, 40);
		confrmpasstxt.setBounds(420, 155, 275, 40);
		jsp.setBounds(420, 215, 275, 40);
		contxt.setBounds(420, 275, 275, 40);
		mailtxt.setBounds(420, 335, 275, 40);

		backpic.setBounds(0, 0, 800, 600);
		update.setBounds(582, 500, 60, 60);
		cancel.setBounds(662, 500, 60, 60);

		contxt.setFont(f);
		mailtxt.setFont(f);
		addtxt.setFont(f);
		passtxt.setFont(f);
		confrmpasstxt.setFont(f);
		jsp.setFont(f);

		cancel.addMouseListener(this);
		update.addMouseListener(this);

		editempframe.add(passtxt);
		editempframe.add(confrmpasstxt);
		editempframe.add(jsp);
		editempframe.add(contxt);
		editempframe.add(mailtxt);
		editempframe.add(cancel);
		editempframe.add(update);
		editempframe.add(backpic);

		editempframe.setUndecorated(true);
		editempframe.setSize(800, 600);
		editempframe.setLocationRelativeTo(null);
		editempframe.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == update) {
			boolean b = true;

			String address = addtxt.getText();
			if (address.equals("")) {
				JOptionPane.showMessageDialog(null, "Address Required", "SYSTEM", 0);
				b = false;
			}

			long contact = 0;
			if (contxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Contact No.", "SYSTEM", 0);
				b = false;
			} else
				try {
					contact = Long.parseLong(contxt.getText());
				} catch (NumberFormatException exp) {
					JOptionPane.showMessageDialog(null, "Contact must be Numeric", "SYSTEM", 0);
					b = false;
				}

			String email = mailtxt.getText();
			if (email.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Email-ID", "SYSTEM", 0);
				b = false;
			}
			String password = String.valueOf(passtxt.getPassword());
			String confirm = String.valueOf(confrmpasstxt.getPassword());

			if (password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Password", "SYSTEM", 0);
				b = false;
			}

			else if (confirm.equals("")) {
				JOptionPane.showMessageDialog(null, "Confirm Passworld Field Mandatory", "SYSTEM", 0);
				b = false;
			}

			if (password.equals(confirm)) {
				b1 = true;
			} else {
				JOptionPane.showMessageDialog(null, "Passwords Not Matched", "SYSTEM", 0);
				passtxt.setText("");
				confrmpasstxt.setText("");
				b1 = false;
			}

			System.out.println(address + " " + contact + " " + password + " " + email);

			if (b == true && b1 == true) {
				try {
					// 1.Driver Registeration
					Class.forName("com.mysql.jdbc.Driver");
					// 2.Connection Establishment
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
					// 3.Prepare SQL Query
					PreparedStatement pstmt = con
							.prepareStatement("update emptab set address=?, contact=?, password=? where email=?");
					pstmt.setString(1, address);
					pstmt.setLong(2, contact);
					pstmt.setString(3, password);
					pstmt.setString(4, email);

					// 4.Execute Query
					int y = pstmt.executeUpdate();
					if (y > 0) {
						JOptionPane.showMessageDialog(null, "Update Successfull", "SYSTEM", 1);
						passtxt.setText("");
						confrmpasstxt.setText("");
						addtxt.setText("");
						contxt.setText("");
						mailtxt.setText("");
					}

				} catch (ClassNotFoundException exp) {
					System.out.println("Driver Class dispute while Updating The Employee record " + exp);
				} catch (MySQLIntegrityConstraintViolationException exp) {
					System.out.println("SQL dispute while Updating The Employee record " + exp);
					JOptionPane.showMessageDialog(null, "Email Already Exist", "Admin", 0);
				} catch (SQLException exp) {
					System.out.println("SQL dispute while Updating The Employee record " + exp);
				}
			}
		}

		if (me.getSource() == cancel) {
			editempframe.dispose();
		}

	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancelb.png"));
		}

		if (me.getSource() == update) {
			update.setIcon(new ImageIcon("myImages/updateb.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancela.png"));
		}

		if (me.getSource() == update) {
			update.setIcon(new ImageIcon("myImages/updatea.png"));
		}
	}
}
