package movq.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class ListModelCollection<T> extends AbstractListModel<T> implements Collection<T>{
	final List<T> under;

	public ListModelCollection(List<T> under) {
		this.under = under;
	}
	
	/* ======================================================
	 * -------------------- ListModel implementation --------
	 * ====================================================== */
	@Override
	public int getSize() {
		return size();
	}
	@Override
	public T getElementAt(int arg0) {
		return under.get(arg0);
	}
	
	/* ======================================================
	 * -------------------- Collection implementation --------
	 * ====================================================== */

	@Override
	public boolean add(T arg0) {
		boolean res = under.add(arg0);
		fireIntervalAdded(this, this.getSize()-1, this.getSize()-1);
		return res;
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		for (T t : arg0) {
			this.add(t);
		}
		return true;
	}

	@Override
	public void clear() {
		int size = this.getSize();
		under.clear();
		fireIntervalRemoved(this, 0, size-1);
	}

	@Override
	public boolean contains(Object arg0) {
		return under.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		return under.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {
		return under.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return under.iterator();
	}

	@Override
	public boolean remove(Object arg0) {
		return under.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return under.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return under.retainAll(arg0);
	}

	@Override
	public int size() {
		return under.size();
	}

	@Override
	public Object[] toArray() {
		return under.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return under.toArray(arg0);
	}

}
