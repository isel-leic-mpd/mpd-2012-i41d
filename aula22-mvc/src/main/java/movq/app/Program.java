package movq.app;

import movq.controllers.MovieController;
import movq.core.IMovie;
import movq.core.IMovieFinder;
import movq.core.IViewQueryMovie;
import movq.core.IViewResults;
import movq.finders.FinderJtmdbService;
import movq.views.MoviesViewerBillboard;
import movq.views.ViewQueryMovieGui;

public class Program {
	public static void main(String [] args) throws Exception{
		
		IMovieFinder finder = new FinderJtmdbService();
		// IViewResults<IMovie> vResults = new ViewResultsInConsle();
		IViewResults<IMovie> vResults = new MoviesViewerBillboard();
		
		//IViewQueryMovie query = new ViewQueryMovieConsole(finder, vResults);
		IViewQueryMovie query = 
			new ViewQueryMovieGui(
				new MovieController(
						finder, 
						vResults));
		query.launch();
	}
}
