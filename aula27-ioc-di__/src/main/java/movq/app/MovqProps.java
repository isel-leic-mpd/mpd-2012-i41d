package movq.app;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;

import movq.controllers.MovieController;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IViewQueryMovie;
import movq.core.IViewResults;
import movq.finders.FinderJtmdbService;
import movq.model.ListModelCollection;
import movq.views.MoviesViewerBillboard;
import movq.views.ViewQueryMovieGui;
import cntdi.AbstractModule;
import cntdi.Scopes;

public class MovqProps extends AbstractModule{
	@Override
	public void configure() {
		bind(IMovieFinder.class).to(FinderJtmdbService.class);
		bind(IViewResults.class).to(MoviesViewerBillboard.class);
		bind(ListModel.class).to(ListModelCollection.class).in(Scopes.SINGLETON);

		bind(IViewQueryMovie.class).to(ViewQueryMovieGui.class);
		// bind(IViewQueryMovie.class).to(ViewQueryMovieConsole.class);
		
		bind(IMovieController.class).to(MovieController.class);

		bind(Collection.class).to(ListModelCollection.class).in(Scopes.SINGLETON);;
		bind(List.class).to(LinkedList.class);
	}
}
