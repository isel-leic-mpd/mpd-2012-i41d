package movq.app;

import java.util.LinkedList;

import movq.controllers.MovieController;
import movq.core.IMovie;
import movq.core.IMovieFinder;
import movq.core.IViewQueryMovie;
import movq.core.IViewResults;
import movq.finders.FinderJtmdbService;
import movq.model.ListModelCollection;
import movq.views.MoviesViewerBillboard;
import movq.views.ViewQueryMovieGui;

public class Program {
	public static void main(String [] args) throws Exception{
		
		IMovieFinder finder = new FinderJtmdbService();

		ListModelCollection<IMovie> model = new ListModelCollection<IMovie>(
				new LinkedList<IMovie>());
		
		// IViewResults<IMovie> vResults = new ViewResultsInConsle();
		new MoviesViewerBillboard(model);
		
		//IViewQueryMovie query = new ViewQueryMovieConsole(finder, vResults);
		IViewQueryMovie query = 
			new ViewQueryMovieGui(
				new MovieController(
						finder, 
						model));
		query.launch();
	}
}
