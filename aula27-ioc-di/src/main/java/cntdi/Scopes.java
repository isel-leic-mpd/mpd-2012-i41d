package cntdi;

public abstract class Scopes{
	
	public final static IScope SINGLETON = new IScope() {
		@Override
		public IProvider scope(final IProvider prov) {
			return new IProvider() {
				private Object inst; 
				
				@Override
				public Object get() {
					if(inst != null) return inst;
					inst = prov.get();
					return inst;
				}
			};
		}
	}; 

}
