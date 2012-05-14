package cntdi;

import movq.core.IMovieFinder;

public abstract class AbstractModule implements IModule{
	IBinder binder;
	
	@Override
	public final void configure(IBinder binder){
		this.binder = binder;
		configure();
	}
	
	public abstract void configure();
	
	protected <T> IBindingBuilder<T> bind(Class<T> key) {
		return binder.bind(key);
	}
}
