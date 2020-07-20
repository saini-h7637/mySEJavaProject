import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.toedter.calendar.JDateChooser;

class addemp implements MouseListener, Runnable, KeyListener {
	int count;
	JFrame addempframe;
	JTextField nametxt, contxt, mailtxt, usernametxt, prooftxt;
	JTextArea addtxt;
	JLabel backpic, save, cancel, datelab;
	JDateChooser dobchooser;
	JScrollPane jsp;
	JPasswordField passtxt;
	JComboBox<String> empproof;
	JRadioButton malerb, femalerb;
	ButtonGroup gendergrp;
	String proof[] = { "Select Proof", "Health Card", "Driving Licence", "SIN Number" };
	Font f;

	public void run() {
		showWatch();
	}

	void showWatch() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
		String ct = sdf.format(d);
		datelab.setText(ct);
	}

	public void showaddempframe() {
		addempframe = new JFrame();
		addempframe.setLayout(null);

		nametxt = new JTextField();
		contxt = new JTextField();
		mailtxt = new JTextField();
		usernametxt = new JTextField();
		addtxt = new JTextArea();
		passtxt = new JPasswordField();
		jsp = new JScrollPane(addtxt);
		prooftxt = new JTextField("Proof no.");
		prooftxt.setForeground(new Color(192, 192, 192));
		datelab = new JLabel();
		datelab.setBorder(new LineBorder(Color.GRAY, 1));
		datelab.setOpaque(true);
		datelab.setBackground(Color.WHITE);
		empproof = new JComboBox<String>(proof);

		backpic = new JLabel(new ImageIcon("myImages/backpicforaddemp.jpg"));
		save = new JLabel(new ImageIcon("myImages/savea.png"));
		cancel = new JLabel(new ImageIcon("myImages/cancela.png"));

		malerb = new JRadioButton("MALE");
		malerb.setOpaque(false);
		femalerb = new JRadioButton("FEMALE");
		femalerb.setOpaque(false);
		gendergrp = new ButtonGroup();
		gendergrp.add(malerb);
		gendergrp.add(femalerb);

		dobchooser = new JDateChooser();
		f = new Font("Arial", Font.PLAIN + Font.BOLD, 20);

		nametxt.setBounds(320, 20, 275, 40);
		malerb.setBounds(320, 70, 130, 40);
		femalerb.setBounds(465, 70, 130, 40);
		dobchooser.setBounds(320, 120, 275, 40);
		contxt.setBounds(320, 220, 275, 40);
		mailtxt.setBounds(320, 270, 275, 40);
		empproof.setBounds(320, 320, 160, 40);
		prooftxt.setBounds(485, 320, 110, 40);
		datelab.setBounds(320, 370, 275, 40);
		usernametxt.setBounds(320, 420, 275, 40);
		passtxt.setBounds(320, 470, 275, 40);
		jsp.setBounds(320, 170, 275, 40);

		backpic.setBounds(0, 0, 800, 600);
		save.setBounds(592, 520, 60, 60);
		cancel.setBounds(672, 520, 60, 60);

		nametxt.setFont(f);
		contxt.setFont(f);
		mailtxt.setFont(f);
		usernametxt.setFont(f);
		addtxt.setFont(f);
		passtxt.setFont(f);
		datelab.setFont(f);
		dobchooser.setFont(f);
		malerb.setFont(f);
		femalerb.setFont(f);
		empproof.setFont(f);
		prooftxt.setFont(f);

		addempframe.add(nametxt);
		addempframe.add(contxt);
		addempframe.add(mailtxt);
		addempframe.add(usernametxt);
		addempframe.add(passtxt);
		addempframe.add(jsp);
		addempframe.add(datelab);
		addempframe.add(dobchooser);
		addempframe.add(malerb);
		addempframe.add(femalerb);
		addempframe.add(empproof);
		addempframe.add(prooftxt);

		addempframe.add(save);
		addempframe.add(cancel);
		addempframe.add(backpic);

		prooftxt.addKeyListener(this);
		cancel.addMouseListener(this);
		save.addMouseListener(this);

		Thread t = new Thread(this);
		t.start();

		addempframe.setUndecorated(true);
		addempframe.setSize(800, 600);
		addempframe.setLocationRelativeTo(null);
		addempframe.setVisible(true);
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancelb.png"));
		}

		if (me.getSource() == save) {
			save.setIcon(new ImageIcon("myImages/saveb.png"));
		}

	}

	public void mouseExited(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancela.png"));
		}

		if (me.getSource() == save) {
			save.setIcon(new ImageIcon("myImages/savea.png"));
		}

	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == save) {
			boolean b = true;
			String name = nametxt.getText();
			if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "Employee Name Required", "SYSTEM", 0);
				b = false;
			}

			String gender = null;
			if (malerb.isSelected())
				gender = "Male";
			else if (femalerb.isSelected())
				gender = "Female";
			else {
				JOptionPane.showMessageDialog(null, "Select Gender", "SYSTEM", 0);
				b = false;
			}
			Date dob = dobchooser.getDate();
			String dobstr = "";
			if (dob == null) {
				JOptionPane.showMessageDialog(null, "Date of Birth Required", "SYSTEM", 0);
				b = false;
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
				dobstr = sdf.format(dob);
			}

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

			String proof = (String) empproof.getSelectedItem();
			if (proof.equals("Select Proof")) {
				JOptionPane.showMessageDialog(null, "Select Any 1 Proof", "SYSTEM", 0);
				b = false;
			}

			int proofno = 0;
			if (prooftxt.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Proof No.", "SYSTEM", 0);
				b = false;
			} else
				try {
					proofno = Integer.parseInt(prooftxt.getText());
				} catch (NumberFormatException exp) {
					JOptionPane.showMessageDialog(null, "Proof No. must be Numeric", "SYSTEM", 0);
					b = false;
				}

			String date = datelab.getText();

			String username = usernametxt.getText();
			if (username.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Username", "SYSTEM", 0);
				b = false;
			}

			String password = String.valueOf(passtxt.getPassword());
			if (password.equals("")) {
				JOptionPane.showMessageDialog(null, "Please Enter Password", "SYSTEM", 0);
				b = false;
			}

			System.out.println(name + " " + gender + " " + dobstr + " " + address + " " + contact + " " + email + " "
					+ proof + " " + proofno + " " + date + " " + username + " " + password);

			if (b == true) {
				try {
					// 1.Driver Registeration
					Class.forName("com.mysql.jdbc.Driver");
					// 2.Connection Establishment
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
					// 3.Prepare SQL Query
					PreparedStatement pstmt = con.prepareStatement("insert into emptab values(?,?,?,?,?,?,?,?,?,?,?)");
					pstmt.setString(1, name);
					pstmt.setString(2, gender);
					pstmt.setString(3, dobstr);
					pstmt.setString(4, address);
					pstmt.setLong(5, contact);
					pstmt.setString(6, email);
					pstmt.setString(7, proof);
					pstmt.setInt(8, proofno);
					pstmt.setString(9, date);
					pstmt.setString(10, username);
					pstmt.setString(11, password);

					// 4.Execute Query
					int y = pstmt.executeUpdate();
					if (y > 0) {
						JOptionPane.showMessageDialog(null, "New Employee Saved Successfully", "SYSTEM", 1);

						nametxt.setText("");
						passtxt.setText("");
						addtxt.setText("");
						contxt.setText("");
						mailtxt.setText("");
						prooftxt.setText("");
						usernametxt.setText("");
						dobchooser.setDate(null);
						empproof.setSelectedIndex(0);
						gendergrp.clearSelection();
					}

				} catch (ClassNotFoundException exp) {
					System.out.println("Driver Class dispute while Saving The Employee record " + exp);
				} catch (MySQLIntegrityConstraintViolationException exp) {
					System.out.println("SQL dispute while Saving The Employee record " + exp);
					JOptionPane.showMessageDialog(null, "Email Already Exist", "Admin", 0);
				} catch (SQLException exp) {
					System.out.println("SQL dispute while Saving The Employee record " + exp);
				}
			}
		}
		if (me.getSource() == cancel) {
			addempframe.dispose();
		}
	}

	public void keyTyped(KeyEvent e) {
		if (e.getSource() == prooftxt) {
			count++;
			if (count == 1) {
				prooftxt.setText("");
				prooftxt.setForeground(Color.BLACK);
			}
		}
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

}
