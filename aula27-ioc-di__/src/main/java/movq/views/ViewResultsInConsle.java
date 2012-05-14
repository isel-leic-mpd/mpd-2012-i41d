package movq.views;

import javax.swing.event.ListDataEvent;

import movq.core.IMovie;
import movq.core.IViewResults;

public class ViewResultsInConsle implements IViewResults<IMovie>{

	@Override
	public void showResults(Iterable<IMovie> res) {
		for (IMovie m : res) {
			System.out.println(m.getName());
		}
		
	}

	@Override
	public void contentsChanged(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalAdded(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intervalRemoved(ListDataEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
