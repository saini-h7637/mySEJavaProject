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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class adminmodule implements MouseListener {
	JFrame adminmodule;
	JLabel addemp, removeemp, editemp, addcar, editcar, removecar, logout, employee, car;
	JTable cartable, emptable;
	JTextField logintxt;
	JScrollPane cartablescroll, emptablescroll;
	String headings[] = { "CarName", "CarType", "CarModel", "CarColor", "Year", "Price", "Stock", "TotalAmt", "VIN" };
	String headings2[] = { "Employee Name", "Gender", "DOB", "Address", "Contact", "E-Mail", "Proofs", "Proof No.",
			"Date Joined", "Username", "Password" };
	Object data[][] = new Object[50][9];
	Object data2[][] = new Object[50][11];
	Font f, f1;

	public void showadminlogin(String a) {
		adminmodule = new JFrame();
		adminmodule.setLayout(null);

		logintxt = new JTextField();
		logintxt.setBorder(null);
		logintxt.setEditable(false);
		logintxt.setOpaque(false);
		logintxt.setText("Welcome, " + a.toUpperCase());

		employee = new JLabel("Employees");
		car = new JLabel("Vehicles");

		addemp = new JLabel(new ImageIcon("myImages/empadda.png"));
		editemp = new JLabel(new ImageIcon("myImages/empedita.png"));
		removeemp = new JLabel(new ImageIcon("myImages/empremovea.png"));
		addcar = new JLabel(new ImageIcon("myImages/caradda.png"));
		editcar = new JLabel(new ImageIcon("myImages/caredita.png"));
		removecar = new JLabel(new ImageIcon("myImages/carremovea.png"));
		logout = new JLabel(new ImageIcon("myImages/logouta.png"));

		f = new Font("Script MT Bold", Font.PLAIN + Font.BOLD, 25);
		f1 = new Font("Script MT Bold", Font.PLAIN + Font.BOLD, 40);
		employee.setFont(f1);
		car.setFont(f1);

		addemp.setBounds(240, 410, 133, 133);
		editemp.setBounds(108, 567, 133, 133);
		removeemp.setBounds(375, 569, 133, 133);
		addcar.setBounds(970, 412, 130, 130);
		editcar.setBounds(831, 560, 130, 130);
		removecar.setBounds(1106, 558, 130, 130);
		logintxt.setBounds(900, 20, 300, 40);
		logout.setBounds(1222, 20, 90, 41);
		employee.setBounds(240, 180, 250, 150);
		car.setBounds(990, 180, 250, 150);

		logintxt.setFont(f);

		adminmodule.add(addemp);
		adminmodule.add(editemp);
		adminmodule.add(removeemp);
		adminmodule.add(addcar);
		adminmodule.add(removecar);
		adminmodule.add(editcar);
		adminmodule.add(logout);
		adminmodule.add(logintxt);
		adminmodule.add(employee);
		adminmodule.add(car);

		addemp.addMouseListener(this);
		editemp.addMouseListener(this);
		removeemp.addMouseListener(this);
		addcar.addMouseListener(this);
		editcar.addMouseListener(this);
		removecar.addMouseListener(this);
		logout.addMouseListener(this);

		adminmodule.setUndecorated(true);
		adminmodule.setVisible(true);
		adminmodule.setSize(1366, 768);
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == addemp) {
			addemp ef = new addemp();
			ef.showaddempframe();

		}

		if (me.getSource() == editemp) {
			editemp ee = new editemp();
			ee.showeditempframe();

		}

		if (me.getSource() == removeemp) {
			removeemp re = new removeemp();
			re.showremoveempframe();

		}

		if (me.getSource() == addcar) {
			addcar ac = new addcar();
			ac.showaddcarframe();

		}

		if (me.getSource() == editcar) {
			editcar ec = new editcar();
			ec.showeditcarframe();

		}

		if (me.getSource() == removecar) {
			removecar rc = new removecar();
			rc.showremovecarframe();

		}

		if (me.getSource() == logout) {
			adminloginframe alf = new adminloginframe();
			alf.showadminloginframe();
			adminmodule.dispose();

		}
	}

	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == addemp) {
			addemp.setIcon(new ImageIcon("myImages/empaddb.png"));
		}

		if (me.getSource() == editemp) {
			editemp.setIcon(new ImageIcon("myImages/empeditb.png"));
		}

		if (me.getSource() == removeemp) {
			removeemp.setIcon(new ImageIcon("myImages/empremoveb.png"));
		}

		if (me.getSource() == addcar) {
			addcar.setIcon(new ImageIcon("myImages/caraddb.png"));
		}

		if (me.getSource() == editcar) {
			editcar.setIcon(new ImageIcon("myImages/careditb.png"));
		}

		if (me.getSource() == removecar) {
			removecar.setIcon(new ImageIcon("myImages/carremoveb.png"));
		}

	}

	public void mouseExited(MouseEvent me) {
		if (me.getSource() == addemp) {
			addemp.setIcon(new ImageIcon("myImages/empadda.png"));
		}

		if (me.getSource() == editemp) {
			editemp.setIcon(new ImageIcon("myImages/empedita.png"));
		}

		if (me.getSource() == removeemp) {
			removeemp.setIcon(new ImageIcon("myImages/empremovea.png"));
		}

		if (me.getSource() == addcar) {
			addcar.setIcon(new ImageIcon("myImages/caradda.png"));
		}

		if (me.getSource() == editcar) {
			editcar.setIcon(new ImageIcon("myImages/caredita.png"));
		}

		if (me.getSource() == removecar) {
			removecar.setIcon(new ImageIcon("myImages/carremovea.png"));
		}
	}
}