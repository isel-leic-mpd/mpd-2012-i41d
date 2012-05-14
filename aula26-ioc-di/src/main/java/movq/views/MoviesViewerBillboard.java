package movq.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;

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

public class MoviesViewerBillboard implements IViewResults<IMovie>, ListDataListener{
	private static final String NEWLINE = System.getProperty("line.separator");

	private final JTextArea board;
	private final ListModel<IMovie> model;

	public MoviesViewerBillboard(ListModel<IMovie> m){
		this(m, Color.WHITE, Color.black);
	}

	public MoviesViewerBillboard(ListModel<IMovie> m, Color back, Color fg){
		this.model = m;
		m.addListDataListener(this);
		board = new JTextArea();
		board.setPreferredSize(new Dimension(300,300));
		board.setBackground(back);
		board.setForeground(fg);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JDialog dialog = new JDialog();
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
	private void showResults(){
		showResults(new Iterable<IMovie>() {public Iterator<IMovie> iterator() {
			return new Iterator<IMovie>() {
				int idx = 0;
				@Override
				public boolean hasNext() {
					return idx < model.getSize();
				}

				@Override
				public IMovie next() {
					return model.getElementAt(idx++);
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException();					}
			};
		}
		});
	}
	@Override
	public void contentsChanged(ListDataEvent e) {
		showResults();
	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
		showResults();
	}

	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		showResults();
	}
}
