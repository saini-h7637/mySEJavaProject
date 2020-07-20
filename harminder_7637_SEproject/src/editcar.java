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

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

class editcar implements MouseListener, ActionListener {
	JFrame editcarframe;
	JTextField carname, year, stock, cp, totalamt, vin;

	JComboBox<String> cartype, carmodel, carcolor;
	JLabel cancel, update, backpic;
	Font f;
	String type[] = { "Select type", "Petrol", "Diesel" };
	String model[] = { "Select Model" };
	String color[] = { "Select Color", "white", "black", "red", "brown" };

	public void showeditcarframe() {
		editcarframe = new JFrame();
		editcarframe.setLayout(null);
		carname = new JTextField("");
		carname.setOpaque(true);
		year = new JTextField("");
		year.setOpaque(true);
		stock = new JTextField("");
		stock.setOpaque(true);
		cp = new JTextField("");
		cp.setOpaque(true);
		totalamt = new JTextField("");
		totalamt.setOpaque(true);
		vin = new JTextField("");

		cancel = new JLabel(new ImageIcon("myImages/cancela.png"));
		update = new JLabel(new ImageIcon("myImages/updatea.png"));
		backpic = new JLabel(new ImageIcon("myImages/backpicforeditcar.jpg"));

		f = new Font("Arial", Font.PLAIN + Font.BOLD, 25);

		cartype = new JComboBox<String>(type);
		carmodel = new JComboBox<String>(model);
		carcolor = new JComboBox<String>(color);
		cp.setBounds(380, 130, 275, 40);
		stock.setBounds(380, 190, 275, 40);
		totalamt.setBounds(380, 250, 275, 40);
		vin.setBounds(380, 310, 275, 40);

		update.setBounds(592, 520, 60, 60);
		cancel.setBounds(672, 520, 60, 60);

		backpic.setBounds(0, 0, 800, 600);

		carmodel.setFont(f);
		cartype.setFont(f);

		carname.setFont(f);
		carcolor.setFont(f);
		stock.setFont(f);
		year.setFont(f);
		cp.setFont(f);
		totalamt.setFont(f);
		vin.setFont(f);

		cancel.addMouseListener(this);
		update.addMouseListener(this);

		cartype.addActionListener(this);
		carmodel.addActionListener(this);
		editcarframe.add(stock);
		editcarframe.add(cp);
		editcarframe.add(totalamt);
		editcarframe.add(vin);
		editcarframe.add(cancel);
		editcarframe.add(update);

		editcarframe.add(backpic);

		editcarframe.setUndecorated(true);
		editcarframe.setSize(800, 600);
		editcarframe.setLocationRelativeTo(null);
		editcarframe.setVisible(true);
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == cancel) {
			editcarframe.dispose();
		}
		if (me.getSource() == update) {
			int Price = Integer.parseInt(cp.getText());
			int Stock = Integer.parseInt(stock.getText());
			int TotalAmt = Integer.parseInt(totalamt.getText());
			String VIN = vin.getText();
			System.out.println(Price + "" + Stock + "" + TotalAmt + "" + vin);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
				PreparedStatement pstmt = con
						.prepareStatement("update cartab set Price=?,Stock=?,TotalAmt=? where VIN=?");
				pstmt.setInt(1, Price);
				pstmt.setInt(2, Stock);
				pstmt.setInt(3, TotalAmt);
				pstmt.setString(4, VIN);

				int y = pstmt.executeUpdate();
				if (y > 0) {
					JOptionPane.showMessageDialog(null, "record Saved Successfully", "Admin", 1);
					cp.setText("");
					stock.setText("");
					totalamt.setText("");
					vin.setText("");
				}
			}

			catch (ClassNotFoundException exp) {
				System.out.println("Driver Class dispute while Editing The Vehicle record " + exp);
			} catch (MySQLIntegrityConstraintViolationException exp) {
				System.out.println("SQL dispute while while Editing The Vehicle record " + exp);
				JOptionPane.showMessageDialog(null, "VIN Already Exist", "Admin", 0);
			} catch (SQLException exp) {
				System.out.println("SQL dispute while while Editing The Vehicle record " + exp);
			}
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

		if (me.getSource() == update) {
			update.setIcon(new ImageIcon("myImages/updateb.png"));
		}

	}

	public void mouseExited(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancela.png"));
		}
		if (me.getSource() == update) {
			update.setIcon(new ImageIcon("myImages/updatea.png"));
		}
	}

	public void actionPerformed(ActionEvent ae) {
	}

}
