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
	
	public SimpleInjector(IModule cfgModule){
		cfgModule.configure(new IBinder() {
			@Override
			public <T> IBindingBuilder<T> bind(final Class<T> key) {
				return new IBindingBuilder<T>() {
					@Override
					public <S extends T> IScopedBindingBuilder<S> to(final Class<S> impl) {
						IProvider<S> tmpProv = (IProvider<S>) providers.get(impl);
						if(tmpProv == null){
							tmpProv = new ProviderFromClass<S>(SimpleInjector.this, impl);
							providers.put(impl, tmpProv);
						}
						final IProvider<S> prov = tmpProv; 
						final IBinding<T, S> sb = new SimpleBinding<T, S>(key, tmpProv);
						bindings.put(key, sb);
						return new IScopedBindingBuilder<S>() {
							@Override
							public void in(IScope<S> scope) {
								IProvider<S> newProv = scope.scope(prov);
								if(newProv.getClass() != prov.getClass()){
									providers.put(impl, newProv);
									sb.setProvider(newProv);
								}
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
					prov = new ProviderFromClass(this, implClass);
					prov = Scopes.SINGLETON.scope(prov);
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
