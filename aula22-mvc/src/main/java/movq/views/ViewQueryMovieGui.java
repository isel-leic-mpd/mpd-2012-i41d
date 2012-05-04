package movq.views;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IViewQueryMovie;
import movq.core.IViewResults;

public class ViewQueryMovieGui implements IViewQueryMovie{
	final JFrame frm;
	final IMovieController ctr;

	public ViewQueryMovieGui(IMovieController c) {
		this.ctr = c;
		frm = new JFrame();
		frm.setLayout(new FlowLayout());
		frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		final JTextField txt = new JTextField(16);
		JButton bt = new JButton("Search");
		frm.add(new JLabel("Movie key:"));
		frm.add(txt);
		frm.add(bt);
		ActionListener lst = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String key = txt.getText();
				ctr.performQuery(key);
			}
		};
		bt.addActionListener(lst);
		txt.addActionListener(lst);
	}

	@Override
	public void launch() throws Exception {
		frm.pack();
		frm.setVisible(true);		
	}
}
