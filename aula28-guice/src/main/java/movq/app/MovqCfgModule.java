package movq.app;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ListModel;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.Singleton;

import movq.controllers.MovieController;
import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IViewQueryMovie;
import movq.core.IViewResults;
import movq.finders.FinderJtmdbService;
import movq.model.ListModelCollection;
import movq.views.MoviesViewerBillboard;
import movq.views.ViewQueryMovieGui;

import com.google.inject.TypeLiteral;

public class MovqCfgModule extends AbstractModule{
	@Override
	public void configure() {
		bind(IMovieFinder.class).to(FinderJtmdbService.class);
		bind(IViewResults.class).to(MoviesViewerBillboard.class);
		
		
		bind(IViewQueryMovie.class).to(ViewQueryMovieGui.class);
		//bind(IViewQueryMovie.class).to(ViewQueryMovieConsole.class);
		
		bind(IMovieController.class).to(MovieController.class);
		
		/*
		bind(new TypeLiteral<ListModel<IMovie>>(){}).to(new TypeLiteral<ListModelCollection<IMovie>>(){}).in(Scopes.SINGLETON);
		bind(new TypeLiteral<Collection<IMovie>>(){}).to(new TypeLiteral<ListModelCollection<IMovie>>(){}).in(Scopes.SINGLETON);
		bind(new TypeLiteral<List<IMovie>>(){}).to(new TypeLiteral<LinkedList<IMovie>>(){});
		*/
		
		ListModelCollection<IMovie> model = new ListModelCollection<IMovie>(new LinkedList<IMovie>());
		bind(new TypeLiteral<ListModel<IMovie>>(){}).toInstance(model);
		bind(new TypeLiteral<Collection<IMovie>>(){}).toInstance(model);
		Class<ListModel<IMovie>> c;
	}

}
