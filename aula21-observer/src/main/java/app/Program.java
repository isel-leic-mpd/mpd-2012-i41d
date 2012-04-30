package app;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Program {
	public static void main(String [] args){
		//
		// setup window
		// 
		JFrame w = new JFrame();
		final JButton bt1 = new JButton("Hello");
		JButton bt2 = new JButton("<= Forward");
		w.add(bt1);
		w.add(bt2);
		w.setLayout(new FlowLayout());
		w.pack();
		w.setVisible(true);
		// 
		// add Listeners
		// 
		bt1.addMouseListener(new MyMouseListener());
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ola");
			}
		});
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Forward");
				bt1.dispatchEvent(arg0);
			}
		});
	}
}
