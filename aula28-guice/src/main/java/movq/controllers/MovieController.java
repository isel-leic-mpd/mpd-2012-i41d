package movq.controllers;

import java.util.Collection;

import javax.swing.JOptionPane;

import com.google.inject.Inject;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IViewResults;

public class MovieController implements IMovieController{
	final IMovieFinder finder;
	final Collection<IMovie> model;
	
	@Inject
	public MovieController(IMovieFinder finder, Collection<IMovie> m) {
		super();
		this.finder = finder;
		this.model = m;
	}

	@Override
	public void performQuery(String key) {
		try {
			Iterable<IMovie> res = finder.search(key);
			model.clear();
			for (IMovie m : res) {
				model.add(m);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
	
}
