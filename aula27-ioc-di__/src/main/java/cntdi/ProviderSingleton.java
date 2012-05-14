package cntdi;

import java.lang.reflect.Constructor;

public class ProviderSingleton<S> implements IProvider<S>{

	final SimpleInjector inj;
	final Class<S> impl;
	S implValue;
	
	public ProviderSingleton(SimpleInjector inj, Class<S> impl) {
		this.inj = inj;
		this.impl = impl;
	}
	
	@Override
	public S get() {
		if(implValue != null) return implValue;
		Constructor<?> [] ctors = impl.getConstructors();
		Constructor<?> target = null;
		Class<?> [] paramsClasses = null;
		for (Constructor<?> c : ctors) {
			paramsClasses = c.getParameterTypes();
			int count = 0;
			for (Class<?> p : paramsClasses) {
				if(!inj.contains(p)){
					break;
				}
				count++;
			}
			if(count == paramsClasses.length){
				target = c;
				break;
			}
		}
		if(target == null)
			throw new MissingInjectionsException("There are missing injections for class: " + impl.getName());

		Object [] params = new Object[paramsClasses.length];
		for (int i = 0; i < params.length; i++) {
			params[i] = inj.getInstance(paramsClasses[i]);
		}
		try {
			implValue = (S) target.newInstance(params);
			return implValue;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
