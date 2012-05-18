package movq.views;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.inject.Inject;

import movq.core.IMovie;
import movq.core.IMovieController;
import movq.core.IMovieFinder;
import movq.core.IViewQueryMovie;
import movq.core.IViewResults;
import movq.finders.FinderJtmdbService;

public class ViewQueryMovieConsole implements IViewQueryMovie{

	final IMovieController ctr;

	@Inject
	public ViewQueryMovieConsole(final IMovieController c) {
		super();
		this.ctr = c;
	}
	@Override
	public void launch() throws Exception {
		//
		// run command shell
		//
		Scanner cin = new Scanner(in);
		out.println("****** Commmand shell application *****");
		while(true){
			out.print("> ");
			out.flush();
			String inLine = cin.nextLine();
			ctr.performQuery(inLine);
		}		
	}
}
