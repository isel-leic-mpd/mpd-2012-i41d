package cntdi;


public abstract class AbstractModule implements IModule{

	private IBinder binder;
	
	@Override
	public final void configure(IBinder binder) {
		this.binder = binder;
		configure();
	}
	public abstract void configure();
	
	public <T> IBindingBuilder<T> bind(final Class<T> key){
		return binder.bind(key);
	}
}
