package cntdi;

public interface IScope<T> {
	IProvider<T> scope(IProvider<T> prov);
}
