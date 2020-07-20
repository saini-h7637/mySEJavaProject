
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

class addcar implements MouseListener, ActionListener {
	JFrame addcarframe;
	JTextField carname, year, stock, cp, totalamt, vin;
	JComboBox<String> cartype, carmodel, carcolor;
	JLabel cancel, save, backpic;
	Font f;
	String type[] = { "Select type", "Petrol", "Diesel", "Electric" };
	String model[] = { "Select Model" };
	String color[] = { "Select Color", "white", "black", "red", "brown" };

	public void showaddcarframe() {
		addcarframe = new JFrame();
		addcarframe.setLayout(null);

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

		cancel = new JLabel(new ImageIcon("MyImages/cancela.png"));
		backpic = new JLabel(new ImageIcon("MyImages/backpicforaddcar.jpg"));
		save = new JLabel(new ImageIcon("MyImages/savea.png"));

		f = new Font("Arial", Font.PLAIN + Font.BOLD, 25);

		cartype = new JComboBox<String>(type);
		carmodel = new JComboBox<String>(model);
		carcolor = new JComboBox<String>(color);

		carname.setBounds(320, 35, 275, 40);
		cartype.setBounds(320, 90, 275, 40);
		carmodel.setBounds(320, 145, 275, 40);
		carcolor.setBounds(320, 200, 275, 40);
		year.setBounds(320, 255, 275, 40);
		cp.setBounds(320, 310, 275, 40);
		stock.setBounds(320, 365, 275, 40);
		totalamt.setBounds(320, 420, 275, 40);
		vin.setBounds(320, 475, 275, 40);
		save.setBounds(592, 520, 60, 60);
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
		save.addMouseListener(this);
		cartype.addActionListener(this);
		carmodel.addActionListener(this);

		addcarframe.add(cartype);
		addcarframe.add(carmodel);
		addcarframe.add(carname);
		addcarframe.add(carcolor);

		addcarframe.add(stock);
		addcarframe.add(year);
		addcarframe.add(cp);
		addcarframe.add(totalamt);
		addcarframe.add(vin);
		addcarframe.add(cancel);
		addcarframe.add(save);
		addcarframe.add(backpic);

		addcarframe.setUndecorated(true);
		addcarframe.setSize(800, 600);
		addcarframe.setLocationRelativeTo(null);
		addcarframe.setVisible(true);
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == cancel) {
			addcarframe.dispose();
		}

		if (me.getSource() == save) {
			String CarName = carname.getText();
			String CarType = (String) cartype.getSelectedItem();
			String CarModel = (String) carmodel.getSelectedItem();
			String CarColor = (String) carcolor.getSelectedItem();
			int Year = Integer.parseInt(year.getText());
			int Price = Integer.parseInt(cp.getText());
			int Stock = Integer.parseInt(stock.getText());
			int TotalAmt = Integer.parseInt(totalamt.getText());
			String VIN = vin.getText();

			System.out.println(CarName + "" + CarModel + CarType + CarColor + Year + Price + Stock + TotalAmt);
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
				PreparedStatement pstmt = con.prepareStatement("insert into cartab values(?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, CarName);
				pstmt.setString(2, CarType);
				pstmt.setString(3, CarModel);
				pstmt.setString(4, CarColor);
				pstmt.setInt(5, Year);
				pstmt.setInt(6, Price);
				pstmt.setInt(7, Stock);
				pstmt.setInt(8, TotalAmt);
				pstmt.setString(9, VIN);

				int y = pstmt.executeUpdate();
				if (y > 0) {
					JOptionPane.showMessageDialog(null, "record Saved Successfully", "Admin", 1);
					carname.setText("");
					cartype.setSelectedItem("Select type");
					carmodel.setSelectedItem("Select Model");
					carcolor.setSelectedItem("Select Color");
					year.setText("");
					cp.setText("");
					stock.setText("");
					totalamt.setText("");
					vin.setText("");
				}

			}

			catch (ClassNotFoundException exp) {
				System.out.println("Driver Class dispute while Saving The Vehicle record " + exp);
			} catch (MySQLIntegrityConstraintViolationException exp) {
				System.out.println("SQL dispute while Saving The Vehicle record " + exp);
				JOptionPane.showMessageDialog(null, "VIN Already Exist", "Admin", 0);
			} catch (SQLException exp) {
				System.out.println("SQL dispute while Saving The Vehicle record " + exp);
			}
		}
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("MyImages/cancelb.png"));
		}

		if (me.getSource() == save) {
			save.setIcon(new ImageIcon("MyImages/saveb.png"));
		}

	}

	@Override
	public void mouseExited(MouseEvent me) {
		if (me.getSource() == cancel) {
			cancel.setIcon(new ImageIcon("MyImages/cancela.png"));
		}

		if (me.getSource() == save) {
			save.setIcon(new ImageIcon("MyImages/savea.png"));
		}

	}

	@Override
	public void actionPerformed(ActionEvent ae)

	{
		if (ae.getSource() == cartype) {
			// String type=(String)cartype.getSelectedItem();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3308/usedCar_db", "root", "");
				PreparedStatement pstmt = con.prepareStatement("select model from cartypetab");
				// pstmt.setString(1,type);
				ResultSet rst = pstmt.executeQuery();

				carmodel.removeAllItems();
				carmodel.addItem("Select Model");
				while (rst.next()) {
					carmodel.addItem(rst.getString("model"));

				}

			} catch (ClassNotFoundException exp) {
				System.out.println("Driver Class dispute while Saving The Vehicle record " + exp);
			} catch (SQLException exp) {
				System.out.println("SQL dispute while Saving The Vehicle record " + exp);
			}
		}
	}
}
