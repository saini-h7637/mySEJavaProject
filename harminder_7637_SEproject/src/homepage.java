
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class homepage implements ActionListener, Runnable, MouseListener {
	JFrame frontframe;
	JLabel headlogo, headlab, logo, contact, backpic, imagelab, combo, combolabel, adminicon, empicon, exit, exitlab;
	Font f, g;

	public void run() {
		slideImages();
	}

	void slideImages() {
		int i = 1;
		while (true) {
			imagelab.setIcon(new ImageIcon("myImages/snap" + i + ".jpg"));
			i = i + 1;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (i > 4)
				i = 1;
		}
	}

	public void showFrontFrame() {
		frontframe = new JFrame("Home Module");
		frontframe.setLayout(null);

		headlab = new JLabel("Used-Car Dealership");
		headlogo = new JLabel(new ImageIcon("myImages/sale.png"));
		contact = new JLabel(new ImageIcon("myImages/contact.png"));
		adminicon = new JLabel(new ImageIcon("myImages/adminicon.png"));
		empicon = new JLabel(new ImageIcon("myImages/empicon.png"));
		exit = new JLabel(new ImageIcon("myImages/exit4.png"));
		backpic = new JLabel(new ImageIcon("myImages/backpic.jpg"));
		imagelab = new JLabel(new ImageIcon("myImages/snap1.jpg"));

		f = new Font("Script MT Bold", Font.PLAIN + Font.BOLD, 70);
		g = new Font("Arial", Font.PLAIN + Font.BOLD, 30);

		headlab.setBounds(440, 35, 700, 90);
		headlogo.setBounds(5, 25, 380, 140);
		contact.setBounds(1100, 60, 330, 170);
		imagelab.setBounds(0, 195, 1366, 440);
		adminicon.setBounds(156, 645, 100, 101);
		empicon.setBounds(1156, 645, 100, 101);
		exit.setBounds(1222, 25, 90, 90);
		backpic.setBounds(0, 0, 1366, 768);
		headlab.setFont(f);

		adminicon.addMouseListener(this);
		empicon.addMouseListener(this);
		exit.addMouseListener(this);

		frontframe.add(headlab);
		frontframe.add(headlogo);
		frontframe.add(adminicon);
		frontframe.add(empicon);
		frontframe.add(imagelab);
		frontframe.add(exit);
		frontframe.add(backpic);

		Thread t = new Thread(this);
		t.start();

		frontframe.setUndecorated(true);
		frontframe.setVisible(true);
		frontframe.setSize(1366, 768);
	}

	public void mouseEntered(MouseEvent me) {
	}

	public void mouseExited(MouseEvent me) {
	}

	public void mouseClicked(MouseEvent me) {
		if (me.getSource() == exit) {
			frontframe.dispose();
		}

		if (me.getSource() == empicon) {
			emploginframe lf = new emploginframe();
			lf.showloginframe();
			frontframe.dispose();
		}

		if (me.getSource() == adminicon) {
			adminloginframe alf = new adminloginframe();
			alf.showadminloginframe();
			frontframe.dispose();
		}

	}

	public void mousePressed(MouseEvent me) {
	}

	public void mouseReleased(MouseEvent me) {
	}

	public void actionPerformed(ActionEvent e) {
	}

}