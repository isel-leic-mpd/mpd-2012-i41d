package cntdi;

public interface IScopedBindingBuilder<S>{
	void in(IScope<S> scope);
}
