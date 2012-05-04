package movq.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import movq.core.IMovie;
import movq.core.IViewResults;

public class MoviesViewerBillboard implements IViewResults<IMovie>{
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final JTextArea board; 
	
	public MoviesViewerBillboard(){
		this(Color.WHITE, Color.black);
	}
	
	public MoviesViewerBillboard(Color back, Color fg){		
		board = new JTextArea();
		board.setPreferredSize(new Dimension(300,300));
		board.setBackground(back);
		board.setForeground(fg);
	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JDialog dialog = new JDialog();
				dialog.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				dialog.setLayout(new BorderLayout());// Strategy pattern
				dialog.add(
						new JScrollPane(board), // Decorator pattern -> component wrapped inside a scroller 
						BorderLayout.CENTER); // Strategy pattern
				dialog.setModal(false);
				dialog.setTitle("Billboard");
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}
	@Override
	public void showResults(Iterable<IMovie> res) {
		board.setText("");
		for (IMovie m : res) {
			board.append(m.getName());
			board.append(NEWLINE);
		}
		board.invalidate();
		board.repaint();
	}
}
