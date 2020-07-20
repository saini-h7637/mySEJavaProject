
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
import javax.swing.JTextField;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

class removecar implements MouseListener {
	JFrame removecarframe;
	JTextField carname, year, stock, cp, totalamt, cartype, carmodel, carcolor, vin;
	JLabel cancel, view, remove, backpic;
	Font f;

	public void showremovecarframe() {
		removecarframe = new JFrame();
		removecarframe.setLayout(null);

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
		backpic = new JLabel(new ImageIcon("myImages/backpicforaddcar.jpg"));
		view = new JLabel(new ImageIcon("myImages/viewa.png"));
		remove = new JLabel(new ImageIcon("myImages/removecara.png"));

		f = new Font("Arial", Font.PLAIN + Font.BOLD, 25);
		cartype = new JTextField("");
		carmodel = new JTextField("");
		carcolor = new JTextField("");

		carname.setBounds(320, 35, 275, 40);
		cartype.setBounds(320, 90, 275, 40);
		carmodel.setBounds(320, 145, 275, 40);
		carcolor.setBounds(320, 200, 275, 40);
		year.setBounds(320, 255, 275, 40);
		cp.setBounds(320, 310, 275, 40);
		stock.setBounds(320, 365, 275, 40);
		totalamt.setBounds(320, 420, 275, 40);
		vin.setBounds(320, 473, 275, 40);

		view.setBounds(542, 515, 70, 70);
		remove.setBounds(622, 515, 75, 70);
		cancel.setBounds(702, 515, 70, 70);
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
		view.addMouseListener(this);
		remove.addMouseListener(this);

		removecarframe.add(cartype);
		removecarframe.add(carmodel);
		removecarframe.add(carname);
		removecarframe.add(carcolor);

		removecarframe.add(stock);
		removecarframe.add(year);
		removecarframe.add(cp);
		removecarframe.add(totalamt);
		removecarframe.add(vin);
		removecarframe.add(cancel);
		removecarframe.add(remove);
		removecarframe.add(view);
		removecarframe.add(backpic);

		removecarframe.setUndecorated(true);
		removecarframe.setSize(800, 600);
		removecarframe.setLocationRelativeTo(null);
		removecarframe.setVisible(true);
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == cancel) {
			removecarframe.dispose();
		}

		else if (me.getSource() == view) {
			String VIN = vin.getText();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
				PreparedStatement pstmt = con.prepareStatement("select * from cartab where vin=?");
				pstmt.setString(1, VIN);
				ResultSet rst = pstmt.executeQuery();
				if (rst.next()) {
					carname.setText(rst.getString("CarName"));
					cartype.setText(rst.getString("CarType"));
					carmodel.setText(rst.getString("CarModel"));
					carcolor.setText(rst.getString("CarColor"));
					year.setText(Integer.toString(rst.getInt("Year")));
					stock.setText(Integer.toString(rst.getInt("Stock")));
					cp.setText(Integer.toString(rst.getInt("Price")));
					totalamt.setText(Integer.toString(rst.getInt("TotalAmt")));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (me.getSource() == remove) {
			String VIN = vin.getText();

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
				PreparedStatement pstmt = con.prepareStatement("delete from cartab where VIN=?");
				pstmt.setString(1, VIN);

				int y = pstmt.executeUpdate();
				if (y > 0) {
					JOptionPane.showMessageDialog(null, "Record Deleted Successfully", "Admin", 1);
					carname.setText("");
					cartype.setText("");
					carmodel.setText("");
					carcolor.setText("");
					year.setText("");
					cp.setText("");
					stock.setText("");
					totalamt.setText("");
					vin.setText("");
				}

			}

			catch (ClassNotFoundException exp) {
				System.out.println("Driver Class dispute while Deleting The Vehicle record " + exp);
			} catch (MySQLIntegrityConstraintViolationException exp) {
				System.out.println("SQL dispute while Deleting The Vehicle record " + exp);
				JOptionPane.showMessageDialog(null, "VIN Already Exist", "Admin", 0);
			} catch (SQLException exp) {
				System.out.println("SQL dispute while Deleting The Vehicle record " + exp);
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

		if (me.getSource() == view) {
			view.setIcon(new ImageIcon("myImages/viewb.png"));
		}

		if (me.getSource() == remove) {
			remove.setIcon(new ImageIcon("myImages/removecarb.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("myImages/cancela.png"));
		}

		if (me.getSource() == view) {
			view.setIcon(new ImageIcon("myImages/viewa.png"));
		}

		if (me.getSource() == remove) {
			remove.setIcon(new ImageIcon("myImages/removecara.png"));
		}

	}

}
