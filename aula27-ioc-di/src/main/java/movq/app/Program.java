package movq.app;

import java.util.LinkedList;
import java.util.List;

import cntdi.IInjector;
import cntdi.SimpleInjector;

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
	static final String cfgFile = "D:\\MyFolder\\ISEL\\Pg4 mpd - 2011-2012 2º sem\\i41D-git-repo\\MovieQuery.properties";
	
	public static void main(String [] args) throws Exception{
		/*
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
		*/
		
		// IInjector cnt = new SimpleInjector(cfgFile);
		IInjector cnt = new SimpleInjector(new MovqCfgModule());
		IViewQueryMovie query = cnt.getInstance(IViewQueryMovie.class);
		cnt.getInstance(IViewResults.class);
		query.launch();
		
	}
}
