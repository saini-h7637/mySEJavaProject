import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class empmodule implements MouseListener {
	JFrame empmodule;
	JTextField logintxt;
	JLabel backpic, homelab, logout, hatchlab, sedanlab, suvlab, vanlab, empPort;
	Font f;

	public void showempmodule(String a) {

		empmodule = new JFrame("Employee Module");
		empmodule.setLayout(null);

		logintxt = new JTextField();
		logintxt.setEditable(false);
		logintxt.setOpaque(false);
		logintxt.setBorder(null);
		logintxt.setText("Welcome, " + a.toUpperCase());

		empPort = new JLabel("Employee Portal");
		hatchlab = new JLabel(new ImageIcon("myImages/hatchbacka.jpg"));
		sedanlab = new JLabel(new ImageIcon("myImages/sedana.png"));
		suvlab = new JLabel(new ImageIcon("myImages/suva.jpg"));
		vanlab = new JLabel(new ImageIcon("myImages/vana.jpg"));
		logout = new JLabel(new ImageIcon("myImages/logouta.png"));
		homelab = new JLabel(new ImageIcon("myImages/homelab2.png"));
		backpic = new JLabel(new ImageIcon("myImages/showroombackpic.jpg"));

		f = new Font("Script MT Bold", Font.PLAIN + Font.BOLD, 30);

		hatchlab.setBounds(230, 120, 400, 303);
		sedanlab.setBounds(730, 120, 400, 300);
		suvlab.setBounds(230, 450, 400, 302);
		vanlab.setBounds(730, 450, 400, 300);
		logintxt.setBounds(900, 30, 350, 50);
		logout.setBounds(1150, 15, 100, 100);
		homelab.setBounds(1250, 15, 100, 100);
		backpic.setBounds(0, 0, 1366, 768);
		empPort.setBounds(50, 15, 400, 100);

		logintxt.setFont(f);
		empPort.setFont(f);

		hatchlab.addMouseListener(this);
		sedanlab.addMouseListener(this);
		suvlab.addMouseListener(this);
		vanlab.addMouseListener(this);
		homelab.addMouseListener(this);
		logout.addMouseListener(this);

		empmodule.add(homelab);
		// empmodule.add(logout);
		empmodule.add(logintxt);
		empmodule.add(hatchlab);
		empmodule.add(sedanlab);
		empmodule.add(suvlab);
		empmodule.add(vanlab);
		empmodule.add(empPort);

		// empmodule.add(backpic);

		empmodule.setUndecorated(true);
		empmodule.setVisible(true);
		empmodule.setSize(1366, 768);
	}

	public void mouseEntered(MouseEvent me) {
		if (me.getSource() == hatchlab) {
			hatchlab.setIcon(new ImageIcon("myImages/hatchbackb.jpg"));
		}
		if (me.getSource() == sedanlab) {
			sedanlab.setIcon(new ImageIcon("myImages/sedanb.png"));
		}
		if (me.getSource() == suvlab) {
			suvlab.setIcon(new ImageIcon("myImages/suvb.jpg"));
		}
		if (me.getSource() == vanlab) {
			vanlab.setIcon(new ImageIcon("myImages/vanb.jpg"));
		}

	}

	public void mouseExited(MouseEvent me) {
		if (me.getSource() == hatchlab) {
			hatchlab.setIcon(new ImageIcon("myImages/hatchbacka.jpg"));
		}
		if (me.getSource() == sedanlab) {
			sedanlab.setIcon(new ImageIcon("myImages/sedana.png"));
		}
		if (me.getSource() == suvlab) {
			suvlab.setIcon(new ImageIcon("myImages/suva.jpg"));
		}
		if (me.getSource() == vanlab) {
			vanlab.setIcon(new ImageIcon("myImages/vana.jpg"));
		}
	}

	public void mouseClicked(MouseEvent me) {

		if (me.getSource() == homelab) {
			homepage hp = new homepage();
			hp.showFrontFrame();
			empmodule.dispose();
		}
		if (me.getSource() == logout) {
			emploginframe lf = new emploginframe();
			lf.showloginframe();
			empmodule.dispose();
		}
	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}
}
