package cntdi;

public interface IBindingBuilder<T> {
	<S extends T> IScopedBindingBuilder<S> to(Class<S> impl);
}
