package movq.views;

import movq.core.IMovie;
import movq.core.IViewResults;

public class ViewResultsInConsle implements IViewResults<IMovie>{

	@Override
	public void showResults(Iterable<IMovie> res) {
		for (IMovie m : res) {
			System.out.println(m.getName());
		}
		
	}

}
