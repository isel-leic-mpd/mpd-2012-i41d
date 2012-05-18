package movq.core;

import javax.swing.event.ListDataListener;

public interface IViewResults<T> extends ListDataListener{
	void showResults(Iterable<T> res);
}
