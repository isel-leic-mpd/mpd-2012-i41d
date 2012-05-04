package movq.controllers;

import javax.swing.JOptionPane;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IViewResults;

public class MovieController implements IMovieController{
	final IMovieFinder finder;
	final IViewResults<IMovie> vResults;

	
	public MovieController(IMovieFinder finder, IViewResults<IMovie> vResults) {
		super();
		this.finder = finder;
		this.vResults = vResults;
	}

	@Override
	public void performQuery(String key) {
		try {
			Iterable<IMovie> res = finder.search(key);
			vResults.showResults(res);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
}
