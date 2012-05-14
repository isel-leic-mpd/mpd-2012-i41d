package cntdi;

public interface IBinder {
	<T> IBindingBuilder<T> bind(final Class<T> key);
}
