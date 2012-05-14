package cntdi;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class SimpleInjector implements IInjector{

	private Map<Class<?>, IBinding<?,?>> bindings = new HashMap<Class<?>, IBinding<?,?>>();
	private Map<Class<?>, IProvider<?>> providers = new HashMap<Class<?>, IProvider<?>>();
	
	
	public SimpleInjector(IModule propsFile){
		propsFile.configure(new IBinder() {
			@Override
			public <T> IBindingBuilder<T> bind(final Class<T> key) {
				return new IBindingBuilder<T>() {
					@Override
					public <S extends T> IScopedBindingBuilder<S> to(final Class<S> impl) {
						IProvider<S> prov = (IProvider<S>) providers.get(impl);
						if(prov == null){
							// prov = new ProviderMultiple(this, implClass);
							prov = new ProviderMultiple<S>(SimpleInjector.this, impl);
							providers.put(impl, prov);
						}
						SimpleBinding<T, S> b = new SimpleBinding<T , S>(key, prov);
						bindings.put(key, b);
						final IProvider<S> scopedProv = prov; 
						return new IScopedBindingBuilder<S>() {
							@Override
							public void in(IScope<S> scope) {
								if(scopedProv.getClass() == Scopes.SINGLETON.getClass()) return;
								IBinding obj = bindings.remove(key);
								IProvider<S> prov = scope.scope(scopedProv);
								bindings.put(key, new SimpleBinding<T , S>(key, prov));
								providers.put(impl, prov);
							}
						};
					}
				};
			}
		});
	}
	public SimpleInjector(String propsFile){
		Properties p = new Properties();
		try{
			p.load(new FileInputStream(propsFile));

			for (Map.Entry<Object, Object> par : p.entrySet()) {
				Class<?> key = Class.forName(par.getKey().toString());
				String implName = par.getValue().toString();
				Class implClass = Class.forName(implName);
				IProvider prov = providers.get(implClass);
				if(prov == null){
					// prov = new ProviderMultiple(this, implClass);
					prov = new ProviderSingleton(this, implClass);
					providers.put(implClass, prov);
				}
				bindings.put(key, new SimpleBinding(key, prov));
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public <T> T getInstance(Class<T> key){
		IBinding<?, ?> b = bindings.get(key); 
		return (T) b.getInstance();
	}

	@Override
	public <T> boolean contains(Class<T> key) {
		return bindings.containsKey(key);
	}
}
